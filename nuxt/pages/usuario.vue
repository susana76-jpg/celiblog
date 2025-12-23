<template>
  <v-container class="user-profile-page">
    <v-row class="mb-8">
      <v-col cols="12" md="4" class="d-flex justify-center justify-md-start">
        <v-card class="profile-card d-flex flex-column align-center" flat>
          <div class="profile-image-container">
            <v-img
              :src="'/img/avatar_vera.png'"
              alt="Imagen de Perfil"
              class="profile-image"
            ></v-img>
            <v-icon class="edit-icon" color="#8B7B44">mdi-pencil</v-icon>
          </div>

          <div class="profile-fields mt-6">
            <v-text-field
              v-model="formData.nombre"
              prepend-inner-icon="mdi-account"
              label="Nombre"
              variant="solo"
              flat
              class="field-input"
            ></v-text-field>
            <v-text-field
              v-model="formData.email"
              prepend-inner-icon="mdi-email"
              label="Email"
              variant="solo"
              flat
              class="field-input"
              readonly
            ></v-text-field>
            <v-btn
              color="error"
              variant="outlined"
              class="mt-4 mb-4"
              block
              @click="handleDeleteAccount"
            >
              Eliminar Cuenta
            </v-btn>
            
          </div>

          <v-btn
            color="#8B7B44"
            class="mt-4"
            @click="handleSubmit"
            :loading="isSubmitting"
            block
          >
            Guardar Cambios
          </v-btn>
          <v-alert
            v-if="statusMessage"
            :type="statusMessage.includes('Error') ? 'error' : 'success'"
            class="mt-4 w-100 text-center"
            dense
            prominent
          >
            {{ statusMessage }}
          </v-alert>
        </v-card>
      </v-col>

      <v-col cols="12" md="8">
        <v-row dense>
          <v-col v-for="(stat, index) in contadores" :key="index" cols="6" sm="3">
            <v-card class="stat-card" flat>
              <div class="stat-content">
                <v-icon class="stat-icon" size="36">{{ stat.icon }}</v-icon>
                <div class="stat-number">{{ stat.number }}</div>
                <div class="stat-text">{{ stat.text }}</div>
              </div>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12">
        <v-toolbar flat class="content-toolbar">
          <v-btn-toggle
            v-model="barraContenido"
            color="#8B7B44"
            mandatory
            group
          >
            <v-btn value="restaurantes" class="content-btn"><v-icon start>mdi-silverware-fork-knife</v-icon>Restaurantes</v-btn>
            <v-btn value="recetas" class="content-btn"><v-icon start>mdi-food-variant</v-icon>Recetas</v-btn>
            <v-btn value="consejos" class="content-btn"><v-icon start>mdi-lightbulb-on-outline</v-icon>Consejos</v-btn>
            <v-btn value="comentarios" class="content-btn"><v-icon start>mdi-comment-text-multiple-outline</v-icon>Comentarios</v-btn>
          </v-btn-toggle>
        </v-toolbar>
      </v-col>

      <v-col cols="12">
    <v-row v-if="isContentLoading" class="pa-4">
        <v-col cols="12" class="text-center">
            <v-progress-circular indeterminate color="#8B7B44"></v-progress-circular>
            <p class="mt-2">Cargando {{ barraContenido}}...</p>
        </v-col>
    </v-row>

    <v-row v-else-if="contentItems.length > 0">
        <v-col v-for="item in contentItems" :key="item.id" cols="12" sm="6" md="3">
            <v-card class="content-item-card" flat hover @click="goToDetail(item)" style="cursor: pointer">
                <v-img 
                    :src="item.imagenUrl"
                    @error="item.imagenUrl= '/img/consejos/default.jpg'"
                    height="200"
                    cover 
                ></v-img>
                <v-card-text>
                    <div class="font-weight-bold">{{ item.titulo }}</div>
                    <div class="text-caption text-medium-emphasis">
                        {{ item.contenidoResumen || `Item de ${barraContenido}` }}
                    </div>
                </v-card-text>
            </v-card>
        </v-col>
    </v-row>

    <v-row v-else class="pa-4">
        <v-col cols="12">
            <v-alert type="info" variant="tonal">
                No se encontraron favoritos para la categoría **{{ barraContenido }}**.
            </v-alert>
        </v-col>
    </v-row>
</v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
// Asumo que useApiFetch y useAuthStore se mantienen
import { useApiFetch } from '../composables/useApiFetch'; 
import { useAuthStore } from '../composables/useAuthStore'; 
import { useRouter } from 'vue-router';
// Inicialización del Store de Autenticación
const authStore = useAuthStore();
const router = useRouter();

//barra de navegacion de los contenidos
const barraContenido = ref('restaurantes');


// contadores de favoritos
const contadores = ref([
    { icon: 'mdi-food-variant', number: '0', text: 'Recetas' },
    { icon: 'mdi-silverware-fork-knife', number: '0', text: 'Restaurantes' },
    { icon: 'mdi-lightbulb-on-outline', number: '0', text: 'Post' },
    { icon: 'mdi-star-outline', number: '0.0', text: 'Valoración' },
]);


/**
 * Interface para los campos del formulario de Perfil.
 * Solo necesitamos los campos que el usuario puede editar.
 */
interface UserProfileForm {
  nombre: string;
  email: string;
}
interface UserDataResponse {
  nombre: string;
  email: string;
}
interface ContentItem {
    id: number;
    titulo: string;
    imagenUrl: string; 
    contenidoResumen?: string;
}
// interfaz para obtener el contador de favoritos
interface UsuariocontFav{
    numRestaurantes: number;
    numRecetas: number;
    numPost: number;
    numComentarios: number;
    numUsuarios: number;
  }
// iNTERFAZ PARA FAVORITOS
interface FavoritoItem {
    id: number;
    tipo_referencia: 'RESTAURANTE' | 'RECETA' | 'POST' | 'COMENTARIO'; 
    id_referencia: number; 
    titulo: string;
    imagenUrl: string; 
}
// Estado del formulario (inicializado con valores vacíos)
const formData = ref<UserProfileForm>({
  nombre: '',
  email: '',
});

const contentItems = ref<ContentItem[]>([]);
const mapActiveContentToApiReference = (content: string): string | null => {
    switch (content) {
        case 'restaurantes':
            return 'RESTAURANTE';
        case 'recetas':
            return 'RECETA';
        case 'consejos':
            return 'POST'; 
        case 'comentarios':
            return 'COMENTARIO';
        default:
            return null; 
    }
}

//funcion para obtener la media del usuario
const fetchUserStats = async () => {
    
    const API_URL = '/api/favoritos/estadistica'; 

    try {
        const data = await useApiFetch(API_URL); 
        const userFav = data as UsuariocontFav;

        contadores.value = [
        { icon: 'mdi-silverware-fork-knife', number: String(userFav.numRestaurantes || 0), text: 'Restaurantes favoritos' },
        { icon: 'mdi-lightbulb-on-outline', number: String(userFav.numPost || 0), text: 'Consejos favoritos' },
        { icon: 'mdi-food-variant', number: String(userFav.numRecetas || 0), text: 'Recetas favoritas' },
      ];
    } catch (error) {
        console.error('Error al cargar las estadísticas del usuario:', error);
        // Dejar los valores simulados o ponerlos a cero en caso de error
    }
};

onMounted(async () => {
  
    const user = authStore.user.value;
    if (user) {
        formData.value.nombre = user.nombre || '';
        formData.value.email = user.email || '';
    } else {
        await fetchUserData();
    }
    await fetchUserStats();
    
    const initialRef = mapActiveContentToApiReference(barraContenido.value);

    await fetchContent(initialRef);
});
watch(barraContenido, (newContent) => {
    const reference = mapActiveContentToApiReference(newContent);
    fetchContent(reference);
});


const fetchUserData = async () => {
  const API_URL = '/api/usuario/byId'; // Endpoint para obtener el perfil (necesito el ID)
  
  
  try {
    
    const data = await useApiFetch<UserProfileForm>(API_URL); 
    
    // Asumimos que la API devuelve un objeto con nombre y email
    formData.value.nombre = data.nombre || '';
    formData.value.email = data.email || '';
    
  } catch (error) {
    console.error('Error al cargar los datos del usuario:', error);
    statusMessage.value = 'No se pudieron cargar los datos del perfil.';
  }
};


// Estados de la UI
const statusMessage = ref('');
const isSubmitting = ref(false);
const isContentLoading = ref(false);

const mostrarPassword = ref(false);



const fetchContent = async (reference: string | null) => {
  if (!reference) {
    contentItems.value = [];
    return;
  }

  isContentLoading.value = true;

  const API_URL = `/api/favoritos/byReferencia?tipoReferencia=${reference}`;

  try {
    const data = await useApiFetch(API_URL);
    console.log('RAW favoritos:', data);
    const processedItems = (data as any[]).map(item => {

      // RECETA
      if (reference === 'RECETA') {
        return {
          id: item.idReceta,
          titulo: item.titulo || `Receta ${item.idReceta}`,
          imagenUrl: `/img/recetas/${item.imagenUrl}`
        };
      }

      //RESTAURANTE
      if (reference === 'RESTAURANTE') {
        return {
          id: item.idRestaurante,
          titulo: item.titulo || `Restaurante ${item.idRestaurante}`,
          imagenUrl: `/img/restaurantes/${item.imagenUrl}`
        };
      }

      // POST (consejos) 
      if (reference === 'POST') { 
        return {
          id: item.idPost,
          titulo: item.titulo || `Consejos ${item.idPost}`,
          imagenUrl: `/img/consejos/consejo${item.idPost}.jpg`
        };
      };

      
      return {
        id: item.id_referencia,
        titulo: 'Favorito',
        imagenUrl: `https://source.unsplash.com/400x300/?restaurant`
      };

    });

    contentItems.value = processedItems;

    console.log(`Contenido de ${reference} cargado:`, contentItems.value);

  } catch (error) {
    console.error(`Error al cargar ${reference}:`, error);
    contentItems.value = [];
  } finally {
    isContentLoading.value = false;
  }
};

/**
 * Maneja la actualización del perfil de usuario.
 */
const handleSubmit = async () => {
  isSubmitting.value = true;
  statusMessage.value = '';

  if (!formData.value.nombre.trim()) {
    statusMessage.value = 'El nombre es obligatorio.';
    isSubmitting.value = false;
    return;
  }

  try {
    const response = await useApiFetch('/api/usuario/update', {
      method: 'PUT',
      body: { nombre: formData.value.nombre }
    });

    statusMessage.value = '¡Nombre actualizado con éxito!';
  } catch (err) {
    statusMessage.value = 'Error al actualizar el nombre.';
  } finally {
    isSubmitting.value = false;
  }
};
//funcion eliminar cuenta
const handleDeleteAccount = async () => {
  const confirmDelete = confirm('¿Estás seguro de que deseas eliminar tu cuenta? Esta acción es irreversible.');

  if (!confirmDelete) return;

  try {
    await useApiFetch('/api/usuario/delete', { method: 'DELETE' });

    authStore.logout(); 
    window.location.href = '/'; 
  } catch (err) {
    statusMessage.value = 'Error al eliminar la cuenta.';
  }
};
// links para redirigir a los objetos favoritos
const goToDetail = (item: any) => {
  if (barraContenido.value === 'recetas') {
    router.push(`/recetas/${item.id}`);
  }
  if (barraContenido.value === 'restaurantes') {
    router.push(`/restaurantes/${item.id}`);
  }
  if (barraContenido.value === 'consejos') {
    router.push(`/consejos/${item.id}`);
  }
};
</script>

<style lang="scss" scoped>
@import '@/assets/styles/variables'; 
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Arial&display=swap');


$primary-color: #836A02; 
$secondary-color: #333333; 

.user-profile-page {
  background-color: #E1DCC4;
  padding: 32px;
  padding-top: $spacing-xl;
  padding-bottom: $spacing-xl;
}


.profile-card {
  max-width: 350px;
  width: 100%;
  
}

.profile-image-container {
  position: relative;
  border: 4px solid #8B7B44;
  border-radius: 50%;
  overflow: hidden;
  width: 140px;
  height: 140px;
  background-color: #DCD5B2;
  
  box-shadow: 0 0 0 5px rgba($primary-color, 0.4), 0 0 0 7px rgba($primary-color, 0.2);
  margin-bottom: $spacing-sm;
   margin-top: 24px;
  .profile-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .edit-icon {
    position: absolute;
    bottom: 5px;
    right: 5px;
    background-color: white;
    border-radius: 50%;
    padding: 4px;
    border: 1px solid $primary-color;
    cursor: pointer;
    font-size: 1.2rem;
  }
}

.profile-fields {
  width: 100%;
  max-width: 300px; 
  .field-input {
    
    :deep(.v-input__control) {
        .v-field {
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 8px 12px;
            background-color: #f8f8f8 !important; // Fondo muy claro
            box-shadow: none !important;
            margin-bottom: $spacing-xs;
        }
        .v-field__prepend-inner > .v-icon {
            color: $primary-color !important;
        }
    }
    
    :deep(input) {
        color: $secondary-color !important;
        font-weight: 500;
    }
  }
}


.stat-card {
  border: 2px solid #8B7B44;
  border-radius: 6px;
  text-align: center;
  padding: 16px;
  height: 160px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.stat-icon {
  color: #8B7B44;
}

.stat-number {
  font-size: 32px;
  font-weight: bold;
}

.stat-text {
  font-size: 14px;
}


.content-toolbar {
    background: white;
    border-bottom: 2px solid #8B7B44;
    padding: 0;
    
    .v-btn-toggle {
        border-radius: 4px;
       
    }

    .content-btn {
        background-color: #E8E8E8 !important; 
        color: $secondary-color !important;
        border-radius: 0 !important;
        margin: 0 4px;

        // Estilo del botón activo
        &.v-btn--active {
            background-color: $primary-color !important;
            color: white !important;
            font-weight: 600;
        }
        
        &:first-child {
            border-top-left-radius: 4px !important;
            border-bottom-left-radius: 4px !important;
            margin-left: 0;
        }

        &:last-child {
            border-top-right-radius: 4px !important;
            border-bottom-right-radius: 4px !important;
            margin-right: 0;
        }
    }
}


.content-item-card {
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}


@media (max-width: 960px) {
    .profile-card {
        max-width: 100%;
        margin-bottom: $spacing-lg;
    }
    .stat-card {
        margin-bottom: $spacing-sm;
    }
}
</style>

<template>
  <v-container class="user-profile-page">
    <v-row class="mb-8">
      <v-col cols="12" md="4" class="d-flex justify-center justify-md-start">
        <v-card class="profile-card d-flex flex-column align-center" flat>
          <div class="profile-image-container">
            <v-img
              :src="'/img/img1.png'"
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
            ></v-text-field>

            <v-btn 
              variant="text" 
              color="#8B7B44" 
              class="mt-2 mb-4"
              @click="mostrarPassword = !mostrarPassword"
              block
              size="small"
            >
              <v-icon :icon="mostrarPassword ? 'mdi-lock-open-outline' : 'mdi-lock-outline'" start></v-icon>
              {{ mostrarPassword ? 'Cancelar cambio de contraseña' : 'Cambiar contraseña' }}
            </v-btn>

            <div v-if="mostrarPassword">
              <v-divider class="my-4"></v-divider>
              
              <p class="text-subtitle-2 text-medium-emphasis mb-2">Introduce las contraseñas</p>
              
              <v-text-field
                v-model="formData.currentPassword"
                prepend-inner-icon="mdi-lock-open-check-outline"
                label="Contraseña Actual"
                variant="solo"
                flat
                type="password"
                class="field-input"
              ></v-text-field>
              
              <v-text-field
                v-model="formData.newPassword"
                prepend-inner-icon="mdi-lock-plus-outline"
                label="Nueva Contraseña"
                variant="solo"
                flat
                type="password"
                class="field-input"
              ></v-text-field>
              
              <v-text-field
                v-model="formData.confirmNewPassword"
                prepend-inner-icon="mdi-lock-check-outline"
                label="Confirmar Nueva Contraseña"
                variant="solo"
                flat
                type="password"
                class="field-input"
              ></v-text-field>
            </div>
            
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
            <v-card class="content-item-card" flat>
                <v-img 
                    :src="item.imagenUrl" 
                    cover
                    height="200"
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

// Inicialización del Store de Autenticación
const authStore = useAuthStore();


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
  // Campos para cambiar la contraseña
  currentPassword?: string;
  newPassword?: string;
  confirmNewPassword?: string;
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
    receta: number;
    restaurante: number;
    post: number;
    valoracionMedia: number;
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
  currentPassword: '',
  newPassword: '',
  confirmNewPassword: '',
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
    
    const API_URL = '/api/usuario/stats'; //pendiente camiar cuando esté creada

    try {
        const data = await useApiFetch(API_URL); 
        const userFav = data as UsuariocontFav;

        // 
        contadores.value = [
            { icon: 'mdi-food-variant', number: String(userFav.receta || 0), text: 'Recetas' },
            { icon: 'mdi-silverware-fork-knife', number: String(userFav.restaurante| 0), text: 'Restaurantes' },
            { icon: 'mdi-lightbulb-on-outline', number: String(userFav.post || 0), text: 'Post' },
            { icon: 'mdi-star-outline', number: (userFav .valoracionMedia || 0).toFixed(1), text: 'Valoración' },
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
    
    const initialRef = mapActiveContentToApiReference(activeContent.value);
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
const activeContent = ref('restaurantes'); 
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
    
    // El endpoint necesita saber qué referencia buscar
    const API_URL = `/api/favoritos/byReferencia?tipoReferencia=${reference}`; 

      try {
        const data = await useApiFetch(API_URL);
        
        const processedItems = (data as ContentItem[]).map(item => {
            let ruta = '';
            
            
            if (reference === 'recetas') {
                ruta = '/img/recetas/'; 
            } else if (reference === 'restaurantes') {
                ruta = '/img/restaurantes/'; 
            } else if (reference === 'consejos') {
                ruta = '/img/consejos/'; 
            }
            
            const url = ruta + item.imagenUrl;

            return {
                ...item,//operador de propagacion
                imagenUrl: item.imagenUrl.startsWith('/') || item.imagenUrl.startsWith('http') 
                           ? item.imagenUrl 
                           : url
            };
        });
        // ----------------------------------------------------

        contentItems.value = processedItems; 
        console.log(`Contenido de ${reference} cargado y rutas corregidas:`, contentItems.value);

    } catch (error) {
        console.error(`Error al cargar el contenido de ${reference}:`, error);
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

  
  if (formData.value.nombre.trim() === '' || formData.value.email.trim() === '') {
    statusMessage.value = 'El nombre y el email son obligatorios.';
    isSubmitting.value = false;
    return;
  }
  
  
  let dataToSend: any = {
    nombre: formData.value.nombre,
    email: formData.value.email,
  };
  
  // Lógica de validación de cambio de contraseña
  const isPasswordChangeAttempt = formData.value.newPassword || formData.value.currentPassword || formData.value.confirmNewPassword;

  if (isPasswordChangeAttempt) {
    if (!formData.value.currentPassword) {
      statusMessage.value = 'Debes introducir tu contraseña actual para cambiarla.';
      isSubmitting.value = false;
      return;
    }
    if (formData.value.newPassword !== formData.value.confirmNewPassword) {
      statusMessage.value = 'La nueva contraseña y la confirmación no coinciden.';
      isSubmitting.value = false;
      return;
    }
    if (formData.value.newPassword && formData.value.newPassword.length < 6) {
      statusMessage.value = 'La nueva contraseña debe tener al menos 6 caracteres.';
      isSubmitting.value = false;
      return;
    }
    
   
    dataToSend.currentPassword = formData.value.currentPassword;
    dataToSend.newPassword = formData.value.newPassword;
  }
  
  // Endpoint de Actualización de Perfil
  const endpoint = '/api/usuario/update'; // Asumo este endpoint para la actualización
  
  try {
    const responseData = await useApiFetch(endpoint, {
      method: 'PUT', 
      body: dataToSend, 
    });

    
    console.log('Respuesta de actualización:', responseData);
    statusMessage.value = '¡Tu perfil ha sido actualizado con éxito!';
    
    
    
    // Limpiar campos de contraseña tras un cambio exitoso
    formData.value.currentPassword = '';
    formData.value.newPassword = '';
    formData.value.confirmNewPassword = '';

  } catch (error) {
    // CAPTURA DE ERRORES 
    console.error('Error al actualizar el perfil:', error);
    
    const errorDetail = (error as any)?.data?.message || (error as any)?.message || 'Error desconocido del servidor.';
    
    // Detalle de errores específicos, por ejemplo, si la contraseña actual es incorrecta
    if ((error as any)?.response?.status === 400 && errorDetail.includes('contraseña actual')) {
         statusMessage.value = 'Error: La contraseña actual es incorrecta.';
    } else {
         statusMessage.value = `Error al actualizar tu perfil. Detalle: ${errorDetail}`;
    }

  } finally {
    isSubmitting.value = false;
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
  padding-top: $spacing-xl;
  padding-bottom: $spacing-xl;
}


.profile-card {
  max-width: 350px;
  width: 100%;
  
}

.profile-image-container {
  position: relative;
  width: 150px;
  height: 150px;
  border-radius: 50%;
  overflow: hidden;
  
  box-shadow: 0 0 0 5px rgba($primary-color, 0.4), 0 0 0 7px rgba($primary-color, 0.2);
  margin-bottom: $spacing-sm;

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
  text-align: center;
  border: 1px solid rgba($primary-color, 0.3); // Borde delgado
  border-radius: 4px;
  height: 100%; 
  display: flex;
  align-items: center;
  justify-content: center;
  padding: $spacing-md 0; // Padding interno

  .stat-content {
    padding: $spacing-sm;
  }

  .stat-icon {
    color: $primary-color;
    margin-bottom: $spacing-xs;
  }

  .stat-number {
    font-size: 2.2rem;
    font-weight: 700;
    color: $primary-color;
    line-height: 1.1;
  }

  .stat-text {
    font-size: 0.9rem;
    color: $secondary-color;
    margin-top: $spacing-xs;
  }
}


.content-toolbar {
    background-color: transparent !important;
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

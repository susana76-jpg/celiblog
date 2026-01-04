<template>
  <v-container class="user-profile-page">
    <v-row class="mb-8">
      <v-col cols="12" md="4" class="d-flex justify-center justify-md-start">
        <v-card class="profile-card d-flex flex-column align-center" flat>
          <div class="profile-image-container">
            <v-img
              :src="'/img/avatar_1.png'"
              alt="Imagen de Perfil"
              class="profile-image"
            ></v-img>
            <v-icon class="edit-icon" color="#8B7B44">mdi-pencil</v-icon>
          </div>
          <!--Datos Personales-->
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
              @click="confirmDeleteAccount"
            >
              Eliminar Cuenta
            </v-btn>
            
          </div>
          <!--boton guardar cambios-->
          <v-btn color="#8B7B44" class="mt-4" @click="handleSubmit" :loading="isSubmitting" block>
            Guardar Cambios
          </v-btn>
          <v-alert v-if="statusMessage" :type="statusMessage.includes('Error') ? 'error' : 'success'" class="mt-4 w-100 text-center" dense prominent>
            {{ statusMessage }}
          </v-alert>
        </v-card>
      </v-col>
      <!--Fichas contadores favoritos y comentario-->
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
          <v-btn-toggle v-model="barraContenido" color="#8B7B44" mandatory group>
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
    <!--Nueva ficha comentarios-->
    <v-row v-else-if="contentItems.length > 0">
      <!-- =============== COMENTARIOS ================= -->
     <template v-if="barraContenido === 'comentarios'">
        <v-col cols="12" v-for="item in contentItems" :key="item.id">
          <v-card flat class="comment-card pa-4 mb-4">
            <div class="d-flex justify-space-between align-center mb-2">
              <div>
                    <h4 class="font-weight-bold">{{ item.titulo }}</h4>
                    <div class="text-caption text-medium-emphasis">
                      {{ item.fecha }}
                    </div>
              </div>
              <!-- Todos los comentarios. Los Pendientes o Rechazados se pueden editar o eliminar -->
              <div class="d-flex align-center">
                <v-rating :model-value="item.valoracion" readonly dense size="18" color="#8B7B44" />
                <!--Boton Editar-->
                <v-btn v-if="item.estado !== 'APROBADO'"icon variant="text" @click.stop="abrirEditarComentario(item)">
                <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <!--Boton Eliminar-->
                <v-btn v-if="item.estado !== 'APROBADO'" icon variant="text"  color="error" @click.stop="abrirEliminarComentario(item)">
                <v-icon>mdi-delete</v-icon>
                 </v-btn>
              </div>
            </div>
            <p class="mb-0">{{ item.contenidoResumen }}</p>
          </v-card>
        </v-col>
      </template>

        <!-- =============== RESTO DE CONTENIDOS ================= -->
        <template v-else>
          <v-col v-for="item in contentItems":key="item.id" cols="12" sm="6" md="3">
            <v-card class="content-item-card" flat hover style="cursor: pointer"  @click="goToDetail(item)">
              <v-img :src="item.imagenUrl" height="200" cover />
              <v-card-text>
                <div class="font-weight-bold">{{ item.titulo }}</div>
              </v-card-text>
            </v-card>
          </v-col>
        </template>
    </v-row>

    <!--Mensaje no hay favoritos-->
    <v-row v-else class="pa-4">
        <v-col cols="12">
            <v-alert type="info" variant="tonal">
                No se encontraron favoritos para la categoría **{{ barraContenido }}**.
            </v-alert>
        </v-col>
    </v-row>
</v-col>
    </v-row>
      <!--Cuadro dialogo editar comentarios-->
    <v-dialog v-model="dialogEditarComentario" max-width="600">
        <v-card>
          <v-card-title>Editar comentario</v-card-title>
          <v-card-text>
            <v-textarea v-model="comentarioEditado" label="Comentario" auto-grow counter maxlength="255"/>
            <v-alert type="info" variant="tonal" class="mt-2">
              Al guardar, el comentario volverá a estar pendiente de aprobación.
            </v-alert>
          </v-card-text>
          <v-card-actions>
            <v-spacer />
            <v-btn variant="text" @click="dialogEditarComentario = false">
              Cancelar
            </v-btn>
            <v-btn color="#8B7B44" @click="guardarComentarioEditado">
              Guardar
            </v-btn>
          </v-card-actions>
        </v-card>
  </v-dialog>
  <!--Cuadro dialogo eliminar comentarios-->
  <v-dialog v-model="dialogEliminarComentario" max-width="500">
        <v-card>
          <v-card-title class="text-h6">
          Eliminar comentario
          </v-card-title>
          <v-card-text>
          ¿Estás seguro de que deseas eliminar este comentario?
          <v-alert type="warning" variant="tonal" class="mt-2">
            Esta acción no se puede deshacer.
          </v-alert>
          </v-card-text>
          <v-card-actions>
            <v-spacer />
            <v-btn variant="text" @click="dialogEliminarComentario = false">
              Cancelar
            </v-btn>
            <v-btn color="error" @click="confirmarEliminarComentario">
              Eliminar
            </v-btn>
          </v-card-actions>
        </v-card>
  </v-dialog>
  <!-- Popup comentario eliminado -->
  <v-dialog v-model="dialogComentarioEliminado" max-width="450">
      <v-card class="pa-2">
        <v-card-title class="text-h6 text-center">
          Comentario eliminado
        </v-card-title>
        <v-card-text class="text-center">
          <v-icon color="success" size="48" class="mb-2">
            mdi-check-circle
          </v-icon>
          <p>
            El comentario se ha eliminado correctamente.
          </p>
        </v-card-text>
        <v-card-actions class="justify-center">
          <v-btn color="#8B7B44"  @click="dialogComentarioEliminado = false">
            Aceptar
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
  </v-container>
</template>

<script setup lang="ts">
  /*Vista del perfil de usuario
  Funcionalidades: -Editar Nombre
                   -VEr su contenido favorito
                   -Ver comentarios
                   -Editar/Borrar comentarios pendientes o rechazados
                   -Eliminar Cuenta*/
import { ref, onMounted, watch } from 'vue';
import { useApiFetch } from '../composables/useApiFetch'; 
import { useAuthStore } from '../composables/useAuthStore'; 
import { useRouter } from 'vue-router';
import { useConfirmDialog } from '../composables/useConfirmDialog';



// Inicialización del Store de Autenticación
const authStore = useAuthStore();
const router = useRouter();

//barra de navegacion de los contenidos
type BarraContenido= 'restaurantes'|'recetas'|'consejos'|'comentarios';
const barraContenido = ref<BarraContenido>('restaurantes');
//edicion comentarios
const dialogEditarComentario = ref(false);
const comentarioEditando = ref<ContentItem | null>(null);
const comentarioEditado = ref('');
//eliminar comentarios
const dialogEliminarComentario = ref(false);
const comentarioAEliminar = ref<ContentItem | null>(null);
const dialogComentarioEliminado=ref(false);
//confirmDialog
const {showConfirmDialog} = useConfirmDialog();

const abrirEditarComentario = (comentario: ContentItem) => {
  comentarioEditando.value = comentario;
  comentarioEditado.value = comentario.contenidoResumen || '';
  dialogEditarComentario.value = true;
};


// contadores de favoritos
const contadores = ref([
    { icon: 'mdi-food-variant', number: '0', text: 'Recetas' },
    { icon: 'mdi-silverware-fork-knife', number: '0', text: 'Restaurantes' },
    { icon: 'mdi-lightbulb-on-outline', number: '0', text: 'Post' },
    { icon: 'mdi-star-outline', number: '0.0', text: 'Comentarios' },
]);
const abrirEliminarComentario = (comentario: ContentItem) => {
  comentarioAEliminar.value = comentario;
  dialogEliminarComentario.value = true;
};

/**
 * Interface para los campos del formulario de Perfil.
 * Solo necesitamos los campos que el usuario puede editar.
 */
interface UserProfileForm {
  nombre: string;
  email: string;
}

interface ContentItem {
  id: number;
  titulo: string;
  subtitulo: string;
  contenidoResumen?: string;
  imagenUrl?: string;
  fecha?: string;
  valoracion?: number;
  estado?: 'APROBADO' | 'PENDIENTE' | 'RECHAZADO';
  idObjetoComentado: number;
}
// interfaz para obtener el contador de favoritos
interface UsuariocontFav{
    numRestaurantes: number;
    numRecetas: number;
    numPost: number;
    numComentarios: number;
  }

// Estado del formulario (inicializado con valores vacíos)
const formData = ref<UserProfileForm>({
  nombre: '',
  email: '',
});

const contentItems = ref<ContentItem[]>([]);
const mapActiveContentToApiReference = (content: BarraContenido): string | null => {
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
        { icon: 'mdi-comment-text-multiple-outline', number: String(userFav.numComentarios), text: 'Comentarios'}
      ];
    } catch (error) {
        console.error('Error al cargar las estadísticas del usuario:', error);
        // Dejar los valores simulados o ponerlos a cero en caso de error
    }
};
// editar comentarios --  solo PENDIENTES o RECHAZADOS--al guardarlo vuelve a pendiente
const guardarComentarioEditado = async () => {
  const comentario = comentarioEditando.value;
  if (!comentario) return;

  try {
    await useApiFetch('/api/comentario/update', {
      method: 'PUT',
      params: {
        idComentario: comentario.id
      },
      body: {
        titulo: comentario.titulo ?? '',
        subtitulo: comentario.subtitulo ?? '',
        contenido: comentarioEditado.value,
        comentarioUrl: null,
        idObjetoComentado: comentario.idObjetoComentado,
        valoracion: comentario.valoracion ?? 0
      }
    });

    dialogEditarComentario.value = false;
    comentarioEditando.value = null;
    comentarioEditado.value = '';

    statusMessage.value =
      'Comentario actualizado. Quedará pendiente de aprobación.';

    await fetchContent('COMENTARIO');
    await fetchUserStats();

  } catch (error) {
    statusMessage.value = 'Error al actualizar el comentario.';
    console.error(error);
  }
};

//eliminar comentario -- el usuario no puede eliminarlo una vez aprobado
const confirmarEliminarComentario = async () => {
  const comentario = comentarioAEliminar.value;
  if (!comentario) return;

  try {
    await useApiFetch('/api/comentario/delete', {
      method: 'DELETE',
      params: {
        idComentario: comentario.id,
      }
    });

    dialogEliminarComentario.value = false;
    comentarioAEliminar.value = null;

    //statusMessage.value = 'Comentario eliminado correctamente.';
    

    await fetchContent('COMENTARIO');
    await fetchUserStats();
    dialogComentarioEliminado.value= true;

  } catch (error) {
    statusMessage.value = 'Error al eliminar el comentario.';
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
/*Carga el contenido a la pestaña seleccionada
  -comentarios realizados por el usuario
  -Favoritos(post,restaurantes,recetas)*/
const fetchContent = async (reference: string | null) => {
  if (!reference) {
    contentItems.value = [];
    return;
  }

  isContentLoading.value = true;
  statusMessage.value = '';

  try {
    /* ===========================
       COMENTARIOS DEL USUARIO
       =========================== */
        if (reference === 'COMENTARIO') {
          const data = await useApiFetch('/api/publicacion/byUsuario?objetos=COMENTARIO' );// revisar el endpoint en swagger, solo devuelve los aprobados

          const comentarios = data as any[];

          contentItems.value = comentarios.map(c => ({
            id: c.idComentario,
            titulo: c.titulo || 'Comentario',
            subtitulo: c.subtitulo ?? '',
            contenidoResumen: c.contenido,
            fecha: c.fechaCreacion,
            idObjetoComentado: c.idObjetoComentado,
            valoracion: c.valoracion,
            estado: c.estado
          }));
          const numComentarios = comentarios.filter(c => c.estado === 'APROBADO').length;// solo los aprobados

          contadores.value = contadores.value.map(stat =>
          stat.text === 'Comentarios' ? { ...stat, number: String(numComentarios) }: stat);
      
          return;
        }
    /* ===========================
       FAVORITOS 
       =========================== */
    const API_URL = `/api/favoritos/byReferencia?tipoReferencia=${reference}`;
    const data = await useApiFetch(API_URL);
    
    const processedItems = (data as any[])
      .map(item => {
         //Recetas
        if (reference === 'RECETA') {
          return {
            id: item.idReceta,
            titulo: item.titulo || `Receta ${item.idReceta}`,
            imagenUrl: `/img/recetas/${item.imagenUrl}`
          };
        }
        //Restaurantes
        if (reference === 'RESTAURANTE') {
        return {
          id: item.idRestaurante,
          titulo: item.titulo || `Restaurante ${item.idRestaurante}`,
          imagenUrl: item.imagenUrl
            ? `/img/restaurantes/${item.imagenUrl}`
            : '/img/restaurantes/default.png'
        };
        }
        //Consejos y Post
        if (reference === 'POST') {
          return {
            id: item.idPost,
            titulo: item.titulo || `Consejos ${item.idPost}`,
            imagenUrl: `/img/consejos/consejo${item.idPost}.jpg`
          };
        }

        return null;
      })
      .filter(item => item !== null) as ContentItem[];

    contentItems.value = processedItems;
    

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
/**
 * Muestra el diálogo de confirmación antes de eliminar la cuenta
 */
const confirmDeleteAccount = () => {
  showConfirmDialog(
    'Si eliminas tu cuenta, todos tus datos desaparecerán del sistema. Esta acción es irreversible.',
    async () => {
      await handleDeleteAccount();
    }
  );
};
//Elimina la cuenta del usuario
const handleDeleteAccount = async () => {
  try {
    await useApiFetch('/api/usuario/delete', { method: 'DELETE' });

    authStore.logout(); 
    window.location.href = '/'; 
  } catch (err) {
    statusMessage.value = 'Error al eliminar la cuenta.';
  }
};
// links para redirigir a los objetos favoritos
const goToDetail = (item: ContentItem) => {
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
.comment-card {
  border-bottom: 1px solid #e0e0e0;
}

.comment-card h4 {
  margin: 0;
}
</style>

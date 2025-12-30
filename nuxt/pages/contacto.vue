<template>
  <div class="formulario-con-fondo">
  <v-container fluid class="fill-height d-flex justify-center align-center pa-4">
    <v-card
      class="pa-6 pa-sm-10 rounded-xl elevation-10 w-100"
      max-width="800"
      style="background: white !important;"
    >
        <!-- Crear cuenta o iniciar sesión -->
          
          <div v-if="!authStore.user.value">
              <p class="text-center mt-4 font-weight-bold">
                Para enviar una publicación necesitas una cuenta.
              </p>

              <v-row justify="center" class="mt-2">
                <v-col cols="12" sm="6" md="5">
                  <v-btn color="#836a02" size="large" block class="mt-4 font-weight-bold" to="/inicio">
                    <v-icon left>mdi-login</v-icon>
                    Iniciar sesión
                  </v-btn>
                </v-col>

                <v-col cols="12" sm="6" md="5">
                  <v-btn color="#836a02"size="large" block class="mt-4 font-weight-bold" to="/registro">
                    <v-icon left>mdi-account-plus</v-icon>
                    Crear cuenta
                  </v-btn>
                </v-col>
              </v-row>
        </div>
    

      <v-form ref="formRef" validate-on="submit" @submit.prevent="handleSubmit" class="space-y-6 text-font-poppins">
          <v-row class="mb-2"> 
            <v-col cols="12">
              <v-text-field
                v-model="formData.nombre"
                label="Tu Nombre *"
                :rules="[(v: any) => !!v || 'El nombre es obligatorio']"
                prepend-inner-icon="mdi-account"
                variant="outlined"
                color="#836a02" required
                clearable
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row class="mb-2">
            <v-col cols="12">
              <v-text-field
                v-model="formData.email"
                label="Tu Correo Electrónico *"
                :rules="[
    (                v: any) => !!v || 'El email es obligatorio',
    (                v: string) => /.+@.+\..+/.test(v) || 'El email debe ser válido',
                ]"
                prepend-inner-icon="mdi-email"
                variant="outlined"
                color="#836a02" required
                clearable
              ></v-text-field>
            </v-col>
          </v-row>
          

        <div class="text-center mb-8 section-main__title">
          <h1 class="font-weight-black text-gold">
            Contacto y Publicación
          </h1>
          <p class="mt-2 text-subtitle-1 text-medium-emphasis text-gray-darken-1">
            Únete a nuestra comunidad, ¿Sobre qué quieres publicar?
          </p>

          <v-row class="mt-5 mb-5">
            <v-col cols="12">
              <v-select
                v-model="tipoPublicacion"
                :items="opcionesPublicacion"
                label="Tipo de Publicación *"
                :rules="[(v: any) => !!v || 'Elige sección']"
                prepend-inner-icon="mdi-format-list-bulleted"
                variant="outlined"
                color="#836a02"
                required
              ></v-select>
            </v-col>
          </v-row>
        </div>
          
          <template v-if="tipoPublicacion === 'Restaurante'">
            <v-card-title class="pa-0 mt-4 mb-4 text-left text-h6 font-weight-bold text-gold">
              Detalles de la publicación (Restaurante)
            </v-card-title>

            <v-row>
              <v-col cols="12">
                <v-text-field
                  v-model="formData.restauranteNombre"
                  label="Nombre del Restaurante *"
                  :rules="[(v: any) => !!v || 'El nombre es obligatorio']"
                  prepend-inner-icon="mdi-store"
                  variant="outlined"
                  color="#836a02" required clearable
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="formData.restauranteSubtitulo"
                  label="Descripción breve"
                  prepend-inner-icon="mdi-subtitles-outline"
                  variant="outlined"
                  color="#836a02" clearable
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="formData.restauranteDireccion"
                  label="Dirección *"
                  :rules="[(v: any) => !!v || 'La dirección es obligatoria']"
                  prepend-inner-icon="mdi-map-marker"
                  variant="outlined"
                  color="#836a02" required clearable
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="formData.restauranteUbicacion"
                  label="Ciudad/Ubicación *"
                  :rules="[(v: any) => !!v || 'La ubicación es obligatoria']"
                  prepend-inner-icon="mdi-city"
                  variant="outlined"
                  color="#836a02" required clearable
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model.number="formData.restauranteCodigoPostal"
                  label="Código Postal"
                  prepend-inner-icon="mdi-post"
                  variant="outlined"
                  color="#836a02" type="number" clearable
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="formData.restauranteEmail"
                  label="Email del Restaurante"
                  prepend-inner-icon="mdi-email-send"
                  variant="outlined"
                  color="#836a02" clearable
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="formData.restauranteTelefono"
                  label="Teléfono del Restaurante"
                  prepend-inner-icon="mdi-phone-in-talk"
                  variant="outlined"
                  color="#836a02" type="tel" clearable
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="formData.restauranteWeb"
                  label="Dirección Web (URL)"
                  prepend-inner-icon="mdi-web"
                  variant="outlined"
                  color="#836a02" clearable
                ></v-text-field>
              </v-col>
            </v-row>
            
            <v-row>
              <v-col cols="12">
                <v-textarea
                  v-model="formData.restauranteDescripcion"
                  label="Breve Descripción del Restaurante *"
                  :rules="[(v: any) => !!v || 'La descripción es obligatoria']"
                  prepend-inner-icon="mdi-text"
                  variant="outlined"
                  color="#836a02" rows="4" required clearable
                ></v-textarea>
              </v-col>
            </v-row>
          </template>

          <template v-else-if="tipoPublicacion === 'Receta'">
            <v-card-title class="pa-0 mt-4 mb-4 text-left text-h6 font-weight-bold text-gold">
              Detalles de la publicación (Receta)
            </v-card-title>
            
            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="formData.recetaNombre"
                  label="Título de la Receta *"
                  :rules="[(v: any) => !!v || 'El nombre es obligatorio']"
                  prepend-inner-icon="mdi-chef-hat"
                  variant="outlined"
                  color="#836a02" required clearable
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="formData.recetaSubtitulo"
                  label="Subtítulo"
                  prepend-inner-icon="mdi-subtitles-outline"
                  variant="outlined"
                  color="#836a02" clearable
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="12" md="4">
                <v-select
                  v-model="formData.recetaDificultad"
                  :items="['BAJA', 'MEDIA', 'ALTA']"
                  label="Dificultad *"
                  :rules="[(v: any) => !!v || 'La dificultad es obligatoria']"
                  prepend-inner-icon="mdi-stairs"
                  variant="outlined"
                  color="#836a02" required
                ></v-select>
              </v-col>
              <v-col cols="12" md="4">
                <v-text-field
                  v-model.number="formData.recetaTiempoPreparacion"
                  label="Tiempo Preparación (minutos) *"
                  :rules="[(v: any) => !!v || 'El tiempo es obligatorio']"
                  prepend-inner-icon="mdi-timer-sand"
                  variant="outlined"
                  color="#836a02" type="number" required
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="4">
                <v-text-field
                  v-model.number="formData.recetaComensales"
                  label="Comensales *"
                  :rules="[(v: any) => !!v || 'Comensales es obligatorio']"
                  prepend-inner-icon="mdi-account-group"
                  variant="outlined"
                  color="#836a02" type="number" required
                ></v-text-field>
              </v-col>
            </v-row>
            
            <v-row>
              <v-col cols="12" md="6">
                <v-select
                  v-model="formData.recetaTipoComida"
                  :items="opcionesTipoComida"
                  label="Tipo de Comida *"
                  :rules="[(v: any) => !!v || 'El tipo de comida es obligatorio']"
                  prepend-inner-icon="mdi-food-variant"
                  variant="outlined"
                  color="#836a02" required
                ></v-select>
              </v-col>
              <v-col cols="12" md="6">
                <v-text-field
                  v-model.number="formData.recetaValorEnergetico"
                  label="Valor Energético (kcal) *"
                  :rules="[(v: any) => !!v || 'El valor energético es obligatorio']"
                  prepend-inner-icon="mdi-fire"
                  variant="outlined"
                  color="#836a02" type="number" required
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-textarea
                  v-model="formData.recetaIngredientes"
                  label="Ingredientes *"
                  :rules="[(v: any) => !!v || 'Los ingredientes son obligatorios']"
                  prepend-inner-icon="mdi-receipt-text"
                  variant="outlined"
                  color="#836a02" rows="8" required clearable
                  placeholder="Ejemplo:
                  Harina = 200
                  Azúcar = 50
                  Huevos = 2"
                  hint="Formato: Ingrediente = cantidad • ejemplo: Harina = 200"
                  persistent-hint
                ></v-textarea>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-textarea
                  v-model="formData.recetaPasos"
                  label="Pasos de la Receta *"
                  :rules="[(v: any) => !!v || 'Los pasos de la recetas son obligatorios']"
                  prepend-inner-icon="mdi-receipt-text"
                  variant="outlined"
                  color="#836a02" rows="8" required clearable
                ></v-textarea>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-textarea
                  v-model="formData.recetaDescripcion"
                  label="Descripción de la Receta*"
                  :rules="[(v: any) => !!v || 'La descripción es obligatoria']"
                  prepend-inner-icon="mdi-receipt-text"
                  variant="outlined"
                  color="#836a02" rows="8" required clearable
                ></v-textarea>
              </v-col>
            </v-row>
          </template>

          <v-row v-else>
            <v-col cols="12">
              <v-card-title class="pa-0 mt-4 mb-4 text-left text-h6 font-weight-bold text-gold">
                Detalles de la publicación (Post General)
              </v-card-title>
              <v-text-field
                v-model="formData.postTitulo"
                label="Título del Post *"
                :rules="[(v: any) => !!v || 'El título es obligatorio']"
                prepend-inner-icon="mdi-text-box"
                variant="outlined"
                color="#836a02" required clearable
              ></v-text-field>
              <v-text-field
                v-model="formData.postSubtitulo"
                label="Subtítulo del Post"
                prepend-inner-icon="mdi-subtitles"
                variant="outlined"
                color="#836a02" clearable
              ></v-text-field>
              <v-textarea
                v-model="formData.postContenido"
                label="Contenido del Post *"
                :rules="[(v: any) => !!v || 'El contenido es obligatorio']"
                prepend-inner-icon="mdi-message-text"
                variant="outlined"
                color="#836a02" rows="6"
                required
                clearable
              ></v-textarea>
              <v-text-field
                v-model="formData.postUrl"
                label="Dirección Web"
                prepend-inner-icon="mdi-link"
                variant="outlined"
                color="#836a02" clearable
              ></v-text-field>
            </v-col>  
          </v-row>
          
          <v-alert
            v-if="statusMessage"
            :type="statusMessage.includes('éxito') ? 'success' : 'warning'"
            class="mb-4 rounded-lg"
            variant="tonal"
          >
            {{ statusMessage }}
          </v-alert>

          <v-row justify="end">
            <v-col cols="12" sm="4" md="3"> 
              <v-btn
                :loading="isSubmitting"
                :disabled="!authStore.user.value || isSubmitting"
                color="#836a02" size="large" type="submit"
                block class="mt-4 font-weight-bold"
              >
                <v-icon left>mdi-send</v-icon>
                {{ isSubmitting ? 'Enviando...' : 'Enviar' }}
              </v-btn>
            </v-col>
          </v-row>
        </v-form>
          <v-dialog v-model="showDialog" persistent max-width="420">
              <v-card  class="pa-6 text-center" rounded="xl"elevation="12"style="border: 3px solid #836a02;">
                <v-card-title class="d-flex align-center justify-center text-h6 font-weight-bold" style="color: #836a02 ;">
                  <v-icon size="28" color="green">mdi-check-circle</v-icon>
                  <span class="ml-2">¡Publicación enviada!</span>
                </v-card-title>
                <v-card-text class="mt-3" style="color: #5a5a5a;">
                ¿Quieres enviar otra publicación?
                </v-card-text>
                <v-card-actions class="justify-center mt-4">
                  <!-- botón otra publicación -->
                   <v-btn color="#836a02 " class="px-6" rounded style="border-color: #836a02 E; color: #836a02 ;" @click="showDialog = false">
                    Sí
                  </v-btn>
                  <!-- botón ir a inicio -->
                  <v-btn variant="outlined" class="px-6 ml-3" rounded style="border-color: #836a02 E; color: #836a02 ;"
                  @click="router.push('/')">
                    No
                  </v-btn>
                </v-card-actions>
              </v-card>
          </v-dialog>
      </v-card>
    </v-container>
  </div>
</template>
  
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useApiFetch } from '../composables/useApiFetch'; 
import { useAuthStore } from '../composables/useAuthStore';
import { useRouter } from 'vue-router';



const authStore = useAuthStore();

// Opciones de Selectores
const tipoPublicacion = ref('');
const opcionesPublicacion = ['Restaurante', 'Receta', 'Post'];
const opcionesTipoComida = [
  'DESAYUNO',
  'BRUNCH',
  'ALMUERZO',
  'MERIENDA',
  'CENA',
  'POSTRE',
  'SALADO',
  'DULCE',
];
const opcionesDificultad = ['BAJA', 'MEDIA', 'ALTA']; // Opciones para el campo dificultad
const router=useRouter();
//pop up nueva publicacion
const showDialog = ref(false);

interface ContactForm {
  // Datos del Usuario
  nombre: string;
  email: string;
  
  // Campos Post 
  postTitulo: string;
  postSubtitulo: string;
  postContenido: string;
  postUrl: string; 

  
  // Campos Restaurante 
  restauranteNombre: string;
  restauranteSubtitulo: string;
  restauranteDireccion: string;
  restauranteUbicacion: string; 
  restauranteCodigoPostal: number | null; 
  restauranteWeb: string;
  restauranteTelefono: string; 
  restauranteEmail: string;
  restauranteDescripcion: string;
  

  // Campos Receta 
  recetaNombre: string;
  recetaSubtitulo: string;
  recetaTipoComida: string;
  recetaDescripcion: string;
  recetaIngredientes: string;
  recetaPasos: string;
  recetaDificultad: string;
  recetaTiempoPreparacion: number | null;
  recetaValorEnergetico: number | null;
  recetaComensales: number | null;

  tipoPublicacion: string;
}

// Estado del formulario
const formData = ref<ContactForm>({
  // Usuario
  nombre: '',
  email: '',

  // Post
  postTitulo: '',
  postSubtitulo: '',
  postContenido: '',
  postUrl: '',
  
  
  // Restaurante
  restauranteNombre: '',
  restauranteSubtitulo: '',
  restauranteDireccion: '',
  restauranteUbicacion: '',
  restauranteCodigoPostal: null,
  restauranteWeb: '',
  restauranteTelefono: '',
  restauranteEmail: '',
  restauranteDescripcion: '',
  

  // Receta
  recetaNombre: '',
  recetaSubtitulo: '',
  recetaTipoComida: '',
  recetaDescripcion: '',
  recetaIngredientes: '',
  recetaPasos:'',
  recetaDificultad: '',
  recetaTiempoPreparacion: null,
  recetaValorEnergetico: null,
  recetaComensales: null,

  tipoPublicacion: '',
});

// Autorelleno de datos del usuario cuando se ha iniciado sesión
onMounted(() => {
  const user = authStore.user.value;
  if (user) {
    formData.value.nombre = user.nombre || '';
    formData.value.email = user.email || '';
    
  }
});


const statusMessage = ref('');
const isSubmitting = ref(false);

/**
 * Maneja el envío del formulario, mapeando los datos al formato JSON esperado.
 */
const handleSubmit = async () => {
  isSubmitting.value = true;
  statusMessage.value = '';
// comprobar si el usuario ha iniciado sesión
if (!authStore.user.value) {
  statusMessage.value = 'Debes iniciar sesión o crear una cuenta para poder publicar.';
  isSubmitting.value = false;
  return;
}
// Guardar nombre y email 
const nombreUsuario = formData.value.nombre;
const emailUsuario = formData.value.email;

formData.value.tipoPublicacion = tipoPublicacion.value;
 
 //preparación de los campos de receta - ingredientes y pasos_receta -
 const ingredientes = formData.value.recetaIngredientes;
 const pasos_receta = formData.value.recetaPasos; 

  
const isFormValid = formData.value.nombre.trim() !== '' && formData.value.email.trim() !== '' && formData.value.tipoPublicacion.trim() !== '';

if (!isFormValid) {
    statusMessage.value = 'Por favor, completa todos los campos obligatorios (*).';
    isSubmitting.value = false;
    return;
}

  
let dataToSend: any = {};
let endpoint = '';

try {
    switch (formData.value.tipoPublicacion) {
        case 'Restaurante':
          // JSON de Restaurante
          dataToSend = {
            "titulo": formData.value.restauranteNombre,
            "subtitulo": formData.value.restauranteSubtitulo,
            "descripcion": formData.value.restauranteDescripcion,
            "direccion": formData.value.restauranteDireccion,
            "imagenUrl": "",
            "nombre": formData.value.restauranteNombre,
            "ubicacion": formData.value.restauranteUbicacion,
            "codigoPostal": parseInt(String(formData.value.restauranteCodigoPostal)) || 0,
            "urlWeb": formData.value.restauranteWeb || "",
            "telefono": formData.value.restauranteTelefono ? `0034${formData.value.restauranteTelefono.replace(/\D/g, '')}` : "",
            "email": formData.value.restauranteEmail || "",
          };
          endpoint = '/api/restaurante/add';
          break;

       case 'Receta':

       //JSON Receta
        const recetaPayload = {
          idReceta: 0,
          titulo: formData.value.recetaNombre,
          subtitulo: formData.value.recetaSubtitulo,
          descripcion: formData.value.recetaDescripcion,
          imagenUrl: "",
          dificultad: formData.value.recetaDificultad,
          valoracion: 0,
          comensales: parseInt(String(formData.value.recetaComensales)) || 0,
          tiempoPreparacion: parseInt(String(formData.value.recetaTiempoPreparacion)) || 0,
          valorEnergetico: parseInt(String(formData.value.recetaValorEnergetico)) || 0,
          tipoComida: formData.value.recetaTipoComida
        };

        let recetaGuardada: any;

        // guardar receta principal
        try {
          recetaGuardada = await useApiFetch('/api/receta/add', {
            method: 'POST',
            body: recetaPayload
          });
        } catch (e) {
          throw new Error('Fallo al guardar Receta principal.');
        }

        const idReceta = (recetaGuardada as any)?.idReceta;

        if (!idReceta) {
          throw new Error('El servidor no devolvió ID de receta.');
        }

        // procesar ingredientes del textarea → "nombre = cantidad"
        const ingredientesArray = formData.value.recetaIngredientes
          .split('\n')
          .map(line => line.trim())
          .filter(line => line.length > 0)
          .map(line => {
            const [nombre, cantidad] = line.split('=').map(part => part.trim());
            return {
              nombre,
              cantidad: parseInt(cantidad) || 0
            };
          });

        //  procesar pasos_receta
        const pasosArray = formData.value.recetaPasos
          .split('\n')
          .map(line => line.trim())
          .filter(line => line.length > 0);

        //  guardar ingredientes + relación + pasos
        try {

          // Guardar ingredientes con relación
          const ingredientesPromises = ingredientesArray.map(async (ing) => {

            //guardar ingrediente
            const ingredienteResp = await useApiFetch('/api/ingrediente/add', {
              method: 'POST',
              body: { nombre: ing.nombre }
            });

            const idIngrediente = (ingredienteResp as any)?.id_ingrediente;

            // guardar en tabla puente
            return useApiFetch('/api/receta_ingrediente/add', {
              method: 'POST',
              body: {
                id_receta: idReceta,
                id_ingrediente: idIngrediente,
                cantidad: ing.cantidad
              }
            });
          });

          // Guardar pasos
          const pasosPromises = pasosArray.map((paso, index) => {
            return useApiFetch('/api/paso-receta/add', {
              method: 'POST',
              body: {
                id_receta: idReceta,
                orden: index + 1,
                descripcion: paso
              }
            });
          });

          await Promise.all([...ingredientesPromises, ...pasosPromises]);

        } catch (e) {
          throw new Error('Fallo en ingredientes o pasos.');
        }

        //  mensaje éxito
        statusMessage.value = `¡Gracias! Tu Receta se ha enviado con éxito, incluyendo ${ingredientesArray.length} ingredientes y ${pasosArray.length} pasos.`;

        
        break;

        case 'Post':
          // JSON de Post
          dataToSend = {
            "titulo": formData.value.postTitulo,
            "subtitulo": formData.value.postSubtitulo,
            "contenido": formData.value.postContenido,
            "urlPost": formData.value.postUrl || formData.value.postTitulo.toLowerCase().replace(/\s/g, '-'),
          };
          endpoint = '/api/post/add';
          break;

        default:
          
          statusMessage.value = 'Debes seleccionar un tipo de publicación.';
          isSubmitting.value = false;
          return;
      }

    // Llamada API ->useApiFetch usa $fetch y devuelve los datos o lanza un error.
    const responseData = await useApiFetch(endpoint, {
      method: 'POST',
      body: dataToSend, 
    });

    console.log('Respuesta del servidor:', responseData); 
    //Mensaje éxito
    statusMessage.value = `¡Gracias! Tu publicación de **${formData.value.tipoPublicacion}** ha sido enviada con éxito. Nos pondremos en contacto contigo pronto.`;

        
    // Limpiar el formulario y resetear el selector, mantiene el usuario
          
    Object.keys(formData.value).forEach(key => {
    if (key !== 'nombre' && key !== 'email') {
      const k = key as keyof ContactForm;
      (formData.value as any)[k] =
        (typeof formData.value[k] === 'string')
          ? ''
          : (typeof formData.value[k] === 'number' ? 0 : null) as any;
    }
    });
    tipoPublicacion.value = '';
          
    // Restaurar datos usuario
    formData.value.nombre = nombreUsuario;
    formData.value.email = emailUsuario;

    //campos obligatorios se resetean
    const formRef=ref();
    formRef.value?.resetValidation();

//Pop-up otra publicacion
    showDialog.value = true;
    

} catch (error) {
    // CAPTURA DE ERRORES
    console.error('Error al enviar la publicacion:', error);
    const errorMessage = (error as any)?.data?.message || (error as any)?.message || 'Error desconocido';
    
    statusMessage.value = `Ha habido un error al enviar tu publicación. Por favor inténtalo de nuevo. Detalle: ${errorMessage}`;
} finally {
    isSubmitting.value = false;
}
}
</script>
  
  <style lang="scss" scoped>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
  @import url('https://fonts.googleapis.com/css2?family=Arial&display=swap');
  
  // Definición de colores
  .text-gold {
    color: #836a02 !important; 
  }
  
  // Estilos de fuente
  .text-font-poppins {
    font-family: 'Poppins', sans-serif !important; 
  }
  
  .section-main__title {
 
    h1 {
      font-family: 'Poppins', sans-serif;
      font-size: 2.5rem; 
      line-height: 1.1;
      font-weight: 600;
      color: #242424;
      margin-bottom: 0.5rem;
    }
  
    p {
      font-family: 'Arial', sans-serif; 
      font-size: 20px;
      line-height: 1.3;
      letter-spacing: -0.5px;
      color: #555; 
      max-width: 100%;
      margin: 0 auto;
    }


  }
  //imagen de fondo
  .formulario-con-fondo {
  background-image: url('/img/fondo_contacto.png'); 
  background-size: cover;      
  background-position: center;
  background-repeat: no-repeat;
  padding: 2rem;               
  border-radius: 1rem;        
  color: #fff;               
}
.formulario-con-fondo::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(131, 106, 2, 0.55); 
  backdrop-filter: brightness(0.9);
}
  </style>
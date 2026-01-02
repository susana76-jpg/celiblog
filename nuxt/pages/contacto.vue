<template>
  <div class="section-page contacto-page">
    
    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      title="Contacto"
      image-alt="Formulario de contacto"
      subtitle="¿Quieres compartir tu experiencia sin gluten? ¿Tienes un restaurante o una receta que te gustaría que conociéramos? ¡Nos encantaría saber de ti! Rellena el formulario y únete a nuestra comunidad celíaca."
      :image-src="'/img/fondo_contacto.png'"
    />
    <!------------------------------------------->
    
    <!-- MAIN SECTION --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Comparte tu experiencia con nosotros</h2>
        <p>Para enviar publicaciones, recetas o información sobre restaurantes es necesario que primero inicies sesión o te registres en Celiblog. Únete a nuestra comunidad y ayúdanos a crear un espacio seguro y confiable para todos.</p>
      </div>

      <div class="contact-page__form">
        <v-alert 
          v-if="!isAuthenticated"
          class="mb-10"
          density="comfortable"
          type="error" 
        >
          Debes iniciar sesión o registrarte para enviar un formulario.
        </v-alert>
        <v-select
          required
          hide-details
          color="primary"
          base-color="primary"
          variant="outlined"
          density="comfortable"
          label="Tipo de Publicación *"
          prepend-inner-icon="mdi-format-list-bulleted"
          :disabled="!isAuthenticated"
          :items="opcionesPublicacion"
          v-model="tipoPublicacion"
        ></v-select>
        <component 
          :is="currentFormComponent" 
          :disabled="!isAuthenticated" 
        />
      </div>
    </div>
  </div>


        <v-form ref="formRef" validate-on="submit" class="space-y-6 text-font-poppins">
            
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

          </v-form>
</template>
  
<script setup lang="ts">
const authStore = useAuthStore();
const { isAuthenticated } = authStore;

/*********************************/
/* FORM BASICS */
/*********************************/
// Select publication type options
const tipoPublicacion = ref('Post');
const opcionesPublicacion = ['Restaurante', 'Receta', 'Post'];

// Form components mapping
const formComponents: Record<string, any> = {
  'Restaurante': resolveComponent('ContactRestaurantForm'),
  'Receta': resolveComponent('ContactRecipeForm'),
  'Post': resolveComponent('ContactPostForm'),
}

// Computed property for dynamic component
const currentFormComponent = computed(() => {
  return formComponents[tipoPublicacion.value] || formComponents['Post']
});


/*********************************/
/* FORM SUBMISSION */
/*********************************/
// const statusMessage = ref('');

// interface ContactForm {   
//   // Campos Restaurante 
//   restauranteNombre: string;
//   restauranteSubtitulo: string;
//   restauranteDireccion: string;
//   restauranteUbicacion: string; 
//   restauranteCodigoPostal: number | null; 
//   restauranteWeb: string;
//   restauranteTelefono: string; 
//   restauranteEmail: string;
//   restauranteDescripcion: string;
  

//   // Campos Receta 
//   recetaNombre: string;
//   recetaSubtitulo: string;
//   recetaTipoComida: string;
//   recetaDescripcion: string;
//   recetaIngredientes: string;
//   recetaPasos: string;
//   recetaDificultad: string;
//   recetaTiempoPreparacion: number | null;
//   recetaValorEnergetico: number | null;
//   recetaComensales: number | null;

//   tipoPublicacion: string;
// }

// // Estado del formulario
// const formData = ref<ContactForm>({
//   // Restaurante
//   restauranteNombre: '',
//   restauranteSubtitulo: '',
//   restauranteDireccion: '',
//   restauranteUbicacion: '',
//   restauranteCodigoPostal: null,
//   restauranteWeb: '',
//   restauranteTelefono: '',
//   restauranteEmail: '',
//   restauranteDescripcion: '',
  

// });

//  //preparación de los campos de receta - ingredientes y pasos_receta -
//  const ingredientes = formData.value.recetaIngredientes;
//  const pasos_receta = formData.value.recetaPasos; 


// try {
//     switch (formData.value.tipoPublicacion) {
//         case 'Restaurante':
//           // JSON de Restaurante
//           dataToSend = {
//             "titulo": formData.value.restauranteNombre,
//             "subtitulo": formData.value.restauranteSubtitulo,
//             "descripcion": formData.value.restauranteDescripcion,
//             "direccion": formData.value.restauranteDireccion,
//             "imagenUrl": "",
//             "nombre": formData.value.restauranteNombre,
//             "ubicacion": formData.value.restauranteUbicacion,
//             "codigoPostal": parseInt(String(formData.value.restauranteCodigoPostal)) || 0,
//             "urlWeb": formData.value.restauranteWeb || "",
//             "telefono": formData.value.restauranteTelefono ? `0034${formData.value.restauranteTelefono.replace(/\D/g, '')}` : "",
//             "email": formData.value.restauranteEmail || "",
//           };
//           endpoint = '/api/restaurante/add';
//           break;

//        case 'Receta':

//        //JSON Receta
//         const recetaPayload = {
//           idReceta: 0,
//           titulo: formData.value.recetaNombre,
//           subtitulo: formData.value.recetaSubtitulo,
//           descripcion: formData.value.recetaDescripcion,
//           imagenUrl: "",
//           dificultad: formData.value.recetaDificultad,
//           valoracion: 0,
//           comensales: parseInt(String(formData.value.recetaComensales)) || 0,
//           tiempoPreparacion: parseInt(String(formData.value.recetaTiempoPreparacion)) || 0,
//           valorEnergetico: parseInt(String(formData.value.recetaValorEnergetico)) || 0,
//           tipoComida: formData.value.recetaTipoComida
//         };

//         let recetaGuardada: any;

//         // guardar receta principal
//         try {
//           recetaGuardada = await useApiFetch('/api/receta/add', {
//             method: 'POST',
//             body: recetaPayload
//           });
//         } catch (e) {
//           throw new Error('Fallo al guardar Receta principal.');
//         }

//         const idReceta = (recetaGuardada as any)?.idReceta;

//         if (!idReceta) {
//           throw new Error('El servidor no devolvió ID de receta.');
//         }

//         // procesar ingredientes del textarea → "nombre = cantidad"
//         const ingredientesArray = formData.value.recetaIngredientes
//           .split('\n')
//           .map(line => line.trim())
//           .filter(line => line.length > 0)
//           .map(line => {
//             const [nombre, cantidad] = line.split('=').map(part => part.trim());
//             return {
//               nombre,
//               cantidad: parseInt(cantidad) || 0
//             };
//           });

//         //  procesar pasos_receta
//         const pasosArray = formData.value.recetaPasos
//           .split('\n')
//           .map(line => line.trim())
//           .filter(line => line.length > 0);

//         //  guardar ingredientes + relación + pasos
//         try {

//           // Guardar ingredientes con relación
//           const ingredientesPromises = ingredientesArray.map(async (ing) => {

//             //guardar ingrediente
//             const ingredienteResp = await useApiFetch('/api/ingrediente/add', {
//               method: 'POST',
//               body: { nombre: ing.nombre }
//             });

//             const idIngrediente = (ingredienteResp as any)?.id_ingrediente;

//             // guardar en tabla puente
//             return useApiFetch('/api/receta_ingrediente/add', {
//               method: 'POST',
//               body: {
//                 id_receta: idReceta,
//                 id_ingrediente: idIngrediente,
//                 cantidad: ing.cantidad
//               }
//             });
//           });

//           // Guardar pasos
//           const pasosPromises = pasosArray.map((paso, index) => {
//             return useApiFetch('/api/paso-receta/add', {
//               method: 'POST',
//               body: {
//                 id_receta: idReceta,
//                 orden: index + 1,
//                 descripcion: paso
//               }
//             });
//           });

//           await Promise.all([...ingredientesPromises, ...pasosPromises]);

//         } catch (e) {
//           throw new Error('Fallo en ingredientes o pasos.');
//         }

//         //  mensaje éxito
//         statusMessage.value = `¡Gracias! Tu Receta se ha enviado con éxito, incluyendo ${ingredientesArray.length} ingredientes y ${pasosArray.length} pasos.`;

        
//         break;

//     //Mensaje éxito


        
//     // Limpiar el formulario y resetear el selector, mantiene el usuario
          
//     Object.keys(formData.value).forEach(key => {
//     if (key !== 'nombre' && key !== 'email') {
//       const k = key as keyof ContactForm;
//       (formData.value as any)[k] =
//         (typeof formData.value[k] === 'string')
//           ? ''
//           : (typeof formData.value[k] === 'number' ? 0 : null) as any;
//     }
//     });


</script>
  
<style lang="scss" scoped>
.contact-page {
  &__form {
    margin: 30px auto 100px;
    width: 80%;

    @media (max-width: 1100px) {
      width: 90%;
    }
  }
}
</style>
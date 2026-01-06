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

    <!-- LOCATION MAP --------------------------->
    <ContactLocationMap />
    <!------------------------------------------->

  </div>
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
</script>
  
<style lang="scss" scoped>
.contact-page {
  &__form {
    margin: 30px auto 60px;
    width: 80%;

    @media (max-width: 1100px) {
      width: 90%;
    }
  }
}
</style>
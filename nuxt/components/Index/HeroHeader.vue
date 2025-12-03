<template>
  <section class="hero-header">
    <v-img 
      cover
      :src="imgHeroImage" 
      alt="Hero background"
      class="hero-image"
      gradient="to right, #836A02 0%, #836A02 16%, #836A0290 70%, #ffffff85 100%" 
    />
    <div class="hero-content">
      <h1>Vive el Sabor Sin Gluten</h1>
      <p class="hero-description">
        Descubre restaurantes seguros, recetas creativas y consejos esenciales para personas celíacas, con inspiración diaria para disfrutar una vida deliciosa, variada y sin preocupaciones, explorando nuevos sabores y encontrando opciones confiables en cada comida, siempre con la tranquilidad de elegir lo mejor para tu bienestar.
      </p>
      <div class="hero-buttons">
        <v-btn
          v-for="button in infoButtons"
          :key="button.path"
          :to="button.path"
          :append-icon="button.icon"
          :text="button.text"
          :variant="button.variant"
          :class="button.class"
          :color="button.color"
          :rounded="button.rounded"
          size="large"
        />
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
import type { Variant } from '../../types/vuetify';

const imgHeroImage = "/img/hero-image-index.png";

// List of account buttons
const infoButtons = [
  {
    path: '/contacto',
    text: 'Contacto',
    icon: 'mdi-email-outline',
    variant: 'flat' as Variant,
    class: 'text-primary mr-2',
    color: 'white',
    rounded: 'xl'
  },
  {
    path: '/equipo',
    text: 'Saber más',
    icon: 'mdi-arrow-right-drop-circle-outline',
    variant: 'outlined' as Variant,
    color: 'white',
    rounded: 'xl'
  }
];
</script>

<style lang="scss" scoped>
// Hero section
.hero-header {
  position: relative;
  height: 560px;

  .v-img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
  }


  .hero-content {
    position: absolute;
    bottom: 0;
    z-index: 2;
    padding: 80px;
    color: white;
    max-width: 900px;

    h1 {
      font-family: "Arial", sans-serif;
      font-weight: bold;
      font-size: 56px;
      letter-spacing: -1px;
      margin-bottom: 8px;
    }

    .hero-description {
      font-family: "Poppins", sans-serif;
      font-size: 20px;
      line-height: 22px;
      letter-spacing: 1.2px;
      margin-bottom: 32px;
    }
  }
}
</style>

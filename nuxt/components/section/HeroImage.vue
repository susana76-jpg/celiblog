<template>
  <section class="hero-image-section">
    <v-img
      cover
      :src="imageSrc"
      :alt="imageAlt"
      class="hero-image"
      gradient="to bottom, #836A0299 0%, #836A02 100%"
    />
    <div class="hero-text">
      <h1>{{ title }}</h1>
      <p>{{ subtitle }}</p>
    </div>
  </section>
</template>

<script setup lang="ts">
const props = defineProps<{
  imageSrc: string;
  imageAlt: string;
  title: string;
  subtitle: string;
}>();
</script>

<style lang="scss" scoped>
.hero-image-section {
  position: relative;
  height: 320px;
  overflow: hidden; 

    .hero-image {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      z-index: 1; 
    }

    .hero-text {
      position: absolute;
      bottom: 40px;
      left: 50%;
      transform: translateX(-50%);
      width: 65%;
      text-align: center;
      z-index: 2;
      color: white;

      h1 {
        font-family: 'arial', sans-serif;
        font-size: 2.9rem;
      }

      p {
        font-size: 1.2rem;
        line-height: 1.2rem;
      }
    }
}
</style>
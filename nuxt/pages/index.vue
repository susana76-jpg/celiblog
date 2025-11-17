<template>
  <div class="index-page">
    <IndexHeroHeader />
    <IndexCarousel :items="receipes" />
    <IndexCarousel :items="tips" />
  </div>
</template>

<script setup lang="ts">
import { receipes, tips } from '../utils/dummy';
const getReceipes = async () => {
  const { data, pending, error } = await useApiFetch(API.RECETAS.BASE);
  console.log('data', data.value);
};
getReceipes();
</script>

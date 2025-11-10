<template>
  <div class="index-page">
    <IndexHeroHeader />
    <IndexCarousel :items="receipes" />
    <IndexCarousel :items="tips" />
  </div>
</template>

<script setup lang="ts">
import { receipes, tips } from '../utils/dummy';
</script>

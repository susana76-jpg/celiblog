<template>
  <section class="section-page restaurantes-page"> 

    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      title="Restaurantes"
      image-alt="Agradable restaurante"
      subtitle="Te presentamos una guía de restaurantes que cuidan cada detalle para ofrecer platos libres de gluten. Descubre nuevos sabores y disfruta de una experiencia gastronómica segura y de calidad."
      :image-src="img"
    />
    <!------------------------------------------->

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Dónde comer sin gluten</h2>
        <p>Te presentamos una selección de restaurantes comprometidos con la calidad y la seguridad alimentaria para personas con intolerancia al gluten.</p>
      </div>
      
      <SectionFilterBar />
      <RestaurantesMapaIndex
        class="mb-16"
        :restaurants="restaurants" 
      />

      <v-row no-gutters class="section-main__content">
        <v-col
          v-for="item in restaurants"
          :key="item.idRestaurante"
          cols="12"
          md="4"
          xl="3"
        >
          <SectionRestaurantCardItem :item="item" />
        </v-col>
      </v-row>
    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const img = '/img/restaurantes/hero-image.jpg';
const restaurants = ref<Restaurante[]>([]);

// Get receta by ID from API
const getAllRestaurants = async () => {
  try {
    const { data } = await useApiFetch(API.RESTAURANTS.BASE);
  
    restaurants.value = data.value as Restaurante[];
    restaurants.value.forEach((restaurant, index) => restaurant.imagenUrl = `/img/restaurantes/restaurante${index + 1}.jpg`);
  } catch (error) {
    console.error('Error fetching receipe:', error);
  }
};

onMounted(() => {
  getAllRestaurants();
});

</script>

<style lang="scss" scoped>

:deep(.custom-marker) {
  background: none;
  border: none;
}
</style>
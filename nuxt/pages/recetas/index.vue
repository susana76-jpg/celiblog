<template>
  <section class="section-page recetas-page"> 

    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      image-alt="Delicious food"
      title="Recetas"
      subtitle="Descubre una variedad de platos sin gluten, fáciles de preparar y llenos de sabor. Aquí encontrarás ideas para cada ocasión, desde comidas rápidas hasta recetas más elaboradas, todas aptas para personas con intolerancia o sensibilidad al gluten."
      :image-src="img"
    />
    <!------------------------------------------->

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Un vistazo a nuestra cocina sin gluten</h2>
        <p>Te presentamos propuestas variadas que combinan creatividad y tradición, todas libres de gluten. Perfectas para cocinar con confianza y descubrir nuevos sabores.</p>
      </div>
      <SectionFilterBar />
      <v-row no-gutters class="section-main__content">
        <v-col
          v-for="item in recipes"
          :key="item.idReceta"
          cols="12"
          md="4"
          xl="3"
        >
          <SectionRecipeCardItem 
            :item="item" 
            type="recetas"
          />
        </v-col>
      </v-row>
    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const img = '/img/recetas/hero-image.png';
const recipes = ref<Receta[]>([]);

// Get receta by ID from API
const getAllRecipes = async () => {
  try {
    const { data } = await useApiFetch(API.RECIPES.BASE);
    recipes.value = data.value as Receta[];
    recipes.value.forEach((recipe, i) => recipe.imagenUrl = '/img/recetas/receta' + (i + 1) + '.jpg');
  } catch (error) {
    console.error('Error fetching receipe:', error);
  }
};

onMounted(() => {
  getAllRecipes();
});

</script>

<style lang="scss" scoped>
</style>

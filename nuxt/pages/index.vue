<template>
  <div class="index-page">
    <IndexHeroHeader />
    <IndexCarousel 
      v-if="recipes.length > 0"
      title="Nuevas Recetas Sin Gluten"
      subtitle="Descubre las últimas creaciones pensadas para personas celíacas: preparaciones fáciles, seguras y llenas de sabor que podrás disfrutar sin preocupaciones. Explora el carrusel y encuentra tu próxima receta favorita."
      :items="recipes" 
      type="recetas"
    />
    <IndexCarousel 
      v-if="recipes.length > 0"
      title="Consejos para una Vida Sin Gluten"
      subtitle="Accede a las recomendaciones más recientes para vivir con celiaquía de forma segura y práctica. Descubre trucos, guías y hábitos que te ayudarán a disfrutar cada día con mayor tranquilidad y bienestar."
      :items="recipes" 
      type="consejos"
    />
  </div>
</template>

<script setup lang="ts">
const recipes = ref<Receta[]>([]);

// Get receta by ID from API
const getAllRecipes = async () => {
  try {
    const data = await useApiFetch(API.RECIPES.BASE);
    recipes.value = data as Receta[];
    recipes.value.forEach((recipe, i) => recipe.imagenUrl = '/img/recetas/' + recipe.imagenUrl.split('.').shift() + '.png');
    recipes.value = recipes.value.slice(0, 8); 
  } catch (error) {
    console.error('Error fetching receipe:', error);
  }
};

onMounted(() => {
  getAllRecipes();
});
</script>

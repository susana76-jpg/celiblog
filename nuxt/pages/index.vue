<template>
  <div class="index-page">
    <IndexHeroHeader />
    <IndexCarousel 
      v-if="recipes.length > 0"
      title="Nuevas Recetas Sin Gluten"
      subtitle="Descubre las últimas creaciones pensadas para personas celíacas: preparaciones fáciles, seguras y llenas de sabor que podrás disfrutar sin preocupaciones."
      :items="recipes" 
      type="recetas"
    />
    <IndexCarousel 
      v-if="posts.length > 0"
      title="Consejos para una Vida Sin Gluten"
      subtitle="Accede a las recomendaciones más recientes para vivir con celiaquía de forma segura. Descubre trucos, guías y hábitos que te ayudarán a disfrutar cada día."
      :items="posts" 
      type="consejos"
    />
  </div>
</template>

<script setup lang="ts">
const { showError } = useNotification();

const recipes = ref<Receta[]>([]);
const posts = ref<Post[]>([]);

// Get recipes from API
const getAllRecipes = async () => {
  try {
    const data = await useApiFetch(API.RECIPES.BASE);
    recipes.value = data as Receta[];
    recipes.value.forEach((recipe, i) => recipe.imagenUrl = '/img/recetas/' + recipe.imagenUrl);
    recipes.value = recipes.value.slice(0, 8); 
  } catch (error) {
    showError(`Error al cargar las recetas: ${error}`);
  }
};

// Get posts from API
const getAllPosts = async () => {
  try {
    const data = await useApiFetch(API.POSTS.BASE);
    posts.value = data as Post[];
    posts.value.forEach((post, i) => post.urlPost = '/img/consejos/consejo' + (i + 1) + '.jpg');
    posts.value = posts.value.slice(0, 8); 
  } catch (error) {
    showError(`Error al cargar los consejos: ${error}`);
  }
};

onMounted(() => {
  getAllRecipes();
  getAllPosts();
});
</script>

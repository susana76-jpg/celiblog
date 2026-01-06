<template>

  <!-- LOADER -->
  <div v-if="loading" class="loader-container-index">
    <div class="loader">
      <div class="loader__spinner"></div>
      <p class="loader__text">Cargando recetas...</p>
    </div>
  </div>

  <!-- NO FAVORITES MESSAGE -->
  <div v-else-if="!loading && filteredRecipes.length === 0" class="no-favorites-container">
    <div class="no-favorites-content">
      <v-icon size="100" color="primary">mdi-heart-outline</v-icon>
      <h3 class="text-h5 mt-4 mb-2">No tienes recetas favoritas</h3>
      <p class="text-body-1">Empieza a guardar tus recetas favoritas para verlas aquí</p>
    </div>
  </div>

  <!-- RECIPES GRID -->
  <v-row v-else no-gutters class="section-main__content ml-n4 mr-n4">
    <v-col
      v-for="(item, index) in paginatedRecipes"
      :key="item.idReceta"
      cols="12"
      md="4"
      xl="3"
    >
      <SectionRecipeCardItem 
        :item="item" 
        @update:item="($event) => updateRecipe(index, $event)"
        
      />
    </v-col>
  </v-row>

  <!-- PAGINATION -->
  <div 
    v-if="!loading && totalPages > 1"
    class="pagination-container mt-6 mb-10"
  >
    <v-pagination
      active-color="primary"
      variant="flat"
      v-model="currentPage"
      :length="totalPages"
      :total-visible="7"
      @update:model-value="onPageChange"
    ></v-pagination>
  </div>

</template>

<script setup lang="ts">
const { showError } = useNotification();

const loading = ref<boolean>(false);
const recipes = ref<Receta[]>([]);
const filteredRecipes = computed(() => recipes.value.filter(recipe => recipe.esFavoritoUsuario)); 

// Fetch favorite recipes
const getFavoriteRecipes = async () => {
  loading.value = true;

  try { 
    const data = await useApiFetch(API.USER.FAVORITES, {
        method: 'GET',
        params: {
          tipoReferencia: 'RECETA'
        }
      });
    if (data) recipes.value = data as Receta[];
  } catch (error) {
    showError('No se ha podido cargar las recetas favoritas');
  } finally {
    loading.value = false;
  }
};

/*******************************/
/* PAGINATION LOGIC */
/*******************************/
const currentPage = ref<number>(1);
const itemsPerPage = ref<number>(12);
const totalPages = computed(() => Math.ceil(filteredRecipes.value.length / itemsPerPage.value));

const paginatedRecipes = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  const end = start + itemsPerPage.value;
  return filteredRecipes.value.slice(start, end);
});

const onPageChange = (page: number) => {
  currentPage.value = page;

  // Scroll to top of results
  window.scrollTo({ top: 0, behavior: 'smooth' });
};

const updateRecipe = (index: number, updatedRecipe: Receta) => {
  const recipeIndex = recipes.value.findIndex(r => r.idReceta === updatedRecipe.idReceta);
  if (recipeIndex !== -1) recipes.value[recipeIndex] = updatedRecipe;
};

onMounted(() => {
  getFavoriteRecipes();
});
</script>
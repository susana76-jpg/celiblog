<template>
  <section class="section-page recetas-page"> 

    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      image-alt="Delicious food"
      title="Recetas"
      subtitle="Descubre una variedad de platos sin gluten, fáciles de preparar y llenos de sabor. Aquí encontrarás ideas para cada ocasión, desde comidas rápidas hasta recetas más elaboradas, todas aptas para personas con intolerancia o sensibilidad al gluten"
      :image-src="img"
    />
    <!------------------------------------------->

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">

      <!-- TITLE -->
      <div class="section-main__title">
        <h2>Un vistazo a nuestra cocina sin gluten</h2>
        <p>Te presentamos propuestas variadas que combinan creatividad y tradición, todas libres de gluten. Perfectas para cocinar con confianza y descubrir nuevos sabores</p>
      </div>

      <!-- FILTER BAR -->
      <SectionFilterBar 
        label="Busca entre todas nuestras recetas"
        :search="keyword"
        :tags="tags"
        :total="recipes.length"
        @update:search="updateSearch"
        @update:tag="(value) => updateTags(value as RecetaType[])"
      />

      <!-- LOADER -->
      <div v-if="loading" class="loader-container-index">
        <div class="loader">
          <div class="loader__spinner"></div>
          <p class="loader__text">Cargando recetas...</p>
        </div>
      </div>

      <!-- RECIPES GRID -->
      <v-row v-else no-gutters class="section-main__content">
        <v-col
          v-for="(item, index) in paginatedRecipes"
          :key="item.idReceta"
          cols="12"
          md="6"
          lg="4"
          xl="3"
        >
          <SectionRecipeCardItem 
            :item="item" 
            type="recetas"
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
    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const img = '/img/recetas/hero-image.png';
const recipes = ref<Receta[]>([]);
const keyword = ref<string>('');
const type = ref<RecetaType[]>([]);
const loading = ref<boolean>(true);
let searchTimeout: ReturnType<typeof setTimeout> | null = null;
const tags = [ 'DESAYUNO', 'BRUNCH', 'ALMUERZO', 'MERIENDA', 'CENA', 'TAPAS', 'RACIONES', 'POSTRE', 'SALADO', 'DULCE' ];

// Search handlers
const updateSearch = (value: string) => {
  keyword.value = value;
  currentPage.value = 1;
  
  // Clear previous timeout
  if (searchTimeout) clearTimeout(searchTimeout);
  
  // Set new timeout
  searchTimeout = setTimeout(() => {
    getAllRecipes();
  }, 500); 
};  

// Update tags
const updateTags = (value: RecetaType[]) => {
  type.value = value;
  currentPage.value = 1;
  getAllRecipes(); 
};

// Get receta by ID from API
const getAllRecipes = async () => {
  loading.value = true;
  try {
    const data = await useApiFetch(API.RECIPES.SEARCH, {
      params: {
        keyword: keyword.value,
        tipoComida: type.value
      }
    });
    recipes.value = data as Receta[];
  } catch (error) {
    console.error('Error fetching receipe:', error);
  } finally {
    loading.value = false;
  }
};

// Update recipe in the list
const updateRecipe = (index: number, updatedRecipe: Receta) => {
  const recipeIndex = recipes.value.findIndex(r => r.idReceta === updatedRecipe.idReceta);
  if (recipeIndex !== -1) recipes.value[recipeIndex] = updatedRecipe;
};

// Pagination logic
const { currentPage, totalPages, paginatedItems: paginatedRecipes, onPageChange } = usePagination(recipes);

// Fetch recipes on mount
onMounted(() => {
  getAllRecipes();
});


</script>

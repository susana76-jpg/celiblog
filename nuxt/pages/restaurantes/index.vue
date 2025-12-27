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
      
      <SectionFilterBar 
        label="Busca por ciudad entre todos nuestros restaurantes"
        :tags="tags"
        :search="search"
        :total="restaurants.length"
        @update:search="updateSearch"
        @update:tag="updateTags"
      />
      <RestaurantesMapaIndex
        class="mb-16"
        :restaurants="paginatedRestaurants" 
      />

      <!-- LOADER -->
      <div v-if="loading" class="loader-container-index">
        <div class="loader">
          <div class="loader__spinner"></div>
          <p class="loader__text">Cargando restaurantes...</p>
        </div>
      </div>

      <!-- RESTAURANTS GRID -->
      <v-row v-else no-gutters class="section-main__content">
        <v-col
          v-for="item in paginatedRestaurants"
          :key="item.idRestaurante"
          cols="12"
          md="4"
          xl="3"
        >
          <SectionRestaurantCardItem :item="item" />
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
const img = '/img/restaurantes/hero-image.jpg';
const restaurants = ref<Restaurante[]>([]);
const loading = ref<boolean>(true);
const type = ref<string[]>([]);
const search = ref<string>('');
let searchTimeout: ReturnType<typeof setTimeout> | null = null;
const tags = [ 'SIN_GLUTEN', 'MEDITERRANEA', 'ASIATICA', 'VEGANO', 'MEXICANA' ];

// Search handlers
const updateSearch = (value: string) => {
  search.value = value;
  currentPage.value = 1;
  
  // Clear previous timeout
  if (searchTimeout) clearTimeout(searchTimeout);
  
  // Set new timeout
  searchTimeout = setTimeout(() => {
    getAllRestaurants();
  }, 500); 
};  

// Update tags
const updateTags = (value: string[]) => {
  type.value = value;
  currentPage.value = 1;
  getAllRestaurants(); 
};

// Get all restaurants from API
const getAllRestaurants = async () => {
  loading.value = true;

  try {
    const data = await useApiFetch(API.RESTAURANTS.SEARCH, {
      params: {
        ubicacion: search.value,
        tiposRestaurante: type.value,
      }
    });
  
    restaurants.value = data as Restaurante[];
    restaurants.value.forEach((restaurant) => restaurant.imagenUrl = `/img/restaurantes/${restaurant.imagenUrl}`);
  } catch (error) {
    console.error('Error fetching receipe:', error);
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  getAllRestaurants();
});

/*******************************/
/* PAGINATION LOGIC */
/*******************************/
const currentPage = ref<number>(1);
const itemsPerPage = ref<number>(12);
const totalPages = computed(() => Math.ceil(restaurants.value.length / itemsPerPage.value));

const paginatedRestaurants = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  const end = start + itemsPerPage.value;
  return restaurants.value.slice(start, end);
});

const onPageChange = (page: number) => {
  currentPage.value = page;

  // Scroll to top of results
  window.scrollTo({ top: 0, behavior: 'smooth' });
};

</script>

<style lang="scss" scoped>

:deep(.custom-marker) {
  background: none;
  border: none;
}
</style>
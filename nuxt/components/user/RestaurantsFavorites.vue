<template>

  <!-- LOADER -->
  <div v-if="loading" class="loader-container-index">
    <div class="loader">
      <div class="loader__spinner"></div>
      <p class="loader__text">Cargando restaurantes...</p>
    </div>
  </div>

  <!-- NO FAVORITES MESSAGE -->
  <div v-else-if="!loading && filteredRestaurants.length === 0" class="no-favorites-container">
    <div class="no-favorites-content">
      <v-icon size="100" color="primary">mdi-heart-outline</v-icon>
      <h3 class="text-h5 mt-4 mb-2">No tienes restaurantes favoritos</h3>
      <p class="text-body-1">Empieza a guardar tus restaurantes favoritos para verlos aquí</p>
    </div>
  </div>

  <!-- RESTAURANTS GRID -->
  <v-row v-else no-gutters class="section-main__content ml-n4 mr-n4">
    <v-col
      v-for="(item, index) in paginatedRestaurants"
      :key="item.idRestaurante"
      cols="12"
      md="4"
      xl="3"
    >
      <SectionRestaurantCardItem 
        :item="item" 
        @update:item="($event) => updateRestaurant(index, $event)"
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
import { fi } from 'vuetify/locale';

const { showError } = useNotification();

const loading = ref<boolean>(false);
const restaurants = ref<Restaurante[]>([]);
const filteredRestaurants = computed(() => restaurants.value.filter(restaurant => restaurant.esFavoritoUsuario));

// Fetch favorite restaurants
const getFavoriteRestaurants = async () => {
  loading.value = true;

  try { 
    const data = await useApiFetch(API.USER.FAVORITES, {
        method: 'GET',
        params: {
          tipoReferencia: 'RESTAURANTE'
        }
      });
    if (data) restaurants.value = data as Restaurante[];
  } catch (error) {
    showError('No se ha podido cargar los restaurantes favoritos');
  } finally {
    loading.value = false;
  }
};

/*******************************/
/* PAGINATION LOGIC */
/*******************************/
const currentPage = ref<number>(1);
const itemsPerPage = ref<number>(12);
const totalPages = computed(() => Math.ceil(filteredRestaurants.value.length / itemsPerPage.value));

const paginatedRestaurants = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  const end = start + itemsPerPage.value;
  return filteredRestaurants.value.slice(start, end);
});

const onPageChange = (page: number) => {
  currentPage.value = page;

  // Scroll to top of results
  window.scrollTo({ top: 0, behavior: 'smooth' });
};

const updateRestaurant = (index: number, updatedRestaurant: Restaurante) => {
  const restaurantIndex = restaurants.value.findIndex(r => r.idRestaurante === updatedRestaurant.idRestaurante);
  if (restaurantIndex !== -1) restaurants.value[restaurantIndex] = updatedRestaurant;
};

onMounted(() => {
  getFavoriteRestaurants();
});
</script>
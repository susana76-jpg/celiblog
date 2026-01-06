<template>

  <!-- LOADER -->
  <div v-if="loading" class="loader-container-index">
    <div class="loader">
      <div class="loader__spinner"></div>
      <p class="loader__text">Cargando consejos...</p>
    </div>
  </div>

  <!-- NO FAVORITES MESSAGE -->
  <div v-else-if="!loading && filteredPosts.length === 0" class="no-favorites-container">
    <div class="no-favorites-content">
      <v-icon size="100" color="primary">mdi-heart-outline</v-icon>
      <h3 class="text-h5 mt-4 mb-2">No tienes consejos favoritos</h3>
        <p class="text-body-1">Empieza a guardar tus consejos favoritos para verlos aquí</p>
      </div>
    </div>

  <!-- RECIPES GRID -->
  <v-row v-else no-gutters class="section-main__content ml-n4 mr-n4">
    <v-col
      v-for="(item, index) in paginatedPosts"
      :key="item.idPost"
      cols="12"
      md="4"
      xl="3"
    >
      <SectionPostCardItem 
        :item="item" 
        @update:item="($event) => updatePost(index, $event)"
        
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
const posts = ref<Post[]>([]);
const filteredPosts = computed(() => posts.value.filter(post => post.esFavoritoUsuario)); 

// Fetch favorite posts
const getFavoritePosts = async () => {
  loading.value = true;

  try { 
    const data = await useApiFetch(API.USER.FAVORITES, {
        method: 'GET',
        params: {
          tipoReferencia: 'POST'
        }
      });
    if (data) posts.value = data as Post[];
  } catch (error) {
    showError('No se ha podido cargar los posts favoritos');
  } finally {
    loading.value = false;
  }
};

/*******************************/
/* PAGINATION LOGIC */
/*******************************/
const currentPage = ref<number>(1);
const itemsPerPage = ref<number>(12);
const totalPages = computed(() => Math.ceil(filteredPosts.value.length / itemsPerPage.value));

const paginatedPosts = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  const end = start + itemsPerPage.value;
  return filteredPosts.value.slice(start, end);
});

const onPageChange = (page: number) => {
  currentPage.value = page;

  // Scroll to top of results
  window.scrollTo({ top: 0, behavior: 'smooth' });
};

const updatePost = (index: number, updatedPost: Post) => {
  const postIndex = posts.value.findIndex(p => p.idPost === updatedPost.idPost);
  if (postIndex !== -1) posts.value[postIndex] = updatedPost;
};

onMounted(() => {
  getFavoritePosts();
});
</script>
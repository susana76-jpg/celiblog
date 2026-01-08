<template>
  <section class="section-page consejos-page"> 

    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      title="Consejos"
      image-alt="consejos para vivir sin gluten"
      subtitle="Te ofrecemos orientación sencilla y útil para vivir sin gluten sin complicaciones. Aprende trucos, buenas prácticas y sugerencias para cocinar, comprar y comer fuera con total confianza"
      :image-src="img"
    />
    <!------------------------------------------->

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">

      <!-- TITLE -->
      <div class="section-main__title">
        <h2>Para cuidarte mejor</h2>
        <p>Descubre sugerencias simples y efectivas para evitar el gluten en tu día a día. Pequeños cambios que pueden marcar una gran diferencia en tu bienestar</p>
      </div>

      <!-- FILTER BAR -->
      <SectionFilterBar 
        label="Busca entre todos nuestros consejos"
        :search="keyword"
        :total="posts.length"
        :show-tags="false"
        @update:search="updateSearch"
      />

      <!-- LOADER -->
      <div v-if="loading" class="loader-container-index">
        <div class="loader">
          <div class="loader__spinner"></div>
          <p class="loader__text">Cargando consejos...</p>
        </div>
      </div>

      <!-- TIPS GRID -->
      <v-row no-gutters class="section-main__content">
        <v-col
          v-for="(item, index) in paginatedPosts"
          :key="item.idPost"
          cols="12"
          md="6"
          lg="4"
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

    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const { showError } = useNotification();

const img = '/img/blog/hero-image.jpg';
const posts = ref<Post[]>([]);
const keyword = ref<string>('');
const loading = ref<boolean>(true);
let searchTimeout: ReturnType<typeof setTimeout> | null = null;

// Search handlers
const updateSearch = (value: string) => {
  keyword.value = value;
  currentPage.value = 1;
  
  // Clear previous timeout
  if (searchTimeout) clearTimeout(searchTimeout);
  
  // Set new timeout
  searchTimeout = setTimeout(() => {
    getAllPosts();
  }, 500); 
};  

// Get receta by ID from API
const getAllPosts = async () => {
  loading.value = true;
  try {
    const data = await useApiFetch(API.POSTS.SEARCH, {
      params: {
        keyword: keyword.value,
      }
    });

    posts.value = data as Post[];
  } catch (error) {
    showError('No se han podido cargar los consejos');
  } finally {
    loading.value = false;
  }
};

// Update post in the list
const updatePost = (index: number, updatedPost: Post) => {
  const postIndex = posts.value.findIndex(p => p.idPost === updatedPost.idPost);
  if (postIndex !== -1) posts.value[postIndex] = updatedPost;
};

// Pagination logic
const { currentPage, totalPages, paginatedItems: paginatedPosts, onPageChange } = usePagination(posts);

// Fetch posts on mount
onMounted(() => {
  getAllPosts();
});
</script>
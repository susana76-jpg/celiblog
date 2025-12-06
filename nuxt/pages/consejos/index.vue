<template>
  <section class="section-page consejos-page"> 

    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      title="Consejos"
      image-alt="consejos para vivir sin gluten"
      subtitle="Te ofrecemos orientación sencilla y útil para vivir sin gluten sin complicaciones. Aprende trucos, buenas prácticas y sugerencias para cocinar, comprar y comer fuera con total confianza."
      :image-src="img"
    />
    <!------------------------------------------->

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Para cuidarte mejor</h2>
        <p>Descubre sugerencias simples y efectivas para evitar el gluten en tu día a día. Pequeños cambios que pueden marcar una gran diferencia en tu bienestar.</p>
      </div>
      <SectionFilterBar 
        label="Busca entre todos nuestros consejos"
        :search="keyword"
        :total="tips.length"
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
          v-for="item in paginatedTips"
          :key="item.id"
          cols="12"
          md="4"
          xl="3"
        >
          <SectionTipCardItem 
            :item="item" 
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
const img = '/img/consejos/hero-image.jpg';
const tips = ref<any[]>([]);
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
    getAllTips();
  }, 500); 
};  

// Get receta by ID from API
const getAllTips = async () => {
  loading.value = true;
  try {
    const data = await useApiFetch(API.POSTS.BASE);
    tips.value = data as any[];
  } catch (error) {
    console.error('Error fetching tips:', error);
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  getAllTips();
});

/*******************************/
/* PAGINATION LOGIC */
/*******************************/
const currentPage = ref<number>(1);
const itemsPerPage = ref<number>(12);
const totalPages = computed(() => Math.ceil(tips.value.length / itemsPerPage.value));

// Compute paginated tips
const paginatedTips = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  const end = start + itemsPerPage.value;
  return tips.value.slice(start, end);
});

// Handle page change
const onPageChange = (page: number) => {
  currentPage.value = page;

  // Scroll to top of results
  window.scrollTo({ top: 0, behavior: 'smooth' });
};
</script>
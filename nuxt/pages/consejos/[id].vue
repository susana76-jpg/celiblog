<template>
  <section class="details-page post-page">

    <!-- LOADER --------------------------------->
    <DetailsLoaderBlock v-if="loading" />
    <!------------------------------------------->

    <!-- CONTENT (shown when loaded) ------------>
    <div v-else>

      <!-- HERO IMAGE -->
      <DetailsHeroImage 
        v-if="post" 
        type="consejo"
        :item="post" 
        :imageUrl="post.urlPost"
      >
        <template #chip>
          <v-chip
            elevation="7"
            variant="outlined"
            class="card-image__chip bg-success"
            text="consejos practicos"
          />
        </template>
      </DetailsHeroImage> 

      <!-- MAIN CONTENT -->
      <div v-if="post" class="details-page__main post-description">
        <!-- <v-chip-group column  class="tags-list" >
          <v-chip 
            v-for="tag in consejo.tags" 
            :key="tag"
            size="large"
            class="bg-primary"
          >
            {{ tag }}
          </v-chip>
        </v-chip-group> -->

        <h2 class="mt-10">{{ setDate(post.fechaPublicacion) }}</h2>
        <h3 class="mb-4">{{ post.usuarioPublicacion.nombre }}</h3>
        <p class="mb-10" v-html="formatHtmlText(post.contenido)"></p>
        
        <!-- COMMENTS CONTENT -->
        <CommentsMainContent 
          class="mt-15" 
          itemType="POST" 
          :comentarios="comentarios"
          :itemId="id"
        />
      </div>
    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const route = useRoute();
const id = parseInt(route.params.id as string);
const post = ref<Post | null>(null);
const comentarios = ref<any[]>([]);
const loading = ref(true);

// Get receta by ID from API
const getConsejoById = async () => {
  loading.value = true;

  try {
    const data = await useApiFetch(API.POSTS.BY_ID, {
      params: { id },
    });
    post.value = data as Post; 
    post.value.urlPost = '/img/consejos/consejo' + id + '.jpg';
  } catch (error) {
    console.error('Error fetching consejo:', error);
  } finally {
    loading.value = false;
  }
};

// Fetch the receipe data when the component is mounted
onMounted(async () => {  
  await getConsejoById();
});
</script>

<style scoped lang="scss">
.recipe-page {
  font-family: 'Poppins', sans-serif;
  padding-bottom: 60px;
  
  .recipe-description {
    .v-chip__content {
      font-size: 1rem;
    }
  }
}
</style>
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
        type="post"
        :item="post" 
        :imageUrl="post.urlPost"
        defaultImage="/img/blog/comer-fuera-seguro.jpg"
        @update:favorite="post.esFavoritoUsuario = $event"
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
        <h2 class="mt-10">{{ setDate(post.fechaPublicacion) }}</h2>
        <h3 class="mb-4">{{ post.usuarioPublicacion.nombre }}</h3>
        <p class="mb-10" v-html="formatHtmlText(post.contenido)"></p>
        
        <!-- COMMENTS CONTENT -->
        <CommentsMainContent 
          v-if="showComments"
          class="mt-15" 
          :itemType="TYPE.POST as ObjectType" 
          :comentarios="comments"
          :itemId="id"
          @update:comentarios="getComments"
        />
      </div>
    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const { getCommentsByObjectId } = useComments();
const { showError } = useNotification();
const { isAuthenticated } = useAuthStore();

const route = useRoute();
const id = parseInt(route.params.id as string);
const post = ref<Post | null>(null);
const comments = ref<Comentario[]>([]);
const loading = ref(true);

// Get receta by ID from API
const getConsejoById = async () => {
  loading.value = true;

  try {
    const data = await useApiFetch(API.POSTS.BY_ID, {
      params: { id },
    });
    post.value = data as Post; 
    post.value.urlPost = '/img/' + post.value.urlPost;
  } catch (error) {
    showError('Error fetching consejo');
  } finally {
    loading.value = false;
  }
};

// Get comments for the restaurant with the given ID
const getComments = async () => {
  comments.value = await getCommentsByObjectId(TYPE.POST, id) ?? [];
};

// Determine if comments section should be hidden
const showComments = computed(() => comments.value.length > 0 || isAuthenticated.value); 

// Fetch the receipe data when the component is mounted
onMounted(async () => {  
  await getConsejoById();
  await getComments();
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
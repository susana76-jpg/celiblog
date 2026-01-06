<template>
  <section class="details-page recipe-page">

    <!-- LOADER --------------------------------->
    <DetailsLoaderBlock v-if="loading" />
    <!------------------------------------------->

    <!-- CONTENT (shown when loaded) ------------>
    <div v-else>

      <!-- HERO IMAGE ----------------------------->
      <DetailsHeroImage 
        v-if="receta" 
        type="receta"
        :item="receta" 
        :imageUrl="receta.imagenUrl"
        @update:favorite="receta.esFavoritoUsuario = $event"
      />
      <!------------------------------------------->

      <!-- MAIN CONTENT --------------------------->
      <div v-if="receta" class="details-page__main recipe-description">
        <DetailsReceipeKeyfact :receipe="receta" />
        
        <h2 class="mt-10 mb-4">Descripción de la receta</h2>
        <p v-html="formatHtmlText(receta?.descripcion)"></p>
        <h2 class="mt-10 mb-4">Pasos de la receta</h2>
        <ul v-for="(step, index) in pasos" :key="index" class="pl-5">
          <li class="mb-3">{{ step }}</li>
        </ul>

        <h2 class="mt-12 mb-2">Ingredientes de la receta</h2>
        <v-chip-group column>
          <v-chip 
            v-for="ingrediente in ingredientes" 
            :key="ingrediente"
            class="bg-primary"
          >
            {{ ingrediente }}
          </v-chip>
        </v-chip-group>

        <!-- COMMENTS CONTENT ----------------------->
        <CommentsMainContent 
          v-if="showComments"
          class="mt-15" 
          :itemType="TYPE.RECIPE as ObjectType" 
          :comentarios="comentarios"
          :itemId="id"
          @update:comentarios="getComments"
        />
        <!------------------------------------------->

      </div>
      <!------------------------------------------->

    </div>  
  </section>
</template>

<script setup lang="ts">
const { getCommentsByObjectId } = useComments();
const { showError } = useNotification();
const { isAuthenticated } = useAuthStore();

const route = useRoute();
const id = parseInt(route.params.id as string);
const comentarios = ref<Comentario[]>([]);
const ingredientes = ref<string[]>([]);
const receta = ref<Receta | null>(null);
const pasos = ref<string[]>([]);
const loading = ref(true);

// Get receta by ID from API
const getRecipeById = async () => {
  loading.value = true;

  try {
    const data = await useApiFetch(API.RECIPES.BY_ID, {
      params: { id },
    });
    receta.value = data as Receta; 
    receta.value.imagenUrl = '/img/recetas/' + receta.value.imagenUrl;
  } catch (error) {
    showError('Error fetching receipe');
  } finally {
    loading.value = false;
  }
};

// Get receta steps by ID from API
const getRecipeStepsById = async () => {
  try {
    let data = await useApiFetch(API.RECIPES.STEPS, {
      params: { idReceta: id },
    }) as RecetaPaso[];

    // Sort steps by 'orden' and map to descriptions
    data = data.sort((a: RecetaPaso, b: RecetaPaso) => a.orden - b.orden); 
    pasos.value = data.map((step: RecetaPaso) => step.descripcion);
  } catch (error) {
    showError('Error fetching steps');
  }
};

// Get receta ingredients by ID from API
const getRecipeIngredientsById = async () => {
  try {
    const data = await useApiFetch(API.RECIPES.INGREDIENTS, {
      params: { idReceta: id },
    }) as RecetaIngrediente[];

    ingredientes.value = data.map((ingrediente: RecetaIngrediente) => 
      `${ingrediente.cantidad} ${ingrediente.unidad} de ${ingrediente.nombre}`
    );
  } catch (error) {
    showError('Error fetching ingredients');
  }
};

// Get comments for the recipe with the given ID
const getComments = async () => {
  comentarios.value = await getCommentsByObjectId(TYPE.RECIPE, id) ?? [];
};  

// Determine if comments section should be hidden
const showComments = computed(() => comentarios.value.length > 0 || isAuthenticated.value);  

// Fetch the receipe data when the component is mounted
onMounted(async () => {
  await getRecipeById();
  await getRecipeStepsById();
  await getRecipeIngredientsById();
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

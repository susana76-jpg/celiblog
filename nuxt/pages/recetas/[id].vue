<template>
  <section class="details-page recipe-page">

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
    <div class="details-page__main recipe-description">

      <DetailsReceipeKeyfact 
        v-if="receta" 
        :receipe="receta" 
      />
      
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
        class="mt-15" 
        itemType="RECETA" 
        :comentarios="comentarios"
        :itemId="id"
      />
      <!------------------------------------------->

    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const route = useRoute();
const id = parseInt(route.params.id as string);
const comentarios = recetas[0].comentarios;
const ingredientes = ref<string[]>([]);
const receta = ref<Receta | null>(null);
const pasos = ref<string[]>([]);

// Get receta by ID from API
const getReceipeById = async () => {
  try {
    const data = await useApiFetch(API.RECIPES.BY_ID, {
      params: { id },
    });
    receta.value = data as Receta; 
    receta.value.imagenUrl = '/img/recetas/' + receta.value.imagenUrl;
  } catch (error) {
    console.error('Error fetching receipe:', error);
  }
};

// Get receta steps by ID from API
const getReceipeStepsById = async () => {
  try {
    let data = await useApiFetch(API.RECIPES.STEPS, {
      params: { idReceta: id },
    }) as RecetaPaso[];

    // Sort steps by 'orden' and map to descriptions
    data = data.sort((a: RecetaPaso, b: RecetaPaso) => a.orden - b.orden); 
    pasos.value = data.map((step: RecetaPaso) => step.descripcion);
  } catch (error) {
    console.error('Error fetching steps:', error);
  }
};

// Get receta ingredients by ID from API
const getReceipeIngredientsById = async () => {
  try {
    const data = await useApiFetch(API.RECIPES.INGREDIENTS, {
      params: { idReceta: id },
    }) as RecetaIngrediente[];

    ingredientes.value = data.map((ingrediente: RecetaIngrediente) => 
      `${ingrediente.cantidad} ${ingrediente.unidad} de ${ingrediente.nombre}`
    );
  } catch (error) {
    console.error('Error fetching ingredients:', error);
  }
};

// Fetch the receipe data when the component is mounted
onMounted(async () => {
  await getReceipeById();
  await getReceipeStepsById();
  await getReceipeIngredientsById();
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

<template>
  <section class="details-page recipe-page">
    <DetailsHeroImage v-if="receta" :receipe="receta" />
    <div class="recipe-description">
      <DetailsReceipeKeyfact v-if="receta" :receipe="receta" />
      <h2 class="mt-10 mb-4">Descripción de la receta</h2>
      <p>{{ receta.descripcion }}</p>
      <h2 class="mt-10 mb-4">Pasos de la receta</h2>
      <ul v-for="(step, index) in receta.pasos" :key="index" class="pl-5">
        <li class="mb-3">{{ step }}</li>
      </ul>

      <h2 class="mt-12 mb-2">Ingredientes de la receta</h2>
      <v-chip-group column>
        <v-chip 
          v-for="ingrediente in receta.ingredientes" 
          :key="ingrediente"
          class="bg-primary"
        >
          {{ ingrediente }}
        </v-chip>
      </v-chip-group>
    </div>

    <CommentsMainContent class="mt-8 mb-15" :comentarios="receta.comentarios" />
  </section>
</template>

<script setup lang="ts">
const route = useRoute();
const id = parseInt(route.params.id as string);
const receta = recetas[0]; // Example usage of the recetas array
const receipe = ref<Receta | null>(null);
const steps = ref<RecetaPaso[] | null>(null); // Initialize as null to avoid undefined issues

const getReceipeById = async () => {
  try {
    const { data } = await useApiFetch(API.RECETAS.BY_ID, {
      params: { id },
    });
    receipe.value = data.value as Receta; 
    console.log('Fetched receipe:', receipe.value); // Assign the fetched data to receipe
  } catch (error) {
    console.error('Error fetching receipe:', error);
  }
};

const getReceipeStepsById = async () => {
  try {
    const { data } = await useApiFetch(API.RECETAS.STEPS, {
      params: { idReceta: id },
    });
    steps.value = data.value as RecetaPaso[]; 
    console.log('Fetched steps:', steps.value); // Assign the fetched data to steps
  } catch (error) {
    console.error('Error fetching steps:', error);
  }
};

// Fetch the receipe data when the component is mounted
onMounted(() => {
  // getReceipeById();
  // getReceipeStepsById();
});
</script>

<style scoped lang="scss">
.recipe-page {
  font-family: 'Poppins', sans-serif;
  padding-bottom: 60px;
  
  .recipe-description {
    padding: 60px 120px;

    .v-chip__content {
      font-size: 1rem;
    }
  } 
}
</style>

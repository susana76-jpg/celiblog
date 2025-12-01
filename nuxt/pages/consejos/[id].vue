<template>
  <section class="details-page recipe-page">

    <!-- HERO IMAGE ----------------------------->
    <DetailsHeroImage 
      v-if="consejo" 
      type="consejo"
      :item="consejo" 
      :imageUrl="consejo.imagenUrl"
    >
      <template #chip>
        <v-chip
          elevation="7"
          variant="outlined"
          class="card-image__chip bg-success"
          :text="consejo.categoria"
        />
      </template>
    </DetailsHeroImage> 
    <!------------------------------------------->

    <!-- MAIN CONTENT --------------------------->
    <div class="details-page__main recipe-description">
      <v-chip-group column  class="tags-list" >
        <v-chip 
          v-for="tag in consejo.tags" 
          :key="tag"
          size="large"
          class="bg-primary"
        >
          {{ tag }}
        </v-chip>
      </v-chip-group>

      <h2 class="mt-10">{{ setDate(consejo.fechaCreacion) }}</h2>
      <h3 class="mb-4">{{ consejo.autor }}</h3>
      <p
        v-for="texto in consejo.texto"
        :key="texto"
        class="mb-6"
      >
        {{ texto }}
      </p>
      
      <!-- COMMENTS CONTENT ----------------------->
      <CommentsMainContent 
        class="mt-15" 
        :comentarios="comentarios" 
      />
      <!------------------------------------------->

    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const route = useRoute();
const id = parseInt(route.params.id as string);
const consejo = tip;
const comentarios = recetas[0].comentarios;
const ingredientes = ref<string[]>([]);
const pasos = ref<string[]>([]);

// Get receta by ID from API
const getConsejoById = async () => {
  // try {
  //   const data = await useApiFetch(API.RECIPES.BY_ID, {
  //     params: { id },
  //   });
  //   consejo.value = data as Consejo; 
  //   consejo.value.imagenUrl = '/img/consejos/consejo' + id + '.jpg';
  // } catch (error) {
  //   console.error('Error fetching consejo:', error);
  // }
};

// Get receta steps by ID from API
const getReceipeStepsById = async () => {
  // try {
  //   let data = await useApiFetch(API.RECIPES.STEPS, {
  //     params: { idReceta: id },
  //   }) as RecetaPaso[];

  //   // Sort steps by 'orden' and map to descriptions
  //   data = data.sort((a: RecetaPaso, b: RecetaPaso) => a.orden - b.orden); 
  //   pasos.value = data.map((step: RecetaPaso) => step.descripcion);
  // } catch (error) {
  //   console.error('Error fetching steps:', error);
  // }
};

// Get receta ingredients by ID from API
const getReceipeIngredientsById = async () => {
  // try {
  //   const data = await useApiFetch(API.RECIPES.INGREDIENTS, {
  //     params: { idReceta: id },
  //   }) as RecetaIngrediente[];

  //   ingredientes.value = data.map((ingrediente: RecetaIngrediente) => 
  //     `${ingrediente.cantidad} ${ingrediente.unidad} de ${ingrediente.nombre}`
  //   );
  // } catch (error) {
  //   console.error('Error fetching ingredients:', error);
  // }
};

// Fetch the receipe data when the component is mounted
onMounted(async () => {
  // await getConsejoById();
  // await getReceipeStepsById();
  // await getReceipeIngredientsById();
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
<template>
   <section class="details-page recipe-page">
    <DetailsHeroImage 
      v-if="restaurante" 
      :item="restaurante" 
      :imageUrl="restaurante.imagenUrl"
    />
    <div class="recipe-description">
      <h2 class="mt-10 mb-4">Descripción del restaurante</h2>
      <p>{{ restaurante.descripcion }}</p>

      <h2 class="mt-12 mb-2">Ingredientes de la receta</h2>
      <!-- <v-chip-group column>
        <v-chip 
          v-for="ingrediente in receta.ingredientes" 
          :key="ingrediente"
          class="bg-primary"
        >
          {{ ingrediente }}
        </v-chip>
      </v-chip-group> -->
    </div>

    <CommentsMainContent class="mt-8 mb-15" :comentarios="restaurante.comentarios" />
  </section>
</template>

<script setup lang="ts">
const route = useRoute();
const id = parseInt(route.params.id as string);
const restaurante = restaurantes[0]; 

</script>

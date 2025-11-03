<template>
  <v-card elevation="2">
    <v-img
      v-if="recipe.imagenUrl"
      :src="recipe.imagenUrl"
      height="200"
      cover
    ></v-img>
    <v-card-title>{{ recipe.titulo }}</v-card-title>
    <v-card-subtitle>
      Dificultad: {{ recipe.dificultad }}
    </v-card-subtitle>
    <v-card-text>{{ recipe.descripcion }}</v-card-text>
    <v-card-actions>
      <v-chip
        :color="recipe.estado === 'APROBADO' ? 'success' : recipe.estado === 'PENDIENTE' ? 'warning' : 'error'"
        size="small"
      >
        {{ recipe.estado }}
      </v-chip>
      <v-spacer />
      <v-btn variant="tonal" :to="`/recipes/${recipe.idReceta}`">Ver</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script setup lang="ts">
import type { Recipe } from '~/composables/useRecipeApi'
defineProps<{ recipe: Recipe }>()
</script>

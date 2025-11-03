<template>
  <v-card elevation="2">
    <v-card-title>{{ recipe.title }}</v-card-title>
    <v-card-text>{{ recipe.excerpt }}</v-card-text>
    <v-card-actions>
      <v-spacer />
      <v-btn variant="tonal">Ver</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script setup lang="ts">
defineProps<{ recipe: { id: number; title: string; excerpt: string } }>()
</script>

<template>
  <v-app>
    <NavBar />
    <v-main>
      <v-container class="py-6">
        <h2>Recetas de ejemplo</h2>
        <v-row>
          <v-col cols="12" md="4" v-for="r in recipes" :key="r.id">
            <RecipeCard :recipe="r" />
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
const recipes = [
  { id: 1, title: 'Tarta de manzana', excerpt: 'Tarta clásica, sin gluten.' },
  { id: 2, title: 'Pan sin gluten', excerpt: 'Pan casero, paso a paso.' },
  { id: 3, title: 'Galletas de avena', excerpt: 'Crujientes y fáciles.' }
]
</script>

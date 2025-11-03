<template>
  <v-app>
    <NavBar />
    <v-main>
      <v-container class="py-6">
        <div class="d-flex align-center mb-4">
          <h2 class="text-h4">Recetas sin gluten</h2>
          <v-spacer />
          <v-btn color="primary" prepend-icon="mdi-plus" to="/recipes/new">
            Nueva receta
          </v-btn>
        </div>

        <v-row v-if="pending">
          <v-col cols="12" md="4" v-for="n in 3" :key="n">
            <v-card>
              <v-card-text>
                <v-skeleton-loader type="image, article" />
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>

        <v-row v-else-if="error">
          <v-col cols="12">
            <v-alert type="error" text="Error al cargar las recetas. Por favor, intenta de nuevo." />
          </v-col>
        </v-row>

        <v-row v-else>
          <v-col cols="12" md="4" v-for="recipe in recipes" :key="recipe.idReceta">
            <RecipeCard :recipe="recipe" />
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
const { getAllRecipes } = useRecipeApi()
const { data: recipes, pending, error } = useAsyncData('recipes', () => getAllRecipes())
</script>

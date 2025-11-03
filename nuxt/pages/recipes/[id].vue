<template>
  <v-app>
    <NavBar />
    <v-main>
      <v-container class="py-6">
        <div v-if="pending">
          <v-skeleton-loader type="article" />
        </div>

        <div v-else-if="error">
          <v-alert type="error" text="Error al cargar la receta. Por favor, intenta de nuevo." />
        </div>

        <template v-else-if="recipe">
          <v-breadcrumbs :items="[
            { title: 'Recetas', to: '/recipes' },
            { title: recipe.titulo, disabled: true }
          ]" />

          <v-row>
            <v-col cols="12" md="8">
              <h1 class="text-h3 mb-4">{{ recipe.titulo }}</h1>
              
              <v-img
                v-if="recipe.imagenUrl"
                :src="recipe.imagenUrl"
                height="400"
                cover
                class="rounded mb-4"
              />

              <v-card class="mb-4">
                <v-card-text>
                  <div class="text-subtitle-1 mb-2">
                    <v-icon start>mdi-information</v-icon>
                    Descripción
                  </div>
                  <p>{{ recipe.descripcion }}</p>
                </v-card-text>
              </v-card>

              <v-card>
                <v-card-text>
                  <div class="text-subtitle-1 mb-4">
                    <v-icon start>mdi-format-list-numbered</v-icon>
                    Pasos
                  </div>

                  <v-timeline v-if="steps?.length">
                    <v-timeline-item
                      v-for="step in steps"
                      :key="step.idPasoReceta"
                      :dot-color="'primary'"
                      size="small"
                    >
                      <div class="text-subtitle-2 mb-2">Paso {{ step.orden }}</div>
                      <p>{{ step.descripcion }}</p>
                    </v-timeline-item>
                  </v-timeline>

                  <v-alert
                    v-else
                    type="info"
                    text="No hay pasos registrados para esta receta."
                  />
                </v-card-text>
              </v-card>
            </v-col>

            <v-col cols="12" md="4">
              <v-card>
                <v-card-text>
                  <v-list>
                    <v-list-item>
                      <template v-slot:prepend>
                        <v-icon>mdi-chef-hat</v-icon>
                      </template>
                      <v-list-item-title>Dificultad</v-list-item-title>
                      <v-list-item-subtitle>{{ recipe.dificultad }}</v-list-item-subtitle>
                    </v-list-item>

                    <v-list-item>
                      <template v-slot:prepend>
                        <v-icon>mdi-calendar</v-icon>
                      </template>
                      <v-list-item-title>Fecha</v-list-item-title>
                      <v-list-item-subtitle>{{ new Date(recipe.fechaCreacion).toLocaleDateString() }}</v-list-item-subtitle>
                    </v-list-item>

                    <v-list-item>
                      <template v-slot:prepend>
                        <v-icon>mdi-check-circle</v-icon>
                      </template>
                      <v-list-item-title>Estado</v-list-item-title>
                      <v-list-item-subtitle>
                        <v-chip
                          :color="recipe.estado === 'APROBADO' ? 'success' : recipe.estado === 'PENDIENTE' ? 'warning' : 'error'"
                          size="small"
                        >
                          {{ recipe.estado }}
                        </v-chip>
                      </v-list-item-subtitle>
                    </v-list-item>
                  </v-list>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </template>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
const route = useRoute()
const id = parseInt(route.params.id as string)
const { getRecipeById, getRecipeSteps } = useRecipeApi()

const { data: recipe, pending, error } = useAsyncData(
  `recipe-${id}`,
  () => getRecipeById(id)
)

const { data: steps } = useAsyncData(
  `recipe-steps-${id}`,
  () => getRecipeSteps(id)
)

</script>

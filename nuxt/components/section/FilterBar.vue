<template>
  <div class="filter-bar px-4 mb-10">

    <!-- TITLE ------------------------->
    <div class="filter-bar__title">
      <h3>Búsqueda</h3>
      <p>{{ total }} resultados</p>
    </div>
    <!---------------------------------->

    <!-- SEARCH AND FILTER ------------->
    <div class="filter-bar__inputs">
      <v-text-field 
        clearable
        single-line
        hide-details
        prepend-inner-icon="mdi-magnify"
        label="Busca entre todas nuestras recetas" 
        variant="outlined"
        color="primary"
        base-color="primary"
        density="comfortable"
        class="main-search-input"
        :model-value="search"
      />
      <v-menu
        v-model="menu"
        :close-on-content-click="false"
        location="end"
      >
        <template v-slot:activator="{ props }">
          <v-btn
            v-bind="props"
            append-icon="mdi-filter-variant"
            text="filtros"
            variant="outlined"
            color="primary"
            rounded="md"
            height="48px"
            size="large"
          />
        </template>

        <v-card min-width="300">
          Menu

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn
              variant="text"
              @click="menu = false"
            >
              Cancel
            </v-btn>
            <v-btn
              color="primary"
              variant="text"
              @click="menu = false"
            >
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-menu>
    </div>
    <!---------------------------------->

    <!-- CHIP GROUP -------------------->
    <v-chip-group
      column
      class="mt-1"
    >
      <v-chip
        v-for="tag in tags"
        :key="tag"
        closable
        base-color="primary"
        variant="flat"
      >
        {{ tag }}
      </v-chip>
    </v-chip-group>

  </div>
</template>

<script setup lang="ts">
const menu = ref<boolean>(false);
const search = ref<string>('');
const total = ref<number>(15); 
const tags = [ 'Fácil', 'Comida', 'Cena', 'Dulce', 'Salado', 'Desayuno' ];// This would typically come from props or a store
</script>

<style lang="scss">
.filter-bar {
  &__title {
    display: flex;
    align-items: flex-end;
    margin-bottom: 15px;

    h3 {
      font-size: 28px;
      line-height: 26px;
      font-weight: 500;
      letter-spacing: 0.2px;
      color: #242424;
      margin-bottom: 5px;
      margin-right: 12px;
    }

    p {
      font-size: 18px;
      padding: 1px 20px;
      border-radius: 4px;
      background-color: #2424243f;
    }
  }

  &__inputs {
    display: flex;
    align-items: center;
    gap: 10px;

    .main-search-input {
      flex-grow: 1; 

      .v-field__overlay {
        border: 2px solid #836A02 !important;
      }
    }
  }
}
</style>

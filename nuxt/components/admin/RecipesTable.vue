<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar recetas"
    add-button-label="Agregar Receta"
    :show-status-filter="true"
    :fetch-endpoint="API.STATUS.LIST"
    :fetch-params="getFetchParams"
    :delete-endpoint="API.RECIPES.DELETE"
    :delete-params="getDeleteParams"
    selector-column="estado"
    :selector-config="selectorConfig"
    :selector-update-endpoint="API.STATUS.UPDATE"
    :selector-update-params="getSelectorUpdateParams"
    :form-component="AdminRecipeForm"
    form-prop-name="recipe"
    form-update-event="get:recipes"
  />
</template>

<script setup lang="ts">
import AdminRecipeForm from './RecipeForm.vue';

/*************************************/
/* TABLE HEADERS CONFIGURATION */
/*************************************/  
const headers = [
  { title: 'Título', key: 'titulo', align: 'start' as const },
  { title: 'Fecha creación', key: 'fechaCreacion', align: 'start' as const },
  { title: 'Dificultad', key: 'dificultad', align: 'start' as const },
  { title: 'Tiempo', key: 'tiempoPreparacion', align: 'start' as const },
  { title: 'Comensales', key: 'comensales', align: 'start' as const },
  { title: 'Calorías', key: 'valorEnergetico', align: 'start' as const },
  { title: 'Categoría', key: 'tipoComida', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];


/*************************************/
/* FETCH CONFIGURATION */
/*************************************/
// Fetch configuration function
const getFetchParams = (statusFilter: string[]) => ({
  objeto: TYPE.RECIPE,
  estado: statusFilter.length === 3 ? ['TODOS'] : statusFilter
});


/*************************************/
/* DELETE CONFIGURATION */
/*************************************/
// Delete configuration functions
const getDeleteParams = (item: any) => ({ id: item.idReceta });


/*************************************/
/* STATUS SELECTOR CONFIGURATION */
/*************************************/
// Selector configuration
const selectorConfig = {
  items: Object.values(POST_STATUS),
  getValueFn: (item: any) => item.estado
};

// Selector update functions
const getSelectorUpdateParams = (item: any, newStatus: string) => ({
  idObjeto: item.idReceta,
  objeto: TYPE.RECIPE,
  estado: newStatus
});
</script>

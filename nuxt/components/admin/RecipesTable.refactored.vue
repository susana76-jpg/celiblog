<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar recetas"
    add-button-label="Agregar Receta"
    :show-status-filter="true"
    :fetch-endpoint="API.STATUS.LIST"
    :fetch-params="(statusFilter) => ({
      objeto: TYPE.RECIPE,
      estado: statusFilter.length === 3 ? ['TODOS'] : statusFilter
    })"
    :delete-endpoint="API.RECIPES.DELETE"
    :delete-params="(item) => ({ id: item.idReceta })"
    :delete-item-name-getter="(item) => item.titulo"
    delete-success-message="Receta eliminada correctamente"
    selector-column="estado"
    :selector-config="{
      items: Object.values(POST_STATUS),
      getValueFn: (item) => item.estado
    }"
    :selector-update-endpoint="API.STATUS.UPDATE"
    :selector-update-params="(item, newStatus) => ({
      idObjeto: item.idReceta,
      objeto: TYPE.RECIPE,
      estado: newStatus
    })"
    :selector-success-message="(item, newStatus) => 
      `Estado de la receta actualizado a ${newStatus}`"
    :form-component="AdminRecipeForm"
    form-prop-name="recipe"
    form-update-event="get:recipes"
  />
</template>

<script setup lang="ts">
import AdminRecipeForm from './RecipeForm.vue';

// Table headers
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
</script>

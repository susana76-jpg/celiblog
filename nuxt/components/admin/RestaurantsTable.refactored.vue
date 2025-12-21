<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar restaurantes"
    add-button-label="Agregar Restaurante"
    :show-status-filter="true"
    :fetch-endpoint="API.STATUS.LIST"
    :fetch-params="(statusFilter) => ({
      objeto: TYPE.RESTAURANT,
      estado: statusFilter.length === 3 ? ['TODOS'] : statusFilter
    })"
    :delete-endpoint="API.RESTAURANTS.DELETE"
    :delete-params="(item) => ({ id: item.idRestaurante })"
    :delete-item-name-getter="(item) => item.nombre"
    delete-success-message="Restaurante eliminado correctamente"
    selector-column="estado"
    :selector-config="{
      items: Object.values(POST_STATUS),
      getValueFn: (item) => item.estado
    }"
    :selector-update-endpoint="API.STATUS.UPDATE"
    :selector-update-params="(item, newStatus) => ({
      idObjeto: item.idRestaurante,
      objeto: TYPE.RESTAURANT,
      estado: newStatus
    })"
    :selector-success-message="(item, newStatus) => 
      `Estado del restaurante actualizado a ${newStatus}`"
    :form-component="AdminRestaurantForm"
    form-prop-name="restaurant"
    form-update-event="get:restaurants"
  />
</template>

<script setup lang="ts">
import AdminRestaurantForm from './RestaurantForm.vue';

// Table headers
const headers = [
  { title: 'Nombre', key: 'nombre', align: 'start' as const },
  { title: 'Dirección', key: 'direccion', align: 'start' as const },
  { title: 'Ciudad', key: 'ubicacion', align: 'start' as const },
  { title: 'Teléfono', key: 'telefono', align: 'start' as const },
  { title: 'Email', key: 'email', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];
</script>

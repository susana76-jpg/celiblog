<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar restaurantes"
    add-button-label="Agregar Restaurante"
    :show-status-filter="true"
    :fetch-endpoint="API.STATUS.LIST"
    :fetch-params="getFetchParams"
    :delete-endpoint="API.RESTAURANTS.DELETE"
    :delete-params="getDeleteParams"
    selector-column="estado"
    :selector-config="selectorConfig"
    :selector-update-endpoint="API.STATUS.UPDATE"
    :selector-update-params="getSelectorUpdateParams"
    :form-component="AdminRestaurantForm"
    form-prop-name="restaurant"
    form-update-event="get:restaurants"
  />
</template>

<script setup lang="ts">
import AdminRestaurantForm from './RestaurantForm.vue';

/*************************************/
/* TABLE HEADERS CONFIGURATION */
/*************************************/
const headers = [
  { title: 'Nombre', key: 'nombre', align: 'start' as const },
  { title: 'Dirección', key: 'direccion', align: 'start' as const },
  { title: 'Ciudad', key: 'ubicacion', align: 'start' as const },
  { title: 'Teléfono', key: 'telefono', align: 'start' as const },
  { title: 'Email', key: 'email', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];

/*************************************/
/* FETCH CONFIGURATION */
/*************************************/
// Fetch configuration function
const getFetchParams = (statusFilter: string[]) => ({
  objeto: TYPE.RESTAURANT,
  estado: statusFilter.length === 3 ? ['TODOS'] : statusFilter
});


/*************************************/
/* DELETE CONFIGURATION */
/*************************************/
// Delete configuration functions
const getDeleteParams = (item: any) => ({ id: item.idRestaurante });


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
  idObjeto: item.idRestaurante,
  objeto: TYPE.RESTAURANT,
  estado: newStatus
});
</script>

<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar usuarios"
    add-button-label="Agregar Usuario"
    :fetch-endpoint="API.USERS.BASE"
    :delete-endpoint="API.USERS.DELETE"
    :delete-params="getDeleteParams"
    selector-column="rol"
    :selector-config="selectorConfig"
    :selector-update-endpoint="API.USERS.UPDATE_ROLE"
    :selector-update-params="getSelectorUpdateParams"
    :show-edit-button="false"
    :form-component="AdminUserForm"
    form-prop-name="user"
    form-update-event="get:users"
  />
</template>

<script setup lang="ts">
import AdminUserForm from '@/components/admin/UserForm.vue';

// Table headers
const headers = [
  { title: 'Nombre', key: 'nombre', align: 'start' as const },
  { title: 'Correo electrónico', key: 'email', align: 'start' as const },
  { title: 'Fecha Alta', key: 'fechaAlta', align: 'start' as const },
  { title: 'Rol', key: 'rol', align: 'start' as const, sortable: false },
  { title: '', key: 'actions', align: 'end' as const, sortable: false },
];

// Delete configuration functions
const getDeleteParams = (item: any) => ({ idUsuario: item.idUsuario });


/*************************************/
/* ROLE SELECTOR CONFIGURATION */
/*************************************/
// Selector configuration
const selectorConfig = {
  items: USER_ROLES,
  itemTitle: 'nombre',
  itemValue: 'idRol',
  getValueFn: (item: any) => item.rol.idRol
};

// Selector update function
const getSelectorUpdateParams = (item: any, newRoleId: number) => ({
  idUsuario: item.idUsuario,
  idNuevoRol: newRoleId
});
</script>

<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar usuarios"
    add-button-label="Agregar Usuario"
    :fetch-endpoint="API.USERS.BASE"
    :delete-endpoint="API.USERS.DELETE"
    :delete-params="(item) => ({ idUsuario: item.idUsuario })"
    :delete-item-name-getter="(item) => item.nombre"
    delete-success-message="Usuario eliminado correctamente"
    selector-column="rol"
    :selector-config="{
      items: USER_ROLES,
      itemTitle: 'nombre',
      itemValue: 'idRol',
      getValueFn: (item) => item.rol.idRol
    }"
    :selector-update-endpoint="API.USERS.UPDATE_ROLE"
    :selector-update-params="(item, newRoleId) => ({
      idUsuario: item.idUsuario,
      idNuevoRol: newRoleId
    })"
    :selector-success-message="(item, newRoleId) => {
      const roleName = USER_ROLES.find(r => r.idRol === newRoleId)?.nombre || 'nuevo rol';
      return `Rol actualizado correctamente a ${roleName}`;
    }"
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
</script>

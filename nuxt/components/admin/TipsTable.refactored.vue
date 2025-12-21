<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar consejos"
    add-button-label="Agregar Consejo"
    :show-status-filter="true"
    :fetch-endpoint="API.STATUS.LIST"
    :fetch-params="(statusFilter) => ({
      objeto: TYPE.POST,
      estado: statusFilter.length === 3 ? ['TODOS'] : statusFilter
    })"
    :data-transformer="(data) => data.map((post: Post) => ({
      ...post,
      fechaPublicacion: formatDateTime(post.fechaPublicacion)
    }))"
    :delete-endpoint="API.RECIPES.DELETE"
    :delete-params="(item) => ({ id: item.idPost })"
    :delete-item-name-getter="(item) => item.titulo"
    delete-success-message="Post eliminado correctamente"
    selector-column="estado"
    :selector-config="{
      items: Object.values(POST_STATUS),
      getValueFn: (item) => item.estado
    }"
    :selector-update-endpoint="API.STATUS.UPDATE"
    :selector-update-params="(item, newStatus) => ({
      idObjeto: item.idPost,
      objeto: TYPE.POST,
      estado: newStatus
    })"
    :selector-success-message="(item, newStatus) => 
      `Estado del post actualizado a ${newStatus}`"
    :form-component="AdminTipForm"
    form-prop-name="post"
    form-update-event="get:posts"
  />
</template>

<script setup lang="ts">
import AdminTipForm from './TipForm.vue';

// Table headers
const headers = [
  { title: 'Título', key: 'titulo', align: 'start' as const },
  { title: 'Fecha publicación', key: 'fechaPublicacion', align: 'start' as const },
  { title: 'Autor', key: 'usuario.nombre', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];
</script>

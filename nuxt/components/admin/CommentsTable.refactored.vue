<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar comentarios"
    :show-status-filter="true"
    :show-add-button="false"
    :show-edit-button="false"
    :fetch-endpoint="API.STATUS.LIST"
    :fetch-params="(statusFilter) => ({
      objeto: TYPE.COMMENT,
      estado: statusFilter.length === 3 ? ['TODOS'] : statusFilter
    })"
    :data-transformer="(data) => data.map((comment: Comentario) => ({
      ...comment,
      fechaPublicacion: formatDateTime(comment.fechaPublicacion)
    }))"
    :delete-endpoint="API.COMMENTS.DELETE"
    :delete-params="(item) => ({ idComentario: item.idComentario })"
    :delete-item-name-getter="(item) => item.titulo"
    delete-success-message="Comentario eliminado correctamente"
    selector-column="estado"
    :selector-config="{
      items: Object.values(POST_STATUS),
      getValueFn: (item) => item.estado
    }"
    :selector-update-endpoint="API.STATUS.UPDATE"
    :selector-update-params="(item, newStatus) => ({
      idObjeto: item.idComentario,
      objeto: TYPE.COMMENT,
      estado: newStatus
    })"
    :selector-success-message="(item, newStatus) => 
      `Estado del comentario actualizado a ${newStatus}`"
  />
</template>

<script setup lang="ts">
// Table headers
const headers = [
  { title: 'Título', key: 'titulo', align: 'start' as const },
  { title: 'Fecha publicación', key: 'fechaPublicacion', align: 'start' as const },
  { title: 'Autor', key: 'usuario.nombre', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];
</script>

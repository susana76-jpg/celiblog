<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar consejos"
    add-button-label="Agregar Consejo"
    :show-status-filter="true"
    :fetch-endpoint="API.STATUS.LIST"
    :fetch-params="getFetchParams"
    :data-transformer="transformPostData"
    :delete-endpoint="API.RECIPES.DELETE"
    :delete-params="getDeleteParams"
    selector-column="estado"
    :selector-config="selectorConfig"
    :selector-update-endpoint="API.STATUS.UPDATE"
    :selector-update-params="getSelectorUpdateParams"
    :form-component="AdminPostForm"
    form-prop-name="post"
    form-update-event="get:posts"
  />
</template>

<script setup lang="ts">
import AdminPostForm from '@/components/admin/PostForm.vue';

// Table headers
const headers = [
  { title: 'Título', key: 'titulo', align: 'start' as const },
  { title: 'Fecha publicación', key: 'fechaPublicacion', align: 'start' as const },
  { title: 'Autor', key: 'usuario.nombre', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];

/********************************************/
/* FETCH PARAMS AND DATA TRANSFORMER */
/********************************************/
const getFetchParams = (statusFilter: string[]) => ({
  objeto: TYPE.POST,
  estado: statusFilter.length === 3 ? ['TODOS'] : statusFilter
});

// Data transformer function
const transformPostData = (data: Post[]) => 
  data.map((post: Post) => ({
    ...post,
    fechaPublicacion: formatDateTime(post.fechaPublicacion)
  }));


/********************************************/
/* DELETE CONFIGURATION */
/********************************************/
const getDeleteParams = (item: Post) => ({ id: item.idPost });


/********************************************/
/* STATUS SELECTOR CONFIGURATION */
/********************************************/
// Selector configuration
const selectorConfig = {
  items: Object.values(POST_STATUS),
  getValueFn: (item: Post) => item.estado
};

// Selector update params function
const getSelectorUpdateParams = (item: Post, newStatus: string) => ({
  idObjeto: item.idPost,
  objeto: TYPE.POST,
  estado: newStatus
});
</script>

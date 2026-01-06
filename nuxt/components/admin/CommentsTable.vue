<template>
  <AdminTable
    :headers="headers"
    search-label="Buscar comentarios"
    :show-status-filter="true"
    :show-add-button="false"
    :show-edit-button="false"
    :fetch-endpoint="API.STATUS.LIST"
    :fetch-params="getFetchParams"
    :delete-endpoint="API.COMMENTS.DELETE"
    :delete-params="getDeleteParams"
    selector-column="estado"
    :selector-config="selectorConfig"
    :selector-update-endpoint="API.STATUS.UPDATE"
    :selector-update-params="getSelectorUpdateParams"
  />
</template>

<script setup lang="ts">
/*************************************/
/* TABLE HEADERS CONFIGURATION */
/*************************************/
const headers = [
  { title: 'Título', key: 'titulo', align: 'start' as const },
  { title: 'Fecha publicación', key: 'fechaPublicacion', align: 'start' as const },
  { title: 'Autor', key: 'usuario.nombre', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];


/*************************************/
/* FETCH CONFIGURATION */
/*************************************/
// Fetch configuration function
const getFetchParams = (statusFilter: string[]) => ({
  objeto: TYPE.COMMENT,
  estado: statusFilter.length === 3 ? ['TODOS'] : statusFilter
});


/*************************************/
/* DELETE CONFIGURATION */
/*************************************/
// Delete configuration functions
const getDeleteParams = (item: any) => ({ idComentario: item.idComentario });


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
  idObjeto: item.idComentario,
  objeto: TYPE.COMMENT,
  estado: newStatus
});
</script>

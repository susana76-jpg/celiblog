<template>
  <div class="admin-table recipes-table">
    <div class="d-flex">
      <v-text-field
        clearable
        single-line
        hide-details
        class="mb-3 flex-grow-1 mr-2"
        density="compact"
        variant="outlined"
        label="Buscar comentarios"
        prepend-inner-icon="mdi-magnify"
        v-model="search"
      ></v-text-field>
      <v-select
        chips
        multiple
        single-line
        hide-details
        width="350"
        max-width="350"
        class="status-filter mr-0"
        variant="outlined"
        density="compact"
        :items="Object.values(POST_STATUS)"
        v-model="statusFilter"
        @update:modelValue="getComments"
      />
    </div>
    <v-data-table
      hide-default-footer
      density="compact"
      class="mb-10"
      :headers="headers"
      :items="comments"
      :items-length="comments.length"
      :loading="loading"
      :search="search"
      :items-per-page="-1"
      item-value="name"
    >
      <template v-slot:item.estado="{ item }">
        <v-select
          chips
          single-line
          hide-details
          variant="outlined"
          density="compact"
          :items="Object.values(POST_STATUS)"
          v-model="item.estado"
          @update:modelValue="($event) => updateStatus(item, $event)"
        ></v-select>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn 
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="handleDelete(item.titulo, item.idComentario)"
        >
          <v-icon>mdi-delete-outline</v-icon>
        </v-btn>
      </template>
    </v-data-table>
  </div>
</template>

<script setup lang="ts">
const search = ref<string>('');
const loading = ref<boolean>(false);
const statusFilter = ref<string[]>(Object.values(POST_STATUS));

// Use global notification composable
const { showSuccess, showError } = useNotification();


/*************************************/
/* COMMENTS DATA TABLE */
/*************************************/
// List of comments
const comments = ref<Comentario[]>([]);

// Table headers
const headers = [
  { title: 'Título', key: 'titulo', align: 'start' as const },
  { title: 'Fecha publicación', key: 'fechaPublicacion', align: 'start' as const },
  { title: 'Autor', key: 'usuario.nombre', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];


/*************************************/
/* CONFIRM DIALOG USAGE */
/*************************************/
// Import the global confirm dialog composable
const { showConfirmDialog } = useConfirmDialog();

// Example function to handle delete operations
const handleDelete = (itemName: string, itemId: number) => {
  const text = `Si eliminas a ${itemName} de la base de datos, desaparecerá del sistema. ¿Deseas continuar?`;
  showConfirmDialog(text, () => deleteComment(itemId));
}

/*************************************/
/* API CALLS */
/*************************************/
// Delete comment
const deleteComment = async (id: number) => {
  try {
    const response = await useApiFetch(API.COMMENTS.DELETE, {
      method: 'DELETE',
      params: { idComentario: id }
    });

    if (response) showSuccess(`Comentario eliminado correctamente`);
    getComments();
  } catch (error: any) {
    showError('Error al eliminar el comentario');
  }
}

// Fetch comments from API on component mount
const getComments = async () => {
  loading.value = true;
  try {
    const response = await useApiFetch(API.STATUS.LIST, {
      method: 'GET',
      params: {
        objeto: TYPE.COMMENT,
        estado: statusFilter.value.length === 3 ? ['TODOS'] : statusFilter.value
      }
    });
    comments.value = (response as Comentario[]).map((commentData: Comentario) => ({
      ...commentData,
      fechaPublicacion: formatDateTime(commentData.fechaPublicacion)
    }));;
  } catch (error: any) {
    showError(`Error al cargar los comentarios: ${error.message || error}`);
  } finally {
    loading.value = false;  
  }
};

// Update post status
const updateStatus = async (comment: Comentario, newStatus: string) => {
  try {
    const response = await useApiFetch(API.STATUS.UPDATE, {
      method: 'PUT',
      params: {
        idObjeto: comment.idComentario,
        objeto: TYPE.COMMENT,
        estado: newStatus
      }
    });

    if (response) {
      showSuccess(`Estado del comentario actualizado a ${newStatus}`);
      getComments();
    }
  } catch (error: any) {
    showError('Error al actualizar el estado del comentario');
  }
};

// Load data on mount
onMounted(() => {
  getComments();
})
</script>

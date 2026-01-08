<template>

  <!-- LOADER ------------------------------------------>
  <div v-if="loading" class="loader-container-index">
    <div class="loader">
      <div class="loader__spinner"></div>
      <p class="loader__text">Cargando comentarios...</p>
    </div>
  </div>
  <!---------------------------------------------------->

  <!-- NO FAVORITES MESSAGE ---------------------------->
  <div v-else-if="!loading && comments.length === 0" class="no-favorites-container">
    <div class="no-favorites-content">
      <v-icon size="100" color="primary">mdi-heart-outline</v-icon>
      <h3 class="text-h5 mt-4 mb-2">No tienes comentarios publicados</h3>
      <p class="text-body-1">Empieza a escribir comentarios para verlos aquí</p>
    </div>
  </div>
  <!---------------------------------------------------->

  <!-- COMMENTS GRID ----------------------------------->
  <v-row v-else no-gutters class="section-main__content">
    <v-col
      v-for="(item, index) in paginatedComments"
      :key="item.idComentario"
      cols="12"
    >
      <UserCommentCard 
        :comentario="item" 
        @edit="editComment(item)" 
        @delete="deleteComment(item)" 
      />
    </v-col>
  </v-row>
  <!---------------------------------------------------->

  <!-- PAGINATION -------------------------------------->
  <div 
    v-if="!loading && totalPages > 1"
    class="pagination-container mt-6 mb-10"
  >
    <v-pagination
      active-color="primary"
      variant="flat"
      v-model="currentPage"
      :length="totalPages"
      :total-visible="7"
      @update:model-value="onPageChange"
    ></v-pagination>
  </div>
  <!---------------------------------------------------->

  <!-- EDIT COMMENT FORM ------------------------------->
  <UserCommentForm
    :show="showEditForm"
    :comment="comment"
    @close="getComments(); showEditForm = false"
  />
  <!---------------------------------------------------->

</template>

<script setup lang="ts">
const { showError, showSuccess } = useNotification();
const { showConfirmDialog } = useConfirmDialog();

const showEditForm = ref<boolean>(false);
const loading = ref<boolean>(false);
const comments = ref<Comentario[]>([]);
const comment = ref<Comentario | null>(null);


/*******************************/
/* API CALLS */
/*******************************/
// Fetch user comments
const getComments = async () => {
  loading.value = true;

  try { 
    const data = await useApiFetch(API.USER.COMMENTS, {
        method: 'GET',
        params: {
          objetos: 'COMENTARIO'
        }
      });
    if (data) comments.value = data as Comentario[];
  } catch (error) {
    showError('No se ha podido cargar los comentarios');
  } finally {
    loading.value = false;
  }
};

// Edit comment
const editComment = (item: Comentario) => {
  showEditForm.value = true;
  comment.value = item;
};

// Delete comment with confirmation
const deleteComment = async (comment: Comentario) => {
  const text = `Si eliminas esta comentario desaparecerá del sistema. ¿Deseas continuar?`;
  showConfirmDialog(text, () => deleteItem(comment));
};

// Delete comment
const deleteItem = async (comment: Comentario) => {
  try {
    await useApiFetch(API.COMMENTS.DELETE, { 
      method: 'DELETE',
      params: { idComentario: comment.idComentario } 
    });

    showSuccess('Comentario eliminado correctamente.');
    getComments();
  } catch (err: any) {
    showError(`Error al eliminar el comentario: ${err?.data}`);
  }
};


/*******************************/
/* PAGINATION LOGIC */
/*******************************/
const { currentPage, totalPages, paginatedItems: paginatedComments, onPageChange } = usePagination(comments);

onMounted(() => {
  getComments();
});
</script>
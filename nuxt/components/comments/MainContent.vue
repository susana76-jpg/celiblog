<template>
  <section class="comment-section">
    <div class="comment-section__header">
      <h2>Comentarios</h2>
      <v-btn
        v-if="isAuthenticated"
        class="ml-4"
        color="primary"
        variant="flat"
        prepend-icon="mdi-plus"
        text="Añadir comentario"
        @click="openCommentForm = true"
      />
    </div>
    <v-divider :thickness="3"></v-divider>
    <v-container fluid>
      <CommentsSingleComment 
        v-for="(comentario, index) in comentarios" 
        :key="index" 
        :comentario="comentario" 
      />
    </v-container>

    <CommentsReviewForm 
      :show="openCommentForm"
      :itemType="itemType"
      :itemId="itemId"
      @close="closeDialog"
    />
  </section>
</template>

<script setup lang="ts">
const { isAuthenticated } = useAuthStore();

const props = defineProps<{
  comentarios: Comentario[];
  itemType: 'RECETA' | 'RESTAURANTE' | 'POST';
  itemId: number;
}>();

const emit = defineEmits<{
  (e: 'update:comentarios'): void;
}>();

const openCommentForm = ref<boolean>(false);

// Close dialog and emit event to update comments
const closeDialog = () => {
  openCommentForm.value = false;
  emit('update:comentarios');
};
</script>

<style scoped lang="scss">
.comment-section {
  padding: 50px;
  border-radius: 10px;
  background-color: #8080801f;

  &__header {
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    margin-bottom: 15px;
  }
}
</style>
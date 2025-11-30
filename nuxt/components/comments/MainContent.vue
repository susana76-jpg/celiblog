<template>
  <section class="comment-section">
    <div class="comment-section__header">
      <h2>Comentarios</h2>
      <v-btn
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
      @close="openCommentForm = false"
    />
  </section>
</template>

<script setup lang="ts">
const props = defineProps<{
  comentarios: any[];
}>();

const openCommentForm = ref<boolean>(false);
</script>

<style scoped lang="scss">
.comment-section {
  padding: 50px;
  padding-bottom: 0;
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
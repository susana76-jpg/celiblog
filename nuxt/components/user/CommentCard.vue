<template>
  <div class="user-comment-card">

    <!-- COMMENT HEADER ---------------------------------------->
    <div class="user-comment-card__header">
      <h3>{{ comentario.titulo }}</h3>
      <div>
        <v-icon
          variant="text"
          icon="mdi-pencil-outline"
          class="mr-1"
          @click="$emit('edit')"
        />
        <v-icon
          variant="text"
          icon="mdi-trash-can-outline"
          @click="$emit('delete')"
        />
      </div>
    </div>
    <div class="user-comment-card__divider"></div>
    <!---------------------------------------------------------->

    <!-- COMMENT DATE AND RATING ------------------------------->
    <div class="d-flex justify-space-between">
      <p class="user-comment-card__date">
        {{ setDate(comentario.fechaPublicacion) }}
      </p>
      <v-rating
        readonly
        half-increments
        color="primary"
        size="small"
        density="compact"
        :model-value="comentario.valoracion"
      ></v-rating>
    </div>
    <!---------------------------------------------------------->

    <!-- COMMENT CONTENT --------------------------------------->
    <p class="user-comment-card__content">
      {{ comentario.contenido }}
    </p>
    <!---------------------------------------------------------->

  </div>
</template>

<script setup lang="ts">
// Props
defineProps<{
  comentario: Comentario;
}>();

// Emits
defineEmits<{
  edit: [];
  delete: [];
}>();
</script>

<style scoped lang="scss">
@import '@/assets/styles/variables';

.user-comment-card {
  background-color: #afafaf40;
  border-radius: $border-radius-md;
  padding: 30px 40px;

  &__header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #3b3b3b;

    h3 {
      font-size: 1.35rem;
      font-weight: 600;
      line-height: 22px;
      margin: 0 0 12px 0;
      padding-right: 60px;
    }
  }

  &__divider {
    width: 100%;
    height: 1px;
    background-color: #555555;
    margin-bottom: 16px;
  }

  &__date {
    font-size: 18px;
    font-weight: 400;
    line-height: 21px;
    letter-spacing: 1.5px;
    color: $logo-color;
    margin: 0 0 8px 0;
  }

  &__content {
    font-size: 1rem;
    line-height: 1.3125rem;
    letter-spacing: 1.5px;
    color: #242424;
    margin: 0;
    white-space: pre-wrap;
    word-wrap: break-word;
  }
}
</style>

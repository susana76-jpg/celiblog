<template>
  <v-card 
    flat
    class="comment"
  >
    <v-card-subtitle class="comment__title">
      <span>{{ comentario.titulo }}</span>
      <v-rating
        readonly
        half-increments
        color="primary"
        size="small"
        density="compact"
        :model-value="comentario.valoracion"
      ></v-rating>
    </v-card-subtitle>
    <v-card-title class="comment__header">
      <div class="d-flex">
        <p class="comment__date mr-1">{{ comentario.fechaPublicacion }} - </p>
        <h3 class="comment__username">{{ comentario.usuarioPublicacion.nombre }}</h3>
      </div>
    </v-card-title>
    <v-card-text class="comment__body">
      {{ comentario.contenido }}
    </v-card-text>
  </v-card>
</template>

<script setup lang="ts">
const props = defineProps<{
  comentario: Comentario
}>();
</script>

<style scoped lang="scss">
.comment {
  background-color: transparent;
  margin-top: 20px;
  margin-bottom: 46px;

  &__header {
    display: flex;
    align-items: flex-end;
    margin-bottom: 4px;
    padding: 0;

    h3 {
      font-size: 1rem;
      line-height: 1rem;
      font-weight: 600;
      color: #836A02;
    }

    p {
      font-size: 0.8rem;
      color: #836A02;
    }
  }

  &__title {
    padding: 0;
    margin-bottom: 6px;
    font-weight: 600;
    font-size: 1.25rem;
    line-height: 1.1rem;
    color: #242424;
    opacity: 1;
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
  }

  &__body {
    padding: 0;
    font-size: 1rem;
    line-height: 1.3rem;
  }
}
</style>
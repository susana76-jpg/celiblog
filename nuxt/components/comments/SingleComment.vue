<template>
  <v-card 
    flat
    class="comment"
  >
    <v-card-title class="comment__header">
      <v-avatar size="48" class="mr-2">
        <v-img :src="comentario.img || '/img/default-user.png'" alt="User Avatar" />
      </v-avatar>
      <div>
        <h3 class="comment__username">{{ comentario.usuario }}</h3>
        <p class="comment__date">{{ new Date(comentario.fechaPublicacion) }}</p>
      </div>
    </v-card-title>
    <v-card-subtitle class="comment__title">
      <span>{{ comentario.titulo }}</span>
      <v-rating
        readonly
        half-increments
        color="primary"
        size="small"
        density="compact"
        :model-value="comentario.valoracion || 4"
      ></v-rating>
    </v-card-subtitle>
    <v-card-text class="comment__body">
      {{ comentario.comentario }}
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  props: {
    comentario: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped lang="scss">
.comment {
  background-color: transparent;
  margin-top: 20px;
  margin-bottom: 46px;

  &__header {
    display: flex;
    align-items: flex-end;
    margin-bottom: 6px;
    padding: 0;

    h3 {
      font-size: 1rem;
      line-height: 1rem;
      font-weight: 600;
      color: #5D4037;
    }

    p {
      font-size: 0.8rem;
      color: #836A02;
    }
  }

  &__title {
    padding: 0;
    padding-top: 6px;
    font-weight: 600;
    font-size: 1.1rem;
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
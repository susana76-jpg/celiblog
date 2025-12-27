<template>
  <v-card 
    :to="`/recetas/${item.idReceta}`" 
    variant="text"
    class="card-item mx-2 pa-2" 
  >
    <div class="card-image">
      <v-img 
        cover 
        height="400" 
        :src="'/img/recetas/' + item.imagenUrl"
      />
      <v-chip
        class="card-image__chip"
        :class="setChipClass(item.dificultad)"
        variant="outlined"
      >
        <span>{{ item.dificultad || 'fácil' }}</span>
        <v-rating
          readonly
          color="white"
          length="3"
          :model-value="item.dificultad === 'fácil' ? 1 : item.dificultad === 'media' ? 2 : 3"
        ></v-rating>
      </v-chip>
      <v-btn
        v-if="isAuthenticated"
        :color="item.esFavoritoUsuario ? 'error' : 'darkgray'"
        variant="outlined"
        icon="mdi-heart"
        class="card-image__favourite"
      />
    </div>
    <v-card-title class="px-0">
      {{ item.titulo }}
    </v-card-title>
    <v-card-text class="px-0">
      {{ truncatedDescription }}
    </v-card-text>
    <v-rating
      readonly
      half-increments
      color="primary"
      density="compact"
      :model-value="item.valoracion"
    ></v-rating>
  </v-card>
</template>

<script setup lang="ts">
const { isAuthenticated } = useAuthStore();

const props = defineProps<{
  item: Receta;
}>();

// Truncate description to 130 characters
const truncatedDescription = computed(() => {
  const maxLength = 130;
  return props.item.subtitulo.length > maxLength 
    ? props.item.subtitulo.slice(0, maxLength) + '...' 
    : props.item.subtitulo;
});

// Set chip class based on difficulty level
const setChipClass = (difficulty: Receta['dificultad'] | undefined) => {
  switch (difficulty) {
    case 'fácil':
      return 'bg-success';
    case 'media':
      return 'bg-warning';
    case 'difícil':
      return 'bg-error';
    default:
      return 'bg-success';
  }
};

</script>

<style lang="scss">
.card-item {
  margin-bottom: 24px;

  .card-image {
    position: relative;

    img.v-img__img {
      border-radius: 6px;
    }

    &__chip {
      position: absolute;
      top: 20px;
      right: 20px;
      width: 180px;
      font-family: 'Poppins', sans-serif;
      font-size: 16px;
      line-height: 16px;
      font-weight: 500;
      text-transform: capitalize;
      color: #FFF !important;
      border: 3px solid #FFFFFF;

      .v-chip__content {
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;

        .v-rating__item {
          button.v-btn {
            height: 25px !important;
            width: 25px !important;
          }
        }
      }
    }

    &__favourite {
      position: absolute;
      bottom: 20px;
      right: 20px;
      background-color: #ffffffb2;
    }
  } 
  
  .v-card-title {
    font-family: 'Arial', sans-serif;
    font-size: 20px;
    line-height: 20px;
    font-weight: 600;
    color: #333333;
    margin-top: 6px;
    padding-bottom: 6px;
  }

  .v-card-text {
    font-family: 'Poppins', sans-serif;
    font-size: 14px;
    line-height: 16px;
    color: #555555;
    padding-bottom: 8px;
  }
}
</style>

<template>
  <v-card 
    :to="setLink" 
    variant="text"
    class="card-item mx-2 pa-2" 
  >
    <div class="card-image">
      <v-img 
        cover 
        height="400" 
        :src="item.urlPost"
      />
      <v-chip 
        class="card-image__chip bg-success text-white"
        variant="outlined"
      >
        <v-icon left>mdi-plus</v-icon>
        Información
      </v-chip>
      <v-btn
        :color="item.esFavoritoUsuario ? 'error' : 'darkgray'"
        variant="outlined"
        icon="mdi-heart"
        class="card-image__favourite"
        @click.stop="$emit('toggle-favorite', item.idPost)"
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
const props = defineProps<{
  item: Post;
}>();


const setLink = computed(() => `/consejos/${props.item.idPost}`);

// Truncate description to 130 characters
const truncatedDescription = computed(() => {
  const maxLength = 130;
  return props.item.subtitulo.length > maxLength 
    ? props.item.subtitulo.slice(0, maxLength) + '...' 
    : props.item.subtitulo;
});
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

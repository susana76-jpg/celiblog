<template>
  <v-card 
    :to="item.link" 
    variant="text"
    class="card-item mx-2 pa-2" 
  >
    <div class="card-image">
      <v-img 
        cover 
        height="400" 
        :src="item.image"
      />
      <v-chip
        class="card-image__chip"
        :class="setChipClass(item.difficulty)"
        variant="outlined"
      >
        <span>{{ item.difficulty || 'easy' }}</span>
        <v-rating
          readonly
          color="white"
          length="3"
          :model-value="item.difficulty === 'easy' ? 1 : item.difficulty === 'medium' ? 2 : 3"
        ></v-rating>
      </v-chip>
      <v-btn
        :color="item.favorite ? 'error' : 'darkgray'"
        variant="outlined"
        icon="mdi-heart"
        class="card-image__favourite"
        @click.stop="$emit('toggle-favorite', item.id)"
      />
    </div>
    <v-card-title class="px-0">
      {{ item.title }}
    </v-card-title>
    <v-card-text class="px-0">
      {{ item.description }}
    </v-card-text>
    <v-rating
      readonly
      half-increments
      color="primary"
      density="compact"
      :model-value="item.rating"
    ></v-rating>
  </v-card>
</template>

<script setup lang="ts">
const props = defineProps<{
  item: {
    id: number;
    title: string;
    description: string;
    image: string;
    link: string;
    rating: number;
    favorite?: boolean;
    difficulty?: string;
  };
}>(); 

const setChipClass = (difficulty: string | undefined) => {
  switch (difficulty) {
    case 'easy':
      return 'bg-success';
    case 'medium':
      return 'bg-warning';
    case 'hard':
      return 'bg-error';
    default:
      return 'bg-success';
  }
};
</script>

<style lang="scss">
.card-item {
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

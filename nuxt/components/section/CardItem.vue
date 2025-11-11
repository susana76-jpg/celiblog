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
        border="sm"
        class="card-image__chip bg-success"
        color="white"
        variant="outlined"
      >
        {{ item.difficulty || 'easy' }}
        <v-rating
          readonly
          size="small"
          color="primary"
          length="3"
          :model-value="item.difficulty === 'easy' ? 1 : item.difficulty === 'medium' ? 2 : 3"
        ></v-rating>
      </v-chip>
      <v-btn
        :color="item.favorite ? 'red lighten-2' : 'grey lighten-1'"
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
      size="small"
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
      width: 200px;
      font-family: 'Poppins', sans-serif;
      font-size: 14px;
      line-height: 16px;
      font-weight: 500;
      text-transform: capitalize;
    }

    &__favourite {
      position: absolute;
      bottom: 20px;
      right: 20px;
    }
  } 
  
  .v-card-title {
    font-family: 'Arial', sans-serif;
    font-size: 18px;
    line-height: 20px;
    font-weight: 600;
    color: #333333;
    padding-bottom: 6px;
  }

  .v-card-text {
    font-family: 'Poppins', sans-serif;
    font-size: 14px;
    line-height: 12px;
    color: #555555;
    padding-bottom: 8px;
  }
}
</style>

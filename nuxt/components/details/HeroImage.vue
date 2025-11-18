<template>
  <div class="details-page__hero-header d-flex">
    <div class="details-page__hero-info">
      <v-chip
        size="x-large"
        elevation="7"
        variant="outlined"
        class="card-image__chip"
        :class="setLevelChip(receipe.dificultad).color"
      >
        <span>{{ receipe.dificultad || 'fácil' }}</span>
        <v-rating
          readonly
          color="white"
          length="3"
          :model-value="setLevelChip(receipe.dificultad).rating"
        ></v-rating>
      </v-chip>
      <div class="details-page__hero-info__text">
        <v-rating
          readonly
          half-increments
          color="#5D4037"
          density="compact"
          :model-value="4"
        ></v-rating>
        <h1>{{ receipe.titulo }}</h1>
        <p>{{ receipe.subtitulo }}</p>
      </div>
    </div>
    <div class="details-page__hero-image">
      <v-img 
        cover
        height="500"
        src="/img/receipe-hero-image.png" 
        alt="Hero Image" 
      />
      <v-btn
        :color="true ? 'error' : 'darkgray'"
        variant="outlined"
        icon="mdi-heart"
        class="details-page__hero-image__favourite"
        @click.stop="null"
      />
    </div>
  </div>
</template>
<script setup lang="ts">
const props = defineProps<{
  receipe: any;
}>();
</script>
<style lang="scss">
.details-page {
  &__hero-header {
    height: 480px;
    width: 100%;
    border-top: 3px solid #836A02;
    border-bottom: 3px solid #836A02;
  }

  &__hero-info {
    width: 50%;
    padding: 50px 50px 50px 120px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    .v-chip {
      padding: 10px 20px !important;
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

    &__text {
      
      .v-rating {
        width: 190px;
        display: flex;
        justify-content: space-between;

        > label {
          display: none;
        }

        .v-btn--icon.v-btn--size-default {
          --v-btn-size: 1.5rem;
        }
      }

      h1 {
        font-family: 'Arial', sans-serif;
        font-size: 3rem;
        line-height: 40px;
        font-weight: 700;
        margin-bottom: 20px;
        margin-top: 30px;
        color: #836A02;
      }

      p {
        font-family: 'Poppins', sans-serif;
        font-size: 18px;
        line-height: 22px;
        font-weight: 400;
        color: #242424;
      }
    }
  }

  &__hero-image {
    position: relative;
    width: 50%;

    &__favourite {
      position: absolute;
      top: 2rem;
      right: 80px;
      height: 80px !important;
      width: 80px !important;
      background-color: #ffffffd3;

      .v-icon {
        --v-icon-size-multiplier: 2 !important;
      }
    }
  }
}
</style>
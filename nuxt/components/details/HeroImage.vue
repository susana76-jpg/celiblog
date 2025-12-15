<template>
  <div class="details-page__hero-header d-flex">
    <div class="details-page__hero-info">

      <slot name="chip" :item="item" :setLevelChip="setLevelChip">
        <!-- Default chip content for recetas -->
        <v-chip
          size="x-large"
          elevation="7"
          variant="outlined"
          class="card-image__chip"
          :class="setLevelChip(item.dificultad).color"
        >
          <span>{{ item.dificultad || 'fácil' }}</span>
          <v-rating
            readonly
            color="white"
            length="3"
            :model-value="setLevelChip(item.dificultad).rating"
          ></v-rating>
        </v-chip>
      </slot>
      
      <div class="details-page__hero-info__text">
        <v-rating
          v-if="props.showRating"
          readonly
          half-increments
          color="#5D4037"
          density="compact"
          :model-value="item.valoracion || 4"
        ></v-rating>
        <h1>{{ title }}</h1>
        <p>{{ item.subtitulo }}</p>
      </div>
    </div>
    <div class="details-page__hero-image">
      <v-img 
        cover
        height="500"
        :src="imageUrl || '/img/receipe-hero-image.png'" 
        :alt="item.titulo || 'Hero Image'" 
      />
      <v-btn
        v-if="isAuthenticated && props.showFavorite"
        :color="item.esFavoritoUsuario ? 'error' : 'darkgray'"
        variant="outlined"
        icon="mdi-heart"
        class="details-page__hero-image__favourite"
        @click.stop="toggleFavorite(item)"
      />
    </div>
  </div>
</template>
<script setup lang="ts">
const { isAuthenticated } = useAuthStore();
const { addToFavorites, removeFromFavorites } = useUserActions();


const props = withDefaults(defineProps<{
  item: any;
  type: 'receta' | 'restaurante' | 'consejo';
  imageUrl?: string;
  showRating?: boolean;
  showFavorite?: boolean;
  isFavorite?: boolean;
}>(), {
  showRating: true,
  showFavorite: true,
  isFavorite: false
});

const emit = defineEmits<{
  'update:favorite': [isFavorite: boolean]
}>();

const setLevelChip = (difficulty: string | undefined) => {
  switch (difficulty?.toLowerCase()) {
    case 'fácil':
    case 'facil':
      return { color: 'bg-success', rating: 1 };
    case 'media':
    case 'medium':
      return { color: 'bg-warning', rating: 2 };
    case 'difícil':
    case 'dificil':
      return { color: 'bg-error', rating: 3 };
    default:
      return { color: 'bg-success', rating: 1 };
  }
};

const title = computed(() => {
  if (props.type === 'restaurante') return props.item.nombre || 'Nombre no disponible';
  return props.item.titulo || 'Título no disponible';
});

// Toggle favorite status
const toggleFavorite = async (item: Receta) => {
  if (item.esFavoritoUsuario) {
    const response = await removeFromFavorites(item.idReceta);
    if (response.success) emit('update:favorite', false);
  } else {
    const response = await addToFavorites(item.idReceta, 'RECETA');
    if (response.success) emit('update:favorite', true);
  }
};
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
      align-self: flex-start;
      padding: 10px 20px !important;
      min-width: 180px;
      height: 40px;
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
      right: 120px;
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
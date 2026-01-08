<template>
  <div class="details-page__hero-header d-flex">

    <!-- CHIP + TITLE + RATING --------------------------->
    <div class="details-page__hero-info">
      <slot name="chip" :item="item" :setLevelChip="setLevelChip">
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
      <!-------------------------------------------------->

    </div>
    <!---------------------------------------------------->

    <!-- HERO IMAGE -------------------------------------->
    <div class="details-page__hero-image">
      <v-img 
        cover
        height="500"
        :src="currentImageUrl" 
        :alt="item.titulo || 'Hero Image'"
        @error="handleImageError"
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
    <!---------------------------------------------------->    

  </div>
</template>
<script setup lang="ts">
const { isAuthenticated } = useAuthStore();
const { addToFavorites, removeFromFavorites } = useUserActions();

const props = withDefaults(defineProps<{
  item: any;
  type: 'receta' | 'restaurante' | 'post';
  imageUrl?: string;
  defaultImage?: string;
  showRating?: boolean;
  showFavorite?: boolean;
  isFavorite?: boolean;
}>(), {
  defaultImage: '/img/recetas/hero-image.png',
  showRating: true,
  showFavorite: true,
  isFavorite: false
});

const emit = defineEmits<{
  'update:favorite': [isFavorite: boolean]
}>();

// Handle image error
const currentImageUrl = ref(props.imageUrl || props.defaultImage);
const handleImageError = () => {
  currentImageUrl.value = props.defaultImage;
};

// Watch for imageUrl prop changes
watch(() => props.imageUrl, (newUrl) => {
  if (newUrl) {
    currentImageUrl.value = newUrl;
  }
});

// Set chip color and rating based on difficulty
const setLevelChip = (difficulty: string | undefined) => {
  switch (difficulty?.toLowerCase()) {
    case 'fácil':
      return { color: 'bg-success', rating: 1 };
    case 'media':
      return { color: 'bg-warning', rating: 2 };
    case 'difícil':
      return { color: 'bg-error', rating: 3 };
    default:
      return { color: 'bg-success', rating: 1 };
  }
};

// Get title based on type
const title = computed(() => {
  if (props.type === 'restaurante') return props.item.nombre || 'Nombre no disponible';
  return props.item.titulo || 'Título no disponible';
});

// Get item ID based on type
const id = computed(() => {
  if (props.type === 'restaurante') return props.item.idRestaurante;
  if (props.type === 'post') return props.item.idPost;
  return props.item.idReceta;
});

// Toggle favorite status
const toggleFavorite = async (item: Receta) => {
  const objectType = props.type.toUpperCase() as ObjectType;

  if (item.esFavoritoUsuario) {
    const response = await removeFromFavorites(id.value, objectType);
    if (response.success) emit('update:favorite', false);
  } else {
    const response = await addToFavorites(id.value, objectType);
    if (response.success) emit('update:favorite', true);
  }
};

</script>
<style lang="scss">
.details-page {
  &__hero-header {
    min-height: 480px;
    width: 100%;
    border-top: 3px solid #836A02;
    border-bottom: 3px solid #836A02;
  }

  &__hero-info {
    width: 50%;
    padding: 50px 50px 50px 100px;
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
      right: 100px;
      height: 80px !important;
      width: 80px !important;
      background-color: #ffffffe5;

      .v-icon {
        --v-icon-size-multiplier: 2 !important;
      }
    }
  }
}

@media (max-width: 1200px) {
  .details-page__hero-image{
    width: 35%;

    &__favourite {
      right: 20px;
    }
  }

  .details-page__hero-info {
    width: 65%;
    padding: 2rem 20px;
  }
}
</style>
<template>
  <v-card 
    :to="itemLink" 
    variant="text"
    class="card-item mx-2 pa-2" 
  >
    <div class="card-image">
      <v-img 
        cover 
        height="400" 
        :src="imageSource"
      />
      <v-chip
        v-if="chipType === 'difficulty'"
        class="card-image__chip"
        :class="setChipClass(difficulty)"
        variant="outlined"
      >
        <span>{{ difficulty || 'fácil' }}</span>
        <v-rating
          readonly
          color="white"
          length="3"
          :model-value="difficulty === 'fácil' ? 1 : difficulty === 'media' ? 2 : 3"
        ></v-rating>
      </v-chip>
      <v-chip 
        v-else
        class="card-image__chip bg-success text-white"
        variant="outlined"
      >
        <v-icon left>mdi-plus</v-icon>
        Información
      </v-chip>
      <v-btn
        v-if="isAuthenticated"
        :color="isFavorite ? 'error' : 'darkgray'"
        variant="outlined"
        icon="mdi-heart"
        class="card-image__favourite"
        @click.stop.prevent="toggleFavorite"
      />
    </div>
    <v-card-title class="px-0">
      {{ itemTitle }}
    </v-card-title>
    <v-card-text class="px-0">
      {{ truncatedDescription }}
    </v-card-text>
    <v-rating
      readonly
      half-increments
      color="primary"
      density="compact"
      :model-value="itemRating"
    ></v-rating>
  </v-card>
</template>

<script setup lang="ts">
const { isAuthenticated } = useAuthStore();
const { showError, showSuccess } = useNotification();
const { addToFavorites, removeFromFavorites } = useUserActions();

interface Props {
  item: any;
  imageFolder: string;
  linkPath: string;
  idField: string;
  titleField: string;
  descriptionField: string;
  imageField: string;
  chipType: 'difficulty' | 'info';
  favoriteType: 'RESTAURANTE' | 'RECETA' | 'POST';
  successMessage: string;
  removeMessage: string;
}

const props = defineProps<Props>();

const emit = defineEmits<{
  (e: 'update:item', value: any): void;
}>();

// Computed properties for item data
const itemId = computed(() => props.item[props.idField]);
const itemTitle = computed(() => props.item[props.titleField]);
const itemDescription = computed(() => props.item[props.descriptionField]);
const itemRating = computed(() => props.item.valoracion);
const isFavorite = computed(() => props.item.esFavoritoUsuario);
const difficulty = computed(() => props.item.dificultad);

// Compute image source
const imageSource = computed(() => {
  const imagePath = props.item[props.imageField];
  return `/img/${props.imageFolder}/${imagePath}`;
});

// Compute link to detail page
const itemLink = computed(() => `${props.linkPath}${itemId.value}`);

// Truncate description to 130 characters
const truncatedDescription = computed(() => {
  const maxLength = 130;
  return itemDescription.value.length > maxLength 
    ? itemDescription.value.slice(0, maxLength) + '...' 
    : itemDescription.value;
});

// Set chip class based on difficulty level
const setChipClass = (difficulty: string | undefined) => {
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

// Toggle favorite status
const toggleFavorite = async () => {
  if (!isAuthenticated) return;

  try {
    if (isFavorite.value) {
      await removeFromFavorites(itemId.value, props.favoriteType);
      emit('update:item', { ...props.item, esFavoritoUsuario: false });
      showSuccess(props.removeMessage);
    } else {
      await addToFavorites(itemId.value, props.favoriteType);
      emit('update:item', { ...props.item, esFavoritoUsuario: true });
      showSuccess(props.successMessage);
    }
  } catch (error) {
    showError('No se ha podido actualizar la lista de favoritos');
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

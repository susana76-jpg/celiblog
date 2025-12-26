<template>
  <section class="details-page restaurant-page">

    <!-- LOADER --------------------------------->
    <DetailsLoaderBlock v-if="loading" />
    <!------------------------------------------->

    <!-- CONTENT (shown when loaded) ------------>
    <div v-else>
      <DetailsHeroImage 
        v-if="restaurant" 
        type="restaurante"
        :item="restaurant" 
        :imageUrl="restaurant.imagenUrl"
      />

      <div 
        v-if="restaurant"
        class="details-page__main restaurant-description"
      >

        <!-- TAGS -------------------------------------->
        <v-chip-group column class="tags-list" >
          <v-chip 
            v-for="tag in tags" 
            :key="tag.idTag"
            size="large"
            class="bg-primary"
          >
            {{ tag.nombre }}
          </v-chip>
        </v-chip-group>
        <!---------------------------------------------->

        <!-- DESCRIPTION ------------------------------->
        <h2 class="mt-10 mb-4">Descripción del restaurante</h2>
        <p>{{ restaurant.descripcion }}</p>
        <!---------------------------------------------->

        <!-- CONTACT INFO ------------------------------>
        <h2 class="mt-12 mb-4">Datos de contacto del restaurante</h2>
        <div class="contact-section">
          <RestaurantesMapaDetails 
            class="contact-section__map"
            :restaurant="restaurant"
          />
          <div class="contact-section__info">
            <h2>Información de contacto</h2>
            <v-divider :thickness="2" class="mb-10"></v-divider>
            <div>
              <div 
                v-for="item in contactItems" 
                :key="item.label"
                class="contact-item"
              >
                <v-icon :icon="item.icon" class="contact-item__icon" />
                <div class="contact-item__content">
                  <span class="contact-item__label">{{ item.label }}</span>
                  <a 
                    v-if="item.link"
                    :href="item.link" 
                    class="contact-item__value contact-item__link"
                  >
                    {{ item.value }}
                  </a>
                  <span v-else class="contact-item__value">{{ item.value }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!---------------------------------------------->

        <!-- COMMENTS ---------------------------------->
        <CommentsMainContent 
          v-if="showComments"
          class="mt-16"
          :itemType="TYPE.RESTAURANT as ObjectType" 
          :itemId="id"
          :comentarios="comments" 
          @update:comentarios="getComments"
        />
        <!---------------------------------------------->

      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
const { getCommentsByObjectId } = useComments();
const { showError } = useNotification();
const { isAuthenticated } = useAuthStore();

const route = useRoute();
const id = parseInt(route.params.id as string);
const restaurant = ref<Restaurante | null>(null);
const tags = ref<RestauranteTag[]>([]);
const comments = ref<Comentario[]>([]);
const loading = ref(true);

// Computed contact items
const contactItems = computed(() => {
  if (!restaurant.value) return [];
  
  const items = [
    {
      icon: 'mdi-map-marker',
      label: 'Dirección',
      value: restaurant.value.direccion
    },
    {
      icon: 'mdi-phone',
      label: 'Teléfono',
      value: restaurant.value.telefono,
      link: `tel:${restaurant.value.telefono}`
    },
    {
      icon: 'mdi-email',
      label: 'Email',
      value: restaurant.value.email,
      link: `mailto:${restaurant.value.email}`
    }
  ];

  if (restaurant.value.urlWeb) {
    items.push({
      icon: 'mdi-web',
      label: 'Sitio web',
      value: restaurant.value.urlWeb,
      link: restaurant.value.urlWeb,
    });
  }

  return items;
});

// Get restaurant by ID from API
const getRestaurantById = async () => {
  loading.value = true;

  try {
    const data = await useApiFetch(API.RESTAURANTS.BY_ID, {
      params: { id }
    });
    
    restaurant.value = data as Restaurante; 
  } catch (error) {
    showError('Error fetching restaurant');
  } finally {
    loading.value = false;
  }
};

// Get restaurant tags by restaurant ID from API
const getTagsByRestaurantId = async () => {
  try {
    const data = await useApiFetch(API.RESTAURANTS.TAGS, {
      params: { idRestaurante: id }
    });
    
    tags.value = data as RestauranteTag[];
  } catch (error) {
    showError('Error fetching restaurant tags');
  }
};

// Get comments for the restaurant with the given ID
const getComments = async () => {
  comments.value = await getCommentsByObjectId(TYPE.RESTAURANT, id) ?? [];
};

// Determine if comments section should be hidden
const showComments = computed(() => comments.value.length > 0 || isAuthenticated.value); 

onMounted(() => {
  getRestaurantById();
  getTagsByRestaurantId();
  getComments();
});

</script>

<style lang="scss"> 
.contact-section {
  display: grid;
  grid-template-columns: 3fr 1fr;
  gap: 2rem;
  margin-top: 1.5rem;

  @media (max-width: 768px) {
    grid-template-columns: 1fr;
  }

  &__map {
    height: 100%;
    min-height: 300px;
  }

  &__info {
    background-color: #ececec;
    border-radius: 6px;
    padding: 2rem;
    padding-top: 1.4rem;
  }
}

.contact-item {
  display: flex;
  align-items: flex-start;
  gap: 0.8rem;
  margin-bottom: 1.8rem;

  &:last-child {
    margin-bottom: 0;
  }

  &__icon {
    color: rgb(var(--v-theme-primary));
    font-size: 24px;
    flex-shrink: 0;
  }

  &__content {
    display: flex;
    flex-direction: column;
    flex: 1;
  }

  &__label {
    font-size: 0.875rem;
    font-weight: 600;
    color: #6c757d;
    text-transform: uppercase;
    letter-spacing: 0.5px;
    line-height: 0.8rem;
  }

  &__value {
    font-size: 1rem;
    color: #212529;
    word-break: break-word;
  }

  &__link {
    color: rgb(var(--v-theme-primary));
    text-decoration: none;
    transition: color 0.2s;

    &:hover {
      color: rgb(var(--v-theme-primary-darken-1));
      text-decoration: underline;
    }
  }
}
</style>


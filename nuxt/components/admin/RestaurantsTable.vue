<template>
  <div class="admin-table restaurants-table">
    <div class="d-flex">
      <v-text-field
        clearable
        single-line
        hide-details
        v-model="search"
        class="mb-3 flex-grow-1 mr-2"
        density="compact"
        variant="outlined"
        label="Buscar restaurantes"
        prepend-inner-icon="mdi-magnify"
      ></v-text-field>
      <v-select
        chips
        multiple
        single-line
        hide-details
        width="350"
        max-width="350"
        class="status-filter"
        variant="outlined"
        density="compact"
        :items="Object.values(POST_STATUS)"
        v-model="statusFilter"
        @update:modelValue="getRestaurants"
      />
      <v-btn
        height="40"
        color="primary"
        variant="flat"
        prepend-icon="mdi-plus"
        @click="showDialog = true"
      >
        Agregar Restaurante
      </v-btn>
    </div>
    <v-data-table
      hide-default-footer
      density="compact"
      class="mb-10"
      :headers="headers"
      :items="restaurants"
      :items-length="restaurants.length"
      :loading="loading"
      :search="search"
      :items-per-page="-1"
      item-value="name"
    >
      <template v-slot:item.estado="{ item }">
        <v-select
          chips
          single-line
          hide-details
          variant="outlined"
          density="compact"
          :items="Object.values(POST_STATUS)"
          v-model="item.estado"
          @update:modelValue="($event) => updateStatus(item, $event)"
        ></v-select>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn 
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="editRestaurant(item)"
        >
          <v-icon>mdi-pencil-outline</v-icon>
        </v-btn>
        <v-btn 
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="handleDelete(item.nombre, item.idRestaurante)"
        >
          <v-icon>mdi-delete-outline</v-icon>
        </v-btn>
      </template>
    </v-data-table>
  </div>

  <!-- ADD DIALOG ------------------------->
  <AdminRestaurantForm 
    :show="showDialog" 
    :is-edit="isEdit"
    :restaurant="restaurant"
    @update:show="closeDialog"
    @get:restaurants="getRestaurants"
  />
  <!--------------------------------------->

</template>

<script setup lang="ts">
const showDialog = ref<boolean>(false);
const search = ref<string>('');
const loading = ref<boolean>(false);
const restaurant = ref<Restaurante | null>(null);
const isEdit = computed(() => restaurant.value !== null);
const statusFilter = ref<string[]>(Object.values(POST_STATUS));

// Use global notification composable
const { showSuccess, showError } = useNotification();


/*************************************/
/* RESTAURANTS DATA TABLE */
/*************************************/
// List of restaurants
const restaurants = ref<Restaurante[]>([]);

// Table headers
const headers = [
  { title: 'Nombre', key: 'nombre', align: 'start' as const },
  { title: 'Dirección', key: 'direccion', align: 'start' as const },
  { title: 'Ciudad', key: 'ubicacion', align: 'start' as const },
  { title: 'Teléfono', key: 'telefono', align: 'start' as const },
  { title: 'Email', key: 'email', align: 'start' as const },
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false, minWidth: 200 },
  { title: '', key: 'actions', align: 'end' as const, sortable: false, minWidth: 100 },
];


/*************************************/
/* CONFIRM DIALOG USAGE */
/*************************************/
// Import the global confirm dialog composable
const { showConfirmDialog } = useConfirmDialog();

// Example function to handle delete operations
const handleDelete = (itemName: string, itemId: number) => {
  const text = `Si eliminas a ${itemName} de la base de datos, desaparecerá del sistema. ¿Deseas continuar?`;
  showConfirmDialog(text, () => deleteRestaurant(itemId));
}

// Close dialog and reset restaurant
const closeDialog = () => {
  showDialog.value = false;
  restaurant.value = null;
};


/*************************************/
/* API CALLS */
/*************************************/
// Delete restaurant
const deleteRestaurant = async (id: number) => {
  try {
    const response = await useApiFetch(API.RESTAURANTS.DELETE, {
      method: 'DELETE',
      params: { id }
    });

    if (response) showSuccess(`Restaurante eliminado correctamente`);
    getRestaurants();
  } catch (error: any) {
    showError('Error al eliminar el restaurante');
  }
}

// Fetch restaurants from API on component mount
const getRestaurants = async () => {
  if (showDialog.value) showDialog.value = false;

  loading.value = true;
  try {
    const response = await useApiFetch(API.STATUS.LIST, {
      method: 'GET',
      params: {
        objeto: TYPE.RESTAURANT,
        estado: statusFilter.value.length === 3 ? ['TODOS'] : statusFilter.value
      }
    });
    restaurants.value = response as Restaurante[];
  } catch (error: any) {
    showError(`Error al cargar los restaurantes: ${error.message || error}`);
  } finally {
    loading.value = false;
    restaurant.value = null;
  }
};

// Add new restaurant
const editRestaurant = async (restaurantData: any) => {
  showDialog.value = true;
  restaurant.value = restaurantData;
};

// Update restaurant status
const updateStatus = async (restaurant: Restaurante, newStatus: string) => {
  try {
    const response = await useApiFetch(API.STATUS.UPDATE, {
      method: 'PUT',
      params: {
        idObjeto: restaurant.idRestaurante,
        objeto: TYPE.RESTAURANT,
        estado: newStatus
      }
    });

    if (response) {
      showSuccess(`Estado del restaurante actualizado a ${newStatus}`);
      getRestaurants();
    }
  } catch (error: any) {
    showError('Error al actualizar el estado del restaurante');
  }
};

// Load data on mount
onMounted(() => {
  getRestaurants();
})
</script>

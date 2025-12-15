<template>
  <div class="restaurants-table">
    <v-text-field
      single-line
      hide-details
      v-model="search"
      class="mb-3"
      density="compact"
      variant="outlined"
      label="Buscar restaurantes"
      prepend-inner-icon="mdi-magnify"
    ></v-text-field>
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
      <!-- <template v-slot:item.rol="{ item }">
        <v-select
          chips
          single-line
          hide-details
          variant="outlined"
          density="compact"
          item-title="nombre"
          item-value="idRol"
          :items="USER_ROLES"
          v-model="item.rol"
          @update:modelValue="($event) => updateUserRole(item, $event)"
        ></v-select>
      </template> -->
      <template v-slot:item.actions="{ item }">
        <v-btn 
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="emit('edit-restaurant', item)"
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
</template>

<script setup lang="ts">
const emit = defineEmits<{
  (e: 'edit-restaurant', restaurantData: Restaurante): void;
}>();

const search = ref<string>('');
const loading = ref<boolean>(false);

// Use global notification composable
const { showSuccess, showError } = useNotification();
const store = useAdminStore();

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
  { title: 'Estado', key: 'estado', align: 'start' as const, sortable: false },
  { title: '', key: 'actions', align: 'end' as const, sortable: false },
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
  loading.value = true;
  try {
    const response = await useApiFetch(API.RESTAURANTS.BASE);
    restaurants.value = response as Restaurante[];
    store.keyfacts.totalRestaurants = restaurants.value.length;
  } catch (error: any) {
    console.error('Error al obtener restaurantes:', error);
  } finally {
    loading.value = false;
  }
};

// Add new restaurant
const addRestaurant = async (restaurantData: any) => {
  // try {
  //   const response = await useApiFetch(API.USERS.ADD, {
  //     method: 'POST',
  //     body: userData
  //   });
    
  //   if (response) {
  //     showSuccess('Usuario agregado correctamente');
  //     await getUsers();
  //   }
  // } catch (error: any) {
  //   showError(`Error al agregar el usuario: ${error.message || error}`);
  // }
};

// Expose methods to parent component
defineExpose({
  addRestaurant
});

// Load data on mount
onMounted(() => {
  getRestaurants();
})
</script>

<style lang="scss">
.restaurants-table {
  .v-table__wrapper {
    border-radius: 4px 4px 0 0;
  }

  // Table header style
  thead {
    color: #FFF;
    background-color: #836A02;
  }

  // Select role chips style
  span.v-chip.v-chip--variant-tonal {
    background-color: #836A02;
    color: #FFF;
  }
}
</style>

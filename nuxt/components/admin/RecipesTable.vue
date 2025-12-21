<template>
  <div class="admin-table recipes-table">
    <div class="d-flex">
      <v-text-field
        clearable
        single-line
        hide-details
        class="mb-3 flex-grow-1 mr-2"
        density="compact"
        variant="outlined"
        label="Buscar recetas"
        prepend-inner-icon="mdi-magnify"
        v-model="search"
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
        @update:modelValue="getRecipes"
      />
      <v-btn
        height="40"
        color="primary"
        variant="flat"
        prepend-icon="mdi-plus"
        @click="showDialog = true"
      >
        Agregar Receta
      </v-btn>
    </div>
    <v-data-table
      hide-default-footer
      density="compact"
      class="mb-10"
      :headers="headers"
      :items="recipes"
      :items-length="recipes.length"
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
          @click="editRecipe(item)"
        >
          <v-icon>mdi-pencil-outline</v-icon>
        </v-btn>
        <v-btn 
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="handleDelete(item.titulo, item.idReceta)"
        >
          <v-icon>mdi-delete-outline</v-icon>
        </v-btn>
      </template>
    </v-data-table>
  </div>

  <!-- ADD DIALOG ------------------------->
  <AdminRecipeForm 
    :show="showDialog" 
    :is-edit="isEdit"
    :recipe="recipe"
    @update:show="closeDialog"
    @get:recipes="getRecipes"
  />
  <!--------------------------------------->

</template>

<script setup lang="ts">
const showDialog = ref<boolean>(false);
const search = ref<string>('');
const loading = ref<boolean>(false);
const recipe = ref<Receta | null>(null);
const isEdit = computed(() => recipe.value !== null);
const statusFilter = ref<string[]>(Object.values(POST_STATUS));

// Use global notification composable
const { showSuccess, showError } = useNotification();


/*************************************/
/* RECIPES DATA TABLE */
/*************************************/
// List of recipes
const recipes = ref<Receta[]>([]);

// Table headers
const headers = [
  { title: 'Título', key: 'titulo', align: 'start' as const },
  { title: 'Fecha creación', key: 'fechaCreacion', align: 'start' as const },
  { title: 'Dificultad', key: 'dificultad', align: 'start' as const },
  { title: 'Tiempo', key: 'tiempoPreparacion', align: 'start' as const },
  { title: 'Comensales', key: 'comensales', align: 'start' as const },
  { title: 'Calorías', key: 'valorEnergetico', align: 'start' as const },
  { title: 'Categoría', key: 'tipoComida', align: 'start' as const },
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
  showConfirmDialog(text, () => deleteRecipe(itemId));
}

// Close dialog and reset recipe
const closeDialog = () => {
  showDialog.value = false;
  recipe.value = null;
};


/*************************************/
/* API CALLS */
/*************************************/
// Delete recipe
const deleteRecipe = async (id: number) => {
  try {
    const response = await useApiFetch(API.RECIPES.DELETE, {
      method: 'DELETE',
      params: { id }
    });

    if (response) showSuccess(`Receta eliminada correctamente`);
    getRecipes();
  } catch (error: any) {
    showError('Error al eliminar la receta');
  }
}

// Fetch recipes from API on component mount
const getRecipes = async () => {
  if (showDialog.value) showDialog.value = false;

  loading.value = true;
  try {
    const response = await useApiFetch(API.STATUS.LIST, {
      method: 'GET',
      params: {
        objeto: TYPE.RECIPE,
        estado: statusFilter.value.length === 3 ? ['TODOS'] : statusFilter.value
      }
    });
    recipes.value = response as Receta[];
  } catch (error: any) {
    showError(`Error al cargar las recetas: ${error.message || error}`);
  } finally {
    loading.value = false;
    recipe.value = null;
  }
};

// Add new restaurant
const editRecipe = async (recipeData: any) => {
  showDialog.value = true;
  recipe.value = recipeData;
};

// Update recipe status
const updateStatus = async (recipe: Receta, newStatus: string) => {
  try {
    const response = await useApiFetch(API.STATUS.UPDATE, {
      method: 'PUT',
      params: {
        idObjeto: recipe.idReceta,
        objeto: TYPE.RECIPE,
        estado: newStatus
      }
    });

    if (response) {
      showSuccess(`Estado de la receta actualizado a ${newStatus}`);
      getRecipes();
    }
  } catch (error: any) {
    showError('Error al actualizar el estado de la receta');
  }
};

// Load data on mount
onMounted(() => {
  getRecipes();
})
</script>


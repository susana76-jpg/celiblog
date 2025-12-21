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
        label="Buscar consejos"
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
        @update:modelValue="getPosts"
      />
      <v-btn
        height="40"
        color="primary"
        variant="flat"
        prepend-icon="mdi-plus"
        @click="showDialog = true"
      >
        Agregar Consejo
      </v-btn>
    </div>
    <v-data-table
      hide-default-footer
      density="compact"
      class="mb-10"
      :headers="headers"
      :items="posts"
      :items-length="posts.length"
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
          @click="editPost(item)"
        >
          <v-icon>mdi-pencil-outline</v-icon>
        </v-btn>
        <v-btn 
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="handleDelete(item.titulo, item.idPost)"
        >
          <v-icon>mdi-delete-outline</v-icon>
        </v-btn>
      </template>
    </v-data-table>
  </div>

  <!-- ADD DIALOG ------------------------->
  <AdminTipForm 
    :show="showDialog" 
    :is-edit="isEdit"
    :post="post"
    @update:show="closeDialog"
    @get:posts="getPosts"
  />
  <!--------------------------------------->

</template>

<script setup lang="ts">
const showDialog = ref<boolean>(false);
const search = ref<string>('');
const loading = ref<boolean>(false);
const post = ref<Post | null>(null);
const isEdit = computed(() => post.value !== null);
const statusFilter = ref<string[]>(Object.values(POST_STATUS));

// Use global notification composable
const { showSuccess, showError } = useNotification();


/*************************************/
/* POSTS DATA TABLE */
/*************************************/
// List of posts
const posts = ref<Post[]>([]);

// Table headers
const headers = [
  { title: 'Título', key: 'titulo', align: 'start' as const },
  { title: 'Fecha publicación', key: 'fechaPublicacion', align: 'start' as const },
  { title: 'Autor', key: 'usuario.nombre', align: 'start' as const },
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
  post.value = null;
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

    if (response) showSuccess(`Post eliminado correctamente`);
    getPosts();
  } catch (error: any) {
    showError('Error al eliminar la receta');
  }
}

// Fetch recipes from API on component mount
const getPosts = async () => {
  if (showDialog.value) showDialog.value = false;

  loading.value = true;
  try {
    const response = await useApiFetch(API.STATUS.LIST, {
      method: 'GET',
      params: {
        objeto: TYPE.POST,
        estado: statusFilter.value.length === 3 ? ['TODOS'] : statusFilter.value
      }
    });
    posts.value = (response as Post[]).map((postData: Post) => ({
      ...postData,
      fechaPublicacion: formatDateTime(postData.fechaPublicacion)
    }));
  } catch (error: any) {
    showError(`Error al cargar las recetas: ${error.message || error}`);
  } finally {
    loading.value = false;
    post.value = null;
  }
};

// Add new restaurant
const editPost = async (postData: any) => {
  showDialog.value = true;
  post.value = postData;
};

// Update post status
const updateStatus = async (post: Post, newStatus: string) => {
  try {
    const response = await useApiFetch(API.STATUS.UPDATE, {
      method: 'PUT',
      params: {
        idObjeto: post.idPost,
        objeto: TYPE.POST,
        estado: newStatus
      }
    });

    if (response) {
      showSuccess(`Estado del post actualizado a ${newStatus}`);
      getPosts();
    }
  } catch (error: any) {
    showError('Error al actualizar el estado del post');
  }
};

// Load data on mount
onMounted(() => {
  getPosts();
})
</script>

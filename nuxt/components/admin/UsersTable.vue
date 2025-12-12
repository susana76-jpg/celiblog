<template>
  <div class="users-table">
    <v-text-field
      single-line
      hide-details
      v-model="search"
      class="mb-3"
      density="compact"
      variant="outlined"
      label="Buscar usuarios"
      prepend-inner-icon="mdi-magnify"
    ></v-text-field>
    <v-data-table
      hide-default-footer
      density="compact"
      class="mb-10"
      :headers="headers"
      :items="users"
      :items-length="users.length"
      :loading="loading"
      :search="search"
      :items-per-page="-1"
      item-value="name"
    >
      <template v-slot:item.rol="{ item }">
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
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn 
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="handleDelete(item.nombre, item.idUsuario)"
        >
          <v-icon>mdi-delete-outline</v-icon>
        </v-btn>
      </template>
    </v-data-table>
  </div>
</template>

<script setup lang="ts">
const search = ref<string>('');
const loading = ref<boolean>(false);

// Use global notification composable
const { showSuccess, showError } = useNotification();

/*************************************/
/* USERS DATA TABLE */
/*************************************/
// List of users
const users = ref<AdminUsuario[]>([]);

// Table headers
const headers = [
  { title: 'Nombre', key: 'nombre', align: 'start' as const },
  { title: 'Correo electrónico', key: 'email', align: 'start' as const },
  { title: 'Fecha Alta', key: 'fechaAlta', align: 'start' as const },
  { title: 'Rol', key: 'rol', align: 'start' as const, sortable: false },
  { title: '', key: 'actions', align: 'end' as const, sortable: false },
];

/*************************************/
/* CONFIRM DIALOG USAGE */
/*************************************/
// Import the global confirm dialog composable
const { showConfirmDialog } = useConfirmDialog();

// Example function to handle delete operations
const handleDelete = (itemName: string, itemId: number) => {
  const text = `Si eliminas a ${itemName} de la base de datos, no podrá acceder al sistema. ¿Deseas continuar?`;
  showConfirmDialog(text, () => deleteUser(itemId));
}


/*************************************/
/* API CALLS */
/*************************************/
// Delete user
const deleteUser = async (userId: number) => {
  try {
    const response = await useApiFetch(API.USERS.DELETE, {
      method: 'DELETE',
      params: {
        idUsuario: userId,      }
    });

    if (response) showSuccess(`Usuario eliminado correctamente`);
    getUsers();
  } catch (error: any) {
    showError('Error al eliminar el usuario');
  }
}

// Update user role
const updateUserRole = async (user: AdminUsuario, newRoleId: number) => {
  try {
    const response = await useApiFetch(API.USERS.UPDATE_ROLE, {
      method: 'PUT',
      params: {
        idUsuario: user.idUsuario,
        idNuevoRol: newRoleId
      }
    });

    if (response) {
      const roleName = USER_ROLES.find(r => r.idRol === newRoleId)?.nombre || 'nuevo rol';
      showSuccess(`Rol actualizado correctamente a "${roleName}"`);
    }
  } catch (error: any) {
    showError('Error al actualizar el rol del usuario');
  }
}

// Fetch users from API on component mount
const getUsers = async () => {
  loading.value = true;
  try {
    const response = await useApiFetch(API.USERS.BASE);
    users.value = response as AdminUsuario[];
  } catch (error: any) {
    console.error('Login error:', error);
  } finally {
    loading.value = false;
  }
};

// Add new user
const addUser = async (userData: any) => {
  try {
    const response = await useApiFetch(API.USERS.ADD, {
      method: 'POST',
      body: userData
    });
    
    if (response) {
      showSuccess('Usuario agregado correctamente');
      await getUsers();
    }
  } catch (error: any) {
    showError(`Error al agregar el usuario: ${error.message || error}`);
  }
};

// Expose methods to parent component
defineExpose({
  addUser
});

// Load data on mount
onMounted(async () => {
  getUsers();
})
</script>

<style lang="scss">
.users-table {
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

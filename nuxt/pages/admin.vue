<template>
  <div class="admin-page">

    <!-- KEYFACTS --------------------------->
    <AdminKeyfactsCard />
    <!--------------------------------------->

    <!-- TABS + OPEN DIALOG BUTTON ---------->
    <div class="tabs-header">
      <div class="tabs-navigation">
        <v-btn
          v-for="tab in tabs"
          :key="tab.value"
          variant="flat"
          :class="['tab-button', { 'tab-active': activeTab === tab.value }]"
          @click="activeTab = tab.value"
        >
          {{ tab.label }}
        </v-btn>
      </div>

      <v-btn
        color="primary"
        variant="flat"
        prepend-icon="mdi-plus"
        @click="showDialog = true"
      >
        {{ textOpenDialog }}
      </v-btn>
    </div>
    <!--------------------------------------->

    <!-- TAB CONTENT ------------------------>
    <component 
      :ref="setTabRef"
      :is="currentTabComponent" 
      @edit-restaurant="editRestaurant"
    />
    <!------------------------------------->

    <!-- ADD DIALOG ------------------------->
    <v-dialog
      v-model="showDialog"
      max-width="65%"
      opacity="60%"
      class="comment-form"
    >
      <v-card class="px-10 py-6">
        <v-card-title class="text-h5 text-primary pa-0 pb-3">
          {{ textOpenDialog }}
        </v-card-title>
        <v-divider></v-divider>

        <v-card-text>
          <AdminUserForm 
            v-if="activeTab === 'users'"
            id="users-form"
            v-model="userForm"
            @submit="saveItem"
            @update:model-value="(value) => userForm = value"  
          />
          <AdminRestaurantForm 
            v-if="activeTab === 'restaurants'" 
            id="restaurants-form"
            v-model="restaurantForm" 
            @submit="saveItem"
            @update:model-value="(value) => restaurantForm = value"
          />
          <!-- <AdminRecipeForm 
              v-if="activeTab === 'recipes'" 
              v-model="recipeForm" 
            />
            <AdminTipForm 
              v-if="activeTab === 'tips'" 
              v-model="tipForm" 
            /> -->
        </v-card-text>

        <v-divider></v-divider>
        <v-card-actions class="pt-4">
          <v-spacer />
          <v-btn color="grey" variant="text" @click="closeDialog">
            Cancelar
          </v-btn>
          <v-btn 
            color="primary" 
            variant="flat" 
            type="submit"
            :form="`${activeTab}-form`"
          >
            Guardar
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </div>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: 'admin'
})

/**************************************/
/* TABS */
/**************************************/
const activeTab = ref('users');
const usersTableRef = ref<{ addUser: (userData: any) => Promise<void> } | null>(null);
const tabs = [
  { label: 'Usuarios', value: 'users' },
  { label: 'Restaurantes', value: 'restaurants' },
  { label: 'Recetas', value: 'recipes' },
  { label: 'Consejos', value: 'tips' },
  { label: 'Comentarios', value: 'comments' }
]

// Function to handle tab component ref
const setTabRef = (el: any) => {
  if (activeTab.value === 'users' && el) {
    usersTableRef.value = el;
  }
}

// Tab components mapping
const tabComponents: Record<string, any> = {
  users: resolveComponent('AdminUsersTable'),
  restaurants: resolveComponent('AdminRestaurantsTable'),
  recipes: resolveComponent('AdminRecipesTable'),
  tips: resolveComponent('AdminTipsTable'),
  comments: resolveComponent('AdminCommentsTable')
}

// Computed property for dynamic component
const currentTabComponent = computed(() => {
  return tabComponents[activeTab.value] || tabComponents.users
});


/**************************************/
/* OPEN DIALOG BUTTON */
/**************************************/
const showDialog = ref(false);

// Tab dialog text mapping
const tabDialogText: Record<string, string> = {
  users: 'Agregar Usuario',
  restaurants: 'Agregar Restaurante',
  recipes: 'Agregar Receta',
  tips: 'Agregar Consejo',
  comments: 'Agregar Comentario'
}

// Computed property for dynamic dialog text
const textOpenDialog = computed(() => {
  return tabDialogText[activeTab.value] || 'Agregar'
})


/**************************************/
/* DYNAMIC FORMS */
/**************************************/
// User Form
const userForm = ref({
  nombre: '',
  email: '',
  idRol: 3,
  password: ''
});

// Restaurant Form
const restaurantForm = ref({
  titulo: '',
  subtitulo: '',
  descripcion: '',
  direccion: '',
  imagenUrl: '',
  nombre: '',
  ubicacion: '',
  codigoPostal: 0,
  urlWeb: '',
  telefono: '',
  email: '',
  valoracion: 0,
  tipoRestaurante: ''
});

// Reset forms
const resetForms = () => {
  userForm.value = {
    nombre: '',
    email: '',
    idRol: 3,
    password: ''
  };

  restaurantForm.value = {
    titulo: '',
    subtitulo: '',
    descripcion: '',
    direccion: '',
    imagenUrl: '',
    nombre: '',
    ubicacion: '',
    codigoPostal: 0,
    urlWeb: '',
    telefono: '',
    email: '',
    valoracion: 0,
    tipoRestaurante: ''
  };
};

// Close dialog and reset forms
const closeDialog = () => {
  showDialog.value = false;
  nextTick(() => {
    resetForms();
  });
};

const saveItem = async (isValid: boolean) => {
  if (!isValid) return;

  // Handle saving based on active tab
  switch (activeTab.value) {
    case 'users':
      addNewUser(userForm.value);
      break;
    // case 'restaurants':
    //   console.log('Saving restaurant:', restaurantForm.value);
    //   // Add your API call here
    //   break;
    // case 'recipes':
    //   console.log('Saving recipe:', recipeForm.value);
    //   // Add your API call here
    //   break;
    // case 'tips':
    //   console.log('Saving tip:', tipForm.value);
    //   // Add your API call here
    //   break;
  }
  
  closeDialog();
};

const addNewUser = async (userData: any) => {
  if (usersTableRef.value) {
    await usersTableRef.value.addUser(userData);
  } else {
    console.error('UsersTable ref not available');
  }
};  

const editRestaurant = (restaurantData: Restaurante) => {
  showDialog.value = true;
  restaurantForm.value = { ...restaurantForm.value, ...restaurantData };
  console.log('Editing restaurant:', restaurantForm.value)
};

</script>

<style lang="scss" scoped>
.admin-page {
  background-color: #ffffff;
  min-height: 100vh;
  padding: 60px 120px;
  font-family: 'Poppins', sans-serif;
}

// Tabs
.tabs-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 60px;
  margin-bottom: 20px;

  .tabs-navigation {
    display: flex;
    gap: 12px;
  }

  .tab-button {
    min-width: 184px;
    height: 40px;
    padding: 0 24px;
    background-color: rgba(160, 160, 160, 0.52);
    color: #616161;
    letter-spacing: -1px;
    transition: all 0.3s ease;

    &:hover {
      background-color: rgba(131, 106, 2, 0.2);
    }

    &.tab-active {
      background-color: #616161;
      color: #ffffff;

      &:hover {
        background-color: #4a4a4a;
      }
    }
  }
}

// Tab Content
.tab-content {
  padding: 40px 100px;
}

// Responsive Design
@media (max-width: 1600px) {
  .keyfacts-container {
    flex-wrap: wrap;

    .keyfact-card {
      flex-basis: calc(33.333% - 14px);
    }
  }
}

@media (max-width: 1200px) {
  .admin-header,
  .keyfacts-container,
  .tabs-header,
  .tab-content {
    padding-left: 40px;
    padding-right: 40px;
  }

  .keyfacts-container {
    .keyfact-card {
      flex-basis: calc(50% - 10px);
    }
  }

  .keyfact-number {
    font-size: 48px;
  }

  .keyfact-label {
    font-size: 18px;
  }
}

@media (max-width: 768px) {
  .admin-header,
  .keyfacts-container,
  .tabs-header,
  .tab-content {
    padding-left: 20px;
    padding-right: 20px;
  }

  .tabs-header {
    flex-direction: column;
    gap: 20px;
    align-items: stretch;
  }

  .tabs-navigation {
    flex-wrap: wrap;
  }

  .add-user-button {
    width: 100%;
  }

  .keyfacts-container {
    flex-direction: column;

    .keyfact-card {
      flex-basis: 100%;
    }
  }
}
</style>
<template>
  <div class="admin-table">
    <div class="d-flex">
      <v-text-field
        clearable
        single-line
        hide-details
        v-model="search"
        class="mb-3 flex-grow-1 mr-2"
        density="compact"
        variant="outlined"
        :label="searchLabel"
        prepend-inner-icon="mdi-magnify"
      ></v-text-field>
      
      <!-- Status Filter (if enabled) -->
      <v-select
        v-if="showStatusFilter"
        chips
        multiple
        single-line
        hide-details
        width="350"
        max-width="350"
        color="lightgray"
        class="status-filter"
        :class="{ 'mr-2': showAddButton, 'mr-0': !showAddButton }"
        variant="outlined"
        density="compact"
        :items="Object.values(POST_STATUS)"
        v-model="statusFilter"
        @update:modelValue="loadData"
      />
      
      <!-- Add Button (if enabled) -->
      <v-btn
        v-if="showAddButton"
        height="40"
        color="primary"
        variant="flat"
        prepend-icon="mdi-plus"
        @click="showDialog = true"
      >
        {{ addButtonLabel }}
      </v-btn>
    </div>

    <v-data-table
      hide-default-footer
      density="compact"
      class="mb-10"
      :headers="headers"
      :items="items"
      :items-length="items.length"
      :loading="loading"
      :search="search"
      :items-per-page="-1"
      item-value="name"
    >
      <!-- Custom Status/Role Column -->
      <template v-slot:[`item.${selectorColumn}`]="{ item }" v-if="selectorColumn">
        <v-select
          chips
          single-line
          hide-details
          variant="outlined"
          density="compact"
          :item-title="selectorConfig?.itemTitle"
          :item-value="selectorConfig?.itemValue"
          :items="selectorConfig?.items"
          :model-value="getSelectorValue(item)"
          @update:modelValue="($event) => handleSelectorUpdate(item, $event)"
        ></v-select>
      </template>

      <!-- Actions Column -->
      <template v-slot:item.actions="{ item }">
        <v-btn 
          v-if="showEditButton"
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="handleEdit(item)"
        >
          <v-icon>mdi-pencil-outline</v-icon>
        </v-btn>
        <v-btn 
          icon 
          width="32"
          variant="text"
          color="lightgray"
          @click="handleDelete(item)"
        >
          <v-icon>mdi-delete-outline</v-icon>
        </v-btn>
      </template>
    </v-data-table>
  </div>

  <!-- Form Dialog -->
  <component
    v-if="formComponent && showAddButton"
    :is="formComponent"
    :show="showDialog"
    :is-edit="isEdit"
    v-bind="formProps"
    @update:show="closeDialog"
    @[getUpdateEvent]="loadData"
  />
</template>

<script setup lang="ts">
import type { Component } from 'vue';

// Props interface
interface SelectorConfig {
  items: any[];
  itemTitle?: string;
  itemValue?: string;
  getValueFn: (item: any) => any;
}

interface Props {
  // Table configuration
  headers: any[];
  searchLabel: string;
  
  // Data fetching
  fetchEndpoint: string;
  fetchMethod?: string;
  fetchParams?: (statusFilter: string[]) => any;
  dataTransformer?: (data: any) => any[];
  
  // Delete configuration
  deleteEndpoint: string;
  deleteParams: (item: any) => any;
  deleteSuccessMessage?: string;
  
  // Status/Role selector configuration
  selectorColumn?: string;
  selectorConfig?: SelectorConfig;
  selectorUpdateEndpoint?: string;
  selectorUpdateParams?: (item: any, newValue: any) => any;
  
  // Form configuration
  formComponent?: Component | null;
  formPropName?: string;
  formUpdateEvent?: string;
  addButtonLabel?: string;
  showAddButton?: boolean;
  showEditButton?: boolean;
  
  // Status filter
  showStatusFilter?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  fetchMethod: 'GET',
  deleteSuccessMessage: 'Elemento eliminado correctamente',
  addButtonLabel: 'Agregar',
  showAddButton: true,
  showEditButton: true,
  showStatusFilter: false,
});

// State
const showDialog = ref<boolean>(false);
const search = ref<string>('');
const loading = ref<boolean>(false);
const items = ref<any[]>([]);
const editItem = ref<any>(null);
const isEdit = computed(() => editItem.value !== null);
const statusFilter = ref<string[]>(Object.values(POST_STATUS));

// Composables
const { showSuccess, showError } = useNotification();
const { showConfirmDialog } = useConfirmDialog();

// Computed form props
const formProps = computed(() => {
  if (!props.formPropName || !editItem.value) return {};
  return { [props.formPropName]: editItem.value };
});

// Get update event name - use provided event or generate from search label
const getUpdateEvent = computed(() => {
  if (props.formUpdateEvent) return props.formUpdateEvent;
  const baseWord = props.searchLabel.toLowerCase().replace('buscar ', '');
  return `get:${baseWord}`;
});

// Get selector value from item
const getSelectorValue = (item: any) => {
  if (!props.selectorConfig) return null;
  return props.selectorConfig.getValueFn(item);
};

// Load data from API
const loadData = async () => {
  if (showDialog.value) showDialog.value = false;
  
  loading.value = true;
  try {
    const params = props.fetchParams 
      ? props.fetchParams(statusFilter.value)
      : undefined;
    
    const response = await useApiFetch(props.fetchEndpoint, {
      method: props.fetchMethod,
      params
    });
    
    items.value = props.dataTransformer 
      ? props.dataTransformer(response)
      : response as any[];
  } catch (error: any) {
    showError(`Error al cargar los datos: ${error.message || error}`);
  } finally {
    loading.value = false;
    editItem.value = null;
  }
};

// Handle edit action
const handleEdit = (item: any) => {
  editItem.value = item;
  showDialog.value = true;
};

// Handle delete action
const handleDelete = (item: any) => {
  const itemName = item.titulo || item.nombre || 'este elemento';
  const text = `Si eliminas a ${itemName} de la base de datos, desaparecerá del sistema. ¿Deseas continuar?`;
  showConfirmDialog(text, () => deleteItem(item));
};

// Delete item
const deleteItem = async (item: any) => {
  try {
    const params = props.deleteParams(item);
    const response = await useApiFetch(props.deleteEndpoint, {
      method: 'DELETE',
      params
    });

    if (response) showSuccess('Elemento eliminado correctamente');
    loadData();
  } catch (error: any) {
    showError('Error al eliminar el elemento');
  }
};

// Handle selector update (status/role change)
const handleSelectorUpdate = async (item: any, newValue: any) => {
  if (!props.selectorUpdateEndpoint || !props.selectorUpdateParams) return;
  
  try {
    const params = props.selectorUpdateParams(item, newValue);
    const response = await useApiFetch(props.selectorUpdateEndpoint, {
      method: 'PUT',
      params
    });

    if (response) {
      showSuccess(`Elemento actualizado correctamente a ${newValue}`);
    }
    loadData();
  } catch (error: any) {
    showError('Error al actualizar');
  }
};

// Close dialog
const closeDialog = () => {
  showDialog.value = false;
  editItem.value = null;
};

// Load data on mount
onMounted(() => {
  loadData();
});
</script>

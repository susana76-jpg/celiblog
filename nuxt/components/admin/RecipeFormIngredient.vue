<template>
  <v-col cols="12" class="d-flex align-start">
    <v-text-field
      v-bind="commonFieldProps"
      hide-details
      type="number"
      class="mr-2 flex-grow-0"
      label="Cantidad *"
      v-model="ingredientData.cantidad"
    />
    <v-text-field
      v-bind="commonFieldProps"
      clearable
      hide-details="auto"
      :label="`Ingrediente ${index + 1} *`"
      v-model="ingredientData.nombre"
    />
    <v-text-field
      v-bind="commonFieldProps"
      clearable
      hide-details="auto"
      class="ml-2 flex-grow-0"
      label="Unidad *"
      v-model="ingredientData.unidad"
    />
    <v-tooltip location="top" text="Eliminar paso">
      <template v-slot:activator="{ props }">
        <v-btn
          v-bind="props"
          variant="text"
          color="primary"
          icon="mdi-delete-outline"
          height="48"
          width="48"
          @click="deleteIngredient"
        />
      </template>
    </v-tooltip>
  </v-col>
</template>
<script setup lang="ts">
import { id } from 'vuetify/locale';

const { showError } = useNotification();

const props = defineProps<{
  isEdit: boolean;
  idReceta: number | null;
  ingredient: { 
    idIngrediente?: number;
    nombre: string; 
    cantidad: number; 
    unidad: string 
  };
  index: number;
}>();

const emit = defineEmits([
  'delete:ingredient',
  'update:ingredient'
]);

const commonFieldProps = {
  required: true,
  color: 'primary',
  baseColor: 'primary',
  variant: 'outlined',
  density: 'comfortable',
} as const;

const ingredientData = computed({
  get: () => props.ingredient,
  set: (value) => emit('update:ingredient', value),
});

// Delete ingredient handler
const deleteIngredient = async () => {
  if (props.isEdit) await deleteIngredientFromDB();
  else emit('delete:ingredient', props.index);
};

// Function to delete ingredient from database (stub implementation)
const deleteIngredientFromDB = async () => {
  try {
    const response = await useApiFetch(API.RECIPES.DELETE_INGREDIENT, {
      method: 'DELETE',
      params: {
        idReceta: props.idReceta,
        idIngrediente: props.ingredient.idIngrediente
      }
    });
    emit('delete:ingredient', props.index);
  } catch (error: any) {
    showError(`Error al borrar ingrediente ${props.ingredient.nombre}: ${error.message || error}`);
  }
};
</script>
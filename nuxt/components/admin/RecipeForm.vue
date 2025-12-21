<template>
  <v-dialog
    v-model="showDialog"
    max-width="65%"
    opacity="60%"
    class="recipe-form"
  >
    <v-card class="px-10 py-6">
      <v-card-title class="text-h5 text-primary pa-0 pb-3">
        {{ isEdit ? 'Editar' : 'Agregar' }} Receta
      </v-card-title>
      <v-divider></v-divider>

      <v-card-text>
        <v-form 
          id="recipeForm"
          ref="formRef"
          validate-on="submit"
        >
          <v-row class="pt-5">
            <v-col
              v-for="(field, index) in textFields"
              :key="index"
              :cols="field.col"
              class="py-0"
            >
              <v-text-field
                active
                required
                hide-details="auto"
                density="comfortable"
                variant="outlined"
                class="mb-5 mt-3"
                color="primary"
                base-color="primary"
                :type="field.type"
                :label="field.label"
                :rules="field.rules"
                v-model="field.model.value"
              ></v-text-field>
            </v-col>
            <v-col 
              v-for="(select, index) in selectInputs"
              :key="index"
              class="py-0" 
              cols="6"
            >
              <v-select
                chips
                hide-details
                active
                required
                class="mb-5 mt-3"
                color="primary"
                base-color="primary"
                variant="outlined"
                density="comfortable"
                :label="select.label"
                :items="select.items"
                v-model="select.model.value"
                @update:modelValue="($event) => select.model.value = $event"
              ></v-select>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>

      <v-divider></v-divider>
      <v-card-actions class="pt-4">
        <v-spacer />
        <v-btn 
          color="grey" 
          variant="text" 
          @click="showDialog = false"
        >
          Cancelar
        </v-btn>
        <v-btn 
          id="recipeForm"
          type="submit"
          color="primary" 
          variant="flat" 
          @click="validateForm"
        >
          Guardar
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
const { showSuccess, showError } = useNotification();

const props = defineProps({
  show: {
    type: Boolean,
    required: true
  },
  isEdit: {
    type: Boolean,
    required: true,
    default: false
  },
  recipe: {
    type: Object as PropType<Receta | null>,
    required: false,
    default: null
  }
});

const emit = defineEmits<{
  (e: 'update:show', value: boolean): void;
  (e: 'get:recipes'): void;
}>();

// Show dialog computed property
const showDialog = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value)
});


/*************************************/
/* FORM DATA BINDING + INPUTS */
/*************************************/
const level = ['FACIL', 'MEDIO', 'DIFICIL'];
const foodTypes = ['DESAYUNO', 'BRUNCH', 'ALMUERZO', 'MERIENDA', 'CENA', 'TAPAS', 'RACIONES', 'POSTRE', 'SALADO', 'DULCE'];

// Recipe Form
const recipeForm = ref({
  titulo: '',
  subtitulo: '',
  descripcion: '',
  imagenUrl: '',
  dificultad: 'FACIL',
  comensales: 0,
  tiempoPreparacion: 0,
  valorEnergetico: 0,
  tipoComida: 'DESAYUNO'
});

// Watch for dialog open and populate form if editing
watch(() => props.show, (newValue) => {
  if (newValue) {
    if (props.isEdit && props.recipe) {
      recipeForm.value = {
          titulo: props.recipe.titulo || '',
          subtitulo: props.recipe.subtitulo || '',
          descripcion: props.recipe.descripcion || '',
          imagenUrl: props.recipe.imagenUrl || '',
          dificultad: props.recipe.dificultad || 'FACIL',
          comensales: props.recipe.comensales || 0,
          tiempoPreparacion: props.recipe.tiempoPreparacion || 0,
          valorEnergetico: props.recipe.valorEnergetico || 0,
          tipoComida: props.recipe.tipoComida || 'DESAYUNO'
      };
    } else {
      // Reset form for new recipe
      recipeForm.value = {
          titulo: '',
          subtitulo: '',
          descripcion: '',
          imagenUrl: '',
          dificultad: 'FACIL',
          comensales: 0,
          tiempoPreparacion: 0,
          valorEnergetico: 0,
          tipoComida: 'DESAYUNO'
      };
    }
  }
});

// Validation rules
const validationRules = {
  required: [(v: any) => !!v || 'Este campo es obligatorio'],
  title: [
    (v: string) => !!v || 'El título es obligatorio',
    (v: string) => v.length >= 20 || 'El título debe tener al menos 20 caracteres'
  ],
  text: [
    (v: string) => !!v || 'El texto es obligatorio',
    (v: string) => v.length >= 50 || 'El texto debe tener al menos 50 caracteres'
  ],
};

// Text fields configuration array
const textFields = computed(() => [
  {
    label: 'Titulo *',
    type: 'text',
    col: '12',
    model: computed({
      get: () => recipeForm.value.titulo,
      set: (val) => recipeForm.value.titulo = val
    }),
    rules: validationRules.title
  },
  {
    label: 'Subtítulo *',
    type: 'text',
    col: '12',
    model: computed({
      get: () => recipeForm.value.subtitulo,
      set: (val) => recipeForm.value.subtitulo = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Descripción *',
    type: 'text',
    col: '12',
    model: computed({
      get: () => recipeForm.value.descripcion,
      set: (val) => recipeForm.value.descripcion = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Imagen URL *',
    type: 'text',
    col: '6',
    model: computed({
      get: () => recipeForm.value.imagenUrl,
      set: (val) => recipeForm.value.imagenUrl = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Comensales *',
    type: 'number',
    col: '6',
    model: computed({
      get: () => recipeForm.value.comensales,
      set: (val) => recipeForm.value.comensales = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Tiempo preparación *',
    type: 'number',
    col: '6',
    model: computed({
      get: () => recipeForm.value.tiempoPreparacion,
      set: (val) => recipeForm.value.tiempoPreparacion = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Valor energético *',
    type: 'number',
    col: '6',
    model: computed({
      get: () => recipeForm.value.valorEnergetico,
      set: (val) => recipeForm.value.valorEnergetico = val
    }),
    rules: validationRules.required
  }
]);

const selectInputs = computed(() => [
  {
    label: 'Dificultad *',
    model: computed({
      get: () => recipeForm.value.dificultad,
      set: (val) => recipeForm.value.dificultad = val
    }),
    items: level
  },
  {
    label: 'Tipo de comida *',
    model: computed({
      get: () => recipeForm.value.tipoComida,
      set: (val) => recipeForm.value.tipoComida = val
    }),
    items: foodTypes
  }
]);


/*************************************/
/* FORM VALIDATION METHOD */
/*************************************/
const formRef = ref();

// Update recipe
const updateRecipe = async () => {
  try {
    const response = await useApiFetch(API.RECIPES.UPDATE, {
      method: 'PUT',
      body: {
        idReceta: props.recipe?.idReceta,
        ...recipeForm.value
      }
    });
    
    if (response) {
      showSuccess(`Receta ${recipeForm.value.titulo} editada correctamente`);
      emit('get:recipes');
    }
  } catch (error: any) {
    showError(`Error al editar la receta: ${error.message || error}`);
  }
};

// Add new recipe
const addRecipe = async () => {
  try {
    const response = await useApiFetch(API.RECIPES.ADD, {
      method: 'POST',
      body: recipeForm.value
    });
    
    if (response) {
      showSuccess(`Receta ${recipeForm.value.titulo} agregada correctamente`);
      emit('get:recipes');
    }
  } catch (error: any) {
    showError(`Error al agregar la receta: ${error.message || error}`);
  }
};

// Validate form and decide to add or update
const validateForm = async () => {
  const { valid } = await formRef.value?.validate();
  if (!valid) return;

  if (props.isEdit && props.recipe) updateRecipe();
  else addRecipe();  
};
</script>

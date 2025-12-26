<template>
  <v-dialog
    v-model="showDialog"
    max-width="95%"
    opacity="60%"
    class="recipe-form"
  >
    <v-card class="px-10 py-6 recipe-form-card">
      <v-card-title class="text-h5 text-primary pa-0 pb-3 recipe-form-header">
        {{ isEdit ? 'Editar' : 'Agregar' }} Receta
      </v-card-title>
      <v-divider class="recipe-form-header-divider"></v-divider>

      <v-card-text class="recipe-form-content">
        <v-form 
          id="recipeForm"
          ref="formRef"
          validate-on="submit"
        >
          <v-row class="pt-5">
            <v-col
              v-for="(field, index) in textFields"
              :key="index"
              cols="12"
              :md="field.col"
              class="py-0"
            >
              <v-text-field
                v-if="field.input === 'input'"
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
              <v-textarea
                v-else
                active
                required
                no-resize
                persistent-counter
                hide-details="auto"
                class="mb-5 mt-3"
                color="primary"
                base-color="primary"
                variant="outlined"
                density="comfortable"
                :label="field.label"
                v-model="field.model.value"
                :rules="field.rules"
              ></v-textarea>
            </v-col>
            <v-col 
              v-for="(select, index) in selectInputs"
              :key="index"
              class="py-0" 
              cols="12"
              md="4"
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
          <v-row v-if="recipeSteps.length" class="mt-4 mb-6">
            <v-col cols="12">
              <h3 class="text-h6 text-darkgrey font-weight-bold mb-1">Pasos de la receta</h3>
              <v-divider></v-divider>
              <v-row
                v-for="(step, index) in recipeSteps"
                :key="index"
                class="mt-2"
              >
                <AdminRecipeFormStep
                  :step="step"
                  :isEdit="props.isEdit"
                  :idReceta="props.recipe?.idReceta || null"
                  @delete:step="(idx: number) => recipeSteps.splice(idx, 1)"
                  @update:step="(value: string) => step.descripcion = value"
                />
              </v-row>
            </v-col>
          </v-row>
          <v-row v-if="recipeIngredients.length" class="mt-4 mb-6">
            <v-col cols="12">
              <h3 class="text-h6 text-darkgrey font-weight-bold mb-1">Ingredientes de la receta</h3>
              <v-divider></v-divider>
              <v-row
                v-for="(ingredient, index) in recipeIngredients"
                :key="index"
                class="mt-2"
              >
                <AdminRecipeFormIngredient
                  :ingredient="ingredient"
                  :index="index"
                  :isEdit="props.isEdit"
                  :idReceta="props.recipe?.idReceta || null"
                  @delete:ingredient="(idx: number) => recipeIngredients.splice(idx, 1)"
                  @update:ingredient="(value: Ingredient) => recipeIngredients[index] = value"
                />
              </v-row>
            </v-col>
          </v-row>
        </v-form>
        <div class="d-flex justify-end mt-6">
          <v-btn 
            class="mr-2"
            color="primary" 
            variant="outlined" 
            prepend-icon="mdi-plus"
            @click="recipeSteps.push({ orden: recipeSteps.length + 1, descripcion: '' })"
          >
            Añadir pasos
          </v-btn>
          <v-btn 
            color="primary" 
            variant="outlined" 
            prepend-icon="mdi-plus"
            @click="recipeIngredients.push({...ingredient})"
          >
            Añadir ingredientes
          </v-btn>
        </div>
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
type Ingredient = {
  nombre: string;
  cantidad: number;
  unidad: string;
};

type Step = {
  orden: number;
  descripcion: string;
};

const level = ['FACIL', 'MEDIO', 'DIFICIL'];
const ingredient: Ingredient = { nombre: '', cantidad: 0, unidad: 'g' };
const foodTypes = ['DESAYUNO', 'BRUNCH', 'ALMUERZO', 'MERIENDA', 'CENA', 'TAPAS', 'RACIONES', 'POSTRE', 'SALADO', 'DULCE'];

// Recipe Form
const recipeForm = ref({
  idReceta: null as number | null,
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

const recipeSteps = ref<Step[]>([]);
const recipeIngredients = ref<Ingredient[]>([]);

// Watch for dialog open and populate form if editing
watch(() => props.show, (newValue) => {
  if (newValue) {
    if (props.isEdit && props.recipe) {
      recipeForm.value = {
          idReceta: props.recipe.idReceta,
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
      getRecipeStepsById(props.recipe.idReceta);
      getRecipeIngredientsById(props.recipe.idReceta);
    } else {
      // Reset form for new recipe
      recipeForm.value = {
          idReceta: null,
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
      recipeSteps.value = [];
      recipeIngredients.value = [];
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
    input: 'input',
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
    input: 'input',
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
    input: 'textarea',
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
    input: 'input',
    type: 'text',
    col: '4',
    model: computed({
      get: () => recipeForm.value.imagenUrl,
      set: (val) => recipeForm.value.imagenUrl = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Comensales *',
    input: 'input',
    type: 'number',
    col: '4',
    model: computed({
      get: () => recipeForm.value.comensales,
      set: (val) => recipeForm.value.comensales = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Tiempo preparación *',
    input: 'input',
    type: 'number',
    col: '4',
    model: computed({
      get: () => recipeForm.value.tiempoPreparacion,
      set: (val) => recipeForm.value.tiempoPreparacion = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Valor energético *',
    input: 'input',
    type: 'number',
    col: '4',
    model: computed({
      get: () => recipeForm.value.valorEnergetico,
      set: (val) => recipeForm.value.valorEnergetico = val
    }),
    rules: validationRules.required
  }
]);

// Select fields configuration array
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
/* FETCH RECIPE STEPS AND INGREDIENTS */
/*************************************/
// Get receta steps by ID from API
const getRecipeStepsById = async (idReceta: number) => {
  try {
    let data = await useApiFetch(API.RECIPES.STEPS, {
      params: { idReceta },
    }) as RecetaPaso[];

    // Sort steps by 'orden' and map to descriptions
    recipeSteps.value = data.sort((a: RecetaPaso, b: RecetaPaso) => a.orden - b.orden); 
  } catch (error) {
    showError('Error fetching steps');
  }
};

// Get receta ingredients by ID from API
const getRecipeIngredientsById = async (idReceta: number) => {
  try {
    const data = await useApiFetch(API.RECIPES.INGREDIENTS, {
      params: { idReceta },
    }) as RecetaIngrediente[];

    recipeIngredients.value = data;
  } catch (error) {
    showError('Error fetching ingredients');
  }
};


/*************************************/
/* FORM VALIDATION METHOD */
/*************************************/
const formRef = ref();

// Update recipe
const updateRecipe = async () => {
  try {
    const response = await useApiFetch(API.RECIPES.UPDATE, {
      method: 'PUT',
      body: recipeForm.value
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
      if (recipeSteps.value.length) await addSteps(response.idReceta);
      if (recipeIngredients.value.length) await addIngredients(response.idReceta);
      showSuccess(`Receta ${recipeForm.value.titulo} agregada correctamente`);
      emit('get:recipes');
    }
  } catch (error: any) {
    showError(`Error al agregar la receta: ${error.message || error}`);
  }
};

// Add steps to recipe
const addSteps = async (idReceta: number) => {
  for (let i = 0; i < recipeSteps.value.length; i++) {
    const step = recipeSteps.value[i];
    try {
      await useApiFetch(API.RECIPES.ADD_STEP, {
        method: 'POST',
        body: {
          idReceta,
          descripcion: step.descripcion,
          orden: i + 1
        }
      });
    } catch (error: any) {
      showError(`Error al agregar el paso ${i + 1}: ${error.message || error}`);
    }
  }
};

// Add ingredients to recipe
const addIngredients = async (idReceta: number) => {
  const body = recipeIngredients.value.map((ingredient) => ({ idReceta, ...ingredient }));

  try {
    await useApiFetch(API.RECIPES.ADD_INGREDIENT, {
      method: 'POST',
      body
    });
  } catch (error: any) {
    showError(`Error al agregar los ingredientes: ${error.message || error}`);
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

<style lang="scss">
.recipe-form-card {
  display: flex;
  flex-direction: column;
  max-height: 90vh;
}

.recipe-form-header {
  position: sticky;
  top: 0;
  background-color: white;
  z-index: 10;
}

.recipe-form-header-divider {
  position: sticky;
  top: 52px;
  background-color: white;
  z-index: 10;
  margin-bottom: 12px;
}

.recipe-form-content {
  overflow-y: auto;
  flex: 1;
}

.recipe-form-step__number {
  width: 60px;
  text-align: center;
  margin-right: 8px;
  border-radius: 4px;
  flex-grow: 0;
  background-color: #836A02;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;

  input {
    text-align: center !important;
  }
}
</style>

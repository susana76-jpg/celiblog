<template>
  <v-form ref="recipeForm" validate-on="submit">

    <!-- FORM INPUTS ---------------------------------------->
    <p class="mb-7 mt-10">Rellene el formulario para crear una nueva receta</p>
    <v-row>
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
          :disabled="disabled"
          v-model="formData[field.model]"
        ></v-text-field>
        <v-textarea
          v-else
          active
          required
          clearable
          no-resize
          persistent-counter
          hide-details="auto"
          class="mb-5 mt-3"
          color="primary"
          base-color="primary"
          variant="outlined"
          density="comfortable"
          :label="field.label"
          :rows="field.rows"
          :rules="field.rules"
          :disabled="disabled"
          v-model="formData[field.model]"
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
          v-model="formData[select.model] as keyof RecipeForm"
        />
      </v-col>
    </v-row>
    <v-row v-if="recipeSteps.length" class="mt-4 mb-6">
      <v-col cols="12">
        <h3 class="text-h6 text-darkgrey font-weight-bold mb-1">
          Pasos de la receta
        </h3>
        <v-divider></v-divider>
        <v-row v-for="(step, index) in recipeSteps" key="index" class="mt-2">
          <AdminRecipeFormStep
            ref="stepRefs"
            :step="step"
            :isEdit="false"
            :idReceta="null"
            @delete:step="recipeSteps = recipeSteps.filter((step) => step.orden !== $event)"
            @update:step="(value: string) => step.descripcion = value"
          />
        </v-row>
      </v-col>
    </v-row>
    <v-row v-if="recipeIngredients.length" class="mt-4 mb-6">
      <v-col cols="12">
        <h3 class="text-h6 text-darkgrey font-weight-bold mb-1">
          Ingredientes de la receta
        </h3>
        <v-divider></v-divider>
        <v-row
          v-for="(ingredient, index) in recipeIngredients"
          :key="index"
          class="mt-2"
        >
          <AdminRecipeFormIngredient
            ref="ingredientRefs"
            :ingredient="ingredient"
            :index="index"
            :isEdit="false"
            :idReceta="null"
            @delete:ingredient="(idx: number) => recipeIngredients.splice(idx, 1)"
            @update:ingredient="(value: Ingredient) => recipeIngredients[index] = value"
          />
        </v-row>
      </v-col>
    </v-row>
    <!------------------------------------------------------>

    <!-- ALERT MESSAGE ------------------------------------->
    <v-alert
      v-if="message.show"
      closable
      class="mt-4 mb-6"
      density="compact"
      :type="message.type"
    >
      {{ message.text }}
    </v-alert>
    <!------------------------------------------------------>

    <!-- FORM ACTION BUTTONS ------------------------------->
    <div class="mt-4 d-flex justify-end">
      <v-btn
        v-for="(button, index) in actionButtons"
        :key="index"
        height="40"
        rounded="xl"
        color="primary"
        :class="button.class"
        :variant="button.variant"
        :text="button.text"
        :prepend-icon="button.prependIcon"
        :disabled="button.disabled"
        :loading="button.loading || false"
        @click="button.onClick"
      />
    </div>
    <!------------------------------------------------------>

  </v-form>
</template>

<script setup lang="ts">
const props = defineProps<{
  disabled?: boolean;
}>();

/********************************/
/* FORM DATA */
/********************************/
// Recipe Form Data Interface and data
interface RecipeForm {
  titulo: string;
  subtitulo: string;
  descripcion: string;
  imagenUrl: string;
  dificultad: string;
  comensales: number;
  tiempoPreparacion: number;
  valorEnergetico: number;
  tipoComida: string;
}

// Form Data Reactive Object
const formData = ref<RecipeForm>({
  titulo: "",
  subtitulo: "",
  descripcion: "",
  imagenUrl: "",
  dificultad: "FACIL",
  comensales: 0,
  tiempoPreparacion: 0,
  valorEnergetico: 0,
  tipoComida: "ALMUERZO",
});

// Initial Ingredient and Steps
const ingredient: Ingredient = { nombre: "", cantidad: 0, unidad: "g" };
const recipeSteps = ref<Step[]>([]);
const recipeIngredients = ref<Ingredient[]>([]);


/********************************/
/* FORM FIELDS CONFIGURATION */
/********************************/
// Text fields configuration array
const textFields = computed(() => [
  {
    col: "12",
    input: "input",
    label: "Titulo *",
    type: "text",
    required: true,
    model: "titulo" as keyof RecipeForm,
    rules: [(v: any) => !!v || "El título es obligatorio"],
  },
  {
    label: "Subtítulo *",
    input: "input",
    type: "text",
    col: "12",
    required: true,
    model: "subtitulo" as keyof RecipeForm,
    rules: [(v: any) => !!v || "El subtítulo es obligatorio"],
  },
  {
    label: "Descripción *",
    input: "textarea",
    type: "text",
    col: "12",
    rows: 6,
    required: true,
    model: "descripcion" as keyof RecipeForm,
    rules: [(v: any) => !!v || "La descripción es obligatoria"],
  },
  {
    label: "Imagen URL *",
    input: "input",
    type: "text",
    col: "4",
    required: true,
    model: "imagenUrl" as keyof RecipeForm,
    rules: [(v: any) => !!v || "La URL de la imagen es obligatoria"],
  },
  {
    label: "Comensales *",
    input: "input",
    type: "number",
    col: "4",
    required: true,
    model: "comensales" as keyof RecipeForm,
    rules: [(v: any) => !!v || "El número de comensales es obligatorio"],
  },
  {
    label: "Tiempo preparación *",
    input: "input",
    type: "number",
    col: "4",
    required: true,
    model: "tiempoPreparacion" as keyof RecipeForm,
    rules: [(v: any) => !!v || "El tiempo de preparación es obligatorio"],
  },
  {
    label: "Valor energético *",
    input: "input",
    type: "number",
    col: "4",
    required: true,
    model: "valorEnergetico" as keyof RecipeForm,
    rules: [(v: any) => !!v || "El valor energético es obligatorio"],
  },
]);

// Select fields configuration array
const selectInputs = computed(() => [
  {
    label: "Dificultad *",
    model: "dificultad" as keyof RecipeForm,
    items: DIFFICULTY_LEVELS,
  },
  {
    label: "Tipo de comida *",
    model: "tipoComida" as keyof RecipeForm,
    items: FOOD_TYPES,
  },
]);

// Action buttons configuration array
const actionButtons = computed(() => [
  {
    text: "Añadir paso",
    variant: "outlined" as const,
    prependIcon: "mdi-plus",
    class: "mr-2",
    onClick: () => recipeSteps.value.push({ orden: recipeSteps.value.length + 1, descripcion: '' }),
  },
  {
    text: "Añadir ingrediente",
    variant: "outlined" as const,
    prependIcon: "mdi-plus",
    class: "",
    onClick: () => recipeIngredients.value.push({ ...ingredient }),
  },
  {
    text: "Enviar receta",
    variant: "flat" as const,
    prependIcon: undefined,
    class: "ml-2",
    onClick: handleSubmit,
    disabled: props.disabled,
    loading: isSubmitting.value,
  },
]);


/********************************/
/* SUBMISSION STATE */
/********************************/
const recipeForm = ref();
const isSubmitting = ref<boolean>(false);
const message = ref<{
  show: boolean;
  type?: "error" | "info" | "success" | "warning";
  text: string;
}>({
  show: false,
  type: "success",
  text: "",
});

// Handle form submission
const stepRefs = ref<any[]>([]);
const ingredientRefs = ref<any[]>([]);
const handleSubmit = async () => {
  isSubmitting.value = true;
  message.value.show = false;

  // Validate main form
  const { valid } = await recipeForm.value?.validate();

  // Validate all steps
  const stepsValidation = await Promise.all(
    stepRefs.value?.map((ref) => ref.validate()) || []
  );
  const stepsValid = stepsValidation.every((result) => result?.valid !== false);

  // Validate all ingredients
  const ingredientsValidation = await Promise.all(
    ingredientRefs.value?.map((ref) => ref.validate()) || []
  );
  const ingredientsValid = ingredientsValidation.every(
    (result) => result?.valid !== false
  );

  if (!valid || !stepsValid || !ingredientsValid) {
    message.value = {
      show: true,
      type: "error",
      text: "Por favor, complete todos los campos obligatorios",
    };
    isSubmitting.value = false;
    return;
  }

  await addRecipe();

  isSubmitting.value = false;
};


/********************************/
/* API INTERACTION FUNCTIONS */
/********************************/
// Add new recipe
const addRecipe = async () => {
  try {
    const response = await useApiFetch(API.RECIPES.ADD, {
      method: 'POST',
      body: formData.value
    }) as Receta;
    if (response && response.idReceta) {
      if (recipeSteps.value.length) await addSteps(response.idReceta);
      if (recipeIngredients.value.length) await addIngredients(response.idReceta);
    }

    message.value = {
      show: true,
      type: "success" as const,
      text: `Receta ${formData.value.titulo} agregada correctamente`,
    };
    
  } catch (error: any) {
    message.value = {
      show: true,
      type: "error" as const,
      text: `Error al agregar la receta: ${error.message || "Error desconocido"}`,
    };
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
      message.value = {
        show: true,
        type: "error" as const,
        text: `Error al agregar el paso ${i + 1}: ${error.message || "Error desconocido"}`,
      };
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
    message.value = {
      show: true,
      type: "error" as const,
      text: `Error al agregar los ingredientes: ${error.message || "Error desconocido"}`,
    };
  }
};
</script>

<style lang="scss">
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

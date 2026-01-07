<template>
  <v-form ref="formRef" class="w-100" validate-on="submit">
    <v-col cols="12" class="d-flex align-start">
      <v-text-field
        v-bind="commonFieldProps"
        readonly
        hide-details
        class="recipe-form-step__number"
        :model-value="step.orden"
      >
      </v-text-field>
      <v-textarea
        v-bind="commonFieldProps"
        required
        no-resize
        auto-grow
        clearable
        rows="1"
        hide-details="auto"
        variant="outlined"
        :label="`Paso ${step.orden} *`"
        :rules="[(v) => !!v || `Campo obligatorio`, (v) => v.length >= 10 || `Paso debe tener al menos 10 caracteres`]"
        v-model="descripcion"
      ></v-textarea>
      <v-tooltip location="top" text="Eliminar paso">
        <template v-slot:activator="{ props }">
          <v-btn
            v-bind="props"
            variant="text"
            color="primary"
            icon="mdi-delete-outline"
            height="48"
            width="48"
            @click="deleteStep"
          />
        </template>
      </v-tooltip>
    </v-col>
  </v-form>
</template>
<script setup lang="ts">
const props = defineProps<{
  step: Step;
  isEdit: boolean;
  idReceta: number | null;
}>();

const emit = defineEmits([
  'delete:step',
  'update:step'
]);

const commonFieldProps = {
  color: 'primary',
  baseColor: 'primary',
  density: 'comfortable' as const
};

// Computed property for two-way binding of step description
const descripcion = computed<string>({
  get: () => props.step.descripcion,
  set: (value: string) => emit('update:step', value),
});

// Function to delete step from DB if in edit mode
const deleteStep = async () => {
  if (props.isEdit && props.idReceta !== null) deleteStepFromDB();
  else emit('delete:step', props.step.orden);
}; 

// API call to delete step from database
const deleteStepFromDB = async () => {
  try {
    const response = await useApiFetch(API.RECIPES.DELETE_STEP, {
      method: 'DELETE',
      body: {
        idReceta: props.idReceta,
        orden: props.step.orden,
        descripcion: props.step.descripcion
      }
    });

    if (response) emit('delete:step', props.step.orden - 1);
  } catch (error) {
    showError('Error al eliminar el paso.');
  }
};  

// Expose validate method to parent component
const formRef = ref<{ validate: () => Promise<any> } | null>(null);
defineExpose({
  validate: () => formRef.value?.validate()
})
</script>
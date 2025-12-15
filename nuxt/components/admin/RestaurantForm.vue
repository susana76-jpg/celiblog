<template>
  <v-form ref="formRef" @submit.prevent="validateForm">
    <v-text-field
      v-for="(field, index) in textFields"
      :key="index"
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
  </v-form>
</template>

<script setup lang="ts">
const props = defineProps({
  modelValue: {
    type: Object,
    required: true
  },
});

const emit = defineEmits<{
  (e: 'update:modelValue', value: any): void;
  (e: 'submit', isValid: boolean): void;
}>();

/*************************************/
/* FORM DATA BINDING + INPUTS */
/*************************************/
const formData = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
});

// Validation rules
const validationRules = {
  name: [
    (v: string) => !!v || 'El nombre es obligatorio',
    (v: string) => v.length >= 2 || 'El nombre debe tener al menos 2 caracteres'
  ],
  text: [
    (v: string) => !!v || 'El texto es obligatorio',
    (v: string) => v.length >= 20 || 'El texto debe tener al menos 20 caracteres'
  ],
  email: [
    (v: string) => !!v || 'El email es requerido',
    (v: string) => /.+@.+\..+/.test(v) || 'El email debe ser válido'
  ],
  telephone: [
    (v: string) => !!v || 'El teléfono es obligatorio',
    (v: string) => /^\d{9,15}$/.test(v) || 'El teléfono debe ser válido'
  ],  
  postalCode: [
    (v: string) => !!v || 'El código postal es obligatorio',
    (v: string) => /^\d{5}$/.test(v) || 'El código postal debe ser válido'
  ], 
};

// Text fields configuration array
const textFields = computed(() => [
  {
    label: 'Nombre *',
    type: 'text',
    model: computed({
      get: () => formData.value.nombre,
      set: (val) => formData.value.nombre = val
    }),
    rules: validationRules.name
  },
  {
    label: 'Subtítulo *',
    type: 'text',
    model: computed({
      get: () => formData.value.subtitulo,
      set: (val) => formData.value.subtitulo = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Descripción *',
    type: 'text',
    model: computed({
      get: () => formData.value.subtitulo,
      set: (val) => formData.value.subtitulo = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Dirección *',
    type: 'text',
    model: computed({
      get: () => formData.value.direccion,
      set: (val) => formData.value.direccion = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Ubicación *',
    type: 'text',
    model: computed({
      get: () => formData.value.ubicacion,
      set: (val) => formData.value.ubicacion = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Código Postal *',
    type: 'number',
    model: computed({
      get: () => formData.value.codigoPostal,
      set: (val) => formData.value.codigoPostal = val
    }),
    rules: validationRules.postalCode
  },
  {
    label: 'Imagen url *',
    type: 'text',
    model: computed({
      get: () => formData.value.imagenUrl,
      set: (val) => formData.value.imagenUrl = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Página web *',
    type: 'text',
    model: computed({
      get: () => formData.value.urlWeb,
      set: (val) => formData.value.urlWeb = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Teléfono *',
    type: 'number',
    model: computed({
      get: () => formData.value.telefono,
      set: (val) => formData.value.telefono = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Correo electrónico *',
    type: 'text',
    model: computed({
      get: () => formData.value.email,
      set: (val) => formData.value.email = val
    }),
    rules: validationRules.email
  }
]);


/*************************************/
/* FORM VALIDATION METHOD */
/*************************************/
const formRef = ref();
const validateForm = async () => {
  const { valid } = await formRef.value?.validate();
  return emit('submit', valid);
};
</script>

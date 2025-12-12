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
      :prepend-inner-icon="field.icon"
      :rules="field.rules"
      v-model="field.model.value"
    ></v-text-field>
    <v-select
      chips
      hide-details
      active
      required
      class="mb-5 mt-3"
      color="primary"
      base-color="primary"
      variant="outlined"
      item-title="nombre"
      item-value="idRol"
      density="comfortable"
      label="Rol de usuario *"
      :items="USER_ROLES"
      v-model="formData.idRol"
      @update:modelValue="($event) => formData.idRol = $event"
    ></v-select>
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
  email: [
    (v: string) => !!v || 'El email es requerido',
    (v: string) => /.+@.+\..+/.test(v) || 'El email debe ser válido'
  ],
  password: [
    (v: string) => !!v || 'La contraseña es obligatoria',
    (v: string) => v.length >= 6 || 'La contraseña debe tener al menos 6 caracteres'
  ]
};

// Text fields configuration array
const textFields = computed(() => [
  {
    label: 'Nombre *',
    icon: 'mdi-account',
    type: 'text',
    model: computed({
      get: () => formData.value.nombre,
      set: (val) => formData.value.nombre = val
    }),
    rules: validationRules.name
  },
  {
    label: 'Correo electrónico *',
    icon: 'mdi-email',
    type: 'text',
    model: computed({
      get: () => formData.value.email,
      set: (val) => formData.value.email = val
    }),
    rules: validationRules.email
  },
  {
    label: 'Contraseña *',
    icon: 'mdi-lock',
    type: 'text',
    model: computed({
      get: () => formData.value.password,
      set: (val) => formData.value.password = val
    }),
    rules: validationRules.password
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

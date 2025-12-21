<template>
  <v-dialog
    v-model="showDialog"
    max-width="65%"
    opacity="60%"
    class="comment-form"
  >
    <v-card class="px-10 py-6">
      <v-card-title class="text-h5 text-primary pa-0 pb-3">
        Agregar Usuario
      </v-card-title>
      <v-divider></v-divider>

      <v-card-text>
        <v-form 
          id="userForm"
          ref="formRef" 
          validate-on="submit"
        >
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
            v-model="userForm.idRol"
            @update:modelValue="($event) => userForm.idRol = $event"
          ></v-select>
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
          for="userForm"
          type="submit"
          color="primary" 
          variant="flat" 
          @click="addUser"
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
});

const emit = defineEmits<{
  (e: 'update:show', value: boolean): void;
  (e: 'get:users'): void;
}>();


/*************************************/
/* CONSTANTS */
/*************************************/
// Dialog visibility computed property + reset form on close
const showDialog = computed({
  get: () => props.show,
  set: (value) => {
    if (!value) {
      resetForm();
      emit('update:show', false);
    }
  }
});

// Reset form fields
const resetForm = () => {
  userForm.value = {
    nombre: '',
    email: '',
    idRol: 3,
    password: ''
  };
};


/*************************************/
/* FORM DATA BINDING + INPUTS */
/*************************************/
// User Form
const userForm = ref({
  nombre: '',
  email: '',
  idRol: 3,
  password: ''
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
      get: () => userForm.value.nombre,
      set: (val) => userForm.value.nombre = val
    }),
    rules: validationRules.name
  },
  {
    label: 'Correo electrónico *',
    icon: 'mdi-email',
    type: 'text',
    model: computed({
      get: () => userForm.value.email,
      set: (val) => userForm.value.email = val
    }),
    rules: validationRules.email
  },
  {
    label: 'Contraseña *',
    icon: 'mdi-lock',
    type: 'text',
    model: computed({
      get: () => userForm.value.password,
      set: (val) => userForm.value.password = val
    }),
    rules: validationRules.password
  }
]);


/*************************************/
/* FORM VALIDATION METHOD */
/*************************************/
const formRef = ref();

// Add new user
const addUser = async () => {
  const { valid } = await formRef.value?.validate();
  if (!valid) return;

  try {
    const response = await useApiFetch(API.USERS.ADD, {
      method: 'POST',
      body: userForm.value
    });
    
    if (response) {
      showSuccess('Usuario agregado correctamente');
      emit('get:users');
    }
  } catch (error: any) {
    showError(`Error al agregar el usuario: ${error.message || error}`);
  }
};
</script>

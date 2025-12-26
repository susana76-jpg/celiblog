<template>
  <v-dialog
    v-model="showDialog"
    max-width="65%"
    opacity="60%"
    class="comment-form"
  >
    <v-card class="px-10 py-6">
      <v-card-title class="text-h5 text-primary pa-0 pb-3">
        {{ isEdit ? 'Editar' : 'Agregar' }} Restaurante
      </v-card-title>
      <v-divider></v-divider>

      <v-card-text>
        <v-form 
          id="restaurantForm"
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
            <v-col class="py-0" cols="6">
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
                label="Tipo de restaurante *"
                :items="restaurantTypes"
                v-model="restaurantForm.tipoRestaurante"
                @update:modelValue="($event) => restaurantForm.tipoRestaurante = $event"
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
          for="restaurantForm"
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
  restaurant: {
    type: Object as PropType<Restaurante | null>,
    required: false,
    default: null
  }
});

const emit = defineEmits<{
  (e: 'update:show', value: boolean): void;
  (e: 'get:restaurants'): void;
}>();

// Show dialog computed property
const showDialog = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value)
});

/*************************************/
/* FORM DATA BINDING + INPUTS */
/*************************************/
const restaurantTypes = ['SIN_GLUTEN', 'MEDITERRANEA', 'ASIATICA', 'VEGANO', 'MEXICANA'];

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

// Watch for dialog open and populate form if editing
watch(() => props.show, (newValue) => {
  if (newValue) {
    if (props.isEdit && props.restaurant) {
      // Fill form with restaurant data
      restaurantForm.value = {
        titulo: props.restaurant.titulo || '',
        subtitulo: props.restaurant.subtitulo || '',
        descripcion: props.restaurant.descripcion || '',
        direccion: props.restaurant.direccion || '',
        imagenUrl: props.restaurant.imagenUrl || '',
        nombre: props.restaurant.nombre || '',
        ubicacion: props.restaurant.ubicacion || '',
        codigoPostal: props.restaurant.codigoPostal || 0,
        urlWeb: props.restaurant.urlWeb || '',
        telefono: props.restaurant.telefono || '',
        email: props.restaurant.email || '',
        valoracion: props.restaurant.valoracion || 0,
        tipoRestaurante: props.restaurant.tipoRestaurante || 'SIN_GLUTEN'
      };
    } else {
      // Reset form for new restaurant
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
        tipoRestaurante: 'SIN_GLUTEN'
      };
    }
  }
});

// Validation rules
const validationRules = {
  required: [
    (v: any) => !!v || 'Este campo es obligatorio'
  ],
  name: [
    (v: string) => !!v || 'El nombre es obligatorio',
    (v: string) => v.length >= 2 || 'El nombre debe tener al menos 2 caracteres'
  ],
  text: [
    (v: string) => !!v || 'El texto es obligatorio',
    (v: string) => v.length >= 10 || 'El texto debe tener al menos 10 caracteres'
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
    col: '12',
    model: computed({
      get: () => restaurantForm.value.nombre,
      set: (val) => restaurantForm.value.nombre = val
    }),
    rules: validationRules.name
  },
  {
    label: 'Subtítulo *',
    type: 'text',
    col: '12',
    model: computed({
      get: () => restaurantForm.value.subtitulo,
      set: (val) => restaurantForm.value.subtitulo = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Descripción *',
    type: 'text',
    col: '12',
    model: computed({
      get: () => restaurantForm.value.descripcion,
      set: (val) => restaurantForm.value.descripcion = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Dirección *',
    type: 'text',
    col: '6',
    model: computed({
      get: () => restaurantForm.value.direccion,
      set: (val) => restaurantForm.value.direccion = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Ubicación *',
    type: 'text',
    col: '6',
    model: computed({
      get: () => restaurantForm.value.ubicacion,
      set: (val) => restaurantForm.value.ubicacion = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Código Postal *',
    type: 'number',
    col: '6',
    model: computed({
      get: () => restaurantForm.value.codigoPostal,
      set: (val) => restaurantForm.value.codigoPostal = +val
    }),
    rules: validationRules.postalCode
  },
  {
    label: 'Imagen url *',
    type: 'text',
    col: '6',
    model: computed({
      get: () => restaurantForm.value.imagenUrl,
      set: (val) => restaurantForm.value.imagenUrl = val
    }),
    rules: validationRules.required
  },
  {
    label: 'Teléfono *',
    type: 'number',
    col: '6',
    model: computed({
      get: () => restaurantForm.value.telefono,
      set: (val) => restaurantForm.value.telefono = val
    }),
    rules: validationRules.telephone
  },
  {
    label: 'Correo electrónico *',
    type: 'text',
    col: '6',
    model: computed({
      get: () => restaurantForm.value.email,
      set: (val) => restaurantForm.value.email = val
    }),
    rules: validationRules.email
  },
  {
    label: 'Página web *',
    type: 'text',
    col: '6',
    model: computed({
      get: () => restaurantForm.value.urlWeb,
      set: (val) => restaurantForm.value.urlWeb = val
    }),
    rules: validationRules.required
  },
]);


/*************************************/
/* FORM VALIDATION METHOD */
/*************************************/
const formRef = ref();

// Update restaurant
const updateRestaurant = async () => {
  try {
    const url = API.RESTAURANTS.UPDATE + `?idRestaurante=${props.restaurant?.idRestaurante}`;
    const response = await useApiFetch(url, {
      method: 'PUT',
      body: restaurantForm.value
    });
    
    if (response) {
      showSuccess(`Restaurante ${restaurantForm.value.nombre} editado correctamente`);
      emit('get:restaurants');
    }
  } catch (error: any) {
    showError(`Error al editar el restaurante: ${error.message || error}`);
  }
};

// Add new restaurant
const addRestaurant = async () => {
  try {
    const response = await useApiFetch(API.RESTAURANTS.ADD, {
      method: 'POST',
      body: restaurantForm.value
    });
    
    if (response) {
      showSuccess(`Restaurante ${restaurantForm.value.nombre} agregado correctamente`);
      emit('get:restaurants');
    }
  } catch (error: any) {
    showError(`Error al agregar el restaurante: ${error.message || error}`);
  }
};

// Validate form and decide to add or update
const validateForm = async () => {
  const { valid } = await formRef.value?.validate();
  if (!valid) return;

  if (props.isEdit && props.restaurant) updateRestaurant();
  else addRestaurant();  
};
</script>

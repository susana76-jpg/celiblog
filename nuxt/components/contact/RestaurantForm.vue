<template>
  <v-form ref="restaurantForm" validate-on="submit">
    <p class="mb-7 mt-10">Rellene el formulario para crear un nuevo restaurante</p>
    <v-row class="pt-5">
      <v-col
        v-for="(field, index) in restaurantFields"
        :key="index"
        :cols="field.col"
        class="py-0"
      >
        <v-text-field
          active
          clearable
          variant="outlined"
          hide-details="auto"
          density="comfortable"
          class="mb-5 mt-3"
          color="primary"
          base-color="primary"
          :label="field.label"
          :rules="field.rules"
          :required="field.required"
          :disabled="disabled"
          v-model="formData[field.model]"
        />
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
          :items="RESTAURANT_TYPES"
          v-model="formData.tipoRestaurante as string"
        />
      </v-col>
    </v-row>
    <v-alert 
      v-if="message.show"
      closable
      class="mb-4"
      density="compact"
      :type="message.type" 
    >
      {{ message.text }}
    </v-alert>
    <v-btn
      height="40" 
      class="mt-8 ml-auto d-block"
      rounded="xl"
      variant="flat"
      color="primary"
      text="Enviar restaurante"
      :disabled="disabled"
      :loading="isSubmitting"
      @click="handleSubmit"
    />
  </v-form>
</template>

<script setup lang="ts">
const props = defineProps<{
  disabled?: boolean;
}>();


/********************************/
/* FORM DATA */
/********************************/
// Restaurant Form Data Interface and data
interface RestaurantForm {
  titulo: string;
  subtitulo: string;
  descripcion: string;
  direccion: string;
  imagenUrl: string;
  nombre: string;
  ubicacion: string;
  codigoPostal: number;
  urlWeb: string;
  telefono: number;
  email: string;
  tipoRestaurante: string;        
}

// Form Data Reactive Object
const formData = ref<RestaurantForm>({
  titulo: '',
  subtitulo: '',
  descripcion: '',
  direccion: '',
  imagenUrl: '',
  nombre: '',
  ubicacion: '',
  codigoPostal: 0,
  urlWeb: '',
  telefono: 0,
  email: '',
  tipoRestaurante: 'VEGANO'
});

// Post Form Fields Configuration
const restaurantFields = [
  {
    model: 'nombre' as keyof RestaurantForm,
    label: 'Nombre del Restaurante *',
    type: 'text',
    col: '12',
    required: true,
    rules: [(v: any) => !!v || 'El nombre es obligatorio']
  },
  {
    model: 'subtitulo' as keyof RestaurantForm,
    label: 'Subtítulo *',
    type: 'text',
    col: '12',
    required: true,
    rules: [(v: any) => !!v || 'El subtítulo es obligatorio']
  },
  {
    model: 'descripcion' as keyof RestaurantForm,
    label: 'Descripción *',
    type: 'text',
    col: '12',
    required: true,
    rules: [(v: any) => !!v || 'La descripción es obligatoria']
  },
  {
    model: 'direccion' as keyof RestaurantForm,
    label: 'Dirección *',
    type: 'text',
    col: '6',
    required: true,
    rules: [(v: any) => !!v || 'La dirección es obligatoria']
  },
  {
    model: 'ubicacion' as keyof RestaurantForm,
    label: 'Ubicación *',
    type: 'text',
    col: '6',
    required: true,
    rules: [(v: any) => !!v || 'La ubicación es obligatoria']
  },
  {
    model: 'codigoPostal' as keyof RestaurantForm,
    label: 'Código Postal *',
    type: 'number',
    col: '6',
    required: true,
    rules: [
      (v: any) => !!v || 'El código postal es obligatorio',
      (v: any) => (/^\d{5}$/.test(v)) || 'El código postal debe tener 5 dígitos'
    ] 
  },
  {
    model: 'imagenUrl' as keyof RestaurantForm,
    label: 'Imagen url *',
    type: 'text',
    col: '6',
    required: true,
    rules: [(v: any) => !!v || 'La URL de la imagen es obligatoria']
  },
  {
    model: 'telefono' as keyof RestaurantForm,
    label: 'Teléfono *',
    type: 'number',
    col: '6',
    required: true,
    rules: [
      (v: any) => !!v || 'El teléfono es obligatorio',
      (v: any) => (/^\d{9,15}$/.test(v)) || 'El teléfono debe ser válido'
    ]
  },
  {
    model: 'email' as keyof RestaurantForm,
    label: 'Correo electrónico *',
    type: 'text',
    col: '6',
    required: true,
    rules: [
      (v: any) => !!v || 'El correo electrónico es obligatorio',
      (v: any) => (/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(v)) || 'El correo electrónico debe ser válido'
    ]
  },
  {
    model: 'urlWeb' as keyof RestaurantForm,
    label: 'Página web *',
    type: 'text',
    col: '6',
    required: true,
    rules: [(v: any) => !!v || 'La página web es obligatoria']
  }

];


/********************************/
/* SUBMISSION STATE */
/********************************/
const restaurantForm = ref();
const isSubmitting = ref<boolean>(false);
const message = ref<{ show: boolean; type?: 'error' | 'info' | 'success' | 'warning'; text: string }>({
  show: false,
  type: 'success',
  text: ''
});

// Handle form submission
const handleSubmit = async () => {
  isSubmitting.value = true;
  
  const { valid } = await restaurantForm.value?.validate();
  if (!valid) {
    isSubmitting.value = false;
    return;
  }

  await addRestaurant();

  isSubmitting.value = false;
};  

// Add new restaurant
const addRestaurant = async () => {
  try {
    const response = await useApiFetch(API.RESTAURANTS.ADD, {
      method: 'POST',
      body: formData.value
    });
    
    if (response) {
      message.value = { 
        show: true, 
        type: 'success' as const, 
        text: `Publicación ${formData.value.titulo} agregada correctamente` 
      };
    }
  } catch (error: any) {
    message.value = { 
      show: true, 
      type: 'error' as const, 
      text: `Error al agregar la publicación: ${error.message || 'Error desconocido'}` 
    };
  }
};
</script>

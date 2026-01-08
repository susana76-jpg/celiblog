<template>
  <v-form ref="postForm" validate-on="submit">
    <p class="mb-7 mt-10">Rellene el formulario para crear una nueva publicación</p>
    <template v-for="(field, index) in postFields" :key="index">
      <v-textarea
        v-if="field.type === 'textarea'"
        active
        clearable
        no-resize
        variant="outlined"
        hide-details="auto"
        density="comfortable"
        class="mb-5 mt-3"
        color="primary"
        base-color="primary"
        v-model="formData[field.model]"
        :label="field.label"
        :rules="field.rules"
        :rows="field.rows"
        :required="field.required"
        :disabled="disabled"
      ></v-textarea>
      <v-text-field
        v-else
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
      ></v-text-field>
    </template>
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
      text="Enviar publicación"
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
// Post Form Data Interface and data
interface PostForm {
  titulo: string;
  subtitulo: string;
  contenido: string;
  urlPost: string;
}

// Form Data Reactive Object
const formData = ref<PostForm>({
  titulo: "",
  subtitulo: "",
  contenido: "",
  urlPost: "",
});

// Post Form Fields Configuration
const postFields = [
  {
    model: 'titulo' as keyof PostForm,
    label: 'Título del Post *',
    icon: 'mdi-text-box',
    type: 'text',
    required: true,
    rules: [(v: any) => !!v || 'El título es obligatorio']
  },
  {
    model: 'subtitulo' as keyof PostForm,
    label: 'Subtítulo del Post',
    icon: 'mdi-subtitles',
    type: 'text',
    required: false,
    rules: []
  },
  {
    model: 'contenido' as keyof PostForm,
    label: 'Contenido del Post *',
    icon: 'mdi-message-text',
    type: 'textarea',
    rows: 6,
    required: true,
    rules: [(v: any) => !!v || 'El contenido es obligatorio']
  },
  {
    model: 'urlPost' as keyof PostForm,
    label: 'Dirección Web',
    icon: 'mdi-link',
    type: 'text',
    required: false,
    rules: []
  }
];


/********************************/
/* SUBMISSION STATE */
/********************************/
const postForm = ref();
const isSubmitting = ref<boolean>(false);
const message = ref<{ show: boolean; type?: 'error' | 'info' | 'success' | 'warning'; text: string }>({
  show: false,
  type: 'success',
  text: ''
});

// Handle form submission
const handleSubmit = async () => {
  isSubmitting.value = true;
  
  const { valid } = await postForm.value?.validate();
  if (!valid) {
    isSubmitting.value = false;
    return;
  }

  await addPost();

  isSubmitting.value = false;
};  

// Add new post
const addPost = async () => {
  try {
    const response = await useApiFetch(API.POSTS.ADD, {
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

    resetFormData();
  } catch (error: any) {
    message.value = { 
      show: true, 
      type: 'error' as const, 
      text: `Error al agregar la publicación: ${error.message || 'Error desconocido'}` 
    };
  }
};

// Reset form data
const resetFormData = () => {
  formData.value = {
    titulo: "",
    subtitulo: "",
    contenido: "",
    urlPost: "",
  };
};
</script>
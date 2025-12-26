<template>
  <v-dialog
    v-model="showDialog"
    max-width="65%"
    opacity="60%"
    class="post-form"
  >
    <v-card class="px-10 py-6">
      <v-card-title class="text-h5 text-primary pa-0 pb-3">
        {{ isEdit ? 'Editar' : 'Agregar' }} Publicación
      </v-card-title>
      <v-divider></v-divider>

      <v-card-text>
        <v-form 
          id="postForm"
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
          id="postForm"
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
  post: {
    type: Object as PropType<Post | null>,
    required: false,
    default: null
  }
});

const emit = defineEmits<{
  (e: 'update:show', value: boolean): void;
  (e: 'get:posts'): void;
}>();

// Show dialog computed property
const showDialog = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value)
});


/*************************************/
/* FORM DATA BINDING + INPUTS */
/*************************************/
// Post Form
const postForm = ref({
  titulo: '',
  subtitulo: '',
  contenido: '',
  urlPost: '',
});

// Watch for dialog open and populate form if editing
watch(() => props.show, (newValue) => {
  if (newValue) {
    if (props.isEdit && props.post) {
      postForm.value = {
          titulo: props.post.titulo || '',
          subtitulo: props.post.subtitulo || '',
          contenido: props.post.contenido || '',
          urlPost: props.post.urlPost || '',
      };
    } else {
      // Reset form for new recipe
      postForm.value = {
        titulo: '',
        subtitulo: '',
        contenido: '',
        urlPost: '',
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
      get: () => postForm.value.titulo,
      set: (val) => postForm.value.titulo = val
    }),
    rules: validationRules.title
  },
  {
    label: 'Subtítulo *',
    type: 'text',
    col: '12',
    model: computed({
      get: () => postForm.value.subtitulo,
      set: (val) => postForm.value.subtitulo = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Contenido *',
    type: 'text',
    col: '12',
    model: computed({
      get: () => postForm.value.contenido,
      set: (val) => postForm.value.contenido = val
    }),
    rules: validationRules.text
  },
  {
    label: 'Imagen URL *',
    type: 'text',
    col: '12',
    model: computed({
      get: () => postForm.value.urlPost,
      set: (val) => postForm.value.urlPost = val
    }),
    rules: validationRules.required
  },
]);


/*************************************/
/* FORM VALIDATION METHOD */
/*************************************/
const formRef = ref();

// Update recipe
const updatePost = async () => {
  try {
    const url = API.POSTS.UPDATE + `?idPost=${props.post?.idPost}`;
    const response = await useApiFetch(url, {
      method: 'PUT',
      body: postForm.value
    });
    
    if (response) {
      showSuccess(`Publicación ${postForm.value.titulo} editada correctamente`);
      emit('get:posts');
    }
  } catch (error: any) {
    showError(`Error al editar la publicación: ${error.message || error}`);
  }
};

// Add new post
const addPost = async () => {
  try {
    const response = await useApiFetch(API.POSTS.ADD, {
      method: 'POST',
      body: postForm.value
    });
    
    if (response) {
      showSuccess(`Publicación ${postForm.value.titulo} agregada correctamente`);
      emit('get:posts');
    }
  } catch (error: any) {
    showError(`Error al agregar la publicación: ${error.message || error}`);
  }
};

// Validate form and decide to add or update
const validateForm = async () => {
  const { valid } = await formRef.value?.validate();
  if (!valid) return;

  if (props.isEdit && props.post) updatePost();
  else addPost();  
};
</script>


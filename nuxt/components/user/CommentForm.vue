<template>
  <v-dialog
    v-model="dialog"
    max-width="60%"
    opacity="60%"
    class="comment-form"
  >
    <v-card v-if="copyComment" class="px-10 py-6">
      <v-card-title class="comment-form__title pa-0 pb-3">
        Editar comentario
      </v-card-title>
      <v-divider></v-divider>

      <v-card-text class="pa-0 my-4">
        <v-form 
          ref="formRef" 
          validate-on="submit"
          class="d-flex flex-column"
        >

          <!-- RATING ------------------------->
          <v-rating
            class="align-self-end"
            color="primary"
            size="small"
            density="compact"
            v-model="copyComment.valoracion"
            :rules="[ratingRule]"
          ></v-rating>
          <!----------------------------------->

          <!-- TITLE--------------------------->
          <v-text-field
            required
            hide-details="auto"
            type="text"
            density="comfortable"
            variant="outlined"
            class="mb-5 mt-3"
            color="darkgray"
            base-color="darkgray"
            label="Escribe un título de al menos 6 palabras *"
            v-model="copyComment.titulo"
            :rules="[titleRule]"
          ></v-text-field>
          <!----------------------------------->

          <!-- TEXT --------------------------->
          <v-textarea
            required
            no-resize
            persistent-counter
            hide-details="auto"
            class="mb-2"
            color="darkgray"
            base-color="darkgray"
            variant="outlined"
            density="comfortable"
            label="Escribe un comentario de al menos 200 caracteres *"
            :counter="300"
            v-model="copyComment.contenido"
            :rules="[textRule]"
          ></v-textarea>
          <!----------------------------------->

          <small class="text-caption text-medium-emphasis">*Campo obligatorio</small>
        </v-form>
        <v-alert 
          v-if="errorMessage" 
          type="error" 
          class="mb-4"
          density="compact"
        >
          {{ errorMessage }}
        </v-alert>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions class="pt-4">
        <v-btn
          text="Cancelar"
          variant="plain"
          @click="dialog = false"
        ></v-btn>
        <v-btn
          type="submit"
          color="primary"
          text="Actualizar comentario"
          variant="flat"
          @click.prevent="editComment"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
const { showError } = useNotification();

const props = defineProps<{
  show: boolean;
  comment: Comentario | null;
}>();

const emit = defineEmits<{
  (e: 'close'): void;
}>();

const dialog = computed<boolean>({
  get: () => props.show,
  set: (value: boolean) => emit('close'),
});

const copyComment = ref<Comentario | null>(null);

// Watch for changes in the comment prop
watch(() => props.comment, (newComment) => {
  if (newComment) {
    copyComment.value = { ...newComment };
  }
}, { immediate: true });


/******************************/
/* FORM DATA & VALIDATION */
/******************************/
const formRef = ref<HTMLFormElement | null>(null);
const errorMessage = ref<string | null>(null);

// Validation rating rule at least 1 star
const ratingRule = (value: number) => {
  if (value < 1) return 'Debes seleccionar al menos 1 estrella';
  return true;
};

// Validation title rule at least 6 words
const titleRule = (value: string) => {
  const wordCount = value.trim().split(/\s+/).filter(word => word.length > 0).length;
  if (wordCount < 6) return 'El título debe tener al menos 6 palabras';
  return true;
};

// Validation text rule at least 200 characters
const textRule = (value: string) => {
  if (value.trim().length < 200) return 'El texto debe tener al menos 200 caracteres';
  return true;
};


/******************************/
/* ADD COMMENT FUNCTION */
/******************************/
const editComment = async () => {
  const { valid } = await formRef.value?.validate();
  
  if (!valid) return;
  
  try {
    const response = await useApiFetch(API.COMMENTS.UPDATE, {
      method: 'PUT',
      params: {
        idComentario: copyComment.value?.idComentario,
      },
      body: {
        valoracion: copyComment.value?.valoracion,
        titulo: copyComment.value?.titulo,
        contenido: copyComment.value?.contenido,
        subtitulo: '',
        comentarioUrl: '',
        idObjetoComentado: copyComment.value?.idObjetoComentado,
      }
  });

  if (response) dialog.value = false;
  } catch (error) {
    showError('No se ha podido publicar el comentario');
  }
};
</script>

<style scoped lang="scss">
.comment-form {
  &__title {
    font-weight: 600;
    font-size: 1.5rem;
    color: #836A02;
  }
}
</style>
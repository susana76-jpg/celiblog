<template>
  <v-dialog
    v-model="dialog"
    max-width="60%"
    opacity="60%"
    class="comment-form"
  >
    <v-card class="px-10 py-6">
      <v-card-title class="comment-form__title pa-0 pb-3">
        Escribe tu comentario
      </v-card-title>
      <v-divider></v-divider>
      <v-card-text class="pa-0 my-4">
        <v-row dense no-gutters>
          <v-col cols="12" class="d-flex justify-end" >
            <v-rating
              class="ml-auto"
              color="primary"
              size="small"
              density="compact"
              :model-value="comment.rating"
            ></v-rating>
          </v-col>
          <v-col cols="12">
            <v-text-field
              active
              required
              hide-details
              type="text"
              density="comfortable"
              variant="outlined"
              class="mb-5 mt-3"
              color="darkgray"
              base-color="darkgray"
              label="Escribe un título para tu comentario *"
              :model-value="comment.title"
            ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-textarea
                active
                required
                no-resize
                hide-details
                persistent-counter
                class="mb-2"
                color="darkgray"
                base-color="darkgray"
                variant="outlined"
                density="comfortable"
                label="Escribe el texto de tu comentario *"
                :counter="300"
                :model-value="comment.text"
              ></v-textarea>
            </v-col>
          </v-row>

          <small class="text-caption text-medium-emphasis">*Campo obligatorio</small>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            text="Cancelar"
            variant="plain"
            @click="dialog = false"
          ></v-btn>

          <v-btn
            color="primary"
            text="Publicar comentario"
            variant="flat"
            @click="dialog = false"
          ></v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script setup lang="ts">
const props = defineProps<{
  show: boolean;
}>();

const emit = defineEmits<{
  (e: 'close'): void;
}>();

const dialog = computed<boolean>({
  get: () => props.show,
  set: (value: boolean) => emit('close'),
});

type Comment = {
  rating: number;
  title: string;
  text: string;
};

const comment = ref<Comment>({
  rating: 0,
  title: '',
  text: '',
});
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
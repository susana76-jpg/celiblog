<template>
  <v-row>
    <v-col cols="12">
      <v-text-field
        v-model="formData.titulo"
        label="Título del consejo"
        variant="outlined"
        required
      />
    </v-col>
    <v-col cols="12">
      <v-textarea
        v-model="formData.contenido"
        label="Contenido"
        variant="outlined"
        rows="6"
        required
      />
    </v-col>
    <v-col cols="12">
      <v-select
        v-model="formData.categoria"
        :items="tipCategories"
        label="Categoría"
        variant="outlined"
        required
      />
    </v-col>
    <v-col cols="12">
      <v-text-field
        v-model="formData.imagen"
        label="URL de imagen"
        variant="outlined"
      />
    </v-col>
  </v-row>
</template>

<script setup lang="ts">
const props = defineProps({
  modelValue: {
    type: Object,
    required: true
  }
});

const emit = defineEmits(['update:modelValue']);

const formData = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
});

const tipCategories = ref([
  'Salud',
  'Nutrición',
  'Cocina',
  'Estilo de vida',
  'Ingredientes'
]);
</script>

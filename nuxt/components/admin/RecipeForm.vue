<template>
  <v-row>
    <v-col cols="12">
      <v-text-field
        v-model="formData.titulo"
        label="Título de la receta"
        variant="outlined"
        required
      />
    </v-col>
    <v-col cols="12">
      <v-textarea
        v-model="formData.descripcion"
        label="Descripción"
        variant="outlined"
        rows="3"
        required
      />
    </v-col>
    <v-col cols="12">
      <v-textarea
        v-model="formData.ingredientes"
        label="Ingredientes"
        variant="outlined"
        rows="4"
        hint="Separa cada ingrediente con una línea nueva"
        required
      />
    </v-col>
    <v-col cols="12">
      <v-textarea
        v-model="formData.instrucciones"
        label="Instrucciones"
        variant="outlined"
        rows="5"
        required
      />
    </v-col>
    <v-col cols="6">
      <v-text-field
        v-model="formData.tiempoPreparacion"
        label="Tiempo de preparación (min)"
        type="number"
        variant="outlined"
      />
    </v-col>
    <v-col cols="6">
      <v-text-field
        v-model="formData.porciones"
        label="Porciones"
        type="number"
        variant="outlined"
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
</script>

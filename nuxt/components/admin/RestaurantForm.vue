<template>
  <v-row>
    <v-col cols="12">
      <v-text-field
        v-model="formData.nombre"
        label="Nombre del restaurante"
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
      <v-text-field
        v-model="formData.direccion"
        label="Dirección"
        variant="outlined"
        required
      />
    </v-col>
    <v-col cols="6">
      <v-text-field
        v-model="formData.telefono"
        label="Teléfono"
        variant="outlined"
      />
    </v-col>
    <v-col cols="6">
      <v-text-field
        v-model="formData.precio"
        label="Rango de precio"
        variant="outlined"
        placeholder="€€"
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

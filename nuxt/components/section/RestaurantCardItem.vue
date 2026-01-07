<template>
  <SectionCardItem
    :item="item"
    image-folder="restaurantes"
    link-path="/restaurantes/"
    id-field="idRestaurante"
    title-field="nombre"
    description-field="descripcion"
    image-field="imagenUrl"
    chip-type="info"
    favorite-type="RESTAURANTE"
    success-message="Restaurante añadido a favoritos"
    remove-message="Restaurante eliminado de favoritos"
    @update:item="$emit('update:item', $event)"
  />
</template>

<script setup lang="ts">
defineProps<{
  item: Restaurante;
}>();

defineEmits<{
  (e: 'update:item', value: Restaurante): void;
}>();
</script>

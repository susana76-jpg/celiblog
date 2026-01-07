<template>
  <SectionCardItem
    :item="item"
    image-folder="recetas"
    link-path="/recetas/"
    id-field="idReceta"
    title-field="titulo"
    description-field="subtitulo"
    image-field="imagenUrl"
    chip-type="difficulty"
    favorite-type="RECETA"
    success-message="Receta añadida a favoritos"
    remove-message="Receta eliminada de favoritos"
    @update:item="$emit('update:item', $event)"
  />
</template>

<script setup lang="ts">
defineProps<{
  item: Receta;
}>();

defineEmits<{
  (e: 'update:item', value: Receta): void;
}>();
</script>

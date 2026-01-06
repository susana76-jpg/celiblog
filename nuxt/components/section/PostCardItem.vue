<template>
  <SectionCardItem
    :item="item"
    image-folder=""
    link-path="/consejos/"
    id-field="idPost"
    title-field="titulo"
    description-field="subtitulo"
    image-field="urlPost"
    chip-type="info"
    favorite-type="POST"
    success-message="Post añadido a favoritos"
    remove-message="Post eliminado de favoritos"
    @update:item="$emit('update:item', $event)"
  />
</template>

<script setup lang="ts">
defineProps<{
  item: Post;
}>();

defineEmits<{
  (e: 'update:item', value: Post): void;
}>();
</script>

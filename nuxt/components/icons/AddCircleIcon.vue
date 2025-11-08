<template>
  <div class="add-circle-icon">
    <img :src="iconSrc" alt="Add circle icon" />
  </div>
</template>

<script setup lang="ts">
const iconSrc = "https://www.figma.com/api/mcp/asset/80aefa79-29f3-4259-9667-dde79c3493b2"

defineExpose({})
</script>

<style scoped lang="scss">
.add-circle-icon {
  width: 20px;
  height: 20px;
  padding: 8.33%;
  img {
    width: 100%;
    height: 100%;
    object-fit: contain;
  }
}
</style>
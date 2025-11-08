<template>
  <div class="person-icon">
    <img :src="iconSrc" alt="Person icon" />
  </div>
</template>

<script setup lang="ts">
const iconSrc = "https://www.figma.com/api/mcp/asset/e489d49d-d52b-44cb-a58b-fc66db725b05"

defineExpose({})
</script>

<style scoped lang="scss">
.person-icon {
  width: 20px;
  height: 20px;
  padding: 16.67%;
  img {
    width: 100%;
    height: 100%;
    object-fit: contain;
  }
}
</style>
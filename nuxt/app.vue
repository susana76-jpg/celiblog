<template>
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>

<script setup lang="ts">
const authStore = useAuthStore();

if (import.meta.client) {
  window.addEventListener('beforeunload', () => {
    authStore.logout();
  });
}
</script>
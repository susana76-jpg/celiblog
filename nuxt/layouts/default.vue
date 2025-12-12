<template>
  <v-app id="app">
    <Menu />
    <v-main>
      <slot />
    </v-main>
    <Footer />
    
    <!-- Global Notification Snackbar -->
    <v-snackbar
      v-model="notification.show"
      :color="notification.color"
      :timeout="5000"
      location="top right"
    >
      {{ notification.message }}
      <template v-slot:actions>
        <v-btn
          variant="text"
          @click="hideNotification"
        >
          Cerrar
        </v-btn>
      </template>
    </v-snackbar>
  </v-app>
</template>

<script lang="ts" setup>
const { notification, hideNotification } = useNotification();
</script>

<style lang="scss" scoped>
.v-main {
  background-color: #FFF;
  min-height: calc(100% - 146px); 
}
</style>
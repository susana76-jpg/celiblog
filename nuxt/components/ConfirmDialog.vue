<template>
  <v-dialog
    v-model="isOpen"
    max-width="60%"
    min-height="300px"
    opacity="60%"
    class="comment-form"
  >
    <v-card class="px-10 py-6">
      <v-card-text class="pa-0 mt-6 mb-10 d-flex flex-column align-center justify-center">
        <v-icon size="106" color="primary" class="mb-2">mdi-alert-circle-outline</v-icon>
        <h2 class="mb-2 text-primary">¿Estás seguro/a?</h2>
        <p class="text-center">{{ message }}</p>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions class="pt-4">
        <v-btn
          text="Cancelar"
          variant="plain"
          @click="closeDialog"
        ></v-btn>
        <v-btn
          type="submit"
          color="primary"
          text="Confirmar"
          variant="flat"
          @click="confirm"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
const { isOpen, message, confirm, closeDialog } = useConfirmDialog();
</script>

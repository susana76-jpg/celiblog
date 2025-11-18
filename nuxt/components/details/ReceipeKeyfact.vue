<template>
  <div class="receipe-keyfact">
    <div class="keyfact-item">
      <v-icon>mdi-account-outline</v-icon>
      <p>{{ receipe.comensales }} personas</p>
    </div>
    <div class="keyfact-item">
      <v-icon>mdi-timer-outline</v-icon>
      <p>{{ receipe.tiempoPreparacion }} minutos</p>
    </div>
    <div class="keyfact-item">
      <v-icon>mdi-alert-circle-outline</v-icon>
      <p>{{ receipe.calorias }} kcal</p>
    </div>
    <div class="keyfact-item">
      <v-icon>mdi-food-outline</v-icon>
      <p>{{ receipe.tipo }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
const props = defineProps<{
  receipe: any;
}>();
</script>

<style lang="scss" scoped>
.receipe-keyfact {
  display: flex;
  justify-content: flex-end;
  gap: 20px;

  .keyfact-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    color: #FFF;
    background-color: #836A02;
    padding: 10px;
    border-radius: 4px;
    width: 110px;
    height: 110px;

    i {
      font-size: 46px;
    }

    p {
      font-family: 'Poppins', sans-serif;
      font-size: 16px;
      font-weight: 400;
    }
  }
}
</style>
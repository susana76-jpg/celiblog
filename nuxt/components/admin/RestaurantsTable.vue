<template>
  <div class="restaurants-table-container">
    <div class="placeholder-content">
      <v-icon size="80" color="#836a02">mdi-silverware-fork-knife</v-icon>
      <h2>Gestión de Restaurantes</h2>
      <p>Esta sección está en desarrollo</p>
    </div>
  </div>
</template>

<script setup lang="ts">
// Component logic here
</script>

<style lang="scss" scoped>
.restaurants-table-container {
  padding: 60px 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
}

.placeholder-content {
  text-align: center;
  color: #836a02;

  h2 {
    margin-top: 20px;
    font-size: 32px;
    font-weight: 600;
  }

  p {
    margin-top: 10px;
    font-size: 18px;
    color: #616161;
  }
}
</style>

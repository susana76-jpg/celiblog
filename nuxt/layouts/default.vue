<template>
  <v-app id="app">
    <Menu />
    <v-main>
      <slot />
    </v-main>
    <Footer />
  </v-app>
</template>

<script lang="ts" setup>

</script>

<style lang="scss" scoped>
.v-main {
  background-color: #FFF;
  min-height: calc(100% - 146px); 
}
</style>
<template>
  <v-app>
    <v-layout>
      <Menu />
      <v-main>
        <slot />
      </v-main>
    </v-layout>
  </v-app>
</template>

<script lang="ts" setup>

</script>

<style lang="scss">
.v-layout {
  flex-direction: column;

  .v-main {
    padding-top: 0;
  }
}
</style>
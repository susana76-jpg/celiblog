<template>
  <v-footer class="pa-0 flex-grow-0">
    <div class="footer-content">
      <p>@2025Celiblog. Todos los derechos reservados.</p>
      <v-img 
        width="100px"
        src="/img/celiblog_branch.png" 
        alt="Footer logo" 
      />
    </div>
  </v-footer>
</template>
<style lang="scss" scoped>
.footer-content {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px;
  background-color: #b6b6b6;

  p {
    font-size: 14px;
  }

}
</style>
<template>
  <div class="loader-container-details">
    <div class="loader-content">

      <!-- HERO IMAGE SKELETON ---------------------------------->
      <v-skeleton-loader 
        type="image" 
        class="loader-hero-skeleton" 
        height="360" 
      />
      <!-------------------------------------------------------->

      <!-- MAIN CONTENT SKELETON -------------------------------->
      <div class="loader-main">
        <v-skeleton-loader type="chip@3" class="d-flex justify-end mb-8" />
        <v-skeleton-loader type="heading, subtitle" class="mb-6" />
        <v-skeleton-loader type="paragraph@3" class="mb-10" />
        <v-skeleton-loader type="article" />
      </div>
      <!-------------------------------------------------------->
      
    </div>
  </div>
</template>

<style lang="scss">
.loader-container-details {
  animation: fadeIn 0.3s ease-in;
    
  .loader-content {
    width: 100%;
      
    .loader-hero-skeleton {
      width: 100%;
      margin-bottom: 2rem;
      border-radius: 8px;

      .v-skeleton-loader__bone {
        height: 100%;
      }
    }
      
    .loader-main {
      width: 100%;
      padding: 0 120px;
      margin-bottom: 36px;
    }
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>

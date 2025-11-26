<template>
  <section class="section-page consejos-page"> 

    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      title="Consejos"
      image-alt="consejos para vivir sin gluten"
      subtitle="Te ofrecemos orientación sencilla y útil para vivir sin gluten sin complicaciones. Aprende trucos, buenas prácticas y sugerencias para cocinar, comprar y comer fuera con total confianza."
      :image-src="img"
    />
    <!------------------------------------------->

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Para cuidarte mejor</h2>
        <p>Descubre sugerencias simples y efectivas para evitar el gluten en tu día a día. Pequeños cambios que pueden marcar una gran diferencia en tu bienestar.</p>
      </div>
      <SectionFilterBar />
      <v-row no-gutters class="section-main__content">
        <v-col
          v-for="item in restaurants"
          :key="item.id"
          cols="12"
          md="4"
          xl="3"
        >
          <SectionTipCardItem 
            :item="item" 
          />
        </v-col>
      </v-row>
    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const img = '/img/consejos/hero-image.jpg';
const restaurants = [...restaurantes];
restaurants.forEach((restaurant, i) => restaurant.imagenUrl = '/img/consejos/consejo' + (i + 1) + '.jpg');

</script>
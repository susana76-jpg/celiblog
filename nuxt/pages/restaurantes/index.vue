<template>
  <section class="section-page restaurantes-page"> 

    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      title="Restaurantes"
      image-alt="Agradable restaurante"
      subtitle="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam egestas neque nec fringilla finibus. Quisque fringilla odio turpis. Nam eget tincidunt metus."
      :image-src="img"
    />
    <!------------------------------------------->

    <RestaurantesMapaIndex :restaurantes="restaurants" />

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Lorem ipsum dolor sit amet</h2>
        <p>Cras risus risus, accumsan lacinia imperdiet id, varius sed mi. Fusce accumsan nec odio nec consequat.</p>
      </div>
      
      <v-row no-gutters class="section-main__content">
        <v-col
          v-for="item in restaurants"
          :key="item.id"
          cols="12"
          md="4"
          xl="3"
        >
          <SectionCardItem 
            :item="item" 
            type="restaurantes"
          >
            <template #chip="{ item, setChipClass }">
              <v-chip 
                class="card-image__chip bg-success text-white"
                variant="outlined"
              >
                <v-icon left>mdi-plus</v-icon>
                Información
              </v-chip>
            </template>
          </SectionCardItem>
        </v-col>
      </v-row>
    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const img = '/img/restaurant-hero-image.jpg';
const restaurants = restaurantes;
console.log(restaurants);

</script>

<style lang="scss" scoped>
#map { 
  height: 400px;
}

:deep(.custom-marker) {
  background: none;
  border: none;
}
</style>
<template>
  <section class="section-page recetas-page"> 

    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      image-alt="Delicious food"
      title="Recetas"
      subtitle="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam egestas neque nec fringilla finibus. Quisque fringilla odio turpis. Nam eget tincidunt metus."
      :image-src="img"
    />
    <!------------------------------------------->

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Lorem ipsum dolor sit amet</h2>
        <p>Cras risus risus, accumsan lacinia imperdiet id, varius sed mi. Fusce accumsan nec odio nec consequat.</p>
      </div>
      <SectionFilterBar />
      <v-row no-gutters class="section-main__content">
        <v-col
          v-for="item in recetas"
          :key="item.idReceta"
          cols="12"
          md="4"
          xl="3"
        >
          <SectionCardItem :item="item" />
        </v-col>
      </v-row>
    </div>
    <!------------------------------------------->

  </section>
</template>

<script setup lang="ts">
const img = 'https://www.figma.com/api/mcp/asset/26902a0d-e471-4f93-a9fe-7c195740016c';
import { recetas } from '../../utils/dummy';
</script>

<style lang="scss" scoped>
</style>

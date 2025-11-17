<template>
  <section class="section-page"> 

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
          v-for="item in receipes"
          :key="item.id"
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
import { receipes } from '../../utils/dummy';
</script>

<style lang="scss" scoped>
.section-main {
  padding: 0 80px;
  margin-bottom: 60px;

  &__title {
    width: 80%;
    text-align: center;
    margin: 0 auto;
    padding-top: 50px;
    padding-bottom: 50px;

    h2 {
      font-family: 'Poppins', sans-serif;
      font-size: 22px;
      line-height: 22px;
      font-weight: 600;
      color: #242424;
      margin-bottom: 10px;
    }

    p {
      font-family: 'Arial', sans-serif;
      font-size: 32px;
      line-height: 26px;
      letter-spacing: -1px;
      color: #836a02;
      max-width: 70%;
      margin: 0 auto;
    }
  }
}
</style>

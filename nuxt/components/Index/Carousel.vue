<template>
  <section class="carousel-section">
    <div class="carousel-title">
      <h2>Lorem ipsum dolor sit amet</h2>
      <p>Cras risus risus, accumsan lacinia imperdiet id, varius sed mi. Fusce accumsan nec odio nec consequat.</p>
    </div>
    <v-carousel
      height="auto"
      hide-delimiters
      class="carrousel"
      show-arrows="hover"
    >
      <v-carousel-item
        v-for="n in Math.ceil(items.length / 4)"
        :key="n"
      >
        <v-row>
          <v-col
            v-for="item in items.slice((n-1)*4, n*4)"
            :key="item.id"
            cols="12"
            md="3"
          >
            <v-card
              :to="item.link"
              class="mx-2 pa-2"
              variant="text"
            >
              <v-img  
                cover
                height="400"
                :src="item.image"
              ></v-img>
              <v-card-title class="px-0">
                {{ item.title }}
              </v-card-title>
              <v-card-text class="px-0">
                {{ item.description }}
              </v-card-text>
              <v-rating
                readonly
                half-increments
                size="small"
                color="primary"
                density="compact"
                :model-value="item.rating"
              ></v-rating>
            </v-card>
          </v-col>
        </v-row>
      </v-carousel-item>
    </v-carousel>
  </section>
</template>

<script setup lang="ts">
const props = defineProps<{
  items: Array<{
    id: number;
    title: string;
    description: string;
    image: string;
    link: string;
    rating: number;
    favorite?: boolean;
  }>;
}>();
</script>

<style lang="scss" scoped>
.carousel-section {
  padding: 4rem 80px;
  background-color: #FFF;

  .carousel-title {
    text-align: center;
    margin-bottom: 40px;

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

  .v-card {
    .v-card-title {
      font-family: 'Arial', sans-serif;
      font-size: 18px;
      line-height: 20px;
      font-weight: 600;
      color: #333333;
      padding-bottom: 6px;
    }

    .v-card-text {
      font-family: 'Poppins', sans-serif;
      font-size: 14px;
      line-height: 12px;
      color: #555555;
      padding-bottom: 8px;
    }
  }
}

.carousel-section:nth-child(3) {
  background-color: #f1f1f1;
}
</style>
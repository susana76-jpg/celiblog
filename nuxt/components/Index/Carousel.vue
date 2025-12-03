<template>
  <section class="carousel-section">
    <div class="carousel-title">
      <h2>{{ title }}</h2>
      <p>{{ subtitle }}</p>
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
            v-for="(item, i) in items.slice((n-1)*4, n*4)"
            :key="item.idReceta"
            cols="12"
            md="3"
          >
            <v-card
              :to="setItemLink(item.idReceta, type)"
              class="mx-2 pa-2"
              variant="text"
            >
              <v-img  
                cover
                height="400"
                :src="item.imagenUrl"
              ></v-img>
              <v-card-title class="px-0">
                {{ item.titulo }}
              </v-card-title>
              <v-card-text class="px-0">
                {{ item.subtitulo }}
              </v-card-text>
              <v-rating
                readonly
                half-increments
                color="primary"
                density="compact"
                :model-value="item.valoracion"
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
  title: string;
  subtitle: string;
  items: Receta[];
  type: 'recetas' | 'consejos';
}>();

// Set link for each item based on its type
const setItemLink = (id: number, type: 'recetas' | 'consejos') => {
  if (type === 'recetas') return `/recetas/${id}`;
  else if (type === 'consejos') return `/consejos/${id}`;
  return '/';
};
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
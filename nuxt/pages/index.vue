<template>
  <div class="index-page">
    <section class="hero-header">
      <div class="hero-image">
        <img :src="imgHeroImage" alt="Hero background" />
      </div>
      <div class="hero-content">
        <h1>Lorem ipsum dolor</h1>
        <p class="hero-description">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam egestas neque nec fringilla finibus. 
          Quisque fringilla odio turpis. Nam eget tincidunt metus. Sed ornare aliquet tortor, id viverra mi 
          vehicula at. Nullam volutpat turpis a aliquam semper. In dignissim sapien at sapien dignissim 
          porttitor. Proin placerat lacus a luctus consequat. Vestibulum vehicula scelerisque sollicitudin.
        </p>
        <div class="hero-buttons">
          <button class="contact-btn">
            <span>CONTACTO</span>
            <img :src="img10" alt="Contact icon" />
          </button>
          <button class="learn-more-btn">
            <span>SABER MÁS</span>
            <img :src="img11" alt="Learn more icon" />
          </button>
        </div>
      </div>
    </section>

    <section class="carousel-section recipes">
      <div class="carousel-title">
        <h2>Lorem ipsum dolor sit amet</h2>
        <p>Cras risus risus, accumsan lacinia imperdiet id, varius sed mi. Fusce accumsan nec odio nec consequat.</p>
      </div>
      <div class="carousel-content">
        <button class="carousel-nav prev" @click="slidePrev('recipes')">
          <img :src="imgVector4" alt="Previous" />
        </button>
        <div class="carousel-items" ref="recipesCarousel">
          <div v-for="(img, index) in recipeImages" :key="index" class="carousel-item">
            <div class="item-image">
              <img :src="img" alt="Recipe" />
            </div>
            <h3>Lorem ipsum dolor sit amet</h3>
            <p>Pellentesque tincidunt erat ipsum, nec consequat.</p>
            <div class="rating">
              <img :src="img" alt="Rating" />
            </div>
            <div class="favorite">
              <img :src="img2" alt="Favorite" />
            </div>
          </div>
        </div>
        <button class="carousel-nav next" @click="slideNext('recipes')">
          <img :src="imgVector3" alt="Next" />
        </button>
      </div>
    </section>

    <section class="carousel-section tips">
      <div class="carousel-title">
        <h2>Lorem ipsum dolor sit amet</h2>
        <p>Cras risus risus, accumsan lacinia imperdiet id, varius sed mi. Fusce accumsan nec odio nec consequat.</p>
      </div>
      <div class="carousel-content">
        <button class="carousel-nav prev" @click="slidePrev('tips')">
          <img :src="imgVector2" alt="Previous" />
        </button>
        <div class="carousel-items" ref="tipsCarousel">
          <div v-for="(img, index) in tipImages" :key="index" class="carousel-item">
            <div class="item-image">
              <img :src="img" alt="Tip" />
            </div>
            <h3>Lorem ipsum dolor sit amet</h3>
            <p>Pellentesque tincidunt erat ipsum, nec consequat.</p>
            <div class="rating">
              <img :src="img" alt="Rating" />
            </div>
            <div class="favorite">
              <img :src="img2" alt="Favorite" />
            </div>
          </div>
        </div>
        <button class="carousel-nav next" @click="slideNext('tips')">
          <img :src="imgVector1" alt="Next" />
        </button>
      </div>
    </section>

    <footer class="main-footer">
      <div class="footer-content">
        <img :src="imgRectangle29" alt="Footer logo" />
        <p>@2025Celiblog. Todos los derechos reservados.</p>
      </div>
    </footer>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

// Image assets
const imgRectangle29 = "https://www.figma.com/api/mcp/asset/0c85f634-de85-43b7-9d63-923fd5d7323f"
const imgVector = "https://www.figma.com/api/mcp/asset/e4a8e117-6fc1-45d7-873f-42752a59a072"
const imgHeroImage = "https://www.figma.com/api/mcp/asset/eb1361d2-f157-4c17-98c4-0f58bca1686b"
const img = "https://www.figma.com/api/mcp/asset/e20fab5c-a9db-4ea3-8691-799c1e68dd04"
const img2 = "https://www.figma.com/api/mcp/asset/f57d8d7d-6278-4c70-b14f-edbe1571c61b"
const imgVector1 = "https://www.figma.com/api/mcp/asset/eb996084-42d7-4c29-907d-48c7c38a7671"
const imgVector2 = "https://www.figma.com/api/mcp/asset/291859be-5027-4143-9ecf-a71567ce6828"
const imgVector3 = "https://www.figma.com/api/mcp/asset/2107b010-4c44-4d8d-8a5f-69033f2302a6"
const imgVector4 = "https://www.figma.com/api/mcp/asset/46280de3-5984-4ef8-b5b8-d7d6a8a46ce8"
const img10 = "https://www.figma.com/api/mcp/asset/e7706f0e-5cd2-478b-bcb0-b4d6c52f7118"
const img11 = "https://www.figma.com/api/mcp/asset/e5abd5b5-1421-43f7-8d4e-6c554c5bc253"

// Carousel images
const recipeImages = [
  "https://www.figma.com/api/mcp/asset/cf0eb22f-86ec-4b91-a4a1-b4f0f568c3ad",
  "https://www.figma.com/api/mcp/asset/753e43eb-83c7-403d-8446-9dfb0742def9",
  "https://www.figma.com/api/mcp/asset/4c4f4ee5-e26c-41aa-a70b-3556d7098e3e",
  "https://www.figma.com/api/mcp/asset/d672bbe5-1ee6-4162-b424-90f33ef1fb22"
]

const tipImages = [
  "https://www.figma.com/api/mcp/asset/aec021c3-93af-4d85-b7e0-ddd837949c59",
  "https://www.figma.com/api/mcp/asset/4239d7b2-4ce9-431a-acde-c8d133467dc8",
  "https://www.figma.com/api/mcp/asset/7a6d80db-f9f0-4eb1-bffd-58cb220de80c",
  "https://www.figma.com/api/mcp/asset/fa7bccef-cc37-47d2-a9df-d7b528b72292"
]

// Carousel functionality
const recipesCarousel = ref<HTMLElement | null>(null)
const tipsCarousel = ref<HTMLElement | null>(null)

const slideNext = (type: 'recipes' | 'tips') => {
  const container = type === 'recipes' ? recipesCarousel.value : tipsCarousel.value
  if (container) {
    container.scrollLeft += container.offsetWidth
  }
}

const slidePrev = (type: 'recipes' | 'tips') => {
  const container = type === 'recipes' ? recipesCarousel.value : tipsCarousel.value
  if (container) {
    container.scrollLeft -= container.offsetWidth
  }
}
</script>

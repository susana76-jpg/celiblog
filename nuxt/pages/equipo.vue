<template>
  <div class="section-page team-page">
    
    <!-- HERO IMAGE ----------------------------->
    <SectionHeroImage
      title="Nuestro equipo"
      image-alt="Agradable restaurante"
      subtitle="Conoce al grupo de especialistas, cocineros y personas celíacas que dan vida a este blog. Trabajamos juntos para ofrecer información fiable, recetas seguras y apoyo real a quienes viven sin gluten cada día."
      :image-src="img"
    />
    <!------------------------------------------->
    
    <!-- MAIN SECTION --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Quienes hacen Celiblog posible</h2>
        <p>Conoce al equipo que impulsa este blog: personas especializadas y comprometidas con el bienestar de la comunidad celíaca. Trabajamos para ofrecer contenido fiable, apoyo cercano y una experiencia segura en tu vida sin gluten.</p>
      </div>

      <!-- Team Members Section -->
      <div class="team-page__members">
        <v-container>
          <v-row>
            <v-col
              v-for="(member, index) in teamMembers"
              :key="index"
              cols="12"
              md="4"
            >
              <v-card 
                elevation="0"
                rounded="md"
              >
                <v-img
                  cover
                  height="600"
                  :src="member.image"
                  :alt="member.name"
                />
                
                <v-card-text class="team-page__member-info">
                  <h3>{{ member.name }}</h3>
                  <p>{{ member.description }}</p>
                  
                  <div class="team-page__member-links">
                    <v-btn
                      v-for="(link, linkIndex) in member.links"
                      :key="linkIndex"
                      :href="link.url"
                      target="_blank"
                      icon
                      size="40"
                      variant="text"
                      color="secondary"
                      class="team-page__member-link"
                    >
                      <v-icon :icon="link.icon" size="32"></v-icon>
                    </v-btn>
                  </div>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </div>

      <!-- Bottom Text Section -->
      <div class="team-page__bottom-text">
        <h2 class="team-page__bottom-title">Apasionadas. Proactivas. Expertas</h2>
        <p class="team-page__bottom-description">
          Somos un equipo comprometido con la comunidad celíaca, formado por especialistas, nutricionistas, cocineros y colaboradores que trabajan para ofrecer información fiable, recetas seguras y apoyo práctico en la vida sin gluten. Cada miembro aporta su experiencia y entusiasmo, asegurando que cada contenido sea útil, claro y cercano. Nuestra misión es acompañarte en tu día a día, inspirar confianza y facilitar la búsqueda de soluciones seguras para disfrutar de la alimentación sin gluten. Desde la creación de recetas hasta la selección de productos y la atención a las necesidades de nuestra comunidad, cada proyecto refleja nuestra pasión por la calidad, la seguridad y el bienestar.
        </p>
      </div>
    </div>
    <!------------------------------------------->
    
  </div>
</template>

<script setup lang="ts">
interface TeamMember {
  name: string;
  description: string;
  image: string;
  links: {
    icon: string;
    url: string;
  }[];
}

const img = '/img/equipo/hero-image.jpg';

const teamMembers = ref<TeamMember[]>([
  {
    name: 'Susana Rico Vara',
    description: 'Apasionada por la alimentación saludable, Susana guía a la comunidad celíaca con consejos prácticos y recetas seguras. Su experiencia en nutrición garantiza información fiable, ayudando a disfrutar de comidas deliciosas sin gluten y cuidando siempre el bienestar diario..',
    image: '/img/equipo/susana.jpg',
    links: [
      { icon: 'fa:fas fa-square-envelope', url: 'mailto:member3@celiblog.com' },
      { icon: 'fa:fab fa-square-github', url: 'https://github.com' },
      { icon: 'fa:fab fa-linkedin', url: 'https://linkedin.com' }
    ]
  },
  {
    name: 'Maite Rodríguez Jurado',
    description: 'Maite transforma ingredientes sin gluten en platos llenos de sabor y originalidad. Su pasión por la cocina se refleja en recetas innovadoras que combinan tradición y creatividad, ofreciendo opciones seguras y deliciosas para quienes viven una vida libre de gluten.',
    image: '/img/equipo/maite.jpg',
    links: [
      { icon: 'fa:fas fa-square-envelope', url: 'mailto:member3@celiblog.com' },
      { icon: 'fa:fab fa-square-github', url: 'https://github.com' },
      { icon: 'fa:fab fa-linkedin', url: 'https://linkedin.com' }
    ]
  },
  {
    name: 'Nuria Martínez Rodrigo',
    description: 'Nuria conecta con la comunidad celíaca a través de contenidos claros y útiles. Coordina proyectos, comparte experiencias y se asegura de que cada artículo, receta y guía sea accesible, inspirando confianza y haciendo la vida sin gluten más sencilla y disfrutable.',
    image: '/img/equipo/nuria.jpg',
    links: [
      { icon: 'fa:fas fa-square-envelope', url: 'mailto:member3@celiblog.com' },
      { icon: 'fa:fab fa-square-github', url: 'https://github.com' },
      { icon: 'fa:fab fa-linkedin', url: 'https://linkedin.com' }
    ]
  }
]);
</script>

<style lang="scss" scoped>
@import '@/assets/styles/variables';

.team-page {

  // Team Members Section
  &__members {
    padding: 0 0 86px;

    .v-card {
      padding: 0 20px 0;

      .v-card-text {
        padding: 0;
        padding-top: $spacing-md;
        text-align: center;

        h3 {
          font-size: 1.5rem;
          font-weight: 600;
          letter-spacing: 0.08px;
          color: $secondary-color;
          margin-bottom: $spacing-xs;
        }

        p {
          line-height: 1.2rem;
          margin-bottom: $spacing-sm;
        }
      }

      &__member-links {
        display: flex;
        gap: 12px;
        justify-content: center;
      }
  }
  }

  // Bottom Text Section
  &__bottom-text {
    text-align: center;
    padding: 0 200px 80px;
    margin: 0 auto;
  }

  &__bottom-title {
    font-size: 2rem;
    font-weight: 500;
    color: $primary-color;
    line-height: 29px;
    letter-spacing: -1px;
    margin-bottom: $spacing-md;
  }

  // Responsive Design
  @media (max-width: 1264px) {
    &__bottom-text {
      padding: 0 $spacing-xl 96px;
    }
  }

  @media (max-width: 960px) {
    &__bottom-title {
      font-size: 28px;
    }

    &__bottom-description {
      font-size: 18px;
    }
  }

  @media (max-width: 600px) {
    &__members {
      padding: 0 0 64px;
    }

    &__bottom-text {
      padding: 0 $spacing-md 64px;
    }

    &__bottom-title {
      font-size: 24px;
    }

    &__bottom-description {
      font-size: 16px;
    }
  }
}
</style>

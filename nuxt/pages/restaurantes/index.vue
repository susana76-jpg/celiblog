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

    <!-- MAIN CONTENT --------------------------->
    <div class="section-main">
      <div class="section-main__title">
        <h2>Lorem ipsum dolor sit amet</h2>
        <p>Cras risus risus, accumsan lacinia imperdiet id, varius sed mi. Fusce accumsan nec odio nec consequat.</p>
      </div>
      <div id="map"></div>
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
const img = '/img/restaurant-hero-image.jpg';

const restaurants = [
  { id: 1, name: 'La Tagliatella', address: 'Calle de Serrano, 41', lat: 40.4235, lng: -3.6889 },
  { id: 2, name: 'Celicioso', address: 'Calle de Hortaleza, 3', lat: 40.4188, lng: -3.7025 },
  { id: 3, name: 'GF Gallery', address: 'Calle del Conde de Aranda, 11', lat: 40.4227, lng: -3.6844 },
  { id: 4, name: 'Honest Greens', address: 'Calle de Velázquez, 12', lat: 40.4241, lng: -3.6885 },
  { id: 5, name: 'Bāo', address: 'Plaza de Chueca, 3', lat: 40.4220, lng: -3.6968 },
  { id: 6, name: 'El Rincón de Goya', address: 'Calle de Goya, 34', lat: 40.4236, lng: -3.6805 },
  { id: 7, name: 'Viva Chapata', address: 'Calle de Fuencarral, 144', lat: 40.4305, lng: -3.7023 },
  { id: 8, name: 'La Violeta', address: 'Plaza de Canalejas, 6', lat: 40.4174, lng: -3.7020 },
  { id: 9, name: 'Chamberi', address: 'Calle de Alonso Cano, 10', lat: 40.4358, lng: -3.6968 },
  { id: 10, name: 'Lizarrán', address: 'Calle de Alcalá, 34', lat: 40.4188, lng: -3.6995 },
  { id: 11, name: 'Fresh & Go', address: 'Gran Vía, 28', lat: 40.4201, lng: -3.7066 },
  { id: 12, name: 'Makkila', address: 'Calle de Preciados, 17', lat: 40.4189, lng: -3.7057 }
];

onMounted(() => {
  // @ts-ignore - Leaflet is loaded via CDN
  const L = window.L;
  
  const map = L.map('map').setView([40.4168, -3.7038], 13);
  
  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(map);

  // Create custom icon with primary color
  const customIcon = L.divIcon({
    className: 'custom-marker',
    html: `<svg width="25" height="41" viewBox="0 0 25 41" xmlns="http://www.w3.org/2000/svg">
      <path d="M12.5 0C5.6 0 0 5.6 0 12.5c0 9.4 12.5 28.5 12.5 28.5S25 21.9 25 12.5C25 5.6 19.4 0 12.5 0z" fill="#836A02"/>
      <circle cx="12.5" cy="12.5" r="6" fill="white"/>
    </svg>`,
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [0, -41]
  });

  // Add markers for each restaurant
  restaurants.forEach(restaurant => {
    const marker = L.marker([restaurant.lat, restaurant.lng], { icon: customIcon })
      .addTo(map)
      .bindPopup(`
        <div class="restaurant-popup" style="cursor: pointer;">
          <b>${restaurant.name}</b><br>
          ${restaurant.address}
        </div>
      `);
    
    marker.on('popupopen', () => {
      const popup = document.querySelector('.restaurant-popup');
      if (popup) {
        popup.addEventListener('click', () => {
          navigateTo(`/restaurantes/${restaurant.id}`);
        });
      }
    });
  });
});
</script>

<style lang="scss" scoped>
#map { 
  border-radius: 6px;
  height: 320px;
}

:deep(.custom-marker) {
  background: none;
  border: none;
}
</style>
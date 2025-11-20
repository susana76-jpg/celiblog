<template>
  <div id="map"></div>
</template>

<script setup lang="ts">
const props = defineProps<{
  restaurantes: Array<{
    id: number;
    nombre: string;
    contacto: {
      direccion: string;
    };
    latitud: number;
    longitud: number;
  }>;
}>();


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
  props.restaurantes.forEach(restaurant => {
    const marker = L.marker([restaurant.latitud, restaurant.longitud], { icon: customIcon })
      .addTo(map)
      .bindPopup(`
        <div class="restaurant-popup" style="cursor: pointer;">
          <b>${restaurant.nombre}</b><br>
          ${restaurant.contacto.direccion}
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

<style scoped lang="scss">

</style>
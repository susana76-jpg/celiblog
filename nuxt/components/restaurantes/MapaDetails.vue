<template>
  <div id="map"></div>
</template>

<script setup lang="ts">
const props = defineProps<{
  restaurant: Restaurante;
}>();

let map: any = null;
let markersLayer: any = null;

onMounted(() => {
  // @ts-ignore - Leaflet is loaded via CDN
  const L = window.L;
  
  map = L.map('map', { scrollWheelZoom: false }).setView([40.4168, -3.7038], 18);
  
  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(map);

  // Create layer group for markers
  markersLayer = L.layerGroup().addTo(map);
  
  // Add initial markers if restaurants already loaded
  if (props.restaurant) {
    addMarkers();
  }
});

// Watch for restaurants data changes
watch(() => props.restaurant, (newRestaurant) => {
  if (newRestaurant && map && markersLayer) {
    addMarkers();
  }
}, { deep: true });

function addMarkers() {
  // @ts-ignore - Leaflet is loaded via CDN
  const L = window.L;
  
  // Clear existing markers
  markersLayer.clearLayers();

  // Center map on restaurant location with closer zoom
  map.setView([props.restaurant.latitud, props.restaurant.longitud], 18);
  
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
  const marker = L.marker([props.restaurant.latitud, props.restaurant.longitud], { icon: customIcon })
    .addTo(markersLayer)
    .bindPopup(`
      <div class="restaurant-popup" style="cursor: pointer;">
        <b>${props.restaurant.nombre}</b><br>
        ${props.restaurant.direccion}
      </div>
    `);
}
</script>

<style scoped lang="scss">
#map { 
  height: 440px;
  border-radius: 6px;
}
</style>
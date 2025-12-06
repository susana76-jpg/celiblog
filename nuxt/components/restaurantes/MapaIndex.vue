<template>
  <div id="map"></div>
</template>

<script setup lang="ts">
const props = defineProps<{
  restaurants: Restaurante[];
}>();

let map: any = null;
let markersLayer: any = null;

onMounted(() => {
  // @ts-ignore - Leaflet is loaded via CDN
  const L = window.L;
  
  map = L.map('map', { scrollWheelZoom: false }).setView([40.4168, -3.7038], 13);
  
  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(map);

  // Create layer group for markers
  markersLayer = L.layerGroup().addTo(map);
  
  // Add initial markers if restaurants already loaded
  if (props.restaurants.length > 0) {
    addMarkers();
  }
});

// Watch for restaurants data changes
watch(() => props.restaurants, (newRestaurants) => {
  if (newRestaurants.length > 0 && map && markersLayer) {
    addMarkers();
  }
}, { deep: true });

function addMarkers() {
  // @ts-ignore - Leaflet is loaded via CDN
  const L = window.L;
  
  // Clear existing markers
  markersLayer.clearLayers();
  
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

  // Collect all marker coordinates
  const bounds = L.latLngBounds();

  // Add markers for each restaurant
  props.restaurants.forEach(restaurant => {
    const latLng = L.latLng(restaurant.latitud, restaurant.longitud);
    bounds.extend(latLng);
    
    const marker = L.marker(latLng, { icon: customIcon })
      .addTo(markersLayer)
      .bindPopup(`
        <div class="restaurant-popup" style="cursor: pointer;">
          <b>${restaurant.nombre}</b><br>
          ${restaurant.direccion}
        </div>
      `);
    
    marker.on('popupopen', () => {
      const popup = document.querySelector('.restaurant-popup');
      if (popup) {
        popup.addEventListener('click', () => {
          navigateTo(`/restaurantes/${restaurant.idRestaurante}`);
        });
      }
    });
  });

  // Fit map to show all markers with padding
  if (props.restaurants.length > 0) {
    map.fitBounds(bounds, { padding: [50, 50] });
  }
}
</script>

<style scoped lang="scss">
#map { 
  height: 400px;
  margin-left: 16px;
  margin-right: 16px;
  border-radius: 6px;
}
</style>
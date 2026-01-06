<template>
  <div id="map"></div>
</template>

<script setup lang="ts">
const LOCATION = [40.52448, -3.65484] as const;
const ZOOM_LEVEL = 18;

onMounted(() => {
  // @ts-ignore - Leaflet is loaded via CDN
  const L = window.L;
  
  const map = L.map('map', { scrollWheelZoom: false }).setView(LOCATION, ZOOM_LEVEL);
  
  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(map);

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

  L.marker(LOCATION, { icon: customIcon }).addTo(map);
});
</script>

<style scoped lang="scss">
#map { 
  height: 340px;
}
</style>
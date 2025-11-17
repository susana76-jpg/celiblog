<template>
  <section class="details-page receipe-page">
    <DetailsHeroImage v-if="receipe" :receipe="receipe" />
  </section>
</template>

<script setup lang="ts">
const route = useRoute();
const id = parseInt(route.params.id as string);
const receipe = ref(null); // Initialize as null to avoid undefined issues

const getReceipeById = async () => {
  try {
    const { data } = await useApiFetch(API.RECETAS.BY_ID, {
      params: { id },
    });
    receipe.value = data.value; // Assign the fetched data to receipe
  } catch (error) {
    console.error('Error fetching receipe:', error);
  }
};

// Fetch the receipe data when the component is mounted
onMounted(() => {
  getReceipeById();
});
</script>

<style scoped>
.recipe-page {
  font-family: 'Poppins', sans-serif;
  color: #242424;
}
.top-menu {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  padding: 1rem;
}
.menu ul {
  display: flex;
  gap: 1rem;
}
.menu li.active {
  color: #836a02;
}
.hero {
  display: flex;
  justify-content: space-between;
  padding: 2rem;
}
.hero-info {
  max-width: 50%;
}
.hero-image img {
  width: 100%;
  height: auto;
}
.recipe-description,
.recipe-steps {
  margin: 2rem 0;
}
.footer {
  text-align: center;
  padding: 1rem;
  background-color: #d9d9d9;
}
</style>

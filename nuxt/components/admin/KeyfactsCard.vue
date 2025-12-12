<template>
  <div class="keyfacts-container">
    <div 
      v-for="card in keyfactCards" 
      :key="card.tab"
      class="keyfact-card" 
    >
      <v-icon class="keyfact-icon" color="#836a02" size="60">{{ card.icon }}</v-icon>
      <div class="keyfact-content">
        <div class="keyfact-number">{{ stats[card.statsKey] }}</div>
        <div class="keyfact-label">{{ card.label }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Stats with values of the database entities
const stats = {
  restaurantes: 0,
  consejos: 0,
  recetas: 0,
  comentarios: 0,
  usuarios: 0
};

// Array defining the keyfact cards
const keyfactCards = [
  {
    tab: 'restaurants',
    icon: 'mdi-silverware-fork-knife',
    statsKey: 'restaurantes' as keyof typeof stats,
    label: 'Restaurantes'
  },
  {
    tab: 'tips',
    icon: 'mdi-lightbulb-outline',
    statsKey: 'consejos' as keyof typeof stats,
    label: 'Consejos'
  },
  {
    tab: 'recipes',
    icon: 'mdi-food-outline',
    statsKey: 'recetas' as keyof typeof stats,
    label: 'Recetas'
  },
  {
    tab: 'comments',
    icon: 'mdi-comment-outline',
    statsKey: 'comentarios' as keyof typeof stats,
    label: 'Comentarios'
  },
  {
    tab: 'users',
    icon: 'mdi-account-outline',
    statsKey: 'usuarios' as keyof typeof stats,
    label: 'Usuarios'
  }
]

// Fetch stats from API on component mount
const getStats = async () => {
  // try {
  //   const response = await useApiFetch(API.USER.LOGIN);
  // } catch (error: any) {
  //   console.error('Login error:', error);
  // }
};

onMounted(() => {
  getStats();
});
</script>

<style scoped lang="scss">
// Statistics Cards
.keyfacts-container {
  display: flex;
  justify-content: space-between;
  gap: 20px;
}

.keyfact-card {
  flex: 1;
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 20px;
  background-color: #ffffff;
  border: 5px solid #836a02;
  border-radius: 6px;
}

.keyfact-icon {
  flex-shrink: 0;
}

.keyfact-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 4px;
}

.keyfact-number {
  font-size: 64px;
  font-weight: bold;
  line-height: 1;
  color: #836a02;
}

.keyfact-label {
  font-size: 24px;
  line-height: 22px;
  color: #836a02;
  text-align: center;
}
</style>
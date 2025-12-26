<template>
  <div class="keyfacts-container">
    <div 
      v-for="card in keyfactCards" 
      :key="card.tab"
      class="keyfact-card" 
    >
      <v-icon class="keyfact-icon" color="#836a02" size="76">{{ card.icon }}</v-icon>
      <div class="keyfact-content">
        <div class="keyfact-number">{{ stats[card.statsKey as keyof typeof stats] }}</div>
        <div class="keyfact-label">{{ card.label }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Use global notification composable
const { showError } = useNotification();

type StatsType = {
  numRestaurantes: number;
  numPost: number;
  numRecetas: number;
  numComentarios: number;
  numUsuarios: number;
};

const stats = ref<StatsType>({
  numRestaurantes: 0,
  numPost: 0,
  numRecetas: 0,
  numComentarios: 0,
  numUsuarios: 0
});

// Array defining the keyfact cards
const keyfactCards = [
  {
    tab: 'restaurants',
    icon: 'mdi-silverware-fork-knife',
    statsKey: 'numRestaurantes' as keyof StatsType,
    label: 'Restaurantes'
  },
  {
    tab: 'tips',
    icon: 'mdi-lightbulb-outline',
    statsKey: 'numPost' as keyof StatsType,
    label: 'Consejos'
  },
  {
    tab: 'recipes',
    icon: 'mdi-food-outline',
    statsKey: 'numRecetas' as keyof StatsType,
    label: 'Recetas'
  },
  {
    tab: 'comments',
    icon: 'mdi-comment-outline',
    statsKey: 'numComentarios' as keyof StatsType,
    label: 'Comentarios'
  },
  {
    tab: 'users',
    icon: 'mdi-account-outline',
    statsKey: 'numUsuarios' as keyof StatsType,
    label: 'Usuarios'
  }
]

// Fetch statistics from API
const fetchStats = async () => {
  try {
    const response = await useApiFetch(API.STATS.ALL);
    stats.value = response as StatsType;
  } catch (error) {
    showError(`Error fetching stats: ${error}`);
  }
};

onMounted(() => {
  fetchStats();
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
  align-items: end;
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
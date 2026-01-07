<template>
  <v-col 
    v-for="card in keyfactCards"
    :key="card.tab"
    class="user-keyfacts py-0"
    :class="card.class"
  >
    <v-card
      class="keyfact-card text-center"
      elevation="0"
      @click="emit('update:tab', card.tab)"
    >
      <v-icon size="80" color="primary" class="keyfact-icon">{{ card.icon }}</v-icon>
      <div class="keyfact-number">{{ stats[card.statsKey as keyof typeof stats] }}</div>
      <div class="keyfact-label">{{ card.label }}</div>
    </v-card>
  </v-col>
</template>

<script setup lang="ts">
// Use global notification composable
const { showError } = useNotification();

const emit = defineEmits<{
  (e: 'update:tab', tab: string): void;
}>();

// Statistics
const stats = ref({
  numRestaurantes: 9,
  numPost: 12,
  numRecetas: 24,
  numComentarios: 15
});

// Array defining the keyfact cards
const keyfactCards = [
  {
    tab: 'restaurantes',
    icon: 'mdi-silverware-fork-knife',
    statsKey: 'numRestaurantes' as keyof typeof stats,
    label: 'Restaurantes favoritos',
    class: 'pr-2'
  },
  {
    tab: 'recetas',
    icon: 'mdi-food-outline',
    statsKey: 'numRecetas' as keyof typeof stats,
    label: 'Recetas favoritas',
    class: 'px-2'
  },
  {
    tab: 'consejos',
    icon: 'mdi-lightbulb-outline',
    statsKey: 'numPost' as keyof typeof stats,
    label: 'Consejos favoritos',
    class: 'px-2'
  },
  {
    tab: 'comentarios',
    icon: 'mdi-comment-outline',
    statsKey: 'numComentarios' as keyof typeof stats,
    label: 'Comentarios',
    class: 'pl-2'
  }
]

// GET user statistics from API
const fetchUserStats = async () => {
  try {
    const data = await useApiFetch(API.STATS.USER); 
    stats.value = data as typeof stats.value;
  } 
  catch (error) {
    showError(`Error fetching stats: ${error}`);
  }
};

onMounted(() => {
  fetchUserStats();
});
</script>

<style lang="scss" scoped>
.user-keyfacts {
  .keyfact-card {
    height: 100%;
    border: 5px solid #836A02;
    border-radius: 8px;
    padding: 20px;
    cursor: pointer;
    transition: transform 0.2s;
      
    &:hover {
      transform: translateY(-4px);
    }
      
    .keyfact-icon {
      margin-bottom: 16px;
    }
      
    .keyfact-number, .keyfact-label {
      font-size: 42px;
      font-weight: 700;
      color: #836A02;
      line-height: 1;
      margin-bottom: 12px;
    }

    .keyfact-label {
      font-size: 20px;
      font-weight: 500;
      margin-bottom: 0;
    }
  }
}
</style>
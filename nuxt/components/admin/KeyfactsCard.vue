<template>
  <div class="keyfacts-container">
    <div 
      v-for="card in keyfactCards" 
      :key="card.tab"
      class="keyfact-card" 
    >
      <v-icon class="keyfact-icon" color="#836a02" size="60">{{ card.icon }}</v-icon>
      <div class="keyfact-content">
        <div class="keyfact-number">{{ stats[card.statsKey as keyof typeof stats] }}</div>
        <div class="keyfact-label">{{ card.label }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useAdminStore } from '@/stores/admin';

const store = useAdminStore();

type StatsType = {
  restaurants: number;
  posts: number;
  recipes: number;
  comments: number;
  users: number;
};

const stats = computed<StatsType>(() => ({
  restaurants: store.keyfacts.totalRestaurants,
  posts: store.keyfacts.totalPosts,
  recipes: store.keyfacts.totalRecipes,
  comments: store.keyfacts.totalComments,
  users: store.keyfacts.totalUsers
}));

// Array defining the keyfact cards
const keyfactCards = [
  {
    tab: 'restaurants',
    icon: 'mdi-silverware-fork-knife',
    statsKey: 'restaurants' as keyof StatsType,
    label: 'Restaurantes'
  },
  {
    tab: 'tips',
    icon: 'mdi-lightbulb-outline',
    statsKey: 'posts' as keyof StatsType,
    label: 'Consejos'
  },
  {
    tab: 'recipes',
    icon: 'mdi-food-outline',
    statsKey: 'recipes' as keyof StatsType,
    label: 'Recetas'
  },
  {
    tab: 'comments',
    icon: 'mdi-comment-outline',
    statsKey: 'comments' as keyof StatsType,
    label: 'Comentarios'
  },
  {
    tab: 'users',
    icon: 'mdi-account-outline',
    statsKey: 'users' as keyof StatsType,
    label: 'Usuarios'
  }
]
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
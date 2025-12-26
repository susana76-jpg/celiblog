<template>
  <div class="admin-page">

    <!-- KEYFACTS --------------------------->
    <AdminKeyfactsCard />
    <!--------------------------------------->

    <!-- TABS + OPEN DIALOG BUTTON ---------->
    <div class="tabs-header">
      <div class="tabs-navigation">
        <v-btn
          v-for="tab in tabs"
          :key="tab.value"
          variant="flat"
          :class="['tab-button', { 'tab-active': activeTab === tab.value }]"
          @click="activeTab = tab.value"
        >
          {{ tab.label }}
        </v-btn>
      </div>
    </div>
    <!--------------------------------------->

    <!-- TAB CONTENT ------------------------>
    <KeepAlive>
      <component :is="currentTabComponent" />
    </KeepAlive>
    <!------------------------------------->

  </div>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: 'admin'
})

/**************************************/
/* TABS */
/**************************************/
const route = useRoute();
const router = useRouter();

// Initialize activeTab from URL query parameter or default to 'users'
const activeTab = ref((route.query.tab as string) || 'users');

// Watch for route changes to update activeTab
watch(() => route.query.tab, (newTab) => {
  if (newTab && typeof newTab === 'string') {
    activeTab.value = newTab;
  }
});

// Watch activeTab to update URL
watch(activeTab, (newTab) => {
  if (route.query.tab !== newTab) {
    router.push({ query: { ...route.query, tab: newTab } });
  }
});

// List of available tabs
const tabs = [
  { label: 'Usuarios', value: 'users' },
  { label: 'Restaurantes', value: 'restaurants' },
  { label: 'Recetas', value: 'recipes' },
  { label: 'Consejos', value: 'tips' },
  { label: 'Comentarios', value: 'comments' }
]

// Tab components mapping
const tabComponents: Record<string, any> = {
  users: resolveComponent('AdminUsersTable'),
  restaurants: resolveComponent('AdminRestaurantsTable'),
  recipes: resolveComponent('AdminRecipesTable'),
  tips: resolveComponent('AdminPostsTable'),
  comments: resolveComponent('AdminCommentsTable')
}

// Computed property for dynamic component
const currentTabComponent = computed(() => {
  return tabComponents[activeTab.value] || tabComponents.users
});
</script>

<style lang="scss" scoped>
.admin-page {
  background-color: #ffffff;
  min-height: 100vh;
  padding: 60px 120px;
  font-family: 'Poppins', sans-serif;
}

// Tabs
.tabs-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 60px;
  margin-bottom: 20px;

  .tabs-navigation {
    display: flex;
    gap: 12px;
  }

  .tab-button {
    min-width: 184px;
    height: 40px;
    padding: 0 24px;
    background-color: rgba(160, 160, 160, 0.52);
    color: #616161;
    letter-spacing: -1px;
    transition: all 0.3s ease;

    &:hover {
      background-color: rgba(131, 106, 2, 0.2);
    }

    &.tab-active {
      background-color: #616161;
      color: #ffffff;

      &:hover {
        background-color: #4a4a4a;
      }
    }
  }
}

// Tab Content
.tab-content {
  padding: 40px 100px;
}

// Responsive Design
@media (max-width: 1600px) {
  .keyfacts-container {
    flex-wrap: wrap;

    .keyfact-card {
      flex-basis: calc(33.333% - 14px);
    }
  }
}

@media (max-width: 1200px) {
  .admin-header,
  .keyfacts-container,
  .tabs-header,
  .tab-content {
    padding-left: 40px;
    padding-right: 40px;
  }

  .keyfacts-container {
    .keyfact-card {
      flex-basis: calc(50% - 10px);
    }
  }

  .keyfact-number {
    font-size: 48px;
  }

  .keyfact-label {
    font-size: 18px;
  }
}

@media (max-width: 768px) {
  .admin-header,
  .keyfacts-container,
  .tabs-header,
  .tab-content {
    padding-left: 20px;
    padding-right: 20px;
  }

  .tabs-header {
    flex-direction: column;
    gap: 20px;
    align-items: stretch;
  }

  .tabs-navigation {
    flex-wrap: wrap;
  }

  .keyfacts-container {
    flex-direction: column;

    .keyfact-card {
      flex-basis: 100%;
    }
  }
}
</style>
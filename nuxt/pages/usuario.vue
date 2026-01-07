<template>
  <v-container fluid class="user-page">

    <!-- USER INFORMATION ------------------------------------>    
    <v-row class="ma-0" no-gutters>
      <v-col cols="12" lg="5" class="align-self-stretch pr-lg-4 py-0 pl-0 mb-4 mb-lg-0">
        <UserPersonalData class="h-100" />
      </v-col>
      <v-col cols="12" lg="7" class="py-0 pr-0">
        <v-row no-gutters class="h-100">
          <UserFavoritesKeyfacts 
            @update:tab="activeTab = $event"
          />
        </v-row>
      </v-col>
    </v-row>
    <!--------------------------------------------------------->

    <!-- TABS ------------------------------------------------->
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
    <!--------------------------------------------------------->
    
    
    <!-- TAB CONTENT ------------------------------------------>
    <KeepAlive>
      <component :is="currentTabComponent" />
    </KeepAlive>
    <!--------------------------------------------------------->


  </v-container>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: 'auth'
})


/**************************************/
/* TABS */
/**************************************/
const route = useRoute();
const router = useRouter();

// Initialize activeTab from URL query parameter or default to 'restaurantes'
const activeTab = ref((route.query.tab as string) || 'restaurantes');

// Tabs configuration
const tabs = [
  { label: 'Restaurantes', value: 'restaurantes' },
  { label: 'Recetas', value: 'recetas' },
  { label: 'Consejos', value: 'consejos' },
  { label: 'Comentarios', value: 'comentarios' }
];

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

// Tab components mapping
const tabComponents: Record<string, any> = {
  restaurantes: resolveComponent('UserRestaurantsFavorites'),
  recetas: resolveComponent('UserRecipesFavorites'),
  consejos: resolveComponent('UserPostsFavorites'),
  comentarios: resolveComponent('UserCommentsFavorites')
}

// Computed property for dynamic component
const currentTabComponent = computed(() => {
  return tabComponents[activeTab.value] || tabComponents.restaurantes
});
</script>

<style lang="scss" scoped>
.user-page {
  background-color: #ffffff;
  min-height: 100vh;
  padding: 60px 100px;
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
</style>

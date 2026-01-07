<template>
  <v-app-bar 
    height="84"
    class="main-menu"
    scroll-behavior="elevate"
    scroll-threshold="300"
  >

    <!-- LOGO ------------------------------------------>
    <v-img 
      inline
      height="46px"
      :width="130"
      :min-width="130"
      src="/img/celiblog_logo.png" 
      alt="Celiblog logo" 
    />
    <!-------------------------------------------------->

    <!-- DESKTOP NAVIGATION ---------------------------->
    <nav class="desktop-nav">
      <v-btn
        v-for="item in menuItems"
        :key="item.path"
        :to="item.path"
        :icon="item.icon"
        :text="item.text"
        :variant="item.variant"
        :class="{ 'active-menu-item': isActiveRoute(item.path) }"
        rounded="0"
      />
    </nav>
    <!-------------------------------------------------->

    <v-spacer class="mobile-spacer" />

    <!-- DESKTOP ACCOUNT BUTTONS ----------------------->
    <div class="account-buttons desktop-account">
      <template v-if="!isUserLoggedIn">
        <v-btn
          v-for="button in accountButtons"
          :key="button.path"
          :to="button.path"
          :prepend-icon="button.icon"
          :text="button.text"
          :variant="button.variant"
          :class="button.class"
          :color="button.color"
          :rounded="button.rounded"
        />
      </template>
      <template v-else>
        <v-btn
          text
          slim
          size="large"
          color="primary"
          variant="outlined"
          rounded="xl"
          class="user-button text-capitalize text-body-1 px-2"
        >
          <p>Hola, {{ userName }}</p>
          <v-avatar 
            size="34px"
            class="ml-2"
          >
            <v-img
              alt="John"
              src="/img/avator.png"
            ></v-img>
          </v-avatar>
          <v-menu activator="parent">
            <v-list>
              <v-list-item
                v-for="(item, index) in items"
                :key="index"
                :value="index"
                @click="item.event"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-btn>
      </template>
    </div>
    <!-------------------------------------------------->
    
    <!-- MOBILE MENU BUTTON ---------------------------->
    <v-btn
      icon="mdi-menu"
      variant="text"
      class="mobile-menu-btn"
      @click="drawer = !drawer"
    />
    <!-------------------------------------------------->
    
  </v-app-bar>

  <!-- MOBILE NAVIGATION DRAWER ----------------------->
  <v-navigation-drawer
    temporary
    location="right"
    class="mobile-drawer"
    v-model="drawer"
  >
    <v-list>

      <!-- Close button -->
      <v-list-item class="drawer-header">
        <v-btn
          icon="mdi-close"
          variant="text"
          @click="drawer = false"
        />
      </v-list-item>

      <!-- Menu items -->
      <v-list-item
        v-for="item in menuItems"
        :key="item.path"
        :to="item.path"
        :prepend-icon="item.icon"
        :title="item.text || ''"
        :class="{ 'active-drawer-item': isActiveRoute(item.path) }"
        @click="drawer = false"
      />

      <v-divider class="my-4" />

      <!-- Account buttons in drawer -->
      <template v-if="!isUserLoggedIn">
        <v-list-item
          v-for="button in accountButtons"
          :key="button.path"
          :to="button.path"
          :prepend-icon="button.icon"
          :title="button.text"
          @click="drawer = false"
        />
      </template>
      <template v-else>
        <v-list-item
          v-for="(item, index) in items"
          :key="index"
          :title="item.title"
          @click="item.event(); drawer = false"
        />
      </template>
    </v-list>
  </v-navigation-drawer>
  <!-------------------------------------------------->

</template>

<script setup lang="ts">
import { ref,computed} from 'vue';
import { useAuthStore } from '@/composables/useAuthStore';
import { navigateTo } from '#app';
import { useRoute } from 'vue-router';
//import type { Variant } from 'vuetify/components';

const { user, isAuthenticated, logout, isAdmin } = useAuthStore();
const route = useRoute();
const drawer = ref<boolean>(false);

// Computed properties for authentication
const isUserLoggedIn = computed(() => isAuthenticated.value && user.value !== null);
const userName = computed(() => user.value?.nombre || 'Usuario');

// Check if route is active
const isActiveRoute = (path: string) => {
  if (path === '/') return route.path === '/';
  return route.path.includes(path);
};

// List of menu items
const menuItems = [
  { path: '/', icon: 'mdi-home-outline', variant: 'flat' as Variant },
  { path: '/recetas', text: 'Recetas' },
  { path: '/restaurantes', text: 'Restaurantes' },
  { path: '/consejos', text: 'Consejos' },
  { path: '/equipo', text: 'Equipo' },
  { path: '/contacto', text: 'Contacto' }
]

// List of account buttons
const accountButtons = [
  {
    path: '/inicio',
    text: 'Iniciar Sesión',
    icon: 'mdi-account-outline',
    variant: 'flat' as Variant,
    class: 'mr-2',
    color: 'primary',
    rounded: 'xl'
  },
  {
    path: '/registro',
    text: 'Crear Usuario',
    icon: 'mdi-plus-circle-outline',
    variant: 'outlined' as Variant,
    color: 'primary',
    rounded: 'xl'
  }
];

/*User menu items
const items = [
  { 
    title: 'Perfil Usuario',
    event: () => redirectToUserProfile() 
  },
  { 
    title: 'Cerrar Sesión',
    event: () => logout()
  },
]; */
type MenuItem = {
  title: string;
  event: () => unknown;
};
//añadimos redireccion a Panel de Administrador
const items = computed(() => {
   const baseItems: MenuItem[] = [
    {
      title: 'Perfil Usuario',
      event: () => navigateTo('/usuario')
    }
  ];

  // SOLO ADMIN
  if (isAdmin.value) {
    baseItems.push({
      title: 'Panel Administrador',
      event: () => navigateTo('/admin')
      
      
    });
  }

  baseItems.push({
    title: 'Cerrar Sesión',
    event: () => logout()
  });

  return baseItems;
}); 

// Redirect to user profile based on role
const redirectToUserProfile = async () => {
  if (isAdmin.value) await navigateTo('/admin');
  else await navigateTo('/usuario');
};
</script>


<style lang="scss">
.v-app-bar.main-menu {
  padding: 18px 100px;

  // Adjust toolbar content alignment
  .v-toolbar__content {
    height: auto !important;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  // Set styles for active menu item
  nav {
    a[aria-current="page"],
    .active-menu-item {
      background-color: #FFF;
      color: #836A02;
      border-bottom: 2px solid #836A02;    
      
      .v-btn__overlay {
        background-color: transparent !important;
      }
    }
  }

  // Set styles for account buttons
  .account-buttons {
    .user-button {
      p {
        letter-spacing: 0em;
        font-weight: 600;
      }
    }
  }

  // Hide mobile elements on desktop
  .mobile-menu-btn,
  .mobile-spacer {
    display: none;
  }

  @media (max-width: 1420px) {
    padding: 18px 20px;

    // Hide desktop navigation
    .desktop-nav,
    .desktop-account {
      display: none !important;
    }

    // Show mobile elements
    .mobile-menu-btn,
    .mobile-spacer {
      display: flex;
    }
  }

  @media (max-width: 600px) {
    padding: 12px 16px;
    height: 70px !important;

    .v-img {
      height: 36px !important;
      width: 100px !important;
      min-width: 100px !important;
    }
  }
}

// Mobile drawer styles
.mobile-drawer {
  .drawer-header {
    display: flex;
    justify-content: flex-end;
    padding: 8px;
  }

  .active-drawer-item {
    background-color: rgba(131, 106, 2, 0.1);
    color: #836A02;
    font-weight: 600;

    .v-list-item__overlay {
      background-color: transparent !important;
    }
  }

  .v-list-item {
    text-transform: capitalize;
  }
}
</style>

<template>
  <v-app-bar 
    class="main-menu"
    scroll-behavior="elevate"
    scroll-threshold="300"
  >

    <v-img 
      inline
      height="46px"
      :width="130"
      :min-width="130"
      src="/img/celiblog_logo.png" 
      alt="Celiblog logo" 
    />

    <nav>
      <v-btn
        v-for="item in menuItems"
        :key="item.path"
        :to="item.path"
        :icon="item.icon"
        :text="item.text"
        :variant="item.variant"
        rounded="0"
        exact
      />
    </nav>

    <div class="account-buttons">
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
              src="https://cdn.vuetifyjs.com/images/john.jpg"
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
  </v-app-bar>
</template>

<script setup lang="ts">
import { navigateTo } from 'nuxt/app';
import type { Variant } from '../types/vuetify';

const isUserLoggedIn = ref<boolean>(true);
const userName = ref<string>('John Doe');

// List of menu items
const menuItems = [
  { path: '/', icon: 'mdi-home-outline', variant: 'flat' as Variant },
  { path: '/recetas', text: 'recetas' },
  { path: '/restaurantes', text: 'restaurantes' },
  { path: '/consejos', text: 'consejos' },
  { path: '/equipo', text: 'equipo' },
  { path: '/contacto', text: 'contacto' }
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

const items = [
  { 
    title: 'Perfil Usuario',
    event: async () => await navigateTo('/usuario') 
  },
  { 
    title: 'Cerrar Sesión' ,
    event: async () => await navigateTo('/')
  },
];  
</script>


<style lang="scss">
.v-app-bar.main-menu {
  padding: 18px 80px;

  // Adjust toolbar content alignment
  .v-toolbar__content {
    height: auto !important;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  // Set styles for active menu item
  nav {
    a[aria-current="page"] {
      background-color: #FFF;
      color: #836A02;
      border-bottom: 2px solid #836A02;    
      
      .v-btn__overlay {
        background-color: transparent !important;
      }
    }
  }

  .account-buttons {
    .user-button {
      p {
        letter-spacing: 0em;
        font-weight: 600;
      }
    }
  }
}
</style>

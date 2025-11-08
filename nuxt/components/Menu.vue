<template>
  <v-app-bar 
    flat
    class="main-menu"
    scroll-behavior="elevate"
    scroll-threshold="300"
  >
    <div class="logo">
      <img src="/assets/img/celiblog_logo.png" alt="Celiblog logo" />
    </div>

    <nav class="text-menu">
      <v-icon icon="mdi-home-outline" color="green"></v-icon>
      <NuxtLink to="/recetas" class="menu-item">RECETAS</NuxtLink>
      <NuxtLink to="/restaurantes" class="menu-item">RESTAURANTES</NuxtLink>
      <NuxtLink to="/consejos" class="menu-item">CONSEJOS</NuxtLink>
      <NuxtLink to="/equipo" class="menu-item">SOBRE</NuxtLink>
      <NuxtLink to="/contacto" class="menu-item">CONTACTO</NuxtLink>
    </nav>

    <div class="account-buttons">
      <NuxtLink to="/inicio" class="sign-in-button">
        <span>Iniciar Sesión</span>
      </NuxtLink>
      <NuxtLink to="/registro" class="sign-up-button">
        <span>Crear Usuario</span>
      </NuxtLink>
    </div>
  </v-app-bar>
</template>

<style lang="scss">
.v-app-bar.main-menu {
  position: fixed !important;
  top: 0;
  width: 100vw;
  padding: 20px;

  .v-toolbar__content {
    height: auto !important;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .logo {
    width: 120px;
    display: flex;
    flex-direction: column;
    justify-content: center;

    img {
      width: 100%;
      max-width: 100%;
      height: auto;
    }
  }

  .text-menu {
    display: flex;
    gap: 30px;

    .menu-item {
      font-family: var(--font-family-main);
      font-weight: bold;
      color: var(--color-text-primary);
      text-decoration: none;
      transition: color 0.3s;

      &:hover {
        color: var(--color-primary);
      }
    }
  }

  .account-buttons {
    display: flex;
    gap: 15px;

    .sign-in-button,
    .sign-up-button {
      font-family: var(--font-family-main);
      font-weight: bold;
      text-decoration: none;
      padding: 8px 16px;
      border-radius: 4px;
      transition: background-color 0.3s, color 0.3s;
    }

    .sign-in-button {
      color: var(--color-text-primary);

      &:hover {
        background-color: var(--color-primary-light);
        color: #fff;
      }
    }

    .sign-up-button {
      background-color: var(--color-primary);
      color: #fff;

      &:hover {
        background-color: var(--color-primary-dark);
      }
    }
  }
}
</style>

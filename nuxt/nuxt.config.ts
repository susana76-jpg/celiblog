import { defineNuxtConfig } from 'nuxt/config'

export default defineNuxtConfig({
  ssr: true,
  css: ['vuetify/styles', '@/assets/main.scss'],
  app: {
    head: {
      title: 'CeliBlog',
      link: [
        {
          rel: 'stylesheet',
          href: 'https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap'
        },
        {
          rel: 'stylesheet',
          href: 'https://cdn.jsdelivr.net/npm/@mdi/font@latest/css/materialdesignicons.min.css'
        }
      ]
    }
  },
  build: {
    transpile: ['vuetify']
  },
  runtimeConfig: {
    public: {
      apiBase: process.env.NUXT_PUBLIC_API_BASE || 'http://46.183.113.124:8081'
    }
  },

  vite: {
    define: {
      'process.env.DEBUG': false
    }
  }
})

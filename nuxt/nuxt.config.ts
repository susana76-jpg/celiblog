import { defineNuxtConfig } from 'nuxt/config'

export default defineNuxtConfig({
  ssr: true,
  css: ['vuetify/styles', '@/assets/main.scss'],
  app: {
    head: {
      title: 'CeliBlog'
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

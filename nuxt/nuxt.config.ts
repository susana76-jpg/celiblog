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
  vite: {
    define: {
      'process.env.DEBUG': false
    }
  }
})

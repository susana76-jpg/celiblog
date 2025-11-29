import { defineNuxtConfig } from 'nuxt/config'

export default defineNuxtConfig({
  ssr: false,
  css: ['vuetify/styles', '@/assets/styles/main.scss'],
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
        },
        {
          rel: 'stylesheet',
          href: 'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'
        },
        {
          rel: 'icon',
          type: 'image/png',
          href: '/img/celiblog-favicon.png',
          sizes: '32x32'
        },
        {
          rel: 'stylesheet',
          href: 'https://unpkg.com/leaflet@1.9.4/dist/leaflet.css',
          integrity: 'sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=',
          crossorigin: ''
        }
      ],
      script: [
        {
          src: 'https://unpkg.com/leaflet@1.9.4/dist/leaflet.js',
          integrity: 'sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=',
          crossorigin: ''
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

  imports: {
    dirs: [
      'stores', 
      'types',
      'constants'
    ]
  },
  
  vite: {
    define: {
      'process.env.DEBUG': false
    },
    server: {
      proxy: {
        '/api': {
          target: 'http://46.183.113.124:8081',
          changeOrigin: true,
        },
      },
    }
    // vue: { 
    //   template: {
    //     transformAssetUrls: {
    //       base: null,
    //       includeAbsolute: false,
    //     }
    //   }
    // },
    // css: {
    //   preprocessorOptions: {
    //     scss: {
    //       additionalData: '@use "@/assets/styles/_variables.scss" as *;'
    //     }
    //   } 
    // }
  },
  experimental: {
    payloadExtraction: false
  }
})

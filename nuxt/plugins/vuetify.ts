import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import 'vuetify/styles';
import { defineNuxtPlugin } from 'nuxt/app';
import { fa } from 'vuetify/iconsets/fa';
import { mdi } from 'vuetify/iconsets/mdi';

export default defineNuxtPlugin((nuxtApp) => {
  const vuetify = createVuetify({
    components,
    directives,
    icons: {
      defaultSet: 'mdi',
      sets: {
        mdi,
        fa,
      }
    },
    theme: {
      defaultTheme: 'light',
      themes: {
        light: {
          colors: {
            primary: '#836A02',
            secondary: '#2E5635',
            tertiary: '#A3C9A8',
            darkgray: '#333333',
            darkbrown: '#5D4037',
            lightgray: '#5c5c5c',
            background: '#F4F1EA',
            accent: '#FF4081',
            error: '#FF5252',
            info: '#2196F3',
            success: '#4CAF50',
            warning: '#FFC107',
          }
        },
      }
    }
  });
  nuxtApp.vueApp.use(vuetify);
})

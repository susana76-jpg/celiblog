export const API = {
  USERS: {
    BASE: '/api/usuario/all',
    ID: '/api/usuario/byId'
  },
  RECIPES: {
    BASE: '/api/receta/public/all',
    BY_ID: '/api/receta/public/byId',
    STEPS: '/api/paso-receta/public/pasos',
    INGREDIENTS: '/api/receta/public/ingredientes'
  },
  RESTAURANTS: {
    BASE: '/api/restaurante/public/all',
    BY_ID: '/api/restaurante/public/byId',
    TAGS: '/api/tag-restaurante/public/tags'
  },
  USER: {
    REGISTER: '/api/auth/register',
    LOGIN: '/api/auth/login',
    PROFILE: '/api/usuario/profile'
  }
};
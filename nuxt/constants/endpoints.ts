export const API = {
  USERS: {
    BASE: '/api/usuario/all',
    ID: '/api/usuario/byId'
  },
  RECIPES: {
    BASE: '/api/receta/public/all',
    BY_ID: '/api/receta/public/byId',
    STEPS: '/api/paso-receta/public/pasos',
    INGREDIENTS: '/api/receta/public/ingredientes',
    SEARCH: '/api/receta/public/buscar'
  },
  RESTAURANTS: {
    BASE: '/api/restaurante/public/all',
    BY_ID: '/api/restaurante/public/byId',
    TAGS: '/api/tag-restaurante/public/tags',
  },
  POSTS: {
    BASE: '/api/post/public/all',
    BY_ID: '/api/post/public/byId',
    TAGS: '/api/tag-post/public/tags',
  },
  USER: {
    REGISTER: '/api/auth/register',
    LOGIN: '/api/auth/login',
    PROFILE: '/api/usuario/profile'
  },
  FAVORITES: {
    ADD: '/api/favoritos/add',
    REMOVE: '/api/favoritos/deleteById'
  },
  COMMENTS: {
    ALL: '/api/comentario/public/all',
    ADD: '/api/comentario/add',
    DELETE: '/api/comentario/delete',
    UPDATE: '/api/comentario/update',
  }
};
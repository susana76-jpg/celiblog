export const API = {
  USERS: {
    BASE: '/api/usuario/all',
    ID: '/api/usuario/byId',  
    DELETE: '/api/usuario/delete',
    ADD: '/api/usuario/add',
    UPDATE_ROLE: '/api/usuario/update-rol-usuario'
  },
  RECIPES: {
    BASE: '/api/receta/public/all',
    BY_ID: '/api/receta/public/byId',
    ADD: '/api/receta/add',
    ADD_STEP: '/api/paso-receta/add',
    ADD_INGREDIENT: '/api/ingrediente/add',
    UPDATE: '/api/receta/update',
    DELETE: '/api/receta/deleteById',
    DELETE_INGREDIENT: '/api/ingrediente/delete',
    DELETE_STEP: '/api/paso-receta/delete-paso',
    STEPS: '/api/paso-receta/public/pasos',
    INGREDIENTS: '/api/receta/public/ingredientes',
    SEARCH: '/api/receta/public/buscar'
  },
  RESTAURANTS: {
    BASE: '/api/restaurante/public/all',
    BY_ID: '/api/restaurante/public/byId',
    TAGS: '/api/tag-restaurante/public/tags',
    DELETE: '/api/restaurante/deleteById',
    UPDATE: '/api/restaurante/update',
    ADD: '/api/restaurante/add',
    SEARCH: '/api/restaurante/public/ubicacion'
  },
  POSTS: {
    BASE: '/api/post/public/all',
    BY_ID: '/api/post/public/byId',
    ADD: '/api/post/add',
    UPDATE: '/api/post/update',
    DELETE: '/api/post/deleteById',
    TAGS: '/api/tag-post/public/tags',
    SEARCH: '/api/post/public/buscar'
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
  },
  STATUS: {
    UPDATE: '/api/publicacion/update',
    LIST: '/api/publicacion/list'
  },
  STATS: {
    ALL: '/api/estadistica/all'
  }
};
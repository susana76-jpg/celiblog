// nuxt/constants/objects.ts
// Note: All constants are defined in uppercase letters

// Objects types used in the application
export const TYPE = {
  RECIPE: 'RECETA',
  RESTAURANT: 'RESTAURANTE',
  POST: 'POST',
  COMMENT: 'COMENTARIO'
}

// User roles available in the application
export const USER_ROLES = [
  { idRol: 1, nombre: 'Usuario administrador' },
  { idRol: 2, nombre: 'Usuario editor' },
  { idRol: 3, nombre: 'Usuario visitante' }
]

// Possible statuses for posts / restaurants / recipes
export const POST_STATUS = {
  PENDING: 'PENDIENTE',
  APPROVED: 'APROBADO',
  REJECTED: 'RECHAZADO'
}
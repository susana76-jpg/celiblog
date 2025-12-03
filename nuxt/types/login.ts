export interface LoginResponse {
  accessToken: string
  refreshToken: string
  usuarioLogin: UsuarioLogin
}

export interface UsuarioLogin {
  idUsuario: number
  nombre: string
  email: string
  password: string
  rol: {
    idRol: number
    nombre: string
  },
  fechaAlta: string
}
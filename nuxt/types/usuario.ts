export interface UsuarioRol {
  idRol: number;
  nombre: string;
}

export interface Usuario {
  idUsuario: number;
  email: string;
  nombre: string;
  password: string;
  rol: UsuarioRol;
  fechaAlta: string;
  fechaAta: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  nombre: string;
}

export interface AuthResponse {
  token: string;
  user: Usuario;
}

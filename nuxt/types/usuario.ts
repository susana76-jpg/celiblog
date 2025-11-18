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

export interface Rol {
  idRol: number;
  nombre: string;
}

export interface Usuario {
  idUsuario: number;
  email: string;
  nombre: string;
  password: string;
  rol: Rol;
  fechaAlta: string;
  fechaAta: string;
}

export interface Restaurante {
  idRestaurante: number;
  fechaPublicacion: string;
  usuarioDTO: Usuario;
  descripcion: string;
  direccion: string;
  imagenUrl: string;
  nombre: string;
  ubicacion: string;
  codigoPostal: number;
  latitud: number;
  longitud: number;
  urlWeb: string;
  telefono: string;
  email: string;
  valoracion: number;
  estado: string;
  fechaValidacion: string | null;
}

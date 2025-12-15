export interface UsuarioPublicacion {
  email: string;
  nombre: string;
}

export interface Post {
  idPost: number;
  fechaPublicacion: string;
  titulo: string;
  subtitulo: string;
  contenido: string;
  urlPost: string;
  estado: 'PENDIENTE' | 'APROBADO' | 'RECHAZADO';
  fechaValidacion: string;
  valoracion: number;
  esFavoritoUsuario: boolean;
  valoracionMedia: number;
  usuarioPublicacion: UsuarioPublicacion;
}
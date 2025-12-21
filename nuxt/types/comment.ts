export interface Comentario {
  idComentario: number;
  fechaPublicacion: string;
  contenido: string;
  titulo: string;
  subtitulo: string;
  comentarioUrl: string | null;
  idObjetoComentado: number;
  objetoComentado: 'RECETA' | 'RESTAURANTE' | 'CONSEJO';
  fechaValidacion: string;
  valoracion: number;
  estado: 'PENDIENTE' | 'APROBADO' | 'RECHAZADO';
  esFavoritoUsuario: boolean;
  valoracionMedia: number;
  usuarioPublicacion: UsuarioPublicacion;
}
export type ObjectType = 'RECETA' | 'RESTAURANTE' | 'POST';

export interface CommentData {
  idObjetoComentado: number;
  objetoComentado: ObjectType;
  titulo: string;
  subtitulo?: string;
  contenido: string;
  comentarioUrl?: string;
  valoracion: number;
}
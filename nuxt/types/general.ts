export type ObjectType = 'RECETA' | 'RESTAURANTE' | 'POST';

export type RecetaType = 'DESAYUNO' | 'BRUNCH' | 'ALMUERZO' | 'MERIENDA' | 'CENA' | 'TAPAS' | 'RACIONES' | 'POSTRE' | 'SALADO' | 'DULCE';

export interface CommentData {
  idObjetoComentado: number;
  objetoComentado: ObjectType;
  titulo: string;
  subtitulo?: string;
  contenido: string;
  comentarioUrl?: string;
  valoracion: number;
}
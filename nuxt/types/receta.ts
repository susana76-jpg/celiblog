export interface Receta {
  idReceta: number;
  descripcion: string;
  fechaCreacion: string;
  imagenUrl: string;
  dificultad: string;
  titulo: string;
  subtitulo: string;
  estado: string;
  fechaValidacion: string | null;
  comensales: number;
  tiempoPreparacion: number;
  calorias: number;
  tipo: string;
}

export interface RecetaPaso {
  idPaso: number;
  descripcion: string;
  orden: number;
  idReceta: number;
  receta: Receta | null;
}
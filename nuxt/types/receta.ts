export interface Usuario {
  email: string;
  nombre: string;
}

export interface Receta {
  idReceta: number;
  usuario: Usuario;
  descripcion: string;
  fechaCreacion: string;
  imagenUrl: string;
  dificultad: 'fácil' | 'media' | 'difícil';
  titulo: string;
  subtitulo: string;
  estado: string;
  fechaValidacion: string | null;
  valoracion: number;
  comensales: number;
  tiempoPreparacion: number;
  valorEnergetico: number;
  tipoComida: string;
  esFavoritoUsuario: boolean;
}

export interface RecetaPaso {
  idPaso: number;
  descripcion: string;
  orden: number;
  idReceta: number;
  receta: Receta | null;
}

export interface RecetaIngrediente {
  idRecetaIngrediente: number;
  idIngrediente: number;
  idReceta: number;
  nombre: string;
  cantidad: string;
  unidad: string;
}
export interface Rol {
  idRol: number;
  nombre: string;
}

export interface RestauranteUsuario {
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
  usuarioDTO: RestauranteUsuario;
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
  esFavoritoUsuario?: boolean;
  titulo?: string;
  subtitulo?: string;
  tipoRestaurante?: string;
  fechaValidacion: string | null;
}

export interface RestauranteTag {
  idTag: number;
  nombre: string;
  restaurante: Restaurante | null;
  idRestaurante: number;
}

export type TipoRestaurante = 'SIN_GLUTEN' | 'MEDITERRANEA' | 'ASIATICA' | 'VEGANO' | 'MEXICANA'

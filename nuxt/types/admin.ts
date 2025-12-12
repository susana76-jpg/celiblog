export interface AdminUsuario {
  idUsuario: number;
  nombre: string;
  email: string;
  fechaAlta: string;
  rol: {
    idRol: number;
    nombre: string;
  };
}
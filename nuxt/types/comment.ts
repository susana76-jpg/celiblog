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

// {
//     "idComentario": 32,
//     "fechaPublicacion": "2025-11-18T20:43:04.000+00:00",
//     "titulo": "",
//     "subtitulo": "",
//     "contenido": "¡El Pan de trigo sarraceno (Receta 1) me quedó con un sabor a campo delicioso! Muy satisfecho.",
//     "comentarioUrl": null,
//     "usuario": {
//         "idUsuario": 13,
//         "email": "rodriguez.maite@gmail.com",
//         "nombre": "Maite Rodríguez Jurado",
//         "password": "XXXX=",
//         "rol": {
//             "idRol": 1,
//             "nombre": "ADMINISTRADOR"
//         },
//         "fechaAta": "2025-11-29T21:11:57.000+00:00"
//     },
//     "idObjetoComentado": 1,
//     "objeto_comentado": "RECETA",
//     "fechaValidacion": "2025-12-17T15:28:34.000+00:00",
//     "valoracion": 5,
//     "estado": "APROBADO"
// }
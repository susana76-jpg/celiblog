export const useComments = () => {
  const getCommentsByObjectId = async (type: string, id: number) => {
    try {
      const response: Comentario[] = await useApiFetch(API.COMMENTS.ALL, {
        method: 'GET',
        params: {
          objetoComentado: type,
          idObjetoComentado: id,
        }
      }); 

      if (response) return response as Comentario[];
      showError('No se han encontrado comentarios.');
    } catch (error: any) {
      showError('No se has podido obtener los comentarios.');
    }
  }

  return {
    getCommentsByObjectId
  }
}
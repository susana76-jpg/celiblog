export const useUserActions = () => {
  const { showSuccess, showError } = useNotification();

  /**
   * Adds an item to the user's favorites list.
   * 
   * @param itemId - The unique identifier of the item to add to favorites
   * @param type - The type of object being favorited (RECETA, RESTAURANTE, POST)
   */
  const addToFavorites = async (itemId: number, type: ObjectType) => {
    try {
      const response = await useApiFetch(API.FAVORITES.ADD, {
        method: 'GET',
        params: {
          idReferencia: itemId,
          tipoReferencia: type,
        }
      }); 

      showSuccess('Añadido a favoritos');
      return { success: true, data: response }
    } catch (error: any) {
      showError('No se ha podido añadir a favoritos');
      return { 
        success: false,   
        error: error?.data?.message || error?.message || 'Error al añadir a favoritos'
      }

    }
  };

  
  /**
   * Removes an item from the user's favorites list.
   * 
   * @param itemId - The unique identifier of the favorite item to remove
   */
  const removeFromFavorites = async (itemId: number) => {
    try {
      const response = await useApiFetch(API.FAVORITES.REMOVE, {
        method: 'DELETE',
        params: {
          idFavorito: itemId
        }
      });    

      showSuccess('Eliminado de favoritos');
      return { success: true, data: response }
    } catch (error: any) {
      showError('No se ha podido eliminar de favoritos');
      return {
        success: false,
        error: error?.data?.message || error?.message || 'Error al eliminar de favoritos'
      }
    }
  };


  /**
   * Adds a comment to a specific object (recipe, restaurant, or post).
   * 
   * @param commentData - The comment data including the object type and content
   * @returns A promise that resolves with success status and data or error message
   */
  const addComment = async (commentData: CommentData) => { 
    try {
      const url = `${API.COMMENTS.ADD}?objetoComentado=${commentData.objetoComentado}`;
      const { objetoComentado, ...bodyData } = commentData;
      
      const response = await useApiFetch(url, {
        method: 'POST',
        body: bodyData
      });

      showSuccess('Comentario añadido');
      return { success: true, data: response }
    } catch (error: any) {
      showError('No se ha podido añadir el comentario');
      return {
        success: false,
        error: error?.data?.message || error?.message || 'Error al añadir el comentario'
      }
    }
  };

  return {
    addToFavorites,
    removeFromFavorites,
    addComment
  }
}

export const useUserActions = () => {


  /**
   * Adds an item to the user's favorites list.
   * 
   * @param {number} itemId - The unique identifier of the item to add to favorites
   * @param {ObjectType} type - The type of object being favorited (RECETA, RESTAURANTE, POST)
   * @returns {Promise<{success: true, data: any} | {success: false, error: string}>} 
   */
  const addToFavorites = async (itemId: number, type: ObjectType) => {
    try {
      const response = await useApiFetch(API.FAVORITES.ADD, {
        method: 'POST',
        body: {
          tipoReferencia: type,
          idReferencia: itemId
        }
      }); 


      return { success: true, data: response }
    } catch (error: any) {
      return { 
        success: false,   
        error: error?.data?.message || error?.message || 'Error al añadir a favoritos'
      }

    }
  };

  /**
   * Removes an item from the user's favorites list.
   * 
   * @param {number} itemId - The unique identifier of the favorite item to remove
   * @returns {Promise<{success: true, data: any} | {success: false, error: string}>} 
   */
  const removeFromFavorites = async (itemId: number) => {
    try {
      const response = await useApiFetch(API.FAVORITES.REMOVE, {
        method: 'DELETE',
        body: {
          idFavorito: itemId
        }
      });    

      return { success: true, data: response }
    } catch (error: any) {
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

      return { success: true, data: response }
    } catch (error: any) {
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

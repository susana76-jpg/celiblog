export interface Recipe {
  idReceta: number
  titulo: string
  descripcion: string
  imagenUrl?: string
  dificultad: string
  fechaCreacion: string
  estado: 'APROBADO' | 'PENDIENTE' | 'RECHAZADO'
}

export interface RecipeStep {
  idPasoReceta: number
  orden: number
  descripcion: string
}

export const useRecipeApi = () => {
  const config = useRuntimeConfig()
  const baseURL = config.public.apiBase || 'http://46.183.113.124:8081'

  const getAllRecipes = async (): Promise<Recipe[]> => {
    return useFetch(`${baseURL}/api/receta/all`).then(res => res.data.value as Recipe[])
  }

  const getRecipeById = async (id: number): Promise<Recipe> => {
    return useFetch(`${baseURL}/api/receta/byId?id=${id}`).then(res => res.data.value as Recipe)
  }

  const getRecipeSteps = async (idReceta: number): Promise<RecipeStep[]> => {
    return useFetch(`${baseURL}/api/receta/pasos?idReceta=${idReceta}`).then(res => res.data.value as RecipeStep[])
  }

  return {
    getAllRecipes,
    getRecipeById,
    getRecipeSteps
  }
}
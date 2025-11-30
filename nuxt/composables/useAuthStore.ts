import type { Usuario } from '~/types/usuario'

interface AuthState {
  user: Usuario | null
  token: string | null
}

export const useAuthStore = () => {
  const token = useState<string | null>('auth-token', () => null)
  const user = useState<Usuario | null>('auth-user', () => null)

  // Initialize from localStorage on client side
  if (process.client) {
    const storedToken = localStorage.getItem('auth-token')
    const storedUser = localStorage.getItem('auth-user')
    
    if (storedToken && !token.value) {
      token.value = storedToken
    }
    if (storedUser && !user.value) {
      try {
        user.value = JSON.parse(storedUser)
      } catch (e) {
        console.error('Error parsing stored user:', e)
        localStorage.removeItem('auth-user')
      }
    }
  }

  const setAuth = (newToken: string, newUser: Usuario) => {
    token.value = newToken
    user.value = newUser
    
    if (process.client) {
      localStorage.setItem('auth-token', newToken)
      localStorage.setItem('auth-user', JSON.stringify(newUser))
    }
  }

  const clearAuth = () => {
    token.value = null
    user.value = null
    
    if (process.client) {
      localStorage.removeItem('auth-token')
      localStorage.removeItem('auth-user')
    }
  }

  const isAuthenticated = computed(() => !!token.value)

  const login = async (email: string, password: string) => {
    try {
      const config = useRuntimeConfig()
      const response = await $fetch<{ token: string; user: Usuario }>('/auth/login', {
        method: 'POST',
        baseURL: config.public.apiBase,
        body: { email, password }
      })

      if (response.token && response.user) {
        setAuth(response.token, response.user)
        return { success: true, user: response.user }
      }
      
      return { success: false, error: 'Invalid response from server' }
    } catch (error: any) {
      console.error('Login error:', error)
      return { 
        success: false, 
        error: error?.data?.message || error?.message || 'Login failed' 
      }
    }
  }

  const register = async (userData: { email: string; password: string; nombre: string }) => {
    try {
      const config = useRuntimeConfig()
      const response = await $fetch<{ token: string; user: Usuario }>('/auth/register', {
        method: 'POST',
        baseURL: config.public.apiBase,
        body: userData
      })

      if (response.token && response.user) {
        setAuth(response.token, response.user)
        return { success: true, user: response.user }
      }
      
      return { success: false, error: 'Invalid response from server' }
    } catch (error: any) {
      console.error('Registration error:', error)
      return { 
        success: false, 
        error: error?.data?.message || error?.message || 'Registration failed' 
      }
    }
  }

  const logout = () => {
    clearAuth()
    if (process.client) {
      navigateTo('/inicio')
    }
  }

  const refreshUser = async () => {
    if (!token.value) return

    try {
      const config = useRuntimeConfig()
      const response = await $fetch<Usuario>('/auth/me', {
        method: 'GET',
        baseURL: config.public.apiBase,
        headers: {
          Authorization: `Bearer ${token.value}`
        }
      })

      user.value = response
      if (process.client) {
        localStorage.setItem('auth-user', JSON.stringify(response))
      }
    } catch (error) {
      console.error('Error refreshing user:', error)
      clearAuth()
    }
  }

  return {
    token: readonly(token),
    user: readonly(user),
    isAuthenticated,
    login,
    register,
    logout,
    setAuth,
    clearAuth,
    refreshUser
  }
}

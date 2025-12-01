export const useAuthStore = () => {
  const token = useState<string | null>('auth-token', () => null);
  const user = useState<UsuarioLogin | null>('auth-user', () => null);
  const isAuthenticated = computed(() => !!token.value);

  // Initialize from localStorage on client side
  if (import.meta.client) {
    const storedToken = localStorage.getItem('auth-token');
    const storedUser = localStorage.getItem('auth-user');
    
    if (storedToken && !token.value) token.value = storedToken;

    if (storedUser && !user.value) {
      try {
        user.value = JSON.parse(storedUser);
      } catch (e) {
        console.error('Error parsing stored user:', e);
        localStorage.removeItem('auth-user');
      }
    }
  }

  // Set token and user in both state and localStorage
  const setAuth = (newToken: string, newUser: UsuarioLogin) => {
    token.value = newToken;
    user.value = newUser;
    
    if (import.meta.client) {
      localStorage.setItem('auth-token', newToken)
      localStorage.setItem('auth-user', JSON.stringify(newUser))
    }
  };

  // Clear token and user from both state and localStorage
  const clearAuth = () => {
    token.value = null;
    user.value = null;

    if (import.meta.client) {
      localStorage.removeItem('auth-token');
      localStorage.removeItem('auth-user');
    }
  };

  // User login
  const login = async (userData: { email: string; password: string }) => {
    try {
      const response: LoginResponse = await useApiFetch(API.USER.LOGIN, {
        method: 'POST',
        body: userData
      });

      // TODO: Remove this mock data when backend is ready
      response.usuarioLogin =  {
        idUsuario: 1,
        nombre: 'Nuria',
        email: 'nuria@gmail.com',
        password: 'nuria123',
        rol: {
          idRol: 1,
          nombre: 'Admin'
        },
        fechaAlta: '2024-01-01'
      }

      if (response.accessToken && response.usuarioLogin) {
        setAuth(response.accessToken, response.usuarioLogin)
        return { success: true, user: response.usuarioLogin }
      }
      
      return { success: false, error: 'Respuesta inválida del servidor' }
    } catch (error: any) {
      return { 
        success: false, 
        error: error?.data?.message || error?.message || 'Error al iniciar sesión' 
      }
    }
  }

  // User registration
  const register = async (userData: { email: string; password: string; nombre: string }) => {
    try {
      const response: LoginResponse = await useApiFetch(API.USER.REGISTER, {
        method: 'POST',
        body: userData
      });

      if (response.accessToken && response.usuarioLogin) {
        setAuth(response.accessToken, response.usuarioLogin)
        return { success: true, user: response.usuarioLogin }
      }
      
      return { success: false, error: 'Respuesta inválida del servidor' }
    } catch (error: any) {
      return { 
        success: false, 
        error: error?.data?.message || error?.message || 'Error al registrarse' 
      }
    }
  }

  // User logout
  const logout = () => {
    clearAuth();
    if (import.meta.client) navigateTo('/inicio');
  }

  const refreshUser = async () => {
    // if (!token.value) return

    // try {
    //   const config = useRuntimeConfig()
    //   const response = await $fetch<Usuario>('/auth/me', {
    //     method: 'GET',
    //     baseURL: config.public.apiBase,
    //     headers: {
    //       Authorization: `Bearer ${token.value}`
    //     }
    //   })

    //   user.value = response
    //   if (process.client) {
    //     localStorage.setItem('auth-user', JSON.stringify(response))
    //   }
    // } catch (error) {
    //   console.error('Error refreshing user:', error)
    //   clearAuth()
    // }
  }

  return {
    token: readonly(token),
    user: readonly(user),
    isAuthenticated,
    login,
    register,
    logout,
    refreshUser
  }
}

export const useAuthStore = () => {
  const token = useState<string | null>('auth-token', () => null);
  const user = useState<UsuarioLogin | null>('auth-user', () => null);
  const isAuthenticated = computed(() => !!token.value);
  const isAdmin = computed(() => user.value?.rol?.idRol === 1);

  // Initialize from sessionStorage on client side
  if (import.meta.client) {
    const storedToken = sessionStorage.getItem('auth-token');
    const storedUser = sessionStorage.getItem('auth-user');
    
    if (storedToken && !token.value) token.value = storedToken;

    if (storedUser && !user.value) {
      try {
        user.value = JSON.parse(storedUser);
      } catch (e) {
        console.error('Error parsing stored user:', e);
        sessionStorage.removeItem('auth-user');
      }
    }
  }


  /**
   * Sets the authentication token and user data in both state and sessionStorage.
   * @param {string} newToken - The authentication token to store
   * @param {UsuarioLogin} newUser - The user data to store
   */
  const setAuth = (newToken: string, newUser: UsuarioLogin) => {
    token.value = newToken;
    user.value = newUser;
    
    if (import.meta.client) {
      sessionStorage.setItem('auth-token', newToken)
      sessionStorage.setItem('auth-user', JSON.stringify(newUser))
    }
  };


  /**
   * Updates the user data in both state and sessionStorage.
   * @param {UsuarioLogin} newUser - The updated user data to store
   */
  const updateUser = (newUser: UsuarioLogin) => {
    user.value = newUser;
    
    if (import.meta.client) {
      sessionStorage.setItem('auth-user', JSON.stringify(newUser))
    }
  };


  /**
   * Clears the authentication token and user data from both state and sessionStorage.
   */
  const clearAuth = () => {
    token.value = null;
    user.value = null;

    if (import.meta.client) {
      sessionStorage.removeItem('auth-token');
      sessionStorage.removeItem('auth-user');
    }
  };


  /**
   * Authenticates a user with email and password credentials.
   * @param userData - The login credentials
   * @param userData.email - The user's email address
   * @param userData.password - The user's password
   * @returns Login result with user data or error message
   */
  const login = async (userData: { email: string; password: string }) => {
    try {
      const response: LoginResponse = await useApiFetch(API.USER.LOGIN, {
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
        error: error?.data || 'Error al iniciar sesión' 
      }
    }
  }


  /**
   * Registers a new user account with the provided credentials.
   * @param userData - The registration data
   * @param userData.email - The user's email address
   * @param userData.password - The user's password
   * @param userData.nombre - The user's name
   * @returns Registration result with user data or error message
   */
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
        error: error?.data || 'Error al registrarse' 
      }
    }
  }

  
  /**
   * Logs out the current user by clearing authentication data and redirecting to the home page.
   */
  const logout = () => {
    clearAuth();
    if (import.meta.client) navigateTo('/inicio');
  }

  return {
    token: readonly(token),
    user,
    isAuthenticated,
    isAdmin,
    login,
    register,
    logout,
    updateUser,
  }
}

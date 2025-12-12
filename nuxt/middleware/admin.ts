export default defineNuxtRouteMiddleware((to, from) => {
  const { isAuthenticated, user } = useAuthStore();

  // If user is not authenticated, redirect to login/inicio page
  if (!isAuthenticated.value) {
    return navigateTo('/inicio');
  }

  // Check if user has admin role (assuming admin role has idRol = 1)
  // Adjust the idRol value based on your database configuration
  if (!user.value || user.value.rol.idRol !== 1) {
    // User is authenticated but doesn't have admin privileges
    return navigateTo('/usuario'); // Redirect to user profile page
  }
})

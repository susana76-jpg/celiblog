export default defineNuxtRouteMiddleware((to, from) => {
  const { isAuthenticated } = useAuthStore();

  // If user is not authenticated, redirect to login/inicio page
  if (!isAuthenticated.value) {
    return navigateTo('/inicio');
  }
})

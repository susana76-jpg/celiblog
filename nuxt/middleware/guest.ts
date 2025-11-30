export default defineNuxtRouteMiddleware((to, from) => {
  const { isAuthenticated } = useAuthStore()

  // If user is authenticated, redirect to user profile page
  if (isAuthenticated.value) {
    return navigateTo('/usuario')
  }
})

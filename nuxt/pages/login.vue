<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md="6" lg="4">
        <v-card class="pa-4">
          <v-card-title class="text-h5 text-center">Iniciar Sesión</v-card-title>
          
          <v-card-text>
            <v-form @submit.prevent="handleLogin">
              <v-text-field
                v-model="email"
                label="Email"
                type="email"
                required
                :error-messages="emailErrors"
              />
              
              <v-text-field
                v-model="password"
                label="Contraseña"
                type="password"
                required
                :error-messages="passwordErrors"
              />
              
              <v-alert
                v-if="errorMessage"
                type="error"
                class="my-4"
              >
                {{ errorMessage }}
              </v-alert>
              
              <v-btn
                type="submit"
                color="primary"
                block
                :loading="loading"
                class="mt-4"
              >
                Iniciar Sesión
              </v-btn>
            </v-form>
            
            <div class="text-center mt-4">
              <NuxtLink to="/registro">
                ¿No tienes cuenta? Regístrate aquí
              </NuxtLink>
            </div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: 'guest'
})

const { login } = useAuthStore()

const email = ref('')
const password = ref('')
const loading = ref(false)
const errorMessage = ref('')
const emailErrors = ref<string[]>([])
const passwordErrors = ref<string[]>([])

const handleLogin = async () => {
  // Reset errors
  emailErrors.value = []
  passwordErrors.value = []
  errorMessage.value = ''
  
  // Basic validation
  if (!email.value) {
    emailErrors.value = ['El email es requerido']
    return
  }
  if (!password.value) {
    passwordErrors.value = ['La contraseña es requerida']
    return
  }
  
  loading.value = true
  
  const result = await login(email.value, password.value)
  
  loading.value = false
  
  if (result.success) {
    // Redirect to user profile or home page
    await navigateTo('/usuario')
  } else {
    errorMessage.value = result.error || 'Error al iniciar sesión'
  }
}
</script>

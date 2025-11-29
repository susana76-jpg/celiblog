<template>
  <v-container fluid class="registro-page">

    <!-- Image and Welcome Text -------------------------------->
    <v-row class="registro-image">
      <v-img 
        cover
        height="100%"
        src="/img/welcome.jpg" 
        alt="Welcome Image" 
        gradient="to bottom, #836A02 0%, #836A02 36%, #836A0275 100%" 
      />
      <div class="registro-image__text">
        <h1>¡Bienvenid@!</h1>
        <p>Regístrate en nuestro blog para personas celíacas y accede a recetas seguras, guías prácticas y experiencias reales. Únete a una comunidad que comparte consejos, productos recomendados y apoyo diario para vivir sin gluten con tranquilidad y bienestar, cada día siempre.</p>
      </div>
    </v-row>
    <!---------------------------------------------------------->

    <!-- Registration Form ------------------------------------->
    <v-row class="registro-form">
      <div class="section-main__title">
        <h2>Rellena nuestro formulario de registro</h2>
        <p>Así tendrás acceso a todo nuestro contenido, pudiendo guardarlo como favorito y hacer comentarios.</p>
      </div>
      <v-form>
        <v-text-field
          v-for="(field, index) in textFields"
          :key="index"
          active
          required
          hide-details
          :type="field.type"
          density="comfortable"
          variant="outlined"
          class="mb-5 mt-3"
          color="primary"
          base-color="primary"
          :label="field.label"
          :prepend-inner-icon="field.icon"
          v-model="field.model.value"
        ></v-text-field>
        <v-btn
          block
          height="40"
          text="registro"
          variant="flat"
          color="primary"
          rounded="xl"
        />
      </v-form>

      <div class="mt-4 text-center">
        <p>Si ya eres usuario, <nuxt-link to="/login" class="text-primary font-weight-medium">inicia sesión aquí</nuxt-link>.</p>
      </div>
    </v-row>
    <!---------------------------------------------------------->

  </v-container>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: 'guest'
})

const { register } = useAuthStore()

const name = ref<string>('');
const email = ref<string>('');
const password = ref<string>('');
const confirmPassword = ref<string>('');


// Text fields configuration array
const textFields = [
  {
    label: 'Nombre *',
    icon: 'mdi-account',
    type: 'text',
    model: name
  },
  {
    label: 'Correo electrónico *',
    icon: 'mdi-email',
    type: 'text',
    model: email
  },
  {
    label: 'Contraseña *',
    icon: 'mdi-lock',
    type: 'password',
    model: password
  },
  {
    label: 'Confirmar contraseña *',
    icon: 'mdi-lock-check',
    type: 'password',
    model: confirmPassword
  }
];



const loading = ref(false);
const errorMessage = ref('');
const nameErrors = ref<string[]>([]);
const emailErrors = ref<string[]>([]);
const passwordErrors = ref<string[]>([]);
const confirmPasswordErrors = ref<string[]>([]);

const handleRegister = async () => {
  // Reset errors
  nombreErrors.value = []
  emailErrors.value = []
  passwordErrors.value = []
  confirmPasswordErrors.value = []
  errorMessage.value = ''
  
  // Basic validation
  if (!nombre.value) {
    nombreErrors.value = ['El nombre es requerido']
    return
  }
  if (!email.value) {
    emailErrors.value = ['El email es requerido']
    return
  }
  if (!password.value) {
    passwordErrors.value = ['La contraseña es requerida']
    return
  }
  if (password.value.length < 6) {
    passwordErrors.value = ['La contraseña debe tener al menos 6 caracteres']
    return
  }
  if (password.value !== confirmPassword.value) {
    confirmPasswordErrors.value = ['Las contraseñas no coinciden']
    return
  }
  
  loading.value = true
  
  const result = await register({
    nombre: nombre.value,
    email: email.value,
    password: password.value
  })
  
  loading.value = false
  
  if (result.success) {
    // Redirect to user profile
    await navigateTo('/usuario')
  } else {
    errorMessage.value = result.error || 'Error al registrarse'
  }
}
</script>

<style scoped lang="scss">
.registro-page {
  height: calc(100vh - 146px);
  display: flex;
  padding: 0;

  .registro-image {
    position: relative;
    width: 50%;
    margin: 0;

    &__text {
      width: 70%;
      text-align: center;
      position: absolute;
      top: 160px;
      right: 50%;
      transform: translateX(50%);
      color: white;

      h1 {
        font-size: 2.5rem;
        margin-bottom: 10px;
      }

      p {
        font-size: 1.2rem;
        color: #141414;
      }
    }
  }

  .registro-form {
    width: 50%;
    margin: 0;
    padding-left: 100px;
    padding-right: 100px;
    display: flex;
    flex-direction: column;
    justify-content: center;

    .section-main__title {
      width: 100%;
      margin-bottom: 30px;
      padding-top: 0;
      padding-bottom: 0;

      p {
        max-width: 100%;
      }
    }

    .v-form {
      width: 100%;
    }
  }
}
</style>
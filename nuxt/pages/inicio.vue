<template>
  <v-container fluid class="inicio-page">

    <!-- Registration Form ------------------------------------->
    <v-row class="inicio-form">
      <div class="section-main__title">
        <h2>Inicia sesión en tu cuenta</h2>
        <p>Accede a tus recetas guardadas, comenta en el blog y disfruta de todo nuestro contenido sin gluten.</p>
      </div>
      <v-form ref="formRef" @submit.prevent="handleLogin">
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
          :rules="field.rules"
          v-model="field.model.value"
        ></v-text-field>
        <v-alert v-if="errorMessage" type="error" class="mb-4">
          {{ errorMessage }}
        </v-alert>
        <v-btn
          block
          height="40"
          text="inicio de sesión"
          variant="flat"
          color="primary"
          rounded="xl"
          type="submit"
          :loading="loading"
        />
      </v-form>

      <div class="mt-4 text-center">
        <p>Si aún no eres usuario de CeliBlog, <nuxt-link to="/registro" class="text-primary font-weight-medium">regístrate aquí</nuxt-link>.</p>
      </div>
    </v-row>
    <!---------------------------------------------------------->

    <!-- Image and Welcome Text -------------------------------->
    <v-row class="inicio-image">
      <v-img 
        cover
        height="100%"
        src="/img/welcome.jpg" 
        alt="Welcome Image" 
        gradient="to bottom, #836A02 0%, #836A02 36%, #836A0275 100%" 
      />
      <div class="inicio-image__text">
        <h1>¡Hola de nuevo!</h1>
        <p>Inicia sesión para acceder a tu espacio personalizado: guarda tus recetas sin gluten, sigue tus contenidos favoritos y participa en la comunidad. Vuelve a disfrutar de todo lo que nuestro blog para personas celíacas ofrece cada día.</p>
      </div>
    </v-row>
    <!---------------------------------------------------------->

  </v-container>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: 'guest'
})

const { login } = useAuthStore()

const formRef = ref<HTMLFormElement | null>(null);
const email = ref<string>('');
const password = ref<string>('');
const loading = ref<boolean>(false);
const errorMessage = ref<string>('');

// Validation rules
const validationRules = {
  email: [
    (v: string) => !!v || 'El email es requerido',
    (v: string) => /.+@.+\..+/.test(v) || 'El email debe ser válido'
  ],
  password: [
    (v: string) => !!v || 'La contraseña es requerida',
    (v: string) => v.length >= 6 || 'La contraseña debe tener al menos 6 caracteres'
  ],
}

// Text fields configuration array
const textFields = [
  {
    label: 'Correo electrónico *',
    icon: 'mdi-email',
    type: 'text',
    model: email,
    rules: validationRules.email
  },
  {
    label: 'Contraseña *',
    icon: 'mdi-lock',
    type: 'password',
    model: password,
    rules: validationRules.password
  },
];


const handleLogin = async () => {
  errorMessage.value = '';
  const { valid } = await formRef.value?.validate();
  
  if (!valid) return;
  
  loading.value = true;
  
  try {
    const data = await useApiFetch(API.USER.LOGIN, {
      method: 'POST',
      body: {
        username: email.value,
        password: password.value,
      }
    });

    console.log('Login successful:', data);
    await navigateTo('/usuario');

  } catch (error: any) {
    console.error('Login error:', error);
    errorMessage.value = error?.data?.message || 'Error al iniciar sesión';
  }
  
  loading.value = false;
}
</script>
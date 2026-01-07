<template>
  <v-container fluid class="registro-page">

    <!-- IMAGE AND WELCOME TEXT -------------------------------->
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

    <!-- REGISTRATION FORM ------------------------------------->
    <v-row class="registro-form">
      <div class="section-main__title">
        <h2>Rellena nuestro formulario de registro</h2>
        <p>Así tendrás acceso a todo nuestro contenido, pudiendo guardarlo como favorito y hacer comentarios.</p>
      </div>
      <v-form 
        ref="formRef" 
        validate-on="submit"
        @submit.prevent="handleRegister"
      >
        <v-text-field
          v-for="(field, index) in textFields"
          :key="index"
          active
          required
          hide-details="auto"
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
        <v-alert 
          v-if="message.show"
          class="mb-4"
          density="compact"
          :type="message.type" 
        >
          {{ message.text }}
        </v-alert>
        <v-btn
          block
          height="40"
          text="registro"
          variant="flat"
          color="primary"
          rounded="xl"
          type="submit"
          :loading="loading"
        />
      </v-form>

      <div class="mt-4 text-center">
        <p>Si ya eres usuario, <nuxt-link to="/inicio" class="text-primary font-weight-medium">inicia sesión aquí</nuxt-link>.</p>
      </div>
    </v-row>
    <!---------------------------------------------------------->

  </v-container>
</template>

<script setup lang="ts">
const { register } = useAuthStore();

const formRef = ref<HTMLFormElement | null>(null);
const name = ref<string>('');
const email = ref<string>('');
const password = ref<string>('');
const confirmPassword = ref<string>('');
const loading = ref<boolean>(false);
const message = ref<{ show: boolean; text: string; type: string }>({ 
  show: false, 
  text: '', 
  type: 'error' 
});

// Validation rules
const validationRules = {
  name: [
    (v: string) => !!v || 'El nombre es requerido',
    (v: string) => v.length >= 2 || 'El nombre debe tener al menos 2 caracteres'
  ],
  email: [
    (v: string) => !!v || 'El email es requerido',
    (v: string) => /.+@.+\..+/.test(v) || 'El email debe ser válido'
  ],
  password: [
    (v: string) => !!v || 'La contraseña es requerida',
    (v: string) => v.length >= 6 || 'La contraseña debe tener al menos 6 caracteres'
  ],
  confirmPassword: [
    (v: string) => !!v || 'Confirma tu contraseña',
    (v: string) => v === password.value || 'Las contraseñas no coinciden'
  ]
};

// Text fields configuration array
const textFields = [
  {
    label: 'Nombre *',
    icon: 'mdi-account',
    type: 'text',
    model: name,
    rules: validationRules.name
  },
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
  {
    label: 'Confirmar contraseña *',
    icon: 'mdi-lock-check',
    type: 'password',
    model: confirmPassword,
    rules: validationRules.confirmPassword
  }
];

// Handle registration
const handleRegister = async () => {
  message.value.show = false;
  const { valid } = await formRef.value?.validate();
  
  if (!valid) return;
  
  loading.value = true;
  
  const result = await register({
    nombre: name.value,
    email: email.value,
    password: password.value
  });

  if (result.success) setSuccessMessage();
  else setErrorMessage(result.error || 'Error al registrarse');
  
  loading.value = false;
};

// Set success message and redirect to login
const setSuccessMessage = () => {
  message.value = {
    show: true,
    text: 'Registro exitoso. Ya puedes iniciar sesión.',
    type: 'success'
  };

  setTimeout(() => navigateTo('/inicio'), 3000);
};

// Set error message
const setErrorMessage = (errorText: string) => {
  message.value = {
    show: true,
    text: errorText,
    type: 'error'
  };
};
</script>

<style lang="scss">
.registro-page, .inicio-page {
  height: calc(100vh - 145px);
  display: flex;
  padding: 0;

  .registro-image, .inicio-image {
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

  .registro-form, .inicio-form {
    width: 50%;
    margin: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-top: 3px solid #836A02;
    

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

.registro-page {
  .registro-form {
    padding-right: 120px;
    padding-left: 100px;
  }
}
</style>
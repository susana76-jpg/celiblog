<template>
  <v-card class="user-info-card d-flex align-center" elevation="0">

    <!-- USER PHOTO ----------------------------->
    <v-avatar size="180" class="user-avatar">
      <v-img src="/img/avator.png" alt="User Photo" />
    </v-avatar>
    <!------------------------------------------->

    <!-- USER DETAILS FORM --------------------->
    <v-form 
      ref="userForm" 
      validate-on="submit" 
      class="user-details ml-8 flex-grow-1"
    >
      <v-text-field
        v-for="field in textFields"
        :key="field.model"
        active
        required
        single-line
        variant="outlined"
        density="comfortable"
        color="primary"
        base-color="primary"
        class="mb-4"
        :readonly="field.readonly"
        :hide-details="field.hideDetails"
        :prepend-inner-icon="field.icon"
        :rules="field.rules"
        v-model="formData[field.model]"
      />
      <div class="d-flex justify-space-between">
        <v-btn
          v-for="btn in buttons"
          :key="btn.text"
          height="40" 
          rounded="xl"
          variant="outlined"
          color="white"
          :class="btn.class"
          :text="btn.text"
          :type="btn.type"
          :loading="btn.loading"
          :disabled="btn.disabled"
          @click="btn.action"
        />
      </div>
    </v-form>
    <!------------------------------------------->

  </v-card>
</template>
<script setup lang="ts">
const { user, logout, updateUser } = useAuthStore();
const { showSuccess, showError } = useNotification();
const { showConfirmDialog } = useConfirmDialog();

/**********************************/
/* FORM AND INPUTS */
/**********************************/
// Form data
const formData = ref<{ nombre: string; email: string }>({
  nombre: "",
  email: "",
});

// Text fields configuration
const textFields = [
  {
    model: 'nombre' as const,
    icon: 'mdi-account',
    hideDetails: 'auto' as const,
    readonly: false,
    rules: [(v: any) => !!v || 'El nombre es obligatorio']
  },
  {
    model: 'email' as const,
    icon: 'mdi-email',
    hideDetails: true,
    readonly: true,
    rules: []
  }
];

// Buttons configuration
const isSubmitting = ref(false);
const buttons = computed(() => [
  {
    text: 'Eliminar cuenta',
    class: 'mr-2 flex-grow-1',
    type: undefined,
    loading: undefined,
    disabled: undefined,
    action: (e: Event) => {
      e.preventDefault();
      deleteUserAccount();
    }
  },
  {
    text: 'Guardar cambios',
    class: 'flex-grow-1',
    type: 'submit',
    loading: isSubmitting.value,
    disabled: formData.value.nombre === user.value?.nombre,
    action: (e: Event) => {
      e.preventDefault();
      updateUserData();
    }
  }
]);


/**********************************/
/* METHODS */
/**********************************/
// Confirm user wanting to delete account
const deleteUserAccount = async () => {
  const text = `Si eliminas esta cuenta desaparecerá del sistema. ¿Deseas continuar?`;
  showConfirmDialog(text, () => deleteUser());
};

// Delete user account
const deleteUser = async () => {
  try {
    await useApiFetch(API.USERS.DELETE, { 
      method: 'DELETE',
      params: { idUsuario: user.value?.idUsuario } 
    });

    showSuccess('Cuenta eliminada correctamente.');
    setTimeout(() => {
      logout();
      window.location.href = '/';
    }, 3000);
  } catch (err) {
    showError(`Error al eliminar la cuenta: ${err}`);
  }
};

// Validate user data and update
const userForm = ref();
const updateUserData = async () => {
  isSubmitting.value = true;

  const { valid } = await userForm.value?.validate();
  if (!valid) {
    isSubmitting.value = false;
    return;
  }

  try {
    const response = await useApiFetch(API.USERS.UPDATE_NAME, {
      method: 'PUT',
      params: { nombre: formData.value.nombre }
    });

    updateUser(response as UsuarioLogin);
    showSuccess('Nombre actualizado correctamente.');
  } catch (err) {
    showError('Error al actualizar el nombre.');
  } finally {
    isSubmitting.value = false;
  }
};

// Set basic user info on mount
onMounted(() => {
  if (user.value) {
    formData.value.nombre = user.value.nombre;
    formData.value.email = user.value.email;
  }
});
</script>
<style lang="scss">
.user-info-card {
  padding: 20px 40px;
  background-color: #836a02;
  border-radius: 8px;

  .user-details {
    .v-field {
      background-color: #ffffff;
    }

    .v-field__overlay {
      border: 2px solid #836a02;
    }
  }
}
</style>

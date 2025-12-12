<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-card-title class="text-h5">
            Perfil de Usuario
          </v-card-title>
          
          <v-card-text v-if="user">
            <v-list>
              <v-list-item>
                <v-list-item-title>Nombre</v-list-item-title>
                <v-list-item-subtitle>{{ user.nombre }}</v-list-item-subtitle>
              </v-list-item>
              
              <v-list-item>
                <v-list-item-title>Email</v-list-item-title>
                <v-list-item-subtitle>{{ user.email }}</v-list-item-subtitle>
              </v-list-item>
              
              <v-list-item>
                <v-list-item-title>Rol</v-list-item-title>
                <v-list-item-subtitle>{{ user.rol.nombre }}{{ user.rol.idRol }}</v-list-item-subtitle>
              </v-list-item>
              
              <v-list-item>
                <v-list-item-title>Fecha de Alta</v-list-item-title>
                <v-list-item-subtitle>{{ formatDate(user.fechaAlta) }}</v-list-item-subtitle>
              </v-list-item>
            </v-list>
          </v-card-text>
          
          <v-card-actions>
            <v-spacer />
            <v-btn color="error" @click="handleLogout">
              Cerrar Sesión
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: 'auth'
})

const { user, logout } = useAuthStore()

const formatDate = (dateString: string) => {
  try {
    return new Date(dateString).toLocaleDateString('es-ES')
  } catch {
    return dateString
  }
}

const handleLogout = () => {
  logout()
}
</script>
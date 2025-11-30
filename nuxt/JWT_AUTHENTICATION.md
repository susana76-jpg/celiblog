# JWT Authentication Implementation in Nuxt 3

This document explains the JWT authentication implementation in the CeliBlog Nuxt project.

## Overview

The authentication system uses JWT (JSON Web Tokens) for secure user authentication. Tokens are stored in localStorage and automatically included in API requests.

## Components

### 1. Auth Store (`composables/useAuthStore.ts`)

The main authentication composable that manages:
- User state and JWT token
- Login/logout functionality
- User registration
- Token persistence in localStorage

**Usage:**
```typescript
const { user, token, isAuthenticated, login, logout, register } = useAuthStore()
```

**Available methods:**
- `login(email, password)` - Authenticate user and store token
- `register(userData)` - Register new user and store token
- `logout()` - Clear auth state and redirect
- `refreshUser()` - Refresh user data from API
- `setAuth(token, user)` - Manually set authentication state
- `clearAuth()` - Clear authentication state

### 2. API Fetch Wrapper (`composables/useApiFetch.ts`)

Enhanced version of `useFetch` that automatically:
- Includes JWT token in Authorization header
- Uses configured API base URL

**Usage:**
```typescript
const { data, error } = await useApiFetch('/api/endpoint')
```

### 3. Middleware

#### `middleware/auth.ts`
Protects routes requiring authentication. Redirects to `/inicio` if not logged in.

**Usage in pages:**
```vue
<script setup>
definePageMeta({
  middleware: 'auth'
})
</script>
```

## Pages

### Login Page (`pages/login.vue`)
- Email/password login form
- Uses `guest` middleware
- Redirects to `/usuario` on success

### Register Page (`pages/registro.vue`)
- User registration form with validation
- Password confirmation
- Uses `guest` middleware
- Redirects to `/usuario` on success

### User Profile Page (`pages/usuario.vue`)
- Protected with `auth` middleware
- Displays user information
- Logout button

## API Endpoints Expected

The frontend expects the following API endpoints:

### POST `/auth/login`
**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "idUsuario": 1,
    "email": "user@example.com",
    "nombre": "John Doe",
    "rol": {
      "idRol": 1,
      "nombre": "USER"
    },
    "fechaAlta": "2024-01-01",
    "fechaAta": "2024-01-01"
  }
}
```

### POST `/auth/register`
**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "nombre": "John Doe"
}
```

**Response:** Same as login

### GET `/auth/me`
**Headers:**
```
Authorization: Bearer <token>
```

**Response:**
```json
{
  "idUsuario": 1,
  "email": "user@example.com",
  "nombre": "John Doe",
  "rol": {
    "idRol": 1,
    "nombre": "USER"
  },
  "fechaAlta": "2024-01-01",
  "fechaAta": "2024-01-01"
}
```

## Usage Examples

### Protecting a Route
```vue
<!-- pages/admin.vue -->
<template>
  <div>Admin only content</div>
</template>

<script setup>
definePageMeta({
  middleware: 'auth'
})
</script>
```

### Making Authenticated API Calls
```vue
<script setup>
const { data, error } = await useApiFetch('/api/user/favorites', {
  method: 'POST',
  body: { recipeId: 123 }
})
</script>
```

### Accessing User Data
```vue
<script setup>
const { user, isAuthenticated } = useAuthStore()
</script>

<template>
  <div v-if="isAuthenticated">
    <p>Welcome, {{ user.nombre }}!</p>
  </div>
</template>
```

### Custom Login Logic
```vue
<script setup>
const { login } = useAuthStore()

async function handleLogin() {
  const result = await login('user@example.com', 'password123')
  
  if (result.success) {
    console.log('Logged in:', result.user)
    // Navigate or show success
  } else {
    console.error('Login failed:', result.error)
    // Show error message
  }
}
</script>
```

## Security Notes

1. **Token Storage**: Tokens are stored in localStorage. For more security-sensitive applications, consider using httpOnly cookies.

2. **Token Expiration**: The current implementation doesn't handle token expiration. Consider adding:
   - Token refresh mechanism
   - Automatic logout on token expiration
   - Interceptor to handle 401 responses

3. **HTTPS**: Always use HTTPS in production to protect tokens in transit.

4. **Environment Variables**: Store API URLs in environment variables:
   ```
   NUXT_PUBLIC_API_BASE=https://api.celiblog.com
   ```

## Future Enhancements

- [ ] Token refresh mechanism
- [ ] Remember me functionality
- [ ] Role-based access control middleware
- [ ] Social authentication (Google, Facebook)
- [ ] Password reset flow
- [ ] Email verification
- [ ] 2FA support

## Troubleshooting

### Token not being sent
Make sure you're using `useApiFetch` instead of regular `$fetch` or `useFetch`.

### User state not persisting
Check browser localStorage. Clear it and try logging in again.

### Middleware not working
Ensure `definePageMeta` is used at the top level of the `<script setup>` block.

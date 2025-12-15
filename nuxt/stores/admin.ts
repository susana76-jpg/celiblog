import { defineStore } from 'pinia';

export const useAdminStore = defineStore('admin', () => {
  // State - Users
  const keyfacts = ref({
    totalUsers: 0,
    totalRecipes: 0,
    totalComments: 0,
    totalRestaurants: 0,
    totalPosts: 0,
  })
  

  return {
    keyfacts,
  }
});

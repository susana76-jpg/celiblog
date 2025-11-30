export async function useApiFetch<T>(url: string, options?: any): Promise<T> {
  const config = useRuntimeConfig();
  // const { token } = useAuthStore();

  // Merge headers with authorization token if available
  // const headers: Record<string, string> = {
  //   'Content-Type': 'application/json',
  //   ...(options?.headers || {})
  // };

  // if (token.value) {
  //   headers['Authorization'] = `Bearer ${token.value}`;
  // }

  return $fetch<T>(url, {
    baseURL: config.public.apiBase,
    ...options,
    // headers,
  });
}
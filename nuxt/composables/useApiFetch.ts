export function useApiFetch<T>(url: string, options?: any) {
  const config = useRuntimeConfig();
  const { token } = useAuthStore();

  // Merge headers with authorization token if available
  const headers: Record<string, string> = {
    ...(options?.headers || {})
  };

  if (token.value) {
    headers['Authorization'] = `Bearer ${token.value}`;
  }

  return useFetch<T>(url, {
    baseURL: config.public.apiBase,
    ...options,
    headers,
  })
};
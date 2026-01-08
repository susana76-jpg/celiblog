export function usePagination<T>(
  items: Ref<T[]> | ComputedRef<T[]>,
  itemsPerPage: number = 12
) {
  const currentPage = ref<number>(1);
  const itemsPerPageRef = ref<number>(itemsPerPage);

  const totalPages = computed(() => 
    Math.ceil(items.value.length / itemsPerPageRef.value)
  );

  const paginatedItems = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPageRef.value;
    const end = start + itemsPerPageRef.value;
    return items.value.slice(start, end);
  });

  const onPageChange = (page: number) => {
    currentPage.value = page;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  // Reset to page 1 when items change
  watch(() => items.value.length, () => {
    if (currentPage.value > totalPages.value) {
      currentPage.value = 1;
    }
  });

  return {
    currentPage,
    itemsPerPage: itemsPerPageRef,
    totalPages,
    paginatedItems,
    onPageChange,
  };
}

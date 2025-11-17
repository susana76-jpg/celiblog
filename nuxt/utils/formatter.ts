export const setLevelChip = (level: string) => {
    switch (level) {
    case 'fácil':
      return { color: 'bg-success', rating: 1 };
    case 'media':
      return { color: 'bg-warning', rating: 2 };
    case 'difícil':
      return { color: 'bg-error', rating: 3 };
    default:
      return { color: 'bg-success', rating: 1 };
  }
};
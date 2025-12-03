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


export const setDate = (date: string) => {
  const texto = new Intl.DateTimeFormat("es-ES", {
    weekday: "long",
    day: "numeric",
    month: "long",
    year: "numeric"
  }).format(new Date(date));

  return texto.charAt(0).toUpperCase() + texto.slice(1);
};
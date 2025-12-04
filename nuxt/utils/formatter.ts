/**
 * Sets the color and rating for a difficulty level chip.
 * @param level - The difficulty level ('fácil', 'media', or 'difícil')
 * @returns An object containing the background color class and numeric rating
 */
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

/**
 * Formats a date string to Spanish locale format with capitalized first letter.
 * @param date - The date string to format
 * @returns The formatted date string (e.g., "Lunes 4 de diciembre de 2025")
 */
export const setDate = (date: string) => {
  const texto = new Intl.DateTimeFormat("es-ES", {
    weekday: "long",
    day: "numeric",
    month: "long",
    year: "numeric"
  }).format(new Date(date));

  return texto.charAt(0).toUpperCase() + texto.slice(1);
};

/**
 * Converts double line breaks in text to HTML line break tags.
 * @param text - The text to format
 * @returns The formatted HTML text with <br/> tags, or empty string if text is undefined
 */
export const formatHtmlText = (text: string | undefined) => {
  if (!text) return '';
  return text.replace(/\r\n\r\n/g, '<br/><br/>');
}
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
 * @param date - The date string to format (supports DD-MM-YYYY HH:mm:ss format)
 * @returns The formatted date string (e.g., "Lunes 4 de diciembre de 2025")
 */
export const setDate = (date: string) => {
  // Parse DD-MM-YYYY HH:mm:ss format
  const datePattern = /(\d{2})-(\d{2})-(\d{4})\s+(\d{2}):(\d{2}):(\d{2})/;
  const match = date.match(datePattern);
  
  let dateObj: Date;
  if (match) {
    const [, day, month, year, hours, minutes, seconds] = match;
    dateObj = new Date(
      parseInt(year),
      parseInt(month) - 1, // Months are 0-indexed
      parseInt(day),
      parseInt(hours),
      parseInt(minutes),
      parseInt(seconds)
    );
  } else {
    // Fallback to default Date parsing
    dateObj = new Date(date);
  }

  const texto = new Intl.DateTimeFormat("es-ES", {
    weekday: "long",
    day: "numeric",
    month: "long",
    year: "numeric"
  }).format(dateObj);

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

/**
 * Formats an ISO 8601 date string to a user-friendly Spanish format.
 * @param dateString - The ISO date string to format (e.g., "2025-12-17T15:32:25.000+00:00")
 * @returns The formatted date string (e.g., "17/12/2025, 15:32")
 */
export const formatDateTime = (dateString: string) => {
  const date = new Date(dateString);
  
  return new Intl.DateTimeFormat('es-ES', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  }).format(date);
}
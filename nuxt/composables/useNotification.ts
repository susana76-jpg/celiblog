export const useNotification = () => {
  const notification = useState<{
    show: boolean;
    message: string;
    color: 'success' | 'error' | 'warning' | 'info';
  }>('notification', () => ({
    show: false,
    message: '',
    color: 'success'
  }));

  const showNotification = (message: string, color: 'success' | 'error' | 'warning' | 'info' = 'success') => {
    notification.value = {
      show: true,
      message,
      color
    };
  };

  const showSuccess = (message: string) => {
    showNotification(message, 'success');
  };

  const showError = (message: string) => {
    showNotification(message, 'error');
  };

  const showWarning = (message: string) => {
    showNotification(message, 'warning');
  };

  const showInfo = (message: string) => {
    showNotification(message, 'info');
  };

  const hideNotification = () => {
    notification.value.show = false;
  };

  return {
    notification,
    showNotification,
    showSuccess,
    showError,
    showWarning,
    showInfo,
    hideNotification
  };
};

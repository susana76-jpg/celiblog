export const useConfirmDialog = () => {
  const isOpen = useState('confirmDialog_isOpen', () => false);
  const message = useState('confirmDialog_message', () => '');
  const confirmCallback = useState<(() => void) | null>('confirmDialog_callback', () => null);

  const showConfirmDialog = (
    dialogMessage: string,
    onConfirm: () => void
  ) => {
    message.value = dialogMessage;
    confirmCallback.value = onConfirm;
    isOpen.value = true;
  };

  const confirm = () => {
    if (confirmCallback.value) {
      confirmCallback.value();
    }
    closeDialog();
  };

  const closeDialog = () => {
    isOpen.value = false;
    message.value = '';
    confirmCallback.value = null;
  };

  return {
    isOpen,
    message,
    showConfirmDialog,
    confirm,
    closeDialog
  };
};

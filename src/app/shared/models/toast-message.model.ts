export interface ToastMessage {
  severity: 'success' | 'info' | 'warn' | 'error' | 'contrast' | 'secondary';
  summary: string;
  detail: string;
}

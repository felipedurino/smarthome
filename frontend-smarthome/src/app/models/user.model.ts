export interface User {
  id?: number;
  name: string;
  identification: number;
  email: string;
  birthDate: string; // Formato: yyyy-MM-dd
  password?: string;
}

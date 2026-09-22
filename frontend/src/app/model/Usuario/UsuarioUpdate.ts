export interface UsuarioUpdate {
  id: number;
  nombre: string;
  apellidos: string;
  fotoPerfil_url: string;
  email: string;
  password: string;
  fechaCreacion: Date;
  fechaActualizacion: Date;
  rol: string;
}

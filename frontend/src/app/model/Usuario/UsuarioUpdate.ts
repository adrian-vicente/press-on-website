export interface UsuarioUpdate {
  id: number;
  nombre: string;
  descripcion: string;
  fotoPerfil_url: string;
  email: string;
  password: string;
  fechaCreacion: Date;
  fechaActualizacion: Date;
}

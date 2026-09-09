package backend.backend_v1.service;

import org.springframework.stereotype.Service;

import backend.backend_v1.mapper.ColeccionMapper;
import backend.backend_v1.repository.ColeccionRepository;

@Service 
public class ColeccionService {

    // Inyección de dependencias

    private final ColeccionRepository coleccionRepository;
    private final ColeccionMapper coleccionMapper;

    public ColeccionService(ColeccionRepository coleccionRepository, ColeccionMapper coleccionMapper) {
        this.coleccionRepository = coleccionRepository;
        this.coleccionMapper = coleccionMapper;

    } 

    // Método para obtener una colección por id

    // Método para crear una nueva colección

    // Método para modificar una colección existente

    // Método para buscar una colección por nombre

    // Método para obtener todas las colecciones

} // class
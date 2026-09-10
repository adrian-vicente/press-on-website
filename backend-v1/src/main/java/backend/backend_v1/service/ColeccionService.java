package backend.backend_v1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import backend.backend_v1.dto.Coleccion.ColeccionCreateDTO;
import backend.backend_v1.dto.Coleccion.ColeccionDTO;
import backend.backend_v1.dto.Coleccion.ColeccionUpdateDTO;
import backend.backend_v1.exception.Coleccion.ColeccionAlreadyExistsException;
import backend.backend_v1.exception.Coleccion.ColeccionNotFoundException;
import backend.backend_v1.mapper.ColeccionMapper;
import backend.backend_v1.model.Coleccion;
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

    @Transactional(readOnly = true)
    public ColeccionDTO obtenerColeccionPorId(Long coleccion_id) throws ColeccionNotFoundException {
        Coleccion coleccion = coleccionRepository.findById(coleccion_id)
            .orElseThrow(() -> new ColeccionNotFoundException("No se ha encontrado ninguna colección con id: " + coleccion_id));

        return coleccionMapper.toDTO(coleccion);

    }

    // Método para crear una nueva colección

    @Transactional
    public ColeccionDTO crearNuevaColeccion(ColeccionCreateDTO coleccionCreate) throws ColeccionAlreadyExistsException {
        if(coleccionRepository.existsByNombre(coleccionCreate.getNombre())) {
            throw new ColeccionAlreadyExistsException("La colección con nombre: " + coleccionCreate.getNombre() + " ya existe. Cambia el nombre.");

        } // if

        Coleccion coleccionNueva = coleccionMapper.toEntityFromCreateDTO(coleccionCreate);
        return coleccionMapper.toDTO(coleccionRepository.saveAndFlush(coleccionNueva));

    }

    // Método para modificar una colección existente

    @Transactional
    public ColeccionDTO modificarColeccionExistente(Long coleccion_id, ColeccionUpdateDTO coleccionUpdate) throws ColeccionNotFoundException, ColeccionAlreadyExistsException {
        Coleccion coleccionActual = coleccionRepository.findById(coleccion_id)
            .orElseThrow(() -> new ColeccionNotFoundException("No se ha encontrado ninguna colección con id: " + coleccion_id));

        // Obtener la entidad a partir de la entidad actual con los datos modificados, devolver el objeto nuevo.

        Coleccion coleccionModificada = coleccionMapper.toEntityFromUpdateDTO(coleccionMapper.toDTO(coleccionActual), coleccionUpdate);
        return coleccionMapper.toDTO(coleccionRepository.saveAndFlush(coleccionModificada));

    }

    // Método para buscar una colección por nombre

    @Transactional(readOnly = true)
    public ColeccionDTO obtenerColeccionPorNombre(String nombre) throws ColeccionNotFoundException {
        Coleccion coleccion = coleccionRepository.findByNombre(nombre)
            .orElseThrow(() -> new ColeccionNotFoundException("No se ha encontrado ninguna colección con el nombre: " + nombre));

        return coleccionMapper.toDTO(coleccion);


    }

    // Método que permite añadir un producto nuevo a una colección existente

    

    // Método para obtener todas las colecciones

    @Transactional(readOnly = true)
    public List<ColeccionDTO> obtenerTodasLasColecciones() {
        List<Coleccion> colecciones = coleccionRepository.findAll();
        List<ColeccionDTO> coleccionesDTOs = new ArrayList<ColeccionDTO>();
        for(Coleccion coleccion : colecciones) coleccionesDTOs.add(coleccionMapper.toDTO(coleccion));
        return coleccionesDTOs;

    }

} // class
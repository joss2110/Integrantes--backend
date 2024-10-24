package pe.edu.cibertec.backend_integrantes.service;



import pe.edu.cibertec.backend_integrantes.dto.IntegrantesResponseDTO;
import pe.edu.cibertec.backend_integrantes.dto.LoginResquestDTO;

import java.io.IOException;
import java.util.List;

public interface AutenticacionService {

    String[] validarAlumno(LoginResquestDTO loginResquestDTO) throws IOException;
    List<IntegrantesResponseDTO> listarIntegrantes() throws IOException;
}

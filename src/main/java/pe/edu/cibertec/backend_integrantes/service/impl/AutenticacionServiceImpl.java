package pe.edu.cibertec.backend_integrantes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.backend_integrantes.dto.IntegrantesResponseDTO;
import pe.edu.cibertec.backend_integrantes.dto.LoginResquestDTO;
import pe.edu.cibertec.backend_integrantes.service.AutenticacionService;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    @Autowired
    ResourceLoader resourceLoader;


    @Override
    public String[] validarAlumno(LoginResquestDTO loginResquestDTO) throws IOException {
        String[] datosAlumno = null;
        Resource resource = resourceLoader.getResource("classpath:integrantes.txt");


        try(BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))){

            String linea;
            while ((linea = br.readLine()) !=null){
                String[] datos = linea.split(";");
                if(loginResquestDTO.codAlumno().equals(datos[0]) &&
                        loginResquestDTO.password().equals(datos[1])){
                    datosAlumno = new String[]{datos[2],datos[3]};
                    break;
                }
            }
        }catch (IOException e){
            datosAlumno= null;
            throw  new IOException(e);
        }
           return datosAlumno;
    }

    @Override
    public List<IntegrantesResponseDTO> listarIntegrantes() throws IOException {
        List<IntegrantesResponseDTO> integrantesList = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:integrantes.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");
                IntegrantesResponseDTO integrante = new IntegrantesResponseDTO(datos[2],datos[3]);
                integrantesList.add(integrante);
            }
        } catch (IOException e) {
            throw new IOException("Error reading integrantes.txt", e);
        }
        return integrantesList;
    }


    }




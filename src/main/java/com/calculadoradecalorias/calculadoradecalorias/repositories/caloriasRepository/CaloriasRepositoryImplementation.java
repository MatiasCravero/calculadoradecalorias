package com.calculadoradecalorias.calculadoradecalorias.repositories.caloriasRepository;

import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.IngredienteDto;
import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.IngredienteResponseDto;
import com.calculadoradecalorias.calculadoradecalorias.excepcionHandler.IngredientNotFound;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public class CaloriasRepositoryImplementation implements CaloriasRepository {

    @Override
    public IngredienteResponseDto findIngByName(String nombre) throws Exception {
        List<IngredienteResponseDto> ingDto = null;
        ingDto = localDataBase();
        IngredienteResponseDto result = null;
        if(ingDto != null){
            Optional<IngredienteResponseDto> item = ingDto.stream().filter(
                    x -> x.getName().equals(nombre)).findFirst();

            try {
                result = item.get();
            }
            catch (Exception e){
                throw new IngredientNotFound("El ingrediente" + nombre + "no existe");
            }

        }

        return result;

    }


    private List<IngredienteResponseDto> localDataBase() throws Exception{
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:food.json");
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<IngredienteResponseDto>> typeRef = new TypeReference<>(){};
        List<IngredienteResponseDto> ingredienteDtos = null;
        try{
            ingredienteDtos = objectMapper.readValue(file, typeRef);
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }

        return ingredienteDtos;
    }


}

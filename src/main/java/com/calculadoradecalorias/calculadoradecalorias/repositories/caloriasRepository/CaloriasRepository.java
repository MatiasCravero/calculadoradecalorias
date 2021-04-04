package com.calculadoradecalorias.calculadoradecalorias.repositories.caloriasRepository;

import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.IngredienteDto;
import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.IngredienteResponseDto;

public interface CaloriasRepository {
    IngredienteResponseDto findIngByName(String nombre) throws Exception;
}

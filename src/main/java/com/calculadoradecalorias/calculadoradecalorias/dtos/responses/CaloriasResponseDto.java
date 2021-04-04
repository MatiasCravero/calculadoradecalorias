package com.calculadoradecalorias.calculadoradecalorias.dtos.responses;

import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.IngredienteDto;
import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.IngredienteResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CaloriasResponseDto {
    private double CaloriasTotales;
    private IngredienteResponseDto ingredienteCalMax;
    private List<IngredienteResponseDto> calXIng;
    private String errMsg;
}

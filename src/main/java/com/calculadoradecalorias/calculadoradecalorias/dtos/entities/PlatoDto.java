package com.calculadoradecalorias.calculadoradecalorias.dtos.entities;

import lombok.Data;

import java.util.List;

@Data
public class PlatoDto {
    private String nombre;
    private List<IngredienteDto> ingredientes;
}

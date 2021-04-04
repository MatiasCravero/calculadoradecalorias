package com.calculadoradecalorias.calculadoradecalorias.services.caloriasServices;

import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.PlatoDto;
import com.calculadoradecalorias.calculadoradecalorias.dtos.responses.CaloriasResponseDto;

public interface CaloriasServices {
    CaloriasResponseDto calcular(PlatoDto plato) throws Exception;
}

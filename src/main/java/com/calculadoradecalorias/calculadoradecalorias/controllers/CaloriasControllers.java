package com.calculadoradecalorias.calculadoradecalorias.controllers;

import com.calculadoradecalorias.calculadoradecalorias.dtos.requests.CaloriasRequestDto;
import com.calculadoradecalorias.calculadoradecalorias.dtos.responses.CaloriasResponseDto;
import com.calculadoradecalorias.calculadoradecalorias.excepcionHandler.IngredientNotFound;
import com.calculadoradecalorias.calculadoradecalorias.services.caloriasServices.CaloriasServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calorias")
public class CaloriasControllers {
    private CaloriasServices calService;

    public CaloriasControllers(CaloriasServices calService){
        this.calService = calService;
    }
    @PostMapping
    public CaloriasResponseDto calcularCalorias(@RequestBody CaloriasRequestDto req){

        try {
            CaloriasResponseDto responseDto = calService.calcular(req.plato);
            return responseDto;
        }
        catch (Exception e){
            return new CaloriasResponseDto(0,null,null,e.getMessage());
        }

    }

    @ExceptionHandler(IngredientNotFound.class)
    public ResponseEntity<IngredientNotFound> retornarError(IngredientNotFound e){
        IngredientNotFound ex = new IngredientNotFound("El ingrediente no existe");
        ResponseEntity<IngredientNotFound> res = new ResponseEntity<IngredientNotFound>(ex,HttpStatus.BAD_REQUEST);
        return res;
    }

}

package com.calculadoradecalorias.calculadoradecalorias.services.caloriasServices;

import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.IngredienteDto;
import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.IngredienteResponseDto;
import com.calculadoradecalorias.calculadoradecalorias.dtos.entities.PlatoDto;
import com.calculadoradecalorias.calculadoradecalorias.dtos.responses.CaloriasResponseDto;
import com.calculadoradecalorias.calculadoradecalorias.repositories.caloriasRepository.CaloriasRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaloriasServicesImplementation implements CaloriasServices {


    private final CaloriasRepository calRepo;

    public CaloriasServicesImplementation(CaloriasRepository repo){
        this.calRepo = repo;
    }

    @Override
    public CaloriasResponseDto calcular(PlatoDto plato) throws Exception {
        try{
            List<IngredienteResponseDto> list = listaIng(plato);
            CaloriasResponseDto responseDto = new CaloriasResponseDto(totalCal(plato),maxCal(list), listaIng(plato), "OK");
            return responseDto;
        }
        catch  (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    private double totalCal(PlatoDto plato) throws Exception{
        double totalCal = 0;
        try {
            for (IngredienteDto ing : plato.getIngredientes()) {
                IngredienteResponseDto ingrediente = calRepo.findIngByName(ing.getNombre());
                totalCal += ingrediente.getCalories();
            }

            return totalCal;

        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    private ArrayList<IngredienteResponseDto> listaIng(PlatoDto plato) throws Exception{
        ArrayList<IngredienteResponseDto> listaIng = new ArrayList<>();
        try{
            for(IngredienteDto ing: plato.getIngredientes()){
                IngredienteResponseDto i = calRepo.findIngByName(ing.getNombre());
                listaIng.add(i);
            }

            return listaIng;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private IngredienteResponseDto maxCal(List<IngredienteResponseDto> list){
        IngredienteResponseDto max = list.get(0);
        for (IngredienteResponseDto i: list){
            if (i.getCalories() > max.getCalories()){
                max = i;
            }
        }

        return max;
    }


}

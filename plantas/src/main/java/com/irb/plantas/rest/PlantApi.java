package com.irb.plantas.rest;

import com.irb.plantas.model.Plant;
import com.irb.plantas.useCase.PlantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/plant")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200/")
public class PlantApi {

    private final PlantUseCase plantUseCase;

    @PostMapping()
    public ResponseEntity<Plant> createPlant(@Validated @RequestBody Plant plant){
        return new ResponseEntity<>(plantUseCase.createPlant(plant), HttpStatus.ACCEPTED);
    }

    @RequestMapping
    public ResponseEntity<List<Plant>> getUsers() {
        return new ResponseEntity<>(plantUseCase.getPlants(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET,  params = "categoryName")
    public  ResponseEntity<List<Plant>> getPlantByCategory(@RequestParam String categoryName) {
        return new ResponseEntity<>(plantUseCase.getPlantsByCategory(categoryName), HttpStatus.ACCEPTED);
    }
    @DeleteMapping
    public void deleteUser(@RequestParam UUID idPlant) {
        plantUseCase.deletePlant(idPlant);
    }


}


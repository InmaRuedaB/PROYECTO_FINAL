package com.irb.plantas.useCase;

import com.irb.plantas.exceptions.BadRequestException;
import com.irb.plantas.exceptions.NotFoundException;
import com.irb.plantas.model.Category;
import com.irb.plantas.model.Plant;
import com.irb.plantas.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import com.irb.plantas.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PlantUseCase {

    final PlantRepository plantRepository;
    final CategoryRepository categoryRepository;

    public Plant getPlant (String name){
        return this.plantRepository.findByName(name).orElseThrow(()-> new NotFoundException("Planta no encontrada"));
    }

    public Plant createPlant(Plant plant){
        plant.setId(UUID.randomUUID());
        Category category = categoryRepository.findByName(plant.getCategory().getName())
                .orElseThrow(() -> new BadRequestException("No se ha encontrado la categoría: "+ plant.getCategory().getName()));
        plant.setCategory(category);
        return this.plantRepository.save(plant);
    }

    public List<Plant> getPlantsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new BadRequestException("No se ha encontrado la categoría: "+ categoryName));
        return this.plantRepository.findByCategory(category);
    }

    public List<Plant> getPlants(){
        return this.plantRepository.findAll();
    }

    public void deletePlant(UUID id){
        this.plantRepository. deleteById(id);
    }

}

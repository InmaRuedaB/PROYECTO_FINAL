package com.irb.plantas.useCase;

import com.irb.plantas.model.Plant;
import com.irb.plantas.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PurchaseUseCase {

    final PlantRepository plantRepository;

    public void makePurchase(Plant[] plant) {
        Arrays.stream(plant).forEach((p) -> {
            Optional<Plant> aux = plantRepository.findById(p.getId());
            aux.get().setStock(aux.get().getStock()-p.getCount());
            plantRepository.save(aux.get());
        });
    }
}

package com.irb.plantas.rest;

import com.irb.plantas.model.Order;
import com.irb.plantas.model.Plant;
import com.irb.plantas.useCase.PurchaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseApi {

    private final PurchaseUseCase purchaseUseCase;
    @PostMapping
    public ResponseEntity<Void> makePurchase(@Validated @RequestBody Plant[] plant){
        purchaseUseCase.makePurchase(plant);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

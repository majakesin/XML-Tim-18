package com.example.advertmanagerapp.controller.rest;


import com.example.advertmanagerapp.dto.PriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RequestMapping("/advert-manager/discount")
@RestController
public class DiscountController {

    @PostMapping
    public ResponseEntity createDiscount () {

        return new ResponseEntity(HttpStatus.OK);
    }

    //nema klase discount??? Gdje smo to ostavili????
}

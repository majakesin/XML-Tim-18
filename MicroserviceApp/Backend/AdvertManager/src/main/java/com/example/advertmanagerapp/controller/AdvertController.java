package com.example.advertmanagerapp.controller;

import com.example.advertmanagerapp.dto.AdvertDto;
import com.example.advertmanagerapp.dto.CaptureDto;
import com.example.advertmanagerapp.dto.mapper.DtoEntity;
import com.example.advertmanagerapp.service.AdvertService;
import com.example.advertmanagerapp.service.CaptureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/advert-manager/advert")
@RestController
@CrossOrigin
public class AdvertController {

    private final AdvertService advertService;
    private final CaptureService captureService;

    @GetMapping ("/{advert_id}")
    public ResponseEntity<AdvertDto> getAdvertInfo(
            @PathVariable(value="advert_id") Long advert_id,
            @RequestParam(value = "details", required = false) String details ){

        return new ResponseEntity<AdvertDto>(HttpStatus.OK);
    }


    @PostMapping
    public void createAdvert (@RequestBody AdvertDto advertDto){
        advertService.createAdvert(advertDto);
    }


    @PutMapping
    public ResponseEntity updateAdvert (@RequestBody AdvertDto advertDto){
        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping ("/{advert_id}")
    public ResponseEntity deleteAdvert (@PathVariable (value="advert_id") Long advert_id){
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/number")
    public ResponseEntity<Integer> getAdvertNumber (){

        return new ResponseEntity<Integer>(HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<DtoEntity>> getAllAdverts () {
        //izmeniti da radi sa User adverts
        return new ResponseEntity<>(advertService.allAdverts(),HttpStatus.OK);
    }

    @PostMapping("/continue/{advert_id}")
    public ResponseEntity continueAdvertTime (@RequestBody Date date,@PathVariable(value="advert_id") Long advert_id ) {
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/capture/{id}")
    public ResponseEntity<List<DtoEntity>> getCarModel(@PathVariable("id") Long id){
        return new ResponseEntity<>(captureService.getAdvertCaptures(id),HttpStatus.OK);
    }

    @PostMapping("/capture")
    public void createCapture(@RequestBody CaptureDto captureDto){
        captureService.createNewCapture(captureDto);
    }
   

}

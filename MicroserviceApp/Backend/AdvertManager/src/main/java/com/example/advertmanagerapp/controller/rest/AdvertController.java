package com.example.advertmanagerapp.controller.rest;

import com.example.advertmanagerapp.dto.AdvertCartDTO;
import com.example.advertmanagerapp.dto.AdvertDetailDTO;
import com.example.advertmanagerapp.dto.AdvertDto;
import com.example.advertmanagerapp.dto.AdvertMiniDto;
import com.example.advertmanagerapp.dto.CaptureDto;
import com.example.advertmanagerapp.dto.mapper.DtoEntity;
import com.example.advertmanagerapp.service.AdvertService;
import com.example.advertmanagerapp.service.CaptureService;

import com.example.advertmanagerapp.service.CheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/advert-manager/advert")
@RestController
//@CrossOrigin("http://localhost:4200")
public class AdvertController {

    private final CheckService checkService;
    private final CaptureService captureService;
    private final AdvertService advertService;

    @GetMapping (value = "/{advert_id}", produces = "application/json")
    public ResponseEntity<?> getAdvertInfo(
            @PathVariable(value="advert_id") Long advert_id,
            @RequestParam(value = "details", required = true) String details, Principal principal){

        Object ret = null;
        HttpStatus status = HttpStatus.OK;

        if(details.equals("cart")) {

            AdvertCartDTO found = advertService.detailAdForCart(advert_id);

            if(principal!= null && checkService.checkUserReservation(principal.getName())){
                ret = "forbidden";
            } else {
                ret = found;
            }

        } else if(details.equals("client")) {

            AdvertDetailDTO found = advertService.detailAdForClient(advert_id);
            ret = found;
        } else if(details.equals("profile-img")) {

            String profile = "";
            try {
                profile = advertService.getProfileImg(advert_id);
            } catch(Exception e) {
                profile = "ERROR, ADVERT DOSE NOT EXIST";
                status = HttpStatus.BAD_REQUEST;
            }

            ret = profile;
        } else {

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Object>(ret, status);
    }

    @PostMapping
    public String createAdvert (@RequestBody AdvertDto advertDto, Principal principal) throws IOException {
        if(!checkService.checkCreationEnable(principal.getName())){
            return "notCreation";
        }
        if(!checkService.isForbiddenUser(principal.getName())){
            advertDto.setEmail(principal.getName());
            advertService.createAdvert(advertDto);
            return "success";
        }

        return "not";
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

    @GetMapping("/home/all")
    public ResponseEntity<List<AdvertMiniDto>>getHomeAdverts() throws IOException {
        return new ResponseEntity<>(advertService.allAdvertsHome(),HttpStatus.OK);
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

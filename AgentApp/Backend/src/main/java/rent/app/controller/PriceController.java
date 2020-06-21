package rent.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.app.dto.DTOEntity;
import rent.app.dto.PriceDto;
import rent.app.service.PriceService;

import java.util.List;

@RequestMapping("/api/price")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class PriceController {

    private final PriceService priceService;

    @PostMapping
    public void createPrice(@RequestBody PriceDto priceDto){
        priceService.createPrice(priceDto);
    }

    @GetMapping("/allPrices")
    public ResponseEntity<List<DTOEntity>> getAllPrices(){
        return new ResponseEntity<>(priceService.allPrices(), HttpStatus.OK);
    }
}

package com.example.advertmanagerapp.service;

import com.example.advertmanagerapp.dto.CarDto;
import com.example.advertmanagerapp.dto.mapper.DtoEntity;
import com.example.advertmanagerapp.model.ConcreteCar;

import java.util.List;

public interface CarService {
    List<DtoEntity> getCarBrands();
    List<DtoEntity> getCarModels(Long brandId);
    List<DtoEntity> getFuelTypes();
    List<DtoEntity> getCarClasses();
    List<DtoEntity> getTransmissions();
    ConcreteCar createCar(CarDto carDto,String ownerEmail);

    //Prilagoditi za usere
    List<DtoEntity> getAllCars(String ownerEmail);
    List<CarDto> getAllConcreteCar();
}

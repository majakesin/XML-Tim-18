package com.example.advertmanagerapp.service.impl;

import com.example.advertmanagerapp.dto.*;
import com.example.advertmanagerapp.dto.mapper.DtoEntity;
import com.example.advertmanagerapp.dto.mapper.DtoUtils;
import com.example.advertmanagerapp.model.*;
import com.example.advertmanagerapp.repository.*;
import com.example.advertmanagerapp.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CarServiceImplement implements CarService {

    private final CarBrandRepository carBrandRepository;
    private final CarModelRepository carModelRepository;
    private final CarClassRepository carClassRepository;
    private final CarFuelTypeRepository carFuelTypeRepository;
    private final CarTransmissionTypeRepository carTransmissionTypeRepository;
    private final DtoUtils dtoUtils;
    private final ConcreteCarRepository concreteCarRepository;


    @Override
    public List<DtoEntity> getCarBrands() {
        return carBrandRepository.findAll().stream().map(b -> dtoUtils.convertToDto(b,new CarBrandDto())).collect(Collectors.toList());

    }

    @Override
    public List<DtoEntity> getCarModels(Long brandId) {
        CarBrand carBrand=carBrandRepository.findById(brandId).get();
        return carBrand.getCarModelSet().stream().map(cm->dtoUtils.convertToDto(cm,new CarModelDto())).collect(Collectors.toList());
    }

    @Override
    public List<DtoEntity> getFuelTypes() {
        return carFuelTypeRepository.findAll().stream().map(fuel->dtoUtils.convertToDto(fuel,new CarFuelTypeDto())).collect(Collectors.toList());
    }

    @Override
    public List<DtoEntity> getCarClasses() {
        return carClassRepository.findAll().stream().map(carClass -> dtoUtils.convertToDto(carClass,new CarClassDto())).collect(Collectors.toList());
    }

    @Override
    public List<DtoEntity> getTransmissions() {
        return carTransmissionTypeRepository.findAll().stream().map(t->dtoUtils.convertToDto(t,new CarTransmissionTypeDto())).collect(Collectors.toList());
    }

    @Override
    public void createCar(CarDto carDto) {
//        CarBrand carBrand=carBrandRepository.findById(carDto.getCarBrand()).get();
        CarFuelType carFuelType=carFuelTypeRepository.findById(carDto.getCarFuelTypeId()).get();
        CarModel carModel=carModelRepository.findById(carDto.getCarModelId()).get();
        CarTransmissionType carTransmissionType=carTransmissionTypeRepository.findById(carDto.getCarTransmissionTypeId()).get();
        CarClass carClass=carClassRepository.findById(carDto.getCarClassId()).get();
        ConcreteCar car= (ConcreteCar) dtoUtils.convertToEntity(new ConcreteCar(),carDto);
//        car.setCarBrand(carBrand);
        car.setCarClass(carClass);
        car.setCarFuelType(carFuelType);
        car.setCarModel(carModel);
        car.setCarTransmissionType(carTransmissionType);
        concreteCarRepository.save(car);
    }

    @Override
    public List<DtoEntity> getAllCars() {

        List<DtoEntity> cars=concreteCarRepository.findAll().stream().map(c->dtoUtils.convertToDto(c,new CarDto())).collect(Collectors.toList());

        System.out.print(cars.toString());

        return null;
    }
}

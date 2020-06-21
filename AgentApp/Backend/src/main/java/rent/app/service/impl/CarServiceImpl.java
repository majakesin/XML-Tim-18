package rent.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rent.app.dto.*;
import rent.app.model.*;
import rent.app.repository.*;
import rent.app.service.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarModelRepository carModelRepository;
    private final CarBrandRepository carBrandRepository;
    private final CarTransmissionRepository carTransmissionRepository;
    private final CarClassRepository carClassRepository;
    private final CarFuelRepository carFuelRepository;
    private final CarRepository carRepository;
    private final DtoUtils dtoUtils;

    @Override
    public List<DTOEntity> getCarBrands() {
        return carBrandRepository.findAll().stream().map(b->dtoUtils.convertToDto(b,new CarBrandDto())).collect(Collectors.toList());
    }

    @Override
    public List<DTOEntity> getCarModels() {
        return carModelRepository.findAll().stream().map(m->dtoUtils.convertToDto(m,new CarModelDto())).collect(Collectors.toList());
    }

    @Override
    public List<DTOEntity> getFuelTypes() {
        return carFuelRepository.findAll().stream().map(f->dtoUtils.convertToDto(f,new CarFuelTypeDto())).collect(Collectors.toList());
    }

    @Override
    public List<DTOEntity> getCarClasses() {
        return carClassRepository.findAll().stream().map(c->dtoUtils.convertToDto(c,new CarClassDto())).collect(Collectors.toList());
    }

    @Override
    public List<DTOEntity> getTransmissions() {
        return carTransmissionRepository.findAll().stream().map(t->dtoUtils.convertToDto(t,new CarTransmissionTypeDto())).collect(Collectors.toList());
    }

    @Override
    public void createCar(CarDto carDto) {
        CarBrand carBrand=carBrandRepository.findById(carDto.getCarBrand()).get();
        CarFuelType carFuelType=carFuelRepository.findById(carDto.getCarFuelType()).get();
        CarModel carModel=carModelRepository.findById(carDto.getCarModel()).get();
        CarTransmissionType carTransmissionType=carTransmissionRepository.findById(carDto.getCarTransmissionType()).get();
        CarClass carClass=carClassRepository.findById(carDto.getCarClass()).get();

        Car car= (Car) dtoUtils.convertToEntity(new Car(),carDto);
        car.setCarBrand(carBrand);
        car.setCarClass(carClass);
        car.setCarFuelType(carFuelType);
        car.setCarModel(carModel);
        car.setCarTransmissionType(carTransmissionType);
        carRepository.save(car);
    }

    @Override
    public List<CarDtoMini> allCars() {
        List<CarDtoMini> cars=new ArrayList<>();
        List<Car>carList=carRepository.findAll();
        for(Car c: carList){
            CarDtoMini carDtoMini=new CarDtoMini();
            carDtoMini.setId(c.getId());
            carDtoMini.setCarName(c.getCarModel().getModelName()+":"+c.getCarBrand().getName());
            cars.add(carDtoMini);
        }
        return cars;
    }
}

package com.example.advertmanagerapp.mq.service;

import com.example.advertmanagerapp.model.*;
import com.example.advertmanagerapp.mq.dto.*;
import com.example.advertmanagerapp.repository.PictureRepository;
import com.example.advertmanagerapp.service.PictureService;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SoapProducer {

	@Autowired
	private Gson gson;
	@Autowired
	private AmqpTemplate amqpTemplate;
	@Autowired
	private PictureService pictureSerivce;
	@Autowired
	private PictureRepository pictureRepository;

	@Value("${rabbitmq.exchange.soap.ad.soap}")
	private String exchange;

	@Value("${rabbitmq.routingkey.soap.ad.soap}")
	private String routingKey;

	public void produceAdvert(Advert advert){
		Picture p = pictureRepository.findByPath(advert.getProfilePicture());
		Set<Long> gallery = new HashSet<>();
		advert.getPictureSet().forEach(pic -> {
			gallery.add(pic.getId());
		});
		Set<Long> captures = new HashSet<>();
		advert.getCaptures().forEach(cap -> {
			captures.add(cap.getId());
		});

		String msg = "Advert-";
		AdvertMQ mq = new AdvertMQ();
		mq.setId(advert.getId());
		mq.setCarId(advert.getConcreteCar().getId());
		mq.setProfileImgId(p.getId());
		mq.setGalleryImgIds(gallery);
		mq.setStart(advert.getStartOfAdvert());
		mq.setEnd(advert.getEndOfAdvert());
		mq.setDescription(advert.getDescription());
		mq.setPriceId(advert.getPrice().getId());
		mq.setCaptureIds(captures);
		mq.setDeleted(!advert.isActive());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void produceCapture(Capture capture){
		String msg = "Capture-";
		CaptureMQ mq = new CaptureMQ();
		mq.setId(capture.getId());
		mq.setEnd(capture.getLeaveDate());
		mq.setStart(capture.getTakeDate());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void produceCarBrand(CarBrand carBrand){
		String msg = "CarBrand-";
		CarBrandMQ mq = new CarBrandMQ();
		mq.setDeleted(carBrand.isRemoved());
		mq.setName(carBrand.getName());
		mq.setId(carBrand.getId());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void produceCarClass(CarClass carClass){
		String msg = "CarClass-";
		CarClassMQ mq = new CarClassMQ();
		mq.setDeleted(carClass.isRemoved());
		mq.setName(carClass.getClassName());
		mq.setId(carClass.getId());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void produceCarFuelType(CarFuelType carFuelType){
		String msg = "CarFuelType-";
		CarFuelTypeMQ mq = new CarFuelTypeMQ();
		mq.setDeleted(carFuelType.isRemoved());
		mq.setName(carFuelType.getFuelType());
		mq.setId(carFuelType.getId());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void produceCarModel(CarModel carModel){
		String msg = "CarModel-";
		CarModelMQ mq = new CarModelMQ();
		mq.setDeleted(carModel.isRemoved());
		mq.setName(carModel.getModelName());
		mq.setId(carModel.getId());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void produceCarTransmission(CarTransmissionType carTransmissionType){
		String msg = "CarTransmission-";
		CarTransmissionMQ mq = new CarTransmissionMQ();
		mq.setDeleted(carTransmissionType.isRemoved());
		mq.setName(carTransmissionType.getTransmissionType());
		mq.setId(carTransmissionType.getId());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void producePicture(Picture picture){
		String base64;
		try {
			base64= pictureSerivce.getPicture(picture);
		} catch (Exception e) {
			base64 = "";
		}

		String msg = "Picture-";
		PictureMQ mq = new PictureMQ();
		mq.setId(picture.getId());
		mq.setName(picture.getPath());
		mq.setBase64(base64);
		mq.setDeleted(picture.isDeleted());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void producePrice(Price price){
		String msg = "Price-";
		PriceMQ mq = new PriceMQ();
		mq.setId(price.getId());
		mq.setName(price.getName());
		mq.setPricePerDay(price.getPricePerDay());
		mq.setInsurancePrice(price.getInsurancePrice());
		mq.setDistanceOverflowPrice(price.getDistanceOverflowPrice());
		mq.setDiscount(price.getDiscount());
		mq.setDeleted(price.isRemoved());
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}

	public void produceCar(ConcreteCar car){
		String msg = "Car-";
		CarMQ mq = new CarMQ();
		mq.setId(car.getId());
		mq.setCarBrandId(car.getCarBrand().getId());
		mq.setCarModelId(car.getCarModel().getId());
		mq.setCarClassId(car.getCarClass().getId());
		mq.setCarFuelTypeId(car.getCarFuelType().getId());
		mq.setCarTransmissionId(car.getCarTransmissionType().getId());
//		mq.setTraveledDistance(car.getD);
		mq.setTraveledDistanceConstraint((long) car.getTravelDistanceConstraint());
//		mq.setInsurance(car.get);
		mq.setDeleted(false);
		msg += gson.toJson(mq);
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("MQ>>Send to soap: " + msg);
	}
}

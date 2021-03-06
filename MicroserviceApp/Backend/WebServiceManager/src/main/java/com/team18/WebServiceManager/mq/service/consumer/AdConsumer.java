package com.team18.WebServiceManager.mq.service.consumer;

import com.google.gson.Gson;
import com.team18.WebServiceManager.model.*;
import com.team18.WebServiceManager.mq.dto.*;
import com.team18.WebServiceManager.repository.*;
import com.team18.WebServiceManager.ws.client.*;
import com.team18.WebServiceManager.ws.endpoint.dto.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AdConsumer {

	@Autowired
	private Gson gson;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private CarBrandRepository carBrandRepository;
	@Autowired
	private CarModelRepository carModelRepository;
	@Autowired
	private CarClassRepository carClassRepository;
	@Autowired
	private CarFuelTypeRepository carFuelTypeRepository;
	@Autowired
	private CarTransmissionRepository carTransmissionRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private PriceRepository priceRepository;
	@Autowired
	private PictureRepository pictureRepository;
	@Autowired
	private CaptureRepository captureRepository;
	@Autowired
	private AdvertRepository advertRepository;
	@Autowired
	private DriveReportRepository driveReportRepository;
	//BEANOVI ZA SLANJE SOAP-A
	@Autowired
	private CarBrandWSClient carBrandWSClient;
	@Autowired
	private AdvertWSClient advertWSClient;
	@Autowired
	private CaptureWSClient captureWSClient;
	@Autowired
	private CarClassWSClient carClassWSClient;
	@Autowired
	private CarFuelTypeWSClient carFuelTypeWSClient;
	@Autowired
	private CarModelWSClient carModelWSClient;
	@Autowired
	private CarTransmissionWSClient carTransmissionWSClient;
	@Autowired
	private CarWSClient carWSClient;
	@Autowired
	private PictureWSClient pictureWSClient;
	@Autowired
	private PriceWSClient priceWSClient;

	@RabbitListener(queues="${rabbitmq.queue.soap.ad.soap}") //primi poruku sa mq-a(preko njega idu svi oni entiteti)
	public void createAgent(String msg) { //mq je samo string, prvi deo je naziv entiteta koji se salje, onda '-', pa u json formatu spakovan taj entitet

		System.out.println(">MQ RECEIVED A NEW "+ msg);

		String[] message = msg.split("-");

		try {
			if (message[0].equals("CarBrand")) {
				CarBrandMQ mq = gson.fromJson(message[1], CarBrandMQ.class); //konvert u mq model

				List<Agent> agents = agentRepository.findAll(); //iz te liste uzimamo url-ove, i svakom agentu saljemo soap

				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					CarBrandRequest request = new CarBrandRequest();
					CarBrand b = carBrandRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId()); //metodica iz repo.koja pomocu kljuceva(ili jednog ili drugog(2 smera)) vraca CarBrand
					//ako smo uspeli da nadjemo po id od MS,to znaci da je on vec bio poslat(ili ga je MS poslao ili ga je agent kreirao), znaci da vec postoji na agentu, tad se vrsi update
					//u suprotnom se vrsi kreiranje i saljemo 0 ne null
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setName(mq.getName());
					request.setToken(ag.getAgentEmail());
					request.setDeleted(mq.isDeleted()); //sve dovde ide konverzija(mapiranje) iz mq tipa u request(taj request se salje preko soap-a)

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					carBrandWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = carBrandWSClient.handleCarBrand(request); //request iz mq namapiran gore
					System.out.println("------>"+ request.toString());
					if (b == null) {
						//TREBA DA SE KREIRA
						b = new CarBrand();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						carBrandRepository.save(b);
					}
				});

			} else if (message[0].equals("CarModel")) {
				CarModelMQ mq = gson.fromJson(message[1], CarModelMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					CarModelRequest request = new CarModelRequest();
					CarModel b = carModelRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setName(mq.getName());
					request.setToken(ag.getAgentEmail());
					request.setDeleted(mq.isDeleted());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					carModelWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = carModelWSClient.handleCarModel(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new CarModel();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						carModelRepository.save(b);
					}
				});

			} else if (message[0].equals("CarClass")) {
				CarClassMQ mq = gson.fromJson(message[1], CarClassMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					CarClassRequest request = new CarClassRequest();
					CarClass b = carClassRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setName(mq.getName());
					request.setToken(ag.getAgentEmail());
					request.setDeleted(mq.isDeleted());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					carClassWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = carClassWSClient.handleCarClass(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new CarClass();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						carClassRepository.save(b);
					}
				});

			} else if (message[0].equals("CarFuelType")) {
				CarFuelTypeMQ mq = gson.fromJson(message[1], CarFuelTypeMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					CarFuelTypeRequest request = new CarFuelTypeRequest();
					CarFuelType b = carFuelTypeRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setName(mq.getName());
					request.setToken(ag.getAgentEmail());
					request.setDeleted(mq.isDeleted());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					carFuelTypeWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = carFuelTypeWSClient.handleCarFuelType(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new CarFuelType();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						carFuelTypeRepository.save(b);
					}
				});

			} else if (message[0].equals("CarTransmission")) {
				CarTransmissionMQ mq = gson.fromJson(message[1], CarTransmissionMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					CarTransmissionRequest request = new CarTransmissionRequest();
					CarTransmission b = carTransmissionRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setName(mq.getName());
					request.setToken(ag.getAgentEmail());
					request.setDeleted(mq.isDeleted());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					carTransmissionWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = carTransmissionWSClient.handleCarTransmission(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new CarTransmission();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						carTransmissionRepository.save(b);
					}
				});

			} else if (message[0].equals("Car")) {
				CarMQ mq = gson.fromJson(message[1], CarMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					CarRequest request = new CarRequest();
					Car b = carRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setToken(ag.getAgentEmail());
					CarBrand cb = carBrandRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getCarBrandId());
					request.setCarBrandId(cb != null ? cb.getKeyAG() : 0);
					CarModel cm = carModelRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getCarModelId());
					request.setCarModelId(cm != null ? cm.getKeyAG() : 0);
					CarClass cc = carClassRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getCarClassId());
					request.setCarClassId(cc != null ? cc.getKeyAG() : 0);
					CarFuelType cft = carFuelTypeRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getCarFuelTypeId());
					request.setCarFuelTypeId(cft != null ? cft.getKeyAG() : 0);
					CarTransmission cf = carTransmissionRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getCarTransmissionId());
					request.setCarTransmissionId(cf != null ? cf.getKeyAG() : 0);
					request.setTraveledDistance(mq.getTraveledDistance());
					request.setTraveledDistanceConstraint(mq.getTraveledDistanceConstraint());
					request.setInsurance(mq.isInsurance());
					request.setChildrenSitNumber(mq.getChildrenSitNumber());
					request.setDeleted(mq.isDeleted());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					carWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = carWSClient.handleCar(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new Car();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						carRepository.save(b);
					}
				});

			} else if (message[0].equals("Price")) {
				PriceMQ mq = gson.fromJson(message[1], PriceMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					PriceRequest request = new PriceRequest();
					Price b = priceRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setName(mq.getName());
					request.setToken(ag.getAgentEmail());
					request.setPricePerDay(mq.getPricePerDay());
					request.setInsurancePrice(mq.getInsurancePrice());
					request.setDistanceOverflowPrice(mq.getDistanceOverflowPrice());
					request.setDiscount(mq.getDiscount());
					request.setDeleted(mq.isDeleted());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					priceWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = priceWSClient.handlePrice(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new Price();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						priceRepository.save(b);
					}
				});

			} else if (message[0].equals("Picture")) {
				PictureMQ mq = gson.fromJson(message[1], PictureMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					PictureRequest request = new PictureRequest();
					Picture b = pictureRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setName(mq.getName());
					request.setBase64(mq.getBase64());
					request.setToken(ag.getAgentEmail());
					request.setDeleted(mq.isDeleted());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					pictureWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = pictureWSClient.handlePicture(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new Picture();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						pictureRepository.save(b);
					}
				});

			} else if (message[0].equals("Capture")) {
				CaptureMQ mq = gson.fromJson(message[1], CaptureMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					CaptureRequest request = new CaptureRequest();
					Capture b = captureRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setEnd(mq.getEnd());
					request.setStart(mq.getStart());
					request.setToken(ag.getAgentEmail());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					captureWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = captureWSClient.handleCapture(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new Capture();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						captureRepository.save(b);
					}
				});

			} else if (message[0].equals("Advert")) {
				AdvertMQ mq = gson.fromJson(message[1], AdvertMQ.class);

				List<Agent> agents = agentRepository.findAll();
				agents.forEach(ag -> {
					//SVAKOM AGENTU POSLATI SOAP
					AdvertRequest request = new AdvertRequest();
					Advert b = advertRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
					request.setId(b != null ? b.getKeyAG() : 0);
					request.setToken(ag.getAgentEmail());
					Car c = carRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getCarId());
					request.setCarId(c != null ? c.getKeyAG() : 0);
					Picture p = pictureRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getProfileImgId());
					request.setProfileImgId(p != null ? p.getKeyAG() : 0);
					mq.getGalleryImgIds().forEach(img -> {
						Picture g = pictureRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), img);
						request.getGalleryImgIds().add(g.getKeyAG());
					});
					request.setEnd(mq.getEnd());
					request.setStart(mq.getStart());
					request.setDescription(mq.getDescription());
					Price price = priceRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getPriceId());
					request.setPriceId(price != null ? price.getKeyAG() : 0);
					mq.getCaptureIds().forEach(capture -> {
						Capture cap = captureRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), capture);
						request.getCaptureIds().add(cap.getKeyAG());
					});
					request.setDeleted(mq.isDeleted());

					//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
					advertWSClient.setDefaultUri(ag.getAgentUrl());
					SoapResponse response = advertWSClient.handleAdvert(request); //request iz mq namapiran gore

					if (b == null) {
						//TREBA DA SE KREIRA
						b = new Advert();
						b.setAgent(ag);
						b.setKeyMS(mq.getId());
						b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId

						advertRepository.save(b);
					}
				});
			}
//		else if(message[0].equals("DriveReport")) {
//			DriveReportMQ mq = gson.fromJson(message[1], DriveReportMQ.class);
//
//			List<Agent> agents = agentRepository.findAll();
//			agents.forEach(ag -> {
//				//SVAKOM AGENTU POSLATI SOAP
//				DriveReportRequest request = new DriveReportRequest();
//				DriveReport b = driveReportRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getId());
//				request.setId(b!=null?b.getKeyAG():0);
//				request.setToken(ag.getAgentEmail());
//				Car c = carRepository.findByAgentAgentIdAndKeyMS(ag.getAgentId(), mq.getCarId());
//				request.setCarId(c!=null?c.getKeyAG():0);
//				request.setDateOfReport(mq.getDateOfReport());
//				request.setDescription(mq.getDescription());
//				request.setTraveledDistance(mq.getTraveledDistance());
//
//				//POZOVE SE ODGOVARAJUCI KLIENT DA SE POSALJE SOAP
//				DriveRe
//				if(b==null) {
//					//TREBA DA SE KREIRA
//					b = new DriveReport();
//					b.setAgent(ag);
//					b.setKeyMS(mq.getId());
//					b.setKeyAG(response.getAgId());//dobijemo u SOAP RESPONSU, agId
//
//					driveReportRepository.save(b);
//				}
//			});
//		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

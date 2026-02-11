package com.dtt.simulations.service.Impl;


import com.dtt.simulations.dto.CarRentalDto;
import com.dtt.simulations.dto.NotificationContextDTO;
import com.dtt.simulations.dto.NotificationDTO;
import com.dtt.simulations.dto.NotificationDataDTO;
import com.dtt.simulations.enums.ServiceNames;
import com.dtt.simulations.model.CarRental;
import com.dtt.simulations.model.Subscriber;

import com.dtt.simulations.model.SubscriberFCMToken;
import com.dtt.simulations.model.SubscriberStatus;
import com.dtt.simulations.repo.*;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.CarRentalIface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service

public class CarRentalImpl implements CarRentalIface {

    final static String CLASS = "CarRentalImpl";
    Logger logger = LoggerFactory.getLogger(CarRentalImpl.class);

    @Autowired
    CarRentalRepo carRentalRepo;

    @Autowired
    LogModelServiceImpl logModelService;

    @Autowired
    SubscriberCertificatesRepoIface subscriberCertificatesRepoIface;

    @Value("${notify.url}")
    public String notifyUrl;

    @Autowired
    SubscriberFcmTokenRepoIface subscriberFcmTokenRepoIface;

    @Autowired
    SubscriberRepoIface subscriberRepoIface;

    @Autowired
    SubscriberStatusRepoIface subscriberStatusRepoIface;

    @Override
    public ApiResponse saveCarRentalData(String carRentalJson) {
        try{



            Date startTime= AppUtil.getCurrentDate();



            if(carRentalJson.equals("null") || carRentalJson.isEmpty()){
                logger.info(CLASS + " saceCarRentalData() Impl >> false >> Car Rental Details cannot be null");

                return AppUtil.createApiResponse(false, "Car Rental Details  cannot be null", null);
            }



            CarRental carRental = new CarRental();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(carRentalJson);



            String name =  jsonNode.path("fullName").asText(null);
            String passportNo =  jsonNode.path("idDocNumber").asText(null);
            String drivingNo =  jsonNode.path("drivingLicenseNumber").asText(null);

            String internationPermit =  jsonNode.path("internationalPermit").asText(null);
            String country =  jsonNode.path("nationality").asText(null);
            String pickup =  jsonNode.path("pick_up_date").asText(null);
            String rentaldays =  jsonNode.path("rental_days").asText(null);
            String pidDocument =  jsonNode.path("pidDocument").asText(null);
            String mdlDocument =  jsonNode.path("mdlDocument").asText(null);
            String photo =  jsonNode.path("photo").asText(null);






            if(rentaldays.equals("null") || rentaldays.isEmpty() || pickup.equals("null")||pickup.isEmpty()){
                logger.info(CLASS + " saceCarRentalData() Impl >> false >> Car Rental Details cannot be null");

                return AppUtil.createApiResponse(false, "Pick Up date or No of days cannot be null", null);
            }

            if(!internationPermit.equals("Issued")){
                return AppUtil.createApiResponse(false,"International driving permit is not issued",null);

            }//            String rentalDocument = jsonNode.get("rental_document").asText();


            carRental.setApplicantName(name);
            carRental.setPassportNumber(passportNo);

            carRental.setDrivingLicenseDocument(mdlDocument);
            carRental.setDrivingLicenseNumber(drivingNo);
            carRental.setPidDocumnet(pidDocument);

            carRental.setCountry(country);
            carRental.setPickUpDate(pickup);
            carRental.setStatus("APPROVED");
            carRental.setCreatedOn(startTime);
            carRental.setUpdatedOn(startTime);
            carRental.setPhoto(photo);


            carRental.setNoOfDays(rentaldays);
            String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int MAX_DIGITS = 5;
            Random random = new Random();
            char letter = LETTERS.charAt(random.nextInt(LETTERS.length()));
            int number = random.nextInt((int) Math.pow(10, MAX_DIGITS));

            carRental.setCarNumber(String.format("%c%05d", letter, number));
//            carRental.setRentalAgreementFile(rentalDocument);
            carRental.setInternationalPermit(internationPermit);
            carRental.setJsonData(carRentalJson);

            carRentalRepo.save(carRental);

            NotificationDataDTO dataDTO = new NotificationDataDTO();
            NotificationContextDTO contextDTO = new NotificationContextDTO();
            NotificationDTO notificationBody=new NotificationDTO();
            Subscriber subscriber = new Subscriber();


            subscriber=subscriberRepoIface.findbyDocumentNumber(passportNo);

            SubscriberStatus subscriberStatus = subscriberStatusRepoIface.findBysubscriberUid(subscriber.getSubscriberUid());


//            int countOfCert = subscriberCertificatesRepoIface.getCountOfCert(subscriber.getSubscriberUid());
//            if(countOfCert == 0){
//
//                return AppUtil.createApiResponse(false,"Digital Id is not active",null);
//            }

            SubscriberFCMToken subscriberFcmToken=subscriberFcmTokenRepoIface.findBysuid(subscriber.getSubscriberUid());
            notificationBody.setTo(subscriberFcmToken.getFcmToken());
            notificationBody.setPriority("high");
            dataDTO.setTitle("Hi " + subscriber.getFullName());
            Map<String, String> ImmigrationNotification = new HashMap<>();
            ImmigrationNotification.put("Car Rented", "success");
            dataDTO.setBody("Car rented successfully");
            contextDTO.setPREF_IMMIGRATION_AUTHORITY(ImmigrationNotification);
            dataDTO.setNotificationContext(contextDTO);
            notificationBody.setData(dataDTO);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(notificationBody, headers);
            try {
                RestTemplate restTemplate=new RestTemplate();

                ResponseEntity<Object> res = restTemplate.exchange(notifyUrl, HttpMethod.POST, requestEntity,
                        Object.class);
                if (res.getStatusCodeValue() == 200) {
                    System.out.println("Notification sent");
                } else {
                    System.out.println("Notification failed");
                }
            }catch (Exception e){
                return AppUtil.createApiResponse(false,"Something went wrong",null);
            }

            Date start= AppUtil.getCurrentDate();

            Date end=AppUtil.getCurrentDate();
            logModelService.setLogModelDTO(true,subscriber.getSubscriberUid(),null, ServiceNames.OTHER.toString(),AppUtil.getUUId(),"Car rented successfully",start,end,"false");






            return  AppUtil.createApiResponse(true,"Car Rented Successfully",null);

        }catch (Exception e){
            e.printStackTrace();
            return  AppUtil.createApiResponse(false,"Something Went Wrong",null);
        }
    }
    @Override
    public ApiResponse getAllCarRentalData() {
        try{

            List<CarRental> carRentalList = carRentalRepo.getCarRentalDetails();
            List<CarRentalDto> responseForms = new ArrayList<>();
            if (carRentalList != null && !carRentalList.isEmpty()) {

                for (CarRental i : carRentalList) {

                    CarRentalDto carRentalDto = new CarRentalDto();
                    carRentalDto.setId(i.getId());
                    carRentalDto.setApplicantName(i.getApplicantName());
                    carRentalDto.setPassportNumber(i.getPassportNumber());
                    carRentalDto.setDrivingLicenseNumber(i.getDrivingLicenseNumber());
                    carRentalDto.setInternationalPermit(i.getInternationalPermit());
                    carRentalDto.setCreatedOn(i.getCreatedOn());
                    carRentalDto.setUpdatedOn(i.getUpdatedOn());
                    carRentalDto.setStatus(i.getStatus());
                    carRentalDto.setCountry(i.getCountry());
                    carRentalDto.setCarNumber(i.getCarNumber());
                    carRentalDto.setNoOfDays(i.getNoOfDays());
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    // Parse the input date string to LocalDateTime
                    LocalDateTime dateTime = LocalDateTime.parse(i.getPickUpDate().trim(), inputFormatter);

                    carRentalDto.setPickupDate(dateTime.format(outputFormatter));





                    responseForms.add(carRentalDto);

                }




                return AppUtil.createApiResponse(true, "All Data Fetched", responseForms);
            }
            return AppUtil.createApiResponse(false, "No data found", carRentalList);

        }catch (Exception e){
            e.printStackTrace();
            return  AppUtil.createApiResponse(false,"Something Went Wrong",null);
        }
    }

    @Override
    public ApiResponse getCarRentalDataById(int id) {
        try{
            if(id==0){
                return AppUtil.createApiResponse(false,"Id cannot be null",null);
            }

            CarRental carRental = carRentalRepo.getCarRentalDetaildById(id);


            if(carRental==null){
                return AppUtil.createApiResponse(false,"Data related to id cannot be found",null);
            }

            return AppUtil.createApiResponse(true,"Data Fetched Successfully",carRental);

        }catch (Exception e){
            e.printStackTrace();
            return  AppUtil.createApiResponse(false,"Something Went Wrong",null);
        }
    }
}

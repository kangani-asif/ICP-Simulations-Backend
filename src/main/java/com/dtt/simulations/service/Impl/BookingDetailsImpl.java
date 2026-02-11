package com.dtt.simulations.service.Impl;


import com.dtt.simulations.dto.BookingDetailsDto;
import com.dtt.simulations.dto.NotificationContextDTO;
import com.dtt.simulations.dto.NotificationDTO;
import com.dtt.simulations.dto.NotificationDataDTO;
import com.dtt.simulations.enums.ServiceNames;
import com.dtt.simulations.model.HotelSimulator;
import com.dtt.simulations.model.Subscriber;
import com.dtt.simulations.model.SubscriberFCMToken;
import com.dtt.simulations.model.SubscriberStatus;
import com.dtt.simulations.repo.*;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.BookingDetailsIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service

public class BookingDetailsImpl implements BookingDetailsIface {

    @Autowired
    HotelSimulatorRepo hotelSimulatorRepo;

    @Autowired
    SubscriberFcmTokenRepoIface subscriberFcmTokenRepoIface;

    @Autowired
    SubscriberRepoIface subscriberRepoIface;

    @Autowired
    SubscriberStatusRepoIface subscriberStatusRepoIface;

    RestTemplate restTemplate = new RestTemplate();


    @Value("${notify.url}")
    public String notifyUrl;

    @Autowired
    LogModelServiceImpl logModelService;

    @Autowired
    SubscriberCertificatesRepoIface  subscriberCertificatesRepoIface;


    @Override
    public ApiResponse saveBookingDetails(BookingDetailsDto bookingDetailsDto) {

        try {

            Date start= AppUtil.getCurrentDate();
            HotelSimulator hotelSimulator = new HotelSimulator();

            if (bookingDetailsDto == null ) {
                return AppUtil.createApiResponse(false, "DTO cannot be null", null);

            } else if(bookingDetailsDto.getIdDocNumber()==null || bookingDetailsDto.getIdDocNumber().isEmpty()){
                return AppUtil.createApiResponse(false, "Id Document Number cannot be null", null);
            }
            else if(bookingDetailsDto.getDocumentType()==null || bookingDetailsDto.getDocumentType().isEmpty()){
                return AppUtil.createApiResponse(false, "Document Type cannot be null", null);
            }
            else {
                hotelSimulator.setName(bookingDetailsDto.getFullName());
                hotelSimulator.setGender(bookingDetailsDto.getGender());
                hotelSimulator.setPhoto(bookingDetailsDto.getPhoto());
                hotelSimulator.setDateOfBirth(bookingDetailsDto.getDateOfBirth());
                hotelSimulator.setDocumentNumber(bookingDetailsDto.getIdDocNumber());
                Random random = new Random();
                int randomNumber = random.nextInt(1000) + 1;
                hotelSimulator.setRoomAllocated(String.valueOf(randomNumber));
                hotelSimulator.setCreationDate(AppUtil.getDate());
                hotelSimulatorRepo.save(hotelSimulator);

                NotificationDataDTO dataDTO = new NotificationDataDTO();
                NotificationContextDTO contextDTO = new NotificationContextDTO();
                NotificationDTO notificationBody=new NotificationDTO();
                Subscriber subscriber = new Subscriber();
               if ("passport".equals(bookingDetailsDto.getDocumentType()))
               {
                   subscriber=subscriberRepoIface.findByPassportNumber(bookingDetailsDto.getIdDocNumber());
               }
               else if ("emirates_id".equals(bookingDetailsDto.getDocumentType())) {
                   subscriber=subscriberRepoIface.findByNationalIdNumber(bookingDetailsDto.getIdDocNumber());

               }else if("suid".equals(bookingDetailsDto.getDocumentType())){
                   subscriber=subscriberRepoIface.findBySubscriberUid(bookingDetailsDto.getIdDocNumber());
               }

                SubscriberStatus subscriberStatus = subscriberStatusRepoIface.findBysubscriberUid(subscriber.getSubscriberUid());



                SubscriberFCMToken subscriberFcmToken=subscriberFcmTokenRepoIface.findBysuid(subscriber.getSubscriberUid());
                notificationBody.setTo(subscriberFcmToken.getFcmToken());
                notificationBody.setPriority("high");
                dataDTO.setTitle("Hi " + subscriber.getFullName());
                Map<String, String> ImmigrationNotification = new HashMap<>();
                ImmigrationNotification.put("Hotel", "Booked");
                dataDTO.setBody("Hotel check-in completed");
                contextDTO.setPREF_IMMIGRATION_AUTHORITY(ImmigrationNotification);
                dataDTO.setNotificationContext(contextDTO);
                notificationBody.setData(dataDTO);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> requestEntity = new HttpEntity<>(notificationBody, headers);
                try {
                    RestTemplate restTemplate=new RestTemplate();

                    ResponseEntity<String> res = restTemplate.exchange(notifyUrl, HttpMethod.POST, requestEntity,
                            String.class);
                    if (res.getStatusCode().is2xxSuccessful()) {
                        System.out.println("Notification sent: " + res.getBody());
                    } else {
                        System.out.println("Notification failed: " + res.getStatusCode());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return AppUtil.createApiResponse(false,"Something went wrong",null);
                }

                Date end=AppUtil.getCurrentDate();
                logModelService.setLogModelDTO(true,subscriber.getSubscriberUid(),null, ServiceNames.OTHER.toString(),AppUtil.getUUId(),"Hotel check-in completed",start,end,"false");
                return AppUtil.createApiResponse(true,  "Room booked successfully", String.valueOf(randomNumber));
            }
        }catch (Exception e){
            e.printStackTrace();
            return AppUtil.createApiResponse(false, "Something went wrong", null);
        }
    }


    @Override
    public ApiResponse getAllBookingDetails() {
        try{

            return AppUtil.createApiResponse(true,"All Records Fetched Successfully",hotelSimulatorRepo.allBookings());

        }catch(Exception e){
            
            return AppUtil.createApiResponse(false,"something went wrong",null);

        }
    }
}

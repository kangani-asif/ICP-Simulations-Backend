package com.dtt.simulations.service.Impl;


import com.dtt.simulations.dto.NotificationContextDTO;
import com.dtt.simulations.dto.NotificationDTO;
import com.dtt.simulations.dto.NotificationDataDTO;
import com.dtt.simulations.dto.SimIssuanceDto;
import com.dtt.simulations.enums.ServiceNames;
import com.dtt.simulations.model.Subscriber;
import com.dtt.simulations.model.SubscriberFCMToken;
import com.dtt.simulations.model.TelecomSimIssuance;
import com.dtt.simulations.repo.SubscriberFcmTokenRepoIface;
import com.dtt.simulations.repo.SubscriberRepoIface;
import com.dtt.simulations.repo.TelecomSimIssuanceRepo;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.TelecomSimIssuanceIface;
import org.hibernate.PessimisticLockException;
import org.hibernate.QueryTimeoutException;
import org.hibernate.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public class TelecomSimIssuanceImpl implements TelecomSimIssuanceIface {


    @Autowired
    TelecomSimIssuanceRepo telecomSimIssuanceRepo;

    @Autowired
    LogModelServiceImpl logModelService;


    @Value("${notification.url}")
    String sendNotificationURL;




    private static final Logger logger = LoggerFactory.getLogger(TelecomSimIssuanceImpl.class);
    private static final String CLASS = "TelecomSimIssuanceImpl";

    @Autowired
    SubscriberRepoIface subscriberRepoIface;

    @Autowired
    SubscriberFcmTokenRepoIface subscriberFcmTokenRepository;


    @Override
    public ApiResponse saveSimData(SimIssuanceDto dto) {
        try {
            logger.info(CLASS+"saveSimData reqBody :: "+dto);
            Date startTime=AppUtil.getCurrentDate();

            Subscriber subscriber = new Subscriber();

            if ("passport".equals(dto.getDocumentType()))
            {
                subscriber=subscriberRepoIface.findByPassportNumber(dto.getIdDocNumber());
            }
            else if ("emirates_id".equals(dto.getDocumentType())) {
                subscriber=subscriberRepoIface.findByNationalIdNumber(dto.getIdDocNumber());

            }else if("suid".equals(dto.getDocumentType())){
                subscriber=subscriberRepoIface.findBySubscriberUid(dto.getIdDocNumber());
            }
            if(subscriber == null ){
                logger.info(CLASS + " saveSimData() Impl >> false >> Subscriber details not found");
                return AppUtil.createApiResponse(false,"Subscriber details not found for given passport id",null);
            }

            logger.info(CLASS + " saveSimData() Impl >> false >> Inside saveSimData implementation ");

            TelecomSimIssuance telecomSimIssuance = new TelecomSimIssuance();
            telecomSimIssuance.setFullName(dto.getFullName());
            telecomSimIssuance.setIdDocNumber(dto.getIdDocNumber());
            telecomSimIssuance.setMobileNumber(dto.getMobileNumber());
            if (dto.getAdditionalData() != null) {
                telecomSimIssuance.setAdditionalData(dto.getAdditionalData().toString());
            }

            Random random = new Random();
            int simNumber =  100000 + random.nextInt(900000);
            telecomSimIssuance.setSimNumber(simNumber);
            telecomSimIssuanceRepo.save(telecomSimIssuance);


            Date EndTime=AppUtil.getCurrentDate();
            String notificationMessage = "Your SIM Activated Successfully";
            String key = "SimActivation";


//            Date EndTime=AppUtil.getCurrentDate();
            sendNotificationToActivateCertificate(subscriber);
            logModelService.setLogModelDTO(true,subscriber.getSubscriberUid(),null, ServiceNames.SIM.toString(),AppUtil.getUUId(),"Sim Activated",startTime,EndTime,"false");



            return AppUtil.createApiResponse(true,"Sim data saved sucessfully",null);


        } catch (ConstraintViolationException | DataException | LockAcquisitionException |
                 PessimisticLockException | QueryTimeoutException | SQLGrammarException |
                 GenericJDBCException | JDBCConnectionException e) {
            // All DB related exceptions
            logger.error(CLASS + " saveSimData() >> Database error: {}", e.getMessage(), e);
            return AppUtil.createApiResponse(false, "Database operation failed", null);

        } catch (Exception e) {
            // Fallback
            logger.error(CLASS + " saveSimData() >> Unexpected error: {}", e.getMessage(), e);
            return AppUtil.createApiResponse(false, "Something went wrong, please try again later", null);
        }



    }


    public void sendNotificationToActivateCertificate(Subscriber subscriber){
        try{
            System.out.println(subscriber);
            SubscriberFCMToken subscriberFCMToken = subscriberFcmTokenRepository.findBysuid(subscriber.getSubscriberUid());
            if(subscriberFCMToken == null){
                System.out.println("sendNotificationToActivateCertificate FCM token not found for given Subscriber");
            }else{
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                NotificationDTO notificationBody = new NotificationDTO();
                NotificationDataDTO dataDTO = new NotificationDataDTO();
                NotificationContextDTO contextDTO = new NotificationContextDTO();
                notificationBody.setTo(subscriberFCMToken.getFcmToken());
                notificationBody.setPriority("high");
                dataDTO.setTitle("Hi " + subscriber.getFullName());
                Map<String, String> simActivation = new HashMap<>();

                dataDTO.setBody("Your SIM Activated Successfully");


                simActivation.put("SimActivation", "Success");

                contextDTO.setpREF_IMMIGRATION_AUTHORITY(simActivation);
                dataDTO.setNotificationContext(contextDTO);
                notificationBody.setData(dataDTO);
                HttpEntity<Object> requestEntity = new HttpEntity<>(notificationBody, headers);

                ResponseEntity<String> res = restTemplate.exchange(sendNotificationURL, HttpMethod.POST, requestEntity,
                        String.class);
                if (res.getStatusCodeValue() == 200) {
                    System.out.println("Notification sent");
                } else {
                    System.out.println("Notification failed");
                }

            }


        }catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
                | PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }



}

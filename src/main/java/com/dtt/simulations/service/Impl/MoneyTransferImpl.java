package com.dtt.simulations.service.Impl;


import com.dtt.simulations.dto.NotificationContextDTO;
import com.dtt.simulations.dto.NotificationDTO;
import com.dtt.simulations.dto.NotificationDataDTO;
import com.dtt.simulations.enums.ServiceNames;
import com.dtt.simulations.model.MoneyTransfer;
import com.dtt.simulations.model.Subscriber;
import com.dtt.simulations.model.SubscriberFCMToken;
import com.dtt.simulations.repo.MoneyTransferRepo;
import com.dtt.simulations.repo.SubscriberFcmTokenRepoIface;
import com.dtt.simulations.repo.SubscriberRepoIface;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.MoneyTransferIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service

public class MoneyTransferImpl implements MoneyTransferIface {

    @Autowired
    MoneyTransferRepo moneyTransferRepo;

    @Autowired
    LogModelServiceImpl logModelService;

    @Autowired
    SubscriberRepoIface subscriberRepoIface;

    @Autowired
    SubscriberFcmTokenRepoIface subscriberFcmTokenRepoIface;

    @Value("${notify.url}")
    public String notifyUrl;

    @Override
    public ApiResponse getAllMoneyTransfer() {
        try {
            List<MoneyTransfer> moneyTransfers = moneyTransferRepo.findAll();
            List<MoneyTransfer> responseForms = new ArrayList<>();
            for (MoneyTransfer i : moneyTransfers) {
                MoneyTransfer moneyTranferDto = new MoneyTransfer();
                moneyTranferDto.setId(i.getId());
                moneyTranferDto.setPrincipalName(i.getPrincipalName());
                moneyTranferDto.setAgentName(i.getAgentName());
                moneyTranferDto.setNotaryName(i.getNotaryName());
                responseForms.add(moneyTranferDto);

            }

            return AppUtil.createApiResponse(true, "Fetched", responseForms);
        }

       catch (Exception e){
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }


    }

    @Override
    public ApiResponse getMoneyTransferById(int id) {
        try{

            MoneyTransfer moneyTransfer = moneyTransferRepo.findById(id);
            return AppUtil.createApiResponse(true, "Fetched", moneyTransfer);

        }catch (Exception e){
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }
    }

    @Override
    public ApiResponse saveMoneyTransfer(MoneyTransfer moneyTransfer) {
        try{
            if(moneyTransfer == null){
                return AppUtil.createApiResponse(false,"Dto cannot be null",null);
            }

            MoneyTransfer moneyTransfer1 = new MoneyTransfer();
            moneyTransfer1.setPrincipalName(moneyTransfer.getPrincipalName());
            moneyTransfer1.setPrincipalEmail(moneyTransfer.getPrincipalEmail());
            moneyTransfer1.setPrincipalIdDocNumber(moneyTransfer.getPrincipalIdDocNumber());
            moneyTransfer1.setAgentName(moneyTransfer.getAgentName());
            moneyTransfer1.setAgentEmail(moneyTransfer.getAgentEmail());
            moneyTransfer1.setAgentIdDocNumber(moneyTransfer.getAgentIdDocNumber());
            moneyTransfer1.setNotaryName(moneyTransfer.getNotaryName());
            moneyTransfer1.setNotaryEmail(moneyTransfer.getNotaryEmail());
            moneyTransfer1.setNotaryIdDocNumber(moneyTransfer.getNotaryIdDocNumber());
            moneyTransfer1.setPoaSignedDoc(moneyTransfer.getPoaSignedDoc());
            moneyTransfer1.setCreatedOn(AppUtil.getDate());
            moneyTransfer1.setUpdatedOn(AppUtil.getDate());
            moneyTransfer1.setValidUpto(moneyTransfer.getValidUpto());

            moneyTransferRepo.save(moneyTransfer1);

            Date start= AppUtil.getCurrentDate();

            Date end=AppUtil.getCurrentDate();

            Subscriber subscriberagent = new Subscriber();


            subscriberagent=subscriberRepoIface.findbyDocumentNumber(moneyTransfer.getAgentIdDocNumber());

            Subscriber subscriberprincipal = new Subscriber();


            subscriberprincipal=subscriberRepoIface.findbyDocumentNumber(moneyTransfer.getPrincipalIdDocNumber());


            logModelService.setLogModelDTO(true,subscriberagent.getSubscriberUid(),null, ServiceNames.OTHER.toString(),AppUtil.getUUId(),"Money transfer initiated by the agent",start,end,"false");

            NotificationDataDTO dataDTO = new NotificationDataDTO();
            NotificationContextDTO contextDTO = new NotificationContextDTO();
            NotificationDTO notificationBody=new NotificationDTO();

            SubscriberFCMToken subscriberFcmToken=subscriberFcmTokenRepoIface.findBysuid(subscriberagent.getSubscriberUid());
            notificationBody.setTo(subscriberFcmToken.getFcmToken());
            notificationBody.setPriority("high");
            dataDTO.setTitle("Hi " + subscriberagent.getFullName());
            Map<String, String> ImmigrationNotification = new HashMap<>();
            ImmigrationNotification.put("Money", "Transfer");
            dataDTO.setBody("Money transfer initiated successfully");
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
                    System.out.println("Notification sent to agent");
                } else {
                    System.out.println("Notification failed to agent");
                }
            }catch (Exception e){
                e.printStackTrace();
                return AppUtil.createApiResponse(false,"Something went wrong",null);
            }



            NotificationDataDTO dataDTO1 = new NotificationDataDTO();
            NotificationContextDTO contextDTO1= new NotificationContextDTO();
            NotificationDTO notificationBody1=new NotificationDTO();

            SubscriberFCMToken subscriberFcmToken1=subscriberFcmTokenRepoIface.findBysuid(subscriberprincipal.getSubscriberUid());
            if(subscriberFcmToken1 !=null) {
                notificationBody1.setTo(subscriberFcmToken1.getFcmToken());
                notificationBody1.setPriority("high");
                dataDTO1.setTitle("Hi " + subscriberprincipal.getFullName());
                Map<String, String> ImmigrationNotification1 = new HashMap<>();
                ImmigrationNotification1.put("Money", "Transfer");
                dataDTO1.setBody("Money transfer initiated by " + subscriberagent.getFullName());
                contextDTO1.setPREF_IMMIGRATION_AUTHORITY(ImmigrationNotification);
                dataDTO1.setNotificationContext(contextDTO1);
                notificationBody1.setData(dataDTO1);
                HttpHeaders headers1 = new HttpHeaders();
                headers1.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> requestEntity1 = new HttpEntity<>(notificationBody1, headers1);
                try {
                    RestTemplate restTemplate = new RestTemplate();

                    ResponseEntity<Object> res1 = restTemplate.exchange(notifyUrl, HttpMethod.POST, requestEntity1,
                            Object.class);
                    if (res1.getStatusCodeValue() == 200) {
                        System.out.println("Notification sent to principal");
                    } else {
                        System.out.println("Notification failed to principal");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return AppUtil.createApiResponse(false, "Something went wrong", null);
                }
            }
            return AppUtil.createApiResponse(true,"Details saved",null);


        }catch (Exception e){
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }
    }
}

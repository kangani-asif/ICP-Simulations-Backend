package com.dtt.simulations.service.Impl;


import com.dtt.simulations.dto.HospitalInsuranceDTO;
import com.dtt.simulations.dto.NotificationContextDTO;
import com.dtt.simulations.dto.NotificationDTO;
import com.dtt.simulations.dto.NotificationDataDTO;
import com.dtt.simulations.enums.ServiceNames;
import com.dtt.simulations.model.HospitalInsurance;
import com.dtt.simulations.model.Subscriber;
import com.dtt.simulations.model.SubscriberFCMToken;
import com.dtt.simulations.model.SubscriberStatus;
import com.dtt.simulations.repo.*;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.HospitalInsuranceIface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service

public class HospitalInsuranceImpl implements HospitalInsuranceIface {

    Logger logger = LoggerFactory.getLogger(HospitalInsuranceImpl.class);

    @Autowired
    HospitalInsuranceRepo hospitalInsuranceRepo;

    @Autowired
    SubscriberFcmTokenRepoIface subscriberFcmTokenRepository;


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
    public ApiResponse savedata(String JsonData) {
        try {
            if (JsonData.equals("null") || JsonData.isEmpty()) {
                logger.info("savedata() Impl >> false >>Hospital insurance details cannot be null");
                return AppUtil.createApiResponse(false, "Hospital Insurance details cannot be null", null);
            }


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(JsonData);

            HospitalInsurance hospitalInsurance = new HospitalInsurance();

            String InsuredMember = jsonNode.get("insured_member").asText();
            String phone_number = jsonNode.get("phone_number").asText();
            String gender = jsonNode.get("gender").asText();
            String photo = jsonNode.get("portrait").asText();
            String PolicyNumber = jsonNode.get("policy_number").asText();


            String PolicyName = jsonNode.get("policy_name").asText();

            String PolicyStartDate = jsonNode.get("issue_date").asText();
            String PolicyEndDate = jsonNode.get("expiry_date").asText();
            String PolicyStatus = jsonNode.get("policy_status").asText();
            String PolicyDocument = jsonNode.get("health_insurance_document").asText();

            hospitalInsurance.setName(InsuredMember);
            hospitalInsurance.setPhone_number(phone_number);
            hospitalInsurance.setGender(gender);
            hospitalInsurance.setPhoto(photo);
            hospitalInsurance.setPolicy_name(PolicyName);
            hospitalInsurance.setPolicy_number(PolicyNumber);
            hospitalInsurance.setPolicy_start_date(PolicyStartDate);
            hospitalInsurance.setPolicy_end_date(PolicyEndDate);
            hospitalInsurance.setPolicy_status(PolicyStatus);
            hospitalInsurance.setJson_data(JsonData);
            hospitalInsurance.setPolicyDocument(PolicyDocument);
            hospitalInsurance.setCreatedOn(AppUtil.getDate());
            hospitalInsurance.setUpdatedOn(AppUtil.getDate());

            String passportNumber= jsonNode.path("idDocNumber").asText(null);
            String applicantName= jsonNode.path("fullName").asText(null);

            String country = jsonNode.path("nationality").asText(null);

//            String passport_document = modelJson.get("passport document").asText();

//            String photo =  modelJson.path("photo").asText(null);
            String pid_document = jsonNode.path("pidDocument").asText(null);

            String documentType =jsonNode.path("documentType").asText(null);
            Subscriber subscriber = new Subscriber();

            if ("passport".equals(documentType))
            {
                subscriber=subscriberRepoIface.findByPassportNumber(passportNumber);
            }
            else if ("emirates_id".equals(documentType)) {
                subscriber=subscriberRepoIface.findByNationalIdNumber(passportNumber);

            }else if("suid".equals(documentType)){
                subscriber=subscriberRepoIface.findBySubscriberUid(passportNumber);
            }

            if(passportNumber == null || passportNumber.isEmpty()){
                logger.info(  " saveHospitalInsurance() Impl >> false >> passport number cannot be  cannot be null");
                return AppUtil.createApiResponse(false ,"Passport number can't be null or empty",null);
            }


            hospitalInsuranceRepo.save(hospitalInsurance);

            NotificationDataDTO dataDTO = new NotificationDataDTO();
            NotificationContextDTO contextDTO = new NotificationContextDTO();
            NotificationDTO notificationBody=new NotificationDTO();



            SubscriberFCMToken subscriberFcmToken=subscriberFcmTokenRepoIface.findBysuid(subscriber.getSubscriberUid());
            notificationBody.setTo(subscriberFcmToken.getFcmToken());
            notificationBody.setPriority("high");
            dataDTO.setTitle("Hi " + subscriber.getFullName());
            Map<String, String> ImmigrationNotification = new HashMap<>();
            ImmigrationNotification.put("health", "insurance");
            dataDTO.setBody("Health Insurance claim request submitted successfully");
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
                if (res.getStatusCodeValue() == 200) {
                    System.out.println("Notification sent");
                } else {
                    System.out.println("Notification failed");
                }
            }catch (Exception e){
                return AppUtil.createApiResponse(false,"Something went wrong",null);
            }

            Date start=AppUtil.getCurrentDate();

            Date end=AppUtil.getCurrentDate();
            logModelService.setLogModelDTO(true,subscriber.getSubscriberUid(),null, ServiceNames.OTHER.toString(),AppUtil.getUUId(),"Health Insurance claim request submitted successfully",start,end,"false");
//

            return AppUtil.createApiResponse(true, "saved successfully", null);
        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false, "Something went wrong", null);
        }
    }

    @Override
    public ApiResponse getHospitalInsuranceDetails() {
        try {
            List<HospitalInsurance> HospitalInsurances = hospitalInsuranceRepo.getHospitalInsuranceDetails();

            if (HospitalInsurances == null || HospitalInsurances.isEmpty()) {
                return AppUtil.createApiResponse(false, "Applicant not found", null);
            }

            List<HospitalInsuranceDTO> HospitalInsuranceDtos = new ArrayList<>();
            for (HospitalInsurance i : HospitalInsurances) {

                HospitalInsuranceDTO dto = new HospitalInsuranceDTO();
                dto.setId(i.getId());
                dto.setName(i.getName());
                dto.setGender(i.getGender());
                dto.setPhone_number(i.getPhone_number());
                dto.setPolicy_number(i.getPolicy_number());
                dto.setPolicy_name(i.getPolicy_name());
                dto.setPolicy_start_date(i.getPolicy_start_date());
                dto.setPolicy_end_date(i.getPolicy_end_date());
                dto.setPolicy_status(i.getPolicy_status());

                HospitalInsuranceDtos.add(dto);
            }

            return AppUtil.createApiResponse(true, "Hospital Insurance details retrieved successfully", HospitalInsuranceDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false, "Error retrieving Hospital Insurance details", null);
        }
    }

    @Override
    public ApiResponse getHospitalInsuranceDetailsById(int id) {
        try{
            if(id==0){
                return AppUtil.createApiResponse(false,"Id cannot be null",null);
            }

            HospitalInsurance hospitalInsurance = hospitalInsuranceRepo.getHospitalInsuranceDetailsById(id);

            if(hospitalInsurance==null){
                return AppUtil.createApiResponse(false,"Data related to id cannot be found",null);
            }

            return AppUtil.createApiResponse(true, "Health Insurance details retrieved successfully", hospitalInsurance);
        }catch (Exception e){
            e.printStackTrace();
            return AppUtil.createApiResponse(false, "Error retrieving Money Exchange details", null);
        }
    }

    public void sendNotification(String subscriberUUID,String applicantName,String Message){
        try{
            if(subscriberUUID != null){

                SubscriberFCMToken subscriberFCMToken = subscriberFcmTokenRepository.findBysuid(subscriberUUID);
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                NotificationDTO notificationBody = new NotificationDTO();
                NotificationDataDTO dataDTO = new NotificationDataDTO();
                NotificationContextDTO contextDTO = new NotificationContextDTO();
                notificationBody.setTo(subscriberFCMToken.getFcmToken());
                notificationBody.setPriority("high");
                dataDTO.setTitle("Hi " + applicantName);
                Map<String, String> visaApprovedStatus = new HashMap<>();


                dataDTO.setBody(Message);
                visaApprovedStatus.put("Bank", "Success");

                contextDTO.setPREF_IMMIGRATION_AUTHORITY(visaApprovedStatus);
                dataDTO.setNotificationContext(contextDTO);
                notificationBody.setData(dataDTO);
                HttpEntity<Object> requestEntity = new HttpEntity<>(notificationBody, headers);

                ResponseEntity<Object> res = restTemplate.exchange(notifyUrl, HttpMethod.POST, requestEntity,
                        Object.class);
                if (res.getStatusCodeValue() == 200) {
                    System.out.println("Notification sent");
                } else {
                    System.out.println("Notification failed");
                }
            }
        }catch (Exception e){
            // Exception caught and handled
        }

    }

}

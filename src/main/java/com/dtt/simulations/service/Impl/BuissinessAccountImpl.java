package com.dtt.simulations.service.Impl;


import com.dtt.simulations.dto.*;
import com.dtt.simulations.enums.BuisinessAccountStatus;
import com.dtt.simulations.enums.ServiceNames;
import com.dtt.simulations.model.BusinessAccountModel;
import com.dtt.simulations.model.Subscriber;
import com.dtt.simulations.model.SubscriberFCMToken;
import com.dtt.simulations.repo.*;
import com.dtt.simulations.responseentity.APIRequestHandler;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.BuissinessAccountIface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.PessimisticLockException;
import org.hibernate.QueryTimeoutException;
import org.hibernate.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;

@Service
public class BuissinessAccountImpl implements BuissinessAccountIface {

    private static Logger logger = LoggerFactory.getLogger(BuissinessAccountImpl.class);

    final static String CLASS = "BusinessRegistrationImpl";

    @Autowired
    SubscriberRepoIface subscriberRepo;

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    BuisinessAccountRepo buisinessAccountRepo;

    @Autowired
    SubscriberFcmTokenRepoIface subscriberFcmTokenRepository;

    @Autowired
    LogModelServiceImpl logModelService;

    @Autowired
    SubscriberCertificatesRepoIface subscriberCertificatesRepoIface;

    @Value("${notify.url}")
    public String notifyUrl;

    @Value("${userprofile.url}")
    public String userProfileUrl;

    @Value("${sign.x-coordinate}")
    String xcoordinate;

    @Value("${sign.y-coordinate}")
    String ycoordinate;


    @Value("${pdf.file.path}")
    private String pdfBasePath;

    @Value("${pdf.file.name}")
    private String pdfFileName;

    @Value("${file.sign.url}")
    String filesign;

    @Autowired
    APIRequestHandler apiRequestHandler;

    @Autowired
    SubscriberFcmTokenRepoIface subscriberFcmTokenRepoIface;

    @Autowired
    SubscriberRepoIface subscriberRepoIface;

    @Autowired
    SubscriberStatusRepoIface subscriberStatusRepoIface;


    @Value("${bank.client.id}")
    public String clientId;

    @Value("${bank.secret}")
    public String clientSecret;


    @Value("${bank.access.token.url}")
    public String accessTokenUrl;





    @Override
    public ApiResponse saveBuissinessBankAccount(String businessAccountDto) {
        try{

            logger.info(CLASS + " saveBusinessBankAccount() Impl  >> Inside saveVisa implementation businessAccountDto"+businessAccountDto);

            Date startTime = AppUtil.getTimeStamp();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(businessAccountDto);


            String model=jsonNode.get("model").asText();

            JsonNode modelJson = objectMapper.readTree(model);


            String base64=jsonNode.get("base64").asText();
            String documentType =jsonNode.path("documentType").asText(null);


            if(base64==null){
                return AppUtil.createApiResponse(false,"Bank Registration form cannot be null",null);
            }
            String passportNumber=modelJson.get("id_document_number").asText();
            String applicantName=modelJson.get("given_name").asText();


            if(passportNumber == null || passportNumber.isEmpty()){
                logger.info(CLASS + " saveBuissinessBankAccount() Impl >> false >> passport number cannot be  cannot be null");
                return AppUtil.createApiResponse(false ,"Passport number can't be null or empty",null);
            }


            BusinessAccountModel businessAccountModel = new BusinessAccountModel();

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
            //  subscriber=subscriberRepoIface.findbyDocumentNumber(passportNumber);
            businessAccountModel.setSubscriberUId(subscriber.getSubscriberUid());
            businessAccountModel.setApplicantName(applicantName);
//            businessAccountModel.setCompanyName(companyName);
//                businessAccountModel.setApplicantPhoto(applicantPhoto);
            businessAccountModel.setPassportNumber(passportNumber);
//            businessAccountModel.setNationality(nationality);
            businessAccountModel.setBankAccountOpeningForm(base64);
//                businessAccountModel.setBankAccountOpeningJsonData(jsonFormData);
            businessAccountModel.setApplicationStatus(BuisinessAccountStatus.APPLIED.toString());
            businessAccountModel.setBankAccountHolder(applicantName);
            businessAccountModel.setCreatedOn(AppUtil.getDate());



            BusinessAccountModel businessAccountModelDB=buisinessAccountRepo.save(businessAccountModel);

            String msg = "Your Bank account opening form is submitted successfully";
            sendNotification(subscriber.getSubscriberUid(),applicantName,msg);

            Date EndTime = AppUtil.getTimeStamp();
            logModelService.setLogModelDTO(true,subscriber.getSubscriberUid(),null, ServiceNames.OTHER.toString(),AppUtil.getUUId(),"Your Bank account opening form is submitted successfully",startTime,EndTime,"false");
            return AppUtil.createApiResponse(true,"Your Bank account opening form is submitted successfully",null);

        } catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
                 | PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
            e.printStackTrace();
            logger.error(CLASS + "tradeLicense Exception Something went Wrong, " + e.getMessage());
            return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        } catch (Exception e) {

            logger.error(CLASS + "tradeLicense Exception Something went Wrong, Onboarding Failed " + e.getMessage());
            e.printStackTrace();
            return  AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        }

    }


    @Override
    public ApiResponse approveOrRejectBuisinessAccount(String passport, String status) {
        try{
            Date startTime = AppUtil.getTimeStamp();
            if(passport.isEmpty()){
                return AppUtil.createApiResponse(false,"Passport cannot be null ",null);
            }
            if(status == null || status.isEmpty()){
                return AppUtil.createApiResponse(false,"Status cannot be null or empty",null);
            }

            BusinessAccountModel businessAccountModel = buisinessAccountRepo.getByPassportId(passport);
            String bankAccountNumber = getRandomNumber(11);
            if(businessAccountModel == null){
                return AppUtil.createApiResponse(false,"Business bank Account data not found for given id",null);

            }
            if(status.equals("APPROVED")){
                businessAccountModel.setBankAccountNumber(bankAccountNumber);
                businessAccountModel.setUpdatedOn(AppUtil.getDate());
                businessAccountModel.setBankAccountStatus(BuisinessAccountStatus.ACTIVE.toString());
                businessAccountModel.setApplicationStatus(BuisinessAccountStatus.APPROVED.toString());
//              if(extendedKyc.equals("yes")){
//                  businessAccountModel.setExtendedEkyc(true);
//              }else{
//                  businessAccountModel.setExtendedEkyc(false);
//              }
                buisinessAccountRepo.save(businessAccountModel);
                Date EndTime = AppUtil.getTimeStamp();
                logModelService.setLogModelDTO(true,businessAccountModel.getSubscriberUId(),null, ServiceNames.OTHER.toString(),AppUtil.getUUId(),"Buisiness bank account Approved ",startTime,EndTime,"false");
                String msg = "Your Bank account is Approved";
//              sendNotification(businessAccountModel.getSubscriberUId(),businessAccountModel.getApplicantName(),msg);
                return AppUtil.createApiResponse(true,"Bank account Approved",null);
            } else if (status.equals("REJECTED")) {
                businessAccountModel.setUpdatedOn(AppUtil.getDate());
                businessAccountModel.setApplicationStatus(BuisinessAccountStatus.REJECTED.toString());
//              if(extendedKyc.equals("yes")){
//                  businessAccountModel.setExtendedEkyc(true);
//              }else{
//                  businessAccountModel.setExtendedEkyc(false);
//              }
                buisinessAccountRepo.save(businessAccountModel);
                Date EndTime = AppUtil.getTimeStamp();
                logModelService.setLogModelDTO(true,businessAccountModel.getSubscriberUId(),null, ServiceNames.OTHER.toString(),AppUtil.getUUId(),"Buisiness bank account Rejected",startTime,EndTime,"false");
                String msg = "Your Bank account is Rejected";
//              sendNotification(businessAccountModel.getSubscriberUId(),businessAccountModel.getApplicantName(),msg);
                return AppUtil.createApiResponse(true,"Bank account Rejected",null);
            }else{
                return AppUtil.createApiResponse(false,"input status is not APPROVED nor REJECTED",null);
            }

        } catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
                 | PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
            e.printStackTrace();
            logger.error(CLASS + "tradeLicense Exception Something went Wrong, Onboarding Failed " + e.getMessage());
            return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        } catch (Exception e) {
            logger.error(CLASS + "tradeLicense Exception Something went Wrong, Onboarding Failed " + e.getMessage());
            e.printStackTrace();
            return  AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        }
    }

    @Override
    public ApiResponse getBuisinessAccountByPassport(String passportNumber) {
        try{
            BusinessAccountModel businessAccountModel = buisinessAccountRepo.getByPassportId(passportNumber);
            if(businessAccountModel == null){
                return AppUtil.createApiResponse(false,"Data not found for given passport number",false);
            }
            return AppUtil.createApiResponse(true,"For given passport number Business Bank Account data fetched successfully",businessAccountModel);

        }catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
                | PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
            e.printStackTrace();
            logger.error(CLASS + "tradeLicense Exception Something went Wrong, Onboarding Failed " + e.getMessage());
            return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        } catch (Exception e) {
            logger.error(CLASS + "tradeLicense Exception Something went Wrong, Onboarding Failed " + e.getMessage());
            e.printStackTrace();
            return  AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        }
    }

    @Override
    public ApiResponse getAllBuisinessAccount() {
        try{
            List<BusinessAccountModel> businessAccountModelList = buisinessAccountRepo.getAllData();
            List<BusinessAccountDto> businessAccountDtosList = new ArrayList<>();
            if(businessAccountModelList == null || businessAccountModelList.isEmpty()){
                return AppUtil.createApiResponse(false,"No data found",businessAccountModelList);
            }

            for(BusinessAccountModel i :businessAccountModelList ){
                BusinessAccountDto businessAccountDto = new BusinessAccountDto();
                businessAccountDto.setId(i.getId());
                businessAccountDto.setSubscriberUId(i.getSubscriberUId());
                businessAccountDto.setBankAccountHolder(i.getBankAccountHolder());
                businessAccountDto.setApplicantName(i.getApplicantName());
                businessAccountDto.setCompanyName(i.getCompanyName());
                businessAccountDto.setApplicantPhoto(i.getApplicantPhoto());
                businessAccountDto.setPassportNumber(i.getPassportNumber());
                businessAccountDto.setNationality(i.getNationality());
                businessAccountDto.setVisaNumber(i.getVisaNumber());
                businessAccountDto.setBankAccountOpeningJsonData(i.getBankAccountOpeningJsonData());
                businessAccountDto.setApplicationStatus(i.getApplicationStatus());
                businessAccountDto.setBankAccountNumber(i.getBankAccountNumber());
                businessAccountDto.setBankAccountStatus(i.getBankAccountStatus());
                businessAccountDto.setCreatedDate(i.getCreatedOn());

                businessAccountDtosList.add(businessAccountDto);

            }




            return AppUtil.createApiResponse(true,"All Bank Account data fetched Sucessfully",businessAccountDtosList);

        }catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
                | PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
            e.printStackTrace();
            logger.error(CLASS + "tradeLicense Exception Something went Wrong, Onboarding Failed " + e.getMessage());
            return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        } catch (Exception e) {
            logger.error(CLASS + "tradeLicense Exception Something went Wrong, Onboarding Failed " + e.getMessage());
            e.printStackTrace();
            return  AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        }
    }

    @Override
    public ApiResponse getBuisinessAccountById(int id) {
        try{
            BusinessAccountModel businessAccountModel = buisinessAccountRepo.getbyId(id);
            if(businessAccountModel == null){
                return AppUtil.createApiResponse(false,"Data not found for given passport number",false);
            }
            return AppUtil.createApiResponse(true,"Business account data by id fetched sucessfully",businessAccountModel);

        }catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
                | PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
            e.printStackTrace();
            logger.error(CLASS + "tradeLicense Exception Something went Wrong" + e.getMessage());
            return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        } catch (Exception e) {
            logger.error(CLASS + "tradeLicense Exception Something went Wrong" + e.getMessage());
            e.printStackTrace();
            return  AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        }
    }



    public String getRandomNumber(int maxLength) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            StringBuilder otp = new StringBuilder(maxLength);

            for (int i = 0; i < maxLength; i++) {
                otp.append(secureRandom.nextInt(9));
            }
            return otp.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

                ResponseEntity<String> res = restTemplate.exchange(notifyUrl, HttpMethod.POST, requestEntity,
                        String.class);
                if (res.getStatusCodeValue() == 200) {
                    System.out.println("Notification sent");
                } else {
                    System.out.println("Notification failed");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }





    @Override
    public ApiResponse approveRejectBankAccount(BankApproveRejectDto bankApproveRejectDto) {
        try{
            BusinessAccountModel businessAccountModel = buisinessAccountRepo.getByPassportId(bankApproveRejectDto.getPassport());
            if(businessAccountModel == null){
                return AppUtil.createApiResponse(false,"Cannot find data with Id document no",null);
            }

            if(bankApproveRejectDto.getStatus().equals("APPROVED")) {

                businessAccountModel.setBankAccountNumber(getRandomNumber(11));
                businessAccountModel.setUpdatedOn(AppUtil.getDate());
                businessAccountModel.setBankAccountStatus(BuisinessAccountStatus.ACTIVE.toString());
                businessAccountModel.setApplicationStatus(BuisinessAccountStatus.APPROVED.toString());
                if (bankApproveRejectDto.getExtendedKyc().equals("yes")) {
                    businessAccountModel.setExtendedEkyc(true);
                } else {
                    businessAccountModel.setExtendedEkyc(false);
                }
                businessAccountModel.setKycJson(bankApproveRejectDto.getKycJson());

                buisinessAccountRepo.save(businessAccountModel);


                NotificationDataDTO dataDTO = new NotificationDataDTO();
                NotificationContextDTO contextDTO = new NotificationContextDTO();
                NotificationDTO notificationBody = new NotificationDTO();
                Subscriber subscriber = new Subscriber();


                subscriber = subscriberRepoIface.findbyDocumentNumber(bankApproveRejectDto.getPassport());


                SubscriberFCMToken subscriberFcmToken = subscriberFcmTokenRepoIface.findBysuid(subscriber.getSubscriberUid());
                notificationBody.setTo(subscriberFcmToken.getFcmToken());
                notificationBody.setPriority("high");
                dataDTO.setTitle("Hi " + subscriber.getFullName());
                Map<String, String> ImmigrationNotification = new HashMap<>();
                ImmigrationNotification.put("Bank", "Approve");
                dataDTO.setBody("Bank Account is Approved");
                contextDTO.setPREF_IMMIGRATION_AUTHORITY(ImmigrationNotification);
                dataDTO.setNotificationContext(contextDTO);
                notificationBody.setData(dataDTO);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> requestEntity = new HttpEntity<>(notificationBody, headers);
                try {
                    RestTemplate restTemplate = new RestTemplate();

                    ResponseEntity<Object> res = restTemplate.exchange(notifyUrl, HttpMethod.POST, requestEntity,
                            Object.class);
                    if (res.getStatusCodeValue() == 200) {
                        System.out.println("Notification sent");
                    } else {
                        System.out.println("Notification failed");
                    }
                } catch (Exception e) {
                    return AppUtil.createApiResponse(false, "Something went wrong", null);
                }

                Date end = AppUtil.getCurrentDate();
                Date start = AppUtil.getCurrentDate();
                logModelService.setLogModelDTO(true, subscriber.getSubscriberUid(), null, ServiceNames.OTHER.toString(), AppUtil.getUUId(), "Bank Account Approved", start, end, "false");


                return AppUtil.createApiResponse(true, "Bank Account Approved", null);
            }else{

                businessAccountModel.setUpdatedOn(AppUtil.getDate());
                businessAccountModel.setApplicationStatus(BuisinessAccountStatus.REJECTED.toString());
                if (bankApproveRejectDto.getExtendedKyc().equals("yes")) {
                    businessAccountModel.setExtendedEkyc(true);
                } else {
                    businessAccountModel.setExtendedEkyc(false);
                }
                businessAccountModel.setKycJson(bankApproveRejectDto.getKycJson());
                buisinessAccountRepo.save(businessAccountModel);
                Subscriber subscriber = new Subscriber();
                NotificationDataDTO dataDTO = new NotificationDataDTO();
                NotificationContextDTO contextDTO = new NotificationContextDTO();
                NotificationDTO notificationBody = new NotificationDTO();
                subscriber = subscriberRepoIface.findbyDocumentNumber(bankApproveRejectDto.getPassport());


                SubscriberFCMToken subscriberFcmToken = subscriberFcmTokenRepoIface.findBysuid(subscriber.getSubscriberUid());
                notificationBody.setTo(subscriberFcmToken.getFcmToken());
                notificationBody.setPriority("high");
                dataDTO.setTitle("Hi " + subscriber.getFullName());
                Map<String, String> ImmigrationNotification = new HashMap<>();
                ImmigrationNotification.put("Bank", "Reject");
                dataDTO.setBody("Bank Account is Rejected");
                contextDTO.setPREF_IMMIGRATION_AUTHORITY(ImmigrationNotification);
                dataDTO.setNotificationContext(contextDTO);
                notificationBody.setData(dataDTO);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> requestEntity = new HttpEntity<>(notificationBody, headers);
                try {
                    RestTemplate restTemplate = new RestTemplate();

                    ResponseEntity<Object> res = restTemplate.exchange(notifyUrl, HttpMethod.POST, requestEntity,
                            Object.class);
                    if (res.getStatusCodeValue() == 200) {
                        System.out.println("Notification sent");
                    } else {
                        System.out.println("Notification failed");
                    }
                } catch (Exception e) {
                    return AppUtil.createApiResponse(false, "Something went wrong", null);
                }

                Date end = AppUtil.getCurrentDate();
                Date start = AppUtil.getCurrentDate();
                logModelService.setLogModelDTO(true, subscriber.getSubscriberUid(), null, ServiceNames.OTHER.toString(), AppUtil.getUUId(), "Bank Account Rejected", start, end, "false");
                return AppUtil.createApiResponse(true,"Bank account Rejected",null);


            }
        }catch (Exception e){
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }
    }

    @Override
    public ApiResponse viewforApproveReject(int id) {
        try {
            BusinessAccountModel businessAccountModel = buisinessAccountRepo.getbyId(id);
            if(businessAccountModel == null){
                return AppUtil.createApiResponse(false,"Data not found for given passport number",false);
            }
            ViewApproveRejectForBankDto viewApproveRejectForBankDto = new ViewApproveRejectForBankDto();


            if(businessAccountModel.isExtendedEkyc()) {

                String kycJsonString = businessAccountModel.getKycJson();

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode kycData = objectMapper.readTree(kycJsonString);

                viewApproveRejectForBankDto.setExtendedKyc(businessAccountModel.isExtendedEkyc());
                viewApproveRejectForBankDto.setKycReport(kycData.get("Ekyc_document").asText());
            }else{
                String kycJsonString = businessAccountModel.getKycJson();

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode kycData = objectMapper.readTree(kycJsonString);

                viewApproveRejectForBankDto.setExtendedKyc(businessAccountModel.isExtendedEkyc());
                viewApproveRejectForBankDto.setKycReport(kycData.get("kyc_document").asText());
            }


            return AppUtil.createApiResponse(true,"For given passport number Business Bank Account data fetched successfully",viewApproveRejectForBankDto);

        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false, "Something went wrong", null);

        }
    }


    @Override
    public ApiResponse saveBankAccountOpeningWeb(String bankAccountDto) {
        try{

            Date startTime = AppUtil.getTimeStamp();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode modelJson=objectMapper.readTree(bankAccountDto);



            String passportNumber= modelJson.path("idDocNumber").asText(null);
            String applicantName= modelJson.path("fullName").asText(null);

            String country = modelJson.path("nationality").asText(null);



            String photo =  modelJson.path("photo").asText(null);
            String pid_document = modelJson.path("pidDocument").asText(null);


            String documentType =modelJson.path("documentType").asText(null);

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
                logger.info(CLASS + " saveBuissinessBankAccount() Impl >> false >> passport number cannot be  cannot be null");
                return AppUtil.createApiResponse(false ,"Passport number can't be null or empty",null);
            }

            BusinessAccountModel businessAccountModel = new BusinessAccountModel();



            businessAccountModel.setSubscriberUId(subscriber.getSubscriberUid());
            businessAccountModel.setApplicantName(applicantName);


            businessAccountModel.setPassportNumber(passportNumber);
            businessAccountModel.setVisitorDocument(pid_document);

            businessAccountModel.setNationality(country);
            businessAccountModel.setApplicantPhoto(photo);

            businessAccountModel.setApplicationStatus(BuisinessAccountStatus.APPLIED.toString());
            businessAccountModel.setBankAccountHolder(applicantName);
            businessAccountModel.setCreatedOn(AppUtil.getDate());

            FileSigningDto fileSigningDto = new FileSigningDto();
            fileSigningDto.setSuid(subscriber.getSubscriberUid());

//            ApiResponse res = fileSigning(fileSigningDto);


//            if(!res.isSuccess()){
//                System.out.println("Business account file signing failed");
//                return AppUtil.createApiResponse(false,"File signing failed",null);
//            }


            BusinessAccountModel businessAccountModelDB=buisinessAccountRepo.save(businessAccountModel);

            String msg = "Your Bank account opening form is submitted successfully";
            sendNotification(subscriber.getSubscriberUid(),applicantName,msg);

            Date EndTime = AppUtil.getTimeStamp();
            logModelService.setLogModelDTO(true,subscriber.getSubscriberUid(),null, ServiceNames.OTHER.toString(),AppUtil.getUUId(),"Your Bank account opening form is submitted successfully",startTime,EndTime,"false");
            return AppUtil.createApiResponse(true,"Your Bank account opening form is submitted successfully",null);



        } catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
                 | PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
            e.printStackTrace();
            logger.error(CLASS + "tradeLicense Exception Something went Wrong, " + e.getMessage());
            return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        } catch (Exception e) {

            logger.error(CLASS + "tradeLicense Exception Something went Wrong" + e.getMessage());
            e.printStackTrace();
            return  AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        }
    }


    @Override
    public ApiResponse getUserProfile(BankRequestDto bankRequestDto) {

        try{

            ApiResponse response1 = getAccessToken();
            if(!response1.isSuccess()){
                return AppUtil.createApiResponse(false,"Access token cannot be fetched",null);
            }

            String url1 = userProfileUrl;



            BankRequestDto bankRequestDto1 = new BankRequestDto();

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.set("UgPassAuthorization", "Bearer "+response1.getResult());





            bankRequestDto1.setUserId(bankRequestDto.getUserId());
            bankRequestDto1.setUserIdType(bankRequestDto.getUserIdType());
            bankRequestDto1.setProfileType(bankRequestDto.getProfileType());
            bankRequestDto1.setPurpose(bankRequestDto.getPurpose());

            HttpEntity<Object> reqEntity1 = new HttpEntity<>(bankRequestDto1, headers);


            String response = String.valueOf(restTemplate.exchange(url1, HttpMethod.POST, reqEntity1, String.class));
            String jsonString = response.substring(response.indexOf("{"));
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponse apiResponse = objectMapper.readValue(jsonString,ApiResponse.class);
            if(!apiResponse.isSuccess()){
                return AppUtil.createApiResponse(false,apiResponse.getMessage(),null);
            }
            return AppUtil.createApiResponse(apiResponse.isSuccess(),apiResponse.getMessage(),apiResponse.getResult());

        }catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException |
                PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException ex) {
            ex.printStackTrace();
            return AppUtil.createApiResponse(false, "Something went wrong", null);
        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false, "Something went wrong", null);
        }

    }


    public ApiResponse getAccessToken() throws JsonProcessingException {
        try{
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            String authHeaderVal=clientId+":"+clientSecret;
            String clientIdBase64= Base64.getEncoder().encodeToString(authHeaderVal.getBytes());
            headers.set("UgPassAuthorization", "Basic "+clientIdBase64);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "client_credentials");
            map.add("client_id", clientId);

            HttpEntity<MultiValueMap<String, String>> entity1 = new HttpEntity<>(map,headers);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
            ResponseEntity<String> response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST, entity,
                    String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());


                if (root.has("error")) {

                    return AppUtil.createApiResponse(false, "Error from access token api", null);
                }

                JsonNode access_token = root.path("access_token");
                String accessToken = access_token.asText();

                return AppUtil.createApiResponse(true, "Fetched", accessToken);
            }
            else{
                return AppUtil.createApiResponse(false, "Error from access token api", null);
            }

        }catch (Exception e){
            e.printStackTrace();
            return  AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
        }
    }


//    @Override
//    public ApiResponse fileSign(fileSigningDto fileSigningDto) {
//        try{
//
//            Map<String, Object> mapmodel = new HashMap<>();
//            mapmodel.put("accountType" ,"self");
//            mapmodel.put("accountId",fileSigningDto.getSuid());
//            mapmodel.put("documentType", "PADES");
//            mapmodel.put("subscriberUniqueId" ,fileSigningDto.getSuid());
//            mapmodel.put("organizationUid" ,null);
//            mapmodel.put("qrCodeRequired" ,false);
//
//
//
//            Map<String, Object> signPlaceHolderCoordinates = new HashMap<>();
//            signPlaceHolderCoordinates.put("pageNumber", 1);
//            signPlaceHolderCoordinates.put("signatureXaxis", xcoordinate);
//            signPlaceHolderCoordinates.put("signatureYaxis", ycoordinate);
//            signPlaceHolderCoordinates.put("imgWidth",126);
//            signPlaceHolderCoordinates.put("imgHeight",30.09);
//
//            mapmodel.put("placeHolderCoordinates",signPlaceHolderCoordinates);
//
//            mapmodel.put("esealPlaceHolderCoordinates",null);
//            mapmodel.put("deligationSign",null);
//            mapmodel.put("recipientName",null);
//            mapmodel.put("recipientEncryptedString",null);
//            mapmodel.put("authPin",null);
//            mapmodel.put("signPin",null);
//            mapmodel.put("mobile",false);
//
//            // Convert the model object to
//            ObjectMapper objectMapper = new ObjectMapper();
//            String model = objectMapper.writeValueAsString(mapmodel);
//            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//            body.add("model", model);
//
//            Path testFile = Files.createTempFile("temp", ".pdf");
//
//            byte[] byteArray = Base64.getDecoder().decode(fileSigningDto.getBase64());
//
//            Files.write(testFile, byteArray);
////					System.out.println(AppUtil.getBase64FromByteArr(decodedBytes));
//
//            Resource resource = new FileSystemResource(testFile.toFile());
//
//            System.out.println("Resource"+resource);
//
//            body.add("file", resource);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//            HttpEntity<Object> reqEntity = new HttpEntity<>(body, headers);
//            ApiResponse res = apiRequestHandler.handleApiRequest(consentsign, HttpMethod.POST, reqEntity);
    ////            ResponseEntity<ApiResponse> res = restTemplate.exchange(consentsign, HttpMethod.POST, reqEntity, ApiResponse.class);
//            if (!res.isSuccess()) {
//                return AppUtil.createApiResponse(false, res.getMessage(), null);
//            }
//            return AppUtil.createApiResponse(true, res.getMessage(), res.getResult());
//
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return AppUtil.createApiResponse(false, "something went wrong", null);
//        }

    public ApiResponse fileSigning(FileSigningDto fileSigningDto) {
        try{
            // Your existing map setup code remains the same...
            Map<String, Object> mapmodel = new HashMap<>();
            mapmodel.put("accountType" ,"self");
            mapmodel.put("accountId",fileSigningDto.getSuid());
            mapmodel.put("documentType", "PADES");
            mapmodel.put("subscriberUniqueId" ,fileSigningDto.getSuid());
            mapmodel.put("organizationUid" ,null);
            mapmodel.put("qrCodeRequired" ,false);

            Map<String, Object> signPlaceHolderCoordinates = new HashMap<>();
            signPlaceHolderCoordinates.put("pageNumber", 1);
            signPlaceHolderCoordinates.put("signatureXaxis", xcoordinate);
            signPlaceHolderCoordinates.put("signatureYaxis", ycoordinate);
            signPlaceHolderCoordinates.put("imgWidth",126);
            signPlaceHolderCoordinates.put("imgHeight",30.09);

            mapmodel.put("placeHolderCoordinates",signPlaceHolderCoordinates);
            mapmodel.put("esealPlaceHolderCoordinates",null);
            mapmodel.put("deligationSign",null);
            mapmodel.put("recipientName",null);
            mapmodel.put("recipientEncryptedString",null);
            mapmodel.put("authPin",null);
            mapmodel.put("signPin",null);
            mapmodel.put("mobile",false);

            ObjectMapper objectMapper = new ObjectMapper();
            String model = objectMapper.writeValueAsString(mapmodel);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("model", model);

            String fullPath = pdfBasePath + pdfFileName;

            Path pdfPath = Paths.get(fullPath);

            if (!Files.exists(pdfPath)) {
                System.out.println("PDF file not found at path : "+fullPath);
                return AppUtil.createApiResponse(false,
                        "PDF file not found at path: " + fullPath, null);
            }

            Resource resource = new FileSystemResource(pdfPath.toFile());

            body.add("file", resource);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<Object> reqEntity = new HttpEntity<>(body, headers);

            ApiResponse res = apiRequestHandler.handleApiRequest(filesign, HttpMethod.POST, reqEntity);
            if (!res.isSuccess()) {
                return AppUtil.createApiResponse(false, res.getMessage(), null);
            }
            return AppUtil.createApiResponse(true, res.getMessage(), res.getResult());

        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false, "something went wrong", null);
        }
    }

}


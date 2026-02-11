package com.dtt.simulations.service.Impl;


import com.dtt.simulations.dto.*;
import com.dtt.simulations.model.Subscriber;
import com.dtt.simulations.model.SubscriberOnboardingData;
import com.dtt.simulations.repo.SubscriberOnboardingDataRepoIface;
import com.dtt.simulations.repo.SubscriberRepoIface;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.FaceVerificationIface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class FaceVerificationImpl implements FaceVerificationIface {

    @Autowired
    SubscriberRepoIface subscriberRepoIface;

    @Autowired
    SubscriberOnboardingDataRepoIface subscriberOnboardingDataRepoIface;

    RestTemplate restTemplate = new RestTemplate();

    @Value("${subscriber.ob.data}")
    String subscriberObData;

    @Value("${verify.face}")
    String verifyFace;

     ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public ApiResponse verifyFace(VerifyFaceDto verifyFaceDto) {
        try{
            if(verifyFaceDto.getCapturedImage()==null || verifyFaceDto.getCapturedImage().isEmpty()){
                return AppUtil.createApiResponse(false,"Image is empty",null);
            }

            if(verifyFaceDto.getDocumentNumber()==null || verifyFaceDto.getDocumentNumber().isEmpty()){
                return AppUtil.createApiResponse(false,"Document number is empty",null);
            }

            Subscriber subscriber = subscriberRepoIface.getSubscriberDetail(verifyFaceDto.getDocumentNumber());
            System.out.println("subscriberrrrr::::::::::"+subscriber);
            if(subscriber==null){
                return AppUtil.createApiResponse(false,"Subscriber with this doc number not found",null);
            }
            Optional<SubscriberOnboardingData> subscriberOnboardingDataOpt =
                    subscriberOnboardingDataRepoIface
                            .findTopByIdDocNumberIgnoreCaseOrderByCreatedDateDesc(
                                    verifyFaceDto.getDocumentNumber()
                            );
            System.out.println("ouygdi::::::::::"+subscriberOnboardingDataOpt);

            SubscriberOnboardingData subscriberOnboardingData = subscriberOnboardingDataOpt.orElse(null);
            System.out.println("sub ob dat:::::"+subscriberOnboardingData);

           // SubscriberOnboardingData subscriberOnboardingData = subscriberOnboardingDataRepoIface.findSubscriberByDocIdLatestRecord(verifyFaceDto.getDocumentNumber());

            if(subscriberOnboardingData==null)
            {
                return AppUtil.createApiResponse(false,"Subscriber with this doc number not found",null);
            }

            FaceVerificationResponseDto faceVerificationResponseDto = new FaceVerificationResponseDto();
            faceVerificationResponseDto.setName(subscriber.getFullName());
            faceVerificationResponseDto.setEmail(subscriber.getEmailId());
            faceVerificationResponseDto.setMobileNumber(subscriber.getMobileNumber());
            faceVerificationResponseDto.setIdDocNumber(subscriber.getIdDocNumber());
            faceVerificationResponseDto.setDob(subscriber.getDateOfBirth());
            faceVerificationResponseDto.setGender(subscriberOnboardingData.getGender());
            String jsonString = subscriberOnboardingData.getOnboardingDataFieldsJson();



            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String nationality = jsonNode.get("nationality").asText();
            faceVerificationResponseDto.setNationality(nationality);


            String url = subscriberObData;
            CheckSubscriberFaceDto checkSubscriberFacedto = new CheckSubscriberFaceDto();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("deviceId", "WEB");
            checkSubscriberFacedto.setSuid(subscriber.getSubscriberUid());
            checkSubscriberFacedto.setSelfieRequired(true);

            HttpEntity<Object> reqEntity = new HttpEntity<>(checkSubscriberFacedto, headers);
            ResponseEntity<ApiResponse> res = restTemplate.exchange(url, HttpMethod.POST, reqEntity, ApiResponse.class);


            if (!res.getBody().isSuccess()) {
                return AppUtil.createApiResponse(false, res.getBody().getMessage(), null);
            }


            String Response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(res.getBody().getResult());
            CheckSubscriberFaceResponseDto checkSubscriberFacedtoResponse = objectMapper.readValue(Response, CheckSubscriberFaceResponseDto.class);


            String ugpassPhoto = checkSubscriberFacedtoResponse.getSubscriberData().getSubscriberSelfie();



            String url1 = verifyFace;
            System.out.println("Face URL:::"+ url1);
            VerifyImageDto verifyImageDto = new VerifyImageDto();
            HttpHeaders headers1 = new HttpHeaders();
            headers1.setContentType(MediaType.APPLICATION_JSON);
            headers1.setBasicAuth("admin", "K9rzgr47wz");
            verifyImageDto.setImage1(ugpassPhoto);
            verifyImageDto.setImage2(verifyFaceDto.getCapturedImage());
            verifyImageDto.setLivenesscheck(false);
            HttpEntity<Object> reqEntity1 = new HttpEntity<>(verifyImageDto, headers1);
            ResponseEntity<ApiResponse> res1 = restTemplate.exchange(url1, HttpMethod.POST, reqEntity1, ApiResponse.class);


            if (!res1.getBody().isSuccess()) {
                return AppUtil.createApiResponse(false, res1.getBody().getMessage(), null);
            }

            faceVerificationResponseDto.setUserPhoto(ugpassPhoto);


            return AppUtil.createApiResponse(true, "Face Match Successful", faceVerificationResponseDto);


        } catch (JsonMappingException e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }catch (Exception e){
            
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }

    }
}

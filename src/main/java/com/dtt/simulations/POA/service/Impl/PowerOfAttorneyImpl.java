package com.dtt.simulations.POA.service.Impl;

import com.dtt.simulations.POA.dto.*;
import com.dtt.simulations.POA.model.*;
import com.dtt.simulations.POA.repo.*;
import com.dtt.simulations.POA.responseentity.ApiResponsePOA;
import com.dtt.simulations.POA.responseentity.ApiResponseW3c;
import com.dtt.simulations.POA.responseentity.AppUtilPOA;
import com.dtt.simulations.POA.service.Iface.PowerOfAttorneyIface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.layout.element.Text;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;


@Service
@EnableScheduling
public class PowerOfAttorneyImpl implements PowerOfAttorneyIface {

    @Value("${poa.Coordinates}")
    public String poaCoordinates;

    private static Logger logger = LoggerFactory.getLogger(PowerOfAttorneyImpl.class);


    final static String CLASS = "PowerOfAttorney Implementation ";


    @Value(value = "${create.request}")
    String createRequestUrl;

    @Value(value = "${fetch.verify}")
    String fetchVerifyUrl;
    @Value(value = "${notification}")
    String notificationUrl;

    @Value(value = "${api.access}")
    String accessApi;

    @Value(value = "${save.doc}")
    String saveDocUrl;

    @Value(value = "${doc.status}")
    String docStatus;

    @Value(value = "${edms.url}")
    String edmsUrl;

    @Value(value = "${org.id}")
    String orgId;

    @Value(value = "${org.name}")
    String orgName;

    @Value(value = "${pdf.embed}")
    String pdfEmbed;

    @Value(value = "${scope.list}")
    List<String> scopeList;

    @Autowired
    PoaSubscriberFcmTokenRepoIface subscriberFcmTokenRepoIface;

    @Autowired
    PoaPowerOfAttorneyRepo powerOfAttorneyRepo;

    @Autowired
    PoaSubscriberRepo subscriberRepo;

    @Autowired
    PoaTemporaryPoaRepo temporaryPoaRepo;

    @Autowired
    PoaOrgContactEmailRepo orgContactEmailRepo;


  @Autowired
   PoaCredentialsRepo poaCredentialsRepo;

  @Autowired
  PoaVisitorCompleteDetailsRepo visitorCompleteDetailsRepo;

    @Autowired
    private PoaRepositoryImpl poaRepository;


    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ApiResponsePOA savePoaInTemp(SavePoaDto powerOfAttorney) {
        try {
            System.out.println("response:::::::"+powerOfAttorney);
            if (powerOfAttorney.getPrincipleEmail() == null || powerOfAttorney.getPrincipleEmail().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Principal Email cannot be null or empty", null);
            }
            if (powerOfAttorney.getAgentEmail() == null || powerOfAttorney.getAgentEmail().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Agent Email cannot be null or empty", null);
            }
            if (powerOfAttorney.getNotaryEmail() == null || powerOfAttorney.getNotaryEmail().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Notary Email cannot be null or empty", null);
            }
            if (powerOfAttorney.getPrincipleName() == null || powerOfAttorney.getPrincipleName().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Principal Name cannot be null or empty", null);
            }
            if (powerOfAttorney.getAgentName() == null || powerOfAttorney.getAgentName().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Agent Name cannot be null or empty", null);
            }
            if (powerOfAttorney.getPrincipleIdDocNumber() == null || powerOfAttorney.getPrincipleIdDocNumber().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Principal Id Document Number cannot be null or empty", null);
            }
            if (powerOfAttorney.getAgentIdDocNumber() == null || powerOfAttorney.getAgentIdDocNumber().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Agent Id Document Number cannot be null or empty", null);
            }

//            Subscriber principal = subscriberRepo.fetchSubscriberByEmailId(powerOfAttorney.getPrincipleEmail());
//            Subscriber agent = subscriberRepo.fetchSubscriberByEmailId(powerOfAttorney.getAgentEmail());
//            Subscriber notary = subscriberRepo.fetchSubscriberByEmailId(powerOfAttorney.getNotaryEmail());

            PoaSubscriber principal = subscriberRepo.fetchSubscriberByIDDocNumber(powerOfAttorney.getPrincipleIdDocNumber());
            PoaSubscriber agent = subscriberRepo.fetchSubscriberByIDDocNumber(powerOfAttorney.getAgentIdDocNumber());
//            PoaSubscriber notary = subscriberRepo.fetchSubscriberByIDDocNumber(powerOfAttorney.getNotaryIdDocNumber());
            PoaSubscriber notary = subscriberRepo.fetchSubscriberByEmailId(powerOfAttorney.getNotaryEmail());
            if (principal == null) {
                return AppUtilPOA.createApiResponse(false, "Principal Not Found", null);
            }
            if (agent == null) {
                return AppUtilPOA.createApiResponse(false, "Agent Not Found", null);
            }
            if (notary == null) {
                return AppUtilPOA.createApiResponse(false, "Notary Not Found", null);
            }
            if (!principal.getIdDocNumber().equals(powerOfAttorney.getPrincipleIdDocNumber())) {
                return AppUtilPOA.createApiResponse(false, "Principal Id Document does not matched", null);

            }
            if (!agent.getIdDocNumber().trim().equals(powerOfAttorney.getAgentIdDocNumber().trim())) {
                return AppUtilPOA.createApiResponse(false, "Agent Id Document does not matched", null);
            }

            if (powerOfAttorney.getPrincipleEmail().equals(powerOfAttorney.getAgentEmail())) {
                return AppUtilPOA.createApiResponse(false, "Both Principal And Agent cannot be same", null);
            } else if (powerOfAttorney.getPrincipleName().equals(powerOfAttorney.getNotaryEmail())) {
                return AppUtilPOA.createApiResponse(false, "Both Principal And Notary cannot be same", null);

            } else if (powerOfAttorney.getAgentEmail().equals(powerOfAttorney.getNotaryEmail())) {
                return AppUtilPOA.createApiResponse(false, "Both Agent And Notary cannot be same", null);
            }


            PoaTemporary temporaryPoa = new PoaTemporary();

            temporaryPoa.setPrincipleEmail(principal.getEmailId());
            temporaryPoa.setPrincipleIdDocNumber(principal.getIdDocNumber());
            temporaryPoa.setPrincipleName(principal.getFullName());
            temporaryPoa.setPrincipleSuid(principal.getSubscriberUid());

            temporaryPoa.setNotaryIdDocNumber(notary.getIdDocNumber());
            temporaryPoa.setNotaryName(notary.getFullName());
            temporaryPoa.setNotaryEmail(notary.getEmailId());
            temporaryPoa.setNotarySuid(notary.getSubscriberUid());


            temporaryPoa.setAgentEmail(agent.getEmailId());
            temporaryPoa.setAgentIdDocNumber(agent.getIdDocNumber());
            temporaryPoa.setAgentName(agent.getFullName());
            temporaryPoa.setAgentSuid(agent.getSubscriberUid());

            temporaryPoa.setEffectiveDate(powerOfAttorney.getEffectiveFrom());
            temporaryPoa.setScope(powerOfAttorney.getScope());

            temporaryPoa.setCreatedOn(AppUtilPOA.getDate());
            temporaryPoa.setUpdatedOn(AppUtilPOA.getDate());
            temporaryPoa.setStatus("APPLIED");


            PowerOfAttorneyDto authorizationDetails = new PowerOfAttorneyDto();
            authorizationDetails.setEndDate(powerOfAttorney.getEffectiveFrom());
            authorizationDetails.setStartDate(AppUtilPOA.getDate().substring(0, 10));
            authorizationDetails.setScope(powerOfAttorney.getScope());
            authorizationDetails.setAgentName(powerOfAttorney.getAgentName());
            authorizationDetails.setPrincipalName(powerOfAttorney.getPrincipleName());
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<Object> request = new HttpEntity<>(authorizationDetails, headers);
//            ResponseEntity<String> response = restTemplate.exchange(pdfEmbed, HttpMethod.POST, request, String.class);

            String response = modifypoa(authorizationDetails);
            temporaryPoa.setPoaRequestForm(response);

            PoaTemporary savedPoa = temporaryPoaRepo.save(temporaryPoa);

            ApiResponsePOA response1 = initiateSigning(savedPoa.getId());
            if(!response1.isSuccess()){
                return AppUtilPOA.createApiResponse(response1.isSuccess(),response1.getMessage(),response1.getResult());
            }
            return AppUtilPOA.createApiResponse(true, "Request Submitted successfully", savedPoa);

        } catch (Exception e) {
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false,"something went wrong ",null);

        }
    }




    public ApiResponsePOA initiateSigning(int id) {
        try {

            PoaTemporary powerOfAttorney = temporaryPoaRepo.fetchById(id);
            if (powerOfAttorney.getStatus().equals("IN PROGRESS")) {
                return AppUtilPOA.createApiResponse(false, "Initiated Signing Already", null);
            }
            powerOfAttorney.setStatus("IN PROGRESS");
            temporaryPoaRepo.save(powerOfAttorney);
            Thread thread = new Thread(() -> {

                try {
                    this.initiateSigningOriginal(id);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
            thread.start();
            return AppUtilPOA.createApiResponse(true, "Signing Initiated Successfully", null);

        } catch (Exception e) {
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false, "Something went wrong", null);
        }
    }



    public ApiResponsePOA initiateSigningOriginal(int id) {
        try {
//            LocalDateTime now = LocalDateTime.now();
//            LocalDateTime oneYearLater = now.plusYears(1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
            String oneYearLater = ZonedDateTime.now(ZoneOffset.UTC).plusYears(1).format(formatter);

            PoaTemporary powerOfAttorney = temporaryPoaRepo.fetchById(id);
            Model model = new Model();
            model.setAction("send");
            model.setDisableOrder(false);
            model.setDocData("");
            model.setAllowToAssignSomeone(false);
            model.setWatermarkText("");
            model.setTempId("");
            model.setSignatureEnvironment("");
            model.setDocSerialNo("");
            ClassPathResource resource = new ClassPathResource("static/" + "MultiSign.pdf");
            File file = resource.getFile();
//           File file=new File("/MultiSign.pdf");

            model.setDocData("");
            DocDetails docDetails = new DocDetails();
            docDetails.setAnnotations("");
            docDetails.setExpiredate(oneYearLater);
            docDetails.setOrgn_name("");
            docDetails.setAutoReminders("");
            docDetails.setDaysToComplete("2");
            docDetails.setOwnerName(powerOfAttorney.getPrincipleName());
            docDetails.setRemindEvery("");
            docDetails.setNoteToAll("");
            docDetails.setSignaturesRequiredCount(3);
            docDetails.setWatermark("");
            docDetails.setTempname("POA.pdf");
            Receps primary = new Receps();
            List<Receps> recepsList = new ArrayList<>();

            Receps agent = new Receps();
            Receps notary = new Receps();
            primary.setAllowComments(false);
            primary.setEmail(powerOfAttorney.getPrincipleEmail());
            primary.setSuid(powerOfAttorney.getPrincipleSuid());
            primary.setIndex("");
            primary.setOrder(1);
            primary.setAlternateSignatories("");
            List<User> alter = new ArrayList<>();
            primary.setAlternateSignatoriesList(alter);
            primary.setName(powerOfAttorney.getPrincipleName());
            primary.setEseal(false);
            primary.setSignedBy("");
            primary.setDelegationId("");
            primary.setOrgName("");
            primary.setSignatureMandatory(true);
            primary.setReferredTo("");
            primary.setReferredBy("");
            primary.setOrgUID("");
            primary.setHasDelegation(false);
            recepsList.add(primary);
            agent.setAllowComments(false);
            agent.setEmail(powerOfAttorney.getAgentEmail());
            agent.setSuid(powerOfAttorney.getAgentSuid());
            agent.setIndex("");
            agent.setOrder(2);
            agent.setAlternateSignatories("");
            agent.setAlternateSignatoriesList(alter);
            agent.setName(powerOfAttorney.getAgentName());
            agent.setEseal(false);
            agent.setSignedBy("");
            agent.setDelegationId("");
            agent.setOrgName("");
            agent.setSignatureMandatory(true);
            agent.setReferredTo("");
            agent.setReferredBy("");
            agent.setOrgUID("");
            agent.setHasDelegation(false);
            recepsList.add(agent);
            notary.setAllowComments(false);
            notary.setEmail(powerOfAttorney.getNotaryEmail());
            notary.setSuid(powerOfAttorney.getNotarySuid());
            notary.setIndex("");
            notary.setOrder(3);
            notary.setAlternateSignatories("");
            notary.setAlternateSignatoriesList(alter);
            notary.setName(powerOfAttorney.getNotaryName());
            notary.setEseal(true);
            notary.setSignedBy("");
            notary.setDelegationId("");
            notary.setOrgName(orgName);
            notary.setSignatureMandatory(true);
            notary.setReferredTo("");
            notary.setReferredBy("");
            notary.setOrgUID(orgId);
            notary.setHasDelegation(false);
            recepsList.add(notary);
            docDetails.setReceps(recepsList);
            model.setDocDetails(docDetails);
            Map<String, EsealCordinates> esealCordinatesMap = new HashMap<>();
            EsealCordinates esealCordinates = new EsealCordinates();
            esealCordinates.setFieldName("");
            esealCordinates.setOrganizationID(orgId);
            esealCordinates.setPosX(336.8);
            esealCordinates.setPosY(152.8);
            esealCordinates.setHeight(96);
            esealCordinates.setWidth(96);
            esealCordinates.setPageNumber(2);
            esealCordinatesMap.put(powerOfAttorney.getNotarySuid(), esealCordinates);
            model.setEsealCords(esealCordinatesMap);
            Map<String, Coordinates> signCords = new HashMap<>();
            Coordinates principalCordinates = new Coordinates();
            principalCordinates.setFieldName("");
            principalCordinates.setHeight(45);
            principalCordinates.setWidth(174);
            principalCordinates.setPosX(72);
            principalCordinates.setPosY(540);
            principalCordinates.setPageNumber(1);
            signCords.put(powerOfAttorney.getPrincipleSuid(), principalCordinates);
            Coordinates agetnCordinates = new Coordinates();
            agetnCordinates.setFieldName("");
            agetnCordinates.setHeight(45);
            agetnCordinates.setWidth(169);
            agetnCordinates.setPosX(74.4);
            agetnCordinates.setPosY(104);
//           agetnCordinates.setHeight(74.4);
            agetnCordinates.setPageNumber(2);
            signCords.put(powerOfAttorney.getAgentSuid(), agetnCordinates);
            Coordinates notaryCordinates = new Coordinates();
            notaryCordinates.setFieldName("");
            notaryCordinates.setHeight(46);
            notaryCordinates.setWidth(169);
            notaryCordinates.setPosX(76.8);
            notaryCordinates.setPosY(199.2);
            notaryCordinates.setPageNumber(2);
            signCords.put(powerOfAttorney.getNotarySuid(), notaryCordinates);
            model.setSignCords(signCords);
            model.setEntityName("");
            model.setMobile(false);
            model.setQrCords(null);
            model.setQrCodeRequired(false);
            model.setMultisign(true);
            model.setFileName(file.getName());
//           model.setActoken();
            MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
            String base64 = powerOfAttorney.getPoaRequestForm();
            byte[] byteArray = Base64.getDecoder().decode(base64);

            Path testFile = Files.createTempFile("POA", ".pdf");

            Files.write(testFile, byteArray);
//
            model.setFileName("POA.pdf");

//           Resource resourcenew = new FileSystemResource(testFile.toFile());


            String url = accessApi;
            GetAccessTokenDto getAccessTokenDto = new GetAccessTokenDto();
            getAccessTokenDto.setEmail(powerOfAttorney.getPrincipleEmail());
            getAccessTokenDto.setName(powerOfAttorney.getPrincipleName());
            getAccessTokenDto.setSuid(powerOfAttorney.getPrincipleSuid());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth("QT95Ix3sjFtY9jf6SnxnelXaqwUdJAXWoTI9RBEhVfk3oJdi", "sLHmG9J26omJmLfSzqWu8qByGi82nyaSIhPgs63gEU7h6D9TPDL3eYygHwPxjBmy");
            HttpEntity<Object> requestEntity = new HttpEntity<>(getAccessTokenDto, headers);

            ResponseEntity<ApiResponsePOA> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponsePOA.class);

            String res = objectMapper.writeValueAsString(response.getBody().getResult());
            AccessTokenResponseDto accessTokenResponseDto = objectMapper.readValue(res, AccessTokenResponseDto.class);

            model.setActoken("");


            String mod = objectMapper.writeValueAsString(model);


            Files.write(testFile, byteArray);



            Resource resourcenew = new FileSystemResource(testFile.toFile());

            formData.add("file", resourcenew);
            formData.add("model", mod);
            String saveDoc = saveDocUrl;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            httpHeaders.set("x-access-token", accessTokenResponseDto.getApiToken());
            HttpEntity<Object> requestEntityNew = new HttpEntity<>(formData, httpHeaders);
            ResponseEntity<ApiResponsePOA> responseNew = restTemplate.exchange(saveDoc, HttpMethod.POST, requestEntityNew, ApiResponsePOA.class);

            String resNew = objectMapper.writeValueAsString(responseNew.getBody().getResult());
            Result mappedResult = objectMapper.readValue(resNew, Result.class);

//            TempDocStatus tempDocStatus = new TempDocStatus();
//            tempDocStatus.setDocId(mappedResult.getTempId());
//            tempDocStatus.setStatus("IN Progress");
//            tempDocStatus.setPoaId(id);
//            tempDocStatusRepo.save(tempDocStatus);


            powerOfAttorney.setDocId(mappedResult.getTempId());
            powerOfAttorney.setStatus("IN Progress");
            powerOfAttorney.setPoaId(id);
            temporaryPoaRepo.save(powerOfAttorney);


            NotificationDTOPOA notificationDTO = new NotificationDTOPOA();
            PoaSubscriberFcmToken subscriberFcmToken = new PoaSubscriberFcmToken();
            NotificationContextDTOPOA notificationContextDTO = new NotificationContextDTOPOA();
            Map<String, String> contextDto = new HashMap<>();
            contextDto.put("glitrchtip", "raised");
            notificationContextDTO.setPREF_GLITCHTIP(contextDto);
            NotificationDataDTOPOA notificationDataDTO = new NotificationDataDTOPOA();
            notificationDataDTO.setBody("Your POA document is ready for signing. Please sign the document");
            notificationDataDTO.setTitle("Hi " + powerOfAttorney.getPrincipleName());
            notificationDataDTO.setNotificationContext(notificationContextDTO);
            notificationDTO.setData(notificationDataDTO);
            notificationDTO.setPriority("high");
            notificationDTO.setTo(subscriberFcmTokenRepoIface.findBysubscriberUid(powerOfAttorney.getPrincipleSuid()).getFcmToken());
            HttpHeaders notifyHeaders = new HttpHeaders();
            notifyHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestNotify = new HttpEntity<>(notificationDTO, notifyHeaders);
            restTemplate.exchange(notificationUrl, HttpMethod.POST, requestNotify, Object.class);
            return AppUtilPOA.createApiResponse(true, "Signing Initiated Successful", null);
        } catch (Exception e) {
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false, "Something went wrong", null);
        }
    }

//    @Scheduled(cron = "*/10 * * * * *")
//    public void checkStatus() throws JsonProcessingException {
//        List<PoaTemporary> tempDocStatusList = temporaryPoaRepo.fetchNotCompletedDocs("Completed");
//       // System.out.println("listttttt"+tempDocStatusList);
//
//      //  System.out.println("repooooooooooooo:"+tempDocStatus);
//
//        for (PoaTemporary tempDocStatus : tempDocStatusList) {
//
//          //  PoaTemporary powerOfAttorney = temporaryPoaRepo.fetchById(tempDocStatus.getPoaId());
//
//            PoaTemporary powerOfAttorney = temporaryPoaRepo.findById(tempDocStatus.getId())
//                    .orElse(null);
//
//            //System.out.println("responseee::::::::::"+powerOfAttorney);
//
//            String url = accessApi;
//            GetAccessTokenDto getAccessTokenDto = new GetAccessTokenDto();
//            getAccessTokenDto.setEmail(powerOfAttorney.getPrincipleEmail());
//            getAccessTokenDto.setName(powerOfAttorney.getPrincipleName());
//            getAccessTokenDto.setSuid(powerOfAttorney.getPrincipleSuid());
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setBasicAuth("QT95Ix3sjFtY9jf6SnxnelXaqwUdJAXWoTI9RBEhVfk3oJdi", "sLHmG9J26omJmLfSzqWu8qByGi82nyaSIhPgs63gEU7h6D9TPDL3eYygHwPxjBmy");
//            HttpEntity<Object> requestEntity = new HttpEntity<>(getAccessTokenDto, headers);
//            ResponseEntity<ApiResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
//
//            String res = objectMapper.writeValueAsString(response.getBody().getResult());
//            AccessTokenResponseDto accessTokenResponseDto = objectMapper.readValue(res, AccessTokenResponseDto.class);
//
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//            httpHeaders.set("x-access-token", accessTokenResponseDto.getApiToken());
//            HttpEntity<Object> requestEntityNew = new HttpEntity<>(httpHeaders);
//            ResponseEntity<ApiResponse> responseNew = restTemplate.exchange(docStatus + tempDocStatus.getDocId(), HttpMethod.GET, requestEntityNew, ApiResponse.class);
//            System.out.println(":respobseeee"+responseNew);
//            String resNew = objectMapper.writeValueAsString(responseNew.getBody().getResult());
//            DocStatusDto mappedResult = objectMapper.readValue(resNew, DocStatusDto.class);
//            System.out.println("mapppedresulu::::::::::"+mappedResult);
//            List<Recipients> recepientsList = mappedResult.getRecepientsList();
//            if (mappedResult.getStatus().equals("Completed")) {
//
//                tempDocStatus.setStatus("Completed");
//                temporaryPoaRepo.save(tempDocStatus);
//                HttpHeaders edmsHeaders = new HttpHeaders();
//                edmsHeaders.setContentType(MediaType.APPLICATION_JSON);
//                edmsHeaders.set("x-access-token", accessTokenResponseDto.getApiToken());
//                HttpEntity<Object> edmsEntity = new HttpEntity<>(edmsHeaders);
//                ResponseEntity<byte[]> responseEntity = restTemplate.exchange(edmsUrl + mappedResult.getEdmsId(), HttpMethod.GET, edmsEntity, byte[].class);
//                String base64Doc = AppUtil.getBase64FromByteArr(responseEntity.getBody());
//
//                ApiResponse res1 = saveInMainTable(tempDocStatus,base64Doc);
//                if(!res1.isSuccess()){
//
//                }
//
//
//            } else if (recepientsList.get(0).getStatus().toLowerCase().equals("signed") && recepientsList.get(1).getStatus().toLowerCase().equals("need to sign")) {
//                PoaSubscriber subscriber = subscriberRepo.fetchSubscriberByEmailId(recepientsList.get(1).getEmail());
//
//                String agentSuid = recepientsList.get(1).getSuid();
//                if (!tempDocStatus.isAgent()) {
//                    PoaSubscriberFcmToken subscriberFcmToken = subscriberFcmTokenRepoIface.findBysubscriberUid(agentSuid);
//                    NotificationDTO notificationDTO = new NotificationDTO();
//                    NotificationContextDTO notificationContextDTO = new NotificationContextDTO();
//                    NotificationDataDTO notificationDataDTO = new NotificationDataDTO();
//                    notificationDataDTO.setBody("Principal has completed signing. Please sign the POA document");
//                    notificationDataDTO.setTitle("Hi " + subscriber.getFullName());
//                    notificationDataDTO.setNotificationContext(notificationContextDTO);
//                    Map<String, String> contextDto = new HashMap<>();
//                    contextDto.put("glitrchtip", "raised");
//                    notificationContextDTO.setPREF_GLITCHTIP(contextDto);
//                    notificationDTO.setData(notificationDataDTO);
//                    notificationDTO.setPriority("high");
//                    notificationDTO.setTo(subscriberFcmToken.getFcmToken());
//                    HttpHeaders notifyHeaders = new HttpHeaders();
//                    notifyHeaders.setContentType(MediaType.APPLICATION_JSON);
//                    HttpEntity<Object> requestNotify = new HttpEntity<>(notificationDTO, notifyHeaders);
//                    restTemplate.exchange(notificationUrl, HttpMethod.POST, requestNotify, Object.class);
//                    tempDocStatus.setAgent(true);
//                    temporaryPoaRepo.save(tempDocStatus);
//                }
//
//            } else if (recepientsList.get(1).getStatus().toLowerCase().equals("signed") && recepientsList.get(2).getStatus().toLowerCase().equals("need to sign")) {
//                PoaSubscriber notary = subscriberRepo.fetchSubscriberByEmailId(recepientsList.get(2).getEmail());
//                String notarySuid = recepientsList.get(2).getSuid();
//                if (!tempDocStatus.isNotary()) {
//                    PoaSubscriberFcmToken subscriberFcmToken = subscriberFcmTokenRepoIface.findBysubscriberUid(notarySuid);
//                    NotificationDTO notificationDTO = new NotificationDTO();
//                    NotificationContextDTO notificationContextDTO = new NotificationContextDTO();
//                    NotificationDataDTO notificationDataDTO = new NotificationDataDTO();
//                    notificationDataDTO.setBody("POA Document is ready. Please sign and eseal on behalf of "+orgName);
//                    notificationDataDTO.setTitle("Hi " + notary.getFullName());
//                    notificationDataDTO.setNotificationContext(notificationContextDTO);
//                    Map<String, String> contextDto = new HashMap<>();
//                    contextDto.put("glitrchtip", "raised");
//                    notificationContextDTO.setPREF_GLITCHTIP(contextDto);
//                    notificationDTO.setData(notificationDataDTO);
//                    notificationDTO.setPriority("high");
//                    notificationDTO.setTo(subscriberFcmToken.getFcmToken());
//                    HttpHeaders notifyHeaders = new HttpHeaders();
//                    notifyHeaders.setContentType(MediaType.APPLICATION_JSON);
//                    HttpEntity<Object> requestNotify = new HttpEntity<>(notificationDTO, notifyHeaders);
//                    restTemplate.exchange(notificationUrl, HttpMethod.POST, requestNotify, Object.class);
//                    tempDocStatus.setNotary(true);
//                    temporaryPoaRepo.save(tempDocStatus);
//                }
//            }
//
//        }
//
//
//
//    }

    public ApiResponsePOA saveInMainTable(PoaTemporary temporaryPoa, String poaSignedDoc){

       try {

           PowerOfAttorney powerOfAttorney = new PowerOfAttorney();
           powerOfAttorney.setPrincipleEmail(temporaryPoa.getPrincipleEmail());
           powerOfAttorney.setPrincipleIdDocNumber(temporaryPoa.getPrincipleIdDocNumber());
           powerOfAttorney.setPrincipleName(temporaryPoa.getPrincipleName());
           powerOfAttorney.setPrincipleSuid(temporaryPoa.getPrincipleSuid());

           powerOfAttorney.setAgentEmail(temporaryPoa.getAgentEmail());
           powerOfAttorney.setAgentIdDocNumber(temporaryPoa.getAgentIdDocNumber());
           powerOfAttorney.setAgentName(temporaryPoa.getAgentName());
           powerOfAttorney.setAgentSuid(temporaryPoa.getAgentSuid());


           powerOfAttorney.setNotaryEmail(temporaryPoa.getNotaryEmail());
           powerOfAttorney.setNotaryIdDocNumber(temporaryPoa.getNotaryIdDocNumber());
           powerOfAttorney.setNotaryName(temporaryPoa.getNotaryName());
           powerOfAttorney.setNotarySuid(temporaryPoa.getNotarySuid());

           powerOfAttorney.setPoaRequestForm(temporaryPoa.getPoaRequestForm());
           powerOfAttorney.setPoaDocSigned(poaSignedDoc);

           powerOfAttorney.setEffectiveFrom(temporaryPoa.getEffectiveDate());
           powerOfAttorney.setScope(temporaryPoa.getScope());

           powerOfAttorney.setCreatedOn(AppUtilPOA.getDate());
           powerOfAttorney.setUpdatedOn(AppUtilPOA.getDate());

           powerOfAttorneyRepo.save(powerOfAttorney);

           PoaSubscriberFcmToken subscriberFcmToken = subscriberFcmTokenRepoIface.findBysubscriberUid(temporaryPoa.getPrincipleSuid());
           NotificationDTOPOA notificationDTO = new NotificationDTOPOA();
           NotificationContextDTOPOA notificationContextDTO = new NotificationContextDTOPOA();
           NotificationDataDTOPOA notificationDataDTO = new NotificationDataDTOPOA();
           notificationDataDTO.setBody("POA has been approved. Provision your poa credentials request document");
           notificationDataDTO.setTitle("Hi " + temporaryPoa.getPrincipleName());
           notificationDataDTO.setNotificationContext(notificationContextDTO);
           Map<String, String> contextDto = new HashMap<>();
           contextDto.put("glitrchtip", "raised");
           notificationContextDTO.setPREF_GLITCHTIP(contextDto);
           notificationDTO.setData(notificationDataDTO);
           notificationDTO.setPriority("high");
           notificationDTO.setTo(subscriberFcmToken.getFcmToken());
           HttpHeaders notifyHeaders = new HttpHeaders();
           notifyHeaders.setContentType(MediaType.APPLICATION_JSON);
           HttpEntity<Object> requestNotify = new HttpEntity<>(notificationDTO, notifyHeaders);
           restTemplate.exchange(notificationUrl, HttpMethod.POST, requestNotify, Object.class);


           return AppUtilPOA.createApiResponse(true,"saved successfully",null);

       }catch (Exception e){
           e.printStackTrace();
           return AppUtilPOA.createApiResponse(false,"Something went wrong",null);
       }
    }


    @Override
    public ApiResponsePOA getStatus(int id) {
        try{
            PoaTemporary temporaryPoa = temporaryPoaRepo.fetchById(id);
            if(temporaryPoa.getStatus().equals("Completed")){
                return AppUtilPOA.createApiResponse(true,"Notarization done",null);

            }
            else{
                return AppUtilPOA.createApiResponse(false,"Not Completed",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false,"Something went wrong",null);
        }
    }


    @Override
    public ApiResponsePOA getNotaryAndScopeInformation() {
        try {
            List<PoaOrgContactsEmail> orgContactsEmailList = orgContactEmailRepo.fetchAllEmployees(orgId);
            List<NotaryDto> notaryDtoList = new ArrayList<>();
            for (PoaOrgContactsEmail orgContactsEmail : orgContactsEmailList) {
                NotaryDto notaryDto = new NotaryDto();
                notaryDto.setNotaryEmployeeEmail(orgContactsEmail.getEmployeeEmail());
                notaryDto.setNotarySuid(orgContactsEmail.getSubscriberUid());
                notaryDto.setNotaryUaeIdEmail(orgContactsEmail.getUgpassEmail());

                notaryDtoList.add(notaryDto);
            }
            NotaryResponseDto notaryResponseDto = new NotaryResponseDto();
            notaryResponseDto.setNotaryDtoList(notaryDtoList);
            notaryResponseDto.setScope(scopeList);
            return AppUtilPOA.createApiResponse(true, "Fetched Notary Response Successfully", notaryResponseDto);

        } catch (Exception e) {
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false, "Something went wrong", null);
        }
    }


    @Override
    public ApiResponsePOA transferPoa(String agentEmail) {
        try {
            String url = createRequestUrl;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            POACredentialRequests poaCredentialRequests = new POACredentialRequests();
            poaCredentialRequests.setType("POACredentialRequest");
            poaCredentialRequests.setScope("POACredentialRequest");
            poaCredentialRequests.setClientId("");
            SelectedClaims selectedClaims = new SelectedClaims();
            selectedClaims.setDocument(Arrays.asList(
                    "principleEmail", "principleName", "principleIdDocNumber",
                    "principleSuid", "agentEmail", "agentName", "agentSuid",
                    "agentIdDocNumber", "notarySuid", "notaryIdDocNumber",
                    "notaryEmail", "poaDocSigned", "scope", "notaryName", "delegationUpto","photo"
            ));
            poaCredentialRequests.setSelectedClaims(selectedClaims);
            HttpEntity<Object> requestEntity = new HttpEntity<>(poaCredentialRequests, headers);

            ResponseEntity<ApiResponseW3c> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponseW3c.class);
            List<String> parts = Arrays.asList(response.getBody().getResult().toString().split("/"));
            String transactionId = parts.get(parts.size() - 1);
            PoaSubscriber subscriber = subscriberRepo.fetchSubscriberByEmailId(agentEmail);
            Thread thread = new Thread(() -> {

                try {
                    this.verifyCredentials(transactionId, agentEmail, subscriber.getSubscriberUid(), subscriber.getIdDocNumber(), subscriber.getFullName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

            });
            thread.start();
            return AppUtilPOA.createApiResponse(true, "Transfer in progress", response.getBody().getResult().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false, "Something went wrong", null);
        }

    }


    public void verifyCredentials(String transactionId, String email, String suid, String idDoc, String name) throws InterruptedException, JsonProcessingException {
        String url = fetchVerifyUrl + "/" + transactionId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<ApiResponseW3c> response;
        ResponseEntity<String>response;
        boolean success = false;
        do {
//            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponseW3c.class);

            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);


            String jsonResponse = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();

            ApiResponseW3c apiResponse = objectMapper.readValue(jsonResponse, ApiResponseW3c.class);

            if (apiResponse.isSuccess()) {

                success = true;


                String res = objectMapper.writeValueAsString(apiResponse.getResult());

                W3cResult w3cResult = objectMapper.readValue(res, W3cResult.class);

                PoaCredentials poaCredentialsDb = poaCredentialsRepo.fetchByEmail(email);
                if (poaCredentialsDb != null) {

                    PoaVisitorCompleteDetails visitorCompleteDetails = visitorCompleteDetailsRepo.fetchDetails(suid);


                    HttpHeaders head = new HttpHeaders();
                    HttpEntity<Object> request = new HttpEntity<>(head);
                    ResponseEntity<byte[]> resp = restTemplate.exchange(visitorCompleteDetails.getSelfieUri(), HttpMethod.GET, request, byte[].class);
                    if(resp.getStatusCodeValue()==200){

                    }


                    String base64 = AppUtilPOA.getBase64FromByteArr(resp.getBody());
                    poaCredentialsDb.setAgentPhoto(base64);
                    poaCredentialsDb.setAgentIdDocNumber(idDoc);
                    poaCredentialsDb.setAgentName(name);
                    poaCredentialsDb.setAgentSuid(suid);
                    poaCredentialsDb.setPoaCredential(w3cResult.getVpToken());
                    poaCredentialsRepo.save(poaCredentialsDb);
                } else {
                    PoaCredentials poaCredentials = new PoaCredentials();

                    PoaVisitorCompleteDetails visitorCompleteDetails = visitorCompleteDetailsRepo.fetchDetails(suid);
                    HttpHeaders head = new HttpHeaders();
                    HttpEntity<Object> request1 = new HttpEntity<>(head);
                    ResponseEntity<byte[]> resp = restTemplate.exchange(visitorCompleteDetails.getSelfieUri(), HttpMethod.GET, request1, byte[].class);
                    if(resp.getStatusCodeValue()==200){

                    }


                    String base64 = AppUtilPOA.getBase64FromByteArr(resp.getBody());



                    poaCredentials.setAgentPhoto(base64);

                    poaCredentials.setAgentSuid(suid);
                    poaCredentials.setPoaCredential(w3cResult.getVpToken());
                    poaCredentials.setAgentEmail(email);
                    poaCredentials.setAgentIdDocNumber(idDoc);
                    poaCredentials.setAgentName(name);
                    poaCredentials.setAgentSuid(suid);
                    poaCredentials.setCreatedOn(AppUtilPOA.getDate());
                    poaCredentials.setUpdatedOn(AppUtilPOA.getDate());
                    poaCredentials.setStatus("APPROVED");
                    poaCredentialsRepo.save(poaCredentials);
                }

                PoaSubscriberFcmToken subscriberFcmToken = subscriberFcmTokenRepoIface.findBysubscriberUid(suid);

                NotificationDTOPOA notificationDTO = new NotificationDTOPOA();
                NotificationContextDTOPOA notificationContextDTO = new NotificationContextDTOPOA();
                NotificationDataDTOPOA notificationDataDTO = new NotificationDataDTOPOA();
                notificationDataDTO.setBody("Provision POACredential");
                notificationDataDTO.setTitle("Hi " + name);
                notificationDataDTO.setNotificationContext(notificationContextDTO);
                Map<String, String> contextDto = new HashMap<>();
                contextDto.put("glitrchtip", "raised");
                notificationContextDTO.setPREF_GLITCHTIP(contextDto);
                notificationDTO.setData(notificationDataDTO);
                notificationDTO.setPriority("high");
                notificationDTO.setTo(subscriberFcmToken.getFcmToken());
                HttpHeaders notifyHeaders = new HttpHeaders();
                notifyHeaders.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> requestNotify = new HttpEntity<>(notificationDTO, notifyHeaders);
                restTemplate.exchange(notificationUrl, HttpMethod.POST, requestNotify, Object.class);
            } else {

                Thread.sleep(2000);
            }
        } while (!success);
    }


    @Transactional
    public ApiResponsePOA resetPoaData() {
        try {
            logger.info("Starting POA data reset process...");

            String result = poaRepository.deleteFromPoaTables();

            logger.info("POA data reset completed successfully: {}", result);

            return AppUtilPOA.createApiResponse(true, result, null);

        } catch (Exception e) {
            logger.error("Error during POA data reset: {}", e.getMessage(), e);
            return AppUtilPOA.createApiResponse(false, "Error occurred during data reset", e.getMessage());
        }
    }


    @Override
    public ApiResponsePOA getAllPoaCredentialRequests(String principalDocumentNumber) {
        try {

            PowerOfAttorney powerOfAttorney = powerOfAttorneyRepo.fetchApprovedPoaRequests(principalDocumentNumber);

            if(powerOfAttorney == null){
                return AppUtilPOA.createApiResponse(false, "No user found",null);
            }


            PoaCredentialsRequestDto dto = new PoaCredentialsRequestDto();

            dto.setPrincipleEmail(powerOfAttorney.getPrincipleEmail());
            dto.setPrincipleName(powerOfAttorney.getPrincipleName());
            dto.setAgentEmail(powerOfAttorney.getAgentEmail());
            dto.setAgentName(powerOfAttorney.getAgentName());
//            dto.setJson(powerOfAttorney.getJson());
            dto.setDelegationUpto(powerOfAttorney.getEffectiveFrom());
            dto.setStatus(powerOfAttorney.getStatus());
            dto.setPoaRequestForm(powerOfAttorney.getPoaRequestForm());
            dto.setPrincipleIdDocNumber(powerOfAttorney.getPrincipleIdDocNumber());
            dto.setPrincipleSuid(powerOfAttorney.getPrincipleSuid());
            dto.setAgentIdDocNumber(powerOfAttorney.getAgentIdDocNumber());
            dto.setAgentSuid(powerOfAttorney.getAgentSuid());
            dto.setNotaryName(powerOfAttorney.getNotaryName());
            dto.setNotaryIdDocNumber(powerOfAttorney.getNotaryIdDocNumber());
            dto.setNotaryEmail(powerOfAttorney.getNotaryEmail());
            dto.setNotarySuid(powerOfAttorney.getNotarySuid());
            dto.setScope(powerOfAttorney.getScope());
            dto.setPoaDocSigned(powerOfAttorney.getPoaDocSigned());
            dto.setCreatedOn(powerOfAttorney.getCreatedOn());
            dto.setUpdatedOn(powerOfAttorney.getUpdatedOn());
            dto.setPhoto(powerOfAttorney.getPrincipalPhoto());
            return AppUtilPOA.createApiResponse(true, "fetched successfully", dto);
        } catch (Exception e) {
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false, "Something went wrong", null);
        }
    }


    @Override
    public ApiResponsePOA getAllPoaCredentials(String agentDocumentNumber) {
        try {
            PoaCredentials poaCredentials = poaCredentialsRepo.fetchByDocNumber(agentDocumentNumber);

            if(poaCredentials ==null){
                return AppUtilPOA.createApiResponse(false, "No details found", null);
            }

            PoaCredentialsDto dto = new PoaCredentialsDto();

            dto.setAgentSuid(poaCredentials.getAgentSuid());
            dto.setAgentName(poaCredentials.getAgentName());
            dto.setAgentIdDocNumber(poaCredentials.getAgentIdDocNumber());
            dto.setPoaCredential(poaCredentials.getPoaCredential());
            dto.setAgentEmail(poaCredentials.getAgentEmail());
            dto.setCreatedOn(poaCredentials.getCreatedOn());
            dto.setUpdatedOn(poaCredentials.getUpdatedOn());
            dto.setStatus(poaCredentials.getStatus());
            dto.setPhoto(poaCredentials.getAgentPhoto());

            return AppUtilPOA.createApiResponse(true, "fetched successfully", dto);
        } catch (Exception e) {
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false, "Something went wrong", null);
        }
    }


    @Override
    public String modifypoa(PowerOfAttorneyDto powerOfAttorneyDto) throws com.itextpdf.io.IOException, IOException {

        ClassPathResource resource = new ClassPathResource("poa.pdf");
        byte[] existingPdfBytes = Files.readAllBytes(Paths.get(resource.getURI()));


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        PdfReader pdfReader = new PdfReader(new ByteArrayInputStream(existingPdfBytes));
        PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfReader, pdfWriter);

        Field[] fields = powerOfAttorneyDto.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {


                int pageNumber = 1;
                PdfCanvas pdfCanvas = new PdfCanvas(pdfDocument.getPage(pageNumber));


                JsonNode jsonNode = jsonconvertion(poaCoordinates);

                String input= jsonNode.get(field.getName()).toString();


                String[] parts = input.split(",");


                float x = Float.parseFloat(parts[0].replace("\"", ""));
                float y = Float.parseFloat(parts[1].replace("\"", ""));



                Rectangle rectangle = new Rectangle(x, y, 1000, 50); // Adjust width and height as needed
                Canvas canvas = new Canvas(pdfCanvas, rectangle);

                Text pdfText = new Text(((String) field.get(powerOfAttorneyDto)).toUpperCase())
                        .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                        .setFontSize(11)
                        .setFontColor(ColorConstants.BLACK);

                float maxWidth = 500;

                Paragraph paragraph = new Paragraph().add(pdfText)
                        .setWidth(maxWidth)
                        .setMultipliedLeading(1);



                canvas.add(paragraph);



            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }


        try {
            int pageNumber = 1;
            PdfCanvas pdfCanvas = new PdfCanvas(pdfDocument.getPage(pageNumber));

            JsonNode jsonNode = jsonconvertion(poaCoordinates);


            String reusedFieldCoordinates = "120,93";
            String[] parts = reusedFieldCoordinates.split(",");

            float x = Float.parseFloat(parts[0]);
            float y = Float.parseFloat(parts[1]);

            Rectangle rectangle = new Rectangle(x, y, 1000, 50); // Adjust as needed
            Canvas canvas = new Canvas(pdfCanvas, rectangle);

            // Fetch the value of `agentName` from DTO
            Field agentNameField = powerOfAttorneyDto.getClass().getDeclaredField("agentName");
            agentNameField.setAccessible(true);
            String agentNameValue = (String) agentNameField.get(powerOfAttorneyDto);

            Text pdfText = new Text(agentNameValue.toUpperCase())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(11)
                    .setFontColor(ColorConstants.BLACK);

            Paragraph paragraph = new Paragraph().add(pdfText)
                    .setWidth(200)
                    .setMultipliedLeading(1);

            canvas.add(paragraph);

        } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
            e.printStackTrace();
            return null;
        }






        pdfDocument.close();
        byte[] modifiedPdfBytes = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(modifiedPdfBytes);

    }



    public JsonNode jsonconvertion(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    @Override
    public ApiResponsePOA savePoa(SavePoaDto savePoaDto) {
        try {
            System.out.println("response:::::::"+savePoaDto);
            if (savePoaDto.getPrincipleEmail() == null || savePoaDto.getPrincipleEmail().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Principal Email cannot be null or empty", null);
            }
            if (savePoaDto.getAgentEmail() == null || savePoaDto.getAgentEmail().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Agent Email cannot be null or empty", null);
            }
            if (savePoaDto.getNotaryEmail() == null || savePoaDto.getNotaryEmail().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Notary Email cannot be null or empty", null);
            }
            if (savePoaDto.getPrincipleName() == null || savePoaDto.getPrincipleName().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Principal Name cannot be null or empty", null);
            }
            if (savePoaDto.getAgentName() == null || savePoaDto.getAgentName().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Agent Name cannot be null or empty", null);
            }
            if (savePoaDto.getPrincipleIdDocNumber() == null || savePoaDto.getPrincipleIdDocNumber().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Principal Id Document Number cannot be null or empty", null);
            }
            if (savePoaDto.getAgentIdDocNumber() == null || savePoaDto.getAgentIdDocNumber().isEmpty()) {
                return AppUtilPOA.createApiResponse(false, "Agent Id Document Number cannot be null or empty", null);
            }

//            Subscriber principal = subscriberRepo.fetchSubscriberByEmailId(powerOfAttorney.getPrincipleEmail());
//            Subscriber agent = subscriberRepo.fetchSubscriberByEmailId(powerOfAttorney.getAgentEmail());
//            Subscriber notary = subscriberRepo.fetchSubscriberByEmailId(powerOfAttorney.getNotaryEmail());

            PoaSubscriber principal = subscriberRepo.fetchSubscriberByIDDocNumber(savePoaDto.getPrincipleIdDocNumber());
            PoaSubscriber agent = subscriberRepo.fetchSubscriberByIDDocNumber(savePoaDto.getAgentIdDocNumber());
//            PoaSubscriber notary = subscriberRepo.fetchSubscriberByIDDocNumber(powerOfAttorney.getNotaryIdDocNumber());
            PoaSubscriber notary = subscriberRepo.fetchSubscriberByEmailId(savePoaDto.getNotaryEmail());
            if (principal == null) {
                return AppUtilPOA.createApiResponse(false, "Principal Not Found", null);
            }
            if (agent == null) {
                return AppUtilPOA.createApiResponse(false, "Agent Not Found", null);
            }
            if (notary == null) {
                return AppUtilPOA.createApiResponse(false, "Notary Not Found", null);
            }
            if (!principal.getIdDocNumber().equals(savePoaDto.getPrincipleIdDocNumber())) {
                return AppUtilPOA.createApiResponse(false, "Principal Id Document does not matched", null);

            }
            if (!agent.getIdDocNumber().trim().equals(savePoaDto.getAgentIdDocNumber().trim())) {
                return AppUtilPOA.createApiResponse(false, "Agent Id Document does not matched", null);
            }

            if (savePoaDto.getPrincipleEmail().equals(savePoaDto.getAgentEmail())) {
                return AppUtilPOA.createApiResponse(false, "Both Principal And Agent cannot be same", null);
            } else if (savePoaDto.getPrincipleName().equals(savePoaDto.getNotaryEmail())) {
                return AppUtilPOA.createApiResponse(false, "Both Principal And Notary cannot be same", null);

            } else if (savePoaDto.getAgentEmail().equals(savePoaDto.getNotaryEmail())) {
                return AppUtilPOA.createApiResponse(false, "Both Agent And Notary cannot be same", null);
            }


            PowerOfAttorney powerOfAttorney = new PowerOfAttorney();
            powerOfAttorney.setPrincipleEmail(powerOfAttorney.getPrincipleEmail());
            powerOfAttorney.setPrincipleIdDocNumber(savePoaDto.getPrincipleIdDocNumber());
            powerOfAttorney.setPrincipleName(principal.getFullName());
            powerOfAttorney.setPrincipleSuid(principal.getSubscriberUid());



            powerOfAttorney.setAgentEmail(savePoaDto.getAgentEmail());
            powerOfAttorney.setAgentIdDocNumber(savePoaDto.getAgentIdDocNumber());
            powerOfAttorney.setAgentName(agent.getFullName());
            powerOfAttorney.setAgentSuid(agent.getSubscriberUid());


            powerOfAttorney.setNotaryEmail(savePoaDto.getNotaryEmail());
            powerOfAttorney.setNotaryIdDocNumber(savePoaDto.getNotaryIdDocNumber());
            powerOfAttorney.setNotaryName(notary.getFullName());
            powerOfAttorney.setNotarySuid(notary.getSubscriberUid());

//            powerOfAttorney.setPoaRequestForm(savePoaDto.getPoaRequestForm());
//            powerOfAttorney.setPoaDocSigned(poaSignedDoc);

            powerOfAttorney.setEffectiveFrom(savePoaDto.getEffectiveFrom());

            powerOfAttorney.setScope(savePoaDto.getScope());

            powerOfAttorney.setCreatedOn(AppUtilPOA.getDate());
            powerOfAttorney.setUpdatedOn(AppUtilPOA.getDate());

            powerOfAttorneyRepo.save(powerOfAttorney);

            PoaSubscriberFcmToken subscriberFcmToken = subscriberFcmTokenRepoIface.findBysubscriberUid(principal.getSubscriberUid());
            NotificationDTOPOA notificationDTO = new NotificationDTOPOA();
            NotificationContextDTOPOA notificationContextDTO = new NotificationContextDTOPOA();
            NotificationDataDTOPOA notificationDataDTO = new NotificationDataDTOPOA();
            notificationDataDTO.setBody("POA has been approved. Provision your poa credentials request document");
            notificationDataDTO.setTitle("Hi " + principal.getFullName());
            notificationDataDTO.setNotificationContext(notificationContextDTO);
            Map<String, String> contextDto = new HashMap<>();
            contextDto.put("glitrchtip", "raised");
            notificationContextDTO.setPREF_GLITCHTIP(contextDto);
            notificationDTO.setData(notificationDataDTO);
            notificationDTO.setPriority("high");
            notificationDTO.setTo(subscriberFcmToken.getFcmToken());
            HttpHeaders notifyHeaders = new HttpHeaders();
            notifyHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestNotify = new HttpEntity<>(notificationDTO, notifyHeaders);
            restTemplate.exchange(notificationUrl, HttpMethod.POST, requestNotify, Object.class);
            return AppUtilPOA.createApiResponse(true, "Request Submitted successfully", null);

        } catch (Exception e) {
            e.printStackTrace();
            return AppUtilPOA.createApiResponse(false,"something went wrong ",null);

        }
    }

}

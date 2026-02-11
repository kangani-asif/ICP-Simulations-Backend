package com.dtt.simulations.service.Impl;

import com.dtt.simulations.dto.LogModelDTO;
import com.dtt.simulations.enums.LogMessageType;
import com.dtt.simulations.enums.TransactionType;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.LogModelServiceIface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ug.daes.DAESService;
import ug.daes.Result;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;

import static com.dtt.simulations.enums.ServiceNames.HOTEL;
//
//@Service
//public class LogModelServiceImpl implements LogModelServiceIface {
//
//	private static Logger logger = LoggerFactory.getLogger(LogModelServiceImpl.class);
//
//	/** The Constant CLASS. */
//	final static String CLASS = "LogModelServiceImpl";
//
//
//
//	@Autowired
//	MessageSource messageSource;
//
//
//	RestTemplate restTemplate = new RestTemplate();
//
//	@Value("${central.log}")
//	public String centralLog;
//
//
//	@Override
//	public void setLogModelDTO(Boolean response, String Identifier, String geoLocation, String serviceName,
//			String correlationID, String message, Date startTime, Date endTime, String otpStatus)
//			throws ParseException {
//		LogModelDTO logModel = new LogModelDTO();
//		logModel.setIdentifier(Identifier);
//		logModel.setCorrelationID(null);
//		logModel.setTransactionID(null);
//		logModel.setTimestamp(null);
//		logModel.setStartTime(AppUtil.getTimeStampString(startTime));
//		logModel.setEndTime(AppUtil.getTimeStampString(endTime));
//		logModel.setServiceName(serviceName);
//		logModel.setLogMessage(message);
//		logModel.setTransactionType(TransactionType.BUSINESS.toString());
//		logModel.setGeoLocation(geoLocation);
//		logModel.seteSealUsed(false);
//		logModel.setSignatureType(null);
//		logModel.setCallStack(otpStatus);
//		if (response) {
//			logModel.setLogMessageType(LogMessageType.SUCCESS.toString());
//		} else {
//			logModel.setLogMessageType(LogMessageType.FAILURE.toString());
//		}
//		logModel.setChecksum(null);
//
//		try {
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			String json = objectMapper.writeValueAsString(logModel);
//			Result checksumResult = DAESService.addChecksumToTransaction(json);
//			String push = new String(checksumResult.getResponse());
//			LogModelDTO log = objectMapper.readValue(push, LogModelDTO.class);
//
//			String url = centralLog;
//			System.out.println("Sgnature Service Url >> " + url);
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			HttpEntity<Object> requestEntity = new HttpEntity<>(log, headers);
//			ResponseEntity<Void> res = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
//			if (res.getStatusCodeValue() == 200) {
//				System.out.println("Response Body" + res.getBody());
//				System.out.println("Log sent Success");
//			}
//
////			mqSender.send(log);
//			logger.info(CLASS + " setLogModel log {}",logModel );
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(CLASS + " setLogModel Exception  {]", e.getMessage() );
//		}
//	}
//
//
//}

@Service
public class LogModelServiceImpl implements LogModelServiceIface {

	private static final Logger logger = LoggerFactory.getLogger(LogModelServiceImpl.class);
	private static final String CLASS = "LogModelServiceImpl";

	@Autowired
	private KafkaTemplate<String, LogModelDTO> kafkaTemplate;


	@Value("${kafka.topic.log}")
	private String logTopic;

	@Override
	public void setLogModelDTO(Boolean response, String Identifier, String geoLocation, String serviceName,
							   String correlationID, String message, Date startTime, Date endTime,
							   String otpStatus) throws ParseException {

		try {
			LogModelDTO logModel = new LogModelDTO();
			logModel.setIdentifier(Identifier);
			logModel.setCorrelationID(correlationID);
			logModel.setTransactionID(null);
			logModel.setTimestamp(AppUtil.getTimeStampString(new Date()));
			logModel.setStartTime(AppUtil.getTimeStampString(startTime));
			logModel.setEndTime(AppUtil.getTimeStampString(endTime));
			logModel.setServiceName(String.valueOf(serviceName));
			logModel.setLogMessage(message);
			logModel.setTransactionType(TransactionType.BUSINESS.toString());
			logModel.setGeoLocation(geoLocation);
			logModel.seteSealUsed(false);
			logModel.setSignatureType(null);
			logModel.setCallStack(otpStatus);
			logModel.setLogMessageType(response ?
					LogMessageType.SUCCESS.toString() :
					LogMessageType.FAILURE.toString());
			logModel.setChecksum(null);

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(logModel);

			Result checksumResult = DAESService.addChecksumToTransaction(json);
			logModel.setChecksum(new String(checksumResult.getResponse()));
//			String push = new String(checksumResult.getResponse(), StandardCharsets.UTF_8);
//
//			LogModelDTO log = mapper.readValue(push, LogModelDTO.class);
			System.out.println("Printing Logs");
			logger.info("Log Model :  {}", logModel.toString());
			kafkaTemplate.send(logTopic, logModel);

			logger.info("{} - Log published to Kafka topic {}", CLASS, logTopic);

		} catch (Exception e) {
			logger.error("{} - Error sending log to Kafka", CLASS, e);
		}
	}
}


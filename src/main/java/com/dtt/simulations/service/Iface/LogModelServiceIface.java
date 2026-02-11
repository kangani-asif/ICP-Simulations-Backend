package com.dtt.simulations.service.Iface;

import java.text.ParseException;
import java.util.Date;

public interface LogModelServiceIface {
	

	
	
	public void setLogModelDTO(Boolean response,String Identifier, String geoLocation,
			String serviceName,String correlationID,String totalTime, 
			Date startTime,Date endTime,String otpStatus) throws ParseException;


}

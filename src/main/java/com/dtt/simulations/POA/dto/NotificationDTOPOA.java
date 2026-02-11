/**
 * 
 */
package com.dtt.simulations.POA.dto;

import java.io.Serializable;

/**
 * @author Raxit Dubey
 *
 */
public class NotificationDTOPOA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String to;

    private String priority;

    private NotificationDataDTOPOA data;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public NotificationDataDTOPOA getData() {
		return data;
	}

	public void setData(NotificationDataDTOPOA data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NotificationDTO [to=" + to + ", priority=" + priority + ", data=" + data + "]";
	}   

}

/**
 * 
 */
package com.dtt.simulations.POA.dto;

import java.io.Serializable;

/**
 * @author Raxit Dubey
 *
 */
public class NotificationDataDTOPOA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String body;

    private String title;

    private NotificationContextDTOPOA notificationContext;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public NotificationContextDTOPOA getNotificationContext() {
		return notificationContext;
	}

	public void setNotificationContext(NotificationContextDTOPOA notificationContext) {
		this.notificationContext = notificationContext;
	}

	@Override
	public String toString() {
		return "NotificationDataDTO [body=" + body + ", title=" + title + ", notificationContext=" + notificationContext
				+ "]";
	}
}

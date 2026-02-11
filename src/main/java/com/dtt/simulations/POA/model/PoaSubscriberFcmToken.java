package com.dtt.simulations.POA.model;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="subscriber_fcm_token")
@NamedQuery(name="PoaSubscriberFcmToken.findAll", query="SELECT s FROM PoaSubscriberFcmToken s")
public class PoaSubscriberFcmToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="fcm_token")
	private String fcmToken;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subscriber_fcm_token_id")
	private int subscriberFcmTokenId;

	@Column(name="subscriber_uid")
	private String subscriberUid;

	public PoaSubscriberFcmToken() {
	}

	public String getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getFcmToken() {
		return this.fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public int getSubscriberFcmTokenId() {
		return this.subscriberFcmTokenId;
	}

	public void setSubscriberFcmTokenId(int subscriberFcmTokenId) {
		this.subscriberFcmTokenId = subscriberFcmTokenId;
	}

	public String getSubscriberUid() {
		return this.subscriberUid;
	}

	public void setSubscriberUid(String subscriberUid) {
		this.subscriberUid = subscriberUid;
	}

	@Override
	public String toString() {
		return "SubscriberFcmToken [createdDate=" + createdDate + ", fcmToken=" + fcmToken + ", subscriberFcmTokenId="
				+ subscriberFcmTokenId + ", subscriberUid=" + subscriberUid + "]";
	}
	

}

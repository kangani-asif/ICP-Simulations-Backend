/**
 * 
 */
package com.dtt.simulations.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Raxit Dubey
 *
 */
public class NotificationContextDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private Map<String,String> pREF_PAYMENT_STATUS;

	private Map<String,String> pREF_TRANSACTION_ID;

	private Map<String, String>  pREF_ORG_LINK;
	
	private Map<String, String>  pREF_BENEFICIARY_LINK;

	private Map<String,String> pREF_IMMIGRATION_AUTHORITY;

	private Map<String,String> pREF_SIM_SIMULATOR;


	public Map<String, String> getpREF_ORG_LINK() {
		return pREF_ORG_LINK;
	}

	public Map<String,String> PREF_IMMIGRATION_AUTHORITY;

	public Map<String, String> getPREF_IMMIGRATION_AUTHORITY() {
		return PREF_IMMIGRATION_AUTHORITY;
	}

	public void setPREF_IMMIGRATION_AUTHORITY(Map<String, String> PREF_IMMIGRATION_AUTHORITY) {
		this.PREF_IMMIGRATION_AUTHORITY = PREF_IMMIGRATION_AUTHORITY;
	}

	public void setpREF_ORG_LINK(Map<String, String> pREF_ORG_LINK) {
		this.pREF_ORG_LINK = pREF_ORG_LINK;
	}

	public Map<String, String> getpREF_PAYMENT_STATUS() {
		return pREF_PAYMENT_STATUS;
	}

	public void setpREF_PAYMENT_STATUS(Map<String, String> pREF_PAYMENT_STATUS) {
		this.pREF_PAYMENT_STATUS = pREF_PAYMENT_STATUS;
	}

	public Map<String, String> getpREF_TRANSACTION_ID() {
		return pREF_TRANSACTION_ID;
	}

	public void setpREF_TRANSACTION_ID(Map<String, String> pREF_TRANSACTION_ID) {
		this.pREF_TRANSACTION_ID = pREF_TRANSACTION_ID;
	}
	
	

	public Map<String, String> getpREF_BENEFICIARY_LINK() {
		return pREF_BENEFICIARY_LINK;
	}

	public void setpREF_BENEFICIARY_LINK(Map<String, String> pREF_BENEFICIARY_LINK) {
		this.pREF_BENEFICIARY_LINK = pREF_BENEFICIARY_LINK;
	}

	public Map<String, String> getpREF_IMMIGRATION_AUTHORITY() {
		return pREF_IMMIGRATION_AUTHORITY;
	}

	public void setpREF_IMMIGRATION_AUTHORITY(Map<String, String> pREF_IMMIGRATION_AUTHORITY) {
		this.pREF_IMMIGRATION_AUTHORITY = pREF_IMMIGRATION_AUTHORITY;
	}

	public Map<String, String> getpREF_SIM_SIMULATOR() {
		return pREF_SIM_SIMULATOR;
	}

	public void setpREF_SIM_SIMULATOR(Map<String, String> pREF_SIM_SIMULATOR) {
		this.pREF_SIM_SIMULATOR = pREF_SIM_SIMULATOR;
	}

	@Override
	public String toString() {
		return "NotificationContextDTO{" +
				"pREF_PAYMENT_STATUS=" + pREF_PAYMENT_STATUS +
				", pREF_TRANSACTION_ID=" + pREF_TRANSACTION_ID +
				", pREF_ORG_LINK=" + pREF_ORG_LINK +
				", pREF_BENEFICIARY_LINK=" + pREF_BENEFICIARY_LINK +
				", pREF_IMMIGRATION_AUTHORITY=" + pREF_IMMIGRATION_AUTHORITY +
				", pREF_SIM_SIMULATOR=" + pREF_SIM_SIMULATOR +
				", PREF_IMMIGRATION_AUTHORITY=" + PREF_IMMIGRATION_AUTHORITY +
				'}';
	}
}

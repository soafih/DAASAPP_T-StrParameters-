/**
 * 
 */
package com.fox.services.db.common.exception;

import java.io.Serializable;


public class FOXDBServiceFaultBean implements Serializable {
	/**
     *
     */
    private static final long serialVersionUID = 8478217954649795114L;
	private String errorCode;
	private String errorDetails;

	public FOXDBServiceFaultBean() {

	}

	public FOXDBServiceFaultBean(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public FOXDBServiceFaultBean(String errorCode, String errorDetails) {
		this.errorCode = errorCode;
		this.errorDetails = errorDetails;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

}

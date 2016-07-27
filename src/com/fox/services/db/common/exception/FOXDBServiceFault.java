/**
 * 
 */
package com.fox.services.db.common.exception;

import javax.xml.ws.WebFault;


@WebFault(name = "EOMDBHubFault", faultBean = "EOMHubFault")
public class FOXDBServiceFault extends Exception {

	/**
     *
     */
    private static final long serialVersionUID = 1227280671877854041L;
	private FOXDBServiceFaultBean faultInfo;

	public FOXDBServiceFault() {
		super();
	}

	public FOXDBServiceFault(String errorCode, String message) {
		super(message);
		this.faultInfo = new FOXDBServiceFaultBean(errorCode, message);

	}

	public FOXDBServiceFault(int errorCode, String message) {
		super(message);
		this.faultInfo = new FOXDBServiceFaultBean(Integer.toString(errorCode),
				message);

	}

	public FOXDBServiceFault(String message) {
		super(message);
		this.faultInfo = new FOXDBServiceFaultBean(message);

	}

	public FOXDBServiceFaultBean getFaultInfo() {
		return faultInfo;
	}

	public void setFaultInfo(FOXDBServiceFaultBean faultInfo) {
		this.faultInfo = faultInfo;
	}

}

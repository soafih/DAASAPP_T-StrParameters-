/**
 * 
 */
package com.fox.services.db.constants;


public interface IFOXDBHubErrorCodes {
	String DEFAULT_ERROR_CODE = "ERR-01";
	String REQ_OBJ_EMPTY = "ERR-02";
	String DS_EMPTY = "ERR-03";
	String QUERY_EMPTY = "ERR-04";
	String DS_NAMING = "ERR-05";
	String CREATE_CONNECTION = "ERR-06";
        

	String REQ_OBJ_EMPTY_MSG = " Request Data should not be Empty.";
	String DS_EMPTY_MSG = "Data Source should not be Empty.";
	String QUERY_EMPTY_MSG = "Query should not be Empty.";
	String DEFAULT_ERROR_MSG = "Error while Communicating DB.";
}

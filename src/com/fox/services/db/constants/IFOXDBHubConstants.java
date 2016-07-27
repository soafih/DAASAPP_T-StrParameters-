/**
 * 
 */
package com.fox.services.db.constants;


public interface IFOXDBHubConstants {
	String SUCCESS = "Success";
	String ERROR = "Error";
	String DS_NAME = "jdbc/Vault";
	String EMPTY_STR = "";
	String EMPTY_QUERY_ERR_STR = "Input Query Should not Empty";

	// Start Webservice Constants
	String FOXDBHUBSERVICE_NAME = "FOXDBHubService";
	String FOXDBHUBSERVICE_PORTYPE = "FOXDBHubServicePortType";
	String FOXDBSERVICE_TARGET_NS = "http://fox.com/services/db";
	String FOXDBSERVICE_OPT_PROCESSQUERY_TARGET_NS = "http://xmlns.fox.com/schema/services/db";
	String INSERT_OPERATION = "processDML";
	String QUERY_OPERATION = "processSelect";
	String FOXDBSERVICE_REQ = "foxDBHubRequest";
	String FOXDBSERVICE_RESP = "foxDBHubResponse";
	String PROCESSBATCH_DML_OPERATION = "processBatchDML";
	String FOXDBSERVICE_BATCH_REQ = "foxDBHubBatchRequest";
	String FOXDBSERVICE_BATCH_RESP = "foxDBHubBatchResponse";
	String JOIN_QUERY_OPERATION = "processJoinSelect";
	String PROCESS_DML_PLSQL_BLOCK="processDMLPLSQLBlock";
        String EXECUTESQL = "ExecuteSQL";
        String EXECUTESQL_RESP="SQLResponse";
        String GENERATESCHEMA = "GenerateSchema";
        String GENERATESCHEMA_RESP="GenerateSchemaResponse";
    String GENERATESOURCESCHEMA = "GenerateSourceSchema";
    String GENERATESOURCESCHEMA_RESP = "GenerateSourceSchemaResponse";
}

package com.fox.services.daas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fox.services.db.common.exception.FOXDBServiceFault;
import com.fox.services.db.common.util.EOMHubUtil;
import com.fox.services.db.constants.IFOXDBHubErrorCodes;
import com.fox.services.db.core.CoreConnectionFactory;
import com.fox.services.rest.db.payload.FOXDBHubServiceRequest;

@Path("/")
public class FetchDetails {

	@GET
	// @Path("/FetchData")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response processSelect(@Context UriInfo info) throws JSONException {

		String result = "";
		int statusCode = 200;
		Statement stmt;
		try {
			String query = formQuery(info);
			// validateReq(foxRequest);
			Connection conn = CoreConnectionFactory.createConnectionWithDS("jdbc/testdb1");
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = stmt.executeQuery(query);

			JSONObject resultJson = processResultsAsJson(resultSet);
			stmt.close();
			conn.close();
			result = resultJson.toString();

		} catch (Exception ex) {
			ex.printStackTrace();
			JSONObject resultJson = getErrorJson(ex);
			result = resultJson.toString();
			statusCode = 501;

		}
		return Response.status(statusCode).entity(result).build();
	}

	private String formQuery(UriInfo info) throws Exception {
		Properties props = new Properties();
		props.load(getClass().getClassLoader().getResourceAsStream("DAASApp.properties"));
		String query = props.getProperty("Query");

		Pattern patt = Pattern.compile("#[a-zA-Z0-9_]*#");
		Matcher m = patt.matcher(query);
		StringBuffer sb = new StringBuffer(query.length());
		while (m.find()) {
			String text = m.group(0);
			text = text.substring(1, text.length() - 1);

			if (info.getQueryParameters().getFirst(text) == null) {
				throw new Exception("Parameter : " + text + " is not passed");
			}
			// ... possibly process 'text' ...
			m.appendReplacement(sb, info.getQueryParameters().getFirst(text));
		}
		m.appendTail(sb);
		return (sb.toString());

	}

	private String getResponse(JSONObject resultJson, HttpHeaders httpHeaders) throws JSONException {
		String result = resultJson.toString();
		/*
		 * List<MediaType> acceptHeaders=httpHeaders.getAcceptableMediaTypes();
		 * if(null!=acceptHeaders &&
		 * acceptHeaders.contains(AcceptableMediaType.APPLICATION_XML_TYPE)){
		 * result=XML.toString(resultJson); }
		 */
		return result;
	}

	private JSONObject getErrorJson(Exception ex) throws JSONException {
		JSONObject result = new JSONObject();
		JSONObject childObject = new JSONObject();

		childObject.put("ErrorDetails", ex.getMessage());
		childObject.put("Status", "Error");
		result.put("Error", childObject);
		return result;
	}

	private JSONObject processResultsAsJson(ResultSet rs) throws SQLException, JSONException {
		JSONObject rootObj = new JSONObject();
		String colVal;
		if (null != rs) {
			int totalColumns = rs.getMetaData().getColumnCount();
			JSONArray resultRows = new JSONArray();
			JSONObject rootCollectionObj = new JSONObject();

			rootObj.put("DataCollection", rootCollectionObj);
			rootCollectionObj.put("Data", resultRows);
			while (rs.next()) {
				JSONObject rowDataJsonObj = new JSONObject();
				for (int columnIndex = 1; columnIndex <= totalColumns; columnIndex++) {

					colVal = rs.getString(columnIndex);
					if (colVal != null) {
						colVal = colVal.trim();
					}
					rowDataJsonObj.put(rs.getMetaData().getColumnName(columnIndex), colVal);
				}
				resultRows.put(rowDataJsonObj);
			}
		}

		return rootObj;
	}

	private void validateReq(FOXDBHubServiceRequest request) throws FOXDBServiceFault {
		if (null == request) {
			throw new FOXDBServiceFault(IFOXDBHubErrorCodes.REQ_OBJ_EMPTY, IFOXDBHubErrorCodes.REQ_OBJ_EMPTY_MSG);
		}
		if (EOMHubUtil.isEmpty(request.getQuery())) {

			throw new FOXDBServiceFault(IFOXDBHubErrorCodes.QUERY_EMPTY, IFOXDBHubErrorCodes.QUERY_EMPTY_MSG);
		}
	}

}

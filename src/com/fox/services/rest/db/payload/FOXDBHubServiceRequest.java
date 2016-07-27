/**
 * 
 */
package com.fox.services.rest.db.payload;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="foxDBHubRequest")
public class FOXDBHubServiceRequest {
	private String query;
	private String dataSourceName;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}


}

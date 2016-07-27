/**
 * 
 */
package com.fox.services.db.common.util;

import java.io.InputStream;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FOXDBHubServiceProperties {
	private static Properties props = new Properties();
	private static final String fileLoc = "FOXDBHubService.properties";

	static {
		loadProperties();
	}

	private static void loadProperties() {
		try {
			InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileLoc);//new FileInputStream(new File(fileLoc));
			props.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getProperty(String key){
		return props.getProperty(key);
	}
	public static void main(String[] args) {
		System.out.println(FOXDBHubServiceProperties.getProperty("username"));
	}
}

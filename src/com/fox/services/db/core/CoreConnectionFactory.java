/**
 * 
 */
package com.fox.services.db.core;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.fox.services.db.common.exception.FOXDBServiceFault;
import com.fox.services.db.constants.IFOXDBHubErrorCodes;


import java.sql.DriverManager;



public final class CoreConnectionFactory {
    public static Connection createConnectionWithDS(String dataSourceName) throws FOXDBServiceFault {
        Connection connection = null;
        if (null == dataSourceName || "".equals(dataSourceName)) {
            throw new FOXDBServiceFault(IFOXDBHubErrorCodes.DS_EMPTY, IFOXDBHubErrorCodes.DS_EMPTY_MSG);
        }

        try {

            Context ctx = new InitialContext();
           // DataSource dataSource = (DataSource) ctx.lookup(dataSourceName);
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/"+dataSourceName);
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            throw new FOXDBServiceFault(IFOXDBHubErrorCodes.DS_NAMING, e.getMessage());
        } catch (SQLException e) {
            throw new FOXDBServiceFault(e.getErrorCode(), e.getMessage());
        }

        return connection;
    }
    
   
    	    

    	   
    	}

  



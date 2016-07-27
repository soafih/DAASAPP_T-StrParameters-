/**
 * 
 */
package com.fox.services.db.common.util;

import static com.fox.services.db.constants.IFOXDBHubConstants.EMPTY_STR;

import java.sql.Connection;
import java.sql.SQLException;


public class EOMHubUtil {
    public static boolean isEmpty(String str) {
        return (null == str || EMPTY_STR.equals((str.trim())));
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

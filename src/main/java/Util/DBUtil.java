
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
    //Define Database properties

    private static final String URL = "jdbc:mysql://localhost:3306/trainingsNCDMB?serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "%310594%";
    private static Connection con  = null;


    //Static method to get connection

    public static Connection openConnection() throws ClassNotFoundException, SQLException{
        //Check the connection
        if (con == null) {

            //load the driver
            Class.forName(DRIVER);

            //Get the connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //Return the connection

        }
        return con;
    }

    public static void closeConnection(){
        if(con != null){
            con = null;
            System.out.println("DB Connection Closed");
        } else{
            System.out.println("DB connection closed");
        }
    }

}


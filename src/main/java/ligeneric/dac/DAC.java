/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ligeneric.dac;

import java.sql.*;

/**
 *
 * @author liantsiky
 */
public class DAC {
    
    public static Connection getConnectionPostgreSQL(String user,String password,String database) throws Exception{
        Class.forName("org.postgresql.Driver");
            
        Connection connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database,user,password);
         
         return connexion;
    } 
    
    public static Connection getConnectionOracle(String user,String password,String database) throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
            
        Connection connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database,user,password);
         
         return connexion;
    } 
}

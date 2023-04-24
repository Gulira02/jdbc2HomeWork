package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url="jdbc:postgresql://localhost:5432/postgres";
    private static final String  username="postgres";
    private static final String  password="postgres";
    public  static Connection connectionToDatabase(){
        Connection connection=null;
        try{
            System.out.println("Connected to database");
            return  connection= DriverManager.getConnection(url,username ,password);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());}

        return connection;
}}

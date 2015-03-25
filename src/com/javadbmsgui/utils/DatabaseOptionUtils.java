/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javadbmsgui.utils;

/**
 *
 * @author raunakshakya
 */
public class DatabaseOptionUtils {
    
    //configuration for oracle db
    public static final String ORACLE_DB_LABEL = "Oracle DB 12c";
    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String ORACLE_DB_URL = "jdbc:oracle:thin:@localhost:1521:";
    
    //configuration for postgres db
    public static final String POSTGRES_DB_LABEL = "PostGre SQL";
    public static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    public static final String POSTGRES_DB_URL = "jdbc:postgresql://localhost:5432/";
    
    //configuration for sql server db
    public static final String SQL_SERVER_DB_LABEL = "SQL Server 2012";
    public static final String SQL_SERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SQL_SERVER_DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=";
    
    //configuration for mysql db
    public static final String MYSQL_DB_LABEL = "MySQL DB";
    public static final String MYSQL_DB_URL = "jdbc:mysql://localhost/";
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    
    
    
    public static final String SELECT_DBMS_LABEL = "----------------------Select DBMS----------------------";
    public static final String SELECT_DB_DRIVER_LABEL = "----------------Select Database Driver----------------";
    public static final String SELECT_DB_URL_LABEL = "-----------------Select Database URL-----------------";

}

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
public class DatabaseUtils {

    //configuration for oracle db
    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String ORACLE_DB_URL = "jdbc:oracle:thin:@localhost:1521:";

    //configuration for postgres db
    public static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    public static final String POSTGRES_DB_URL = "jdbc:postgresql://localhost:5432/";

    //configuration for sql server db
    public static final String SQL_SERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SQL_SERVER_DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=";

    //configuration for mysql db
    public static final String MYSQL_DB_URL = "jdbc:mysql://localhost/";
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

}

package com.spirit.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;

public class DBUnitConnection {
	
	private static IDatabaseConnection connection;

	public static IDatabaseConnection getConnection() throws ClassNotFoundException, SQLException {
		
		if(connection == null){
		// initialize your database connection here
		Class driverClass = Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://192.168.100.9:3306/SPIRIT_DESARROLLO", "junit", "junit");
		connection = new DatabaseConnection(jdbcConnection);
		String id = "http://www.dbunit.org/features/qualifiedTableNames"; 
		DatabaseConfig config = connection.getConfig(); 
		//Enable multiple schemas support. If enabled, 
		//Dbunit access tables with names fully qualified by schema using this format: SCHEMA.TABLE.
		config.setFeature(id,true);
		}
		return connection;
	}
		

}

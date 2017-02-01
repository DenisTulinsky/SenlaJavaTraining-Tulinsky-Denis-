package com.senla.training.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.senla.training.properties.PropertyFactory;

public class ConnectorDB {
	private Connection connection;
	private static ConnectorDB connectorDb;
	private final Logger log = Logger.getLogger(ConnectorDB.class);
	
	private ConnectorDB() {
		try {
			String url = PropertyFactory.getProps().getValue("db.url");
			String user = PropertyFactory.getProps().getValue("db.user");
			String pass = PropertyFactory.getProps().getValue("db.password");
			connection = DriverManager.getConnection(url, user, pass);
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
	
	public static ConnectorDB getInstance() {
		if (connectorDb == null) {
			connectorDb = new ConnectorDB();
		}
		return connectorDb;
	}
	
	public Connection getConnection(){
		return connection;
	}
		
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
	}
}
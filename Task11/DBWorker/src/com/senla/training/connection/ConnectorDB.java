package com.senla.training.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.senla.training.properties.PropertyFactory;

public class ConnectorDB {
	private Connection connection;
	private static ConnectorDB connectorDb;
	
	private ConnectorDB() {

		try {
			String url = PropertyFactory.getProps().getValue("db.url");
			String user = PropertyFactory.getProps().getValue("db.user");
			String pass = PropertyFactory.getProps().getValue("db.password");
			connection = DriverManager.getConnection(url, user, pass);
			
		} catch (SQLException e) {
			System.err.println("DB connection error: " + e);
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
	
	public Statement getStatement() throws SQLException {
		if (connection != null) {
			Statement statement = connection.createStatement();
			if (statement != null) {
				return statement;
			}
		}
		throw new SQLException("connection or statement is null");
	}

	public void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.err.println("statement is null " + e);
			}
		}
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println(" wrong connection" + e);
			}
		}
	}
}
package com.senla.training.startClient;

import com.senla.training.clientTools.ClientConnector;
import com.senla.training.clientTools.IClientConnector;

public class StartClient {

	public static void main(String[] args) {
				IClientConnector connector =new ClientConnector();
		connector.init();
		
	}

}

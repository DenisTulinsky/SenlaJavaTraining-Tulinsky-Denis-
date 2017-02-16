package com.senla.training.startClient;

import com.senla.training.DI.DI;
import com.senla.training.clientTools.IClientConnector;

public class StartClient {

	public static void main(String[] args) {
		IClientConnector connector =(IClientConnector) DI.load(IClientConnector.class);
		connector.init();
	}
}

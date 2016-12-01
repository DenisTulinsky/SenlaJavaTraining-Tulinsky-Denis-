package com.senla.training.serverStart;

import com.senla.training.DI.DI;
import com.senla.training.serverTools.IServerConnector;

public class StartServer {

	public static void main(String[] args) {
		
		
		IServerConnector serverConnector = (IServerConnector) DI.load(IServerConnector.class);
		
	}
	}


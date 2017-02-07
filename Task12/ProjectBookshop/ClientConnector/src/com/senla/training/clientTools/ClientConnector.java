package com.senla.training.clientTools;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.training.DI.DI;
import com.senla.training.interfaces.INavigator;
import com.senla.training.tools.Transmitter;

/**
 * Provides an instrument to create socket connection and input/output streams. Initializes UI.
 * 
 * @author Denis
 *
 */
public class ClientConnector implements IClientConnector {
	
	private final org.apache.log4j.Logger log = Logger.getLogger(ClientConnector.class);
	
	/**
	 * Creates instances of Socket,Transmitter,INavigator
	 * 
	 * @throws IOException
	 */
	public void init() {
		try {
			Socket socket = new Socket("localhost", 7777);
 			Transmitter trans = new Transmitter(socket);
			INavigator navigator = (INavigator) DI.load(INavigator.class);
			navigator.navigate(trans);
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}

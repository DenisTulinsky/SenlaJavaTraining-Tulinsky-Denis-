package com.senla.training.clientTools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.training.DI.DI;
import com.senla.training.interfaces.INavigator;
import com.senla.training.tools.Transmitter;


public class ClientConnector implements IClientConnector {
	/**
	 * Provides an instrument to create socket connection and input/output streams. Initializes UI.
	 * 
	 * @author Denis
	 *
	 */
	private Socket socket;
	private ObjectOutputStream objectOutput;
	private ObjectInputStream objectInput;
	private final org.apache.log4j.Logger log = Logger.getLogger(ClientConnector.class);
	/**
	 * Creates instances of Socket,ObjectOutputStream,ObjectInputStream,Transmitter,INavigator
	 * 
	 * @throws IOException
	 */
	public void init() {
		try {
			socket = new Socket("localhost", 7777);
			objectOutput = new ObjectOutputStream(socket.getOutputStream());
 			objectInput = new ObjectInputStream(socket.getInputStream());
 			Transmitter trans = new Transmitter(objectOutput,objectInput);
			INavigator navigator = (INavigator) DI.load(INavigator.class);
			navigator.navigate(trans);
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}

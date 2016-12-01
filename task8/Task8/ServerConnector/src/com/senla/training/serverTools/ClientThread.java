package com.senla.training.serverTools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IFacade;
import com.senla.training.tools.MethodInvoker;
/**
 * Provides instruments for reading/writing to the streams, invoking a method received from client, closes the streams. 
 * 
 * @author Denis
 *
 */
class ClientThread extends Thread {

	private ObjectOutputStream objectOutput;
	private ObjectInputStream objectInput;
	private MethodInvoker invoker;
	private final org.apache.log4j.Logger log = Logger.getLogger(ClientThread.class);

	public ClientThread(Socket socket, IFacade facade) throws IOException {
		objectOutput = new ObjectOutputStream(socket.getOutputStream());
		objectInput = new ObjectInputStream(socket.getInputStream());
		invoker = new MethodInvoker(facade);
	}
	/**
	 * Reads and writes to the stream,  disconnects the streams when receives an empty map.
	 * 
	 */
	public void run() {
		
		try {
			while (true) {
				Map<String, Object[]> map = (Map<String, Object[]>) objectInput.readObject();
				if (map.isEmpty()) {
					disconnect();
					break;
				}
				objectOutput.writeObject(invoker.implement(map));
			}
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	/**
	 * Closes the streams. Interrupts the Thread.
	 * 
	 */
	public void disconnect() {
		try {
			if (objectInput != null) {
				objectInput.close();
			}
			if (objectOutput != null) {
				objectOutput.close();
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			this.interrupt();
		}
	}
}
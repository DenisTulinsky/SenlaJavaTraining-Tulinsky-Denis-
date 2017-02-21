package com.senla.training.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.senla.training.requestApi.Request;

/**
 * Provides instruments for sending a map to server via ObjectOutputStream and
 * reading a generic type from ObjectInputStream
 * 
 * @author Denis
 */
public class Transmitter {
	private Socket socket;
	private ObjectOutputStream objectOutput;
	private ObjectInputStream objectInput;
	private final org.apache.log4j.Logger log = Logger.getLogger(Transmitter.class);

	public Transmitter(Socket socket) {
		try {
			this.socket = socket;
			this.objectOutput = new ObjectOutputStream(socket.getOutputStream());
			this.objectInput = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Generic method. Writes a Request object to the stream, receives Object
	 * from the stream, casts it to type parameter <T>, closes the streams
	 * 
	 * @param Request
	 *            object
	 * @return <T>
	 * @throws IOException,UnknownHostException,ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public <T> T toServer(Request method) {
		T results = null;
		try {

			objectOutput.writeObject(method);
			results = (T) objectInput.readObject();

		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		} catch (UnknownHostException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return results;
	}

	/*
	 * @SuppressWarnings("unchecked") public <T> T toServer(Map<String,
	 * Object[]> method) { T results = null; try {
	 * 
	 * objectOutput.writeObject(method); results = (T) objectInput.readObject();
	 * 
	 * } catch (ClassNotFoundException e) { log.error(e.getMessage()); } catch
	 * (UnknownHostException e) { log.error(e.getMessage()); } catch
	 * (IOException e) { log.error(e.getMessage()); } return results; }
	 */
	/**
	 * Closes the streams if not null.
	 * 
	 * @throws IOException
	 */
	public void disconnect() {
		try {
			if (objectInput != null) {
				objectInput.close();
			}
			if (objectOutput != null) {
				objectOutput.close();
			}
			if (socket != null) {
				socket.close();
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
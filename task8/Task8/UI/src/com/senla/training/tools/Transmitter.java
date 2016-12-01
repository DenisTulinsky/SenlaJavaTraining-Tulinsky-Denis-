package com.senla.training.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.log4j.Logger;
/**
 * Provides instruments for sending a map to server via ObjectOutputStream
 *  and reading a generic type from the Object Input Stream
 * @author Denis
 */
public class Transmitter {

	private ObjectOutputStream objectOutput;
	private ObjectInputStream objectInput;
	private final org.apache.log4j.Logger log = Logger.getLogger(Transmitter.class);
	public Transmitter(ObjectOutputStream objectOutput, ObjectInputStream objectInput) {
		this.objectOutput = objectOutput;
		this.objectInput = objectInput;
	}
	/**
	 * Generic method. Writes a map to the stream, receives Object from the stream, 
	 * casts it to type parameter <T>, closes the streams
	 * @param Map<String, Object[]> 
	 * @return <T>
	 * @throws IOException,UnknownHostException,ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public <T> T toServer(Map<String, Object[]> method) {
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
	/**
	 * Closes the streams if not null.
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
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
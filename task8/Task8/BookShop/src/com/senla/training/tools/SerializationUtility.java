package com.senla.training.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IStorage;
import com.senla.training.interfaces.ISerializationUtility;
import com.senla.training.properties.PropertyFactory;
import com.senla.training.storage.Storage;

/**
 * Class for serialization/deserialization of Storage. Filepath taken from
 * Resources/prop.configuration
 * 
 * @author Denis
 *
 */
public class SerializationUtility implements ISerializationUtility {

	private static final String PATH = PropertyFactory.getProps().map.get("serializationPath");
	private final org.apache.log4j.Logger log = Logger.getLogger(SerializationUtility.class);

	/* (non-Javadoc)
	 * @see com.senla.training.tools.IserializationUtility#serialize(com.senla.training.interfaces.IStorage)
	 */
	@Override
	public void serialize(IStorage storage) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))){
			oos.writeObject(storage);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
	}

	/* (non-Javadoc)
	 * @see com.senla.training.tools.IserializationUtility#deserialize()
	 */
	@Override
	public Storage deserialize() {
		Storage storage = null;
		try(ObjectInputStream oin = new ObjectInputStream(new FileInputStream(PATH))) {
			
			storage = (Storage) oin.readObject();
			

		} catch (Exception e) {
			log.error(e.getMessage());

		}

		return storage;

	}
}

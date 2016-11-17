package com.senla.training.interfaces;

import com.senla.training.storage.Storage;

public interface ISerializationUtility {

	/**
	 * Writes serialized storage to file
	 * 
	 * 
	 */
	void serialize(IStorage storage);

	/**
	 * Reads serialized storage from file. Deserializes. Returns Storage or null
	 * 
	 * 
	 */
	Storage deserialize();

}
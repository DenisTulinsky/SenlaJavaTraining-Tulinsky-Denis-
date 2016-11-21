package com.senla.training.properties;

import java.util.HashMap;
import java.util.Map;
/**
 * Privides a map for storing values that were read from config.properties file.
 * 
 * @author Denis
 *
 */
public class Holder {

	public Map<String, String> map = new HashMap<String, String>();;
	/**
	 * Sets the map with key-value pairs
	 * @param String,String
	 */
	public void setProp(String key, String value) {
		map.put(key, value);
	}
	/**
	 * Retrieves the value for the given key.
	 * @param String key
	 */
	public String getValue(String key) {
		return map.get(key);
	}
}
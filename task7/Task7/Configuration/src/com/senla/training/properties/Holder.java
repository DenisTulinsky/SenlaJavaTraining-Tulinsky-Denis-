package com.senla.training.properties;

import java.util.HashMap;
import java.util.Map;

public class Holder {

	public Map<String, String> map = new HashMap<String, String>();;

	public void setProp(String key, String value) {
		map.put(key, value);
	}

	public String getValue(String key) {
		return map.get(key);
	}
}
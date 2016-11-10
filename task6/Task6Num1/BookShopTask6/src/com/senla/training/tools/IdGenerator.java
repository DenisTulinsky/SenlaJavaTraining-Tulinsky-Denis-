package com.senla.training.tools;

public  class IdGenerator {

	private static Integer maxOrderId = 0;
	private static Integer maxBookId = 0;

	public static Integer getMaxBookId() {
		//IdGenerator.maxBookId++;
		return maxBookId;
	}

	public static void setMaxBookId(Integer maxId) {
		IdGenerator.maxBookId = maxId;
	}
	public static Integer getMaxOrderId() {
		IdGenerator.maxOrderId++;
		return maxOrderId;
	}

	public static void setMaxOrderId(Integer maxId) {
		IdGenerator.maxOrderId = maxId;
	}
	public static Integer generateBookId() {
		IdGenerator.maxBookId++;
		return maxBookId;
	}
	
}

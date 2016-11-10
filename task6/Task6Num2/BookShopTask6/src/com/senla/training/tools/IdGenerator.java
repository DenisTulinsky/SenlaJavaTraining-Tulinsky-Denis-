package com.senla.training.tools;

public  class IdGenerator {

	private static Integer maxOrderId = 0;
	private static Integer maxBookId = 0;
	private static Integer maxPreorderId = 0;

	public static Integer getMaxBookId() {
		return maxBookId;
	}

	public static void setMaxBookId(Integer maxId) {
		IdGenerator.maxBookId = maxId;
	}
	
	public static Integer generateBookId() {
		IdGenerator.maxBookId++;
		return maxBookId;
	}
	public static Integer getMaxOrderId() {
		return maxOrderId;
	}

	public static Integer generateOrderId() {
		IdGenerator.maxOrderId++;
		return maxOrderId;
	}
	
	public static void setMaxOrderId(Integer maxId) {
		IdGenerator.maxOrderId = maxId;
	}

	public static Integer getMaxPreorderId() {
		return maxPreorderId;
	}

	public static void setMaxPreorderId(Integer maxPreorderId) {
		IdGenerator.maxPreorderId = maxPreorderId;
	}
	public static Integer generatePreorderId() {
		IdGenerator.maxPreorderId++;
		return maxPreorderId;
	}
	
}

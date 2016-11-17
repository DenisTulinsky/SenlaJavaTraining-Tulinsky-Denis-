package com.senla.training.interfaces;

import java.util.List;

public interface ICSVUtility {

	/**
	 * Writes List<String> to CSV
	 * 
	 * 
	 */
	void writeToCsv(List<String> books);

	/**
	 * Reads lines from CSV and returns List<String>
	 * 
	 * 
	 */
	List<String> readFromCsv();

}
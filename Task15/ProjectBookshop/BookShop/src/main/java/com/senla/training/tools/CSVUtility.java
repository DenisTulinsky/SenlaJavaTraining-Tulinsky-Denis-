package com.senla.training.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Class for writing/reading CSV files.
 * 
 * @author Denis
 *
 */
public class CSVUtility {

	private final static Logger log = Logger.getLogger(CSVUtility.class);

	public static void writeToCsv(List<String> entities, String path) {
		try (FileWriter fileWriter = new FileWriter(path)) {
			for (String str : entities) {
				fileWriter.write(str);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public static List<String> readFromCsv(String path) {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader fileReader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return lines;
	}
}

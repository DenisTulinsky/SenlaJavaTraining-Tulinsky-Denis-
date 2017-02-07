package com.senla.training.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.ICSVUtility;

/**
 * Class for writing/reading CSV files.
 * 
 * @author Denis
 *
 */
public class CSVUtility implements ICSVUtility {

	private final static String FILE_NOT_FOUND_ERROR = "File path not found";
	private final String path;
	private final Logger log = Logger.getLogger(CSVUtility.class);

	
	/**
	 * Constructor checks if the path to file is set.
	 * 
	 * 
	 */
	public CSVUtility(final String filePath) {

		if (filePath == null || filePath.isEmpty()) {
			throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR);
		}
		this.path = filePath;
	}

	/* (non-Javadoc)
	 * @see com.senla.training.tools.ICSVUtility#writeToCsv(java.util.List)
	 */
	@Override
	public void writeToCsv(List<String> books) {

		try (FileWriter fileWriter = new FileWriter(path)) {
			for (String str : books) {
				fileWriter.write(str);
			}
		} catch (Exception e) {
			log.error(e.getMessage());

		}

	}

	/* (non-Javadoc)
	 * @see com.senla.training.tools.ICSVUtility#readFromCsv()
	 */
	@Override
	public List<String> readFromCsv() {

		List<String> lines = new ArrayList<String>();

		try (BufferedReader fileReader = new BufferedReader(new FileReader(path))) {

			String line;
			while ((line = fileReader.readLine()) != null) {
				lines.add(line);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return lines;

	}
}

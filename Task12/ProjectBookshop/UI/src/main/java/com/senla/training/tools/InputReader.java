package com.senla.training.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IInputReader;

public class InputReader implements IInputReader {

	private static final String YEAR = "yyyy";
	private static final String NOT_A_NUMBER = "Not a number";
	private static final String DATE_FORMAT = "yyyy.MM.dd";
	public static final String WRONG_DATE_FORMAT = "Date format is unacceptable";
	private ScannerUI scan;
	private final Logger log = Logger.getLogger(InputReader.class);

	public InputReader(ScannerUI scan) {
		this.scan = scan;
	}

	/* (non-Javadoc)
	 * @see com.senla.training.tools.IInputReader#getDate()
	 */
	@Override
	public GregorianCalendar getDate() {
		Boolean correctDate = false;
		GregorianCalendar date = new GregorianCalendar();
		SimpleDateFormat simpleDate;
		while (!correctDate) {

			String str = scan.getString();
			if (str.length() > 4) {
				simpleDate = new SimpleDateFormat(DATE_FORMAT);
			} else {
				simpleDate = new SimpleDateFormat(YEAR);
			}
			try {
				date.setTime(simpleDate.parse(str));
				correctDate = true;
			} catch (ParseException pe) {
				log.error(pe.getMessage());
				Printer.printString(WRONG_DATE_FORMAT);
			}
		}
		return date;
	}

	/* (non-Javadoc)
	 * @see com.senla.training.tools.IInputReader#getString()
	 */
	@Override
	public String getString() {
		String s = scan.getString();
		return s;
	}

	/* (non-Javadoc)
	 * @see com.senla.training.tools.IInputReader#getInteger()
	 */
	@Override
	public Integer getInteger() {

		Boolean correctInteger = false;
		Integer number = null;
		while (!correctInteger) {
			String str = scan.getString();

			try {
				number = Integer.parseInt(str);
				correctInteger = true;
			} catch (Exception ex) {
				log.error(ex.getMessage());
				Printer.printString(NOT_A_NUMBER);

			}
		}
		return number;

	}
}

package com.senla.training.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IConverter;
import com.senla.training.interfaces.ICSVFileWorker;
import com.senla.training.interfaces.ICSVUtility;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IStorage;

public class CSVFileWorker implements ICSVFileWorker {

	private IConverter converter;
	private final Logger log = Logger.getLogger(CSVFileWorker.class);
	

	public CSVFileWorker(IConverter converter ) {
		this.converter = converter;
		
	}

	@Override
	public void writeBooksToCsv(IStorage storage) {
		ICSVUtility csvUtil = new CSVUtility("Resources/Books.csv");

		try {
			List<IBook> allBooks = storage.getAllBooks();
			List<String> strBooks = new ArrayList<String>();
			for (IBook book : allBooks) {
				strBooks.add(converter.bookToString(book));
			}

			csvUtil.writeToCsv(strBooks);

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());

		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	public void writeOrdersToCsv(IStorage storage) {

		ICSVUtility csvUtil = new CSVUtility("Resources/Orders.csv");
		try {
			List<IOrder> allOrders = storage.getAllOrders();
			List<String> strOrders = new ArrayList<String>();
			for (IOrder order : allOrders) {
				strOrders.add(converter.orderToString(order));
			}

			csvUtil.writeToCsv(strOrders);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void writePreordersToCsv(IStorage storage) {
		ICSVUtility csvUtil = new CSVUtility("Resources/Preorders.csv");
		try {

			List<IPreorder> allPreorders = storage.getAllPreorders();
			List<String> strPreorders = new ArrayList<String>();
			for (IPreorder preorder : allPreorders) {
				strPreorders.add(converter.preorderToString(preorder));
			}

			csvUtil.writeToCsv(strPreorders);

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void readBooksFromCsv(IStorage storage) {
		ICSVUtility csvUtil = new CSVUtility("Resources/Books.csv");

		try {

			List<String> strBooks = csvUtil.readFromCsv();

			for (String str : strBooks) {
				IBook book = converter.stringToBook(str);
				storage.addBook(book);

			}
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void readPreordersFromCsv(IStorage storage) {
		ICSVUtility csvUtil = new CSVUtility("Resources/Preorders.csv");
		try {

			List<String> strPreorders = csvUtil.readFromCsv();

			for (String str : strPreorders) {
				IPreorder preorder = converter.stringToPreorder(str);
				storage.addPreorder(preorder);

			}

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void readOrdersFromCsv(IStorage storage) {
		ICSVUtility csvUtil = new CSVUtility("Resources/Orders.csv");

		try {

			List<String> strOrders = csvUtil.readFromCsv();

			for (String str : strOrders) {
				IOrder order = converter.stringToOrder(str);
				storage.addOrder(order);

			}

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}

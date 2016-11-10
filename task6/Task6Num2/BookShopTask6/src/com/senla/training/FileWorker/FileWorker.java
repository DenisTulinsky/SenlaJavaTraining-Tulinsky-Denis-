package com.senla.training.FileWorker;

import java.util.List;

import org.apache.log4j.Logger;

import com.danco.training.TextFileWorker;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IConverter;
import com.senla.training.interfaces.IFileWorker;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IStorage;
import com.senla.training.tools.IdGenerator;

public class FileWorker implements IFileWorker {

	private String books_txt = "d:/Books.txt";
	private String preorders_txt = "d:/Preorders.txt";
	private String orders_txt = "d:/Orders.txt";
	private IConverter converter;
	private  final Logger log = Logger.getLogger(FileWorker.class);
	public FileWorker(IConverter converter) {
		this.converter = converter;

	}

	@Override
	public void writeToFile(IStorage storage) {
		// wr books
		try  {
		List<IBook> allBooks = storage.getAllBooks();
		TextFileWorker tfwbooks  = new TextFileWorker(books_txt);
			

		String[] strBooks = new String[allBooks.size()];
		for (IBook book : allBooks) {
			String bookString = converter.bookToString(book);
			for (int j = 0; j < strBooks.length; j++) {
				if (strBooks[j] == null) {
					strBooks[j] = bookString;

					break;
				}
			}

		}
			tfwbooks.writeToFile(strBooks);
		// wr orders
		List<IOrder> allOrders = storage.getAllOrders();
		TextFileWorker tfworders = new TextFileWorker(orders_txt);

		String[] strOrders = new String[allOrders.size()];
		for (IOrder order : allOrders) {
			String orderString = converter.orderToString(order);
			for (int j = 0; j < strOrders.length; j++) {
				if (strOrders[j] == null) {
					strOrders[j] = orderString;

					break;
				}
			}

		}
		tfworders.writeToFile(strOrders);

		// wr preorders
		List<IPreorder> allPreorders = storage.getAllPreorders();
		TextFileWorker tfwpreorders = new TextFileWorker(preorders_txt);

		String[] strPreorders = new String[allPreorders.size()];
		for (IPreorder preord : allPreorders) {
			String preorderString = converter.preorderToString(preord);
			for (int j = 0; j < strPreorders.length; j++) {
				if (strPreorders[j] == null) {
					strPreorders[j] = preorderString;

					break;
				}
			}

		}
		tfwpreorders.writeToFile(strPreorders);

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			
		}catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	// read book

	@Override
	public void readFromFile(IStorage storage) {
		try {
			
		
		TextFileWorker tfw = new TextFileWorker(books_txt);
		String[] booksString = tfw.readFromFile();
		for (int j = 0; j < booksString.length; j++) {
			if (booksString[j] != null) {
				IBook book = converter.stringToBook(booksString[j]);
				checkBookId(book.getId());
				storage.addBook(book);
			}

		}

		// read preod

		TextFileWorker tfwPreord = new TextFileWorker(preorders_txt);
		String[] preordersString = tfwPreord.readFromFile();
		for (int j = 0; j < preordersString.length; j++) {
			if (preordersString[j] != null) {
				IPreorder preorder = converter.stringToPreorder(preordersString[j]);
				checkPreorderId(preorder.getId());
				storage.addPreorder(preorder);
			}

		}

		// read order

		TextFileWorker tfwOrder = new TextFileWorker(orders_txt);
		String[] ordersString = tfwOrder.readFromFile();
		for (int j = 0; j < ordersString.length; j++) {
			if (ordersString[j] != null) {
				IOrder order = converter.stringToOrder(ordersString[j]);
				checkOrderId(order.getId());
				storage.addOrder(order);
			}

		}
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage());
		}

	}
	
	private void checkBookId(Integer id){
		if(IdGenerator.getMaxBookId()<id){
			IdGenerator.setMaxBookId(id);
		}
	}
	private void checkOrderId(Integer id){
		if(IdGenerator.getMaxOrderId()<id){
			IdGenerator.setMaxOrderId(id);
		}
	}
	private void checkPreorderId(Integer id){
		if(IdGenerator.getMaxPreorderId()<id){
			IdGenerator.setMaxPreorderId(id);
		}
	}
}

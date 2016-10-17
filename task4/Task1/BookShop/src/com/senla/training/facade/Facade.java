package com.senla.training.facade;

import java.time.Year;
import java.util.Calendar;

import com.senla.training.FileWorker.FileWorker;
import com.senla.training.Storage.Storage;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IFileWorker;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IRequest;
import com.senla.training.interfaces.IStorage;
import com.senla.training.services.BookService;

public class Facade implements IFacade {
	IStorage storage;
	BookService bservice;
	IFileWorker fw;
	@Override
	public void init() {
		fw = new FileWorker();
		storage = new Storage(); 
		bservice = new BookService(storage);
		
	  addBook("A Man", "Miller", Year.of(2002), true, 2, Calendar.getInstance(), "Novel");
	    
	
	}
	
	@Override
	public void addBook(String title,String author, Year publishedDate, Boolean inStock, Integer price, Calendar arrivalDate, String description) {
		bservice.addBook(title,author,publishedDate,inStock,price,arrivalDate,description);
		
		
	}
	

	@Override
	public void removeBook(IBook book) {
		// TODO Auto-generated method stub

	}

	@Override
	public IOrder completeOrder(IOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelOrder(IOrder order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRequest(IRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showAllBooks() {
		bservice.showAllBooks();
		
	}

	@Override
	public void writeToFile() {
		fw.writeToFile(storage);
		
	}

	@Override
	public void readFromFile() {
		fw.readFromFile(storage);
		
	}

	
}

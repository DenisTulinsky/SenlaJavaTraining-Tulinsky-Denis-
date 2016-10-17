package com.senla.training.services;

import java.time.Year;
import java.util.Calendar;
import java.util.List;

import com.senla.training.Storage.Storage;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Book;

public class BookService implements IBookService {
	
	IStorage storage;
	public BookService(IStorage storage){
		this.storage = storage;
		
	}
	
	@Override
	public void addBook(String title, String author, Year publishedDate, Boolean inStock, Integer price,
			Calendar arrivalDate, String description) {
		IBook book = new Book(title,author,publishedDate,inStock,price,arrivalDate,description);
		storage.addBook(book);

	}

	@Override
	public void removeBook(IBook book) {
		

	}

	@Override
	public void showAllBooks() {
		List<IBook> allBooks = storage.getAllBooks();
		for (IBook b : allBooks) {
			b.printMe();
		}
	}

}

package com.senla.training.Storage;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IStorage;

public class Storage implements IStorage {

	private List<IBook> books;

	public Storage() {
		books = new ArrayList<IBook>();
	}

	@Override
	public void addBook(IBook book) {
		books.add(book);

	}

	@Override
	public void removeBook(IBook book) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IBook> getAllBooks() {

		return books;
	}

}

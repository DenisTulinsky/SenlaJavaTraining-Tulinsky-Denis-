package com.senla.training.interfaces;

import java.util.List;

public interface IStorage {
	public void addBook(IBook book);
	public void removeBook(IBook book);
	public List<IBook> getAllBooks();
}

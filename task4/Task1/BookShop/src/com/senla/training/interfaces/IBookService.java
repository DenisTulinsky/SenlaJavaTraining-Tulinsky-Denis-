package com.senla.training.interfaces;

import java.time.Year;
import java.util.Calendar;


public interface IBookService {
	public void addBook(String title,String author, Year publishedDate, Boolean inStock, Integer price, Calendar arrivalDate, String description);
	public void removeBook(IBook book);
	public void showAllBooks();
}

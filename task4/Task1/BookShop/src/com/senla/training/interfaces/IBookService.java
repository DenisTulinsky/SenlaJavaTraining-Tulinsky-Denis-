package com.senla.training.interfaces;

import java.util.Calendar;
import java.util.Comparator;


public interface IBookService {
	public void addBook(String title,String author, Calendar publishedDate, Boolean inStock, Integer price, Calendar arrivalDate, String description);
	public void removeBook(String title,String author, Calendar publishedDate, Boolean inStock, Integer price, Calendar arrivalDate, String description);
	public void showAllBooks(Comparator<IBook> com);
	
}

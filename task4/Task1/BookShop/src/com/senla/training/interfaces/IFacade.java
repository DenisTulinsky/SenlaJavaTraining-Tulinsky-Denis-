package com.senla.training.interfaces;

import java.time.Year;
import java.util.Calendar;


public interface IFacade {
public void init();
public void addBook(String title,String author, Year publishedDate, Boolean inStock, Integer price, Calendar arrivalDate, String description);
public void removeBook(IBook book);
public IOrder completeOrder(IOrder order);
public void cancelOrder(IOrder order);
public void addRequest(IRequest request); 
public void showAllBooks();
public void writeToFile();
public void readFromFile();
	
}



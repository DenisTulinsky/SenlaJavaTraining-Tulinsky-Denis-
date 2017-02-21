package com.senla.training.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import com.senla.training.enums.Status;

public interface IBookService {

	public boolean addBook(String title, String author, GregorianCalendar publishedDate, Status status, Integer price,
			GregorianCalendar arrivalDate, String description);

	public boolean updateStatus(Integer id, Status status);

	public List<String> showAllBooks(String sortBy);

	public List<String> viewBookDescription(Integer id);

	public List<String> showUnwantedBooks(String sortBy);

	 public Boolean deleteBook(Integer id);

	boolean writeBooksToCsv();

	boolean readBooksFromCsv();

	

}
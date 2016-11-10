package com.senla.training.interfaces;

import java.util.Comparator;
import java.util.List;

public interface IBookService {

	public void addBook(IBook book);

	public void removeBook(String title);

	public List<String> showAllBooks(Comparator<IBook> com);

	public List<String> viewBookDescription(String title);

	public List<String> showUnwantedBooks(Comparator<IBook> com);

}
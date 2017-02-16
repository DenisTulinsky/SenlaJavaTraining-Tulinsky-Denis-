package com.senla.training.interfaces;

import java.util.List;

import com.senla.training.enums.Status;
import com.senla.training.model.Book;

public interface IBookService {

	public boolean addBook(Book book);

	public boolean updateStatus(Integer id, Status status);

	public List<String> showAllBooks(String sortBy);

	public List<String> viewBookDescription(Integer id);

	public List<String> showUnwantedBooks(String sortBy);

	 public Boolean deleteBook(Integer id);

}
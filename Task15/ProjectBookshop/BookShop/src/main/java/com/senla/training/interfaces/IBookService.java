package com.senla.training.interfaces;

import java.util.List;

import com.senla.training.exceptions.MyException;
import com.senla.training.model.Book;

public interface IBookService {

	public void updateBook(Book book) throws MyException;

	public List<Book> showAllBooks(String sortBy) throws MyException;

	public Book viewBookDescription(Integer id) throws MyException;

	public List<Book> showUnwantedBooks(String sortBy) throws MyException;

	public void deleteBook(Integer id) throws MyException;

	public void writeBooksToCsv() throws MyException;

	public void readBooksFromCsv() throws MyException;

	public void addBook(Book book) throws MyException;

}
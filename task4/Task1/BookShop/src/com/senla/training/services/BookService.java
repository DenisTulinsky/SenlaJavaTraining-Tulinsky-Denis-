package com.senla.training.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.IPrinter;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Book;

public class BookService implements IBookService {

	private IStorage storage;
	private IPrinter printer;

	public BookService(IStorage storage, IPrinter printer) {
		this.storage = storage;
		this.printer = printer;

	}

	@Override
	public void addBook(String title, String author, Calendar publishedDate, Boolean inStock, Integer price,
			Calendar arrivalDate, String description) {

		IBook book = new Book(title, author, publishedDate.getTime(), inStock, price, arrivalDate.getTime(),
				description);
		storage.addBook(book);

	}

	@Override
	public void removeBook(String title, String author, Calendar publishedDate, Boolean inStock, Integer price,
			Calendar arrivalDate, String description) {
		storage.removeBook(title, author, publishedDate.getTime(), inStock, price, arrivalDate.getTime(), description);

	}

	@Override
	public void showAllBooks(Comparator<IBook> com) {
		List<IBook> allBooks = storage.getAllBooks();
		if (com != null) {
			Collections.sort(allBooks, com);
		}

		for (IBook b : allBooks) {
			printer.print(b);
		}
	}

	public void viewBookDescription(String title, String author) {
		List<IBook> allBooks = storage.getAllBooks();
		for (IBook b : allBooks) {
			if (b != null && b.getTitle().toString().equals(title.toString())
					&& b.getAuthor().toString().equals(author.toString())) {
				printer.print(b);
				break;
			}

		}

	}

	public void showUnwantedBooks(Comparator<IBook> com) {
		List<IBook> allBooks = storage.getAllBooks();
		List<IBook> unwantedBooks = new ArrayList<>();
		for (IBook book : allBooks) {
			if (book.getInStock()) {
				Calendar arrDate = new GregorianCalendar();
				arrDate.setTime(book.getArrivalDate());
				Calendar date = Calendar.getInstance();
				date.add(Calendar.MONTH, -6);
				if (arrDate.before(date)) {
					unwantedBooks.add(book);
				}
			}
		}
		Collections.sort(unwantedBooks, com);
		for (IBook book : unwantedBooks) {
			printer.print(book);
		}
	}

	public void markPreorder(String title, String author) {
		List<IBook> allBooks = storage.getAllBooks();
		int sum;
		for (IBook b : allBooks) {

			if (b != null && b.getTitle().toString().equals(title.toString())
					&& b.getAuthor().toString().equals(author.toString())) {
				sum = b.getPreorders() + 1;
				b.setPreorders(sum);
				break;
			}
		}
	}

	public void showPreorders(Comparator<IBook> com) {
		List<IBook> allBooks = storage.getAllBooks();
		for (IBook b : allBooks) {
			if (b != null && b.getPreorders() != 0) {

			}
		}
		Collections.sort(allBooks, com);
		for (IBook book : allBooks) {
			String str = new StringBuilder().append("Книга: ").append(book.getTitle()).append(" Запросы: ")
					.append(book.getPreorders()).toString();
			System.out.println(str);

		}
	}
}
package com.senla.training.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.senla.training.annotationsWorker.AnnotationsWorker;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IStorage;
import com.senla.training.properties.PropertyFactory;
import com.senla.training.status.Status;

/**
 * Provides instruments for working with books and the storage.
 * 
 * @author Denis
 *
 */
public class BookService implements IBookService {

	private IStorage storage;
	private IConverterReadableString converterToString;
	private final Logger log = Logger.getLogger(BookService.class);

	public BookService(IStorage storage, IConverterReadableString converterToString) {
		this.storage = storage;
		this.converterToString = converterToString;

	}

	@Override
	public void addBook(IBook book) {
		book.setId(UUID.randomUUID().toString());
		storage.addBook(book);
	}

	@Override
	public void removeBook(String title) {
		List<IBook> allBooks = storage.getAllBooks();
		for (IBook b : allBooks) {
			if (b.getTitle().equals(title)) {
				b.setInStock(Status.NOTINSTOCK);
			}
		}
	}

	@Override
	public List<String> showAllBooks(Comparator<IBook> com) {
		List<IBook> allBooks = storage.getAllBooks();
		List<String> result = new ArrayList<>();
		if (com != null) {
			Collections.sort(allBooks, com);
		}
		for (IBook b : allBooks) {
			result.add(converterToString.convert(b));
		}
		return result;
	}

	public List<String> viewBookDescription(String title) {
		List<String> result = new ArrayList<>();
		List<IBook> allBooks = storage.getAllBooks();
		for (IBook b : allBooks) {
			if (b != null && b.getTitle().equals(title)) {
				try {
					result = AnnotationsWorker.createAnnotation(b);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					log.error(e.getMessage());
				}
			}
		}
		return result;
	}

	public List<String> showUnwantedBooks(Comparator<IBook> com) {
		List<IBook> allBooks = storage.getAllBooks();
		List<IBook> unwantedBooks = new ArrayList<>();
		List<String> result = new ArrayList<>();
		try {
			for (IBook book : allBooks) {
				if (book.getInStock() == Status.INSTOCK) {
					Calendar arrDate = new GregorianCalendar();
					arrDate.setTime(book.getArrivalDate());
					Calendar date = Calendar.getInstance();

					Integer mm = Integer.valueOf(PropertyFactory.getProps().getValue("monthsUnwanted"));
					date.add(Calendar.MONTH, -mm);

					if (arrDate.before(date)) {
						unwantedBooks.add(book);
					}
				}
			}
			Collections.sort(unwantedBooks, com);
			for (IBook book : unwantedBooks) {
				result.add(converterToString.convert(book));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}

}
package com.senla.training.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.senla.training.dao.BookDAO;
import com.senla.training.enums.Status;
import com.senla.training.interfaces.IConverter;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class ConverterCSV implements IConverter {

	private final Logger log = Logger.getLogger(ConverterCSV.class);
	private BookDAO bookDao;

	public ConverterCSV(BookDAO bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public String bookToString(Book book) {
		String str = new StringBuilder().append(book.getId()).append(",").append(book.getTitle()).append(",")
				.append(book.getAuthor()).append(",").append(book.getPublishedDate()).append(",")
				.append(book.getStatus()).append(",").append(book.getPrice()).append(",").append(book.getArrivalDate())
				.append(",").append(book.getDescription()).append("\n").toString();

		return str;
	}

	@Override
	public Book stringToBook(String bookstring) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date publDate;
		Date arrivDate;
		Book book = new Book();
		try {
			String[] str = bookstring.split(",");
			book.setId(Integer.valueOf(str[0]));
			book.setTitle(str[1]);
			book.setAuthor(str[2]);
			publDate = format.parse(str[3]);
			book.setPublishedDate(publDate);
			book.setStatus(Status.valueOf(str[4]));
			book.setPrice(Integer.valueOf(str[5]));
			arrivDate = format.parse(str[6]);
			book.setArrivalDate(arrivDate);
			book.setDescription(str[7]);

		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return book;
	}

	@Override
	public String orderToString(Order order) {
		String bookIds = "";
		for (Book b : order.getBooks()) {
			bookIds += b.getId() + "_";
		}

		String str = new StringBuilder().append(order.getId()).append(",").append(bookIds).append(",")
				.append(order.getCustomer()).append(",").append(order.getPrice()).append(",")
				.append(order.getExecutionDate()).append(",").append(order.getStatus()).append("\n").toString();
		return str;
	}

	@Override
	public Order stringToOrder(Session session, String orderstring) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date execDate;
		Set<Book> books = new HashSet<Book>();
		Order order = new Order();
		try {
			String[] str = orderstring.split(",");
			order.setId(Integer.valueOf(str[0]));
			String[] ids = String.valueOf(str[1]).split("_");
			for (String id : ids) {
				books.add(bookDao.findEntityById(session, Integer.valueOf(id)));
			}
			order.setBooks(books);
			order.setCustomer(str[2]);
			order.setPrice(Integer.valueOf(str[3]));
			execDate = format.parse(str[4]);
			order.setExecutionDate(execDate);
			order.setStatus(Status.valueOf(str[5]));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return order;
	}

	@Override
	public String preorderToString(Preorder preord) {
		String str = new StringBuilder().append(preord.getId()).append(",").append(preord.getBook().getId()).append(",")
				.append(preord.getStatus()).append("\n").toString();
		return str;
	}

	@Override
	public Preorder stringToPreorder(Session session, String preorderstring) {

		Preorder preorder = new Preorder();
		String[] str = preorderstring.split(",");
		preorder.setId(Integer.valueOf(str[0]));
		preorder.setBook(bookDao.findEntityById(session, Integer.valueOf(str[1])));
		preorder.setStatus(Status.valueOf(str[2]));
		return preorder;
	}
}

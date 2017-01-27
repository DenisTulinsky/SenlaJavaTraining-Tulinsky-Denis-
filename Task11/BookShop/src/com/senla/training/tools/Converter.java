package com.senla.training.tools;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IConverter;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class Converter implements IConverter {

	private IStorage storage;
	private final Logger log = Logger.getLogger(Converter.class);
	public Converter(IStorage storage) {
		this.storage = storage;

	}

	@Override
	public String bookToString(IBook book) {
		String str = new StringBuilder().append(book.getId()).append(",").append(book.getTitle()).append(",")
				.append(book.getAuthor()).append(",").append(book.getPublishedDate().getTime()).append(",")
				.append(book.getInStock()).append(",").append(book.getPrice()).append(",")
				.append(book.getArrivalDate().getTime()).append(",").append(book.getDescription()).append("\n")
				.toString();

		return str;

	}

	@Override
	public IBook stringToBook(String bookstring) {
		IBook book = new Book(bookstring);
		String[] str = bookstring.split(",");
		book.setId(str[0]);
		book.setTitle(str[1]);
		book.setAuthor(str[2]);

		Date publDate = new Date();
		publDate.setTime(Long.valueOf(str[3]));
		book.setPublishedDate(publDate);

		book.setInStock(Status.valueOf(str[4]));
		book.setPrice(Integer.valueOf(str[5]));

		Date arrivDate = new Date();
		arrivDate.setTime(Long.valueOf(str[6]));
		book.setArrivalDate(arrivDate);
		book.setDescription(str[7]);
		////
		return book;
	}

	@Override
	public String orderToString(IOrder order) {
		String str = new StringBuilder().append(order.getId()).append(",").append(order.getBook().getTitle())
				.append(",").append(order.getBook().getAuthor()).append(",").append(order.getCustomer()).append(",")
				.append(order.getExecutionDate().getTime()).append(",").append(order.getPrice()).append(",")
				.append(order.getStatus()).append("\n").toString();
		return str;
	}

	@Override
	public IOrder stringToOrder(String orderstring) {
		IOrder order = new Order(orderstring);
		try {
			
			String[] str = orderstring.split(",");
			order.setId(str[0]);
			List<IBook> books = storage.getAllBooks();
			for (IBook b : books) {
				if (b != null && b.getTitle().equals(str[1]) && b.getAuthor().equals(str[2])) {

					order.setBook(b);
					break;
				}
			}

			order.setCustomer(str[3]);
			Date execDate = new Date();
			execDate.setTime(Long.valueOf(str[4]));
			order.setExecutionDate(execDate);
			order.setPrice(Integer.valueOf(str[5]));
			order.setStatus(Status.valueOf(str[6]));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return order;

	}

	@Override
	public String preorderToString(IPreorder preord) {
		String str = new StringBuilder().append(preord.getId()).append(",").append(preord.getTitle()).append(",")
				.append(preord.getAuthor()).append(",").append(preord.getCount()).append(",").append(preord.getStatus())
				.append("\n").toString();
		return str;

	}

	@Override
	public IPreorder stringToPreorder(String preorderstring) {

		IPreorder preorder = new Preorder(preorderstring);
		String[] str = preorderstring.split(",");
		preorder.setId(str[0]);
		preorder.setTitle(str[1]);
		preorder.setAuthor(str[2]);
		preorder.setCount(Integer.valueOf(str[3]));
		preorder.setStatus(Status.valueOf(str[4]));
		return preorder;

	}

}

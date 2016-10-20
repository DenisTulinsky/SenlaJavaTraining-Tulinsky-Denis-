package com.senla.training.converter;

import java.util.Date;
import java.util.List;

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

	public Converter(IStorage storage) {
		this.storage = storage;

	}

	@Override
	public String bookToString(IBook book) {
		String str = new StringBuilder().append(book.getTitle()).append(",").append(book.getAuthor()).append(",")
				.append(book.getPublishedDate().getTime()).append(",").append(book.getInStock()).append(",")
				.append(book.getPrice()).append(",").append(book.getArrivalDate().getTime()).append(",")
				.append(book.getDescription()).toString();

		return str;

	}

	@Override
	public IBook stringToBook(String bookstring) {
		IBook book = new Book(bookstring);
		String[] str = bookstring.split(",");
		book.setTitle(str[0]);
		book.setAuthor(str[1]);

		Date publDate = new Date();
		publDate.setTime(Long.valueOf(str[2]));
		book.setPublishedDate(publDate);

		book.setInStock(Boolean.valueOf(str[3]));
		book.setPrice(Integer.valueOf(str[4]));

		Date arrivDate = new Date();
		arrivDate.setTime(Long.valueOf(str[5]));
		book.setArrivalDate(arrivDate);

		book.setDescription(str[6]);
		return book;
	}

	@Override
	public String orderToString(IOrder order) {
		String str = new StringBuilder().append(order.getBook().getTitle()).append(",")
				.append(order.getBook().getAuthor()).append(",").append(order.getCustomer()).append(",")
				.append(order.getExecutionDate().getTime()).append(",").append(order.getPrice()).append(",")
				.append(order.getStatus()).toString();
		return str;
	}

	@Override
	public IOrder stringToOrder(String orderstring) {
		IOrder order = new Order(orderstring);
		String[] str = orderstring.split(",");

		List<IBook> books = storage.getAllBooks();
		for (IBook b : books) {
			if (b != null && b.getTitle().equals(str[0]) && b.getAuthor().equals(str[1])) {

				order.setBook(b);

				break;
			}
		}

		order.setCustomer(str[2]);
		Date execDate = new Date();
		execDate.setTime(Long.valueOf(str[3]));
		order.setExecutionDate(execDate);
		order.setPrice(Integer.valueOf(str[4]));
		order.setStatus(Boolean.valueOf(str[5]));

		return order;

	}

	@Override
	public String preorderToString(IPreorder preord) {
		String str = new StringBuilder().append(preord.getAuthor()).append(",").append(preord.getTitle()).append(",")
				.append(preord.getPublishedDate().getTime()).append(",").append(preord.getStatus()).toString();
		return str;

	}

	@Override
	public IPreorder stringToPreorder(String preorderstring) {

		IPreorder preorder = new Preorder(preorderstring);
		String[] str = preorderstring.split(",");
		preorder.setTitle(str[0]);
		preorder.setAuthor(str[1]);

		Date publDate = new Date();
		publDate.setTime(Long.valueOf(str[2]));
		preorder.setPublishedDate(publDate);
		preorder.setStatus(Boolean.valueOf(str[3]));
		return preorder;

	}

}

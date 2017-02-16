package com.senla.training.tools;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class ConverterReadableString implements IConverterReadableString {
	private final Logger log = Logger.getLogger(ConverterReadableString.class);

	@Override
	public String convert(Book book) {
		String str = null;
		try {
			str = new StringBuilder().append("ID: ").append(book.getId()).append(" Title: ")
					.append(book.getTitle()).append(" Author: ").append(book.getAuthor())
					.append(" Date of publication: ").append(book.getPublishedDate()).append(" Status: ")
					.append(book.getStatus()).append(" Price: ").append(book.getPrice()).append(" Arrival Date: ")
					.append(book.getArrivalDate()).append(" Description: ").append(book.getDescription()).append("\n").toString();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return str;
	}

	@Override
	public String convert(Order order) {
		String str = null;
		try {
			str = new StringBuilder().append("ID: ").append(order.getId()).append(" Number of Books: ")
					.append(order.getBooks().size()).append(" Customer: ").append(order.getCustomer()).append(" Price: ").append(order.getPrice())
					.append(" Execution status: ").append(order.getStatus()).append(" Execution date ")
					.append(order.getExecutionDate()).append("\n").toString();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return str;

	}

	@Override
	public String convert(Preorder preorder) {
		String str = null;
		try {
			str = new StringBuilder().append(" Book title: ").append(preorder.getBook().getTitle()).append(" Book author: ")
					.append(preorder.getBook().getAuthor()).append(" Book id: ").append(preorder.getBook().getId()).append(" Number of Preorders: ").append(preorder.getCount())
					.append(" Execution status: ").append(preorder.getStatus()).append("\n").toString();
			return str;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return str;
	}

}

package com.senla.training.tools;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IPreorder;

public class ConverterReadableString implements IConverterReadableString {
	private final Logger log = Logger.getLogger(ConverterReadableString.class);

	@Override
	public String convert(IBook book) {
		String str = null;
		try {
			str = new StringBuilder().append("ID: ").append(book.getId()).append(" Title: ")
					.append(book.getTitle()).append(" Author: ").append(book.getAuthor())
					.append(" Date of publication: ").append(book.getPublishedDate()).append(" In Stock: ")
					.append(book.getInStock()).append(" Price: ").append(book.getPrice()).append(" Arrival Date: ")
					.append(book.getArrivalDate()).append(" Description: ").append(book.getDescription()).append("\n").toString();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return str;
	}

	@Override
	public String convert(IOrder order) {
		String str = null;
		try {
			str = new StringBuilder().append("ID: ").append(order.getId()).append(" Book id: ")
					.append(order.getBookId()).append(" Customer: ").append(order.getCustomer()).append(" Price: ").append(order.getPrice())
					.append(" Execution status: ").append(order.getStatus()).append(" Execution date ")
					.append(order.getExecutionDate()).append("\n").toString();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return str;

	}

	@Override
	public String convert(IPreorder preorder) {
		String str = null;
		try {
			str = new StringBuilder().append("ID: ").append(preorder.getId())
					.append(" Book title: ").append(preorder.getTitle()).append(" Book author: ")
					.append(preorder.getAuthor()).append(" Number of Preorders: ").append(preorder.getCount())
					.append(" Execution status: ").append(preorder.getStatus()).append("\n").toString();
			return str;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return str;
	}

}

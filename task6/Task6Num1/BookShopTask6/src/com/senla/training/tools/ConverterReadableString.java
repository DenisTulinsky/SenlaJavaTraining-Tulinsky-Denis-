package com.senla.training.tools;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IPreorder;

public class ConverterReadableString implements IConverterReadableString {

	@Override
	public String convert(IBook book) {
		String str = new StringBuilder().append("Book \n").append("Title: ").append(book.getTitle()).append(" Author: ")
				.append(book.getAuthor()).append(" Date of publication: ").append(book.getPublishedDate())
				.append(" In Stock: ").append(book.getInStock()).append(" Price: ").append(book.getPrice())
				.append(" Arrival Date: ").append(book.getArrivalDate()).append(" Description: ")
				.append(book.getDescription()).toString();
		
		return str;
	}

	@Override
	public String convert(IOrder order) {
		String str = new StringBuilder().append("Order \n").append("Book title: ").append(order.getBook().getTitle())
				.append(" Book author: ").append(order.getBook().getAuthor()).append(" Customer: ")
				.append(order.getCustomer()).append(" Price: ").append(order.getPrice()).append(" Execution status: ")
				.append(order.getStatus()).append(" Execution date ").append(order.getExecutionDate()).toString();

		return str;

	}

	@Override
	public String convert(IPreorder preorder) {

		String str = new StringBuilder().append("Preorder \n").append("Book title: ").append(preorder.getTitle())
				.append(" Book author: ").append(preorder.getAuthor()).append(" Number of Preorders: ").append(preorder.getCount()).append(" Execution status: ").append(preorder.getStatus())
				.toString();
		return str;

	}

}

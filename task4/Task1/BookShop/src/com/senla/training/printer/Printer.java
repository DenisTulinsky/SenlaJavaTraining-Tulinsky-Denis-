package com.senla.training.printer;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPrinter;
import com.senla.training.interfaces.IPreorder;

public class Printer implements IPrinter {

	@Override
	public void print(IBook book) {
		String str = new StringBuilder().append("Book \n").append("Title: ").append(book.getTitle()).append(" Author: ")
				.append(book.getAuthor()).append(" Date of publication: ").append(book.getPublishedDate())
				.append(" In Stock: ").append(book.getInStock()).append(" Price: ").append(book.getPrice())
				.append(" Arrival Date: ").append(book.getArrivalDate()).append(" Description: ")
				.append(book.getDescription()).toString();
		System.out.println(str);

	}

	@Override
	public void print(IOrder order) {
		String str = new StringBuilder().append("Order \n").append("Book title: ").append(order.getBook().getTitle())
				.append(" Book author: ").append(order.getBook().getAuthor()).append(" Customer: ")
				.append(order.getCustomer()).append(" Price: ").append(order.getPrice()).append(" Execution status: ")
				.append(order.getStatus()).append(" Execution date ").append(order.getExecutionDate()).toString();

		System.out.println(str);

	}

	@Override
	public void print(IPreorder preorder) {

		String str = new StringBuilder().append("Preorder \n").append("Book title: ").append(preorder.getTitle())
				.append(" Book author: ").append(preorder.getAuthor()).append(" Publication date")
				.append(preorder.getPublishedDate()).append(" Execution status: ").append(preorder.getStatus())
				.toString();
		System.out.println(str);

	}

}

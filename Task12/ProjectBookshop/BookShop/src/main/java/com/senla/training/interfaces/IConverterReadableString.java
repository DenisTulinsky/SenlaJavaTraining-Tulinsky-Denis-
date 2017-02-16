package com.senla.training.interfaces;

import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public interface IConverterReadableString {
	public String convert(Book book);

	public String convert(Order order);

	public String convert(Preorder preorder);
}
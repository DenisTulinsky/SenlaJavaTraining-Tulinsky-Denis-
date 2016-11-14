package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IBook;

public class BookByStatusComparator implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {

		return book1.getInStock().compareTo(book2.getInStock());
	}

}
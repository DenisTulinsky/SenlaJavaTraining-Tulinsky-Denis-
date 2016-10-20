package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IBook;

public class BookByTitleComp implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {
	
		return book1.getTitle().compareTo(book2.getTitle());
	}

}

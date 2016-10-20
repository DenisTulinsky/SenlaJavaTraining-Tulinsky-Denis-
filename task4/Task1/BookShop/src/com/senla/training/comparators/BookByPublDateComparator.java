package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IBook;

public class BookByPublDateComparator implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {
		
		if (book1.getPublishedDate().after(book2.getPublishedDate()))
			return 1;
		else if (book1.getPublishedDate().before(book2.getPublishedDate()))
			return -1;
		else
			return 0;
	}

}

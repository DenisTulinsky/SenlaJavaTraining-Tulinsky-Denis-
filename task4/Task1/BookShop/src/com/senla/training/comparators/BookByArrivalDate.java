package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IBook;

public class BookByArrivalDate implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {
		
		if (book1.getArrivalDate().after(book2.getArrivalDate()))
			return 1;
		else if (book1.getArrivalDate().before(book2.getArrivalDate()))
			return -1;
		else
			return 0;
	}

}

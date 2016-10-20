package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IBook;

public class BookByPriceComparator implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {

		if (book1.getPrice() > book2.getPrice())
			return 1;
		else if (book1.getPrice() < book2.getPrice())
			return -1;
		else
			return 0;
	}

}
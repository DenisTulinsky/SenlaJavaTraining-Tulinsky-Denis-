package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IBook;

public class BookByPreordersNumberComp implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {

		if (book1.getPreorders() > book2.getPreorders())
			return 1;
		else if (book1.getPreorders() < book2.getPreorders())
			return -1;
		else
			return 0;
	}

}
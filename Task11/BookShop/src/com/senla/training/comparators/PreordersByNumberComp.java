package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IPreorder;

public class PreordersByNumberComp implements Comparator<IPreorder> {

	@Override
	public int compare(IPreorder preorder1, IPreorder preorder2) {

		if (preorder1.getCount() > preorder2.getCount())
			return 1;
		else if (preorder1.getCount() < preorder2.getCount())
			return -1;
		else
			return 0;
	}

}
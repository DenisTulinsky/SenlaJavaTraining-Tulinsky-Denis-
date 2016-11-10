package com.senla.training.comparators;

import java.util.Comparator;


import com.senla.training.interfaces.IPreorder;

public class PreordersByTitleComp implements Comparator<IPreorder> {

	@Override
	public int compare(IPreorder preorder1, IPreorder preorder2) {

		return preorder1.getTitle().compareTo(preorder2.getTitle());
	}

}

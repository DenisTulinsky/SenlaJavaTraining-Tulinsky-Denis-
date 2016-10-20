package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IOrder;

public class OrderByPriceComp implements Comparator<IOrder> {

	@Override
	public int compare(IOrder order1, IOrder order2) {

		if (order1.getPrice() > order2.getPrice())
			return 1;
		else if (order1.getPrice() < order2.getPrice())
			return -1;
		else
			return 0;
	}
}

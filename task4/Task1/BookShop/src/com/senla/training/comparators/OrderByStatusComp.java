package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IOrder;

public class OrderByStatusComp implements Comparator<IOrder> {

	@Override
	public int compare(IOrder order1, IOrder order2) {

		if (!order1.getStatus() && order2.getStatus())
			return 1;
		else if (order1.getStatus() && !order2.getStatus())
			return -1;
		else
			return 0;

	}

}
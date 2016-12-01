package com.senla.training.comparators;

import java.util.Comparator;

import com.senla.training.interfaces.IOrder;

public class OrderByStatusComp implements Comparator<IOrder> {

	@Override
	public int compare(IOrder order1, IOrder order2) {

		return order1.getStatus().compareTo(order2.getStatus());
		

	}

}
package com.senla.training.interfaces;

public interface IPrinter {
	public void print(IBook book);

	public void print(IOrder order);

	public void print(IPreorder preorder);
}
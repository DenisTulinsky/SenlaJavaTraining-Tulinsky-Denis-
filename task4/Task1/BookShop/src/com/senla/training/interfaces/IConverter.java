package com.senla.training.interfaces;

public interface IConverter {
	public String bookToString(IBook book);

	public IBook stringToBook(String string);

	public String orderToString(IOrder order);

	public IOrder stringToOrder(String string);

	public String preorderToString(IPreorder preord);

	public IPreorder stringToPreorder(String string);
}

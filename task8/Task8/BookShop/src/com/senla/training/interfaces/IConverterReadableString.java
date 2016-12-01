package com.senla.training.interfaces;

public interface IConverterReadableString {
	public String convert(IBook book);

	public String convert(IOrder order);

	public String convert(IPreorder preorder);
}
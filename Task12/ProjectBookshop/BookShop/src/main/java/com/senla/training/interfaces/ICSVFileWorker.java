package com.senla.training.interfaces;

public interface ICSVFileWorker {
	public boolean writeBooksToCsv(IBookDAO bookDao);
	public boolean writeOrdersToCsv(IOrderDAO orderDao);
	public boolean writePreordersToCsv(IPreorderDAO preorderDao);
	
	public boolean readBooksFromCsv(IBookDAO bookDao);
	public boolean readOrdersFromCsv(IOrderDAO orderDao);
	public boolean readPreordersFromCsv(IPreorderDAO preorderDao);
}

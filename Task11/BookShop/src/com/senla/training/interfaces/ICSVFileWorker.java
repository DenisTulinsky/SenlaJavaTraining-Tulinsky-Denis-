package com.senla.training.interfaces;

import com.senla.training.dao.BookDAO;
import com.senla.training.dao.OrderDAO;
import com.senla.training.dao.PreorderDAO;

public interface ICSVFileWorker {
	public boolean writeBooksToCsv(BookDAO bookDao);
	public boolean writeOrdersToCsv(OrderDAO orderDao);
	public boolean writePreordersToCsv(PreorderDAO preorderDao);
	
	public boolean readBooksFromCsv(BookDAO bookDao);
	public boolean readOrdersFromCsv(OrderDAO orderDao);
	public boolean readPreordersFromCsv(PreorderDAO preorderDao);
}

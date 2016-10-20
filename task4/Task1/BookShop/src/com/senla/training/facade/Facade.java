package com.senla.training.facade;

import java.util.Calendar;
import java.util.Comparator;

import com.senla.training.FileWorker.FileWorker;
import com.senla.training.Storage.Storage;
import com.senla.training.comparators.BookByArrivalDate;
import com.senla.training.comparators.BookByPreordersNumberComp;
import com.senla.training.comparators.BookByPriceComparator;
import com.senla.training.comparators.BookByPublDateComparator;
import com.senla.training.comparators.BookByStatusComparator;
import com.senla.training.comparators.BookByTitleComp;
import com.senla.training.comparators.OrderByDateComp;
import com.senla.training.comparators.OrderByPriceComp;
import com.senla.training.comparators.OrderByStatusComp;
import com.senla.training.converter.Converter;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IConverter;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IFileWorker;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.interfaces.IPrinter;
import com.senla.training.interfaces.IStorage;
import com.senla.training.printer.Printer;
import com.senla.training.services.BookService;
import com.senla.training.services.OrderService;
import com.senla.training.services.PreorderService;

public class Facade implements IFacade {
	IStorage storage;
	BookService bservice;
	IFileWorker fw;
	IPrinter printer;
	IConverter converter;
	IPreorderService preodservice;
	IOrderService orderservice;
	
	@Override
	public void init() {
		
		storage = new Storage(); 
		printer = new Printer();
		bservice = new BookService(storage,printer);
		preodservice = new PreorderService(storage,printer);
		orderservice = new OrderService(storage,printer);
		converter = new Converter(storage);
		fw = new FileWorker(converter);
	    
	
	}
	
	@Override
	public void addBook(String title,String author, Calendar publishedDate, Boolean inStock, Integer price, Calendar arrivalDate, String description) {
		bservice.addBook(title,author,publishedDate,inStock,price,arrivalDate,description);
		preodservice.checkPreorders(title,author,publishedDate);
		
	}
	

	@Override
	public void removeBook(String title,String author, Calendar publishedDate, Boolean inStock, Integer price, Calendar arrivalDate, String description) {
		bservice.removeBook(title,author,publishedDate,inStock,price,arrivalDate,description);

	}

	

	

	

	@Override
	public void showAllBooksSortByTitle() {
		Comparator<IBook> byTitle = new BookByTitleComp();
		bservice.showAllBooks(byTitle);
		
	}
	@Override
	public void showAllBooksSortByPrice() {
		Comparator<IBook> byPrice = new BookByPriceComparator();
		bservice.showAllBooks(byPrice);
		
	}
	@Override
	public void showAllBooksSortByInStock() {
		Comparator<IBook> byInStock = new BookByStatusComparator();
		bservice.showAllBooks(byInStock);
		
	}

	@Override
	public void writeToFile() {
		fw.writeToFile(storage);
		
	}

	@Override
	public void readFromFile() {
		fw.readFromFile(storage);
		
	}

	public void addPreorder(String title, String author,Calendar publishedDate) {
		preodservice.addPreorder(title,author,publishedDate);
		bservice.markPreorder(title,author);
	}

	public void addOrder(String title, String author,String Customer, int price, Calendar executiondate) {
		orderservice.addOrder(title,author,Customer,price,executiondate);
		
	}

	public void cancelOrder(String title, String author,String Customer, int price, Calendar executiondate) {
		orderservice.cancelOrder(title,author,Customer,price,executiondate);
			
	}

	

	public void executeOrder(String title, String author,String Customer, int price, Calendar executiondate) {
		orderservice.executeOrder(title,author,Customer,price,executiondate);
		
	}

	public void showAllPreorders() {
		preodservice.showAllPreorders();
		
	}

	public void showAllBooksSortByPublDate() {
		Comparator<IBook> byPublDate = new BookByPublDateComparator();
		bservice.showAllBooks(byPublDate);
		
	}

	public void showExecOrdersByPeriodSortByDate(Calendar calendar1, Calendar calendar2) {
		Comparator<IOrder> ByDate = new OrderByDateComp();
		orderservice.showExecOrdersByPeriod( calendar1,  calendar2, ByDate );
		
	}

	public void showExecOrdersByPeriodSortByPrice(Calendar c1, Calendar c5) {
		Comparator<IOrder> ByPrice = new OrderByPriceComp();
		orderservice.showExecOrdersByPeriod( c1,  c5, ByPrice );
		
	}

	public void showOrdersByDate() {
		Comparator<IOrder> byExecDate = new OrderByDateComp();
		orderservice.showAllOrders(byExecDate);
			}

	public void showOrdersByPrice() {
		Comparator<IOrder> byPrice = new OrderByPriceComp();
		orderservice.showAllOrders(byPrice);
		
	}

	public void showOrdersByStatus() {
		Comparator<IOrder> byStatus = new OrderByStatusComp();
		orderservice.showAllOrders(byStatus);
		
	}

	public void showExecOrdersCount(Calendar c1, Calendar c5) {
		orderservice.showExecOrdersCount( c1,  c5);
		
	}

	public void showMoneyByPeriod(Calendar c1, Calendar c5) {
		orderservice.showMoneyByPeriod(c1,c5);
		
	}

	public void viewBookDescription(String title, String author) {
		bservice.viewBookDescription(title, author);
		
	}

	public void viewOrderDetail(String title, String author) {
		orderservice.viewOrderDetail(title, author);
		
	}

	public void showUnwantedBooksByArrDate() {
		Comparator<IBook> byArrDate = new BookByArrivalDate();
		bservice.showUnwantedBooks(byArrDate);
	}

	public void showUnwantedBooksByPrice() {
		Comparator<IBook> byPrice = new BookByPriceComparator();
		bservice.showUnwantedBooks(byPrice);
	}

	public void showPreordersByBook() {
		Comparator<IBook> byTitle = new BookByTitleComp();
		bservice.showPreorders(byTitle);
		
	}

	public void showPreordersByNumber() {
		Comparator<IBook> byNumber = new BookByPreordersNumberComp();
		bservice.showPreorders(byNumber);
		
	}

	
	
	
	
	
}

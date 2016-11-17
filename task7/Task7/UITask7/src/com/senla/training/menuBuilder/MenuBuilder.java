package com.senla.training.menuBuilder;

import java.util.ArrayList;

import com.senla.training.booksMenu.AddBook;
import com.senla.training.booksMenu.ExportBooksToCsv;
import com.senla.training.booksMenu.ImportBooksFromCsv;
import com.senla.training.booksMenu.RemoveBook;
import com.senla.training.booksMenu.SortByInStock;
import com.senla.training.booksMenu.SortByPrice;
import com.senla.training.booksMenu.SortByPublDate;
import com.senla.training.booksMenu.SortByTitle;
import com.senla.training.booksMenu.SortUnwantedByArrDate;
import com.senla.training.booksMenu.SortUnwantedByPrice;
import com.senla.training.booksMenu.ViewDescription;
import com.senla.training.interfaces.IMenu;
import com.senla.training.interfaces.IMenuBuilder;
import com.senla.training.interfaces.IMenuItem;
import com.senla.training.menu.Menu;
import com.senla.training.ordersMenu.AddOrder;
import com.senla.training.ordersMenu.CancelOrder;
import com.senla.training.ordersMenu.CloneOrder;
import com.senla.training.ordersMenu.CountExecOrdersByPeriod;
import com.senla.training.ordersMenu.CountExecOrdersMoney;
import com.senla.training.ordersMenu.ExecuteOrder;
import com.senla.training.ordersMenu.ExportOrdersToCsv;
import com.senla.training.ordersMenu.ImportOrdersFromCsv;
import com.senla.training.ordersMenu.SortAllByDate;
import com.senla.training.ordersMenu.SortAllByPrice;
import com.senla.training.ordersMenu.SortAllByStatus;
import com.senla.training.ordersMenu.SortExecOrdersByDate;
import com.senla.training.ordersMenu.SortExecOrdersByPrice;
import com.senla.training.ordersMenu.ViewOrderDetail;
import com.senla.training.preordersMenu.AddPreorder;
import com.senla.training.preordersMenu.ExportPreordersToCsv;
import com.senla.training.preordersMenu.ImportPreordersFromCsv;
import com.senla.training.preordersMenu.SortPreordByBook;
import com.senla.training.preordersMenu.SortPreordByNumber;

public class MenuBuilder implements IMenuBuilder {
	
	private static final String EXPORT_PREORDERS_TO_CSV = "Export Preorders To CSV";
	private static final String IMPORT_PREORDERS_FROM_CSV = "Import Preorders From CSV";
	private static final String EXPORT_BOOKS_TO_CSV = "Export Books to CSV";
	private static final String IMPORT_BOOKS_FROM_CSV = "Import Books From CSV";
	private static final String EXPORT_ORDERS_TO_CSV = "Export Orders to CSV";
	private static final String IMPORT_ORDERS_FROM_CSV = "Import Orders From CSV";
	private static final String CLONE_ORDER = "Clone Order";
	private static final String VIEW_ORDER_DETAIL = "View Order Detail";
	private static final String CANCEL_ORDER = "Cancel Order";
	private static final String EXECUTE_ORDER = "Execute Order";
	private static final String VIEW_DESCRIPTION_OF_A_BOOK = "View Description of a Book";
	private static final String REMOVE_BOOK = "Remove Book";
	private static final String ADD_ORDER = "Add Order";
	private static final String SHOW_PRE_ORDERS = "Show pre-orders";
	private static final String SHOW_ORDERS_BY_PERIOD = "Show orders by period";
	private static final String SHOW_ALL_ORDERS = "Show All Orders";
	private static final String BACK = "Back";
	private static final String SHOW_UNWANTED_BOOKS = "Show Unwanted Books";
	private static final String SHOW_ALL_BOOKS = "Show All Books";
	private static final String EXIT = "Exit";
	private static final String PREORDERS_MENU = "Preorders Menu";
	private static final String ORDERS_MENU = "Orders Menu";
	private static final String BOOKS_MENU = "Books Menu";
	private static final String MAIN_MENU = "Main Menu";
	private static final String SORT_PREORD_BY_NUMBER = "SortPreordByNumber";
	private static final String SORT_PREORD_BY_BOOK = "SortPreordByBook";
	private static final String ADD_PREORDER = "AddPreorder";
	private static final String SORT_EXEC_ORDERS_BY_PRICE = "SortExecOrdersByPrice";
	private static final String SORT_EXEC_ORDERS_BY_DATE = "SortExecOrdersByDate";
	private static final String COUNT_EXEC_ORDERS_MONEY = "CountExecOrdersMoney";
	private static final String COUNT_EXEC_ORDERS_BY_PERIOD = "CountExecOrdersByPeriod";
	private static final String SORT_ALL_BY_STATUS = "SortAllByStatus";
	private static final String SORT_ALL_BY_PRICE = "SortAllByPrice";
	private static final String SORT_ALL_BY_DATE = "SortAllByDate";
	private static final String ADD_BOOK = "Add Book";
	private static final String SORT_UNWANTED_BY_PRICE = "SortUnwantedByPrice";
	private static final String SORT_UNWANTED_BY_ARR_DATE = "SortUnwantedByArrDate";
	private static final String SORT_BY_PUBL_DATE = "SortByPublDate";
	private static final String SORT_BY_IN_STOCK = "SortByInStock";
	private static final String SORT_BY_PRICE = "Sort By Price";
	private static final String SORT_BY_TITLE = "Sort By Title";
	private  ArrayList<IMenuItem> mainMenu = new ArrayList<IMenuItem>();
	private  ArrayList<IMenuItem> booksMenu = new ArrayList<IMenuItem>();
	private  ArrayList<IMenuItem> showBooks = new ArrayList<IMenuItem>();
	private  ArrayList<IMenuItem> showUnwantedBooks = new ArrayList<IMenuItem>();
	private  ArrayList<IMenuItem> ordersMenu = new ArrayList<IMenuItem>();
	private  ArrayList<IMenuItem> showOrdersMenu = new ArrayList<IMenuItem>();
	private  ArrayList<IMenuItem> showExecOrdersMenu = new ArrayList<IMenuItem>();
	private  ArrayList<IMenuItem> preordersMenu = new ArrayList<IMenuItem>();
	private  ArrayList<IMenuItem> showPreorders = new ArrayList<IMenuItem>();
	
	
	private  IMenu MainMenu = new Menu(MAIN_MENU, 1);
	
	private  IMenu BooksMenu = new Menu(BOOKS_MENU, 1);
	private   IMenu OrdersMenu = new Menu(ORDERS_MENU, 2);
	private   IMenu PreordersMenu = new Menu(PREORDERS_MENU, 3);
	private   IMenu Exit = new Menu(EXIT, 4);

	private   IMenu ShowBooks = new Menu(SHOW_ALL_BOOKS, 1);
	private   IMenu ShowUnwantedBooks = new Menu(SHOW_UNWANTED_BOOKS, 2);
	private   IMenu BackBooks = new Menu(BACK, 8);
	
	private   IMenu BackShowBooks = new Menu(BACK, 5);
	private   IMenu BackShowUnwantedBooks = new Menu(BACK, 3);

	private   IMenu ShowAllOrders = new Menu(SHOW_ALL_ORDERS, 1);
	private   IMenu ShowExecutedOrdersByPeriod = new Menu(SHOW_ORDERS_BY_PERIOD, 2);
	private   IMenu BackOrders = new Menu(BACK, 10);
	private   IMenu BackShowOrders = new Menu(BACK, 4);
	private   IMenu BackShowExecOrders = new Menu(BACK, 5);

	private   IMenu ShowPreorders = new Menu(SHOW_PRE_ORDERS, 1);
	private   IMenu BackPreorders = new Menu(BACK, 5);
	private   IMenu BackShowPreorders = new Menu(BACK, 3);

	public IMenu build() {

		// MainMenu
		mainMenu.add(BooksMenu); 
		mainMenu.add(OrdersMenu);
		mainMenu.add(PreordersMenu);
		mainMenu.add(Exit);
		MainMenu.setMenu(mainMenu);

		// BooksMenu
		booksMenu.add(ShowBooks);
		booksMenu.add(ShowUnwantedBooks);
		booksMenu.add(new AddBook(ADD_BOOK, 3));
		booksMenu.add(new RemoveBook(REMOVE_BOOK, 4));
		booksMenu.add(new ViewDescription(VIEW_DESCRIPTION_OF_A_BOOK, 5));
		booksMenu.add(new ImportBooksFromCsv(IMPORT_BOOKS_FROM_CSV, 6));
		booksMenu.add(new ExportBooksToCsv(EXPORT_BOOKS_TO_CSV, 7));
		BackBooks.setMenu(mainMenu);// Back
		booksMenu.add(BackBooks);
		BooksMenu.setMenu(booksMenu);

		// submenu
		showBooks.add(new SortByTitle(SORT_BY_TITLE, 1));
		showBooks.add(new SortByPrice (SORT_BY_PRICE, 2));
		showBooks.add(new SortByInStock(SORT_BY_IN_STOCK, 3));
		showBooks.add(new SortByPublDate(SORT_BY_PUBL_DATE, 4));
		
		BackShowBooks.setMenu(booksMenu);// Back
		showBooks.add(BackShowBooks);
		ShowBooks.setMenu(showBooks);
		// submenu
		showUnwantedBooks.add(new SortUnwantedByArrDate(SORT_UNWANTED_BY_ARR_DATE, 1));
		showUnwantedBooks.add(new SortUnwantedByPrice(SORT_UNWANTED_BY_PRICE, 2));
		BackShowUnwantedBooks.setMenu(booksMenu);// Back
		showUnwantedBooks.add(BackShowUnwantedBooks);
		ShowUnwantedBooks.setMenu(showUnwantedBooks);
		
		// OrdersMenu
		ordersMenu.add(ShowAllOrders);
		ordersMenu.add(ShowExecutedOrdersByPeriod);
		ordersMenu.add(new AddOrder(ADD_ORDER, 3));
		ordersMenu.add(new ExecuteOrder(EXECUTE_ORDER, 4));
		ordersMenu.add(new CancelOrder(CANCEL_ORDER, 5));
		ordersMenu.add(new ViewOrderDetail(VIEW_ORDER_DETAIL, 6));
		ordersMenu.add(new CloneOrder(CLONE_ORDER, 7));
		ordersMenu.add(new ImportOrdersFromCsv(IMPORT_ORDERS_FROM_CSV, 8));
		ordersMenu.add(new ExportOrdersToCsv(EXPORT_ORDERS_TO_CSV, 9));
		BackOrders.setMenu(mainMenu);
		ordersMenu.add(BackOrders);
		OrdersMenu.setMenu(ordersMenu);
		// submenu
		showOrdersMenu.add(new SortAllByDate(SORT_ALL_BY_DATE, 1));
		showOrdersMenu.add(new SortAllByPrice(SORT_ALL_BY_PRICE, 2));
		showOrdersMenu.add(new SortAllByStatus(SORT_ALL_BY_STATUS, 3));
		BackShowOrders.setMenu(ordersMenu);// Back
		showOrdersMenu.add(BackShowOrders);
		ShowAllOrders.setMenu(showOrdersMenu);
		// submenu
		showExecOrdersMenu.add(new CountExecOrdersByPeriod(COUNT_EXEC_ORDERS_BY_PERIOD, 1));
		showExecOrdersMenu.add(new CountExecOrdersMoney(COUNT_EXEC_ORDERS_MONEY, 2));
		showExecOrdersMenu.add(new SortExecOrdersByDate(SORT_EXEC_ORDERS_BY_DATE, 3));
		showExecOrdersMenu.add(new SortExecOrdersByPrice(SORT_EXEC_ORDERS_BY_PRICE, 4));
		BackShowExecOrders.setMenu(ordersMenu);// Back
		showExecOrdersMenu.add(BackShowExecOrders);
		ShowExecutedOrdersByPeriod.setMenu(showExecOrdersMenu);

		// PreordersMenu
		preordersMenu.add(ShowPreorders);
		preordersMenu.add(new AddPreorder(ADD_PREORDER, 2));
		preordersMenu.add(new ImportPreordersFromCsv(IMPORT_PREORDERS_FROM_CSV, 3));
		preordersMenu.add(new ExportPreordersToCsv(EXPORT_PREORDERS_TO_CSV, 4));
		BackPreorders.setMenu(mainMenu);// Back
		preordersMenu.add(BackPreorders);
		PreordersMenu.setMenu(preordersMenu);
		// submenu
		showPreorders.add(new SortPreordByBook(SORT_PREORD_BY_BOOK, 1));
		showPreorders.add(new SortPreordByNumber(SORT_PREORD_BY_NUMBER, 2));
		BackShowPreorders.setMenu(preordersMenu);// Back
		showPreorders.add(BackShowPreorders);
		ShowPreorders.setMenu(showPreorders);

		return MainMenu;
	}

}

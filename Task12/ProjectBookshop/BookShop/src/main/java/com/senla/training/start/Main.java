package com.senla.training.start;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.senla.training.DI.DI;
import com.senla.training.interfaces.IFacade;

public class Main {

	public static void main(String[] args) {

		IFacade facade = (IFacade) DI.load(IFacade.class);
		facade.init();

		//System.out.println(facade.addBook("check1", "check1", (GregorianCalendar) Calendar.getInstance(), Status.NOTINSTOCK, 2,(GregorianCalendar) Calendar.getInstance(), "check1"));
		//facade.viewBookDescription(1);
		//System.out.println(facade.showAllBooksSortByPrice());
		//System.out.println(facade.cloneOrder(1));
		//facade.removeBook(3);
		//System.out.println(facade.executeOrder(1));
		//System.out.println(facade.executeOrder(2));
		GregorianCalendar date =  (GregorianCalendar) Calendar.getInstance();
		date.add(Calendar.YEAR, -1);
		//System.out.println(facade.showExecOrdersByPeriodSortByPrice(date, (GregorianCalendar) Calendar.getInstance()));
	List<Integer> books = new ArrayList<>();
	books.add(11);
	books.add(11);
	//books.add(8);
	//books.add(9);
		
		//System.out.println(facade.addOrder("Jackie Chan",date,books));
	System.out.println(facade.showPreordersByBook());
	//System.out.println(fa cade.showPreordersByNumber());
	//System.out.println(facade.addPreorder(11));
	//System.out.println(facade.addPreorder(6));
	//System.out.println(facade.addPreorder(7));
		//facade.writePreordersToCsv();
		//facade.readPreordersFromCsv();
		//System.out.println(facade.writeOrdersToCsv());
		//System.out.println(facade.readOrdersFromCsv());
	//System.out.println(facade.writeBooksToCsv());
	//System.out.println(facade.readBooksFromCsv());
		//System.out.println(facade.deleteBook(5));
		//System.out.println(facade.deleteOrder(1));
	//facade.addPreorder(4);
	//facade.addPreorder(4);
		//System.out.println(facade.addOrder("misha", date, books));
	//System.out.println(facade.deleteBook(8));
	//System.out.println(facade.viewOrderDetail(2));
	//System.out.println(facade.setBookStockStatus(8, Status.INSTOCK));
	//System.out.println(facade.deleteOrder(1));
	
		
	}

}
 
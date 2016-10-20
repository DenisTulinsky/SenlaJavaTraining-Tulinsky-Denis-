package com.senla.training.start;

import java.util.Calendar;

import com.senla.training.facade.Facade;

public class Main {

	public static void main(String[] args) {
		// String[] args1 = {"path1",p2,p3};
		Facade facade = new Facade();
		// facade.init(args1);
		facade.init();
		// facade.addBook("A dog", "Henry", Year.of(2003), true, 3,
		// Calendar.getInstance(), "Story");
		// facade.addBook("The Man", "Miller", Year.of(2002), true, 2,
		// Calendar.getInstance(), "Novel");

		Calendar c1 = Calendar.getInstance();
		c1.set(2016, Calendar.SEPTEMBER, 1);

		Calendar c2 = Calendar.getInstance();
		c2.set(2002, Calendar.JANUARY, 1);

		Calendar c3 = Calendar.getInstance();
		c3.set(2003, Calendar.JANUARY, 1);

		Calendar c4 = Calendar.getInstance();
		c4.set(2004, Calendar.JANUARY, 1);

		Calendar c5 = Calendar.getInstance();
		c5.set(2020, Calendar.JANUARY, 1);
		// facade.readFromFile();
		// System.out.println("------------");
		// facade.removeBook("The Man", "Miller", Year.of(2002), true, 2,
		// Calendar.getInstance(), "Novel");

		// facade.addPreorder("Fiction", "Jones", cal);
		// facade.showAllBooks();
		

		// facade.showAllBooks();
		// facade.showAllPreorders();
		// System.out.println("------------");
		// facade.removeBook("Fiction", "Jones",cal ,true, 4,
		// Calendar.getInstance(), "Novel");
		// facade.readFromFile();

		facade.addBook("The Man1", "Miller", c1, true, 2, c2, "Stodsfsery");
		facade.addOrder("The Man1", "Miller", "Frank Zappa", 2, c2);
		facade.executeOrder("The Man1", "Miller", "Frank Zappa", 2, c2);

		facade.addBook("The Man", "Miller", c1, true, 3, c3, "Stodsfsery");
		facade.addOrder("The Man", "Miller", "Frank Zappa", 3, c3);
		facade.executeOrder("The Man", "Miller", "Frank Zappa", 3, c3);

		facade.addBook("Fiction", "Jones", c1, true, 4, c4, "Story");
		facade.addOrder("Fiction", "Jones", "Frank Zappa", 4, c4);
		//facade.executeOrder("Fiction", "Jones", "Frank Zappa", 4, c4);
		facade.cancelOrder("Fiction", "Jones", "Frank Zappa", 4, c4);
		
		facade.addPreorder("Fiction", "Jones", c1);
		facade.addPreorder("Fiction", "Jones", c1);
		facade.addPreorder("The Man", "Miller", c1);
		System.out.println("------------");
		facade.showOrdersByDate();
		//facade.showPreordersByBook();
		facade.showPreordersByNumber();
		
		//facade.showUnwantedBooksByArrDate();
		//facade.showUnwantedBooksByPrice();
		//facade.viewBookDescription("Fiction","Jones");
		//facade.viewOrderDetail("The Man", "Miller");
		//facade.showMoneyByPeriod(c1,c5);
		
		// facade.showExecOrdersByPeriodSortByDate(c1,c5);
		// facade.showExecOrdersByPeriodSortByPrice(c1,c5);
		// facade.showOrdersByDate();
		// facade.showOrdersByPrice();
		 //facade.showOrdersByStatus();
		 //facade.showExecOrdersCount(c1, c5);

		// facade.showAllBooksSortByTitle();
		// facade.showAllBooksSortByPrice();
		// facade.showAllBooksSortByInStock();
		 //facade.showAllBooksSortByPublDate();
   
		System.out.println("------------");
		// facade.addOrder("Fiction", "Jones","Frank Zappa",4, cal);
		// facade.showAllOrders();
		// facade.showAllOrders();

		// facade.executeOrder("Fiction", "Jones","Frank Zappa",4, cal);
		 //facade.cancelOrder("Fiction", "Jones", "Frank Zappa", 4, c4);
		// facade.writeToFile();

		facade.writeToFile();
		// facade.showAllPreorders();
		// facade.showExecOrdersByPeriodSortByDate(cal3,cal4);
	}

}

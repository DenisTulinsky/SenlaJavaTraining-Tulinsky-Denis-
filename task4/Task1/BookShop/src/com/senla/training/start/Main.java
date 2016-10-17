package com.senla.training.start;

import java.time.Year;
import java.util.Calendar;

import com.danco.training.TextFileWorker;
import com.senla.training.facade.Facade;

public class Main {

	public static void main(String[] args) {
		Facade facade = new Facade();
        facade.init();
        facade.addBook("A dog", "Henry", Year.of(2003), true, 3, Calendar.getInstance(), "Story");
        
        //facade.writeToFile();
       // facade.readFromFile();
        facade.showAllBooks();
        
	}

}

package com.senla.training.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IMenuItem;

public class Printer {
	private static final Logger log = Logger.getLogger(Printer.class);

	public static void printMenu(ArrayList<IMenuItem> a) {
		Integer j = 1;
		for (IMenuItem i : a) {
			if (i.getId() == 0) {
				System.out.println("0. " + i.getName());
			} else {
				System.out.println(j + ". " + i.getName());
				j++;
			}
		}
	}

	public static void printString(String s) {
		System.out.println(s);
	}

	public static void printList(List<String> results) {
		try {
			for (String s : results) {
				System.out.println(s);
			}
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			System.out.println("No object was received from the Server");
		}

	}

	public static void printInteger(Integer integer) {

		System.out.println(integer);

	}
}

package com.senla.training.services;

import java.util.Calendar;
import java.util.List;

import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.interfaces.IPrinter;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Preorder;

public class PreorderService implements IPreorderService {
	private IStorage storage;
	private IPrinter printer;

	public PreorderService(IStorage storage, IPrinter printer) {
		this.storage = storage;
		this.printer = printer;
	}

	@Override
	public void addPreorder(String title, String author, Calendar publishedDate) {
		IPreorder preorder = new Preorder(title, author, publishedDate.getTime());
		storage.addPreorder(preorder);
	}

	public void checkPreorders(String title, String author, Calendar publishedDate) {
		storage.checkPreorder(title, author, publishedDate.getTime());

	}

	@Override
	public void showAllPreorders() {

		List<IPreorder> allPreorders = storage.getAllPreorders();
		for (IPreorder p : allPreorders) {
			printer.print(p);
		}
	}

}

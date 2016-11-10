package com.senla.training.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.interfaces.IStorage;
import com.senla.training.status.Status;

public class PreorderService implements IPreorderService {
	private IStorage storage;
	private IConverterReadableString converterToString;

	public PreorderService(IStorage storage, IConverterReadableString converterToString) {
		this.storage = storage;
		this.converterToString = converterToString;
	}

	@Override
	public void addPreorder(IPreorder preorder) {
		List<IPreorder> allPreorders = storage.getAllPreorders();
		Boolean nonexisting = true;

		for (IPreorder p : allPreorders) {
			if (p.equals(preorder)) {
				nonexisting = false;
				int total = p.getCount() + 1;
				p.setCount(total);
			}
		}
		if (nonexisting) {
			preorder.setStatus(Status.ACTIVE);
			preorder.setCount(1);
			storage.addPreorder(preorder);
		}
	}

	public void checkPreorders(String title, String author) {
		List<IPreorder> allPreorders = storage.getAllPreorders();
		for (IPreorder preorder : allPreorders) {
			if (preorder != null && preorder.getTitle().equals(title) && preorder.getAuthor().equals(author)) {
				preorder.setStatus(Status.EXECUTED);
				break;
			}
		}
	}

	@Override
	public List<String> showAllPreorders(Comparator<IPreorder> com) {

		List<IPreorder> allPreorders = storage.getAllPreorders();
		List<String> result = new ArrayList<>();
		Collections.sort(allPreorders, com);
		for (IPreorder p : allPreorders) {

			result.add(converterToString.convert(p));
		}
		return result;
	}
}

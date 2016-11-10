package com.senla.training.interfaces;

import java.util.Comparator;
import java.util.List;

public interface IPreorderService {

	public void addPreorder(IPreorder preorder);

	public void checkPreorders(String title, String author);

	public List<String> showAllPreorders(Comparator<IPreorder> com);

}

package com.senla.training.interfaces;

import java.util.Calendar;

public interface IPreorderService {

	public void addPreorder(String title, String author, Calendar publishedDate);

	public void checkPreorders(String title, String author, Calendar publishedDate);

	public void showAllPreorders();

}

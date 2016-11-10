package com.senla.training.interfaces;

import java.util.ArrayList;



public interface IMenu extends IMenuItem {

	public ArrayList<IMenuItem> getMenu();
	
	public void setMenu(ArrayList<IMenuItem> menu);

	String getName();

	Integer getId();
}

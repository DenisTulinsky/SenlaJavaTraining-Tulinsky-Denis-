package com.senla.training.menu;

import java.util.ArrayList;

import com.senla.training.interfaces.IMenu;
import com.senla.training.interfaces.IMenuItem;


public class Menu implements IMenu {
	
	
	private  ArrayList<IMenuItem> menu;
	private String name;
	private  Integer id;;
	
	
	public Menu(String name, Integer id) {
		this.name = name;
		this.id = id;
		
	}
	
	
	
	public void setMenu(ArrayList<IMenuItem> menu){
		this.menu=menu;
		
	}
	
	public ArrayList<IMenuItem> getMenu(){
		return menu;
	}

	

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public Integer getId() {
		
		return id;
	}



	
	

}

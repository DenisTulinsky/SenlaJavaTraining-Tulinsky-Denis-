package com.senla.training.abstractClass;

import com.senla.training.interfaces.IAction;

public abstract class AbstrAction implements IAction {

	public String name;
	public Integer id;
	
	public AbstrAction (String name, Integer id) {
		this.name = name;
		this.id = id;
	}
		
	@Override
	public String getName() {

		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer getId() {
		return this.id;
	}
	
		
	@Override
	public void setId(Integer id) {
		this.id = id;
}

	}
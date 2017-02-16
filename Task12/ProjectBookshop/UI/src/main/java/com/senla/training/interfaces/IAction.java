package com.senla.training.interfaces;

import com.senla.training.tools.Transmitter;

public interface IAction extends IMenuItem {

	public void action(Transmitter transmitter, IInputReader input);

	public String getName();

	public Integer getId();

	public void setId(Integer id);
	
	
}
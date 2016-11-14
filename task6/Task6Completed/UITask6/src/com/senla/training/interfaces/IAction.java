package com.senla.training.interfaces;

import com.senla.training.tools.InputReader;

public interface IAction extends IMenuItem {

	public void action(IFacade facade, InputReader input);

	public String getName();

	public Integer getId();

	public void setId(Integer id);

}
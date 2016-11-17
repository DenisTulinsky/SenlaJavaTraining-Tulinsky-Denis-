package com.senla.training.interfaces;

public interface IAction extends IMenuItem {

	public void action(IFacade facade, IInputReader input);

	public String getName();

	public Integer getId();

	public void setId(Integer id);

}
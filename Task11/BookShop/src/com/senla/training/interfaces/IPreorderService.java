package com.senla.training.interfaces;

import java.util.List;

public interface IPreorderService {

	public boolean addPreorder(IPreorder preorder);

	public boolean checkPreorders(String id);

	public List<String> showAllPreorders(String sortBy);

}

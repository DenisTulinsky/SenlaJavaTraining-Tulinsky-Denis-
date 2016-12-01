package com.senla.training.addidionalActions;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.interfaces.IMenuItem;
import com.senla.training.tools.Transmitter;

public class Exit extends AbstrAction implements IMenuItem {

	public Exit(String name, Integer id) {
		super(name, id);
		
	}

	@Override
	public void action(Transmitter trans, IInputReader input) {
		
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		
		trans.toServer(map);
	}

}

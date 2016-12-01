package com.senla.training.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;
import com.senla.training.interfaces.IFacade;
/**
 * Provides instruments for reading method name and parameters from a map and invoking the method.
 * 
 * @author Denis
 *
 */
public class MethodInvoker {
	private IFacade facade;
	private final org.apache.log4j.Logger log = Logger.getLogger(MethodInvoker.class);

	public MethodInvoker(IFacade facade) {
		this.facade = facade;
	}
	/**
	 * Reads method name from the map, reads arguments from the map,
	 *  gets all the methods of facade and invokes the method with arguments if the name matches
	 *
	 * @param Map<String, Object[]> 
	 * @return Object
	 * @throws IllegalArgumentException,IllegalAccessException
	 *             InvocationTargetException,ClassNotFoundException
	 */
	public Object implement(Map<String, Object[]> map) {
		Object obj = null;
		try {
			Object[] parameters = null;

			Object[] nameAsObject = map.get("methodName");
			String nameFromMap = (String) nameAsObject[0];

			if (map.get("params") != null) {
				parameters = map.get("params");
			}

			Class<?> cl = Class.forName("com.senla.training.facade.Facade");
			Method[] allMethods = cl.getDeclaredMethods();

			for (Method m : allMethods) {
				if (m.getName().equals(nameFromMap)) {
					obj = m.invoke(facade, parameters);
				}
			}
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (InvocationTargetException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
		return obj;

	}
}

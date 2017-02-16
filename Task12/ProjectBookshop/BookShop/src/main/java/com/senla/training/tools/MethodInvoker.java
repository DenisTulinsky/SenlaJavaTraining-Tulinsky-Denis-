package com.senla.training.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IFacade;
import com.senla.training.requestApi.Request;
/**
 * Provides instruments for reading method name and parameters from a Request object and invoking the method.
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
	 * Reads method name from the Request object, reads arguments from the Request object,
	 *  gets all the methods of facade and invokes the method with arguments if the name matches
	 *
	 * @param Request object 
	 * @return Object
	 * @throws IllegalArgumentException,IllegalAccessException
	 *             InvocationTargetException,ClassNotFoundException
	 */
	
	public Object invoke(Request request) {
		Object obj = null;
		try {
			Object[] parameters = null;
			if (request.getParams() != null) {
				parameters = request.getParams();
			}

			Class<?> cl = Class.forName("com.senla.training.facade.Facade");
			
			Method[] allMethods = cl.getDeclaredMethods();
			for (Method m : allMethods) {
				if (m.getName().equals(request.getMethodName())) {
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

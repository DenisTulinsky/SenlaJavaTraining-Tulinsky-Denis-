package com.senla.training.DI;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.properties.PropertyFactory;
/**
 * Class for Dependency Injection 
 * 
 * @author Denis
 *
 */
public class DI {

	
	private static Map<String, Object> mapDI = new HashMap<String, Object>();
	
	/**
	 * Checks if object of the Class<T> is in the map, returns object. 
	 * If not in the map, instantiates a new object
	 * and puts it in the map, returns object.
	 *
	 * @param Class<T> 
	 * @return
	 *  @throws  InstantiationException,IllegalAccessException,ClassNotFoundException 
	 */
	public static <T> Object load(Class<T> cl, Object... constructorParameters) {

		Object obj = null;
		
		if (mapDI.containsKey(cl.getName())) {
			return mapDI.get(cl.getName());

		} else {
			try {
				Class<?> c = Class.forName(PropertyFactory.getProps().getValue(cl.getName()));
								
				 Class<?>[] parameterTypes = new Class[constructorParameters.length];
				    for(int i = 0; i < constructorParameters.length; i++) {
				        parameterTypes[i] = constructorParameters[i].getClass();
				    }
				    
				    
				    
				obj = c.getDeclaredConstructor(parameterTypes).newInstance(constructorParameters);
				mapDI.put(cl.getName(), obj);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
}

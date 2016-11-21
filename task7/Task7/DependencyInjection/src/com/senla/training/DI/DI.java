package com.senla.training.DI;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.senla.training.properties.PropertyFactory;
/**
 * Class provides instruments for Dependencies Injection 
 * 
 * @author Denis
 *
 */
public class DI {

	
	private static Map<String, Object> mapDI = new HashMap<String, Object>();
	
	/**
	 * Checks if Checks if object of the Class<?> interface is in the map, returns object. 
	 * If not in the map, goes to prop.configuration, finds corresponding class, instantiates a new object
	 * and puts it in the map.
	 *
	 * @param Class<T> receives an interface
	 * @return new instance of the class
	 *  @throws  InstantiationException,IllegalAccessException,ClassNotFoundException 
	 */
	public static Object load(Class<?> cl, Object... constructorParameters) {

		Object obj = null;
		
		if (mapDI.containsKey(cl.getName())) {
			return mapDI.get(cl.getName());

		} else {
			try {
				Class<?> c = Class.forName(PropertyFactory.getProps().getValue(cl.getName()));
				
				 Constructor<?>[] constructors = c.getConstructors();
				 Class<?>[] parameterTypes = new Class[constructorParameters.length];
				 for(Constructor<?> constructor:constructors){
					 parameterTypes = constructor.getParameterTypes();
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

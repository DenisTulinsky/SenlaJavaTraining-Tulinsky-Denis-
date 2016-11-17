package com.senla.training.annotationsWorker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.senla.training.annotations.Printable;
import com.senla.training.annotations.PrintableObject;
import com.senla.training.annotations.PrintableRef;

/**
 * Class for printing out annotated objects.
 * 
 * @author Denis
 *
 */
public class AnnotationsWorker {
	/**
	 * Receives Map. Prints entries from the Map
	 *
	 * @param Map<Integer,
	 *            String>
	 * @throws llegalArgumentException,
	 *             IllegalAccessException
	 */
	public static void print(Object obj) throws IllegalArgumentException, IllegalAccessException {

		Map<Integer, String> map = getMap(obj);

		for (Entry<Integer, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	/**
	 * Puts annotated fields names and values in the map. Returns Map
	 *
	 * @param Object   
	 * @return Map<Integer, String>
	 * @throws llegalArgumentException,
	 *             IllegalAccessException
	 */
	public static Map<Integer, String> getMap(Object obj) throws IllegalArgumentException, IllegalAccessException {

		Map<Integer, String> map = new HashMap<Integer, String>();

		Class<? extends Object> cl = obj.getClass();

		if (cl.isAnnotationPresent(PrintableObject.class)) {
			for (Field field : cl.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Printable.class)) {

					Annotation annotation = field.getAnnotation(Printable.class);
					Printable printableAnn = (Printable) annotation;
					if (printableAnn.isDetailedOnly()) {

						map.put(printableAnn.order(),
								printableAnn.name() + ": " + String.valueOf(field.get(obj)) + " ");
					}

				} else if (field.isAnnotationPresent(PrintableRef.class)) {

					Annotation annotation = field.getAnnotation(PrintableRef.class);
					PrintableRef printableAnn = (PrintableRef) annotation;
					if (printableAnn.isDetailedView()) {

						map.put(printableAnn.order(),
								printableAnn.name() + ": " + String.valueOf(getMap(field.get(obj))) + " ");
					}
				}
			}
		}
		return map;
	}
}

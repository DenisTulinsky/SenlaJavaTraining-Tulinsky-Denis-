package com.senla.training.annotationsWorker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	 * Receives object, invokes getMap() method to get a map of object's fields.
	 * Returns List<String>.
	 *
	 * @param Object
	 * @return List<String>
	 * @throws llegalArgumentException,
	 *             IllegalAccessException
	 */
	public static List<String> createAnnotation(Object obj) throws IllegalArgumentException, IllegalAccessException {

		Map<Integer, String> map = getMap(obj);
		List<String> results = new ArrayList<>();

		for (Entry<Integer, String> entry : map.entrySet()) {
			results.add(entry.getValue());
		}
		return results;
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
								printableAnn.name() + ": " + String.valueOf(createAnnotation(field.get(obj))) + " ");
					}
					if (!printableAnn.isDetailedView()) {
						@SuppressWarnings("unchecked")
						Set<Object> set = new HashSet<Object>((Set<Object>) field.get(obj));
						List<String> strings = new ArrayList<>();
						Integer number = set.size();
						for (Object o : set) {

							strings.add(String.valueOf(createAnnotation(o)) + " || ");
						}
						map.put(printableAnn.order(), printableAnn.name() + ": " + number + " " + strings + " ");
					}

				}
			}
		}
		return map;
	}
}

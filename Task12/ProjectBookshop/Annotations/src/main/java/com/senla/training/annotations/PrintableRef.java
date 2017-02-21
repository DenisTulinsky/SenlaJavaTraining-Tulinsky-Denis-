package com.senla.training.annotations;

import java.lang.annotation.*;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PrintableRef {
	String name();

	boolean isDetailedView();

	int order();

	boolean isRecursive() default false;
}

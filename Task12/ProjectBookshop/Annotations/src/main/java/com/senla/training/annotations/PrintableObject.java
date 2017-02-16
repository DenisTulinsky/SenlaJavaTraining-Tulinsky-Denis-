package com.senla.training.annotations;
import java.lang.annotation.*;
@Target(value=ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface PrintableObject {
	 public String name(); 
}

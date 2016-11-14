package com.senla.training.tools;


import java.util.ArrayList;
import java.util.List;

import com.senla.training.interfaces.IMenuItem;

public class Printer {
	
	
	public static void printMenu(ArrayList<IMenuItem> a){
		Integer j=1;
		for(IMenuItem i:a){
			if(i.getId()==0){
				System.out.println("0. "+i.getName());
			}else{
			System.out.println(j+". "+i.getName());
			j++;
			}
		}
	}
	
	public static void printString(String s){
		System.out.println(s);
	}
	
	
	public static void printList(List<String> results){
		for(String s:results){
			System.out.println(s);
		}
	}
	
	public static void printInteger(Integer integer){
			
			System.out.println(integer);
			
	}
		}


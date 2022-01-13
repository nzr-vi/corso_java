package it.jaita52.exercise.util;

public class Extensions {
	public static <T> void printCollection(Iterable<T> collection){		
		StringBuilder array_printer = new StringBuilder();
		int index = 1;
		for(T object : collection)
		{			
			array_printer.append("(id:"+index+" "+object.toString()+")\n");
			index++;
		}
		System.out.println(array_printer.toString());
	}
}

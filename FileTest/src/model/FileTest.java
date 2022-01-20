package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileTest {
	public static void main (String[] args) {
		
		try {
			
			FileInputStream fi = new FileInputStream("src/test.txt");
			System.out.println(fi.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

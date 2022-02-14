package controller;

import model.InputFormData;

public class InputFormDataFactory {
	
	public static InputFormData create(String value) {
		return create("text",value);
	}
	
	public static InputFormData create(String type, String value) {
		var ifd = new InputFormData();
		ifd.setType(type);
		ifd.setValue(value);
		return ifd;
	}
	
	public static InputFormData create(String type, int value) {
		return create(type,Integer.toString(value));
	}
	
	public static InputFormData create(String type, double value) {
		return create(type,Double.toString(value));
	}
	
	
}

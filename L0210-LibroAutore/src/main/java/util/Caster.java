package util;

import exception.NullCastException;
import exception.WrongTypeException;

public class Caster {

	public static <T extends Object> T tryCastTo(Class<T> cls, Object obj){
		try {
			return castTo(cls, obj);
		} catch (NullCastException | WrongTypeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends Object> T castTo(Class<T> cls, Object obj) throws NullCastException, WrongTypeException{
		
		if(obj == null)
			throw new NullCastException();
		if(obj.getClass().equals(cls))
			return (T)obj;
		else
			throw new WrongTypeException();
	}
	
	public static <T extends Object> boolean safeIsType(Class<T> cls, Object obj) {
		if(obj != null)
			return (obj.getClass().equals(cls));
		return false;
	}
	
}

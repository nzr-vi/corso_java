package model;

import exceptions.CalcolatriceException;

public interface Operazione {
	public Double calcola(Double a, Double b) throws CalcolatriceException ;
}

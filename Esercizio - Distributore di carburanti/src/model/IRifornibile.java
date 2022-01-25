package model;

interface IRifornibile {
	String tipoRifornimento();
	void variazione(long amount)throws DistributoreException;
}

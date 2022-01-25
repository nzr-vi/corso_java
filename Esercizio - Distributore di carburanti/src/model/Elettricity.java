package model;

class Elettricity implements IRifornibile {

	@Override
	public String tipoRifornimento() {
		return "Elettricity";
	}

	@Override
	public void variazione(long amount) throws DistributoreException {
		if (amount < 0)
			throw new DistributoreException("Can't put any elettricity into the gas pump");
		return;
	}
}

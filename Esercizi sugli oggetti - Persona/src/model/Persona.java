package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Persona {
	protected String name,surname;
	protected final Date birthDate;
	protected Date deathDate;
	
	public Persona(String name, String surname, String birth) throws ParseException {
		this.name =name;
		this.surname =surname;
		
		SimpleDateFormat birthFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.birthDate = birthFormat.parse(birth);
	}
	
	public int age() {
		Date last = this.deathDate!=null? this.deathDate:new Date();
		Period elapsed = Period.between(
				this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				last.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		return elapsed.getYears();
	}

	public String dettaglio() {
		return "Persona [name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", deathDate="
				+ deathDate + "]";
	}
	
	
	
}

package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Persona {
	protected String name,surname;
	protected final Date birthDate;
	protected Date deathDate = null;
	
	public Persona(String name, String surname, String birth) throws ParseException {
		this.name =name;
		this.surname =surname;
		
		SimpleDateFormat birthFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.birthDate = birthFormat.parse(birth);
	}
	
	public void setDeath(Date death) {
		this.deathDate = death;
	}
	
	public int age() {
		Date last = this.deathDate!=null? this.deathDate:new Date();
		Period elapsed = Period.between(
				this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				last.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		return elapsed.getYears();
	}

	public String dettaglio() {
		
		SimpleDateFormat birthFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder sb = new StringBuilder();
		sb.append( "[name=" + name + ", surname=" + surname + 
				", birthDate=" + birthFormat.format(birthDate) +", ");
		if(this.deathDate!=null) 
			sb.append("deathDate=" + birthFormat.format(deathDate) + "]");
		else
			sb.append("age="+this.age());
		sb.append("]");
		return sb.toString();		
	}
	
	
	
}

package project;

public class PersonValidator {
	
	private boolean isInt(String string) {  //hjelpemetode (derfor private)
		try {
			Integer.parseInt(string);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public boolean isValidPersonName(String name) {
		return !(name.isEmpty() || (!name.matches("[a-zA-Z]+"))); 
	}
	
	public boolean isValidPersonAge(int age) {
		return (age >= 0 && age <= 100);
	}
	
	public boolean isValidPersonAgeString(String ageString) {
		return (isInt(ageString) && isValidPersonAge(Integer.parseInt(ageString)));
	}
}

package project;

public class SubjectValidator {
	
	private boolean isInt(String string) { //hjelpemetode (derfor private)
		try {
			Integer.parseInt(string);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public boolean isValidSubjectName(String name) {
		return !(name.isEmpty() || !name.matches("[a-zA-Z0-9 ]+"));
	}

	public boolean isValidSubjectGrade(int grade) { 
		return (grade <= 6 && grade >= 1); 
	}
	
	public boolean isValidSubjectGradeString(String inputGrade) { 
		return (isInt(inputGrade) && isValidSubjectGrade(Integer.parseInt(inputGrade)));
	}
}
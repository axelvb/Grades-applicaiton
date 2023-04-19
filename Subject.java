package project;

public class Subject implements Comparable<Subject> { 
	private String name;
	private int grade;
	
	public Subject(String name, int grade) {
		SubjectValidator subjectValidator = new SubjectValidator(); 
																
		if (!subjectValidator.isValidSubjectName(name)) {
			throw new IllegalArgumentException("Illegal value for subject name, name = " + name);
		}
		this.name = name;		
		
		if (!subjectValidator.isValidSubjectGrade(grade)) { 
			throw new IllegalArgumentException("Illegal value for the subject grade, grade: " + grade);
		}
		this.grade = grade;
	}
			
	public String getName() {
		return name;
	}
	
	public int getGrade() {
		return grade;
	}
	
	
	@Override 									
	public int compareTo(Subject other) {
		return (other.getGrade() - this.getGrade());
	}	
	
	@Override
	public boolean equals(Object other) {  
		if (!(other instanceof Subject)) {
			return false;
		}		
		Subject otherSubject = (Subject) other;
		return (this.name.equals(otherSubject.getName()) && 
				this.grade == otherSubject.getGrade());	
	} 
	//for å sjekke at fagene til Person-objektet man skriver til fil er de samme som de man leser fra fil
	
	
	
	@Override
	public String toString() {
		return "(Subject name: " +  this.name + ", grade: " + this.grade + ")";
	}
	
	
}

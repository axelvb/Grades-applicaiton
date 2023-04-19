
package project;
import java.util.ArrayList;
import java.util.Collections; //Trenger denne for å sammenligne
import java.util.List;


public class Person {
	private String name;
	private int age;
	private ArrayList<Subject> subjects = new ArrayList<>();  
	
	
	public Person(String name, int age) {
		PersonValidator personValidator = new PersonValidator();
		
		if (!personValidator.isValidPersonName(name)) {
			throw new IllegalArgumentException("Illegal value for the name: " + name);
		}
		this.name = name;
		
		if (!personValidator.isValidPersonAge(age)) {
			throw new IllegalArgumentException("Illegal value for the age: " + age);
		}
		this.age = age;	
	}

	
	public List<Subject> getSubjects() { 			
		List<Subject> subjectsCopy = new ArrayList<Subject>(subjects); 	
		return subjectsCopy;	
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void addSubject(Subject subject) { 
		String nameOfSubject = subject.getName();
		
		for (int i = 0; i < subjects.size(); i++) {
			Subject sub = subjects.get(i); 
			String subjectName = sub.getName(); 
			if (nameOfSubject.equals(subjectName)) { 
				throw new IllegalArgumentException("Subject is already in list" + subject);
			}
		}
		subjects.add(subject); 
	}
	
	public void removeSubjects() { 
		subjects.clear(); 
	}
	
	public boolean hasSubjectName(String subjectname) {
		for (int i = 0; i < subjects.size(); i++) {
			Subject subject = subjects.get(i);
			String subjectName = subject.getName();
			if (subjectname.equals(subjectName)) {
				return true;
			}
		}
		return false;
	} 
				
	public int getGradeBySubjectName(String subjectName) {
		for (int i = 0; i < subjects.size(); i++) {
			Subject s = subjects.get(i);
			
			if (subjectName.equals(s.getName())) {
				return s.getGrade();
			} 
		}
		throw new IllegalArgumentException("The subject does not exist, subject: " + subjectName);
	}
	
	public float findAverage() {
		if (subjects.isEmpty()) { 							
			throw new IllegalStateException("List can not be empty"); 
		}															
		int sumGrades = 0;
		for (int i = 0; i < subjects.size(); i++) {
			Subject subject = subjects.get(i);
			int grade = subject.getGrade();
			sumGrades += grade;	
		}
		float avg = (float)(sumGrades)/subjects.size(); 
		return avg;										
	}
	
	
	public float findGradeMedian() {
		ArrayList<Subject> subjectsCopy = new ArrayList<>(subjects);
		Collections.sort(subjectsCopy); 
		
		if (subjectsCopy.size() == 0) {
			throw new IllegalStateException("Cannot find median, list is empty");
		}
	
		Subject midElement = subjectsCopy.get(subjectsCopy.size()/2); 
		
		float median;
		if (subjectsCopy.size() == 1) {
			median = subjectsCopy.get(0).getGrade();
		}
		
		else if (subjectsCopy.size()%2 == 0) {   //partall antall fag    
			Subject midElement1 = subjectsCopy.get(subjectsCopy.size()/2-1);
			median = ((float)(midElement.getGrade() + midElement1.getGrade()))/2; 
		}
		
		else {  //odde antall fag                              
			median = midElement.getGrade(); 
		}
		return median;
	}
	
	
	private Subject bestSubject() {   //hjelpemetode (derfor private)
		if (subjects.isEmpty()) {
			throw new IllegalStateException("List can not be empty"); 
		}
		Subject bestSubject = subjects.get(0); 
										
        for (int i = 1; i < subjects.size(); i++) { 
			Subject subject = subjects.get(i);
			
			if (subject.getGrade() > bestSubject.getGrade()) {
				bestSubject = subject; 	
			}	
        }
		return bestSubject;	
	}
	
	
	public List<Subject> bestSubjects() {
		List<Subject> bestSubjects = new ArrayList<>(); 
		Subject theBestSubject = bestSubject(); 
		
		for (int i = 0; i < subjects.size(); i++) {
			Subject subject = subjects.get(i);
			if (subject.getGrade() == theBestSubject.getGrade()) {
				bestSubjects.add(subject);
			}	
        }
		return bestSubjects;
	}
	
	
	private Subject worstSubject() {   		//Hjelpemetode (derfor private)
		if (subjects.isEmpty()) {
			throw new IllegalStateException("List can not be empty");
		}
		Subject worstSubject = subjects.get(0); 
															
        for (int i = 1; i < subjects.size(); i++) {
			Subject subject = subjects.get(i);
			if (subject.getGrade() < worstSubject.getGrade()) {
				worstSubject = subject;	
			}	
        }
		return worstSubject;
	}
	
	public List<Subject> worstSubjects() {
		List<Subject> worstSubjects = new ArrayList<>(); 
		Subject theWorstSubject = worstSubject();
		
		for (int i = 0; i < subjects.size(); i++) {
			Subject subject = subjects.get(i);
			
			if (subject.getGrade() == theWorstSubject.getGrade()) {
				worstSubjects.add(subject);		
			}	
        }
		return worstSubjects;
	}
		
	public int getNumberOfSubjects() { 
		return subjects.size(); 
	}
	
	
	@Override 
	public boolean equals(Object other) { 
		if (!(other instanceof Person)) {
			return false;
		}
		Person otherPerson = (Person) other; 
		return (this.age == otherPerson.getAge() && 
				this.name.equals(otherPerson.getName()) && 
				this.subjects.equals(otherPerson.getSubjects()));	
	}
	//for å sjekke at Person-objektet man skriver og leser fra fil er det samme objektet
	
	
	@Override
	public String toString() {
		return "{Person name = " + this.name + ", age = " + this.age + ", subjects = " + this.subjects + "}";
	}
	
}

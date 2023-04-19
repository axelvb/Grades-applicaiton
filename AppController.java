package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {
	
	private Person person;	
	@FXML private TextField inputSubjectName, inputSubjectGrade;
	@FXML private TextArea output, subjectOverview; 
	
	private PersonFileManagement readWriteFile = new ReadWritePerson(); 
	private String filename = "characters.txt";
	
	public AppController() {
		person = new Person("Axel", 20);		
	}

	public void initialize() { 
		this.writeSubjects();
	}
		
	public void writeSubjects() {
		List<Subject> listOfSubjects = person.getSubjects();
		String stringWithSubjects = "";
		
		for (int i = 0; i < listOfSubjects.size(); i++) {
			Subject subject = listOfSubjects.get(i);
			stringWithSubjects += subject.getName() + " : " + subject.getGrade() + "\n" ;
		}
		subjectOverview.setText(stringWithSubjects);
	}
	
	
	@FXML
	public void add() {	
		SubjectValidator subjectValidator = new SubjectValidator();
		
		String inputName = inputSubjectName.getText();
		String inputGrade = inputSubjectGrade.getText();
		
		if (!subjectValidator.isValidSubjectName(inputName)) { 
			output.setText("Illegal value for the subject name");
			
		} else if (!subjectValidator.isValidSubjectGradeString(inputGrade)) { 
			output.setText("Illegal value for the subject grade");
		
		} else if (person.hasSubjectName(inputName)) {
			output.setText("You have already added the subject");    
			
		} else {
			int inputGradeInt = Integer.parseInt(inputGrade); 
			Subject subject1 = new Subject(inputName, inputGradeInt);
			person.addSubject(subject1);
			this.writeSubjects();	
			output.setText("Subject successfully added");
		}
	}
	
	
	@FXML
	public void removeSubjects() { 
		person.removeSubjects();
		this.writeSubjects();
		output.setText("All subjects removed");	
	}
	
	@FXML
	public void showAverage() {
		if (person.getNumberOfSubjects() == 0) {
			output.setText("No subjects added");
		}
		else {
			float average = person.findAverage();
			output.setText("Your average grade is: " + average);
		}
	}
	
	@FXML
	public void showBestSubject() {
		if (person.getNumberOfSubjects() == 0) {
			output.setText("No subjects added");
			
		} else {
			List<Subject> yourBestSubject = person.bestSubjects();
			
			if (person.getNumberOfSubjects() == 1) {
				output.setText("Your only subject is: " + yourBestSubject);
			}
			
			else {
				String stringWithBestSubjects = "";
				for (int i = 0; i < yourBestSubject.size(); i++) {
					Subject sub = yourBestSubject.get(i);
					stringWithBestSubjects += sub.getName() + " : " + sub.getGrade() + "\n";
				}
				output.setText("Your best subject is: " + "\n" + stringWithBestSubjects);
			}
		}
	}
	
	
	@FXML
	public void showWorstSubject() {
		if (person.getNumberOfSubjects() == 0) {
			output.setText("No subjects added");
		}	
		else {
			List<Subject> yourWorstSubject = person.worstSubjects();
			
			if (person.getNumberOfSubjects() == 1) {
				output.setText("Your only subject is: " + yourWorstSubject);
			}
			
			else {
				String stringWithWorstSubjects = "";
				for (int i = 0; i < yourWorstSubject.size(); i++) {
					Subject subject = yourWorstSubject.get(i);
					stringWithWorstSubjects += subject.getName() + " : " + subject.getGrade() + "\n";
				}
				output.setText("Your worst subject is: " + "\n" + stringWithWorstSubjects);
			}
		}	
	}
	
	
	@FXML 
	public void showMedian() {
		if (person.getNumberOfSubjects() == 0) {
			output.setText("Cannot find median, no subjects added");
		}
		
		else {
			float median = person.findGradeMedian(); 
			output.setText("The median of your characters is: " + median);	
		}
	}
	
	
	@FXML
	public void saveSubjects() { 
		try {
			readWriteFile.writePersonToFile(filename, person); 

		} catch (FileNotFoundException e) {
			output.setText("Not possible to write to file");
		}
	}
	

	@FXML
	public void readSubjects() { 
		try {
			Person readPerson = readWriteFile.readPersonFromFile(filename);
			this.person = readPerson;
			this.writeSubjects();	

		} catch (IOException e) {
			output.setText("Not possible to read from file");
		}
	}
}

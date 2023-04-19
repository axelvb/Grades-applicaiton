package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class ReadWritePerson implements PersonFileManagement {
	
	public void writePersonToFile(String filename, Person person) throws FileNotFoundException { 
		PrintWriter writer = new PrintWriter(filename);
		writer.println(person.getName());
		writer.println(person.getAge());
		
		List<Subject> SubjectsToFile = person.getSubjects();
		for (int i = 0; i < SubjectsToFile.size(); i++) {
			Subject subject = SubjectsToFile.get(i);
			writer.println(subject.getName() + ":" + subject.getGrade());
		}
		writer.flush();
		writer.close();	
	}
		
	
	public Person readPersonFromFile(String filename) throws IOException {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filename)); 
			
			if (!scanner.hasNextLine()){
				throw new IOException("The file has no person name");
			} 
			String personName = scanner.nextLine();
			//filen skal være på et sånt format at navn på Person står øverst i fila
			
			
			if (!scanner.hasNextLine()) {
				throw new IOException("The file has no person age");
			} 
			String stringAge = scanner.nextLine();
			//filen skal være på et sånt format at alder til Person står under navnet
			
			PersonValidator personValidator = new PersonValidator();
			SubjectValidator subjectValidator = new SubjectValidator();
			
			if (!personValidator.isValidPersonName(personName)) {
				throw new IOException("Illegal value for the person name: " + personName);
			}
			
			if (!personValidator.isValidPersonAgeString(stringAge)) {
				throw new IOException("Illegal value for the subject age: " + stringAge);
			}
			
			int intAge = Integer.parseInt(stringAge); 
			Person person = new Person(personName, intAge);
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lineSplit = line.split(":");
				if (lineSplit.length != 2) {
					throw new IOException("Illegal file format"); 
				}
				
				String subjectName = lineSplit[0];
				String subjectGradeString = lineSplit[1];
							
				if (!subjectValidator.isValidSubjectName(subjectName)) {
					throw new IOException("Illegal subject name in file");	
				}
				if (!subjectValidator.isValidSubjectGradeString(subjectGradeString)) {
					throw new IOException("Illegal subject grade in file");
				}
				int subjectGradeInt = Integer.parseInt(subjectGradeString); 
				Subject subject = new Subject(subjectName, subjectGradeInt);
				person.addSubject(subject);	
			}
			return person;
			
		} catch (FileNotFoundException e) {
			return null;
			
		} finally {
			
			if (scanner != null) {
				scanner.close();
			}
		}	
	}
}
		
		
	
	
	



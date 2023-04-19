package project;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PersonFileManagement {
	public void writePersonToFile(String filename, Person person) throws FileNotFoundException;
	public Person readPersonFromFile(String filename) throws IOException; 
}

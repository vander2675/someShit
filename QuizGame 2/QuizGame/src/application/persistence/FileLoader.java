package application.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileLoader implements IFileLoader {

	@Override
	public Scanner loadCSVtoScanner(String path) {
		Scanner result = null;
		try{
			File file = new File(path);
			result = new Scanner(file);
			result.useDelimiter(";");
		} catch (FileNotFoundException e){
			System.err.println("File could not be loaded.");
		}
		return result;
	}
}

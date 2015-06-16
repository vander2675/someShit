package application.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;


public class FileLoader implements IFileLoader {

	@Override
	public Scanner loadCSVtoScanner(String path) {
		Scanner result = null;
		try{
			InputStream stream = getClass().getResourceAsStream(path);
			result = new Scanner(stream);
			result.useDelimiter(";");
		} catch (Exception e){
			System.err.println("File could not be loaded.");
		}
		return result;
	}
}

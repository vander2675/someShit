package application.persistence;

import java.util.Scanner;

public interface IFileLoader {
	IFileLoader fileLoader = new FileLoader();
	
	public Scanner loadCSVtoScanner(String path);
}

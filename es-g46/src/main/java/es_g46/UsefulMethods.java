package es_g46;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class that generates the Excel sheet and go look where are listed the files
 * 
 * @author g46
 * @version 2.0
 */

public class UsefulMethods {
	
	/**
	 * Returns an excel sheet  
	 * 
	 * @throws FileNotFoundException If a file was not found
	 * @throws IOException if an I/O exception of some sort has occurred
	 * 
	 * @param excelFileChooser JFileChooser to select the file thats is going to be an excel sheet
	 * 
	 * @return XSSFSheet an excel sheet
	 */

	static XSSFSheet generateExcelSheet(JFileChooser excelFileChooser) throws FileNotFoundException, IOException {
		File excelFile;
		FileInputStream excelFIS;
		BufferedInputStream excelBIS;
		XSSFWorkbook excelJtableImport;
		excelFile = excelFileChooser.getSelectedFile();
		excelFIS = new FileInputStream(excelFile);
		excelBIS = new BufferedInputStream(excelFIS);
		excelJtableImport = new XSSFWorkbook(excelBIS);
		XSSFSheet excelSheet = excelJtableImport.getSheetAt(0);
		return excelSheet;
	}
	
	/**
	 * Method get all the files from a directory
	 * 
	 * @param directoryName String with the directory
	 * @param files List of files .java to be chosen from
	 */

	static void listJavaFiles(String directoryName, List<File> files) {
		File directory = new File(directoryName);

		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList != null)
			for (File file : fList) {
				if (file.isFile() && file.getName().endsWith(".java")) {
					files.add(file);
				} else if (file.isDirectory()) {
					listJavaFiles(file.getAbsolutePath(), files);
				}
			}
	}
}

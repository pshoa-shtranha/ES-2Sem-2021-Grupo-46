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

public class UsefulMethods {

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

package es_g46.es_g46;

import java.io.File;
import java.util.List;

public class UsefulMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void listJavaFiles(String directoryName, List<File> files) {
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

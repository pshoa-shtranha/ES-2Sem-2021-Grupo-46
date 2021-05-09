package es_g46;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class that generates the Excel sheet and go look where are listed the files
 * 
 * @author g46
 * @version 2.0
 */

public class EssentialMethodsForGUI {
	

	public static void importExcelFile(File excelFile, DefaultTableModel tableModel, DefaultTableModel comparisonTModel) {
		try {
			//Clear table with code smells when importing
			comparisonTModel.setRowCount(0);
			
			XSSFSheet excelSheet = generateExcelSheet(excelFile);
			// Looping through excel columns and rows (comeÃ§a na 2a fila)
			tableModel.setRowCount(0);
			for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
				XSSFRow excelRow = excelSheet.getRow(row);
				// DefaultTableModel model = (DefaultTableModel) table.getModel();
				// model.setRowCount(0);
				// adding row to the table
				tableModel.addRow(new Object[] { excelRow.getCell(0), excelRow.getCell(1), excelRow.getCell(2),
						excelRow.getCell(3), excelRow.getCell(4), excelRow.getCell(5), excelRow.getCell(6),
						excelRow.getCell(7), excelRow.getCell(8), excelRow.getCell(9),
						excelRow.getCell(10) });
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block1
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (IOException e2) {
			// TODO Auto-generated catch block2
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
		

	public static void importJavaFiles(File file, DefaultTableModel model, DefaultTableModel comparisonTModel) {
		File ficheirosJava;
		String ficheiroPath;
		//Clear table with code smells when importing
		comparisonTModel.setRowCount(0);
		
		ficheiroPath = file.getAbsolutePath();
		ficheirosJava = new File(ficheiroPath);
		List<File> files = new ArrayList<File>();
		if (ficheirosJava.isDirectory()) {
			// List<File> files = new ArrayList<File>();
			// guarda os ficheiros .java todos do diretÃ³rio numa lista
			listJavaFiles(ficheirosJava.getAbsolutePath(), files);

			// print files on screen
			for (File f : files) {
				System.out.println(f.getName());
			}
		} else if (ficheirosJava.isFile()) {
			System.out.println("Ã‰ um ficheiro");
		}
		int[] smells = new int[5];
		smells[0] = 1;
		smells[1] = 1;
		smells[2] = 1;
		smells[3] = 1;
		smells[4] = 1;
		String project = file.getName();
		System.out.println(project);
		File directory = new File("metricas");
		if (!directory.exists())
			directory.mkdir();
		String excelDir = "metricas/";
		try {
			new FileManagement(files, smells, excelDir, project);
		} catch (IOException i) {
			
			i.printStackTrace();
		}
		StringBuilder location = new StringBuilder();
		location.append(excelDir);
		location.append(project);
		location.append("_metrics.xlsx");
		try {
			FileInputStream excelFIS = new FileInputStream(location.toString());
			BufferedInputStream excelBIS = new BufferedInputStream(excelFIS);
			XSSFWorkbook excelJtableImport = new XSSFWorkbook(excelBIS);
			XSSFSheet excelSheet = excelJtableImport.getSheetAt(0);
			// Looping through excel columns and rows (comeÃ§a na 2a fila)
			model.setRowCount(0);
			for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
				XSSFRow excelRow = excelSheet.getRow(row);
				// DefaultTableModel model = (DefaultTableModel) table.getModel();
				// model.fireTableRowsDeleted(0, model.getColumnCount());
				// adding row to the table
				// model.setRowCount(0);
				model.addRow(new Object[] { excelRow.getCell(0), excelRow.getCell(1), excelRow.getCell(2),
						excelRow.getCell(3), excelRow.getCell(4), excelRow.getCell(5), excelRow.getCell(6),
						excelRow.getCell(7), excelRow.getCell(8), excelRow.getCell(9), excelRow.getCell(10) });
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block1
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (IOException e2) {
			// TODO Auto-generated catch block2
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}

	
	public static void extractInfoIntoPanel(DefaultTableModel model, JPanel panel, JTextArea classTextArea, JTextArea methodTextArea) {
		String currentPackageName = "";
		int nPackages = 0;
		String currentClassName = "";
		int nClasses = 0;
		int nMethods = 0;
		double nLines = 0;
		for (int i = 0; i < model.getRowCount(); i++) {
			// calculates number of packages
			if (model.getValueAt(i, 1) != null && !model.getValueAt(i, 1).toString().equals(currentPackageName)) {
				currentPackageName = model.getValueAt(i, 1).toString();
				nPackages++;
				
			}
			// calculate number of classes and sum their lines into total number of lines
			if (model.getValueAt(i, 2) != null && !model.getValueAt(i, 2).toString().equals(currentClassName)
					|| (model.getValueAt(i, 1) != null && !model.getValueAt(i, 1).toString().equals(currentPackageName)
					&& model.getValueAt(i, 2).toString().equals(currentClassName))) {
				currentClassName = model.getValueAt(i, 2).toString();
				nClasses++;
				// nLines += Double.parseDouble(model.getValueAt(i, 5).toString());
				if (model.getValueAt(i, 5) != null) {
					nLines += Double.parseDouble(model.getValueAt(i, 5).toString());
				}
			}
			
			// Calculate number of methods
			if (model.getValueAt(i, 3) != null && model.getValueAt(i, 3) != null
					&& !model.getValueAt(i, 3).toString().isEmpty()) {
				nMethods++;
			}
		}
		
		panel.removeAll();
		panel.add(new JLabel("Nº total de packages:"));
		panel.add(new JLabel(String.valueOf(nPackages)));
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(new JLabel("Nº total de classes:"));
		panel.add(new JLabel(String.valueOf(nClasses)));
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(new JLabel("Nº total de métodos:"));
		panel.add(new JLabel(String.valueOf(nMethods)));
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(new JLabel("Nº total de linhas de código:"));
		panel.add(new JLabel(String.valueOf(nLines)));
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JTabbedPane indicadoresAcertoTextoPane = new JTabbedPane(JTabbedPane.TOP);
		
		classTextArea.setEditable(false);
		JScrollPane classScrollPanel = new JScrollPane(classTextArea);
		indicadoresAcertoTextoPane.addTab("Class Indicators", null, classScrollPanel, null);
		
		methodTextArea.setEditable(false);
		JScrollPane methodScrollPanel = new JScrollPane(methodTextArea);
		indicadoresAcertoTextoPane.addTab("Method Indicators", null, methodScrollPanel, null);
		
		panel.add(indicadoresAcertoTextoPane);
		
		panel.setPreferredSize(new Dimension(300, panel.getPreferredSize().height));
		panel.revalidate();
	}

	/**
	 * Returns an excel sheet  
	 * 
	 * @throws FileNotFoundException If a file was not found
	 * @throws IOException if an I/O exception of some sort has occurred
	 * @param excelFileChooser JFileChooser to select the file thats is going to be an excel sheet
	 * @return XSSFSheet an excel sheet
	 */
	
	static XSSFSheet generateExcelSheet(File excelFile) throws FileNotFoundException, IOException {
		FileInputStream excelFIS;
		BufferedInputStream excelBIS;
		XSSFWorkbook excelJtableImport;
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

package es_g46;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

class EssentialMethodsForGUITest {

	@Test
	void testImportExcelFile() {
		File excelFile = new File("metricas/Code_Smells.xlsx");
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();
		
		EssentialMethodsForGUI.importExcelFile(excelFile, tModel, tModel2);
		assert(tModel.getRowCount() > 0);
//		assert(tModel2.getRowCount() == 0);
	}
	
	@Test
	void testImportExcelFileNotFound() {	
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();

		File file2 = new File("non-existant-file");
		EssentialMethodsForGUI.importExcelFile(file2, tModel, tModel2);
		assert(tModel.getRowCount() == 0);
	}

	@Test
	void testImportJavaFiles() {
		File javaFolder = new File("src/main");
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();
		EssentialMethodsForGUI.importJavaFiles(javaFolder, tModel, tModel2);
		
	}
	
	@Test
	void testImportJavaSingleFile() {
		File javaFile = new File("src/main/java/es_g46/GUI.java");
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();
		EssentialMethodsForGUI.importJavaFiles(javaFile, tModel, tModel2);
		
	}

	@Test
	void testImportJavaFilesNotFound() {
		File javaFolder = new File("non-existant-file");
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();
		
		EssentialMethodsForGUI.importJavaFiles(javaFolder, tModel, tModel2);
		assert(tModel.getRowCount() == 0);
	}
	
	@Test
	void testExtractInfoIntoPanel() {
		File excelFile = new File("metricas/Code_Smells.xlsx");
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();
		
		EssentialMethodsForGUI.importExcelFile(excelFile, tModel, tModel2);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JTextArea textArea1 = new JTextArea("");
		JTextArea textArea2 = new JTextArea("");
		EssentialMethodsForGUI.extractInfoIntoPanel(tModel, panel, textArea1, textArea2);
		
	}

}

/**
 * 
 */
package es_g46;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

/**
 * @author scilens
 *
 */
class EssentialJanelasMetricasMethodsTest {

	/**
	 * Test method for {@link es_g46.EssentialJanelasMetricasMethods#saveRegras(java.lang.String[])}.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	void testSaveRegras() {
		String[] stringRegras = new String[7];
		stringRegras[0] = "NOM_class";
		stringRegras[1] = "<";
		stringRegras[2] = "20";
		stringRegras[3] = "OR";
		stringRegras[4] = "LOC_class";
		stringRegras[5] = ">";
		stringRegras[6] = "1000";
		
		EssentialJanelasMetricasMethods.saveRegras(stringRegras);
		File file = new File("regras/Class[NOM_class, MENOR, 20, OR, LOC_class, MAIOR, 1000].txt");
		assert(file.exists());
	}

	/**
	 * Test method for {@link es_g46.EssentialJanelasMetricasMethods#saveRegras2(java.lang.String[])}.
	 */
	@Test
	void testSaveRegras2() {
		String[] stringRegras = new String[7];
		stringRegras[0] = "LOC_method";
		stringRegras[1] = ">";
		stringRegras[2] = "300";
		stringRegras[3] = "AND";
		stringRegras[4] = "CYCLO_Method";
		stringRegras[5] = "<";
		stringRegras[6] = "100";
		
		EssentialJanelasMetricasMethods.saveRegras2(stringRegras);
		File file = new File("regras/Method[LOC_method, MAIOR, 300, AND, CYCLO_Method, MENOR, 100].txt");
		assert(file.exists());
	}

	/**
	 * Test method for {@link es_g46.EssentialJanelasMetricasMethods#submitClassRule(javax.swing.JTable, javax.swing.JTable, java.lang.String[], javax.swing.JTextArea)}.
	 */
	@Test
	void testSubmitClassRule() {
		File excelFile = new File("metricas/Code_Smells.xlsx");
		
		JTable table1 = new JTable();
		table1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel1 = (DefaultTableModel) table1.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();
		
		EssentialMethodsForGUI.importExcelFile(excelFile, tModel1, tModel2);
		
		String[] stringRegras = new String[7];
		stringRegras[0] = "NOM_class";
		stringRegras[1] = ">";
		stringRegras[2] = "20";
		stringRegras[3] = "OR";
		stringRegras[4] = "LOC_class";
		stringRegras[5] = ">";
		stringRegras[6] = "1000";
		
		JTextArea testArea = new JTextArea("");
		
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		
		assert(tModel2.getRowCount() > 0);
	}
	
	/**
	 * Test method for {@link es_g46.EssentialJanelasMetricasMethods#submitMethodRule(javax.swing.JTable, javax.swing.JTable, java.lang.String[], javax.swing.JTextArea)}.
	 */
	@Test
	void testSubmitMethodRule() {
		File excelFile = new File("metricas/Code_Smells.xlsx");
		
		JTable table1 = new JTable();
		table1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel1 = (DefaultTableModel) table1.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();
		
		EssentialMethodsForGUI.importExcelFile(excelFile, tModel1, tModel2);
		
		String[] stringRegras = new String[7];
		stringRegras[0] = "LOC_method";
		stringRegras[1] = ">";
		stringRegras[2] = "300";
		stringRegras[3] = "AND";
		stringRegras[4] = "CYCLO_Method";
		stringRegras[5] = "<";
		stringRegras[6] = "100";
		
		JTextArea testArea = new JTextArea("");
		
		EssentialJanelasMetricasMethods.submitMethodRule(table1, table2, stringRegras, testArea);
		
		assert(tModel2.getRowCount() > 0);
	}


	/**
	 * Test method for {@link es_g46.EssentialJanelasMetricasMethods#loadRegras1(javax.swing.JComboBox, javax.swing.JComboBox, javax.swing.JComboBox, javax.swing.JComboBox, javax.swing.JComboBox, javax.swing.JTextField, javax.swing.JTextField)}.
	 */
	@Test
	void testLoadRegras1() {
		String opcoes[] = { "NOM_class", "LOC_class", "WMC_class" };
		String sinais[] = { "<", ">", "=" };
		String logica[] = { "AND", "OR" };

		JComboBox<?> comboBox1Name = new JComboBox<Object>(opcoes);
		JComboBox<?> comboBox2Sign = new JComboBox<Object>(sinais);
		JTextField text_regras = new JTextField(10);
		JComboBox<?> comboBox3Operator = new JComboBox<Object>(logica);
		JComboBox<?> comboBox4Name2 = new JComboBox<Object>(opcoes);
		JComboBox<?> comboBox5Sign2 = new JComboBox<Object>(sinais);
		JTextField text_regras1 = new JTextField(10);

		EssentialJanelasMetricasMethods.loadRegras1(comboBox1Name, comboBox2Sign, comboBox3Operator, 
				comboBox4Name2, comboBox5Sign2, text_regras, text_regras1);
		
	}

	/**
	 * Test method for {@link es_g46.EssentialJanelasMetricasMethods#loadRegras2(javax.swing.JComboBox, javax.swing.JComboBox, javax.swing.JComboBox, javax.swing.JComboBox, javax.swing.JComboBox, javax.swing.JTextField, javax.swing.JTextField)}.
	 */
	@Test
	void testLoadRegras2() {
		String opcoes[] = { "LOC_method", "CYCLO_Method" };
		String sinais[] = { "<", ">", "=" };
		String logica[] = { "AND", "OR" };

		JComboBox<?> comboBox1Name = new JComboBox<Object>(opcoes);
		JComboBox<?> comboBox2Sign = new JComboBox<Object>(sinais);
		JTextField text_regras = new JTextField(10);
		JComboBox<?> comboBox3Operator = new JComboBox<Object>(logica);
		JComboBox<?> comboBox4Name2 = new JComboBox<Object>(opcoes);
		JComboBox<?> comboBox5Sign2 = new JComboBox<Object>(sinais);
		JTextField text_regras1 = new JTextField(10);

		EssentialJanelasMetricasMethods.loadRegras2(comboBox1Name, comboBox2Sign, comboBox3Operator, 
				comboBox4Name2, comboBox5Sign2, text_regras, text_regras1);
		
	}

	/**
	 * Test method for {@link es_g46.EssentialJanelasMetricasMethods#writeGeneratedCodeSmellToComparisonTable(javax.swing.table.DefaultTableModel, javax.swing.table.DefaultTableModel, java.lang.String[], int, int, int, int)}.
	 */
	@Test
	void testSubmitClassRules() {
		File excelFile = new File("metricas/Code_Smells.xlsx");
		
		JTable table1 = new JTable();
		table1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		DefaultTableModel tModel1 = (DefaultTableModel) table1.getModel();
		
		JTable table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		DefaultTableModel tModel2 = (DefaultTableModel) table2.getModel();
		
		EssentialMethodsForGUI.importExcelFile(excelFile, tModel1, tModel2);
		
		String[] stringRegras = new String[7];
		stringRegras[0] = "NOM_class";
		stringRegras[1] = ">";
		stringRegras[2] = "1";
		stringRegras[3] = "AND";
		stringRegras[4] = "LOC_class";
		stringRegras[5] = ">";
		stringRegras[6] = "1";
		
		JTextArea testArea = new JTextArea("");
		
		stringRegras[1] = ">";
		stringRegras[5] = ">";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "<";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "=";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[1] = "<";
		stringRegras[5] = ">";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "<";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "=";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[1] = "=";
		stringRegras[5] = ">";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "<";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "=";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[3] = "OR";
		stringRegras[1] = ">";
		stringRegras[5] = ">";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "<";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "=";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[1] = "<";
		stringRegras[5] = ">";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "<";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "=";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[1] = "=";
		stringRegras[5] = ">";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "<";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		stringRegras[5] = "=";
		EssentialJanelasMetricasMethods.submitClassRule(table1, table2, stringRegras, testArea);
		assert(tModel2.getRowCount() > 0);
	}

}

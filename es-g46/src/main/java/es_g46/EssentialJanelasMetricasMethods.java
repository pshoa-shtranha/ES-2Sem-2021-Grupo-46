package es_g46;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EssentialJanelasMetricasMethods {

	// retorna um array de String com as regras criadas
	public static String[] saveRegras(String[] string_regras) {
		System.out.println(Arrays.toString(string_regras));
		File directory = new File("regras");
		if (!directory.exists())
			directory.mkdir();
		String nomeFicheiro = Arrays.toString(string_regras);
		if (nomeFicheiro.contains("<"))
			nomeFicheiro = nomeFicheiro.replace("<", "MENOR");
		if (nomeFicheiro.contains(">"))
			nomeFicheiro = nomeFicheiro.replace(">", "MAIOR");

		System.out.println(nomeFicheiro);
		Writer output = null;
		File file = new File("regras/" + "Class" + nomeFicheiro + ".txt");
		try {
			file.createNewFile();
			output = new BufferedWriter(new FileWriter(file));
			output.write(Arrays.toString(string_regras));
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		System.out.println("File has been written");
		return string_regras;
	}

	/**
	 * Save the rules chosen in the frame "method"
	 * 
	 * @return Array of Strings includes the saved rules
	 */

	public static String[] saveRegras2(String[] string_regras) {
		System.out.println(Arrays.toString(string_regras));
		File directory = new File("regras");
		if (!directory.exists())
			directory.mkdir();
		String nomeFicheiro = Arrays.toString(string_regras);
		if (nomeFicheiro.contains("<"))
			nomeFicheiro = nomeFicheiro.replace("<", "MENOR");
		if (nomeFicheiro.contains(">"))
			nomeFicheiro = nomeFicheiro.replace(">", "MAIOR");
		Writer output = null;
		File file = new File("regras/" + "Method" + nomeFicheiro + ".txt");
		
		try {
		file.createNewFile();
		output = new BufferedWriter(new FileWriter(file));
		output.write(Arrays.toString(string_regras));
		output.close();
		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		System.out.println("File has been written");
		return string_regras;
	}

	public static void submitClassRule(JTable table1, JTable table2, String[] stringRegras, JTextArea eastPClassTextArea) {
		// Definir modelos para adicionar regras à tabela 2
		DefaultTableModel excelTModel = (DefaultTableModel) table1.getModel();
		DefaultTableModel comparisonTModel = (DefaultTableModel) table2.getModel();

		// Make table 2 have the same number of rows as table 1, also clears any rows
		// table 2 might've had
		if (excelTModel.getRowCount() != comparisonTModel.getRowCount()) {
			comparisonTModel.setRowCount(0);
			for (int i = 0; i < excelTModel.getRowCount(); i++) {
				comparisonTModel.addRow(new Object[] { null, null, null, null });
			}
		}

		// Verifica-se onde é que ele vai buscar a informação para comparar
		int excelTClassCol1;
		excelTClassCol1 = getColInxOfSelMetric(stringRegras[0], table1);
		int excelTClassCol2;
		excelTClassCol2 = getColInxOfSelMetric(stringRegras[4], table1);
		for (int i = 0; i < excelTModel.getRowCount(); i++) {
			// sets table 2 class name
			comparisonTModel.setValueAt(excelTModel.getValueAt(i, table1.getColumn("class").getModelIndex()), i, 0);
			// sets table 2 manual god class
			comparisonTModel.setValueAt(excelTModel.getValueAt(i, table1.getColumn("is_God_Class").getModelIndex()),
					i, 1);
			// sets table 2 auto-generated god class
			writeGeneratedCodeSmellToComparisonTable(excelTModel, comparisonTModel, stringRegras, excelTClassCol1,
					excelTClassCol2, i, 2);
			// set verdadeiros positivos and stuff for Class
			setDetectionQualityInCell2(comparisonTModel, i, 1, 2, 3);
			// set verdadeiros positivos and stuff in text in class indicator text tab
			if (comparisonTModel.getValueAt(i, 0) != null && comparisonTModel.getValueAt(i, 3) != null
					&& !comparisonTModel.getValueAt(i, 0).toString().equals("")
					&& !comparisonTModel.getValueAt(i, 3).toString().equals("")) {
				String lastClass = "";
				String currentClass;
				String currentIndicator;
				if (i > 0) {
					lastClass = comparisonTModel.getValueAt(i - 1, 0).toString();
				}

				currentClass = comparisonTModel.getValueAt(i, 0).toString();
				if (!currentClass.equals(lastClass)) {
					currentIndicator = comparisonTModel.getValueAt(i, 3).toString();
					eastPClassTextArea.append(currentClass + ": " + currentIndicator + "\n");
				}
			}
		}
	}

	public static void submitMethodRule(JTable table1, JTable table2, String[] stringRegras, JTextArea eastPMethodTextArea) {
		// Definir modelos para adicionar regras à tabela 2
		DefaultTableModel excelTModel = (DefaultTableModel) table1.getModel();
		DefaultTableModel comparisonTModel = (DefaultTableModel) table2.getModel();

		// Make table 2 have the same number of rows as table 1, also clears any rows
		// table 2 might've had
		if (excelTModel.getRowCount() != comparisonTModel.getRowCount()) {
			comparisonTModel.setRowCount(0);
			for (int i = 0; i < excelTModel.getRowCount(); i++) {
				comparisonTModel.addRow(new Object[] { null, null, null, null });
			}
		}

		// Verifica-se onde é que ele vai buscar a informação para comparar
		int excelTClassCol1;
		excelTClassCol1 = getColInxOfSelMetric(stringRegras[0], table1);
		int excelTClassCol2;
		excelTClassCol2 = getColInxOfSelMetric(stringRegras[4], table1);
		for (int i = 0; i < excelTModel.getRowCount(); i++) {
			// sets method id on table 2
			comparisonTModel.setValueAt(i + 1, i, 4);
			// sets table 2 class name
			comparisonTModel.setValueAt(excelTModel.getValueAt(i, table1.getColumn("method").getModelIndex()), i, 5);
			// sets table 2 manual god class
			comparisonTModel
					.setValueAt(excelTModel.getValueAt(i, table1.getColumn("is_Long_Method").getModelIndex()), i, 6);

			writeGeneratedCodeSmellToComparisonTable(excelTModel, comparisonTModel, stringRegras, excelTClassCol1,
					excelTClassCol2, i, 7);
			// set verdadeiros positivos and stuff for Method
			setDetectionQualityInCell2(comparisonTModel, i, 6, 7, 8);
			// set verdadeiros positivos and stuff in method indicator text tab
			if (comparisonTModel.getValueAt(i, 4) != null && comparisonTModel.getValueAt(i, 5) != null
					&& comparisonTModel.getValueAt(i, 8) != null
					&& !comparisonTModel.getValueAt(i, 4).toString().equals("")
					&& !comparisonTModel.getValueAt(i, 5).toString().equals("")
					&& !comparisonTModel.getValueAt(i, 8).toString().equals("")) {
				String currentMethodID;
				String currentMethodName;
				String currentIndicator;

				currentMethodID = comparisonTModel.getValueAt(i, 4).toString();
				currentMethodName = comparisonTModel.getValueAt(i, 5).toString();
				currentIndicator = comparisonTModel.getValueAt(i, 8).toString();
				eastPMethodTextArea
						.append(currentMethodID + " - " + currentMethodName + ": " + currentIndicator + "\n");
			}
		}
	}

	/**
	 * Returns the number of the column of the metric chosen
	 * 
	 * @param stringRegra string of rules
	 * 
	 * @return int includes the number of the column
	 */

	public static int getColInxOfSelMetric(String stringRegra, JTable table1) {
		int excelTClassCol1 = -1;
		if (stringRegra.equals("NOM_class")) {
			excelTClassCol1 = table1.getColumn("NOM_class").getModelIndex();
		} else if (stringRegra.equals("LOC_class")) {
			excelTClassCol1 = table1.getColumn("LOC_class").getModelIndex();
		} else if (stringRegra.equals("WMC_class")) {
			excelTClassCol1 = table1.getColumn("WMC_class").getModelIndex();
		} else if (stringRegra.equals("LOC_method")) {
			excelTClassCol1 = table1.getColumn("LOC_method").getModelIndex();
		} else if (stringRegra.equals("CYCLO_Method")) {
			excelTClassCol1 = table1.getColumn("CYCLO_Method").getModelIndex();
		}
		return excelTClassCol1;
	}

	public static void writeGeneratedCodeSmellToComparisonTable(DefaultTableModel excelTModel,
			DefaultTableModel comparisonTModel, String[] stringRegras, int excelTCol1, int excelTCol2, int row,
			int col2) {
		// define table 1 and table 2 columns
		double t1v1;
		double t1v2;
		double v1 = Double.parseDouble(stringRegras[2]);
		double v2 = Double.parseDouble(stringRegras[6]);

		if (excelTModel.getValueAt(row, excelTCol1) != null
				&& !excelTModel.getValueAt(row, excelTCol1).toString().equals("")) {
			t1v1 = Double.parseDouble(excelTModel.getValueAt(row, excelTCol1).toString());
		} else {
			t1v1 = 0;
		}

		if (excelTModel.getValueAt(row, excelTCol2) != null
				&& !excelTModel.getValueAt(row, excelTCol2).toString().equals("")) {
			t1v2 = Double.parseDouble(excelTModel.getValueAt(row, excelTCol2).toString());
		} else {
			t1v2 = 0;
		}

		// sets table 2 generated field
		if (stringRegras[3].equals("AND")) {
			if (stringRegras[1].equals(">")) {
				if (stringRegras[5].equals(">")) {
					if (t1v1 > v1 && t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("<")) {
					if (t1v1 > v1 && t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("=")) {
					if (t1v1 > v1 && t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			} else if (stringRegras[1].equals("<")) {
				if (stringRegras[5].equals(">")) {
					if (t1v1 < v1 && t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("<")) {
					if (t1v1 < v1 && t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("=")) {
					if (t1v1 < v1 && t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			} else if (stringRegras[1].equals("=")) {
				if (stringRegras[5].equals(">")) {
					if (t1v1 == v1 && t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("<")) {
					if (t1v1 == v1 && t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("=")) {
					if (t1v1 == v1 && t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			}
		} else if (stringRegras[3].equals("OR")) {
			if (stringRegras[1].equals(">")) {
				if (stringRegras[5].equals(">")) {
					if (t1v1 > v1 || t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("<")) {
					if (t1v1 > v1 || t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("=")) {
					if (t1v1 > v1 || t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			} else if (stringRegras[1].equals("<")) {
				if (stringRegras[5].equals(">")) {
					if (t1v1 < v1 || t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("<")) {
					if (t1v1 < v1 || t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("=")) {
					if (t1v1 < v1 || t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			} else if (stringRegras[1].equals("=")) {
				if (stringRegras[5].equals(">")) {
					if (t1v1 == v1 || t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("<")) {
					if (t1v1 == v1 || t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				} else if (stringRegras[5].equals("=")) {
					if (t1v1 == v1 || t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					} else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			}
		}
	}

	/**
	 * Method to set the detection quality that we encounter in cell2 Through the
	 * values of "true" and "false" is defined the correct value in the row
	 * 
	 * @param comparisonTModel default table model
	 * @param row              int
	 * @param c1               int
	 * @param c2               int
	 * @param c3               int
	 */

	public static void setDetectionQualityInCell2(DefaultTableModel comparisonTModel, int row, int c1, int c2, int c3) {
		if (comparisonTModel.getValueAt(row, c1) != null && comparisonTModel.getValueAt(row, c2) != null
				&& !comparisonTModel.getValueAt(row, c1).toString().equals("")
				&& !comparisonTModel.getValueAt(row, c2).toString().equals("")) {
			if (comparisonTModel.getValueAt(row, c1).toString().equals("TRUE")
					&& comparisonTModel.getValueAt(row, c2).toString().equals("TRUE")) {
				comparisonTModel.setValueAt("VP", row, c3);
			} else if (comparisonTModel.getValueAt(row, c1).toString().equals("TRUE")
					&& comparisonTModel.getValueAt(row, c2).toString().equals("FALSE")) {
				comparisonTModel.setValueAt("FN", row, c3);
			} else if (comparisonTModel.getValueAt(row, c1).toString().equals("FALSE")
					&& comparisonTModel.getValueAt(row, c2).toString().equals("TRUE")) {
				comparisonTModel.setValueAt("FP", row, c3);
			} else if (comparisonTModel.getValueAt(row, c1).toString().equals("FALSE")
					&& comparisonTModel.getValueAt(row, c2).toString().equals("FALSE")) {
				comparisonTModel.setValueAt("VN", row, c3);
			}
		}
	}

	public static void loadRegras1(JComboBox<?> comboBox1Name, JComboBox<?> comboBox2Sign, JComboBox<?> comboBox3Operator, 
			JComboBox<?> comboBox4Name2, JComboBox<?> comboBox5Sign2, JTextField text_regras, JTextField text_regras1) {
		File FicheirosTXT;
		String ficheiroPath;
		File directory = new File("regras");
		if (!directory.exists())
			directory.mkdir();
		String defaultCurrentDirectoryPath = "regras/";
		JFileChooser fileChooser = new JFileChooser(defaultCurrentDirectoryPath);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int chooser = fileChooser.showOpenDialog(null);
		if (chooser == JFileChooser.APPROVE_OPTION) {
			ficheiroPath = fileChooser.getSelectedFile().getAbsolutePath();
			FicheirosTXT = new File(ficheiroPath);
			if (FicheirosTXT.isDirectory()) {
				List<File> files = new ArrayList<File>();
				// guarda os ficheiros .java todos do diretório numa lista
				EssentialMethodsForGUI.listJavaFiles(FicheirosTXT.getAbsolutePath(), files);

				// print files on screen
				for (File f : files) {
					System.out.println(f.getName());
				}
			} else if (FicheirosTXT.isFile()) {
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(ficheiroPath));
					try {
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();

						while (line != null) {
							sb.append(line);
							sb.append(System.lineSeparator());
							line = br.readLine();
						}
						String everything = sb.toString();
						String everythingReplace = everything.replace("[", "");
						String everythingReplace2 = everythingReplace.replace("]", "");

						System.out.println(everythingReplace2);
						String[] ss = everythingReplace2.split(",");
						ss[1] = ss[1].replace(" ", "");
						ss[3] = ss[3].replace(" ", "");
						ss[4] = ss[4].replace(" ", "");
						ss[5] = ss[5].replace(" ", "");

						for (int i = 0; i < 3; i++) {

							String textoDaComboBox1 = comboBox1Name.getItemAt(i).toString();
							if (textoDaComboBox1.equals(ss[0])) {
								comboBox1Name.setSelectedIndex(i);
							}
							String textoDaComboBox2 = comboBox2Sign.getItemAt(i).toString();
							if (textoDaComboBox2.equals(ss[1])) {
								comboBox2Sign.setSelectedIndex(i);
							}
							String textoDaComboBox5 = "";
							if (i < 2) {
								textoDaComboBox5 = comboBox3Operator.getItemAt(i).toString();

								if (textoDaComboBox5.equals(ss[3])) {
									comboBox3Operator.setSelectedIndex(i);
								}
							}
							String textoDaComboBox3 = comboBox4Name2.getItemAt(i).toString();
							if (textoDaComboBox3.equals(ss[4])) {
								comboBox4Name2.setSelectedIndex(i);
							}
							String textoDaComboBox4 = comboBox5Sign2.getItemAt(i).toString();
							if (textoDaComboBox4.equals(ss[5])) {
								comboBox5Sign2.setSelectedIndex(i);
							}

							text_regras.setText(ss[2]);
							text_regras1.setText(ss[6]);

						}

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							br.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

	public static void loadRegras2(JComboBox<?> comboBox1v2Name, JComboBox<?> comboBox2v2Sign, JComboBox<?> comboBox3v2Operator, 
			JComboBox<?> comboBox4v2Name2, JComboBox<?> comboBox5v2Sign2, JTextField text_regrasv2, JTextField text_regras1v2) {
		File FicheirosTXT;
		String ficheiroPath;
		File directory = new File("regras");
		if (!directory.exists())
			directory.mkdir();
		String defaultCurrentDirectoryPath = "regras/";
		JFileChooser fileChooser = new JFileChooser(defaultCurrentDirectoryPath);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int chooser = fileChooser.showOpenDialog(null);
		if (chooser == JFileChooser.APPROVE_OPTION) {
			ficheiroPath = fileChooser.getSelectedFile().getAbsolutePath();
			FicheirosTXT = new File(ficheiroPath);
			if (FicheirosTXT.isDirectory()) {
				List<File> files = new ArrayList<File>();
				// guarda os ficheiros .java todos do diretório numa lista
				EssentialMethodsForGUI.listJavaFiles(FicheirosTXT.getAbsolutePath(), files);

				// print files on screen
				for (File f : files) {
					System.out.println(f.getName());
				}
			} else if (FicheirosTXT.isFile()) {
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(ficheiroPath));
					try {
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();

						while (line != null) {
							sb.append(line);
							sb.append(System.lineSeparator());
							line = br.readLine();
						}
						String everything = sb.toString();
						String everythingReplace = everything.replace("[", "");
						String everythingReplace2 = everythingReplace.replace("]", "");

						System.out.println(everythingReplace2);
						String[] ss = everythingReplace2.split(",");
						ss[1] = ss[1].replace(" ", "");
						ss[3] = ss[3].replace(" ", "");
						ss[4] = ss[4].replace(" ", "");
						ss[5] = ss[5].replace(" ", "");

						for (int i = 0; i < 3; i++) {
							if (i < 2) {
								String textoDaComboBox1 = comboBox1v2Name.getItemAt(i).toString();
								if (textoDaComboBox1.equals(ss[0])) {
									comboBox1v2Name.setSelectedIndex(i);
								}
							}
							String textoDaComboBox2 = comboBox2v2Sign.getItemAt(i).toString();
							if (textoDaComboBox2.equals(ss[1])) {
								comboBox2v2Sign.setSelectedIndex(i);
							}
							String textoDaComboBox5 = "";
							if (i < 2) {
								textoDaComboBox5 = comboBox3v2Operator.getItemAt(i).toString();

								if (textoDaComboBox5.equals(ss[3])) {
									comboBox3v2Operator.setSelectedIndex(i);
								}
							}
							if (i < 2) {
								String textoDaComboBox3 = comboBox4v2Name2.getItemAt(i).toString();
								if (textoDaComboBox3.equals(ss[4])) {
									comboBox4v2Name2.setSelectedIndex(i);
								}
							}
							String textoDaComboBox4 = comboBox5v2Sign2.getItemAt(i).toString();
							if (textoDaComboBox4.equals(ss[5])) {
								comboBox5v2Sign2.setSelectedIndex(i);
							}
							text_regrasv2.setText(ss[2]);
							text_regras1v2.setText(ss[6]);
						}

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							br.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}

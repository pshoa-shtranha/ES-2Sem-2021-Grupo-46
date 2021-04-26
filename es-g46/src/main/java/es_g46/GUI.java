package es_g46;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 * Class that opens the window showing all the buttons that have functionalites attached to them
 * 
 * @author Margarida Correia and Pavlo
 * @version 2.0
 */

public class GUI {

	private JFrame frame;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel eastPanel;
	static JTable table;
	static JTable table2;

	/**
	 * Method executed in function addFrameContent()
	 * Creates a table where it can present a excel sheet
	 * Creates a table to compare code smells
	 * 
	 */
	
	private void topPanel() {

		topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(topPanel, BorderLayout.CENTER);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// Criação da tabela para visualizar excel
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		table.getColumnModel().getColumn(9).setPreferredWidth(91);

		// Criação da tabela para comparar code smells
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "className", "Manual_God_Class", "Generated_God_Class", "Class_Indicator", "MethodID",
						"methodName", "Manual_Long_Method", "Generated_Long_Method", "Method_Indicator" }));
		table2.getColumnModel().getColumn(8).setPreferredWidth(91);

		topPanel.add(tabbedPane);
		JScrollPane table1ScrollPane = new JScrollPane(table);
		tabbedPane.addTab("Visualização do Excel", null, table1ScrollPane, null);
		JScrollPane table2ScrollPane = new JScrollPane(table2);
		tabbedPane.addTab("Comparação de code smells", null, table2ScrollPane, null);

	}
	
	/**
	 * Method executed in function addFrameContent()
	 * Creates a button to import excel
	 * Creates another button to import java files
	 * Creates a third button to create a rule
	 * Creates the last button to choose the saved rules
	 * 
	 */

	private void bottomPanel() {

		JButton button0 = new JButton("Import Excel");
		button0.setBackground(Color.WHITE);
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String defaultCurrentDirectoryPath = "src/";
				JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
				int excelChooser = excelFileChooser.showOpenDialog(null);
				// if import button is clicked
				if (excelChooser == JFileChooser.APPROVE_OPTION) {
					try {
						XSSFSheet excelSheet = UsefulMethods.generateExcelSheet(excelFileChooser);
						// Looping through excel columns and rows (comeÃ§a na 2a fila)
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0);
						for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
							XSSFRow excelRow = excelSheet.getRow(row);
							// DefaultTableModel model = (DefaultTableModel) table.getModel();
							// model.setRowCount(0);
							// adding row to the table
							model.addRow(new Object[] { excelRow.getCell(0), excelRow.getCell(1), excelRow.getCell(2),
									excelRow.getCell(3), excelRow.getCell(4), excelRow.getCell(5), excelRow.getCell(6),
									excelRow.getCell(7), excelRow.getCell(8), excelRow.getCell(9),
									excelRow.getCell(10) });
						}

						// Adicona o painel do lado direto com informações do projeto
						extractInfoIntoRightPanel(model);
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
			}

		});

		JButton button1 = new JButton("Import Java Files");
		button1.setBackground(Color.WHITE);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<File> files = new ArrayList<File>();
				File ficheirosJava;
				String ficheiroPath;

				String defaultCurrentDirectoryPath = "src/";
				JFileChooser fileChooser = new JFileChooser(defaultCurrentDirectoryPath);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int chooser = fileChooser.showOpenDialog(null);
				if (chooser == JFileChooser.APPROVE_OPTION) {
					ficheiroPath = fileChooser.getSelectedFile().getAbsolutePath();
					ficheirosJava = new File(ficheiroPath);
					if (ficheirosJava.isDirectory()) {
						// List<File> files = new ArrayList<File>();
						// guarda os ficheiros .java todos do diretÃ³rio numa lista
						UsefulMethods.listJavaFiles(ficheirosJava.getAbsolutePath(), files);

						// print files on screen
						for (File f : files) {
							System.out.println(f.getName());
						}
					} else if (ficheirosJava.isFile()) {
						System.out.println("Ã‰ um ficheiro");
					}
				}
				int[] smells = new int[5];
				smells[0] = 1;
				smells[1] = 1;
				smells[2] = 1;
				smells[3] = 1;
				smells[4] = 1;
				String project = fileChooser.getSelectedFile().getName();
				System.out.println(project);
				File directory = new File("metricas");
				if (!directory.exists())
					directory.mkdir();
				String excelDir = "metricas/";
				try {
					FileManagement a = new FileManagement(files, smells, excelDir, project);
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
					DefaultTableModel model = (DefaultTableModel) table.getModel();
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

					extractInfoIntoRightPanel(model);
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
		});

		JButton button2 = new JButton("Criar regra");
		button2.setBackground(Color.WHITE);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Primeira frame
				JanelasMetricas.gui_criar_regras();
				JanelasMetricas.loadCriarRegrasWindow();
			}
		});

		JButton button3 = new JButton("Regras guardadas");
		button3.setBackground(Color.WHITE);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelasMetricas.gui_metricas();
				JanelasMetricas.loadRegrasGuardadasWindow();
			}

		});

		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout());
		
		bottomPanel.add(button0);
		bottomPanel.add(button1);
		bottomPanel.add(button2);
		bottomPanel.add(button3);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
	}
	
	/**
	 * Method executed in function addFrameContent()
	 * set the layout of the panel located on the east side of the panel
	 * 
	 */

	public void eastPanel() {
		eastPanel = new JPanel();
		frame.getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
	}
	
	/**
	 * Method creates the main frame
	 * 
	 */

	public GUI() {
		frame = new JFrame("Excel Reader");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
	}
	
	/**
	 * Method joins the 3 panels in the main frame
	 * 
	 */

	public void addFrameContent() {
		frame.getContentPane().setLayout(new BorderLayout());
		topPanel();
		bottomPanel();
		eastPanel();
	}
	
	/**
	 * Method turns visible the main frame
	 * 
	 */

	public void open() {
		frame.setLocation(100, 50);
		frame.setSize(1050, 500);
		frame.setVisible(true);
	}
	
	/**
	 * Method extract all the information from the right panel
	 * The information consist in the number of packages, classes, methods and number of lines
	 * 
	 * @param model default table model
	 */

	private void extractInfoIntoRightPanel(DefaultTableModel model) {
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

		eastPanel.removeAll();

		eastPanel.add(new JLabel("Nº total de packages:"));
		eastPanel.add(new JLabel(String.valueOf(nPackages)));
		eastPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		eastPanel.add(new JLabel("Nº total de classes:"));
		eastPanel.add(new JLabel(String.valueOf(nClasses)));
		eastPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		eastPanel.add(new JLabel("Nº total de métodos:"));
		eastPanel.add(new JLabel(String.valueOf(nMethods)));
		eastPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		eastPanel.add(new JLabel("Nº total de linhas de código:"));
		eastPanel.add(new JLabel(String.valueOf(nLines)));
		eastPanel.revalidate();
	}

	/**
	 * Returns the result from this class
	 * The result is the main frame
	 * 
	 * @param args array of strings
	 */
	
	public static void main(String args[]) {
		GUI gui = new GUI();
		gui.open();
	}
}
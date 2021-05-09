package guis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import es_g46.EssentialMethodsForGUI;

/**
 * Class that opens the window showing all the buttons that have functionalites attached to them
 * 
 * @author g46
 * @version 2.0
 */

public class GUI {
 
	private JFrame frame;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel eastPanel;
	static JTable table;
	static JTable table2;
	static JTextArea eastPClassTextArea;
	static JTextArea eastPMethodTextArea;

	/**
	 * Method executed in function addFrameContent()
	 * Creates a table where it can present a excel sheet
	 * Creates a table to compare code smells
	 * 
	 */
	
	public void topPanel() {
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

	public void bottomPanel() {

		JButton button0 = new JButton("Import Excel");
		button0.setBackground(Color.WHITE);
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String defaultCurrentDirectoryPath = "src/";
				JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
				int excelChooser = excelFileChooser.showOpenDialog(null);
				// if import button is clicked
				if (excelChooser == JFileChooser.APPROVE_OPTION) {
					File excelFile = excelFileChooser.getSelectedFile();
					DefaultTableModel tModel = (DefaultTableModel) table.getModel();
					DefaultTableModel comparisonTModel = (DefaultTableModel) table2.getModel();
					EssentialMethodsForGUI.importExcelFile(excelFile, tModel, comparisonTModel);
					// Adicona o painel do lado direto com informações do projeto
					eastPClassTextArea = new JTextArea("");
					eastPMethodTextArea = new JTextArea("");
					EssentialMethodsForGUI.extractInfoIntoPanel(tModel, eastPanel, eastPClassTextArea, eastPMethodTextArea);
				}
			}

		});

		JButton button1 = new JButton("Import Java Files");
		button1.setBackground(Color.WHITE);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String defaultCurrentDirectoryPath = "src/";
				JFileChooser fileChooser = new JFileChooser(defaultCurrentDirectoryPath);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int chooser = fileChooser.showOpenDialog(null);
				if (chooser == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					DefaultTableModel tModel = (DefaultTableModel) table.getModel();
					DefaultTableModel comparisonTModel = (DefaultTableModel) table2.getModel();
					EssentialMethodsForGUI.importJavaFiles(file, tModel, comparisonTModel);
					// Adicona o painel do lado direto com informações do projeto
					EssentialMethodsForGUI.extractInfoIntoPanel(tModel, eastPanel, eastPClassTextArea, eastPMethodTextArea);
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
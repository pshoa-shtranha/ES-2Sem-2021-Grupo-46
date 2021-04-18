package es_g46.es_g46;

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

public class GUI {

	private JFrame frame;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JTable table;
	private void topPanel() {

		topPanel = new JPanel();
		frame.getContentPane().add(topPanel, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
						"is_God_Class", "LOC_method", "CYCLO_Method", "is_Long_Method" }));
		table.getColumnModel().getColumn(9).setPreferredWidth(91);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(table);
		JScrollPane js = new JScrollPane(table);
		topPanel.add(js);
	}
	
	private void bottomPanel() {

		JButton button0 = new JButton("Import Excel");
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
							//DefaultTableModel model = (DefaultTableModel) table.getModel();
							//model.setRowCount(0);
							// adding row to the table
							model.addRow(new Object[] { excelRow.getCell(0), excelRow.getCell(1), excelRow.getCell(2),
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
			}

		});

		JButton button1 = new JButton("Import Java Files");
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
						//List<File> files = new ArrayList<File>();
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
			    if (! directory.exists())
			        directory.mkdir();
				String excelDir = "metricas/";
				try {
				FileManagement a = new FileManagement(files, smells, excelDir, project);
				} catch(IOException i) {
					
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
						//DefaultTableModel model = (DefaultTableModel) table.getModel();
						//model.fireTableRowsDeleted(0, model.getColumnCount());
						// adding row to the table
						//model.setRowCount(0);
						model.addRow(new Object[] { excelRow.getCell(0), excelRow.getCell(1), excelRow.getCell(2),
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
		});

		JButton button2 = new JButton("Criar regra"); 
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Primeira frame
				JanelasMetricas.gui_criar_regras();
				JanelasMetricas.loadCriarRegrasWindow();
			}
		});
		
		JButton button3 = new JButton("Regras guardadas"); 
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelasMetricas.gui_metricas();
				JanelasMetricas.loadRegrasGuardadasWindow();
			}

			
		});
		
		bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout());

		bottomPanel.add(button0);
		bottomPanel.add(button1);
		bottomPanel.add(button2);
		bottomPanel.add(button3);
	}

	
	public GUI() {
		frame = new JFrame("Excel Reader");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
	}

	private void addFrameContent() {
		frame.getContentPane().setLayout(new BorderLayout());
		topPanel();
		bottomPanel();
	}
	
	public void open() {
		frame.setLocation(100, 50);
		frame.setSize(1050, 500);
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		GUI gui = new GUI();
		gui.open();
	}
}
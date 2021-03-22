package es_g46.es_g46;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.BoxLayout;

public class GUI {

	private JFrame frame;
	private JFrame frame_metricas;
	private JPanel bottomPanel;
	private JPanel topPanel;
	private File file;
	private String path = "C:\\Users\\marga\\OneDrive\\Ambiente de Trabalho\\Code_Smells.xlsx";
	private JTable table;

	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	private JCheckBox checkBox3;
	private JCheckBox checkBox4;
	private JCheckBox checkBox5;
	private JTextField text_metricas;

	private int[] metricas;
	private File[] list;

	public GUI() {
		frame = new JFrame("Excel Reader");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
	}

	public void gui_metricas() {

		frame_metricas = new JFrame("metricas");
		frame_metricas.getContentPane().setLayout(new BorderLayout());
		frame_metricas.pack();
		frame_metricas.setLocation(100, 50);
		frame_metricas.setSize(100, 20);
		frame_metricas.setVisible(true);
	}

	public void open() {
		frame.setLocation(100, 50);
		frame.setSize(1050, 500);
		frame.setVisible(true);
	}

	/*
	 * public void showExcel(){
	 * 
	 * file = new File(path); String line = null; System.out.println("aqui aqui");
	 * try { Scanner scanner = new Scanner(file); System.out.println("please");
	 * while(scanner.hasNextLine()) { line = scanner.nextLine(); System.out.println(
	 * "aqui"); } scanner.close(); } catch (FileNotFoundException e) {
	 * System.err.println("Ficheiro não encontrado"); } System.out.println(line);
	 * textArea = new JTextArea(line); frame.add(textArea, BorderLayout.CENTER); }
	 * 
	 * public void showExcel() { { try {
	 * 
	 * file = new File(path); //creating a new file instance FileInputStream fis =
	 * new FileInputStream(path); //obtaining bytes from the file //creating
	 * Workbook instance that refers to .xlsx file XSSFWorkbook wb = new
	 * XSSFWorkbook(fis); XSSFSheet sheet = wb.getSheetAt(0); //creating a Sheet
	 * object to retrieve object Iterator<Row> itr = sheet.iterator(); //iterating
	 * over excel file while (itr.hasNext()) { Row row = itr.next(); Iterator<Cell>
	 * cellIterator = row.cellIterator(); //iterating over each column while
	 * (cellIterator.hasNext()) { Cell cell = cellIterator.next(); switch
	 * (cell.getCellType()) { case Cell.CELL_TYPE_STRING: //field that represents
	 * string cell type System.out.print(cell.getStringCellValue() + "\t\t\t");
	 * break; case Cell.CELL_TYPE_NUMERIC: //field that represents number cell type
	 * System.out.print(cell.getNumericCellValue() + "\t\t\t"); break; default: } }
	 * System.out.println(""); } } catch(Exception e) { e.printStackTrace(); } } }
	 */
	private int[] buttonMetricas() {

		metricas = new int[5];
		metricas[0] = 0;
		metricas[1] = 0;
		metricas[2] = 0;
		metricas[3] = 0;
		metricas[4] = 0;

		for (int i = 0; i < metricas.length; i++) {
			if (checkBox1.isSelected()) {
				metricas[0] = 1;

			}
			if (checkBox2.isSelected()) {
				metricas[1] = 1;

			}
			if (checkBox3.isSelected()) {
				metricas[2] = 1;

			}
			if (checkBox4.isSelected()) {
				metricas[3] = 1;

			}
			if (checkBox5.isSelected()) {
				metricas[4] = 1;

			} else {
				// System.out.println("erro");
			}
			System.out.println(metricas[i]);
		}
		return metricas;
	}

	private void lowPanel() {

		JButton button0 = new JButton("Import Excel");
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File excelFile;
				FileInputStream excelFIS = null;
				BufferedInputStream excelBIS = null;
				XSSFWorkbook excelJtableImport = null;

				String defaultCurrentDirectoryPath = "src/";
				JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
				int excelChooser = excelFileChooser.showOpenDialog(null);
				// if import button is clicked
				if (excelChooser == JFileChooser.APPROVE_OPTION) {
					try {
						excelFile = excelFileChooser.getSelectedFile();
						excelFIS = new FileInputStream(excelFile);
						excelBIS = new BufferedInputStream(excelFIS);

						excelJtableImport = new XSSFWorkbook(excelBIS);
						XSSFSheet excelSheet = excelJtableImport.getSheetAt(0);

						// Looping through excel columns and rows (começa na 2a fila)
						for (int row = 1; row < excelSheet.getLastRowNum(); row++) {
							XSSFRow excelRow = excelSheet.getRow(row);

							DefaultTableModel model = (DefaultTableModel) table.getModel();
							// adding row to the table
							model.addRow(new Object[] { excelRow.getCell(0), excelRow.getCell(1), excelRow.getCell(2),
									excelRow.getCell(3), excelRow.getCell(4), excelRow.getCell(5), excelRow.getCell(6),
									excelRow.getCell(7), excelRow.getCell(8), excelRow.getCell(9),
									excelRow.getCell(10) });

//							for(int column = 0; column < excelRow.getLastCellNum(); column++) {
//								XSSFCell excelCell = excelRow.getCell(column);
//								if(excelCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
//									System.out.println(excelCell.getNumericCellValue());									
//								}
//								else if(excelCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
//									System.out.println(excelCell.getStringCellValue());
//								}
//							}
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
						List<File> files = new ArrayList<File>();
						//guarda os ficheiros .java todos do diretório numa lista
						UsefulMethods.listJavaFiles(ficheirosJava.getAbsolutePath(), files);
						
						//print files on screen
						for (File f : files) {
							System.out.println(f.getName());
						}
					} else if (ficheirosJava.isFile()) {
						System.out.println("É um ficheiro");
					}
				}
			}
		});
		
		
		JButton button2 = new JButton("Add Rule");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// colocar ação do botão
			}
		});

		JButton button3 = new JButton("Delete all Rules");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// colocar ação do botão
			}
		});

		JButton button4 = new JButton("Detect Code Smells");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// colocar ação do botão
			}
		});

		JButton button5 = new JButton("Evaluate Quality");
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// colocar ação do botão
			}
		});

		JButton button6 = new JButton("Regras guardadas");
		button6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				gui_metricas();

				JLabel label_metricas = new JLabel("Where is the file?");
				text_metricas = new JTextField(10);

				JPanel frame2 = new JPanel();
				frame_metricas.getContentPane().add(frame2, BorderLayout.NORTH);
				frame2.setLayout(new FlowLayout());

				frame2.add(label_metricas);
				frame2.add(text_metricas);

				JPanel frame3 = new JPanel();
				frame_metricas.getContentPane().add(frame3, BorderLayout.CENTER);
				frame3.setLayout(new GridLayout(6, 1));

				checkBox1 = new JCheckBox("NOM_class");
				checkBox2 = new JCheckBox("LOC_class");
				checkBox3 = new JCheckBox("WMC_class");
				checkBox4 = new JCheckBox("LOC_method");
				checkBox5 = new JCheckBox("CYCLO_method");

				frame3.add(checkBox1);
				frame3.add(checkBox2);
				frame3.add(checkBox3);
				frame3.add(checkBox4);
				frame3.add(checkBox5);

				JButton button20 = new JButton("Check");
				button20.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						buttonMetricas();
						// listFiles();
					}
				});

				frame_metricas.getContentPane().add(button20, BorderLayout.SOUTH);
			}
		});

		/*
		 * JButton button7 = new JButton("Data Size"); button7.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { //colocar
		 * ação do botão } });
		 * 
		 * JButton button8 = new JButton("Exit"); button7.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { //colocar
		 * ação do botão } });
		 */
		JButton button9 = new JButton("Choose metricas");
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui_metricas();

				JLabel label_metricas = new JLabel("Where is the file?");
				JTextField text_metricas = new JTextField(10);

				JPanel frame2 = new JPanel();
				frame_metricas.getContentPane().add(frame2, BorderLayout.NORTH);
				frame2.setLayout(new FlowLayout());

				frame2.add(label_metricas);
				frame2.add(text_metricas);

				JPanel frame3 = new JPanel();
				frame_metricas.getContentPane().add(frame3, BorderLayout.CENTER);
				frame3.setLayout(new GridLayout(6, 1));

				JCheckBox checkBox1 = new JCheckBox("0");
				JCheckBox checkBox2 = new JCheckBox("1");
				JCheckBox checkBox3 = new JCheckBox("2");
				JCheckBox checkBox4 = new JCheckBox("3");
				JCheckBox checkBox5 = new JCheckBox("4");

				frame3.add(checkBox1);
				frame3.add(checkBox2);
				frame3.add(checkBox3);
				frame3.add(checkBox4);
				frame3.add(checkBox5);

				// check state
				if (checkBox1.isSelected()) {

					// do something...

				} else {

					// do something else...

				} // (----------------COMPLETAR---------------)

				JButton button20 = new JButton("Check");
				button20.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// colocar ação do botão
					}
				});

				frame_metricas.getContentPane().add(button20, BorderLayout.SOUTH);
			}
		});

		bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout());

		bottomPanel.add(button0);
		bottomPanel.add(button1);
		bottomPanel.add(button2);
		bottomPanel.add(button3);
		bottomPanel.add(button4);
		bottomPanel.add(button5);
		bottomPanel.add(button6);
//		topPanel.add(button7);
//		topPanel.add(button8);
		bottomPanel.add(button9);

	}

	

	private void upPanel() {

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
//		js.setVisible(true);
	}

	private void addFrameContent() {
		frame.getContentPane().setLayout(new BorderLayout());
		upPanel();
		lowPanel();
	}

	public static void main(String args[]) {
		GUI gui = new GUI();
		gui.open();
	}
}
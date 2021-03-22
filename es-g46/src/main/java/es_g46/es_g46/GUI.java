package es_g46.es_g46;

import java.awt.BorderLayout;
import  java.io.*;
import java.util.Scanner;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.swing.table.DefaultTableModel;

public class GUI {
	
	private JFrame frame;
	private JFrame frame_metricas;
	private JPanel bottomPanel;
	private JPanel topPanel;
	private File file;
	private String path = "C:\\Users\\marga\\OneDrive\\Ambiente de Trabalho\\Code_Smells.xlsx";
	private JTable table;
	

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
	public void showExcel(){

		file = new File(path);
		String line = null;
	System.out.println("aqui aqui");
		try {
			Scanner scanner = new Scanner(file);
			System.out.println("please");
			while(scanner.hasNextLine()) {
				line = scanner.nextLine();	
				System.out.println( "aqui");
			}
			scanner.close();
			} catch (FileNotFoundException e) {
				System.err.println("Ficheiro não encontrado");
			}
		System.out.println(line);
		textArea = new JTextArea(line);
		frame.add(textArea, BorderLayout.CENTER);
    }

	public void showExcel() {
		{  
			try  
			{ 
				
			file = new File(path);   //creating a new file instance  
			FileInputStream fis = new FileInputStream(path);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			while (itr.hasNext())                 
			{  
			Row row = itr.next();  
			Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
			while (cellIterator.hasNext())   
			{  
			Cell cell = cellIterator.next();  
			switch (cell.getCellType())               
			{  
			case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
			System.out.print(cell.getStringCellValue() + "\t\t\t");  
			break;  
			case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type  
			System.out.print(cell.getNumericCellValue() + "\t\t\t");  
			break;  
			default:  
			}  
			}  
			System.out.println("");  
			}  
			}  
			catch(Exception e)  
			{  
			e.printStackTrace();  
			}  
			}  
	}
	*/
	private void lowPanel() {
		
		JButton button = new JButton("Show Excel"); 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
				//showExcel();
			}
		});
		
		JButton button1 = new JButton("Reset Excel"); 
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
		
		JButton button2 = new JButton("Add Rule"); 
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
		
		JButton button3 = new JButton("Delete all Rules"); 
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
		
		JButton button4 = new JButton("Detect Code Smells"); 
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
		
		JButton button5 = new JButton("Evaluate Quality"); 
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
		
		JButton button6 = new JButton("Existing Rules"); 
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
/*		
		JButton button7 = new JButton("Data Size"); 
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
		
		JButton button8 = new JButton("Exit"); 
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
*/		
		JButton button9 = new JButton("Choose metricas"); 
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui_metricas();
				
							
				JLabel label_metricas = new JLabel("Where is the file?");
				JTextField text_metricas = new JTextField(10);

				JPanel frame2 = new JPanel(); 
				frame_metricas.getContentPane().add(frame2, BorderLayout.NORTH);
				frame2.setLayout(new FlowLayout() );
				
				frame2.add(label_metricas);
				frame2.add(text_metricas);
				
				JPanel frame3 = new JPanel(); 
				frame_metricas.getContentPane().add(frame3, BorderLayout.CENTER);
				frame3.setLayout(new GridLayout(6,1));
				
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
		         
		        }//(----------------COMPLETAR---------------)
		        
		        JButton button20 = new JButton("Check"); 
				button20.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//colocar ação do botão
					}
				});
				
				frame_metricas.getContentPane().add(button20, BorderLayout.SOUTH);			
			}
		});
		
		bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout());
		
		bottomPanel.add(button);
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
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(1,5));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class", "LOC_method", "CYCLO_Method"},
			},
			new String[] {
				"MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class", "LOC_method", "CYCLO_Method"
			}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(8).setPreferredWidth(91);
		topPanel.add(table);
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
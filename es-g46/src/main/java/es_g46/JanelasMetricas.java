package es_g46;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Class that creates the windows when a button is pushed
 * 
 * @author Margarida Correia , Pavlo and Beatriz Patricio
 * @version 2.0
 */

public class JanelasMetricas {
	private static JFrame frame_criar_regras;
	private static JFrame frame_criar_regras1;
	private static JFrame frame_criar_regras2;
	private static JPanel topPanel_regras;
	private static JPanel bottomPanel_regras;
	private static JPanel bottomPanel_regrasv2;
	private static JPanel middlePanel_regras;
	private static JPanel middlePanel_regras2;
	private static JPanel topPanel_regras2;
	private static JPanel bottomPanel_regras2;
	private static JPanel bottomPanel_regras222;
	private static JComboBox<?> comboBox1Name;
	private static JComboBox<?> comboBox2Sign;
	private static JComboBox<?> comboBox3Operator;
	private static JComboBox<?> comboBox4Name2;
	private static JComboBox<?> comboBox5Sign2;
	private static JComboBox<?> comboBox1v2Name;
	private static JComboBox<?> comboBox2v2Sign;
	private static JComboBox<?> comboBox3v2Operator;
	private static JComboBox<?> comboBox4v2Name2;
	private static JComboBox<?> comboBox5v2Sign2;
	private static JTextField text_regras;
	private static JTextField text_regras1;
	private static JTextField text_regrasv2;
	private static JTextField text_regras1v2;
	private static JLabel label_regras1;
	private static JLabel label_regras1v2;
	private static ButtonGroup radio_buttons;
	private static JRadioButton radio_button;
	private static JRadioButton radio_button1;
	private static JButton b;
	private static String[] string_regras;
	private static String[] string_regras2;
	private static JFrame frame_metricas;
	private static JList<String> list;


	/**
	 * Returns a window when the button of create rules is pushed
	 * 
	 */
	
	// janela de quando se carrega no botao "criar regras"
	public static void gui_criar_regras() {

		frame_criar_regras = new JFrame("Defenir regras");
		frame_criar_regras.setLayout(new GridLayout(3, 0));
		frame_criar_regras.pack();
	//	frame_criar_regras.getContentPane().setBackground(Color.WHITE);
		frame_criar_regras.setLocation(100, 50);
		frame_criar_regras.setSize(250, 140);
		frame_criar_regras.setVisible(true);
	}
	
	/**
	 * Creates the initial layout of the frame
	 * It´s added 2 radio buttons to choose the next window to open
	 * It´s added a button to check to open the window
	 * 
	 */
	
	public static void loadCriarRegrasWindow() {
		radio_buttons = new ButtonGroup();
		radio_button = new JRadioButton("Class");
		radio_button.setBackground(Color.WHITE);
		radio_buttons.add(radio_button);
		radio_button1 = new JRadioButton("Method");
		radio_button1.setBackground(Color.WHITE);
		radio_buttons.add(radio_button1);

		frame_criar_regras.add(radio_button);
		frame_criar_regras.add(radio_button1);

		JButton button30 = new JButton("Check");
		button30.setBackground(Color.white);
		button30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Segunda e Terceira frame
				if (radio_button.isSelected() && radio_button1.isSelected()) {
					JOptionPane.showMessageDialog(b, "Tente novamente. Escolha apenas uma opção.");

				} else if (radio_button.isSelected()) {
					gui_regra1();
					frameRegra1();

				} else if (radio_button1.isSelected()) {
					gui_regra2();
					frameRegra2();

				} else {
					System.out.println("nada foi selecionado");
				}
			}
		});
		frame_criar_regras.add(button30);
	}
	
	/**
	 * Returns a new window when the option chosen in the initial window was "class"
	 * 
	 */
	
	// janela abre qd se escolhe opçao de class
	public static void gui_regra1() {

		frame_criar_regras1 = new JFrame("Regra_class");
		frame_criar_regras1.setLayout(new GridLayout(5, 0));
		frame_criar_regras1.pack();
		frame_criar_regras1.setLocation(100, 50);
		frame_criar_regras1.setSize(350, 320);
		frame_criar_regras1.setVisible(true);
		frame_criar_regras1.getContentPane().setBackground(Color.WHITE);
		
	}
	
	/**
	 * Returns a new window when the option chosen in the initial window was "method"
	 * 
	 */

	// janela abre qd se escolhe opçao de method
	public static void gui_regra2() {

		frame_criar_regras2 = new JFrame("Regra_method");
		frame_criar_regras2.setLayout(new GridLayout(5, 0));
		frame_criar_regras2.pack();
		frame_criar_regras2.setLocation(100, 50);
		frame_criar_regras2.setSize(350, 320);
		frame_criar_regras2.setVisible(true);
		frame_criar_regras2.getContentPane().setBackground(Color.WHITE);
	}
	
	/**
	 * Layout of the frame chosen as "class"
	 * It has some spaces that need to be filled by the user to create the rules
	 * Those spaces consist in all the classes available, logic signs and the limits
	 * 3 buttons to save, upload or submit the rules chosen
	 * Button save has the functionality of saving rules in a file and in the portofolio "regras"
	 * Button load has the functionality of let the people select a file from the portfolio "regras" and filled the spaces
	 * Button submit has the functionality of execute the rules
	 * 
	 */
	
	// Janelas do defenir regras
	public static void frameRegra1() {

		// Regra1
		label_regras1v2 = new JLabel("");

		frame_criar_regras1.add(label_regras1v2);
		topPanel_regras = new JPanel();
		topPanel_regras.setBackground(Color.WHITE);
		frame_criar_regras1.add(topPanel_regras);
		topPanel_regras.setLayout(new FlowLayout());

		String opcoes[] = { "NOM_class", "LOC_class", "WMC_class" };
		String sinais[] = { "<", ">", "=" };
		String logica[] = { "AND", "OR" };

		comboBox1Name = new JComboBox<Object>(opcoes);
		comboBox2Sign = new JComboBox<Object>(sinais);
		comboBox3Operator = new JComboBox<Object>(logica);
		comboBox1Name.setBackground(Color.WHITE);
		comboBox2Sign.setBackground(Color.WHITE);
		comboBox3Operator.setBackground(Color.WHITE);

		text_regras = new JTextField(10);

		topPanel_regras.add(comboBox1Name);
		topPanel_regras.add(comboBox2Sign);
		topPanel_regras.add(text_regras);

		middlePanel_regras = new JPanel();
		middlePanel_regras.setBackground(Color.WHITE);
		frame_criar_regras1.add(middlePanel_regras);
		middlePanel_regras.setLayout(new FlowLayout());

		middlePanel_regras.add(comboBox3Operator);

		// Regra2

		bottomPanel_regras = new JPanel();
		bottomPanel_regras.setBackground(Color.WHITE);
		frame_criar_regras1.add(bottomPanel_regras);
		bottomPanel_regras.setLayout(new FlowLayout());

		comboBox4Name2 = new JComboBox<Object>(opcoes);
		comboBox5Sign2 = new JComboBox<Object>(sinais);
		comboBox4Name2.setBackground(Color.WHITE);
		comboBox5Sign2.setBackground(Color.WHITE);

		text_regras1 = new JTextField(10);

		bottomPanel_regras.add(comboBox4Name2);
		bottomPanel_regras.add(comboBox5Sign2);
		bottomPanel_regras.add(text_regras1);

		bottomPanel_regrasv2 = new JPanel();
		bottomPanel_regrasv2.setBackground(Color.WHITE);
		frame_criar_regras1.add(bottomPanel_regrasv2);
		bottomPanel_regrasv2.setLayout(new FlowLayout());

		JButton button33 = new JButton("Save");
		button33.setBackground(Color.WHITE);
		button33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveRegras();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton submitButton = new JButton("Submit");
		submitButton.setBackground(Color.WHITE);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] stringRegras = new String[7];
				stringRegras[0] = (String) comboBox1Name.getSelectedItem();
				stringRegras[1] = (String) comboBox2Sign.getSelectedItem();
				stringRegras[2] = text_regras.getText();
				stringRegras[3] = (String) comboBox3Operator.getSelectedItem();
				stringRegras[4] = (String) comboBox4Name2.getSelectedItem();
				stringRegras[5] = (String) comboBox5Sign2.getSelectedItem();
				stringRegras[6] = text_regras1.getText();
				
				
				//Definir modelos para adicionar regras à tabela 2
				DefaultTableModel excelTModel = (DefaultTableModel) GUI.table.getModel();
				DefaultTableModel comparisonTModel = (DefaultTableModel) GUI.table2.getModel();
				
				//Make table 2 have the same number of rows as table 1, also clears any rows table 2 might've had
				if (excelTModel.getRowCount() != comparisonTModel.getRowCount()) {
					comparisonTModel.setRowCount(0);
					for (int i = 0; i < excelTModel.getRowCount(); i++) {
						comparisonTModel.addRow(new Object[]{null,null,null,null});
					}
				}
				
				//Verifica-se onde é que ele vai buscar a informação para comparar
				int excelTClassCol1;
				excelTClassCol1 = getColInxOfSelMetric(stringRegras[0]);
				int excelTClassCol2;
				excelTClassCol2 = getColInxOfSelMetric(stringRegras[4]);
				for (int i = 0; i < excelTModel.getRowCount(); i++) {
					//sets table 2 class name
					comparisonTModel.setValueAt(excelTModel.getValueAt(i, GUI.table.getColumn("class").getModelIndex()), i, 0);
					//sets table 2 manual god class
					comparisonTModel.setValueAt(excelTModel.getValueAt(i, GUI.table.getColumn("is_God_Class").getModelIndex()), i, 1);
					//sets table 2 auto-generated god class
					writeGeneratedCodeSmellToComparisonTable(excelTModel, comparisonTModel, stringRegras, excelTClassCol1, excelTClassCol2, i, 2);
					//set verdadeiros positivos and stuff for Class 
					setDetectionQualityInCell2(comparisonTModel, i, 1, 2, 3);
				}
			}
		});

		JButton button33v2 = new JButton("Load");
		button33v2.setBackground(Color.WHITE);
		button33v2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File FicheirosTXT;
				String ficheiroPath;
				File directory = new File("regras");
			    if (! directory.exists())
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
						UsefulMethods.listJavaFiles(FicheirosTXT.getAbsolutePath(), files);

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
							    String everythingReplace = everything.replace("[","");
							    String everythingReplace2 = everythingReplace.replace("]","");
							    
							    System.out.println(everythingReplace2);
							    String[] ss=everythingReplace2.split(",");
							    ss[1]=ss[1].replace(" ","");
							    ss[3]=ss[3].replace(" ","");
							    ss[4]=ss[4].replace(" ","");
							    ss[5]=ss[5].replace(" ","");
							    

							    
							   
		    
							    for(int i =0; i<3;i++)
							    {
							    	
							    	String textoDaComboBox1=comboBox1Name.getItemAt(i).toString();
							   if( textoDaComboBox1.equals(ss[0]))
							   {						   	
								   comboBox1Name.setSelectedIndex(i);}
							    String textoDaComboBox2=comboBox2Sign.getItemAt(i).toString();
							    if( textoDaComboBox2.equals(ss[1]))
								   {
									   comboBox2Sign.setSelectedIndex(i);
									   }
							    String textoDaComboBox5="";
							    if(i<2)
							    { textoDaComboBox5=comboBox3Operator.getItemAt(i).toString();
							 
							    if( textoDaComboBox5.equals(ss[3]))
								   {
									   comboBox3Operator.setSelectedIndex(i);
									   }
							    }
							    String textoDaComboBox3=comboBox4Name2.getItemAt(i).toString();
								   if( textoDaComboBox3.equals(ss[4]))
								   {							   	
									   comboBox4Name2.setSelectedIndex(i);}
								   String textoDaComboBox4=comboBox5Sign2.getItemAt(i).toString();
								   if( textoDaComboBox4.equals(ss[5]))
								   {							   	
									   comboBox5Sign2.setSelectedIndex(i);}
								  
								   						   	
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
		});
		bottomPanel_regrasv2.add(button33);
		bottomPanel_regrasv2.add(submitButton);
		bottomPanel_regrasv2.add(button33v2);
	}
	
	/**
	 * Layout of the frame chosen as "method"
	 * It has some spaces that need to be filled by the user to create the rules
	 * Those spaces consist in all the classes available, logic signs and the limits
	 * 3 buttons to save, upload or submit the rules chosen
	 * Button save has the functionality of saving rules in a file and in the pastes "rules"
	 * Button load has the functionality of let the people select a file from the pastes "rules" and filled the spaces
	 * Button submit has the functionality of execute the rules
	 * 
	 */

	private static void frameRegra2() {

		label_regras1 = new JLabel("");
		frame_criar_regras2.add(label_regras1);

		// Regra1

		topPanel_regras2 = new JPanel();
		topPanel_regras2.setBackground(Color.WHITE);
		frame_criar_regras2.add(topPanel_regras2);
		topPanel_regras2.setLayout(new FlowLayout());

		String opcoes[] = { "LOC_method", "CYCLO_Method" };
		String sinais[] = { "<", ">", "=" };
		String logica[] = { "AND", "OR" };

		comboBox1v2Name = new JComboBox<Object>(opcoes);
		comboBox2v2Sign = new JComboBox<Object>(sinais);
		comboBox3v2Operator = new JComboBox<Object>(logica);
		comboBox1v2Name.setBackground(Color.WHITE);
		comboBox2v2Sign.setBackground(Color.WHITE);
		comboBox3v2Operator.setBackground(Color.WHITE);

		text_regrasv2 = new JTextField(10);

		topPanel_regras2.add(comboBox1v2Name);
		topPanel_regras2.add(comboBox2v2Sign);
		topPanel_regras2.add(text_regrasv2);

		middlePanel_regras2 = new JPanel();
		middlePanel_regras2.setBackground(Color.WHITE);
		frame_criar_regras2.add(middlePanel_regras2);
		middlePanel_regras2.setLayout(new FlowLayout());

		middlePanel_regras2.add(comboBox3v2Operator);

		// Regra2

		bottomPanel_regras2 = new JPanel();
		bottomPanel_regras2.setBackground(Color.WHITE);
		frame_criar_regras2.add(bottomPanel_regras2);
		bottomPanel_regras2.setLayout(new FlowLayout());

		comboBox4v2Name2 = new JComboBox<Object>(opcoes);
		comboBox5v2Sign2 = new JComboBox<Object>(sinais);
		comboBox4v2Name2.setBackground(Color.WHITE);
		comboBox5v2Sign2.setBackground(Color.WHITE);

		text_regras1v2 = new JTextField(10);

		bottomPanel_regras2.add(comboBox4v2Name2);
		bottomPanel_regras2.add(comboBox5v2Sign2);
		bottomPanel_regras2.add(text_regras1v2);

		bottomPanel_regras222 = new JPanel();
		bottomPanel_regras222.setBackground(Color.WHITE);
		frame_criar_regras2.add(bottomPanel_regras222);
		bottomPanel_regras222.setLayout(new FlowLayout());

		JButton button33v2 = new JButton("Save");
		button33v2.setBackground(Color.WHITE);
		button33v2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveRegras2();
			}
		});
		JButton submitButton = new JButton("Submit");
		submitButton.setBackground(Color.WHITE);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] stringRegras = new String[7];
				stringRegras[0] = (String) comboBox1v2Name.getSelectedItem();
				stringRegras[1] = (String) comboBox2v2Sign.getSelectedItem();
				stringRegras[2] = text_regrasv2.getText();
				stringRegras[3] = (String) comboBox3v2Operator.getSelectedItem();
				stringRegras[4] = (String) comboBox4v2Name2.getSelectedItem();
				stringRegras[5] = (String) comboBox5v2Sign2.getSelectedItem();
				stringRegras[6] = text_regras1v2.getText();
				
				
				//Definir modelos para adicionar regras à tabela 2
				DefaultTableModel excelTModel = (DefaultTableModel) GUI.table.getModel();
				DefaultTableModel comparisonTModel = (DefaultTableModel) GUI.table2.getModel();
				
				//Make table 2 have the same number of rows as table 1, also clears any rows table 2 might've had
				if (excelTModel.getRowCount() != comparisonTModel.getRowCount()) {
					comparisonTModel.setRowCount(0);
					for (int i = 0; i < excelTModel.getRowCount(); i++) {
						comparisonTModel.addRow(new Object[]{null,null,null,null});
					}
				}
				
				//Verifica-se onde é que ele vai buscar a informação para comparar
				int excelTClassCol1;
				excelTClassCol1 = getColInxOfSelMetric(stringRegras[0]);
				int excelTClassCol2;
				excelTClassCol2 = getColInxOfSelMetric(stringRegras[4]);
				for (int i = 0; i < excelTModel.getRowCount(); i++) {
					//sets method id on table 2
					comparisonTModel.setValueAt(i + 1, i, 4);
					//sets table 2 class name
					comparisonTModel.setValueAt(excelTModel.getValueAt(i, GUI.table.getColumn("method").getModelIndex()), i, 5);
					//sets table 2 manual god class
					comparisonTModel.setValueAt(excelTModel.getValueAt(i, GUI.table.getColumn("is_Long_Method").getModelIndex()), i, 6);
					writeGeneratedCodeSmellToComparisonTable(excelTModel, comparisonTModel, stringRegras, excelTClassCol1, excelTClassCol2, i, 7);
					//set verdadeiros positivos and stuff for Method
					setDetectionQualityInCell2(comparisonTModel, i, 6, 7, 8);
				}
			}
		});
		JButton button33v22 = new JButton("Load");
		button33v22.setBackground(Color.WHITE);
		button33v22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File FicheirosTXT;
				String ficheiroPath;
				File directory = new File("regras");
			    if (! directory.exists())
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
						UsefulMethods.listJavaFiles(FicheirosTXT.getAbsolutePath(), files);

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
							    String everythingReplace = everything.replace("[","");
							    String everythingReplace2 = everythingReplace.replace("]","");
							    
							    System.out.println(everythingReplace2);
							    String[] ss=everythingReplace2.split(",");
							    ss[1]=ss[1].replace(" ","");
							    ss[3]=ss[3].replace(" ","");
							    ss[4]=ss[4].replace(" ","");
							    ss[5]=ss[5].replace(" ","");
							    

							    
							   
		    
							    for(int i =0; i<3;i++)
							    {
							    	if(i<2) {	
							    	String textoDaComboBox1=comboBox1v2Name.getItemAt(i).toString();
							   if( textoDaComboBox1.equals(ss[0]))
							   {						   	
								   comboBox1v2Name.setSelectedIndex(i);}}
							    String textoDaComboBox2=comboBox2v2Sign.getItemAt(i).toString();
							    if( textoDaComboBox2.equals(ss[1]))
								   {
									   comboBox2v2Sign.setSelectedIndex(i);
									   }
							    String textoDaComboBox5="";
							    if(i<2)
							    { textoDaComboBox5=comboBox3v2Operator.getItemAt(i).toString();
							 
							    if( textoDaComboBox5.equals(ss[3]))
								   {
									   comboBox3v2Operator.setSelectedIndex(i);
									   }
							    }
							    if(i<2) {
							    String textoDaComboBox3=comboBox4v2Name2.getItemAt(i).toString();
								   if( textoDaComboBox3.equals(ss[4]))
								   {							   	
									   comboBox4v2Name2.setSelectedIndex(i);}}
								   String textoDaComboBox4=comboBox5v2Sign2.getItemAt(i).toString();
								   if( textoDaComboBox4.equals(ss[5]))
								   {							   	
									   comboBox5v2Sign2.setSelectedIndex(i);}
								  
								   						   	
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
		});
		bottomPanel_regras222.add(button33v2);
		bottomPanel_regras222.add(submitButton);
		bottomPanel_regras222.add(button33v22);
	}

	/**
	 * Save the rules chosen in the frame "class"
	 * 
	 * @throws UnsupportedEncodingException if the Character Encoding is not supported
	 * @throws FileNotFoundException If a file was not found
	 * @throws IOexception if an I/O exception of some sort has occurred
	 * 
	 * @return Array of Strings includes the saved rules 
	 */
	
	// retorna um array de String com as regras criadas
	private static String[] saveRegras() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		string_regras = new String[7];

		for (int i = 0; i < string_regras.length; i++) {
			string_regras[0] = (String) comboBox1Name.getSelectedItem();
			string_regras[1] = (String) comboBox2Sign.getSelectedItem();
			string_regras[2] = text_regras.getText();
			string_regras[3] = (String) comboBox3Operator.getSelectedItem();
			string_regras[4] = (String) comboBox4Name2.getSelectedItem();
			string_regras[5] = (String) comboBox5Sign2.getSelectedItem();
			string_regras[6] = text_regras1.getText();
		}
		System.out.println(Arrays.toString(string_regras));		
		File directory = new File("regras");
	    if (! directory.exists())
	        directory.mkdir();
		 try{
		        String nomeFicheiro= Arrays.toString(string_regras);
			if(nomeFicheiro.contains("<"))
			nomeFicheiro = nomeFicheiro.replace("<", "MENOR");
			if(nomeFicheiro.contains(">"))
				nomeFicheiro = nomeFicheiro.replace("<", "MAIOR");
			
			
			System.out.println(nomeFicheiro);
		        Writer output = null;
		        File file = new File("regras/"+"Class"+nomeFicheiro+ ".txt");
		        file.createNewFile();
		        output = new BufferedWriter(new FileWriter(file));

		        output.write(Arrays.toString(string_regras));

		        output.close();
		        System.out.println("File has been written");

		    }catch(Exception e){
		        System.out.println("Could not create file");
		    }
		
		return string_regras;
	}
	
	/**
	 * Save the rules chosen in the frame "method"
	 * 
	 * @return Array of Strings includes the saved rules 
	 */

	private static String[] saveRegras2() {
		string_regras2 = new String[7];

		for (int i = 0; i < string_regras2.length; i++) {
			string_regras2[0] = (String) comboBox1v2Name.getSelectedItem();
			string_regras2[1] = (String) comboBox2v2Sign.getSelectedItem();
			string_regras2[2] = text_regrasv2.getText();
			string_regras2[3] = (String) comboBox3v2Operator.getSelectedItem();
			string_regras2[4] = (String) comboBox4v2Name2.getSelectedItem();
			string_regras2[5] = (String) comboBox5v2Sign2.getSelectedItem();
			string_regras2[6] = text_regras1v2.getText();
		}
		System.out.println(Arrays.toString(string_regras2));
		File directory = new File("regras");
	    if (! directory.exists())
	        directory.mkdir();
		 try{
		        String nomeFicheiro= Arrays.toString(string_regras2);
				if(nomeFicheiro.contains("<"))
				nomeFicheiro = nomeFicheiro.replace("<", "MENOR");
				if(nomeFicheiro.contains(">"))
					nomeFicheiro = nomeFicheiro.replace("<", "MAIOR");
		        Writer output = null;
		        File file = new File("regras/"+"Method"+ nomeFicheiro+ ".txt");
		        output = new BufferedWriter(new FileWriter(file));

		        output.write(Arrays.toString(string_regras2));

		        output.close();
		        System.out.println("File has been written");

		    }catch(Exception e){
		        System.out.println("Could not create file");
		    }
		return string_regras2;
	}

	/**
	 * Returns a window when the button of saved rules is pushed
	 * 
	 */
	
	// janela de quando se carrega no botao "regras guardadas"
	public static void gui_metricas() {

		frame_metricas = new JFrame("metricas");
		frame_metricas.setLayout(new BorderLayout());
		frame_metricas.pack();
		frame_metricas.setLocation(100, 50);
		frame_metricas.setSize(500, 500);
		frame_metricas.setVisible(true);
	}
	
	/**
	 * Load the saved rules into a list of files is a specified directory
	 * Creation of a button "Submit"
	 * 
	 */
	
	static void loadRegrasGuardadasWindow() {
		
	      //Creating a File object for directory
	      File directoryPath = new File("regras/");
	      //List of all files and directories
	      String contents[] = directoryPath.list();
	      System.out.println("List of files and directories in the specified directory:");
	      for(int i=0; i<contents.length; i++) {
	         System.out.println(contents[i]);		         
	}
	      list = new JList<String>();
	      list.setListData( contents);

			
			frame_metricas.add(list);

		JButton button20 = new JButton("Submit");
		button20.setBackground(Color.WHITE);
		button20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//list.getSelectedValue();
				System.out.println(list.getSelectedValue());
			}
		});
		frame_metricas.add(button20, BorderLayout.SOUTH);
	}

	/**
	 * Returns the number of the column of the metric chosen
	 * 
	 * @param stringRegra string of rules
	 * 
	 * @return int includes the number of the column
	 */
	
	private static int getColInxOfSelMetric(String stringRegra) {
		int excelTClassCol1 = -1;
		if (stringRegra.equals("NOM_class")) {
			excelTClassCol1 = GUI.table.getColumn("NOM_class").getModelIndex();
		}
		else if (stringRegra.equals("LOC_class")) {
			excelTClassCol1 = GUI.table.getColumn("LOC_class").getModelIndex();
		}
		else if(stringRegra.equals("WMC_class")) {
			excelTClassCol1 = GUI.table.getColumn("WMC_class").getModelIndex();
		}
		else if(stringRegra.equals("LOC_method")) {
			excelTClassCol1 = GUI.table.getColumn("LOC_method").getModelIndex();
		}
		else if(stringRegra.equals("CYCLO_Method")) {
			excelTClassCol1 = GUI.table.getColumn("CYCLO_Method").getModelIndex();
		}
		return excelTClassCol1;
	}

	/**
	 * Method defines 2 tables columns and go get their values
	 * the table 2 generates field is set
	 * 
	 * @param excelTModel default table model
	 * @param comparisonTModel default table model
	 * @param stringRegras array of Strings with all the rules
	 * @param excelTCol1 int
	 * @param excelTCol2 int
	 * @param row int number of rows
	 * @param col2 int
	 */
	
	private static void writeGeneratedCodeSmellToComparisonTable(DefaultTableModel excelTModel, DefaultTableModel comparisonTModel, String[] stringRegras, int excelTCol1, int excelTCol2, int row, int col2) {
		//define table 1 and table 2 columns
		double t1v1;
		double t1v2;
		double v1 = Double.parseDouble(stringRegras[2]);
		double v2 =Double.parseDouble(stringRegras[6]);
		
		if (excelTModel.getValueAt(row, excelTCol1) != null && !excelTModel.getValueAt(row, excelTCol1).toString().equals("")) {
			t1v1 = Double.parseDouble (excelTModel.getValueAt(row, excelTCol1).toString());
		}
		else {
			t1v1 = 0;
		}
		
		if (excelTModel.getValueAt(row, excelTCol2) != null && !excelTModel.getValueAt(row, excelTCol2).toString().equals("")) {
			t1v2 = Double.parseDouble (excelTModel.getValueAt(row, excelTCol2).toString());
		}
		else {
			t1v2 = 0;
		}
		
		
		//sets table 2 generated field
		if(stringRegras[3].equals("AND")) {
			if(stringRegras[1].equals(">")) {
				if(stringRegras[5].equals(">")) {
					if(t1v1 > v1 && t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("<")) {
					if(t1v1 > v1 && t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("=")) {
					if(t1v1 > v1 && t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			}
			else if(stringRegras[1].equals("<")) {
				if(stringRegras[5].equals(">")) {
					if(t1v1 < v1 && t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("<")) {
					if(t1v1 < v1 && t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("=")) {
					if(t1v1 < v1 && t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			}
			else if(stringRegras[1].equals("=")) {
				if(stringRegras[5].equals(">")) {
					if(t1v1 == v1 && t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("<")) {
					if(t1v1 == v1 && t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("=")) {
					if(t1v1 == v1 && t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			}
		}
		else if(stringRegras[3].equals("OR")) {
			if(stringRegras[1].equals(">")) {
				if(stringRegras[5].equals(">")) {
					if(t1v1 > v1 || t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("<")) {
					if(t1v1 > v1 || t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("=")) {
					if(t1v1 > v1 || t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			}
			else if(stringRegras[1].equals("<")) {
				if(stringRegras[5].equals(">")) {
					if(t1v1 < v1 || t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("<")) {
					if(t1v1 < v1 || t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("=")) {
					if(t1v1 < v1 || t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			}
			else if(stringRegras[1].equals("=")) {
				if(stringRegras[5].equals(">")) {
					if(t1v1 == v1 || t1v2 > v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("<")) {
					if(t1v1 == v1 || t1v2 < v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
				else if(stringRegras[5].equals("=")) {
					if(t1v1 == v1 || t1v2 == v2) {
						comparisonTModel.setValueAt("TRUE", row, col2);
					}
					else {
						comparisonTModel.setValueAt("FALSE", row, col2);
					}
				}
			}
		}
	}
	
	private static void setDetectionQualityInCell2(DefaultTableModel comparisonTModel, int row, int c1, int c2, int c3) {
		if(comparisonTModel.getValueAt(row, c1) != null && comparisonTModel.getValueAt(row, c2) != null && 
				!comparisonTModel.getValueAt(row, c1).toString().equals("") && !comparisonTModel.getValueAt(row, c2).toString().equals("")) {
			if(comparisonTModel.getValueAt(row, c1).toString().equals("TRUE") && comparisonTModel.getValueAt(row, c2).toString().equals("TRUE")) {
				comparisonTModel.setValueAt("VP", row, c3);
			}
			else if(comparisonTModel.getValueAt(row, c1).toString().equals("TRUE") && comparisonTModel.getValueAt(row, c2).toString().equals("FALSE")) {
				comparisonTModel.setValueAt("FN", row, c3);
			}
			else if(comparisonTModel.getValueAt(row, c1).toString().equals("FALSE") && comparisonTModel.getValueAt(row, c2).toString().equals("TRUE")) {
				comparisonTModel.setValueAt("FP", row, c3);
			}
			else if(comparisonTModel.getValueAt(row, c1).toString().equals("FALSE") && comparisonTModel.getValueAt(row, c2).toString().equals("FALSE")) {
				comparisonTModel.setValueAt("VN", row, c3);
			}
		}
	}
}

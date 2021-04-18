package es_g46;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextField;

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
	private static JComboBox<?> comboBox1;
	private static JComboBox<?> comboBox2;
	private static JComboBox<?> comboBox3;
	private static JComboBox<?> comboBox4;
	private static JComboBox<?> comboBox5;
	private static JComboBox<?> comboBox1v2;
	private static JComboBox<?> comboBox2v2;
	private static JComboBox<?> comboBox3v2;
	private static JComboBox<?> comboBox4v2;
	private static JComboBox<?> comboBox5v2;
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
	private static JCheckBox checkBox1;
	private static JCheckBox checkBox2;
	private static JCheckBox checkBox3;
	private static JCheckBox checkBox4;
	private static JCheckBox checkBox5;
	private static JList<String> list;
	private static JTextField text_metricas;



	// janela de quando se carrega no botao "criar regras"
	public static void gui_criar_regras() {

		frame_criar_regras = new JFrame("Defenir regras");
		frame_criar_regras.setLayout(new GridLayout(3, 0));
		frame_criar_regras.pack();
		frame_criar_regras.setLocation(100, 50);
		frame_criar_regras.setSize(250, 140);
		frame_criar_regras.setVisible(true);
	}
	
	static void loadCriarRegrasWindow() {
		radio_buttons = new ButtonGroup();
		radio_button = new JRadioButton("Class");
		radio_buttons.add(radio_button);
		radio_button1 = new JRadioButton("Method");
		radio_buttons.add(radio_button1);

		frame_criar_regras.add(radio_button);
		frame_criar_regras.add(radio_button1);

		JButton button30 = new JButton("Check");
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
	
	// janela abre qd se escolhe opçao de class
	public static void gui_regra1() {

		frame_criar_regras1 = new JFrame("Regra_class");
		frame_criar_regras1.setLayout(new GridLayout(5, 0));
		frame_criar_regras1.pack();
		frame_criar_regras1.setLocation(100, 50);
		frame_criar_regras1.setSize(350, 320);
		frame_criar_regras1.setVisible(true);
	}

	// janela abre qd se escolhe opçao de method
	public static void gui_regra2() {

		frame_criar_regras2 = new JFrame("Regra_method");
		frame_criar_regras2.setLayout(new GridLayout(5, 0));
		frame_criar_regras2.pack();
		frame_criar_regras2.setLocation(100, 50);
		frame_criar_regras2.setSize(350, 320);
		frame_criar_regras2.setVisible(true);
	}
	
	// Janelas do defenir regras
	private static void frameRegra1() {

		// Regra1
		label_regras1v2 = new JLabel("");

		frame_criar_regras1.add(label_regras1v2);
		topPanel_regras = new JPanel();
		frame_criar_regras1.add(topPanel_regras);
		topPanel_regras.setLayout(new FlowLayout());

		String opcoes[] = { "NOM_class", "LOC_class", "WMC_class" };
		String sinais[] = { "<", ">", "=" };
		String logica[] = { "AND", "OR" };

		comboBox1 = new JComboBox<Object>(opcoes);
		comboBox2 = new JComboBox<Object>(sinais);
		comboBox5 = new JComboBox<Object>(logica);

		text_regras = new JTextField(10);

		topPanel_regras.add(comboBox1);
		topPanel_regras.add(comboBox2);
		topPanel_regras.add(text_regras);

		middlePanel_regras = new JPanel();
		frame_criar_regras1.add(middlePanel_regras);
		middlePanel_regras.setLayout(new FlowLayout());

		middlePanel_regras.add(comboBox5);

		// Regra2

		bottomPanel_regras = new JPanel();
		frame_criar_regras1.add(bottomPanel_regras);
		bottomPanel_regras.setLayout(new FlowLayout());

		comboBox3 = new JComboBox<Object>(opcoes);
		comboBox4 = new JComboBox<Object>(sinais);

		text_regras1 = new JTextField(10);

		bottomPanel_regras.add(comboBox3);
		bottomPanel_regras.add(comboBox4);
		bottomPanel_regras.add(text_regras1);

		bottomPanel_regrasv2 = new JPanel();
		frame_criar_regras1.add(bottomPanel_regrasv2);
		bottomPanel_regrasv2.setLayout(new FlowLayout());

		JButton button33 = new JButton("Save");
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

		JButton button33v1 = new JButton("Submit");
		button33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// açao do botao
			}
		});

		JButton button33v2 = new JButton("Load");
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
							    	
							    	String textoDaComboBox1=comboBox1.getItemAt(i).toString();
							   if( textoDaComboBox1.equals(ss[0]))
							   {						   	
								   comboBox1.setSelectedIndex(i);}
							    String textoDaComboBox2=comboBox2.getItemAt(i).toString();
							    if( textoDaComboBox2.equals(ss[1]))
								   {
									   comboBox2.setSelectedIndex(i);
									   }
							    String textoDaComboBox5="";
							    if(i<2)
							    { textoDaComboBox5=comboBox5.getItemAt(i).toString();
							 
							    if( textoDaComboBox5.equals(ss[3]))
								   {
									   comboBox5.setSelectedIndex(i);
									   }
							    }
							    String textoDaComboBox3=comboBox3.getItemAt(i).toString();
								   if( textoDaComboBox3.equals(ss[4]))
								   {							   	
									   comboBox3.setSelectedIndex(i);}
								   String textoDaComboBox4=comboBox4.getItemAt(i).toString();
								   if( textoDaComboBox4.equals(ss[5]))
								   {							   	
									   comboBox4.setSelectedIndex(i);}
								  
								   						   	
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
		bottomPanel_regrasv2.add(button33v1);
		bottomPanel_regrasv2.add(button33v2);
	}

	private static void frameRegra2() {

		label_regras1 = new JLabel("");
		frame_criar_regras2.add(label_regras1);

		// Regra1

		topPanel_regras2 = new JPanel();
		frame_criar_regras2.add(topPanel_regras2);
		topPanel_regras2.setLayout(new FlowLayout());

		String opcoes[] = { "LOC_method", "CYCLO_method" };
		String sinais[] = { "<", ">", "=" };
		String logica[] = { "AND", "OR" };

		comboBox1v2 = new JComboBox<Object>(opcoes);
		comboBox2v2 = new JComboBox<Object>(sinais);
		comboBox5v2 = new JComboBox<Object>(logica);

		text_regrasv2 = new JTextField(10);

		topPanel_regras2.add(comboBox1v2);
		topPanel_regras2.add(comboBox2v2);
		topPanel_regras2.add(text_regrasv2);

		middlePanel_regras2 = new JPanel();
		frame_criar_regras2.add(middlePanel_regras2);
		middlePanel_regras2.setLayout(new FlowLayout());

		middlePanel_regras2.add(comboBox5v2);

		// Regra2

		bottomPanel_regras2 = new JPanel();
		frame_criar_regras2.add(bottomPanel_regras2);
		bottomPanel_regras2.setLayout(new FlowLayout());

		comboBox3v2 = new JComboBox<Object>(opcoes);
		comboBox4v2 = new JComboBox<Object>(sinais);

		text_regras1v2 = new JTextField(10);

		bottomPanel_regras2.add(comboBox3v2);
		bottomPanel_regras2.add(comboBox4v2);
		bottomPanel_regras2.add(text_regras1v2);

		bottomPanel_regras222 = new JPanel();
		frame_criar_regras2.add(bottomPanel_regras222);
		bottomPanel_regras222.setLayout(new FlowLayout());

		JButton button33v2 = new JButton("Save");
		button33v2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveRegras2();
			}
		});
		JButton button33v12 = new JButton("Submit");
		button33v12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// açao do botao
			}
		});
		JButton button33v22 = new JButton("Load");
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
							    	String textoDaComboBox1=comboBox1v2.getItemAt(i).toString();
							   if( textoDaComboBox1.equals(ss[0]))
							   {						   	
								   comboBox1v2.setSelectedIndex(i);}}
							    String textoDaComboBox2=comboBox2v2.getItemAt(i).toString();
							    if( textoDaComboBox2.equals(ss[1]))
								   {
									   comboBox2v2.setSelectedIndex(i);
									   }
							    String textoDaComboBox5="";
							    if(i<2)
							    { textoDaComboBox5=comboBox5v2.getItemAt(i).toString();
							 
							    if( textoDaComboBox5.equals(ss[3]))
								   {
									   comboBox5v2.setSelectedIndex(i);
									   }
							    }
							    if(i<2) {
							    String textoDaComboBox3=comboBox3v2.getItemAt(i).toString();
								   if( textoDaComboBox3.equals(ss[4]))
								   {							   	
									   comboBox3v2.setSelectedIndex(i);}}
								   String textoDaComboBox4=comboBox4v2.getItemAt(i).toString();
								   if( textoDaComboBox4.equals(ss[5]))
								   {							   	
									   comboBox4v2.setSelectedIndex(i);}
								  
								   						   	
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
		bottomPanel_regras222.add(button33v12);
		bottomPanel_regras222.add(button33v22);
	}

	// retorna um array de String com as regras criadas
	private static String[] saveRegras() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		string_regras = new String[7];

		for (int i = 0; i < string_regras.length; i++) {
			string_regras[0] = (String) comboBox1.getSelectedItem();
			string_regras[1] = (String) comboBox2.getSelectedItem();
			string_regras[2] = text_regras.getText();
			string_regras[3] = (String) comboBox5.getSelectedItem();
			string_regras[4] = (String) comboBox3.getSelectedItem();
			string_regras[5] = (String) comboBox4.getSelectedItem();
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

	private static String[] saveRegras2() {
		string_regras2 = new String[7];

		for (int i = 0; i < string_regras2.length; i++) {
			string_regras2[0] = (String) comboBox1v2.getSelectedItem();
			string_regras2[1] = (String) comboBox2v2.getSelectedItem();
			string_regras2[2] = text_regrasv2.getText();
			string_regras2[3] = (String) comboBox5v2.getSelectedItem();
			string_regras2[4] = (String) comboBox3v2.getSelectedItem();
			string_regras2[5] = (String) comboBox4v2.getSelectedItem();
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

	
	// janela de quando se carrega no botao "regras guardadas"
	public static void gui_metricas() {

		frame_metricas = new JFrame("metricas");
		frame_metricas.setLayout(new BorderLayout());
		frame_metricas.pack();
		frame_metricas.setLocation(100, 50);
		frame_metricas.setSize(500, 500);
		frame_metricas.setVisible(true);
	}
	
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
		button20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//list.getSelectedValue();
				System.out.println(list.getSelectedValue());
			}
		});
		frame_metricas.add(button20, BorderLayout.SOUTH);
	}
}

package guis;

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

import es_g46.EssentialJanelasMetricasMethods;

/**
 * Class that creates the windows when a button is pushed
 * 
 * @author g46
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
		// frame_criar_regras.getContentPane().setBackground(Color.WHITE);
		frame_criar_regras.setLocation(100, 50);
		frame_criar_regras.setSize(250, 140);
		frame_criar_regras.setVisible(true);
	}

	/**
	 * Creates the initial layout of the frame It´s added 2 radio buttons to choose
	 * the next window to open It´s added a button to check to open the window
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
	 * Returns a new window when the option chosen in the initial window was
	 * "method"
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
	 * Layout of the frame chosen as "class" It has some spaces that need to be
	 * filled by the user to create the rules Those spaces consist in all the
	 * classes available, logic signs and the limits 3 buttons to save, upload or
	 * submit the rules chosen Button save has the functionality of saving rules in
	 * a file and in the portofolio "regras" Button load has the functionality of
	 * let the people select a file from the portfolio "regras" and filled the
	 * spaces Button submit has the functionality of execute the rules
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
					string_regras = new String[7];
					
					string_regras[0] = (String) comboBox1Name.getSelectedItem();
					string_regras[1] = (String) comboBox2Sign.getSelectedItem();
					string_regras[2] = text_regras.getText();
					string_regras[3] = (String) comboBox3Operator.getSelectedItem();
					string_regras[4] = (String) comboBox4Name2.getSelectedItem();
					string_regras[5] = (String) comboBox5Sign2.getSelectedItem();
					string_regras[6] = text_regras1.getText();
					EssentialJanelasMetricasMethods.saveRegras(string_regras);
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

				EssentialJanelasMetricasMethods.submitClassRule(GUI.table, GUI.table2, stringRegras, GUI.eastPClassTextArea);
			}
		});

		JButton button33v2 = new JButton("Load");
		button33v2.setBackground(Color.WHITE);
		button33v2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EssentialJanelasMetricasMethods.loadRegras1(comboBox1Name, comboBox2Sign, comboBox3Operator,
						comboBox4Name2, comboBox5Sign2, text_regras, text_regras1);
			}
		});
		bottomPanel_regrasv2.add(button33);
		bottomPanel_regrasv2.add(submitButton);
		bottomPanel_regrasv2.add(button33v2);
	}

	/**
	 * Layout of the frame chosen as "method" It has some spaces that need to be
	 * filled by the user to create the rules Those spaces consist in all the
	 * classes available, logic signs and the limits 3 buttons to save, upload or
	 * submit the rules chosen Button save has the functionality of saving rules in
	 * a file and in the pastes "rules" Button load has the functionality of let the
	 * people select a file from the pastes "rules" and filled the spaces Button
	 * submit has the functionality of execute the rules
	 * 
	 */

	public static void frameRegra2() {

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
				string_regras2 = new String[7];

					string_regras2[0] = (String) comboBox1v2Name.getSelectedItem();
					string_regras2[1] = (String) comboBox2v2Sign.getSelectedItem();
					string_regras2[2] = text_regrasv2.getText();
					string_regras2[3] = (String) comboBox3v2Operator.getSelectedItem();
					string_regras2[4] = (String) comboBox4v2Name2.getSelectedItem();
					string_regras2[5] = (String) comboBox5v2Sign2.getSelectedItem();
					string_regras2[6] = text_regras1v2.getText();
				
				EssentialJanelasMetricasMethods.saveRegras2(string_regras2);
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

				EssentialJanelasMetricasMethods.submitMethodRule(GUI.table, GUI.table2, stringRegras, GUI.eastPMethodTextArea);
			}
		});
		JButton button33v22 = new JButton("Load");
		button33v22.setBackground(Color.WHITE);
		button33v22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EssentialJanelasMetricasMethods.loadRegras2(comboBox1v2Name, comboBox2v2Sign, comboBox3v2Operator,
						comboBox4v2Name2, comboBox5v2Sign2, text_regrasv2, text_regras1v2);
			}
		});
		bottomPanel_regras222.add(button33v2);
		bottomPanel_regras222.add(submitButton);
		bottomPanel_regras222.add(button33v22);
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
	 * Load the saved rules into a list of files is a specified directory Creation
	 * of a button "Submit"
	 * 
	 */

	public static void loadRegrasGuardadasWindow() {

		// Creating a File object for directory
		File directoryPath = new File("regras/");
		// List of all files and directories
		String contents[] = directoryPath.list();
		System.out.println("List of files and directories in the specified directory:");
		for (int i = 0; i < contents.length; i++) {
			System.out.println(contents[i]);
		}
		list = new JList<String>();
		list.setListData(contents);

		frame_metricas.add(list);

		JButton button20 = new JButton("Submit");
		button20.setBackground(Color.WHITE);
		button20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// list.getSelectedValue();
				System.out.println(list.getSelectedValue());
				String[] s1 = list.getSelectedValue().split("\\[");
				String sType = s1[0];
				String s2 = s1[1].split("\\]")[0];

				// replace letters with symbols
				if (s2.contains("MENOR"))
					s2 = s2.replace("MENOR", "<");
				if (s2.contains("MAIOR"))
					s2 = s2.replace("MAIOR", ">");

				String[] string_regras = s2.split(", ");
				for (int i = 0; i < 7; i++) {
					System.out.println(string_regras[i]);
				}
				if (sType.equals("Class")) {
					EssentialJanelasMetricasMethods.submitClassRule(GUI.table, GUI.table2, string_regras, GUI.eastPClassTextArea);
				} else if (sType.equals("Method")) {
					EssentialJanelasMetricasMethods.submitMethodRule(GUI.table, GUI.table2, string_regras, GUI.eastPMethodTextArea);
				}

			}
		});
		frame_metricas.add(button20, BorderLayout.SOUTH);
	}
}

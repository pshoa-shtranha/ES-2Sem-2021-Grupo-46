package es_g46.es_g46;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
				saveRegras();
			}
		});

		JButton button33v1 = new JButton("Submit");
		button33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// açao do botao
			}
		});

		JButton button33v2 = new JButton("Load");
		button33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// açao do botao
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
				// açao do botao
			}
		});
		bottomPanel_regras222.add(button33v2);
		bottomPanel_regras222.add(button33v12);
		bottomPanel_regras222.add(button33v22);
	}

	// retorna um array de String com as regras criadas
	private static String[] saveRegras() {
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
		return string_regras2;
	}

	
	// janela de quando se carrega no botao "regras guardadas"
	public static void gui_metricas() {

		frame_metricas = new JFrame("metricas");
		frame_metricas.setLayout(new BorderLayout());
		frame_metricas.pack();
		frame_metricas.setLocation(100, 50);
		frame_metricas.setSize(250, 220);
		frame_metricas.setVisible(true);
	}
	
	static void loadRegrasGuardadasWindow() {
		JLabel label_metricas = new JLabel("Where is the file?");
		text_metricas = new JTextField(10);

		JPanel frame2 = new JPanel();
		frame_metricas.add(frame2, BorderLayout.NORTH);
		frame2.setLayout(new FlowLayout());

		frame2.add(label_metricas);
		frame2.add(text_metricas);

		JPanel frame3 = new JPanel();
		frame_metricas.add(frame3, BorderLayout.CENTER);
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
				// FileManagement fm = new FileManagement(listFiles(), buttonMetricas());
			}
		});
		frame_metricas.add(button20, BorderLayout.SOUTH);
	}
}

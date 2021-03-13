package es_g46.es_g46;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class GUI {
	
	private static JFrame frame;
	private static JTextField text;
	private static JButton button;

	
	public static void open() {
		frame.setLocation(100, 50);
		frame.setSize(1000, 500);
		frame.setVisible(true);
	}
	
	private void addFrameContent() {
		frame.setLayout(new BorderLayout());
	}
	
	
	public static void main(String args[]) {
		frame = new JFrame("ES");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		open();
	}
}
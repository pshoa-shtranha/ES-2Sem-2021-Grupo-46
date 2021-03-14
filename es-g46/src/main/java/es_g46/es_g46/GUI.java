package es_g46.es_g46;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {
	
	public static  JFrame frame;
	private  JTextField text;


	public GUI() {
		frame = new JFrame("ES");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
	}
	
	public void open() {
		frame.setLocation(100, 50);
		frame.setSize(1000, 500);
		frame.setVisible(true);
	}
	
	private void addFrameContent() {
		frame.setLayout(new BorderLayout());
		
		JButton button = new JButton("Extrair métricas"); 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
				
			}
		});
		frame.add(button, BorderLayout.SOUTH);
	}
	
	
	public static void main(String args[]) {
		GUI gui = new GUI();
		gui.open();
	}
}
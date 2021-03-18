package es_g46.es_g46;

import java.awt.BorderLayout;
import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {
	
	private JFrame frame;
	private JPanel topPanel;
	private JPanel bottomPanel;


	public GUI() {
		frame = new JFrame("Excel Reader");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
	}
	
	public void open() {
		frame.setLocation(100, 50);
		frame.setSize(1000, 500);
		frame.setVisible(true);
	}
/*	
	public void createExcel() {
		try {
            String filename = "C:/Code_Smells.xls" ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Métricas");  

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("NOM_class");
            rowhead.createCell(1).setCellValue("LOC_class");
            rowhead.createCell(2).setCellValue("WMC_method");
            rowhead.createCell(3).setCellValue("CYCLOD_Method");


            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
       //     workbook.close();
            System.out.println("Your excel file has been generated!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
	
	*/
	
	private void upPanel() {
		
		JButton button = new JButton("Show Excel"); 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
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
		
		JButton button7 = new JButton("Data Size"); 
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
		
		JButton button8 = new JButton("Exit"); 
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
			}
		});
		
		topPanel = new JPanel();
		frame.add(topPanel, BorderLayout.SOUTH);
		topPanel.setLayout(new FlowLayout());
		
		topPanel.add(button);
		topPanel.add(button1);
		topPanel.add(button2);
		topPanel.add(button3);
		topPanel.add(button4);
		topPanel.add(button5);
		topPanel.add(button6);
		topPanel.add(button7);
		topPanel.add(button8);
	}
	
	private void lowPanel() {
	
		bottomPanel = new JPanel();
		frame.add(bottomPanel, BorderLayout.NORTH);
		bottomPanel.setLayout(new GridLayout(1,5));
	}
	
	
	private void addFrameContent() {
		frame.setLayout(new BorderLayout());
		upPanel();
		lowPanel();
	}
	
	
	public static void main(String args[]) {
		GUI gui = new GUI();
		gui.open();
	}
}
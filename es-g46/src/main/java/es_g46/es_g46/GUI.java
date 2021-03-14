package es_g46.es_g46;

import java.awt.BorderLayout;
import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
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
	
	
	private void addFrameContent() {
		frame.setLayout(new BorderLayout());
		
		JButton button = new JButton("Open Excel"); 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar ação do botão
				createExcel();
			}
		});
		frame.add(button, BorderLayout.SOUTH);
	}
	
	
	public static void main(String args[]) {
		GUI gui = new GUI();
		gui.open();
	}
}
package es_g46;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class that test the program
 * A path is chosen and is written in the excel
 * 
 * @author Luis Santos
 * @version 2.0
 */

public class FileManagement extends Thread {

	private int[] id;
	private List<File> files;
	private String[][] board;
	private String excelDir;
	private String nameProject;
	

	FileManagement(List<File> files, int[] id, String excelDir, String nameProject) throws IOException {
		
		this.id = id;
		this.files = files;
		this.board = new String[files.size()][9];
		this.excelDir = excelDir;
		this.nameProject = nameProject;
		Thread[] threads = new Thread[files.size()];
		for(int i = 0; i < files.size(); i++) {
			
			
			OneFile a = new OneFile(id, files.get(i), board, i + 1);
			threads[i] = a;
		}

		for(Thread t:threads) {
			
			t.start();
		}
		
		for(Thread t:threads) {
			
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		writeExcel(this.board, this.excelDir, this.nameProject);
		
	}
	
/*
	public static void main(String[] args) throws InterruptedException, IOException {
		
		//classe para testar o programa
		//insiram os vários ficheiros ao array de files e esta pronto a testar!!
		//final String FILE_PATH = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\FileManagement.java";
//		File file = new File(FILE_PATH);
		final String FILE_PATH = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\jasml\\compiler\\ConstantPoolGenerator.java";
		File file = new File(FILE_PATH);
		final String FILE_PATH2 = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\jasml\\compiler\\SourceCodeParser.java";
		File file2 = new File(FILE_PATH2);
		final String FILE_PATH3 = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\jasml\\decompiler\\JavaClassParser.java";
		File file3 = new File(FILE_PATH3);
//		File[] files = new File[1];
		List<File> files = new ArrayList<File>();
		//File[] files = new File[3];
		files.add(file);
		files.add(file2);
		files.add(file3);
		//files[0] = file;
		//files[1] = file2;
		//files[2] = file3;
		int[] smells = new int[5];
		smells[0] = 1;
		smells[1] = 1;
		smells[2] = 1;
		smells[3] = 1;
		smells[4] = 1;
		// sitio onde ele guarda o excel
		String excelDir = "C:\\Users\\Maintenant Prêt\\Desktop\\1_metricas.xlsx";
		FileManagement a = new FileManagement(files, smells, excelDir, "jasml");
		System.out.println("A pesquisa no ficheiro terminou!");

	}*/
	
	/**
	 * Method that writes directly in the excel
	 * The project is named
	 *
	 * @param board headquarters of String thats going to show the sheet
	 * @param excelDir String of where are the excel directories
	 * @param nameProject string with the name of the project
	 */
	
	public void writeExcel(String[][] board, String excelDir, String nameProject) {
		
		//necessita de alteracao
		//o nome do excel tem de ter o nome do projeto seguido de _metrics
		//o nome do projeto deve estar na variavel nameProject
		//exemplo: eusouonomedoprojeto_metrics.xlsl
		
		List<String> a = new ArrayList<String>();
		a.add(0, "MethodID");
		a.add(1, "package");
		a.add(2, "class");
		a.add(3, "method");
		a.add(4, "NOM_class");
		a.add(5, "LOC_class");
		a.add(6, "WMC_class");
		a.add(7, "is_God_Class");
		a.add(8, "LOC_method");
		a.add(9, "CYCLO_method");
		a.add(10, "is_Long_Method");
		
		FileOutputStream fout = null;
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet excelSheet = wb.createSheet("Metricas");
		Row excelRow = excelSheet.getRow(0);
		if (excelRow == null) {
//			System.out.println("null row");
			excelRow = excelSheet.createRow(0);			
		}
		int w = 0;
		for(w = 0; w <= 10; w++) {
			
			//Row excelRow = excelSheet.getRow(0);
			Cell cell = excelRow.createCell(w);
			cell.setCellValue(a.get(w));
		}
	
		int lastRow = 1;
		for(int i = 0; i < board.length; i ++) {
			System.out.println("Inicio do ficheiro!");
			int column = 0;
			for(int j = 0; j < board[i].length; j++) {
//				System.out.println(board[i][j]);
				String columnContent = board[i][j];
				//remove last "|" from the string:
				if (columnContent != null && columnContent.length() > 0 && columnContent.charAt(columnContent.length() - 1) == '|') {
					columnContent = columnContent.substring(0, columnContent.length() - 1);
				}
				System.out.println(columnContent);
				String[] cells = columnContent.split("\\|");
				int row = lastRow;
				Row excelRow2 = null;
				for (int k = 0; k < cells.length; k++) {
					excelRow2 = excelSheet.getRow(row);
					if (excelRow2 == null) {
//						System.out.println("null row");
						excelRow2 = excelSheet.createRow(row);			
					}
					if(column == 7) {
						column = 8;
					}
					Cell cell = excelRow2.createCell(column);
					cell.setCellValue(cells[k]);
					row++;
				}
				column++;
			}
			lastRow = excelSheet.getLastRowNum() + 1;
		}
		StringBuilder path = new StringBuilder();
		path.append(excelDir);
		path.append(nameProject);
		path.append("_metrics.xlsx");
		
		try {
			fout = new FileOutputStream(path.toString());
			//System.out.println(fout);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb.write(fout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
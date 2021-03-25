package es_g46.es_g46;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileManagement extends Thread {

	private int[] id;
	private File[] files;
	private String[][] board;

	FileManagement(File[] files, int[] id, String excelDir) throws IOException {
		
		this.id = id;
		this.files = files;
		this.board = new String[files.length][9];
		Thread[] threads = new Thread[files.length];
		for(int i = 0; i < files.length; i++) {
			
			
			OneFile a = new OneFile(id, files[i], board, i + 1);
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
		
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet excelSheet = wb.createSheet("Metricas");
		
		
		int lastRow = 0;
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
				Row excelRow = null;
				for (int k = 0; k < cells.length; k++) {
					excelRow = excelSheet.getRow(row);
					if (excelRow == null) {
//						System.out.println("null row");
						excelRow = excelSheet.createRow(row);			
					}
					Cell cell = excelRow.createCell(column);
					cell.setCellValue(cells[k]);
					row++;
				}
				column++;
			}
			lastRow = excelSheet.getLastRowNum() + 1;
		}
		
		FileOutputStream fout = new FileOutputStream(excelDir);
		wb.write(fout);
		fout.close();
		//chamar um metodo que estara dentro desta classe e que ira escrever todos os valores
		//presentes no array board para um ficheiro excel que sera criado no metodo
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		//classe para testar o programa
		//insiram os vários ficheiros ao array de files e esta pronto a testar!!
		//final String FILE_PATH = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\FileManagement.java";
//		File file = new File(FILE_PATH);
		final String FILE_PATH = "C:\\Users\\Visha\\Desktop\\jasml\\compiler\\ConstantPoolGenerator.java";
		File file = new File(FILE_PATH);
		final String FILE_PATH2 = "C:\\Users\\Visha\\Desktop\\jasml\\compiler\\SourceCodeParser.java";
		File file2 = new File(FILE_PATH2);
		final String FILE_PATH3 = "C:\\Users\\Visha\\Desktop\\jasml\\decompiler\\JavaClassParser.java";
		File file3 = new File(FILE_PATH3);
//		File[] files = new File[1];
		File[] files = new File[3];
		files[0] = file;
		files[1] = file2;
		files[2] = file3;
		int[] smells = new int[5];
		smells[0] = 1;
		smells[1] = 1;
		smells[2] = 1;
		smells[3] = 1;
		smells[4] = 1;
		// sitio onde ele guarda o excel
		String excelDir = "C:\\Users\\Visha\\Desktop\\1_metricas.xlsx";
		new FileManagement(files, smells, excelDir);
		System.out.println("A pesquisa no ficheiro terminou!");

	}
}
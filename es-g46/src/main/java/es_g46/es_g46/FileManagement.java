package es_g46.es_g46;

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

	}
	public void writeExcel(String[][] board, String excelDir, String nameProject) {
		
		//necessita de alteracao
		//o nome do excel tem de ter o nome do projeto seguido de _metrics
		//o nome do projeto deve estar na variavel nameProject
		//exemplo: eusouonomedoprojeto_metrics.xlsl
		
		FileOutputStream fout = null;
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
	
		try {
			fout = new FileOutputStream(excelDir);
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
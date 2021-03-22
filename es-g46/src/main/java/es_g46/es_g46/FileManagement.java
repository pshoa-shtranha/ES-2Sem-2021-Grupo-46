package es_g46.es_g46;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class FileManagement extends Thread {

	private int[] id;
	private File[] files;
	private String[][] board;

	FileManagement(File[] files, int[] id) {
		
		this.id = id;
		this.files = files;
		this.board = new String[files.length][this.id.length + 4];
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
		
		//chamar um metodo que estara dentro desta classe e que ira escrever todos os valores
		//presentes no array board para um ficheiro excel que sera criado no metodo
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		//classe para testar o programa
		//insiram os vários ficheiros ao array de files e esta pronto a testar!!
		//final String FILE_PATH = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\FileManagement.java";
		//File file = new File(FILE_PATH);
		final String FILE_PATH = "C:\\Users\\Maintenant Prêt\\Desktop\\jasml\\compiler\\SourceCodeParser.java";
		File file = new File(FILE_PATH);
		//final String FILE_PATH2 = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\CloudToMySQL.java";
		//File file2 = new File(FILE_PATH2);
		File[] files = new File[1];
		files[0] = file;
		//files[1] = file2;
		int[] smells = new int[5];
		smells[0] = 1;
		smells[1] = 1;
		smells[2] = 1;
		smells[3] = 1;
		smells[4] = 1;
		FileManagement a = new FileManagement(files, smells);
		System.out.println("A pesquisa no ficheiro terminou!");

	}
}
package es_g46.es_g46;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.github.javaparser.Range;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class OneFile extends Thread {

	private int[] id;
	private File files;
	private String[][] board;
	private CompilationUnit cu = null;
	private int numberFile;

	public OneFile(int[] id, File files, String[][] board, int numberFile) {
		
		this.id = id;
		this.files = files;
		this.board = board;
		this.numberFile = numberFile;
		 
		try {
			this.cu = StaticJavaParser.parse(files);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void run() {
		
		String coluna1 = this.getMethodID();
		System.out.println(coluna1);
		board[numberFile - 1][0] = coluna1;
		
		String coluna2 = this.getPackageName();
		System.out.println(coluna2);
		board[numberFile - 1][1] = coluna2;
		
		String coluna3 = this.getClassName();
		System.out.println(coluna3);
		board[numberFile - 1][2] = coluna3;
		
		String coluna4 = this.getNameMethods();
		board[numberFile - 1][3] = coluna4;
		System.out.println(coluna4);
		
			for(int i = 0; i < id.length; i++) {
				
				if(id[i] == 1) {
					
					switch(i) {
					
						case 0:
							
							String coluna5 = this.getNumberMethods();
							board[numberFile - 1][i + 2] = coluna5;
							System.out.println(coluna5);
							//nom_class
							//numero metodos por classe
							break;
						case 1:
							String coluna6 = this.getClassSize();
							board[numberFile - 1][i + 2] = coluna6;
							System.out.println(coluna6);
							//numero de linhas da classe
							//loc_class
							
							break;
						case 2:
							
							//complexidade ciclica da classe
							String coluna7 = this.getCyclicClass();
							board[numberFile - 1][i + 2] = coluna7;
							System.out.println(coluna7);
							break;
						case 3:
							String coluna8 = this.getSizeMethods();
							board[numberFile - 1][i + 2] = coluna8;
							System.out.println(coluna8);
							//numero de linhas do metodo
							
							break;
						case 4:
							
							String coluna9 = this.getCyclicMethods();
							board[numberFile - 1][i + 2] = coluna9;
							System.out.println(coluna9);
							
							//complexidade ciclioca do metodo
							break;
					}
				}
			}
	}
	
	/*public static void main(String[] args) throws InterruptedException {
		
		final String FILE_PATH = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\FileManagement.java";
		int[] smells = new int[5];
		smells[0] = 1;
		smells[1] = 1;
		smells[2] = 1;
		smells[3] = 1;
		smells[4] = 1;
		String[][] board = new String[1][smells.length + 4];
		File file = new File(FILE_PATH);
		OneFile a = new OneFile(smells, file, board, 1);
		a.start();
		a.join();
		System.out.println("A pesquisa no ficheiro terminou!");

	}*/
	
	public String getNameMethods() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> methodNames = new ArrayList<>();
		VoidVisitor<List<String>> methodNameVisitor = new MethodNameCollector();
		methodNameVisitor.visit(this.cu, methodNames);
		
		while(!methodNames.isEmpty()) {
			String method = methodNames.remove(0);
			
			list.append(method);
			list.append("|");
		}
		return list.toString();
	}
	
	public String getSizeMethods() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> methodSizes = new ArrayList<>();
		VoidVisitor<List<String>> methodSizeVisitor = new MethodSizeCollector();
		methodSizeVisitor.visit(this.cu, methodSizes);
		
		while(!methodSizes.isEmpty()) {
			String method = methodSizes.remove(0);
			
			list.append(method);
			list.append("|");
		}
		return list.toString();
	}
	public String getCyclicMethods() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> methodSizes = new ArrayList<>();
		VoidVisitor<List<String>> methodSizeVisitor = new MethodCyclicCollector();
		methodSizeVisitor.visit(this.cu, methodSizes);
		
		while(!methodSizes.isEmpty()) {
			String method = methodSizes.remove(0);
			
			list.append(method);
			list.append("|");
		}
		return list.toString();
	}
	public String getCyclicClass() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> methodSizes = new ArrayList<>();
		VoidVisitor<List<String>> methodSizeVisitor = new MethodCyclicCollector();
		methodSizeVisitor.visit(this.cu, methodSizes);
		int count = 0;
		while(!methodSizes.isEmpty()) {
			String method = methodSizes.remove(0);
			
			count = count + Integer.valueOf(method);
			
		}
		for(int i = 0; i < this.getTotalMethods(); i++) {
			list.append(count);
			list.append("|");
		}
		return list.toString();
	}
	
	public String getMethodID() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> methodNames = new ArrayList<>();
		VoidVisitor<List<String>> methodNameVisitor = new MethodNameCollector();
		methodNameVisitor.visit(this.cu, methodNames);
		int count = 0;
		while(!methodNames.isEmpty()) {
			methodNames.remove(0);
			
			list.append(count + 1);
			count++;
			list.append("|");
		}
		return list.toString();
	}
	
	public String getNumberMethods() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> methodNames = new ArrayList<>();
		VoidVisitor<List<String>> methodNameVisitor = new MethodNameCollector();
		methodNameVisitor.visit(this.cu, methodNames);
		int total = methodNames.size();
		
		while(!methodNames.isEmpty()) {
			methodNames.remove(0);
			list.append(total);
			list.append("|");
		}
		
		return list.toString();
	}
	
	public int getTotalMethods() {
		
		List<String> methodNames = new ArrayList<>();
		VoidVisitor<List<String>> methodNameVisitor = new MethodNameCollector();
		methodNameVisitor.visit(this.cu, methodNames);
		int total = methodNames.size();
		return total;
	}
	
	public String getClassSize() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> classSizes = new ArrayList<>();
		VoidVisitor<List<String>> methodSizeVisitor = new ClassSize();
		methodSizeVisitor.visit(this.cu, classSizes);
		int size = this.getTotalMethods();
		String tamanho = classSizes.remove(0);
		for(int i = 0; i < size; i++) {
			list.append(tamanho);
			list.append("|");
		}
		return list.toString();
	}
	
	public String getClassName() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> classSizes = new ArrayList<>();
		VoidVisitor<List<String>> methodSizeVisitor = new ClassName();
		methodSizeVisitor.visit(this.cu, classSizes);
		int size = this.getTotalMethods();
		String nome = classSizes.remove(0);
		for(int i = 0; i < size; i++) {
			list.append(nome);
			list.append("|");
		}
		return list.toString();
	}
	
	public String getPackageName() {
		
		Scanner reader;
		String pacote = null;
		try {
			reader = new Scanner(this.files);
			pacote = reader.nextLine().toString();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pacote;
	}
}
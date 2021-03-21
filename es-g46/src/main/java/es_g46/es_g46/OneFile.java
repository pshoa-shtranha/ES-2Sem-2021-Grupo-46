package es_g46.es_g46;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
		
			for(int i = 0; i < id.length; i++) {
				
				if(id[i] == 1) {
					
					switch(i) {
					
						case 0:
							
							String result = this.getNameMethods();
							board[numberFile - 1][i] = result;
							System.out.println(result);
							//NOM_class
							break;
						case 1:
							//cyclesClass();
							//WMC_class
							String result1 = this.getCyclicClass();
							board[numberFile - 1][i] = result1;
							System.out.println(result1);
							break;
						case 2:
							String result2 = this.getSizeMethods();
							board[numberFile - 1][i] = result2;
							System.out.println(result2);
							//LOC_method
							break;
						case 3:
							String result3 = this.getCyclicMethods();
							board[numberFile - 1][i] = result3;
							System.out.println(result3);
							
							//CYCLO_method
							break;
						case 4:
							//numberLinesClass();
							//LOC_class
							StringBuilder list = new StringBuilder();
							list.append("|");
							List<String> classSizes = new ArrayList<>();
							VoidVisitor<List<String>> methodSizeVisitor = new ClassSize();
							methodSizeVisitor.visit(this.cu, classSizes);
							while(!classSizes.isEmpty()) {
								String method = classSizes.remove(0);
								
								list.append(method);
								System.out.println(method);
								list.append("|");
							}
							System.out.println(list.toString());
							break;
					}
				}
			}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final String FILE_PATH = "C:\\Users\\Maintenant Prêt\\Desktop\\ES\\FileManagement.java";
		int[] smells = new int[5];
		smells[0] = 1;
		smells[1] = 1;
		smells[2] = 1;
		smells[3] = 1;
		smells[4] = 1;
		String[][] board = new String[1][smells.length];
		File file = new File(FILE_PATH);
		OneFile a = new OneFile(smells, file, board, 1);
		a.start();
		a.join();
		System.out.println("O programa terminou!");

	}
	
	public String getNameMethods() {
		
		StringBuilder list = new StringBuilder();
		list.append("|");
		List<String> methodNames = new ArrayList<>();
		VoidVisitor<List<String>> methodNameVisitor = new MethodNameCollector();
		methodNameVisitor.visit(this.cu, methodNames);
		
		while(!methodNames.isEmpty()) {
			String method = methodNames.remove(0);
			
			list.append(method);
			System.out.println(method);
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
			System.out.println(method);
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
			System.out.println(method);
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
		System.out.println(count);
		for(int i = 0; i < 2; i++) {
			list.append(count);
			list.append("|");
		}
		return list.toString();
	}
	
}
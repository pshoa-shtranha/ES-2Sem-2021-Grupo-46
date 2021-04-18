package es_g46;

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

/**
 * Class that extracts the intended metrics from a .java
 * 
 * @author Luis Santos and Pavlo
 * @version 2.0
 */

public class OneFile extends Thread {

	private int[] id;
	private File files;
	private String[][] board;
	private CompilationUnit cu = null;
	private int numberFile;
	
	/**
	 * Calls the methods that do the extraction of certain metrics
	 * Is ready to distinguish between class and interface
	 * Writes the info on the console for programming purposes 
	 * 
	 * @param id array of integer with the intended metrics to be extracted
	 * @param files the .java to be extracted from
	 * @param board array of strings where the metrics will be stored
	 * @param numberFile the number of the file, used to know where to store the info in the array of strings
	 */
	
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

	/**
	 * Method executed when the thread starts
	 * Extracts different information if .java is class or interface
	 *
	 */
	
	public void run() {
		
		List<String> classSizes = new ArrayList<>();
		VoidVisitor<List<String>> classNameVisitor = new ClassName();
		classNameVisitor.visit(this.cu, classSizes);
		
		/*if(this.getTotalMethods() == 0 && classSizes.size() != 0) {
			
			String class1 = this.getPackageName();
			System.out.println("coluna 2: " + class1);
			board[numberFile - 1][1] = class1;
			
			String class2 = this.getClassName();
			System.out.println("coluna 3: " + class2);
			board[numberFile - 1][2] = class2;
			
			String class3 = this.getClassSize();
			board[numberFile - 1][5] = class3;
			System.out.println("coluna 6: " + class3);
		} else {
		*/	
		String coluna1 = this.getMethodID();
		System.out.println("coluna1: " + coluna1);
		board[numberFile - 1][0] = coluna1;
		
		String coluna2 = this.getPackageName();
		System.out.println("coluna2: " + coluna2);
		board[numberFile - 1][1] = coluna2;
		
		String coluna3 = this.getClassName();
		System.out.println("coluna3: " + coluna3);
		board[numberFile - 1][2] = coluna3;
		
		String coluna4 = this.getNameMethods();
		board[numberFile - 1][3] = coluna4;
		System.out.println("coluna4: " + coluna4);
		
			for(int i = 0; i < id.length; i++) {
				
				if(id[i] == 1) {
					
					switch(i) {
					
						case 0:
							
							String coluna5 = this.getNumberMethods();
							board[numberFile - 1][i + 4] = coluna5;
							System.out.println("coluna5: " + coluna5);
							//nom_class
							//numero metodos por classe
							break;
						case 1:
							String coluna6 = this.getClassSize();
							board[numberFile - 1][i + 4] = coluna6;
							System.out.println("coluna6: " + coluna6);
							//numero de linhas da classe
							//loc_class
							
							break;
						case 2:
							
							//complexidade ciclica da classe
							String coluna7 = this.getCyclicClass();
							board[numberFile - 1][i + 4] = coluna7;
							System.out.println("coluna7: " + coluna7);
							break;
						case 3:
							String coluna8 = this.getSizeMethods();
							board[numberFile - 1][i + 4] = coluna8;
							System.out.println("coluna8: " + coluna8);
							//numero de linhas do metodo
							
							break;
						case 4:
							
							String coluna9 = this.getCyclicMethods();
							board[numberFile - 1][i + 4] = coluna9;
							System.out.println("coluna9: " + coluna9);
							
							//complexidade ciclioca do metodo
							break;
					}
				}
			}
		}
	
	
	/**
	 * Puts the names of the methods into a single String to be deciphered later
	 * 
	 * @return String includes the names of all the methods of the class
	 */
	
	public String getNameMethods() {
		
		StringBuilder list = new StringBuilder();
//		list.append("|");
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
	
	/**
	 * Puts the size of the methods into a single String to be deciphered later
	 * 
	 * @return String includes the size of all the methods of the class
	 */
	
	public String getSizeMethods() {
		
		StringBuilder list = new StringBuilder();
//		list.append("|");
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
	
	/**
	 * Puts the cyclic complexity of the methods into a single String to be deciphered later
	 * 
	 * @return String includes the cyclic complexity of all the methods of the class
	 */
	
	public String getCyclicMethods() {
		
		StringBuilder list = new StringBuilder();
//		list.append("|");
		List<String> methodSizes = new ArrayList<>();
		VoidVisitor<List<String>> methodCyclicVisitor = new MethodCyclicCollector();
		methodCyclicVisitor.visit(this.cu, methodSizes);
		
		while(!methodSizes.isEmpty()) {
			String method = methodSizes.remove(0);
			
			list.append(method);
			list.append("|");
		}
		return list.toString();
	}
	
	/**
	 * Puts the cyclic complexity of the class into a single String to be deciphered later
	 * 
	 * @return String includes the cyclic complexity of the class
	 */
	
	public String getCyclicClass() {
		
		StringBuilder list = new StringBuilder();
//		list.append("|");
		List<String> methodSizes = new ArrayList<>();
		VoidVisitor<List<String>> classCyclicVisitor = new MethodCyclicCollector();
		classCyclicVisitor.visit(this.cu, methodSizes);
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
	
	/**
	 * Puts the method id into a single String to be deciphered later
	 * 
	 * @return String includes the id of all the methods of the class by the order of appearance
	 */
	
	public String getMethodID() {
		
		StringBuilder list = new StringBuilder();
//		list.append("|");
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
	
	/**
	 * Puts the number of methods into a single String to be deciphered later
	 * 
	 * @return String includes the number of methods of the class
	 */
	
	public String getNumberMethods() {
		
		StringBuilder list = new StringBuilder();
//		list.append("|");
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
	
	/**
	 * Calculates how many methods exist in the class
	 * 
	 * @return integer includes the number of methods
	 */
	
	public int getTotalMethods() {
		
		List<String> methodNames = new ArrayList<>();
		VoidVisitor<List<String>> methodNameVisitor = new MethodNameCollector();
		methodNameVisitor.visit(this.cu, methodNames);
		return methodNames.size();
	}
	
	/**
	 * Puts the size of the class into a String
	 * 
	 * @return String includes the size of the class
	 */
	
	public String getClassSize() {
		
		StringBuilder list = new StringBuilder();
//		list.append("|");
		List<String> classSizes = new ArrayList<>();
		VoidVisitor<List<String>> classSizeVisitor = new ClassSize();
		classSizeVisitor.visit(this.cu, classSizes);
		int size = this.getTotalMethods();
		if(size == 0) {
			size = 1;
		}
		String tamanho = classSizes.remove(classSizes.size() - 1);
		for(int i = 0; i < size; i++) {
			list.append(tamanho);
			list.append("|");
		}
		return list.toString();
	}
	
	/**
	 * Puts the name of main class into a single String to be deciphered later
	 * 
	 * @return String includes the name of the class
	 */
	
	public String getClassName() {
		
		StringBuilder list = new StringBuilder();
//		list.append("|");
		List<String> classSizes = new ArrayList<>();
		VoidVisitor<List<String>> classNameVisitor = new ClassName();
		classNameVisitor.visit(this.cu, classSizes);
		int size = this.getTotalMethods();
		if(size == 0) {
			size = 1;
		}
		String nome = classSizes.remove(classSizes.size() - 1);
		for(int i = 0; i < size; i++) {
			list.append(nome);
			list.append("|");
		}
		return list.toString();
	}
	
	/**
	 * Puts the name of the package into a single String to be deciphered later
	 * 
	 * @return String includes the name of the package
	 */
	
	public String getPackageName() {
		
		Scanner reader;
		String pacote = null;
		String[] nomePacote = null;
		try {
			
			reader = new Scanner(this.files);
			while(reader.hasNext()) {
				pacote = reader.nextLine().toString();
				if(pacote.contains("package")) {
					nomePacote = pacote.split(" ");
					reader.close();
					return nomePacote[1];
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nomePacote[1];
	}
}
package es_g46.es_g46;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Class that allows the .java to be visited by the CompilationUnit
 * 
 * @author Luis Santos
 * @version 2.0
 */

public class ClassSize extends VoidVisitorAdapter<List<String>> {
	
	/**
	 * Returns the sizes of classes of the .java and puts it inside a list
	 * 
	 * @param md file .java with the right parser
	 * @param collector list of strings
	 */
	 public void visit(ClassOrInterfaceDeclaration md, List<String> collector) {
	 super.visit(md, collector);
	 int number = md.getEnd().get().line - md.getBegin().get().line;
	 collector.add(String.valueOf(number));
	 }

}
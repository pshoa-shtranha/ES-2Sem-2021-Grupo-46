package es_g46.es_g46;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Class that allows the .java to be visited by the CompilationUnit
 * 
 * @author Luis Santos
 * @version 2.0
 */

public class MethodNameCollector extends VoidVisitorAdapter<List<String>> {

	/**
	 * Returns the names of methods of the .java and puts it inside a list
	 * 
	 * @param md file .java with the right parser
	 * @param collector list of strings
	 */
	 public void visit(MethodDeclaration md, List<String> collector) {
	 super.visit(md, collector);
	 collector.add(md.getDeclarationAsString(false, false, false));
	 }

}
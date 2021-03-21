package es_g46.es_g46;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodNameCollector extends VoidVisitorAdapter<List<String>> {
	
	@Override
	 public void visit(MethodDeclaration md, List<String> collector) {
	 super.visit(md, collector);
	 collector.add(md.getDeclarationAsString(false, false, false));
	 }

}
package es_g46.es_g46;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassName extends VoidVisitorAdapter<List<String>> {
	
	@Override
	 public void visit(ClassOrInterfaceDeclaration md, List<String> collector) {
	 super.visit(md, collector);
	 collector.add(md.getNameAsString());
	 }

}
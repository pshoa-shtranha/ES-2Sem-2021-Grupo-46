package es_g46.es_g46;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;

/**
 * Class that allows the .java to be visited by the CompilationUnit
 * 
 * @author Luis Santos
 * @version 2.0
 */

public class MethodCyclicCollector extends VoidVisitorAdapter<List<String>> {
	
	/**
	 * Returns the cyclic complexity of the methods of the .java and puts it inside a list
	 * 
	 * @param md file .java with the right parser
	 * @param collector list of strings
	 */
    public void visit(MethodDeclaration md, List<String> collector) {
        super.visit(md, collector);
        int count = 0;
        
        ForStmt forStmt = new ForStmt();
        List<? extends ForStmt> listOfFor = md.findAll(forStmt.getClass());
        count = count + listOfFor.size();
        
        IfStmt ifStmt = new IfStmt();
        List<? extends IfStmt> listOfIf = md.findAll(ifStmt.getClass());
        count = count + listOfIf.size();
        
        ForEachStmt forEachStmt = new ForEachStmt();
        List<? extends ForEachStmt> listOfForEach = md.findAll(forEachStmt.getClass());
        count = count + listOfForEach.size();
        
        WhileStmt whileStmt = new WhileStmt();
        List<? extends WhileStmt> listOfWhile = md.findAll(whileStmt.getClass());
        count = count + listOfWhile.size();
        
        DoStmt doStmt = new DoStmt();
        List<? extends DoStmt> listOfDo = md.findAll(doStmt.getClass());
        count = count + listOfDo.size();
        
        SwitchEntry switchEntryStmt = new SwitchEntry();
        List<? extends SwitchEntry> listOfSwitchEntry = md.findAll(switchEntryStmt.getClass());
        count = count + listOfSwitchEntry.size();
        
        CatchClause catchClauseStmt = new CatchClause();
        List<? extends CatchClause> listOfCatchClause = md.findAll(catchClauseStmt.getClass());
        count = count + listOfCatchClause.size();
        
        ConditionalExpr conditionalExpr = new ConditionalExpr();
        List<? extends ConditionalExpr> listOfConditionalExpr = md.findAll(conditionalExpr.getClass());
        count = count + listOfConditionalExpr.size();
        
        BinaryExpr binaryExpr = new BinaryExpr();
        List<? extends BinaryExpr> listOfBinaryExpr = md.findAll(binaryExpr.getClass());
        count = count + listOfBinaryExpr.size();
        
        collector.add(String.valueOf(count));
        
    }

}
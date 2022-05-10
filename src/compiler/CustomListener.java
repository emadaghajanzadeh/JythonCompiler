package compiler;

import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class CustomListener implements jythonListener {
//    ctx is the whole tree obtained from previous steps.
    private ArrayList<String> imports = new ArrayList<String>();
    private ArrayList<String> classes = new ArrayList<String>();


    @Override
    public void enterProgram(jythonParser.ProgramContext ctx) {
//        System.out.println(ctx.getText());
    }

    @Override
    public void exitProgram(jythonParser.ProgramContext ctx) {
//        System.out.println(ctx.getText());

    }

    @Override
    public void enterImportclass(jythonParser.ImportclassContext ctx) {
        String originalText = ctx.getText();
        imports.add(originalText.replace("import", ""));

    }

    @Override
    public void exitImportclass(jythonParser.ImportclassContext ctx) {

    }

    @Override
    public void enterClassDef(jythonParser.ClassDefContext ctx) {
        String originalText = ctx.getText();
        System.out.println(originalText);

        String classString = "class";
        int index = originalText.indexOf(classString);
        int index_open = originalText.indexOf("(");
        int index_close = originalText.indexOf(")");


        int classNameIndex = index + classString.length();
        String temp = originalText.substring(classNameIndex);
        String className = temp.split("\\(")[0];
        classes.add(className);

        String insideCure = originalText.substring(index_open+1, index_close);
        for (String s:insideCure.split(",")){
            classes.add(s);
        }

        System.out.println(classes);



//        String classString = "class";
//        int index = originalText.indexOf(classString);
//        while (index >= 0) {
//
//            int classNameIndex = index + classString.length();
//            String temp = originalText.substring(classNameIndex);
//
//            String className = temp.split(" ")[0];
//            System.out.println(className);
//            index = originalText.indexOf("class", index + 1);
//        }


    }

    @Override
    public void exitClassDef(jythonParser.ClassDefContext ctx) {
//        System.out.println(ctx.getText());

    }

    @Override
    public void enterClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void exitClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void enterVarDec(jythonParser.VarDecContext ctx) {
//        System.out.println(ctx.getText());

    }

    @Override
    public void exitVarDec(jythonParser.VarDecContext ctx) {

    }

    @Override
    public void enterArrayDec(jythonParser.ArrayDecContext ctx) {

    }

    @Override
    public void exitArrayDec(jythonParser.ArrayDecContext ctx) {

    }

    @Override
    public void enterMethodDec(jythonParser.MethodDecContext ctx) {

    }

    @Override
    public void exitMethodDec(jythonParser.MethodDecContext ctx) {

    }

    @Override
    public void enterConstructor(jythonParser.ConstructorContext ctx) {

    }

    @Override
    public void exitConstructor(jythonParser.ConstructorContext ctx) {

    }

    @Override
    public void enterParameter(jythonParser.ParameterContext ctx) {

    }

    @Override
    public void exitParameter(jythonParser.ParameterContext ctx) {

    }

    @Override
    public void enterStatement(jythonParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(jythonParser.StatementContext ctx) {

    }

    @Override
    public void enterReturn_statment(jythonParser.Return_statmentContext ctx) {

    }

    @Override
    public void exitReturn_statment(jythonParser.Return_statmentContext ctx) {

    }

    @Override
    public void enterCondition_list(jythonParser.Condition_listContext ctx) {

    }

    @Override
    public void exitCondition_list(jythonParser.Condition_listContext ctx) {

    }

    @Override
    public void enterCondition(jythonParser.ConditionContext ctx) {

    }

    @Override
    public void exitCondition(jythonParser.ConditionContext ctx) {

    }

    @Override
    public void enterIf_statment(jythonParser.If_statmentContext ctx) {

    }

    @Override
    public void exitIf_statment(jythonParser.If_statmentContext ctx) {

    }

    @Override
    public void enterWhile_statment(jythonParser.While_statmentContext ctx) {

    }

    @Override
    public void exitWhile_statment(jythonParser.While_statmentContext ctx) {

    }

    @Override
    public void enterIf_else_statment(jythonParser.If_else_statmentContext ctx) {

    }

    @Override
    public void exitIf_else_statment(jythonParser.If_else_statmentContext ctx) {

    }

    @Override
    public void enterPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(jythonParser.For_statmentContext ctx) {

    }

    @Override
    public void exitFor_statment(jythonParser.For_statmentContext ctx) {

    }

    @Override
    public void enterMethod_call(jythonParser.Method_callContext ctx) {

    }

    @Override
    public void exitMethod_call(jythonParser.Method_callContext ctx) {

    }

    @Override
    public void enterAssignment(jythonParser.AssignmentContext ctx) {

    }

    @Override
    public void exitAssignment(jythonParser.AssignmentContext ctx) {

    }

    @Override
    public void enterExp(jythonParser.ExpContext ctx) {

    }

    @Override
    public void exitExp(jythonParser.ExpContext ctx) {

    }

    @Override
    public void enterPrefixexp(jythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void exitPrefixexp(jythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void enterArgs(jythonParser.ArgsContext ctx) {

    }

    @Override
    public void exitArgs(jythonParser.ArgsContext ctx) {

    }

    @Override
    public void enterExplist(jythonParser.ExplistContext ctx) {

    }

    @Override
    public void exitExplist(jythonParser.ExplistContext ctx) {

    }

    @Override
    public void enterArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void exitArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void enterRelational_operators(jythonParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void exitRelational_operators(jythonParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void enterAssignment_operators(jythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void exitAssignment_operators(jythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

    public void print(){
        System.out.println();
    }
}

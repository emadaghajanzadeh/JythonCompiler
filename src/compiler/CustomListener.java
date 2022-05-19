package compiler;

import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomListener implements jythonListener {
//    ctx is the whole tree obtained from previous steps.
    private ArrayList<String> imports = new ArrayList<String>();
    private ArrayList<String> classes = new ArrayList<String>();

    public int isInsideIf = 0;
    public int isInsideWhile = 0;
    public int isInsideIfElse = 0;
    public int isInsideFor = 0;
//    private ArrayList<Boolean> instructions = new ArrayList<Boolean>();
    private int[] instructions = new int[4];

    public String arguments = "parameters list: [";
    private int indentNum = 0;

    @Override
    public void enterProgram(jythonParser.ProgramContext ctx) {
        System.out.println("Program Start{");
        indentNum ++;
        instructions[0] = isInsideIf;
        instructions[1] = isInsideWhile;
        instructions[2] = isInsideIfElse;
        instructions[3] = isInsideFor;

//        System.out.println(ctx.getText());
    }

    @Override
    public void exitProgram(jythonParser.ProgramContext ctx) {
//        System.out.println(ctx.getText());
        indentNum--;
        System.out.println( getIndent() + "}");

    }

    @Override
    public void enterImportclass(jythonParser.ImportclassContext ctx) {
        System.out.println(getIndent()+ "import class: "+ctx.CLASSNAME().getText());

//        String originalText = ctx.getText();
//        imports.add(originalText.replace("import", ""));

    }

    @Override
    public void exitImportclass(jythonParser.ImportclassContext ctx) {

    }

    @Override
    public void enterClassDef(jythonParser.ClassDefContext ctx) {
        String classDef = getIndent();
        classDef = classDef + "class: "+ ctx.CLASSNAME(0);
        if (ctx.CLASSNAME().size()>=2) classDef = classDef + "/ class parents: ";
        for (int i = 1 ; i<ctx.CLASSNAME().size(); i++){
            classDef = classDef + ctx.CLASSNAME(i) + ",";
        }
        classDef = classDef + " {";
        indentNum++;
        System.out.println(classDef);
    }

    @Override
    public void exitClassDef(jythonParser.ClassDefContext ctx) {
        indentNum --;
        System.out.println(getIndent() + "}");

    }

    @Override
    public void enterClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void exitClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void enterVarDec(jythonParser.VarDecContext ctx) {
        //Each Variable has either (ID, TYPE) or (ID, CLASSNAME).
        // The former is for pre-declared variable types and the later is for newly declared variables such as class objects.
//        System.out.println(ctx.getText());
//        System.out.println(ctx.ID().getText());
//        try {
//            System.out.println(ctx.TYPE().getText());
//        }catch (Exception e){
//            System.out.println("-");
//        }
//        try {
//            System.out.println(ctx.CLASSNAME().getText());
//        }catch(Exception e1){
//            System.out.println("-");
//        }
//        System.out.println(ctx.getParent());
        String id = "";
        String type_ = "";
        String class_name = "";
        if(ctx.ID() != null){
            id = ctx.ID().getText();
        }
        if(ctx.TYPE() != null){
            type_ = ctx.TYPE().getText();
        }
        if (ctx.CLASSNAME() != null){
            class_name = ctx.CLASSNAME().getText();
        }

        //Detect inputs of a function.
        if (ctx.getParent() instanceof jythonParser.ParameterContext){
            arguments = arguments + class_name + type_ +
                    " " + id + ",";
        }
        else {
            if (ctx.CLASSNAME() != null) {
                class_name = ctx.CLASSNAME().getText();
            }
            System.out.println(getIndent() + "field: " + id + "/ type= " + class_name + type_);
        }


    }

    @Override
    public void exitVarDec(jythonParser.VarDecContext ctx) {

    }

    @Override
    public void enterArrayDec(jythonParser.ArrayDecContext ctx) {
//        System.out.println(ctx.getText());
        String id = "";
        String type_ = "";
        String class_name = "";
        if(ctx.ID() != null){
            id = ctx.ID().getText();
        }
        if(ctx.TYPE() != null){
            type_ = ctx.TYPE().getText();
        }
        if (ctx.CLASSNAME() != null){
            class_name = ctx.CLASSNAME().getText();
        }
        System.out.println(getIndent() + "field: " + id + "/ type= " + class_name + type_);

    }

    @Override
    public void exitArrayDec(jythonParser.ArrayDecContext ctx) {

    }

    @Override
    public void enterMethodDec(jythonParser.MethodDecContext ctx) {
        String id = "";
        String type_ = "";
        String class_name = "";
        boolean isMain = false;

        if(ctx.ID() != null){
            id = ctx.ID().getText();
        }
        if(ctx.ID().getText().equals("main")){
            isMain = true;
        }
        if(ctx.TYPE() != null){
            type_ = ctx.TYPE().getText();
        }
        if (ctx.CLASSNAME() != null){
            class_name = ctx.CLASSNAME().getText();
        }


        if (!isMain) {
            if (type_.equals("") && class_name.equals("")) {
                System.out.println(getIndent() + "class method: " + id + "{");
            } else {
                System.out.println(getIndent() + "class method: " + id + "/ return type: " +
                        type_ + class_name + "{");
            }
        }else{
            if (type_.equals("") && class_name.equals("")) {
                System.out.println(getIndent() + "main method: " + "{");
            } else {
                System.out.println(getIndent() + "main method: " + "/ return type: " +
                        type_ + class_name + "{");
            }
        }
        indentNum++;

    }

    @Override
    public void exitMethodDec(jythonParser.MethodDecContext ctx) {

        if (arguments != "parameters list: ["){ //if it had input arguments
            System.out.println(getIndent() + arguments + "]");
            arguments = "parameters list: [";
        }
        indentNum--;
        System.out.println(getIndent() + "}");

    }

    @Override
    public void enterConstructor(jythonParser.ConstructorContext ctx) {
        System.out.println(getIndent() + "class constructor: " + ctx.CLASSNAME().getText() + "{");
        indentNum++;
    }

    @Override
    public void exitConstructor(jythonParser.ConstructorContext ctx) {
        System.out.println(getIndent() + arguments + "]");
        arguments = "parameters list: [";
        indentNum--;
        System.out.println(getIndent() + "}");
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
        nestedStatementDetection("if");
    }

    @Override
    public void exitIf_statment(jythonParser.If_statmentContext ctx) {
        nestedStatementExit("if");
    }

    @Override
    public void enterWhile_statment(jythonParser.While_statmentContext ctx) {
        nestedStatementDetection("while");
    }

    @Override
    public void exitWhile_statment(jythonParser.While_statmentContext ctx) {
        nestedStatementExit("while");
    }

    @Override
    public void enterIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        nestedStatementDetection("if-else");
    }

    @Override
    public void exitIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        nestedStatementExit("if-else");
    }

    @Override
    public void enterPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(jythonParser.For_statmentContext ctx) {
        nestedStatementDetection("for");
    }

    @Override
    public void exitFor_statment(jythonParser.For_statmentContext ctx) {
        nestedStatementExit("for");
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

    private String getIndent(){
        String s = "";
        for (int i = 0; i < indentNum; i++){
            s = s + "\t";
        }
        return s;
    }

    private void nestedStatementDetection(String inst){
        for(int instructNum: instructions){
            if(instructNum>0){
                System.out.println(getIndent() + "nested statement{");
                indentNum++;
                break;
            }
        }
        if (inst == "if"){
            instructions[0]++;
        }else if(inst == "while"){
            instructions[1]++;
        }else if (inst == "if-else"){
            instructions[2]++;
        }else if (inst == "for"){
            instructions[3]++;
        }
    }

    private void nestedStatementExit(String inst){

        if ((instructions[0]+instructions[1]+instructions[2]+instructions[3])>1){    //if we have created a nested loop for this inst already, then we should now close its brackets.
            indentNum--;
            System.out.println(getIndent() + "}");
        }
        if (inst == "if"){
            instructions[0]--;
        }else if(inst == "while"){
            instructions[1]--;
        }else if (inst == "if-else"){
            instructions[2]--;
        }else if (inst == "for"){
            instructions[3]--;
        }
    }
}

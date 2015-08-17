package com.eclipse.knife;


public class Main {
    public static void main(String args[]){
//        ListLexer lexer = new ListLexer(args[0]);
//        ListParser parser = new ListParser(lexer)
//        parser.list();
        SimpleAnalysis sa = new SimpleAnalysis();
        sa.parse();
        sa.codeGen();
    }
    
}

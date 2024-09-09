package com.pair.CreateProblem;

import java.util.Random;

public class ProblemAnalysis {

    public static String ADD = " + ";
    public static String SUB = " - ";
    public static String MUL = " × ";
    public static String DIV = " ÷ ";
    public static String[] OPERATOR={ADD,SUB,MUL,DIV};
    public static String EQU = " = ";
    public static String SLA = "/";

    public static void create(int num,int scope){
        for(int i = 0; i < num; i++){
            Random random = new Random();


            System.out.println(random.nextInt(scope));
        }
    }
}

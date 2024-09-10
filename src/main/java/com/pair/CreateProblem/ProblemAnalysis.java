package com.pair.CreateProblem;

import com.pair.AnswerCheck.ResultComputation;

import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;

public class ProblemAnalysis {

    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "×";
    public static final String DIV = "÷";
    public static final String[] OPERATOR={ADD,SUB,MUL,DIV};
    public static final String EQU = " = ";
    public static final String SLA = "/";

    public static void create(int loop,int scope){
        for(int i = 0; i < loop; i++){
            Random random = new Random();
            StringJoiner sj = new StringJoiner(" ","",EQU);

            // 生成随机数1
            int num1 = random.nextInt(scope);
            sj.add(String.valueOf(num1));

            for (int j = 0; j < random.nextInt(3) + 1; j++){
                // 随机选择运算符
                String opera = OPERATOR[random.nextInt(4)];
                // 生成随机数2，非零判断及零不作分母判断
                int num2 = random.nextInt( Objects.equals(opera, DIV) ? scope - 1 :scope);
                if(Objects.equals(opera, DIV)) num2 += 1;

                System.out.println(
                        sj.add(opera)
                          .add(String.valueOf(num2))
                );
            }

            String result = ResultComputation.result(String.valueOf(sj));
            System.out.println("结果为：" + result);
        }
    }
}

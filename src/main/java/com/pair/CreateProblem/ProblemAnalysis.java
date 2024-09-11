package com.pair.CreateProblem;

import com.pair.AnswerCheck.ResultComputation;
import com.pair.ReadWriteUtils.OutputFile;

import java.io.IOException;
import java.util.*;

public class ProblemAnalysis {

    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "×";
    public static final String DIV = "÷";
    public static final String[] OPERATOR = {ADD, SUB, MUL, DIV};
    public static final String EQU = "=";
    public static final String SLA = "/";
    public static final String APO = "'";
    public static final String LEF = "(";
    public static final String RIG = ")";
    public static final ArrayList<String> resArr = new ArrayList<>();
    public static final ArrayList<String> proArr = new ArrayList<>();

    public static void create(int loop, int scope) throws IOException {
        for (int i = 0; i < loop; i++) {
            Random random = new Random();
            StringJoiner sj = new StringJoiner(" ", "", " " + EQU + " ");

            boolean flag = false;
            boolean flag0 = false;

            // 生成随机数1
            int num1 = random.nextInt(scope);
            int n20 = random.nextInt(9) + 1;
            int n10 = random.nextInt(n20 - 1 == 0 ? 1 : n20 - 1) + 1;

            int n30 = n10 + num1 * n20;

            if (random.nextInt(3) == 0) {
                flag = true;
                sj.add(LEF);
            }

            sj.add(ResultComputation.Simplify(n30 + SLA + n20));


            for (int j = 0; j < random.nextInt(3) + 1; j++) {
                // 随机选择运算符
                String opera = OPERATOR[random.nextInt(4)];
                // 生成随机数2，非零判断及零不作分母判断
                int num2 = random.nextInt(Objects.equals(opera, DIV) ? scope - 1 : scope);
                if (Objects.equals(opera, DIV)) num2 += 1;

                int n2 = random.nextInt(9) + 1;
                int n1 = random.nextInt(n2 - 1 == 0 ? 1 : n2 - 1) + 1;

                int n3 = n1 + num2 * n2;

                // 真分数转假分数，调用函数转真分数同时化简
                String str = ResultComputation.Simplify(n3 + SLA + n2);

                sj.add(opera);

                if (random.nextInt(3) == 0 && !flag) {
                    flag = true;
                    sj.add(LEF);
                }

                sj.add(str);

                if (random.nextInt(3) == 0 && flag && !flag0) {
                    flag0 = true;
                    sj.add(RIG);
                }
            }
            if (flag && !flag0) sj.add(RIG);

            String pro = sj.toString();
            String[] pA = pro.split(" ");
            if (Objects.equals(pA[0], LEF) && Objects.equals(pA[pA.length - 2], RIG))
                pro = pro.replace(LEF + " ", "").replace(" " + RIG, "");
            for (int n = 0; n < pA.length; n++) {
                if (Objects.equals(pA[n], LEF) && Objects.equals(pA[n + 2], RIG))
                    pro = pro.replace(LEF + " ", "").replace(" " + RIG, "");
            }

            System.out.println("题目为：" + pro);
            String result = ResultComputation.result(pro);
            System.out.println("结果为：" + result);
            if (Integer.parseInt(result.split("['/]")[0]) < 0) create(1, scope);
            else if (ProblemCheck.CheckRes(resArr.toArray(new String[0]), result)) create(1, scope);
            else {
                proArr.add(pro);
                resArr.add(result);
            }

        }

        OutputFile.Output(proArr.toArray(new String[0]), "Exercises.txt");
        OutputFile.Output(resArr.toArray(new String[0]), "Answers.txt");
    }
}

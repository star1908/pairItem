package com.pair.CreateProblem;

import com.pair.AnswerCheck.ResultComputation;
import com.pair.ReadWriteUtils.OutputFile;

import java.io.IOException;
import java.util.*;

public class ProblemAnalysis {

    // 定义符号及运算符常类
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
    // 定义问题与结果存放ArrayList
    public static final ArrayList<String> resArr = new ArrayList<>();
    public static final ArrayList<String> proArr = new ArrayList<>();

    /**
     * 生成运算问题
     *
     * @param loop  循环次数
     * @param scope 数字范围
     */
    public static void create(int loop, int scope) throws IOException {
        for (int i = 0; i < loop; i++) {
            Random random = new Random();
            StringJoiner sj = new StringJoiner(" ", "", " " + EQU + " ");

            // 定义布尔判断左右括号是否随机生成
            boolean flag = false;
            boolean flag0 = false;

            // 随机生成第一个数值
            int num1 = random.nextInt(scope);
            int n20 = random.nextInt(9) + 1;
            int n10 = random.nextInt(n20 - 1 == 0 ? 1 : n20 - 1) + 1;

            int n30 = n10 + num1 * n20;

            // 随机生成左括号
            if (random.nextInt(3) == 0) {
                flag = true;
                sj.add(LEF);
            }

            sj.add(ResultComputation.Simplify(n30 + SLA + n20));

            // 随机循环1~3，生成剩余的运算符和数值
            for (int j = 0; j < random.nextInt(3) + 1; j++) {
                // 随机选择运算符
                String opera = OPERATOR[random.nextInt(4)];
                // 随机生成第二个数值，非零判断及零不作分母判断
                int num2 = random.nextInt(Objects.equals(opera, DIV) ? scope - 1 : scope);
                if (Objects.equals(opera, DIV)) num2 += 1;

                int n2 = random.nextInt(9) + 1;
                int n1 = random.nextInt(n2 - 1 == 0 ? 1 : n2 - 1) + 1;

                int n3 = n1 + num2 * n2;

                // 真分数转假分数，调用函数转真分数同时化简
                String str = ResultComputation.Simplify(n3 + SLA + n2);

                sj.add(opera);

                // 随机生成左括号
                if (random.nextInt(3) == 0 && !flag) {
                    flag = true;
                    sj.add(LEF);
                }

                sj.add(str);

                // 若有左括号，随机生成右括号
                if (random.nextInt(3) == 0 && flag && !flag0) {
                    flag0 = true;
                    sj.add(RIG);
                }
            }
            // 若有左括号，无右括号，在算式最后生成右括号
            if (flag && !flag0) sj.add(RIG);

            String pro = sj.toString();
            String[] pA = pro.split(" "); // 分割算式数组
            // 若括号内只有一个数值或将整个算式括起来，无意义，去除括号
            if (Objects.equals(pA[0], LEF) && Objects.equals(pA[pA.length - 2], RIG))
                pro = pro.replace(LEF + " ", "").replace(" " + RIG, "");
            for (int n = 0; n < pA.length; n++) {
                if (Objects.equals(pA[n], LEF) && Objects.equals(pA[n + 2], RIG))
                    pro = pro.replace(LEF + " ", "").replace(" " + RIG, "");
            }

            System.out.println("题目为：" + pro);
            String result = ResultComputation.result(pro);
            System.out.println("结果为：" + result);
            // 自然数部分小于0，重新生成算式
            if (Integer.parseInt(result.split("['/]")[0]) < 0) create(1, scope);
            else if (ProblemCheck.CheckRes(resArr.toArray(new String[0]), result))
                create(1, scope); // 结果与以往结果一致，重新生成算式，避免重复题目
            else {
                proArr.add(pro);
                resArr.add(result);
            }

        }

        // 调用文件输出类函数，输出结果和算式
        OutputFile.Output(proArr.toArray(new String[0]), "Exercises.txt");
        OutputFile.Output(resArr.toArray(new String[0]), "Answers.txt");
    }
}

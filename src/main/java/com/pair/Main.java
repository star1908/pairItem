package com.pair;

import com.pair.AnswerCheck.ResultCheck;
import com.pair.CreateProblem.ProblemAnalysis;
import com.pair.Exception.InsufficientParametersException;

import java.io.IOException;
import java.util.Objects;

public class Main {
    /**
     * 主启动方法main
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) throws IOException {

        String[] argsArr = new String[]{"", "", "", ""};
        argsArr[0] = "1";

        // 遍历命令行参数，匹配参数类型
        for (int i = 0; i < args.length; i += 2) {
            // System.out.println(args[i]);
            switch (args[i]) {
                case "-n":
                    System.out.println("数目参数" + args[i + 1]);
                    argsArr[0] = args[i + 1];
                    break;
                case "-r":
                    System.out.println("范围参数" + args[i + 1]);
                    argsArr[1] = args[i + 1];
                    break;
                case "-e":
                    System.out.println("题目文件" + args[i + 1]);
                    argsArr[2] = args[i + 1];
                    break;
                case "-a":
                    System.out.println("答案文件" + args[i + 1]);
                    argsArr[3] = args[i + 1];
                    break;
            }
        }

        // 若 -r 不为空，进入题目创建函数
        if (!Objects.equals(argsArr[1], ""))
            ProblemAnalysis.create(Integer.parseInt(argsArr[0]), Integer.parseInt(argsArr[1]));
        else if (Objects.equals(argsArr[2], "") && Objects.equals(argsArr[3], "")) // 若 -e 和 -a 也不存在，抛出命令行参数异常
            throw new InsufficientParametersException("命令行未输入数值范围，请加上例如“ -r 10 ”再次运行程序。");

        // 检查文件答案对错函数
        ResultCheck.Check(argsArr[2], argsArr[3]);
    }
}
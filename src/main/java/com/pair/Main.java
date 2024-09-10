package com.pair;

import com.pair.CreateProblem.ProblemAnalysis;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // String[] arr = ReadFile.Read("D:/test/tt.txt");

        String[] argsArr = new String[]{ "", "", "", "" };
        argsArr[0] = "1";

        for(int i = 0; i<args.length;i+=2){
            // System.out.println(args[i]);
            switch (args[i]){
                case "-n":
                    System.out.println("数目参数" + args[i+1]);
                    argsArr[0]=args[i+1];
                    break;
                case "-r":
                    System.out.println("范围参数" + args[i+1]);
                    argsArr[1]=args[i+1];
                    break;
                case "-e":
                    System.out.println("题目文件" + args[i+1]);
                    argsArr[2]=args[i+1];
                    break;
                case "-a":
                    System.out.println("答案文件" + args[i+1]);
                    argsArr[3]=args[i+1];
                    break;
            }
        }

        ProblemAnalysis.create(Integer.parseInt(argsArr[0]),Integer.parseInt(argsArr[1]));

        // OutputFile.Output(arr,"D:/test/tf.txt");
    }
}
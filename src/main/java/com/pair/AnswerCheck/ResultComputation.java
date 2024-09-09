package com.pair.AnswerCheck;

import com.pair.CreateProblem.ProblemAnalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static com.pair.CreateProblem.ProblemAnalysis.*;

public class ResultComputation {
    public static int result(String opera){
        // 分割字符串
        String[] arr = opera.split(" ");
        int result = 0;

        for(int i = 0; i < arr.length; i++) {
            int finalI = i;
            if (Arrays.stream(ProblemAnalysis.OPERATOR).anyMatch(item -> Objects.equals(item, arr[finalI]))) {
                String[] strArr = {};
                String[] strArr1 = {};

                // 分离分子与分母
                strArr = arr[i - 1].split("/");
                arr[i - 1] = strArr[0];
                strArr1 = arr[i + 1].split("/");
                arr[i + 1] = strArr1[0];

                switch (arr[i]) {
                    case ADD: // 调用加法，如遇分数则乘数乘以对方分母再加上分子
                        if(strArr.length != 1) arr[i + 1] = String.valueOf(Integer.parseInt(arr[i + 1]) * Integer.parseInt(strArr[1]));
                        if(strArr1.length != 1) arr[i - 1] = String.valueOf(Integer.parseInt(arr[i - 1]) * Integer.parseInt(strArr1[1]));
                        arr[i + 1] = String.valueOf(Integer.parseInt(arr[i - 1]) + Integer.parseInt(arr[i + 1]));
                        break;
                    case SUB: // 调用减法，如遇分数则乘数乘以对方分母再减去分子
                        if(strArr.length != 1) arr[i + 1] = String.valueOf(Integer.parseInt(arr[i + 1]) * Integer.parseInt(strArr[1]));
                        if(strArr1.length != 1) arr[i - 1] = String.valueOf(Integer.parseInt(arr[i - 1]) * Integer.parseInt(strArr1[1]));
                        arr[i + 1] = String.valueOf(Integer.parseInt(arr[i - 1]) - Integer.parseInt(arr[i + 1]));
                        break;
                    case MUL: // 调用乘法，分子互乘，分母互乘
                        if(strArr.length != 1 && strArr1.length != 1) {
                            strArr[1] = String.valueOf(Integer.parseInt(strArr[1]) * Integer.parseInt(strArr1[1]));
                            strArr1 = new String[]{strArr1[0]};
                        }
                        arr[i + 1] = String.valueOf(Integer.parseInt(strArr[0]) * Integer.parseInt(strArr1[0]));
                        break;
                    case DIV: // 调用除法，结果为假分数，例如6/4/3，后续化简
                        if(strArr1.length != 1) {
                            // 除以分数相当于乘其倒数，故翻转为倒数
                            strArr1 = new String[]{strArr1[1], strArr1[0]};

                            // 调用乘法
                            if(strArr.length != 1) {
                                strArr[1] = String.valueOf(Integer.parseInt(strArr[1]) * Integer.parseInt(strArr1[1]));
                                strArr1 = new String[]{strArr1[0]};
                            }
                            arr[i + 1] = String.valueOf(Integer.parseInt(strArr[0]) * Integer.parseInt(strArr1[0]));
                        }
                        else arr[i + 1] = arr[i - 1] + "/" + arr[i + 1]; // 除法以分数形式，后续化简
                        break;
                }

                // 加上分母
                if(strArr.length != 1) arr[i + 1] = arr[i + 1] + "/" + strArr[1];
                if(strArr1.length != 1) arr[i + 1] = arr[i + 1] + "/" + strArr1[1];
                arr[i] = "";
                arr[i - 1] = "";

            }

        }
        System.out.println("结果为：" + arr[arr.length - 2]);

        return result;
    }
}

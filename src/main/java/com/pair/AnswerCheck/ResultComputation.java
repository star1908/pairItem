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

                strArr = arr[i - 1].split("/");
                arr[i - 1] = strArr[0];
                strArr1 = arr[i + 1].split("/");
                arr[i + 1] = strArr1[0];

                switch (arr[i]) {
                    case ADD:
                        if(strArr.length != 1) arr[i + 1] = String.valueOf(Integer.parseInt(arr[i + 1]) * Integer.parseInt(strArr[1]));
                        if(strArr1.length != 1) arr[i - 1] = String.valueOf(Integer.parseInt(arr[i - 1]) * Integer.parseInt(strArr1[1]));
                        arr[i + 1] = String.valueOf(Integer.parseInt(arr[i - 1]) + Integer.parseInt(arr[i + 1]));
                        break;
                    case SUB:
                        if(strArr.length != 1) arr[i + 1] = String.valueOf(Integer.parseInt(arr[i + 1]) * Integer.parseInt(strArr[1]));
                        if(strArr1.length != 1) arr[i - 1] = String.valueOf(Integer.parseInt(arr[i - 1]) * Integer.parseInt(strArr1[1]));
                        arr[i + 1] = String.valueOf(Integer.parseInt(arr[i - 1]) - Integer.parseInt(arr[i + 1]));
                        break;
                    case MUL:
                        arr[i + 1] = String.valueOf(Integer.parseInt(arr[i - 1]) * Integer.parseInt(arr[i + 1]));
                        break;
                    case DIV:
                        arr[i + 1] = arr[i - 1] + "/" + arr[i + 1];
                        break;
                }

                if(strArr.length != 1) arr[i + 1] = arr[i + 1] + "/" + strArr[1];
                if(strArr1.length != 1) arr[i + 1] = arr[i + 1] + "/" + strArr1[1];
                arr[i] = "";
                arr[i - 1] = "";

                System.out.println(Arrays.toString(arr));
            }
        }

        return result;
    }
}

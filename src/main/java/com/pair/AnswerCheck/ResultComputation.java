package com.pair.AnswerCheck;

import java.util.Arrays;
import java.util.Objects;

import static com.pair.CreateProblem.ProblemAnalysis.*;

public class ResultComputation {
    public static String[] lsArr = new String[0];
    public static int cs = 0;

    public static String result(String opera){
        // 分割字符串
        String[] arr = opera.split(" ");

        String[] operaArr = new String[]{ MUL, DIV, ADD, SUB };

        for(int oi = 0; oi < operaArr.length; oi += 2){

            for(int i = 0; i < arr.length; i++) {
                if (Objects.equals(arr[i], operaArr[oi]) || Objects.equals(arr[i], operaArr[oi + 1])) {
                    String[] strArr;
                    String[] strArr1;

                    // 分离分子与分母
                    strArr = arr[i - 1].split("/");
                    arr[i - 1] = strArr[0];
                    strArr1 = arr[i + 1].split("/");
                    arr[i + 1] = strArr1[0];

                    if(operaArr[oi].equals(MUL)) switch (arr[i]) {
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
                    } else switch (arr[i]) {
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
                    }

                    // 加上分母
                    if(strArr.length != 1) arr[i + 1] = arr[i + 1] + "/" + strArr[strArr.length - 1];
                    if(strArr1.length != 1) arr[i + 1] = arr[i + 1] + "/" + strArr1[strArr1.length - 1];
                    arr[i] = "";
                    arr[i - 1] = "";

                    // 双除数时，化简分母
                    if(arr[i + 1].split("/").length > 2) {
                        String[] arrTest = arr[i + 1].split("/");
                        int num = Integer.parseInt(arrTest[1]) * Integer.parseInt(arrTest[2]);
                        arr[i + 1] = arrTest[0] + "/" + num;
                    }

                    // 计算数组剩余运算符和数字
                    int number = 0;
                    for (String string : arr) {
                        if (!Objects.equals(string, "")) {
                            number++;
                        }
                    }

                    // 新建数组存储剩余运算符和数字
                    lsArr = new String[number];
                    int n = 0;
                    for (String s : arr) {
                        if (!Objects.equals(s, "")) {
                            lsArr[n] = s;
                            n++;
                        }
                    }
                    System.out.println("数组："+Arrays.toString(lsArr));

                }

            }
            // 将重组的数组赋值再次遍历
            arr = lsArr;

        }
        System.out.println(Simplify(arr[arr.length - 2]));
        return Simplify(arr[arr.length - 2]);
    }

    public static String Simplify(String str){
        String[] strArr = str.split("/");

        if(strArr.length != 1) {
            // 计算自然数
            int num0 = Integer.parseInt(strArr[0]) / Integer.parseInt(strArr[1]);

            strArr[0] = String.valueOf(Integer.parseInt(strArr[0]) % Integer.parseInt(strArr[1]));

            // 求最大公因数
            int x = Integer.parseInt(strArr[0]);
            int y = Integer.parseInt(strArr[1]);
            while (y != 0) {
                int r = x % y;
                x = y;
                y = r;
            }

            String res = num0 + "'" + Integer.parseInt(strArr[0]) / x + "/" + Integer.parseInt(strArr[1]) / x;
            if(Integer.parseInt(strArr[0]) / x == 0) res = String.valueOf(num0);

            return res;
        }

        return strArr[0];
    }

}

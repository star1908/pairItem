package com.pair.AnswerCheck;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

import static com.pair.CreateProblem.ProblemAnalysis.*;

public class ResultComputation {
    public static String[] lsArr = new String[0];

    public static String result(String opera) {
        // 分割字符串
        String[] arr = opera.split(" ");

        if (Objects.equals(arr[arr.length - 1], EQU)) arr = Arrays.copyOf(arr, arr.length - 1);

        String[] operaArr = new String[]{MUL, DIV, ADD, SUB};

        // 解析数字，由真分数转假分数
        for (int k = 0; k < arr.length; k++) {
            String[] ar = arr[k].split(APO);
            if (ar.length != 1
                    && !Objects.equals(ar[0], operaArr[0])
                    && !Objects.equals(ar[0], operaArr[1])
                    && !Objects.equals(ar[0], operaArr[2])
                    && !Objects.equals(ar[0], operaArr[3])
            ) {
                String[] ar2 = ar[1].split(SLA);
                ar[0] = String.valueOf(Integer.parseInt(ar[0]) * Integer.parseInt(ar2[1]));
                arr[k] = Integer.parseInt(ar[0]) + Integer.parseInt(ar2[0]) + SLA + ar2[1];
            }
        }
        lsArr = arr;

        int km = -1, lm = -1;
        for (int k = 0; k < arr.length; k++) {
            if (Objects.equals(arr[k], LEF)) {
                km = k;
                break;
            }
        }
        for (int l = km + 1; l < arr.length; l++) {
            if (Objects.equals(arr[l], RIG)) {
                lm = l;
                break;
            }
        }
        if (km != -1 && lm != -1) {
            StringJoiner sj = new StringJoiner(" ");
            for (int m = km + 1; m < lm; m++) {
                sj.add(arr[m]);
                arr[m] = "";
            }
            arr[km] = "";
            arr[lm] = result(String.valueOf(sj));
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
            arr = lsArr;
            // 解析数字，由真分数转假分数
            for (int k = 0; k < arr.length; k++) {
                String[] ar = arr[k].split(APO);
                if (ar.length != 1
                        && !Objects.equals(ar[0], operaArr[0])
                        && !Objects.equals(ar[0], operaArr[1])
                        && !Objects.equals(ar[0], operaArr[2])
                        && !Objects.equals(ar[0], operaArr[3])
                ) {
                    String[] ar2 = ar[1].split(SLA);
                    ar[0] = String.valueOf(Integer.parseInt(ar[0]) * Integer.parseInt(ar2[1]));
                    arr[k] = Integer.parseInt(ar[0]) + Integer.parseInt(ar2[0]) + SLA + ar2[1];
                }
            }
            lsArr = arr;
        }

        for (int oi = 0; oi < operaArr.length; oi += 2) {

            for (int i = 0; i < arr.length; i++) {
                if (Objects.equals(arr[i], operaArr[oi]) || Objects.equals(arr[i], operaArr[oi + 1])) {
                    String[] strArr;
                    String[] strArr1;

                    // 分离分子与分母
                    strArr = arr[i - 1].split(SLA);
                    arr[i - 1] = strArr[0];
                    strArr1 = arr[i + 1].split(SLA);
                    arr[i + 1] = strArr1[0];

                    if (operaArr[oi].equals(MUL)) switch (arr[i]) {
                        case MUL: // 调用乘法，分子互乘，分母互乘
                            if (strArr.length != 1 && strArr1.length != 1) {
                                strArr[1] = String.valueOf(Integer.parseInt(strArr[1]) * Integer.parseInt(strArr1[1]));
                                strArr1 = new String[]{strArr1[0]};
                            }
                            arr[i + 1] = String.valueOf(Integer.parseInt(strArr[0]) * Integer.parseInt(strArr1[0]));
                            break;
                        case DIV: // 调用除法，结果为假分数，例如6/4/3，后续化简

                            if (strArr1.length != 1) {
                                // 除以分数相当于乘其倒数，故翻转为倒数
                                strArr1 = new String[]{strArr1[1], strArr1[0]};

                                // 调用乘法
                                if (strArr.length != 1) {
                                    strArr[1] = String.valueOf(Integer.parseInt(strArr[1]) * Integer.parseInt(strArr1[1]));
                                    strArr1 = new String[]{strArr1[0]};
                                }
                                arr[i + 1] = String.valueOf(Integer.parseInt(strArr[0]) * Integer.parseInt(strArr1[0]));
                            } else arr[i + 1] = arr[i - 1] + SLA + arr[i + 1]; // 除法以分数形式，后续化简
                            break;
                    }
                    else switch (arr[i]) {
                        case ADD: // 调用加法，如遇分数则乘数乘以对方分母再加上分子
                            if (strArr.length != 1)
                                arr[i + 1] = String.valueOf(Integer.parseInt(arr[i + 1]) * Integer.parseInt(strArr[1]));
                            if (strArr1.length != 1)
                                arr[i - 1] = String.valueOf(Integer.parseInt(arr[i - 1]) * Integer.parseInt(strArr1[1]));
                            arr[i + 1] = String.valueOf(Integer.parseInt(arr[i - 1]) + Integer.parseInt(arr[i + 1]));
                            break;
                        case SUB: // 调用减法，如遇分数则乘数乘以对方分母再减去分子
                            if (strArr.length != 1)
                                arr[i + 1] = String.valueOf(Integer.parseInt(arr[i + 1]) * Integer.parseInt(strArr[1]));
                            if (strArr1.length != 1)
                                arr[i - 1] = String.valueOf(Integer.parseInt(arr[i - 1]) * Integer.parseInt(strArr1[1]));
                            arr[i + 1] = String.valueOf(Integer.parseInt(arr[i - 1]) - Integer.parseInt(arr[i + 1]));
                            break;
                    }

                    // 加上分母
                    if (strArr.length != 1) arr[i + 1] = arr[i + 1] + SLA + strArr[strArr.length - 1];
                    if (strArr1.length != 1) arr[i + 1] = arr[i + 1] + SLA + strArr1[strArr1.length - 1];
                    arr[i] = "";
                    arr[i - 1] = "";

                    // 双除数时，化简分母
                    if (arr[i + 1].split(SLA).length > 2) {
                        String[] arrTest = arr[i + 1].split(SLA);
                        int num = Integer.parseInt(arrTest[1]) * Integer.parseInt(arrTest[2]);
                        arr[i + 1] = arrTest[0] + SLA + num;
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
                    // System.out.println("数组："+Arrays.toString(lsArr));

                }

            }
            // 将重组的数组赋值再次遍历
            arr = lsArr;

        }
        // System.out.println(Simplify(arr[arr.length - 2]));
        int ll = arr.length - 1;
        if (Objects.equals(arr[ll], RIG)) ll = arr.length - 2;
        return Simplify(arr[ll]);
    }

    public static String Simplify(String str) {
        String[] strArr = str.split(SLA);

        if (strArr.length != 1) {
            if (Integer.parseInt(strArr[0]) < 0) return "-1";
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

            int num1 = Integer.parseInt(strArr[0]) / x;
            int num2 = Integer.parseInt(strArr[1]) / x;

            // 拼接形式 num0'num1/num2
            String res = num0 + APO + num1 + SLA + num2;
            // 结果分子为零时，仅返回自然数部分
            if (Integer.parseInt(strArr[0]) / x == 0) res = String.valueOf(num0);
            // 自然数为零时，返回分数部分
            if (num0 == 0) res = num1 + SLA + num2;

            return res;
        }

        return strArr[0];
    }

}

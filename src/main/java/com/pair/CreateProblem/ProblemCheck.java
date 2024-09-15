package com.pair.CreateProblem;

import java.util.Objects;

import static com.pair.CreateProblem.ProblemAnalysis.MUL;

public class ProblemCheck {
    /**
     * TODO 检查问题不一致，达到不重复问题的效果（未实现）
     *
     * @param arr 算式数组
     */
    public static void CheckPro(String[] arr) {
        int num = 0;
        int flag;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                flag = 0;
                String[] strArr1 = arr[i].split(" ");
                String[] strArr2 = arr[j].split(" ");

                if (strArr1.length != strArr2.length) return;
                else {
                    for (int k = 0; k < strArr1.length; k++) {
                        for (int l = 0; l < strArr2.length; l++) {
                            if (Objects.equals(strArr1[k], MUL) && Objects.equals(strArr2[l], MUL)) {
                                if ((Objects.equals(strArr1[k - 1], strArr2[l - 1])
                                        && Objects.equals(strArr1[k + 1], strArr2[l + 1]))
                                        || (Objects.equals(strArr1[k + 1], strArr2[l - 1])
                                        && Objects.equals(strArr1[k - 1], strArr2[l + 1]))) {
                                    flag++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 检查问题结果使结果不一致，达到不重复问题的效果
     *
     * @param arr 结果数组
     * @param res 本题结果
     * @return 返回布尔值
     */
    public static boolean CheckRes(String[] arr, String res) {

        for (String s : arr) {
            if (Objects.equals(s, res)) return true;
        }
        return false;
    }
}

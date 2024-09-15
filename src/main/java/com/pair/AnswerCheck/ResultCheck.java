package com.pair.AnswerCheck;

import com.pair.ReadWriteUtils.OutputFile;
import com.pair.ReadWriteUtils.ReadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ResultCheck {
    /**
     * 检查算式文件运算结果是否能和结果文件一致
     *
     * @param exerciseFile 算式文件
     * @param answerFile   结果文件
     */
    public static void Check(String exerciseFile, String answerFile) throws IOException {
        // 调用文件读取类，读取算式和结果
        String[] exerciseArr = ReadFile.Read(exerciseFile);
        String[] answerArr = ReadFile.Read(answerFile);

        String[] result = new String[exerciseArr.length];

        // 去除题号
        for (int i = 0; i < exerciseArr.length; i++) {
            exerciseArr[i] = exerciseArr[i].split("\\. ")[1];
            result[i] = ResultComputation.result(exerciseArr[i]);
        }
        for (int i = 0; i < answerArr.length; i++) {
            answerArr[i] = answerArr[i].split("\\. ")[1];
        }

        // 记录对错
        int correct = 0;
        int wrong = 0;
        ArrayList<String> correctList = new ArrayList<String>();
        ArrayList<String> wrongList = new ArrayList<String>();
        // 循环记录对错和题号
        for (int i = 0; i < result.length; i++) {
            if (Objects.equals(result[i], answerArr[i])) {
                correct++;
                correctList.add(String.valueOf(i + 1));
            } else {
                wrong++;
                wrongList.add(String.valueOf(i + 1));
            }
        }

        System.out.println("Correct: " + correct + " (" + String.join(",", correctList) + ")");
        System.out.println("Wrong: " + wrong + " (" + String.join(",", wrongList) + ")");

        // 调用文件输出类函数，输出比较结果到Grade.txt
        OutputFile.Output(new String[]{"Correct: " + correct + " (" + String.join(",", correctList) + ")",
                "Wrong: " + wrong + " (" + String.join(",", wrongList) + ")"}, "Grade.txt");
    }
}

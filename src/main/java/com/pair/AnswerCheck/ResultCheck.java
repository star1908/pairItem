package com.pair.AnswerCheck;

import com.pair.ReadWriteUtils.OutputFile;
import com.pair.ReadWriteUtils.ReadFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ResultCheck {
    public static void Check(String exerciseFile,String answerFile) throws IOException {
        String[] exerciseArr = ReadFile.Read(exerciseFile);
        String[] answerArr = ReadFile.Read(answerFile);

        String[] result = new String[exerciseArr.length];

        for(int i = 0; i < exerciseArr.length; i++){
            exerciseArr[i] = exerciseArr[i].split("\\. ")[1];
            result[i] = ResultComputation.result(exerciseArr[i]);
        }
        for(int i = 0; i < answerArr.length; i++){
            answerArr[i] = answerArr[i].split("\\. ")[1];
        }

        int correct = 0;
        int wrong = 0;
        ArrayList<String> correctList = new ArrayList<String>();
        ArrayList<String> wrongList = new ArrayList<String>();
        for(int i = 0; i < result.length; i++){
            if(Objects.equals(result[i], answerArr[i])) {
                correct++;
                correctList.add(String.valueOf(i + 1));
            }
            else {
                wrong++;
                wrongList.add(String.valueOf(i + 1));
            }
        }

        System.out.println("Correct: " + correct + " (" + String.join(",",correctList) + ")");
        System.out.println("Wrong: " + wrong + " (" + String.join(",",wrongList) + ")");
        OutputFile.Output(new String[]{ "Correct: " + correct + " (" + String.join(",",correctList) + ")" ,
                "Wrong: " + wrong + " (" + String.join(",",wrongList) + ")" },"Grade.txt");
    }
}

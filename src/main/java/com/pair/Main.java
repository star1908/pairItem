package com.pair;

import com.pair.ReadWriteUtils.OutputFile;
import com.pair.ReadWriteUtils.ReadFile;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] arr = ReadFile.Read("D:/test/tt.txt");

        OutputFile.Output(arr,"D:/test/tf.txt");
    }
}
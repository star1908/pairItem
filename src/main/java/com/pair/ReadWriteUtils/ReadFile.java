package com.pair.ReadWriteUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ReadFile {

    /**
     * 读取指定路径的文件内容并返回其内容的字符串表示
     *
     * @param url 读取文本文件绝对路径
     * @return 文本字符串
     * @throws FileNotFoundException 文件未找到异常
     */
    public static String[] Read(String url) throws FileNotFoundException {
        // 接收文件路径获取文件
        File file = new File(url);

        try {
            // 使用Scanner类读取文件内容
            Scanner scanner = new Scanner(file);
            ArrayList<String> arrayList = new ArrayList<String>();

            // 逐行读取文件内容并追加到StringBuilder对象中
            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }

            // 关闭Scanner对象以释放资源
            scanner.close();

            // 输出文件内容到控制台
            System.out.println(arrayList);

            // 返回文件内容
            return arrayList.toArray(new String[0]);

        } catch (FileNotFoundException e) {
            // 如果文件未找到，抛出运行时异常并附带详细错误信息
            throw new FileNotFoundException("文件未找到" + e);
        }

    }
}

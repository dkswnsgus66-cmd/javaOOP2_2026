package io.ch17;

import java.io.*;

public class ImageCopy {

    public static void main(String[] args) {

        // abc.png파일을 읽어서
        // abc.png 파일을 만들어 보세요

        String sourceFile = "abc.png";
        String destFile = "C:\\_work_java\\abc2.png";

        long start = System.currentTimeMillis();

        readFile(sourceFile,destFile); // 이미지 읽기

        long end = System.currentTimeMillis();

        System.out.println("소요시간: " + (end - start) ); // 1310 1.3 초 걸림
    }


    private static void readFile(String sourceFile, String destFile) {

        try ( FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile,true)
        ) {

            int readData; // 이미지가 숫자로 표현되서 넣어짐

            while ( (readData = fis.read()) != -1 ){ // 와일문 안의 동작이 반복
                fos.write(readData); // 이미지 를 와일문 만큼 반복해서 그림

            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}

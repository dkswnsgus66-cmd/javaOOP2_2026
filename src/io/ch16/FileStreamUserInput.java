package io.ch16;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileStreamUserInput {

    public static void main(String[] args) {

        writeUserInputToFile("user_input.txt");

    } // end of main

    public static void writeUserInputToFile(String fileName) {
        // FileWriter
        /**
         * 키보드 입력은 InputStreamReader(System.in); (바이트 -> 문자 변환)
         * 파일에 쓰기 FileWriter(fileName) (문자 기반 파일 출력)
         */

        try (
                InputStreamReader reader = new InputStreamReader(System.in);
                FileWriter writer = new FileWriter(fileName, true)) {

            System.out.println("텍스트를 입력하세요 (종료: Ctrl + D)");
            // 1. 사용자가 입력한 값을 받자 키보드에서
            int charCode; // 읽는거
            while ((charCode = reader.read()) != -1) {
                //읽는거
                // [] 임시 메모리 공간
                writer.write(charCode); // 쓰는거
                // 문자 하나 받을때 마다 즉시 파일에 저장
                writer.flush();
            }

            System.out.println(fileName + "에 텍스트를 모두 작성함");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}// end of class

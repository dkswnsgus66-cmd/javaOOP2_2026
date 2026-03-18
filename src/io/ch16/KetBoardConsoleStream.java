package io.ch16;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class KetBoardConsoleStream {
    /**
     * 바이트 단위 스트림 형태 : InputStream , OutputStream
     * 문자 기반 스트림 이름형태 : xxxReader, xxxWriter
     */
    public static void main(String[] args) {
        /**
         * InputStreamReader 의 read()메서드는 하나의 문자를 읽어서 유니코드 (정수값)로 반환합니다.
         * PrintWriter는 문자 기반의 출력 스트림 입니다.
         * System.out은 콘솔 출력 입니다.
         */
        // 키보드에서 값을 읽어 보자
//        productFile();

        readFromFile("user_input.txt");

    }// end of main

    // 이거 파일 읽는 메서드네? 졸아서 다른곳에 입력했어요
    private static void productFile() {
        try (InputStreamReader reader = new InputStreamReader(System.in)) {

            PrintWriter writer = new PrintWriter(System.out,true);
            System.out.println("텍스트를 입력 하세요(종료는 Ctrl + D)");
            System.out.println("-----------------------------");
            int charCode;
            // 계행 문자로 종료구문
            while ( (charCode = reader.read()) != -1 ){
                writer.print((char) charCode);
            }
            writer.flush(); // 버퍼에 남은 데이를 즉시 출력
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    // 직접 코드 작성해 보기
        // 파일에서 텍스트를 읽는 메서드를 직접 구현해 보세요

    public static void readFromFile(String fileName){

        // .. 파일의 내용을 문자 기반으로 읽어서 콘솔창에 출력
        try (FileReader reader = new FileReader(fileName)) {

            int readData;
            while ( (readData = reader.read()) != -1 ){
                System.out.print((char) readData);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

} // end of class

package io.ch17;

import java.io.*;

public class BufferedFileReader {

    public static void main(String[] args) {

        // FileReader는 기반 스트림 BufferedFileReader (보조스트림)로 감쌈


        try (BufferedReader bfr = new BufferedReader(new FileReader("data.txt"))) {

            String line;
            // Fileread은 한글자식 읽음
            // readLine() : 한줄 전체를 String으로 읽음
            // 반환값이 null 이면 파일끝
            while ( (line = bfr.readLine()) != null ){
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

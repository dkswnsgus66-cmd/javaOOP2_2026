package io.ch18;

import java.io.*;

public class FileCopy {


    public static void main(String[] args) {


        String sourceFilePath = "employees.zip";
        String destinationFilePath = "employees_copy.zip";

        // 소요시간 측정
        // 현재시각을 나노초(10억분의 1초) 단위로 변환 1970 ~
        long startTime = System.nanoTime();

        // 파일 복사기능

        try (
                FileInputStream fis = new FileInputStream(sourceFilePath);
                FileOutputStream fos = new FileOutputStream(destinationFilePath)

                // employees.zip 에서 1바이트씩 읽어서
                // emplotees_copy.zip 에서 1바이트 씩 쓰기
        ) {

            int read; // 바이트 단위로 읽음
            while ( (read = fis.read()) != -1) {

                fos.write(read);
            }
            System.out.println("파일 복사 완료");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 소요시간 계산
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        double seconds = duration / 1_000_000_000.0; // 나노 초 --> 초 변환

        System.out.println("나노초 값 : " + duration);
        System.out.println("초 값: " + seconds);


    }


}

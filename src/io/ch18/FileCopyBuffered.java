package io.ch18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopyBuffered {


    public static void main(String[] args) {


        String sourceFilePath = "employees.zip";
        String destinationFilePath = "employees_copy.zip";

        // 소요시간 측정
        // 현재시각을 나노초(10억분의 1초) 단위로 변환 1970 ~
        long startTime = System.nanoTime();

        // 파일 복사기능 - 빠른버전 (버퍼활용)


        try (
                FileInputStream fis = new FileInputStream(sourceFilePath);
                FileOutputStream fos = new FileOutputStream(destinationFilePath);
                BufferedInputStream bis = new BufferedInputStream(fis);
                BufferedOutputStream bos = new BufferedOutputStream(fos)
                // employees.zip 에서 1바이트씩 읽어서
                // emplotees_copy.zip 에서 1바이트 씩 쓰기
        ) {

            // 버퍼에 크기를 직접 지정해 주자
            // 1 바이트가 1000개 있으면 1KB - 정확히는 1024 바이트
            byte[] bytes = new byte[1024]; // 1KB씩 읽을 버퍼 배열 , 2KB


            int read; // 바이트 단위로 읽음
            while ( (read = bis.read(bytes)) != -1) { // 한번 데이터를 킬때 1024 바이트 만큼 담고 읽어라
                bos.write(bytes,0,read); // 바구니 길이부터 읽은만큼 써라 1번 반복될때마다
                //        바이트배열만큼  0부터 읽은만큼만 써라
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

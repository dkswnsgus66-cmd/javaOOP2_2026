package io.ch15_1;

import java.io.*;
import java.util.Scanner;

public class TypingRecord {

    public static void main(String[] args) throws Exception {
        boolean plag = true;
        Scanner sc = new Scanner(System.in); // System(운영체제).in(InputStream)
        while (plag) {
            System.out.println("===== 타자 연습 기록기====");
            System.out.println("1. 문장저장 ");
            System.out.println("2. 기록보기 ");
            System.out.println("3. 종료");
            System.out.print("선택 : ");
            String choice = sc.nextLine();
            if (choice.equals("1")) {

                saveRecord(sc);
                break;
            } else if (choice.equals("2")) {

                    printRecord();
                    break;
                // // 메모리 누수 방지
            } else if (choice.equals("3")) {
                plag = false;
            }else {
                System.out.println("숫자외 또는 표시되지 않은 숫자는 넣지마세요");
                break;
            }
        }


    } // end of main

    private static void printRecord() throws Exception {
        System.out.println("======= 저장된 기록 ======");
        try (FileInputStream fis = new FileInputStream("typing_record.txt")) {
            int data;
            while ( (data = fis.read()) != -1 ){
                System.out.print((char) data); // 71 ,72
            }
            fis.close();
        }
    }

    private static void saveRecord(Scanner sc) {
        System.out.print("연습할 문장을 입력하세요: " );
        String input = sc.nextLine();

        try (FileOutputStream fos = new FileOutputStream("typing_record.txt", true)) {

            fos.write(input.getBytes());
            // 줄바꿈 추가
            fos.write("\n".getBytes());
            System.out.println("저장 완료!");
            fos.close();

        } catch (Exception e) {// catch는 내가 직접 예외를 처리한것
            throw new RuntimeException(e);
        }

    }

} // end of class

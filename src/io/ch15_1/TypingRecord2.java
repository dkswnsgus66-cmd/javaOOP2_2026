package io.ch15_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * 현재 출력:               개선 후 출력:
 * The quick brown fox      1번 기록 : The quick brown fox
 * jumps over the lazy      2번 기록 : jumps over the lazy
 * dog                      3번 기록 : dog
 *                          총 3개의 기록이 있습니다.
 *                          \n 입력할때마다 기록 생성
 */
public class TypingRecord2 {




    public static void main(String[] args) throws Exception {

        TypingRecord2 ty = new TypingRecord2();


        Scanner sc = new Scanner(System.in);

        System.out.println("===타자 연습 기록기===");
        System.out.println("1. 문장 저장");
        System.out.println("2. 기록 보기");
        System.out.print("선택 : ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            saveRecord(sc);

        } else if (choice.equals("2")) {
            printRecord();
        }

        sc.close(); // 메모리 누수 방지1

    } // end of main

    private static void printRecord() throws Exception {
        System.out.println("\n===저장된 기록 ===");
        try (FileInputStream fin = new FileInputStream("typing_record.txt")) {
            int data;
            int lineNunmber = 1; // 현재 출력중인 줄번호
            StringBuilder sb = new StringBuilder();
            // StringBuilder 는 문자를 하나씩 이어 붙이는 가변 문자열 버퍼
            // String += "가" 를 반복하면 매번 새로운 객체가 생겨 느리므로 StringBulider를 사용한다.
            // 저장된 기록마다
            while ((data = fin.read()) != -1) {
                System.out.print((char) data);

                if((char) data == '\n'){
                    // 개행문자 (\n) 을 만났다 == 한줄이 끝났다

                    lineNunmber++;
                }else {
                    // 개행문자(\n) 아니라면 sb에 계속 이어 붙임
                    sb.append((char) data);
                }
                // 출력할때 만약 \n(개행문자) 이 들어온다면 카운트를 1씩 올리겠다.
            }// end of while
            System.out.println(sb.toString());
            System.out.println("총" + lineNunmber + "개의 기록이 있습니다.");
        }
    }

    private static void saveRecord(Scanner sc ) {

        System.out.print("연습한 문장을 입력하세요 : ");
        String input = sc.nextLine();

        try (FileOutputStream fos = new FileOutputStream("typing_record.txt", true)) {

            fos.write(input.getBytes());
            // 줄바꿈 추가
            fos.write("\n".getBytes());

            System.out.println("저장 완료!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
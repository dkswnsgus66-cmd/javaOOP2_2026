package io.ch15_1;

import java.io.*;
import java.util.Scanner;

public class ScoreTable {
//toString() 메소드는 객체가 가지고 있는 정보(상태)를 문자열 형태로 반환하는 Object 클래스의 기본 메소드입니다.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===시험 점수 저장소===");
        System.out.println("1. 점수 저장");
        System.out.println("2. 결과분석");
        String choice = sc.nextLine();
        if(choice.equals("1")){
            saveScore(sc);
        } else if (choice.equals("2")) {
            printTotalScore();
        }
    }


    private static void printTotalScore() {
        System.out.println("점수 분석 총점/평균");
        try (FileInputStream fis = new FileInputStream("scores.txt")) {

            // 파일 전체를 문자열로 읽기
            StringBuffer sb = new StringBuffer(); // 기존 문자를 추가하기 위해 선언함
            int data;
            while ((data = fis.read()) != -1) {
                sb.append((char) data);//자바의 append()는 StringBuilder나 StringBuffer 클래스에서 문자열의 기존 내용을 변경하지 않고 끝에 새로운 데이터를 추가하여 문자열을 효율적으로 결합하는 메서드
            }
            // 공백 기준으로 문자열을 자르는 split.. --> 배열 char 반환
            String[] parts = sb.toString().trim().split(" "); // trim(" "); " " 제거  split 들어간 문자만큼 배열생성 String 메서드이다

            int total = 0;

            for (String part : parts) {
                System.out.println("점수: " + part);
                // 문자열을 정수값으로 형변환 하는 방법
                total += Integer.parseInt(part);
            }
            System.out.println("총점: " + total);
            System.out.println("평균: " +(double) total / parts.length);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveScore(Scanner sc) {
        System.out.println("학생수를 입력하세요: ");
        // 문자열값을 int 형으로 변환
        //
//        int count = Integer.parseInt(sc.nextLine());
        try {
            // 예상값 ---> 3
            int count = Integer.parseInt(sc.nextLine());
            StringBuffer sb = new StringBuffer(); // 불변객체 하나의 객체에 계속 문자를 더할수 있다.
            for (int i = 1; i <= count; i++) {
                System.out.println(i + "번째 학생점수 ");
                // sb 에 계속 append() 10 + 20 + 30 +....
                // 하나의 문저열로 만들려고
                // 10 공백 20공백 30공백
                String score = sc.nextLine();
                sb.append(score);
                sb.append(" ");
                // toString 저장된 문자열 모두 표시
            }
            try (FileOutputStream fos = new FileOutputStream("scores.txt")) {
                fos.write(sb.toString().getBytes());
                System.out.println("저장완료");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}

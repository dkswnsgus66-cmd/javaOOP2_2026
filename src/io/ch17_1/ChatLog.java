package io.ch17_1;

import java.io.*;
import java.util.Scanner;

public class ChatLog {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== 채팅 로그 저장소 =====");
        System.out.println("1. 대화저장");
        System.out.println("2. 전체로그보기");
        System.out.println("3. 단어검색");
        System.out.print("4. 선택 : ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            saveChat();
        } else if (choice.equals("2")) {
            printAll();
        } else if (choice.equals("3")) {
            System.out.print("검색할 단어 : ");
            String keyword = sc.nextLine();
            searchChat(keyword);
        } else {
            System.out.println("똑바로 입력해라");
        }
        sc.close(); // 스캐너 닫기 스캐너는 자동닫기 기능이 없다

    } // end of main

    private static void searchChat(String keyword) {

        // keyword <== (안녕)

        System.out.println("\n===" + keyword + "검색 결과");
        try (BufferedReader br = new BufferedReader(new FileReader("chat_log.txt"))) {

            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                // 만약 keyword 단어가 포함되어 있다면....
                if (line.contains(keyword)) {

                    count++;

                }
                if(count == 0){
                    System.out.println(keyword + "가 포함된 대화가 없습니다.");
                }else {
                    System.out.println("\n총 " + count + "개의 대화 내용이 발견 되었습니다.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void printAll() {

        System.out.println("\n=== 전체 채팅 로그 ===");
//        FileReader fr = ne
        try (BufferedReader br = new BufferedReader(new FileReader("chat_log.txt"))) {

            String line;
            int num = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(num + " | " + line);
                num++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static void saveChat() {

        System.out.println("이름과 메시지를 입력하세요. (종료. 빈 줄 입력)");
        System.out.println("형식: 이름 > 메시지    예)홍길동>안녕하세요");

        // System.in(바이트) 기반으로 읽는다 -> InputStreamReader (문자 변환) --> BufferedReader (버퍼 + readLine() )

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new FileWriter("chat_log.txt", true))

        ) {

            String line;
            while ((line = br.readLine()) != null) {

                bw.write(line);
                bw.newLine();
                bw.flush(); // 입력할때마다 파일에 쓰기
                System.out.println("채팅 로그가 저장 됐습니다.");

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // FileWriter("파일명")

    } // end of saveChat();

} // end of class

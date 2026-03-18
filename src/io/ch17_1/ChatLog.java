package io.ch17_1;

import java.io.*;
import java.util.Scanner;

public class ChatLog {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean plag = true;
        while (plag) {
            System.out.println("===== 채팅 로그 저장소 =====");
            System.out.println("1. 대화저장");
            System.out.println("2. 전체로그보기");
            System.out.println("3. 단어검색");
            System.out.println("4. 이름검색");
            System.out.println("5. 종료");
            System.out.print("6. 선택 : ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                saveChat();
            } else if (choice.equals("2")) {
                printAll();
            } else if (choice.equals("3")) {
                System.out.print("검색할 단어 : ");
                String keyword = sc.nextLine();
                searchChat(keyword);
            } else if (choice.equals("4")) {
                System.out.print("이름입력: ");
                String name = sc.nextLine();
                searchByName2(name);
            } else if (choice.equals("5")) {
                plag = false;
            }
        } // end of while
        sc.close(); // 스캐너 닫기 스캐너는 자동닫기 기능이 없다
    } // end of main

    private static void searchByName2(String name){

        System.out.println("\n===" + name + "검색 결과");
        try (BufferedReader br = new BufferedReader(new FileReader("chat_log.txt"))) {

            String line;
            int count = 0;
            while ( (line = br.readLine()) != null ){
                // 만약 keyword 단어 포함 되어 있다면
                // "이름>" 으로 시작하는 줄만 필터링
                if(line.startsWith(name + ">")){
                    System.out.println(line);
                    count++;
                }
            }
            if(count >= 1){
                System.out.println("\n총 "+count + " 개 의 대화가 출력 되었습니다.");
            }else {
                System.out.println( name + " 의 이름의 가진 사람의 대화가 없습니다.");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    private static void searchByName(String name) {

        System.out.println("\n===" + name + "검색 결과");
        try (BufferedReader br = new BufferedReader(new FileReader("chat_log.txt"))) {

            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                // 만약 keyword 단어가 포함되어 있다면....
                if (line.contains(name)) {
                    System.out.println(line);
                    count++;
                }
            }
            if(count == 0){
                System.out.println(name + "의 이름을 가진사람의 대화가 없습니다.");
            }else {
                System.out.println("\n총 " + count + "개의 대화 내용이 발견 되었습니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void searchChat(String keyword) {

        // keyword <== (안녕)

        System.out.println("\n===" + keyword + "검색 결과");
        try (BufferedReader br = new BufferedReader(new FileReader("chat_log.txt"))) {

            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                // 만약 keyword 단어가 포함되어 있다면....
                if (line.contains(keyword)) {
                    System.out.println(line);
                    count++;
                }

            }
            if(count == 0){
                System.out.println(keyword + "가 포함된 대화가 없습니다.");
            }else {
                System.out.println("\n총 " + count + "개의 대화 내용이 발견 되었습니다.");
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

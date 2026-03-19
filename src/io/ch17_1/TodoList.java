package io.ch17_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoList {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean plag = true;

        while (plag) {
            System.out.println("=== Todo리스트 ===");
            System.out.println("1. 할일 추가");
            System.out.println("2. 전체 목록보기");
            System.out.println("3. 완료처리");
            System.out.println("4. 미완료 목록만 보기");
            System.out.println("5. 완료 취소"); // [V] 자바 --> [ ] 자바
            System.out.println("6. 완료 만 표시");
            System.out.println("7 종료");
            System.out.print("선택 : ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addTask(sc);
            } else if (choice.equals("2")) {
                //읽기
                readTask();
            } else if (choice.equals("3")) {
                // 완료처리
                checkList(sc);
            } else if (choice.equals("4")) {
                // 미완료
                failTodo();
            } else if (choice.equals("5")) {
                // 완료취소
                failCheakList(sc);
            } else if (choice.equals("6")) {
                // 완료체크
                cheakCompleteTodo();
            } else if (choice.equals("7")) {
                plag = false;
            }
        }
        sc.close();

    } // end of main

    private static void cheakCompleteTodo() {

        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("[V]")) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void failCheakList(Scanner sc) {

        ArrayList<String> tasks = new ArrayList<>();
        String line;
        int countLine = 1;
        int targetNumber;
        // 체크리스트 번호와 함께 출력
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            while ((line = br.readLine()) != null) {
                System.out.println(countLine + ". " + line); // notion에 적힌 요구 조건 수행
                tasks.add(line);
                countLine++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.print("완료한 항목의 번호를 입력하세요 : ");
        targetNumber = sc.nextInt();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt"))) {

            int target = targetNumber - 1; // 입력 받은 값 정수 변경
            String targetString = tasks.get(target);
            tasks.set(target, "[ ] " + targetString.substring(4)); // 체크 기능

            for (int i = 0; i < tasks.size(); i++) {

                if ((target) == i) { // 입력한
                    bw.write(tasks.get(target));
                } else {
                    bw.write(tasks.get(i));
                }
                if (i != (tasks.size() - 1)) { // 마지막 줄에 \n을 넣지 않음
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private static void completeTodo(Scanner sc) {

//        System.out.println("완료한 Todo를 입력해 주세요");
//        String todo = sc.nextLine();
        String text;
        List<String> lines = new ArrayList<>();

        try (
                BufferedReader br = new BufferedReader(new FileReader("todo.txt"));

        ) {

            String line;
            int count = 0; // 확인용

            while ((line = br.readLine()) != null) {

                if (line.contains("[ ]")) {

                    // [ ] 포함하는 것에 V를 추가해주는 기능구현
                    // 배열에 [V] 추가해서 반환
                    // 메서드가 한번 동작할때마다 [V] 가 추가되어 출력
                    lines.add(line); // [ ] 이 읽힐때 마다 라인 단위로 배열에 넣어라 어차피 읽는 동작이 라인 단위라 [ ] 읽을때마다 해당라인을 배열에 넣음
                    // 라인즈에 들어감
                }
            } // end of while

            /**
             * List<String> lines = new ArrayList<>();
             * lines.add("HelloWorld");
             *
             * // 0번 인덱스 문자열 수정
             * String str = lines.get(0);
             *
             * // "HelloWorld" → "Hello Java" 로 바꾸기
             * str = str.substring(0, 5) + " Java";
             *
             * // 다시 넣기
             * lines.set(0, str);
             *
             * System.out.println(lines);
             */
            // 지우고 새로 쓰기
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt", false))) {
                for (int i = 0; i < lines.size(); i++) {
                    // 배열에 있는 텍스트 새로 쓰기
                    text = lines.get(i).substring(0, 3) + "[V]";
                    bw.write(text);
                    bw.newLine();
                    bw.flush();
                }
                lines.removeAll(lines);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkList(Scanner sc) {

        ArrayList<String> tasks = new ArrayList<>();
        String line;
        int countLine = 1;
        int targetNumber;
        // 체크리스트 번호와 함께 출력
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            while ((line = br.readLine()) != null) {
                System.out.println(countLine + ". " + line); // notion에 적힌 요구 조건 수행
                tasks.add(line);
                countLine++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("완료한 항목의 번호를 입력하세요 : ");
        targetNumber = sc.nextInt();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt"))) {

            int target = targetNumber - 1; // 입력 받은 값 정수 변경

            String targetString = tasks.get(target); // 스트링을 선언해서 sub스트링을 쓴다

            tasks.set(target, "[v] " + targetString.substring(3)); // 체크 기능

            for (int i = 0; i < tasks.size(); i++) {

                // target 은 해당 리스트의 인덱스 넘버
//                if ((target) == i) {
//                    bw.write(tasks.get(target)); // 체크된 해당인덱스를 넣음
//                } else {
//                    bw.write(tasks.get(i)); // 체크 안될걸 써냄
//                }
//                if (i != (tasks.size() - 1)) { // 마지막 줄에 \n을 넣지 않음
//                    bw.newLine(); //
//                }
                bw.write(tasks.get(i));
                bw.newLine();
                bw.flush();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void failTodo() {

        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("[ ]")) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static void readTask() {

        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {

                System.out.println(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void addTask(Scanner sc) {
        System.out.print("추가할 일을 입력 하세요: ");

        String text = sc.nextLine();

        // "[ ] 할일 내용" 형식으로 지정
        // [ ] 미완료 상태

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt", true))) {

            // 자바 복습
            bw.write("[ ]" + text);
            bw.newLine();
            bw.flush();
            System.out.println("추가 됐습니다." + text);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    } // end of addTask
} // end of class


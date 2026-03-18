package io.ch15_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class SecretNote {

    public static void main(String[] args) {

        // 1. 키보드에서 값을 받아야 한다.
        // 2. 파일에다가 키보드에서 입력받은 값을 저장 시켜야 한다
        // 2.1 단 -----> 내용을 살짝 변경해서 저장해야 한다( 아스키 코드(65) (66) (67) 값에 +3)

        //
        Scanner sc = new Scanner(System.in); //System.in 일종의 InputStream이다
        //saveMemo(sc);
        // 3. 파일에서 데이터를 한 바이트씩 읽어야 한다.
        // 3.1 한 바이트를 읽을때 마다 -3 씩 아스키 코드값 기준 -3 씩 해서 암호를 해석한다.
//        readData();
        System.out.println("=== 비밀 메모장 ===");
        System.out.println("1. 메모 암호화 저장");
        System.out.println("2. 메모 복호화 읽기");
        System.out.println("선택: ");
        String chioce = sc.nextLine();

        if(chioce.equals("1")){
            saveMemo(sc);
        } else if (chioce.equals("2")) {
            readData(sc);
        }


    }

    private static void readData(Scanner sc) {
        System.out.println("\n=== 복호화된 메모 ===");
        System.out.print("복호화할 키를 입력하세요");
        String key = sc.nextLine();
        try (FileInputStream fis = new FileInputStream("secret.txt")) {
            // 스스로 도전 (한 바이트씩 갯수만큼 읽어서 콘솔에 출력하기 - 암호도 풀어보기
            int readData; // = fis.read(); // 파일 읽는 메서드 ( H ) 여기서 한번 동작해버림

            while ((readData = fis.read()) != -1) {// 그래서 여기서 초기값이 e 가 나옴
                System.out.print((char) (readData - Integer.parseInt(key)));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void saveMemo(Scanner sc) {
        System.out.println("====비밀 메모장====");
        System.out.print("저장할 메모를 입력하세요: ");
        String input = sc.nextLine();

        System.out.print("암호화할 키를 입력하세요: ");
        String key = sc.nextLine();


        try (FileOutputStream fos = new FileOutputStream("secret.txt", false)) {
            byte[] original = input.getBytes(); // [65] [67] [68].....  new byte[10]
            byte[] encryted = new byte[original.length];// [65] [67] [68].....
            // 배열의 크기만 선언한 상태

            for (int i = 0; i < original.length; i++) {
                encryted[i] = (byte) (original[i] + Integer.parseInt(key));
            }
            // 데이터를 암호화한후 파일에 쓰기
            fos.write(encryted);
//            fos.flush(); -> fos.close() 내부적으로 동작


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}// end of class

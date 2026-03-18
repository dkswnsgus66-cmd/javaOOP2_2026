package io.my_;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Record {

    public static void main(String[] args) {

        boolean plag = true;

        Scanner sc = new Scanner(System.in);
        while (plag) {
            System.out.println("선택. 1. 입력 2. 출력 3. 종료");
            String select = sc.nextLine();
            if (select.equals("1")) {
                recordData(sc);
            } else if (select.equals("2")) {
                readData();
            } else if (select.equals("3")) {
                plag = false;
            } else {
                System.out.println("똑바로 입력해라.");
            }
        }//end of while
    }// end of main

    private static void readData() {
        try (FileInputStream fis = new FileInputStream("Record.txt")) {

            int readData;
            while ((readData = fis.read()) != -1){
                System.out.print((char)(readData -3));
            }
            System.out.println("");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void recordData(Scanner sc) {
        try (FileOutputStream fos = new FileOutputStream("Record.txt", false)) {

            int secretNumber;
            StringBuilder sb = new StringBuilder();
            System.out.println("글자 입력: ");

            // String 과 StringBuilder 차이점

            sb.append(sc.nextLine());

            // 암호화 해보기
            byte[] origin = sb.toString().getBytes(); // 문자열만큼 바이트 배열 크기 지정하고 거기에 데이터가 담김

            for(int i =0 ; i < origin.length; i++){
                origin[i] = (byte)(origin[i] + 3);// 들어가는 배열은 byte 타입인데 왜 암호화 할때 int 로 변하냐 -> 자바에서 +3을 할때 int로 승격시켜서 계산해주기 때문
                // 그래서 따로 형변환을 해줘야한다
            }
            fos.write(origin);
        }catch (Exception e){
           throw new RuntimeException(e);
        }
    }

}

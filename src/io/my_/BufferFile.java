package io.my_;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class BufferFile {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("생산할 파일이름 입력: ");
        StringBuilder sb = new StringBuilder();
        sb = sb.append(sc.nextLine());

        String sign = sb.toString();

        fileCreate(sign);
        fileRead(sign);

    }// end of main


    // 파일 읽는 메서드
    public static void fileRead(String fileName){

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {


            String readLine; // String 반환

            while ( (readLine = br.readLine()) != null ){

                System.out.println(readLine);

            }

        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    // 문자기반 텍스트 파일 만들기 이미지나 동영상은 안됨
    public static void fileCreate(String fileName){

        try ( FileWriter fw = new FileWriter(fileName,true) ) {
            Scanner sc  = new Scanner(System.in);
            StringBuilder data = new StringBuilder();

                System.out.println("파일 내용기입: ");

                data.append(sc.nextLine());
                String data1 = data.toString();

                fw.write(data1);

        }catch (Exception e){
            throw new RuntimeException();
        }


    }

}

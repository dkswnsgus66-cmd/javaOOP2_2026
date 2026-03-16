package io.ch15;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFileOutputSystem {

    public static void main(String[] args) {

        String data = "Hello Java, FileOutputSystem abc abc 안녕 반가워";
            //      [72]
            // 파일이 없으면 새로 생성, 있으면 덮어쓰기
            //FileOutputStream fos = new FileOutputStream("output.txt", false)

            // 기존파일에 내용이 있다면 true뒤에 이어 쓰기, false -> 덮어쓰기
            // FileOutputStream fos = new FileOutputStream("output.txt", true) --> Append모드

            //
        try (FileOutputStream fos = new FileOutputStream("output.txt", false)) {
            // 문자열은 FileOutputStream 으로 직접 쓸수없다.
            // 문자열을 -> byte 배열로 변환해서 넣어주어야 합니다.
            // 문자열을 byte 배열로 변환해서 임시 변수에 담아 둠
            //byte[] bytes = data.getBytes(); // 바이트로 변환 Stream은 바이트 기반이기 때문에

            fos.write(data.getBytes());
            // fos.flush();
            System.out.println("파일 출력 완료: output.txt");

        }catch (IOException e){
            throw new RuntimeException();
        }catch (Exception e){
            throw new RuntimeException();
        }
        // 똑같이 바이트로 만들었는데 한글이 안꺠진 이유
        // 참고 : output.txt 를 에디터로 열면 텍스트가 보입니다.
        // 에디터가 바이트 데이터를 문자로 해석해서 보여주기 때문에
        // FileOutputSystem 으로 바이트를 썼지만, 보여주는 방식은 에디터가 결정합니다.  에디터는 UTF-8로 인코딩 했기에 한글이 안깨짐


    }// end of main
} // end of class

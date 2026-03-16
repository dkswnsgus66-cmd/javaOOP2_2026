package io.ch14;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileInputStream3 {

    public static void main(String[] args) {


        try (FileInputStream in = new FileInputStream("a_txt")) {// a_txt 파일에 연결된 스트림 (통로) 생성
            int readData; // 객체의 메서드를 통해 글자 1개씩 readData에 넣기 in.read가 1 번 동작하면 L 이 반환된다
            while ( (readData = in.read()) != -1){ // 글자 한개씩 읽고 그 글자가 readData에 반환되고 -1(읽을 데이터가 없다) 구문이 아닐때 true 계속 동작
                // while 문 조건을 보면 in.read가 -1 이 될때까지 readData에 문자를 넣고 다음 문자가 덮어씌어지는게 반복되는 구조이다
                //readData = in.read() 를 안쓰고 readData만 쓰면 L이 무한히 반복됨
                System.out.print((char) readData);
                // in.read(); 는 들어온 데이터를 int 형태로 반환함 왜냐면 바이트 형태로 데이터를 읽기 때문에
            }
            System.out.println(readData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }// end of main
} // end of class

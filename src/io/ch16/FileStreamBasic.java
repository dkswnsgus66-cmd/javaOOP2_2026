package io.ch16;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

public class FileStreamBasic {

    public static void main(String[] args) {
//        writeToFile("basic_output.txt");
        System.out.println("-------------------");
        readFromFile("basic_output.txt");
    } // end of main


    // 파일에 텍스트를 쓰는 메서드 (문자 기반 스트림을 사용할 예정)
    public static void writeToFile(String fileName) {
        /**
         * FileWriter 는 문자기반 출력 스트림입니다.
         * FileOutputStream과 fos.write(byte[]) 달리 write(String)이 가능합니다.
         * getBytes() 변환이 필요 없습니다.
         */
        // append모드를 설정하지 않으면 기본값이 false 이다.
        try ( FileWriter writer = new FileWriter(fileName,false) ) {

            String text = "자바문자 기반 스트림 예제\n";

            writer.write(text);
            writer.write("추가 문자열을 기록 합니다.");
//            writer.flush();
            System.out.println("파일에 텍스트를 잘 기록 하였습니다.");

        } catch (Exception e) {
            System.err.println("파일쓰기중 오류발생 : " + e.getMessage());
        }
    }

    public static void readFromFile(String fileName){
        /**
         * FileReader는 문자 기반 입력 스트림입니다
         * read() 는 한 문자씩 읽어 유니코드 값(정수)으로 반환합니다.
         * FileInputStream과 사용법은 같지만 한글이 깨지지 않습니다.
         */
        try (Reader reader = new FileReader(fileName)) {

            int charCode;
            while ( (charCode = reader.read() ) != -1){
                System.out.print((char) charCode);
            }

        }catch (Exception e){
            System.err.println("해당하는 파일이 없습니다" + e.getMessage());
        }
    }

}// end of class

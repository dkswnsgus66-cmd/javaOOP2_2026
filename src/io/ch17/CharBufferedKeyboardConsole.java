package io.ch17;

import java.io.*;

public class CharBufferedKeyboardConsole {


    public static void main(String[] args) throws IOException {

        /**
         * 스트림 체인구조
         *
         * [키보드]
         *      System.in(InputStream)
         * InputStreamReader(브릿지)
         * Buffered/Read (버퍼 + readLine() 추가)
         *
         * [프로그램] ===> 콘솔
         * BufferedWrite() (버퍼 + newLine() 추가)
         * PrintWriter
         * System.out(PrintWriter) - 콘솔창에 출력
         *
         */

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        PrintWriter pw = new PrintWriter(System.out);
        BufferedWriter bw = new BufferedWriter(pw);

        // 콘솔창에 종료 명령은 (Ctrl + D)
        System.out.println("텍스트를 입력하세요 (종료: 빈줄입력)");

        String line;
        while ((line = br.readLine()) != null) {
            bw.write("받은값 출력: " + line);
            bw.newLine(); // 운영체제에 맞는 줄바꿈 자동 삽입 운영체제에 맞게 \n or \r 자동으로 넣어준다 가끔가다 윈도우에서 \n이 안먹을때가 있다 그때 newLine쓰면 좋다
            // \n <-- 리눅스, Mac
            // \r\n <---- 윈도우
            bw.flush(); // 버퍼에 남은 데이터 즉시 출력
        }


    } // end of main

}// end of class

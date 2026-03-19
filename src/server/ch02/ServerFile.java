package server.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5001)) {

            // serverSocket.accept(); // 클라이언트 대기 (블로킹 메서드)
            System.out.println("서버시작 포트번호- 5001번");
            Socket clientSocket = serverSocket.accept(); // 서버가 클라이언트와 연결된 상태
            System.out.println("클라이언트 연결됨");

            // 간단한 흐름 약속 ( 연결 후 클라이언트가 먼저 서버측으로 메세지를 보낼 예정)
            //clientSocket.getInputStream() 클라이언트의 소켓을 읽음
            //new InputStreamReader(clientSocket.getInputStream() 글자를 읽음

            // 읽기 스트림 준비 (클라이언트 --> 서버)
            // 버퍼로 성능 향상
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));



            // 쓰기 스트림 준비 (서버 ---> 클라이언트)
            // 소켓은 Prinwriter로 쓰는게 좋다
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // 기능 호출
            // 1. 먼저 클라이언트에서 보낸 메세지 받기
            String message = reader.readLine();
            System.out.println("클라이언트 측 메세지: " + message);

            // 2. 서버가 클라이언트 에게 응답 전송
            writer.println("안녕 나는 서버야 반가워 !");
            writer.flush();
        } catch (IOException e) {
            System.err.println("오류발생: " + e.getMessage());
        }


    }// end of main

} // end of class

package server.ch01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

    public static void main(String[] args) {

        // 소켓 통신을 하기위한 서버측 테스트 코드 1
        // (내 IP 주소 당연히 알고있음) 포트 번호를 열고
        // 포트번호를 열고 클라이언트에 연결 요청을 기다리는 서버소켓
        // IP: 192.168.4.11
        try (ServerSocket serverSocket = new ServerSocket(5000)) { // 5000번 포트번호를 서버 소켓으로 지정

            // accept() - 클라이언트가 연결할때까지 이 줄에서 멈춤(블로킹)
            Socket clientSocket = serverSocket.accept(); // 코드가 아래로 안내려감 (블로킹) FileWriter의 newLine전까지 밑으로 안내려가는것 처럼
            // return new ServerSocket 한것과 같다 accept()가 실행이되면
            System.out.println("클라이언트가 연결되었습니다.");

            // 2. 소켓 객체가 생성이 되면 accept 이 소켓이 클라이언트 소켓과 연결되어 있는 소켓이다

            // IO 단원에서 배운 체인 그래로
            InputStream input = clientSocket.getInputStream();// 소켓안의 InputStream 을 그대로 받게됨
            // 문자를 서버에 보낼거라서 Reader 씀
            BufferedReader reader = new BufferedReader(new InputStreamReader(input)); // input 은 InputStream이라 바이트를 인트 단위로 향상되서 넣어진다 그걸 문자로 바꾸기 위해 Reader로 감쌋다
            // 클라이언트가 보낸 한 줄을 읽음
            String message = reader.readLine();
            // 내서버측 콘솔창에 출력
            System.out.println("클라이언트 메세지: " + message);

            clientSocket.close();

        } catch (IOException e) {
            System.err.println("오류발생 : 포트 5000 이미 사용중이거나 연결에 실패 했습니다.");
        }


    }
}

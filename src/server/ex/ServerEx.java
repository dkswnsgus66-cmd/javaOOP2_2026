package server.ex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEx {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            System.out.println("클라이 언트 기다리는 중...");
            Socket clientsocket = serverSocket.accept();
            System.out.println("클라이언트와 연결 완료");
            // 클라이언트 메세지 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            // 서버가 클라이언트 에게 메세지 보내기
            PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
            // 키보드로 클라이 언트에게 메세지 보내기
            BufferedReader inputKeyboard = new BufferedReader(new InputStreamReader(System.in));


            // 먼저 메세지 먼저 읽고 쓰기 가능
            String text;



            // 클라이언트 로 부터 받은 메세지 읽기
            while ((text = in.readLine()) != null) {

                System.out.println("클라이언트로 부터 받은 메세지 : " + text);
                // 처음 메세지가 전달되었고 그 다음부터 메세지가 전달이 안된다.
                // 클라이언트는 전달이 잘 된다.

                String outText = inputKeyboard.readLine();

                // 클라이언트에 보낼 메세지 쓰기
                // 키보드로 입력 받은 outText 클라이언트에게 전달
                out.println("서버에 보낸 메세지: " + outText);
                if(outText.equalsIgnoreCase("exit")){
                    break;
                }
            }


        } catch (Exception e) {
            System.err.println("클라이언트와 연결이 끊겼습니다.");

        }

    }

}

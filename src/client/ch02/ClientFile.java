package client.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientFile {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5001)) {

            // Socket socket = new Socket("localhost", 5001) 생성되는 순간
            // 서버측과 연결된 상태

            // 쓰는 스트림 준비 (클라이 언트 --> 서버)
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

            // 읽기 스트림 필요 (서버 ---> 클라이언트)

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 읽는 기능

            // 기능 실행
            // 서버에 보내는 송신기능
            writer.println("서버야 안녕!!!~~");
            // 서버에서 보낸 응답 수신
            String restance = reader.readLine();
            System.out.println("서버 측 응답: " + restance);

        } catch (UnknownHostException e) {
            System.err.println("서버 측을 알수없습니다." + e.getMessage());
        } catch (IOException e) {
            System.err.println("서측에 연결할수 없습니다.");
        }


    }

}

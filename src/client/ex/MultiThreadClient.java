package client.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThreadClient {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5000)) {
            // 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 쓰기
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            // 키보드 입력
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));


        }catch (IOException e){

            throw new RuntimeException(e);
        }

    }

}

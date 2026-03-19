package client.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {

        try (Socket connectServer = new Socket("localhost", 5002)) {

            // 서버로 부터 받은 걸 읽는 기능
            BufferedReader reader = new BufferedReader(new InputStreamReader(connectServer.getInputStream()));
            // 서버에 메세지를 보내는 기능
            PrintWriter writer = new PrintWriter(connectServer.getOutputStream(),true);


            writer.println("난 클라이트다 반갑다."); // 여기도 마찬가지로 서버에 먼저 메세지를 보내야 서버가 응답을 보내 읽는거를 수행시킬수 있다. 아까는 서버랑 클라이언트가 서로 읽기만 해서 다음 동작을 수행못함
            String message = reader.readLine(); // 서버에서 메세지를 줄때까지 코드 실행을 못함

            System.out.println(message);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

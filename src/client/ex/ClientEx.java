package client.ex;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientEx {

    public static void main(String[] args) {

        try (Socket client = new Socket("localhost", 5000)) {

            // 서버로 부터 받은 메세지 읽기
            BufferedReader serverMessage = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // 서버에 보낼 메세지 보내는기능
            PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
            // 메세지 키보드로 쓰는 기능
            BufferedReader writeKetboard = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                //메세지 입력
                System.out.println("서버에 보낼 메세지를 입력해 주세요: ");
                String text = writeKetboard.readLine(); // 키보드 입력을 문장단위로 받음

                writer.println(text);

                // 받은 메세지 읽기
                String serverText = serverMessage.readLine();
                // 읽은 메세지 출력
                System.out.println("서버로 부터 받은 메세지: " + serverText);
                if(text.equalsIgnoreCase("exit")){
                    break;
                }
            }




        } catch (UnknownHostException e) {
            System.out.println("서버 주소 모른다");
        } catch (IOException e) {
            System.out.println("서버 연결이 끊김");
        }

    }

}

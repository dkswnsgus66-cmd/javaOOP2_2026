package client.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5000)) {

            // 소켓에서 연결할 , 출력 스트림 2개가 필요하고
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //클라이언트에서 키보드에서 값을 입력 받을 스트림이 필요하다
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while (true) {
                System.out.println("클라이언트 입력 > ");
                String input = keyboardReader.readLine(); //블로킹 상태
                writer.println(input);
                if ( "exit".equalsIgnoreCase(input)) {
                    break;
                }
                //서버측에서 보낸 메세지를 받아서 클라이언트 콘솔창에 출력



                String response = reader.readLine(); // readLine(); \n 기준으로 문장을 넣는다
                // while 이기에 계속 새롭게 sout 출력을 \n 단위로 한다

                if("exit".equalsIgnoreCase(response)) {
                    System.out.println("서버측에서 대화 종료 요청");
                    break;
                }
                System.out.println("서버 응답 : " + response);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } // end of main
} // end of class
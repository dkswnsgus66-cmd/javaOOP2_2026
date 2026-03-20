package server.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {


    public static void main(String[] args) throws InterruptedException {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("클라이언트 기다리는중...");
            Socket socket = serverSocket.accept();
            System.out.println("클라이 언트와 연결성공");

            // 읽기 스트림
            BufferedReader clientMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 보내기 스트림
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            // 키보드 쓰기 스트림
            // System.in은 키보드를 통해 보내는 스트림이다.
            BufferedReader keyboardRead = new BufferedReader(new InputStreamReader(System.in));

            // 스레드 만들어서 읽기 쓰기 동시에 해봐라
           Thread readThread =  new Thread(new Runnable() {
               @Override
               public void run() {
                   String readText;
                   try {
                       while ( (readText = clientMessage.readLine()) != null){
                           System.out.println("클라이 언트로 부터 받은 메세지: " + readText);
                       }
                   }catch (IOException e){
                       throw new RuntimeException(e);
                   }
               }
           });


           Thread writeThread = new Thread(new Runnable() {
               @Override
               public void run() {

                   String writeTextKeyboard;
                   System.out.print("클라이 언트에 보낼 메세지 입력: ");
                   try {
                       while ( (writeTextKeyboard = keyboardRead.readLine()) != null ){
                           writer.println(writeTextKeyboard);
                           System.out.println("클라이 언트에 메세지를 보냈습니다.");
                       }
                   }catch (IOException e){
                       throw new RuntimeException(e);
                   }
               }
           });

            readThread.start();
            writeThread.start();
            readThread.join();
            writeThread.join();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

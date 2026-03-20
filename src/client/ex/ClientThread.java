package client.ex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5000)) {

            // 읽기
            BufferedReader readServerMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 키보드로 쓰기
            BufferedReader writeKeyboard = new BufferedReader(new InputStreamReader(System.in));
            // 서버에 보내기
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

            // 서버 메세지 읽는 스레드
            Thread readThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String text;
                    try {
                        while ( (text = readServerMessage.readLine()) != null ){
                            System.out.println("서버로 부터 받은 메세지: " + text);
                        }
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            });

            Thread writeThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    String text;
                    System.out.print("서버에 보낼 메세지 입력: ");
                    try {
                        while ( (text = writeKeyboard.readLine() ) != null ){

                            writer.println(text);
                            System.out.println("서버에 메세지를 보냈습니다.");
                        }

                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            });

            // 메인스레드 대기 시키는 기능 그래서 start 사이에 끼워 넣어서 메인스레드가 write 스레드 동작을 시켜야 하는데 대기 시켜서 동작안됨
            readThread.start();
            writeThread.start();
            readThread.join();
            writeThread.join();

        }catch (Exception e){
            throw new RuntimeException(e);
        }



    }

}

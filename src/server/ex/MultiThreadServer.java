package server.ex;

import java.awt.im.InputMethodRequests;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

    public static void main(String[] args) {

        try (ServerSocket socketServer = new ServerSocket(5000)) {

           Socket clientConnect = socketServer.accept();

            // 읽기 기능
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientConnect.getInputStream()));
            // 쓰기 기능
            PrintWriter writer = new PrintWriter(clientConnect.getOutputStream(),true);
            // 키보드 입력
            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

            // 쓰레드 생성해서 클라이언트가 보낸 메세지 읽기기능 구현
            Thread readThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String line;
                    try {
                        while( (line = reader.readLine()) != null ){
                            System.out.println("클라이언트가 보낸 메세지: " + line);
                        }
                    }catch (IOException e){
                        System.err.println("메세지를 읽는중 연결이 끊겼습니다." + e);
                    }
                }
            });


            // 쓰레드 생성해서 클라이언트에게 보낼 메세지 쓰기
            Thread writeThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        String serverMessage = keyboardInput.readLine();
                        while (serverMessage != null){
                            writer.println(serverMessage); // 키보드 입력 받으니 넣는거 구분이 되어야 한다
                            if("exit".equalsIgnoreCase(serverMessage)){
                                System.out.println("서버에서 종료 했습니다.");
                                break;
                            }
                        }
                    }catch (IOException e) {
                        System.err.println("메세지 전송중 연결이 끊겼습니다." + e);
                    }
                }
            });


            readThread.start();
            readThread.join();
            writeThread.start();
            writeThread.join();


        }catch (Exception e){
            throw new RuntimeException(e);
        }




    }

}

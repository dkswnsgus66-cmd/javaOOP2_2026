package client.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("채팅 이름을 입력하세요: ");
        String name = sc.nextLine();

        try (Socket socket = new Socket("192.168.4.101", 5000)) {

            System.out.println(name + "님, 채팅방 입장했음 (종료: exit)");
            // 강사님 서버주소
            // 소켓에서 연결할 , 출력 스트림 2개가 필요하고
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //클라이언트에서 키보드에서 값을 입력 받을 스트림이 필요하다
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));


            // 읽기 쓰레드
            Thread readThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String serverMessage;
                        while ((serverMessage = reader.readLine()) != null) {

                            System.out.println("서버: " + serverMessage);
                        }

                    } catch (IOException e) {
                        System.out.println("서버측과 연결이 끊겼습니다");
                    }
                }
            });
            // 쓰기 쓰레드 생성
            Thread writeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String clientMessage;
                        while ((clientMessage = keyboardReader.readLine()) != null) {

                            if ("exit".equalsIgnoreCase(clientMessage)) {
                                System.out.println("채팅방 종료.");
                                writer.println("[" + name +"] 님이 퇴장 했습니다.");
                                break;
                            }
                            // 서버의 메세지 전송
                            writer.println("[" + name + "]" + clientMessage);

                        }
                    } catch (IOException e) {
                        System.out.println("메세지 전송중 오류가 발생했습니다.");
                    }
                }
            });

            readThread.start();
            writeThread.start();

            readThread.join();
            writeThread.join();

        } catch (UnknownHostException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } // end of main
} // end of class
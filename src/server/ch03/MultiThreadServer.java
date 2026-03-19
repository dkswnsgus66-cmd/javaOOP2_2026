package server.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("클라이언트에 연결 요청을 기다립니다");
            Socket clientSocket = serverSocket.accept();
            System.out.println("============== 서버 실행  ===========");

            ///  // 소켓과 연결 된 스트림
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            ///  키보드와 연결할 스트림
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            // 읽기 쓰레드 : 클라이언트 메세지를 계속 수산하는 일
            Thread readThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        String clientMessage;
                        while ((clientMessage = reader.readLine()) != null) {

                            if ("exit".equalsIgnoreCase(clientMessage)) {
                                System.out.println("클라이 언트가 종료 했습니다.");
                                break;
                            }
                            System.out.println("클라이언트 메세지: " + clientMessage);
                        }
                    } catch (IOException e) {
                        System.err.println("클라이 언트와 연결이 끊겼습니다");
                    }
                }
            });
            Thread writeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        String serverMessage;

                        while ((serverMessage = keyboardReader.readLine()) != null) {
                            writer.println(">>>" + serverMessage);
                            if ("exit".equalsIgnoreCase(serverMessage)) {
                                System.out.println("종료했습니다");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("서버 mew");
                    }
                }
            });
            readThread.start(); // 읽기 쓰레드 시작
            writeThread.start(); // 쓰기 쓰레드 시작

            readThread.join(); // 읽기 쓰레드가 종료까지 대기
            writeThread.join(); // 쓰기 쓰레드가 종료까지 대기

            /**
             * join() = 이 쓰레드가 끝날때 가지 기다려 줘 라는 의미이다
             * Thread.sleep() 이 N초동안 잠깐 멈추라는 의미
             * join()은 저 쓰레드가 끝날때까지 멈춰 입니다.
             *
             * join() 이 없으면 main 쓰레드가 바로 try 블록을 벗어난다 try resource라 close 자동호출되고 소켓이 끊긴다
             * 그리고 아직 실행중인 readThread writeThread 가 닫힌 소켓으로 통신시도... 오류발생 join은 메인쓰레드가 종안되게 막아버림
             * readThread writeThread 에 조인을 쓰면 메인 쓰레드 범위 밖으로 벗어나지 않는다
             */

        }// Bufferd
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 쓰기 쓰레드 : 키보드 입력을 받아 클라이언트에게 전종

    } // end of main
} // end of class
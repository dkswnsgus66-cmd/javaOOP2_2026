package client.ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class AbstractClient {

    private String name;
    private Socket socket;
    private PrintWriter writerStream;
    private BufferedReader readerStream;
    private BufferedReader keyboardReader;

    public AbstractClient(String name) {
        this.name = name;
    }

    // 실행 흐름 정의 (자식 클래스에서 재정의 불가) final
    public final void run() {

        // 순서가 중요
        try {
            connectToServer(); // 서버연결
            setupStreams(); // 연결되면 읽기 쓰기 키보드 생성
            startCommunication();
        } catch (Exception e) {
            e.printStackTrace(); // 스택 구조 예외를 추적하게 하는 부분을 출력
        }finally {
            // 최종에는 socket.close 처리
            cleanUp();
        }
    }

    // AbstractClient 를 상속받은 자식 클래스는 무조건 connectToServer 라는 메서드를 재정의 해야한다
    protected abstract void connectToServer() throws IOException;


    private void setupStreams() throws IOException {
        writerStream = new PrintWriter(socket.getOutputStream(), true);
        readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        keyboardReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void startCommunication() throws InterruptedException {
        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg;
                    while ((msg = readerStream.readLine()) != null) {

                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("서버와의 연결이 끊겼을때.");
                }
            }
        });

        // 키보드 에서 값을 받아서 메세지 전송
        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String input;
                    while ((input = keyboardReader.readLine()) != null) {
                        writerStream.println("[" + name + "]" + input);
                    }
                } catch (Exception e) {
                    System.out.println("전송중 오류발생");
                }
            }
        });

        readThread.start();
        writeThread.start();
        readThread.join();
        writeThread.join();

    } // end of startCommunication

    // 외부에서 메서드를 통해서 주소값을 주입받음
    protected void setSocket(Socket socket){
        this.socket = socket;
    }

    private void cleanUp(){
        try {
            if(socket != null){
                socket.close();
            }
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}

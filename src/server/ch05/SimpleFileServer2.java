package server.ch05;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFileServer2 {

    private static final int PORT = 5000;
    private static final String UPLOAD_DIR = "Uloads/";

    public static void main(String[] args) {
        // 실행에 흐름 처리
        // 먼저 저장 폴더 생성하는 법
        new File(UPLOAD_DIR).mkdir(); // 저장 폴더 생성
        System.out.println("파일서버 시작 - 포트 : " + PORT);
        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
                Socket socket = serverSocket.accept(); // 블로킹 상태
        ) {


            System.out.println("클라이언트 연결 성공");

            handleClient(socket);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    } // end of main

    // 파일 처리기능 함수
    private static void handleClient(Socket socket) {

        try (
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream()
        ) {
            // 1 단계 : 파일 이름 수신(약속으로 고정 100바이트)
            byte[] nameBuffer = new byte[100];
            int text = in.read(nameBuffer);// 한번에 100 바이트 읽어
            // 이 다음부터 아래에서 다시 in.read() 는 파일 내용을 읽을거임
            //[a],[b],[c],[.],[t],[x],[t],[0][0][0][0].... 이렇게 들어올거임 100 바이트 단위 문자가 아니기에 0이 들어올거임
            String fileName = new String(nameBuffer).trim(); // 바이트를 문자열로 읽음
            // .trim()은 뒤에 붙은 빈 공간 [0] 제거
            System.out.println("수신할 파일이름: " + fileName);

            // 2단계 : 파일 내용 수신후 서버측 컴퓨터에 저장
            File file = new File(UPLOAD_DIR + fileName); // 자바 클래스 파일에 접근할수있는객체 이러면 객체생성과 동시에 파일이름 지정
            try (FileOutputStream fos = new FileOutputStream(file)) {
                // 클리이언트가 보낸 데이터를 읽어서 서버측 컴퓨터에 저장 해야 함.
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead); // 읽은 만큼만 저장해
                }
            }

            System.out.println("파일 저장 완료: " + UPLOAD_DIR + fileName);
            // 클라이 언트측에 완료 메세지 전송
            out.write(("파일 업로드 성공 : " + fileName).getBytes()); // outStream 이라 바이트 로 변환 시켜야 한다
            out.flush();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

} // end of class

package client.ch06;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SinpleFileClient2 {

    private static final int PORT = 5000;

    public static void main(String[] args) {
            System.out.println("파일전송 클라이언트 시작");
            Scanner sc = new Scanner(System.in);
            System.out.println("전송할 파일 경로를 입력하세요 (예: C:\\_work_java\\text.txt");
            String filePath = sc.nextLine();

            File file = new File(filePath);
            if (file.exists() || file.isFile()) {//입력한ㅇ 파일의 경로에서 실제로 있는지 확인하는 메서드 || 실제로 있지만 그게 파일인지 확인
                System.out.println("파일이 존재하지 않습니다");
                return;
            }

        try (Socket socket = new Socket("localhost", PORT)) {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            // 1단계 : 파일 이름 전송 고정 100바이트 제약
            String fileName = file.getName();
            //C:/test.txt --> getName 을 호출하면 --> test.txt 만 추출 해줌 이걸 파싱이라 한다
            byte[] nameBytes = fileName.getBytes();
            byte[] buffer = new byte[100];

            // 방어적 코드 - 파일 이름이 100 바이트 이상 안되게 처리
            if (nameBytes.length > 100) {
                System.out.println("파일 이름이 너무 깁니다 최대 100자");
                return;
            }
            // 파일 이름 전송
            for (int i = 0; i < nameBytes.length; i++) {
                buffer[i] = nameBytes[i];
            }
            // 서버측에서 딱 한번 100바이트 통으로 읽기 위한 코드로 작성 되어있음.
            out.write(buffer);
            out.flush();

            // 2단계 파일 내용 전송
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] bufferFile = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(bufferFile)) != -1) {
                    out.write(bufferFile, 0, bytesRead);
                }
                out.flush();
                System.out.println("전송완료: " + fileName);
            }
            // 3 단계 서버측 응답 수신
            byte[] restanceBuffer = new byte[1024];
            int restanceLength = in.read(restanceBuffer);
            if (restanceLength > 0) {
                System.out.println("서버 응답 : " + new String(restanceBuffer, 0, restanceLength));
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } // end of main

} // end of class

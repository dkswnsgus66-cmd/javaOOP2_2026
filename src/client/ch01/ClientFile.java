package client.ch01;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

// 클라이언트 소켓 통신을 위한 클라이언트 측 코드
public class ClientFile {

    public static void main(String[] args) {

        // 클라이언트 는 접근할 서버 컴퓨터의 IP주소와 포트 번호를 알고있어야 한다.
        try (Socket socket = new Socket("192.168.4.10", 5000)) { // 서버IP 주소를 넣어서 보낸다 주소를 지정하고 데이터를 보낼때 쓰는 포트가 5000번 이다

            //Socket socket = new Socket("localhost", 5000)
            // 위 코드가 실행되는 순간 내부적으로 이미 서버 컴퓨터와 연결을 시도한 상태
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true); // 글자를 내보낸다
//            writer.write("Hello Server!~~~~~~" + "\n"); // 개행 문자까지 보내야 정상 보내짐
            writer.println("안녕 ㅇㄴㄹㄴㅇㅎㄱ");


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

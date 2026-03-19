package server.ex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    BufferedReader reader;
    PrintWriter writer;

    public Server(BufferedReader reader ,PrintWriter writer){
        this.reader = reader;
        this.writer = writer;
    }


    public void writeForServer(){

        this.writer.println("클라이언트에게 보낼 메세지");

    }


    private void readForServer() throws IOException {
        try {
            String message = this.reader.readLine();
            System.out.println(message);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        try {
            writeForServer();
            readForServer();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(5002)
        ) {

            Socket client =serverSocket.accept(); // 클라인언트 를 받을때까지 대기
            System.out.println("클라이언트 연결");
            // 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream())); // 연결된 클라이언트로 부터 받은걸 읽기
            // 쓰기
            PrintWriter writer = new PrintWriter(client.getOutputStream(),true); // 연결된 클라이언트에게 보내기
            Server server = new Server(reader,writer);

            server.run();




//            String dataRead = reader.readLine(); // 읽고 쓰는 기능을 동시에 수행할수 없다 보니 먼저 클라이언트 에서 메세지를 받을때 까지 다음 동작을 안한다
//            System.out.println(dataRead);
//            writer.println("클라이언트 에게 보낸다.");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

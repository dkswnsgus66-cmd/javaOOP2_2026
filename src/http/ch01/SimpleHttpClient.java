package http.ch01;

//https://jsonplaceholder.typicode.com/todos/1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleHttpClient {

    public static void main(String[] args) {
        //자바로 HTTP 요청과 응답 만들어 보기
        //String urlString = "https://nid.naver.com/nidlogin.login?mode=form&url=https://www.naver.com/";
        String urlString = "https://jsonplaceholder.typicode.com/posts/1";

        // HTTP 통신하는 클래스
        HttpURLConnection connection = null;

        // 1단계 URL 객체 생성
        try {
            URL url = new URL(urlString);

            // 2단계 : HTTP 연결 객체 생성
            connection = (HttpURLConnection)url.openConnection();

            // 3단계 : 요청 방식 설정 (GET , POST ,PUT ,DELETE)
            // 자원의 요청 --> GET 던져야 함
            connection.setRequestMethod("GET");//(GET , POST ,PUT ,DELETE) 메서드라 불린다
            connection.setRequestProperty("Accept","application/json");

            // 4단계 : 실제 요청 전송 --> 서버 --> 즉시 응답 --> (응답 코드)
            int responseCode = connection.getResponseCode();
            System.out.println("응답 코드 확인 : " + responseCode);

            if(responseCode != 200){
                System.out.println("요청 실패!");
                return;
            }

            // 5단계 : 응답 본문 읽기
            // I/O 단원에서 배운 체인 그대로
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

                // 문자가 많을때 StringBuffer 를 사용하는게 좋다
                StringBuffer response = new StringBuffer();
                String line;
                while ( (line = reader.readLine()) != null ){
                    response.append(line).append("\n");  // 스트링버퍼에 추가하는데 \n까지 읽지 않기에 끝에 추가
                }

                System.out.println("응답내용 : ");
                System.out.println(response.toString());
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                connection.disconnect();
            }
        }

    } // end of main

}

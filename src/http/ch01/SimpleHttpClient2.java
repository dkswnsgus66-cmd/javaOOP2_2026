package http.ch01;

//https://jsonplaceholder.typicode.com/todos/1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class SimpleHttpClient2 {



    public static void main(String[] args) {
        // 가짜 서버에 유저 10번의 정보를 요청 하고 응답을 받고 콘솔창에 출력하세요
        String userUrl = "https://jsonplaceholder.typicode.com/users/10";
        HttpURLConnection connection = null; // connection 에 HttpURLConnection 을 넣기 때문에 굳이 생성자를 만들 필요없다
        // 클래스 안에서 HttpURLConnection connection; 를 선언하면 알아서 null을 선언해 주지만 class 밖에선 null 값을 넣어주지 않기 때문에 null을 넣어 줘야 한다

        try {
            URL url = new URL(userUrl);
            // HTTP 연결 객체 생성
            connection = (HttpURLConnection) url.openConnection(); // url을 다운캐스팅 url 메서드를 통해서 객체생성 그리고 그 객체를 connection에 넣어줌

            connection.setRequestMethod("POST"); // GET요청날림
//            connection.setRequestProperty("Accept","application/json");
            int responseCode = connection.getResponseCode(); // 요청 응답 코드
            System.out.println("응답코드 확인: " + responseCode);
            if(responseCode != 200){
                System.out.println("요청 실패");
                return;
            }


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ( (line = br.readLine()) != null){
                System.out.println(line);
            }


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

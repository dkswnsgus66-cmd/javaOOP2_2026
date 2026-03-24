package http.gson;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TodoClient {

    public static void main(String[] args) {
        // 통신할 주소 : https://jsonplaceholder.typicode.com/todos/1
        // 단건 조회 -> JSON Object { } 응답 됨.
        String urlString = "https://jsonplaceholder.typicode.com/todos/1";
        HttpURLConnection connection;

        try {

            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            // HTTP 요청 메세지 만들어서 --> 연결 요청
            connection.setRequestMethod("GET");
            // 내가 설정하지 않더라고 기본적인 설정이 구축되어 있음.
            // 바로 연결요청
            int responseHttpCode = connection.getResponseCode(); // 통신
            System.out.println("통신성공여부 : " + responseHttpCode);

            // 응답 본문 추출
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                // 한줄 읽고 데이터를 보관해야 하기때문에 StringBuffer를 쓴다
                StringBuffer response = new StringBuffer();
                String read;
                while ((read = reader.readLine()) != null) {
                    response.append(read);
                }
                // StringBuffer --> 문자열로 형 변환 toString() 호출 , "" 문자열 더하기
                String jsonString = response.toString();
                System.out.println("JSON 응답 : " + response);
                // 자바 프로그램 사용하려면 JSON 형식 텍스트를 파싱 처리해야 한다.
                // GSON 라이브러리 사용 - 사용방법
                Gson gson = new Gson();
                //Java 객체 변환  <--- json 문자열

                // 변환 하고자 하는 json 형식의 문자열 <--- 1번째 인수 값
                // 변환 하고자 하는 dto 타입 (클래스)
                Todo todo = gson.fromJson(jsonString, Todo.class); // 클래스 객체 생성가능
                // Gson lib 활용해서 간단히 파싱 처리하고 우리 자바에서 쓰는 데이터 타입으로 활용가능.
                System.out.println("--------------------");
                System.out.println(todo.getId());
                System.out.println(todo.getUserId());
                System.out.println(todo.getTitle());
                System.out.println(todo.toString());
            }
        } catch (Exception e) {
            System.out.println("통신 실패: " + e.getMessage());
        }


    } // end of main

}

package http.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TodoClient2 {

    public static void main(String[] args) {

        // 여러건 조회 -> JSON Array [{ },{ },{ } ...]
        String urlString = "https://jsonplaceholder.typicode.com/todos";
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

                String jsonString = response.toString();
                System.out.println("JSON Array응답 : " + response);
                // JSON Array 파싱
                // TypeToken : List<Todo> 처럼 제네릭 타입을 파싱할때 사용

                Gson gson = new Gson();
                // TypeToken은 Gson이 List<Todo> 라는걸 인지 시키기 위한것이다
                // 그리고 이걸 익명클래스로 객체 생성과 동시에 클래스를 정의 한다고 볼수 있다
                // 그냥 List<Todo> list = new ArrayList<Todo> 로 객체는 넣을수 있지만 Gson이 이게 Todo 인지 인지를 못한다
                // 그래서 TypeToken을 상속받는 List<Todo> 를 만든 것이다.
                TypeToken<List<Todo>> typeToken = new TypeToken<>() {}; // Gson이 판별할수있는 Todo 객체를 넣을수 있는 리스트 선언
                // TypeToken 을 선언하지 않으면 Gson이 해당 리스트를 알아보지 못해  Map으로 넣어버린다

                List<Todo> todoList = gson.fromJson(jsonString, typeToken.getType());

                System.out.println("전체 갯수 : " + todoList.size() + "개"); // Todo 객체 200개 가 들어감
                // 200개 중에 앞에있는 3개만 출력
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(todoList.get(i));
                }

            }
        } catch (Exception e) {
            System.out.println("통신 실패: " + e.getMessage());
        }


    } // end of main

}

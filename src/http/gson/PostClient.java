package http.gson;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostClient {

    //https://jsonplaceholder.typicode.com/comments/1
    public static void main(String[] args) {

        String urlString = "https://jsonplaceholder.typicode.com/comments/1";

        // H
        HttpURLConnection connection = null;
        // 연결 선언
        try {
            URL url = new URL(urlString);
            // 연결 선언하고 HTTP 통신과 연결 메서드를 통해 생성자를 만든다
            connection = (HttpURLConnection) url.openConnection();
            // 연결 되었으니 내용물 출력 그리고 그 내용물들을 자바 객체에 넣어 데이터 타입으로 사용 하게 만들기
            // 그리고 내용물 읽기 데이터 InputStream 내용 물은 문자라 Reader 를 쓰면 도니다
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

                StringBuffer sb = new StringBuffer(); // String 에서 새로운 객체를 생성해 메모리 누수 방지를 위해 쓴거
                String line;

                while ( (line = reader.readLine()) != null ){
                     // 한번 동작 할때마다 한 라인씩 해당 문자열에 더해준다
                    sb.append(line); // StringBuffer 는 \n 없이 한라인 마다 이어 붙이기에 붙여 준거임
                }
                String jsonString = sb.toString(); // String 으로 변환
                Gson gson = new Gson();
                Post post = gson.fromJson(jsonString,Post.class); // 해당 url에서 가져온 문자들을 가져왔고 그걸 지정한 클래스 멤버 변수에 넣어줬다
                System.out.println(post.getPostId());
                System.out.println(post.getId());
                System.out.println(post.getName());
                System.out.println(post.getBody());
                System.out.println(post.getEmail());
                System.out.println("-------------------");
                System.out.println(post.toString());

            }




        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }//

}

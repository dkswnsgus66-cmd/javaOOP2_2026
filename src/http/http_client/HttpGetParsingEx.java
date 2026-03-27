package http.http_client;

//

import com.google.gson.Gson;
import http.http_client.dtos.Post;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpGetParsingEx {

    public static void main(String[] args) {

        // 1. HttpClient와 Gson 준비 하기
        // HttpClient 기능 추상 클래스이기에 new 로 직접 생산 못하고 메서드로 생산하게 자바 개발자가 설계
        HttpClient client = HttpClient.newHttpClient(); // 메서드로 직접 생성자 생성 생성된 객체의 주소가 담김
        Gson gson = new Gson();

        try {

            // Http 요청 메세지 생성 (요청 생성)
            // HttpRequest <--- 객체를 생성해주는 녀석
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts/1")).GET().build();

            // 3. 응답 받기
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. 응답 내용 확인 (로깅) -- > Http응답 메세지
            System.out.println("response : " + response);
            System.out.println("response : " + response.body());

            // 5. 파싱 처리하기. 오브젝트를 문자열 or 문자열을 오브젝트로
            Post post = gson.fromJson(response.body(), Post.class);
            System.out.println("=====파싱완료=====");
            System.out.println("게시글 아이디: " + post.getId());
            System.out.println("게시글 작성자 아이디: " + post.getUserId());
            System.out.println("게시글 게시글 제목: " + post.getTitle());
            System.out.println("게시글 게시글 본문: " + post.getBody());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }// end of main


}

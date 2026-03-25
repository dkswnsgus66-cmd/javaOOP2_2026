package toJson;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ToJsonEx {

    public static void main(String[] args) {

        // 자바 객체생성 ---> 텍스트 형식인 Json으로 변환
        Post post = new Post();
        post.setUserId(1);
        post.setId(10);
        post.setTitle("자바 공부");
        post.setBody("JSON 파싱 연습");

        Gson gson = new Gson();
        //gson.fromJson(); // Json 을 Object로 만드는것
        String json = gson.toJson(post,Post.class); // 오브젝트를 Json으로 만드는것
        System.out.println(json);

        // jsonPost를 다시 역방향으로

        Post repost = gson.fromJson(json, Post.class);
        System.out.println(repost);

    } // end of main


}
@AllArgsConstructor
@NoArgsConstructor
@Data
class Post{

    private int userId;
    private int id;
    private String title;
    private String body;

}
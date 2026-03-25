package http.gson;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Post {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;


}

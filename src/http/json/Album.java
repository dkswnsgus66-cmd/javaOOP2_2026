package http.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class Album{

   private int userId;
   private int id;
   private String title;

//    public Album(int userId, int id, String title) {
//        this.userId = userId;
//        this.id = id;
//        this.title = title;
//    }

    @Override
    public String toString() {
        return "Album{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
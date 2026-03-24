package http.gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Todo {

    private int userId;
    private int id;
    private String title;
    private boolean completed;

}

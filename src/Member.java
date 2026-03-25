import lombok.*;

// 자동으로 기본 생성자 만들어 주나?

@Data
//@Setter
//@Getter
@AllArgsConstructor // 매개변수 전부 가지고 있는 생성자
//@NoArgsConstructor // 아무것도 안받는 생성자
//@ToString // toString 만듬
public class Member {

    private int id;
    private String name;
    private  String email;
    private  int age;

    // 필요하다면 직접 생생자 생성가능


    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
   public Member(){

   }
}

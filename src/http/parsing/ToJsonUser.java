package http.parsing;

import client.ch05.AbstractClient;
import com.google.gson.Gson;

public class ToJsonUser {

    public static void main(String[] args) {

        User user = new User();
        Address address = new Address();
        Company company = new Company();
        user.setId(1);
        user.setName("홍길동");
        user.setEmail("dkswnsgus@naver.com");
        user.setAddress(address);
        address.setStreet("반여2동");
        address.setStreet("중아대로");
        user.setCompany(company);
        user.getCompany().setName("그냥 회사");

        // 객체 ---> (직렬화) JSON 텍스트 형식

        Gson gson = new Gson();
       String json = gson.toJson(user,User.class);
        System.out.println(json);



    }

}

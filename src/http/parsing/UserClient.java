package http.parsing;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//https://jsonplaceholder.typicode.com/users/1
public class UserClient {





    public static void main(String[] args) {
        HttpURLConnection connection = null;

        String url = "https://jsonplaceholder.typicode.com/users/1";

        try {


            URL urlString = new URL(url);
            connection = (HttpURLConnection) urlString.openConnection(); // http로 변환 다운 캐스팅

            // 데이터 읽을수 있는 기능
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer sb = new StringBuffer();
            String line;
            while ( (line = br.readLine()) != null){

                sb.append(line); // 하나의 객체인 스트링 버퍼에 계속 문자 이어 붙이기

            }
            // 내용 물을 읽었으니 해당 내용물을 User 클래스에 할당 Gson
            String jsonString = sb.toString(); // sb를 다시 스트링으로 변환
            Gson gson = new Gson(); // 내용물 자바 객체에 할당하는 기능

            // 받은 내용들 jsonString은 전부 String이라 String으로 멤버변수에 할당된다 근데 id는 왜 int로 할당되냐?
            User user =  gson.fromJson(jsonString,User.class); // 해당 클래스에 데이터 할당
            //
            System.out.println(user.getId());
            System.out.println(user.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }// end of main



}

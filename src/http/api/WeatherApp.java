package http.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {

    public static void main(String[] args) {
        String apiKey = "48f17fd9e73e60977fa6d8246d59eb27"; // 발급받은 API 키 입력
        String city = "Busan";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                "&appid=" + apiKey + "&units=metric&lang=kr";
        System.out.println(urlString);

        try {
            // 1. URL 객체 생성 및 연결 설정
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000); // 연결 타임아웃 5초
            conn.setReadTimeout(5000);    // 읽기 타임아웃 5초

            // 2. 응답 코드 확인 (200 OK 인지 체크)
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                // 3. 입력 스트림을 통해 데이터 읽기
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                // 4. 결과 출력
                System.out.println("응답 성공!");
                Gson gson = new Gson();
                WeatherDTO weather = gson.fromJson(response.toString(), WeatherDTO.class);// DTO 데이터 넣음

                String icon = weather.getWeather().get(0).getIcon();// 아이콘
                String main = weather.getWeather().get(0).getMain(); // 대략적 날씨상태 (ex 비)
                String description = weather.getWeather().get(0).getDescription(); //세세한 날씨 상태(ex 실 비 )

                double temprature = weather.getMain().getTemp();// 기온
                double humidity = weather.getMain().getHumidity(); // 습도

                if (temprature > 13 && temprature < 20) {
                    System.out.println("오늘 날씨가 산책하기 좋네요. \uD83C\uDF1E");
                    System.out.println("현재 기온 : " + temprature + "C");
                }
                if (40 < humidity && humidity < 60){
                    System.out.println("습도가 적당 하네요. " + icon);
                    System.out.println("현재 습도: " + humidity + "%");
                }
                System.out.println("날씨 상태: " + description);


            } else {
                System.out.println("호출 실패. 응답 코드: " + responseCode);
            }
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
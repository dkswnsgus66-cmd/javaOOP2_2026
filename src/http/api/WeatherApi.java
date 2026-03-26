package http.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherApi {

    public static void main(String[] args) {

        // 1. 공공 데이터 포탈 인증키 (보통 디코딩 사용)
        String serviceKey = "4ecb405c670333d8cd7da1f0915855a2f6403e082c0e724d833fecda0fca669b";

        // 2. 조회에 필요한 파라미터 설정
        String baseData = "20260325";
        String baseTime = "0500";
        String nx = "35";
        String ny = "129";
        try {

            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
            urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode("20260325", "UTF-8")); /*‘21년 6월 28일 발표*/
            urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*06시 발표(정시단위) */
            urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
            urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/

            // http 연결 설정
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // 메서드 객체생성
            connection.setRequestMethod("GET");
            // 생략

            BufferedReader rd;
            if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
                // 통신 성공
                rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                // 통신 실패
                rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                text.append(line);
            }
            rd.close();
            connection.disconnect();
            System.out.println(text.toString());

            Gson gson = new Gson();
//                gson.fromJson(text.toString(),);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }


    }

}

package http.api.my_;

import java.net.URLEncoder;

public class Encode {

    public static void main(String[] args) {

        //"http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"

        StringBuilder url = new StringBuilder();
       String url1 = URLEncoder.encode("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo");
        System.out.println(url1);
        url.append(url1);

    }

}

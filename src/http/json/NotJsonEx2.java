package http.json;

public class NotJsonEx2 {

    public static void main(String[] args) {
        // 자바 프로그램에서 ---> 서버측으로 데이터를 보내려면 json 형식에 텍스트를 가장 많이 사용
        Album album2 = new Album(1, 1, "안녕반가워 나의앨범이야");
        // joson Object로 변환해서 보내기

        // 1.
        StringBuffer sb = new StringBuffer();

        sb.append("{");// 시작 중괄호
        sb.append("\"userId\":").append(album2.getUserId()).append(",");
        sb.append("\"id\":").append(album2.getId()).append(",");
        sb.append("\"title\":").append("\"" + album2.getTitle() + "\"").append("");
        sb.append("}");// 종료 중괄호
        System.out.println(sb.toString());

//        String json = "{\"userId\": 1,\"id\": 1,\"title\": \"quidem molestiae enim\"}";
//
//        String step1 = json.replace("{","").replace("}","");
//        System.out.println(step1);
//
//        String[] parts = step1.split(","); // split에서 "," 기준으로 배열에 넣는다
////        System.out.println(parts.length);
////        System.out.println(parts[0]);
////        System.out.println(parts[1]);
////        System.out.println(parts[2]);
//
//
//
//        // userId 추출
//        String userIdValue = parts[0].split(":")[1]; //
//        int userIdValueParse = Integer.parseInt(userIdValue.trim()); // value 를 인트 타입으로 변환 생성자는 인트를 받기때문에
//        // id 추출
//        String idValue = parts[1].split(":")[1];
//        int idValueParse = Integer.parseInt(idValue.trim()); // value 를 인트 타입으로 변환 생성자는 인트를 받기때문에 trim()공백제거
//        // title value추출
//        String titleValue  = parts[2].split(":")[1];
//        Album album = new Album(userIdValueParse,idValueParse,titleValue);
//
//
//        System.out.println(album.toString());

    } // end of main
}// end of class



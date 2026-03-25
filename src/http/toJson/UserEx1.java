package http.toJson;

public class UserEx1 {

    public static void main(String[] args) {

        // User (DTO) 데이터를 담는 목적으로만 설계된 클래스
        // UserDTO 클래스를 내부 클래스에 필드를 만들었을 경우 사용법

        User user = new User();
        user.setId(100);
        user.setName("홍길동");
        user.setEmail("dkswnsgus88@naver.com");

        // User 안에 있는 Address 객체를 생성하는 방법
        // 유저 객체가 태어나 있어야 Adress 가 생길수 있으니 user.new Adress가 맞다
        User.Address address = user.new Address();
        address.setCity("부산");
        address.setStreet("중앙대로");

        User.Address.Geo geo = user.getAddress().new Geo();
        address.setGeo(geo);
        geo.setLng("55.6");
        geo.setLat("112.6");
    } // end of main

}

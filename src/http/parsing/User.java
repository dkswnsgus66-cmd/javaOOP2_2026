package http.parsing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Address address;
    private Company company;
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Address {
        private Geo geo;
        private String street;
        private String suite;
        private String zipcode;


        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class Geo{
            private String lat;
            private String lng;
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Company{
        private String name;
        private String catchphrase;
        private String bs;
    }

}

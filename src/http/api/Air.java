package http.api;

import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Air {

    private Response response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Response {

        private Body body;
        private Header header;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        static class Header {

            private String resultMsg;
            private String resultCode;

        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        static class Body {

            private List<Item> items;
            private int totalCount;
            private int pageNo;
            private int numOfRows;


            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            static class Item {

                private String clearVal;
                private String sn;
                private String districtName;
                private String dataDate;
                private String issueVal;
                private String issueTime;
                private String clearDate;
                private String issueDate;
                private String moveName;
                private String clearTime;
                private String issueGbn;
                private String itemCode;

            }

        }

    }

}

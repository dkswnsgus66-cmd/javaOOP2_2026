package http.api.my_;

import lombok.Data;

import java.util.List;
@Data
public class WeatherDTO {

    private Response response;
    @Data
    class Response {

        private Heder heder;
        private Body body;


    } // end of inner Response class
    @Data
    public static class Item{
        private String baseDate; /*06시 발표(정시단위) */
        private String baseTime;/*06시 발표(정시단위) */
        private String category;
        private int nx;
        private int ny;
        private String obsrValue;
    }
    @Data
    public static class Items{

        private List<Item> item;

    }
    @Data
    public static class Body {
        private String dataType;
        private Items items;
        private int pageNo;
        private int numOfRows;
        private int totalCount;

    }
    @Data
    public static class Heder {
        private String resultCode;
        private String resultMsg;

    }

} // end of outerclass



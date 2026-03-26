package http.api;

import lombok.Data;

import java.util.List;
@Data
public class WeatherDTO {

    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Call main;
    private int visibility;
    private Wind wind;
    private Sys sys;
    private int dt;
    private int timezone;
    private int id;
    private String name;
    private int cod;
    @Data
    public static class Coord{

        private double lon;
        private double lat;

    }
    @Data
    public static class Weather{
        private int id;
        private String main;
        private String description;
        private String icon;
    }
    @Data
    public static class Call{
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;

    }
    @Data
    public static class Wind{

        private double speed;
        private int deg;

    }
    @Data
    public static class Sys{
        private int type;
        private int id;
        private String country;
        private int sunrise;
        private int sunset;

    }



}

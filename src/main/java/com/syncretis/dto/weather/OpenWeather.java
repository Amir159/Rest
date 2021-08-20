package com.syncretis.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeather {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private String name;

    public OpenWeather() {
    }

    public OpenWeather(List<Weather> weather, Main main, Wind wind, String name) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public String getName() {
        return name;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.syncretis.controller;

import com.syncretis.dto.weather.OpenWeather;
import com.syncretis.service.WeatherService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class OpenWeatherController {
    private final WeatherService weatherService;

    public OpenWeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    OpenWeather getWeather(@CurrentSecurityContext(expression = "authentication?.name") String username) {
        return weatherService.getOpenWeather(username);
    }

    //----------------------GET ALL HEADERS---------------------------
    /*@GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println((String.format("Header '%s' = %s", key, value)));
        });

        return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }*/
}
package com.syncretis.service;

import com.syncretis.dto.weather.OpenWeather;
import com.syncretis.exception.UsernameNotFoundException;
import com.syncretis.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final UserRepository userRepository;

    public WeatherService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OpenWeather getOpenWeather(String username) {
        String url = "https://api.openweathermap.org";
        String token = "f0d7c77842669fcea2015fdeb04698ea";
        String city = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(HttpStatus.NOT_FOUND)).getCity();

        return restTemplate.getForObject(url + "/data/2.5/weather?q=" + city + "&appid=" + token, OpenWeather.class);
    }

    /* //------------------------------OkHttp3-return null bcs not all fields are declared in the class-------------------------------------
    public OpenWeather getOpenWeather(String username) {
        OpenWeather openWeather = null;
        OkHttpClient client = new OkHttpClient();
        String city = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(HttpStatus.NOT_FOUND)).getCity();
        String token = "f0d7c77842669fcea2015fdeb04698ea";
        HttpUrl mySearchUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment("data")
                .addPathSegment("2.5")
                .addPathSegment("weather")
                .addQueryParameter("q", city)
                .addQueryParameter("appid", token)
                .build();
        Request request = new Request.Builder().url(mySearchUrl).get().build();
        try {
            Response response = client.newCall(request).execute();
            ObjectMapper mapper = new ObjectMapper();
            openWeather = mapper.readValue(response.body().string(), OpenWeather.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return openWeather;
    }*/
}
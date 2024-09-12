package com.exercise.wether.controllers;


import com.exercise.wether.models.Current;
import com.exercise.wether.models.Weather;
import com.exercise.wether.repositories.CityInFileRepository;
import com.exercise.wether.service.WeatherClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
@RequiredArgsConstructor


public class TestController {

    final WeatherClient weatherClient;


//    @GetMapping("/{id}")
//    public Person getPerson(@PathVariable Long id) {


    @GetMapping("/weather")
    public String taskInfo() {

        return "weather";

    }

    @PostMapping ("/weather")
    public String showWeather(@RequestParam String city, Model model) {

//        Weather weather = weatherClient.getWeatherNow();
//        Weather weather = new Weather();

        CityInFileRepository cityInFileRepository = new CityInFileRepository();
        String cityCorrect = cityInFileRepository.isExistCityWithErrorName(city);
//        System.out.println("Names:  "+city+"    "+cityCorrect);
        String reportCity = "The results for the request "+city+" are given";
//        String reportCity = "";
        if (!city.equals(cityCorrect)) reportCity = "The entered city "+city+" is missing, the results for the city "+cityCorrect+" are given";

        RestTemplate restTemplate = new RestTemplate();
        Weather weather = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=32e079e0ae0e4e908e4195940240809&q=" + cityCorrect + "&aqi=no", Weather.class);

        model.addAttribute("reportCity", reportCity);
//        model.addAttribute("title", reportCity);
        model.addAttribute(weather);
        return "weather";

    }
}

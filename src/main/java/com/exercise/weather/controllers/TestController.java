package com.exercise.weather.controllers;


import com.exercise.weather.models.Weather.Weather;
import com.exercise.weather.repositories.CityInFileRepository;
import com.exercise.weather.service.Initialize;
import com.exercise.weather.service.WeatherClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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
//        log.trace("trace");
//        log.debug("debug");
//        log.info("info");
//        log.warn("warn");
//        log.error("error");
        CityInFileRepository cityInFileRepository = null;
        try {
            cityInFileRepository = new CityInFileRepository();
        } catch (IOException e) {
            System.out.println();
            log.warn("Error with {}", cityInFileRepository.getClass(),  e);
        }
//        try {
//            throw new IOException();
//        } catch (IOException e) {
//            log.warn("Sldf{}","2354235", e);
//        }
//            log.warn("Sldf{}", new Throwable().getStackTrace());
        String cityCorrect = cityInFileRepository.isExistCityWithErrorName(city);
        String reportCity = "The results for the request "+city+" are given";
        if (!city.equals(cityCorrect)) reportCity = "The entered city "+city+" is missing, the results for the city "+cityCorrect+" are given";

        RestTemplate restTemplate = new RestTemplate();
        Weather weather = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=32e079e0ae0e4e908e4195940240809&q=" + cityCorrect + "&aqi=no", Weather.class);

        model.addAttribute("reportCity", reportCity);
        model.addAttribute(weather);
        return "weather";

    }

    @GetMapping("/initialize-city")
    public String initializeCity(){
        Long count = Initialize.initializeCity();
        log.info("Initialize {} cites", count);
        return "weather";
    }
}

//package com.exercise.weather.service;
//
//import com.exercise.weather.models.Weather.Weather;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//
//@FeignClient(value = "weather", url = "http://api.weatherapi.com/v1/current.json?key=32e079e0ae0e4e908e4195940240809&q=London&aqi=no")
//
//public interface WeatherClient {
//
//    @RequestMapping(method = RequestMethod.GET, value = "/posts")
//    Weather getWeatherNow();
//
//}

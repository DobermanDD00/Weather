package com.exercise.weather.service;

import com.exercise.weather.models.City;
import com.exercise.weather.repositories.CityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CityService {
    private static CityRepository cityRepo;
    public Long save(City city){
        return cityRepo.save(city).getId();
    }
    public City getCityById(Long id){
        return cityRepo.findById(id).orElse(null);
    }


}

package com.exercise.weather.service;

import com.exercise.weather.models.City;
import com.exercise.weather.repositories.CityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.DefaultRepositoryMetadata;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CityService {

    private final CityRepository cityRepo;

    public City findById(long id){
        return cityRepo.findById(id);
    }


}

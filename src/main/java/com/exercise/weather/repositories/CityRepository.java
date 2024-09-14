package com.exercise.weather.repositories;

import com.exercise.weather.models.City;
import org.springframework.data.jpa.repository.JpaRepository;




public interface CityRepository extends JpaRepository <City, Long> {
    City findById(long id);
}

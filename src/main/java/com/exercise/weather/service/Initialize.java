package com.exercise.weather.service;

import com.exercise.weather.models.City;
import com.exercise.weather.repositories.CityInFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class Initialize {

    private static CityService cityService;//**************************

    public static Long initializeCity() {
        Long countFounded = 0L;
        Long countSaved = 0L;
        try {
            CityInFileRepository cityFileRepo = new CityInFileRepository();
            countFounded = (long) cityFileRepo.getStrs().size();
            for (String str : cityFileRepo.getStrs()) {
                List<Integer> indexesQuotationMarks = cityFileRepo.indexesQuotationMarks(str);
                String cityName = str.substring(indexesQuotationMarks.get(2) + 1, indexesQuotationMarks.get(3));


//                cityService.save(new City(0L, cityName));//********************************
                countSaved++;

            }
        } catch (IOException e) {
            log.warn("Error with creation repository file", e);

        }

        if (countFounded == countSaved)
            log.info("All founded cites saved: {}", countSaved);
        else
            log.warn("Founded cites: {}, saved: {}", countFounded, countSaved);

        return countSaved;
    }


}

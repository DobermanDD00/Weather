package com.exercise.weather.models.Weather;

import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@ToString
@Component
public class Weather {
    Location location;
    Current current;
}

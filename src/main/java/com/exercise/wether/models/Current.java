package com.exercise.wether.models;

import lombok.*;

@Getter
@Setter
@ToString

public class Current {

    private String last_updated;
    private double temp_c;
    private Condition condition;
    private double wind_kph;
    private int wind_degree;
    private double pressure_mb;
    private int humidity;
    private int cloud;
    private double feelslike_c;
    private double gust_kph;







}

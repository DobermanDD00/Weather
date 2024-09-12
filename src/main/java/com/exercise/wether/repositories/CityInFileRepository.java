package com.exercise.wether.repositories;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CityInFileRepository {
    public static void main(String[] args) {
        CityInFileRepository cityInFileRepository  = new CityInFileRepository();
        System.out.println(cityInFileRepository.isMissingSymbol("Mosko", "Moskow"));
        System.out.println(cityInFileRepository.isMissingSymbol("Toky", "Tokyo"));
        System.out.println(cityInFileRepository.isExistCityWithErrorName("Mosko"));
        System.out.println(cityInFileRepository.isExistCityWithErrorName("Toky"));

    }

    //    "city","city_ascii","lat","lng","country","iso2","iso3","admin_name","capital","population","id"

    Path path;
    List<String> strs;
    String lineHeaders;


    public CityInFileRepository() {
        this("src/main/resources/static/worldcities.csv");
    }

    public CityInFileRepository(String path) {
        this(Paths.get(path));
    }

    @SneakyThrows
    CityInFileRepository(Path path) {
        this.path = path;
        strs = Files.readAllLines(path);
        lineHeaders = strs.get(0);
        strs.remove(0);
    }


    public String isExistCityWithErrorName(String inputCityName) {

        for (String str : strs) {
            List<Integer> indexesQuotationMarks = indexesQuotationMarks(str);
            String cityName = str.substring(indexesQuotationMarks.get(2) + 1, indexesQuotationMarks.get(3));
            if (inputCityName.equals(cityName)) return inputCityName;
            else if (isOneError(inputCityName, cityName)) return cityName;
        }
        return null;
    }

    public boolean isOneError(String name, String planeName) {
        if (isMissingSymbol(name, planeName)) return true;
        if (isRedundantSymbol(name, planeName)) return true;
        if (isWrongSymbol(name, planeName)) return true;
        if (isMisplacedSymbol(name, planeName)) return true;
        return false;

    }

    public boolean isMissingSymbol(String name, String planeName) {
        if (name.length() != planeName.length() - 1) return false;
        int error = 0;
        for (int i = 0; i < name.length(); ) {
            if (name.charAt(i) == planeName.charAt(i + error)) {
                i++;
            } else {
                if (error == 0) error++;
                else
                    return false;
            }
        }
        return true;

    }

    public boolean isRedundantSymbol(String name, String planeName) {
        return isMissingSymbol(planeName, name);
    }

    public boolean isWrongSymbol(String name, String planeName) {
        if (name.length() != planeName.length()) return false;
        int errors = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != planeName.charAt(i)) errors++;
            if (errors > 1) return false;
        }
        return true;

    }

    public boolean isMisplacedSymbol(String name, String planeName) {
        if (name.length() != planeName.length()) return false;
        int[] indexesMisplaced = new int[2];
        int errors = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != planeName.charAt(i)) {
                if (errors > 1) return false;
                indexesMisplaced[errors] = i;
                errors++;
            }
        }

        if (name.charAt(indexesMisplaced[0]) == planeName.charAt(indexesMisplaced[1])
                && name.charAt(indexesMisplaced[1]) == planeName.charAt(indexesMisplaced[0])) return true;
        else return false;

    }

    private List<Integer> indexesQuotationMarks(String str) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '"') indexes.add(i);
        }
        return indexes;
    }


}

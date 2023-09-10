package org.fasttrackit.curs18.tema18.controller;

import org.fasttrackit.curs18.tema18.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping("countries")
    public List<Country> listAllCountries() {

        return countryService.getAllCountries().stream()
                .collect(Collectors.toList());
    }

    @GetMapping("countries/names")
    public List<String> listAllCountriesNames() {

        return countryService.getAllCountries().stream()
                .map(country -> country.getName())
                .collect(Collectors.toList());
    }

    @GetMapping("countries/{id}/capital")
    public String getCapitalById(@PathVariable int id) {

        Optional<String> capital = countryService.getAllCountries().stream()
                .filter(country -> country.getId() == id)
                .map(Country::getCapital)
                .findFirst();

        return capital.orElse("No country found with this ID: " + id);

    }
    @GetMapping("countries/{id}/population")
    public long getPopulationById(@PathVariable int id) {

        Optional<Integer> population =countryService.getAllCountries().stream()
                .filter(country -> country.getId() == id)
                .map(Country::getPopulation)
                .findFirst();

        return population.orElse(0);

    }
    @GetMapping("continents/{continentName}/countries")
    public List<Country> getCountriesByContinent(@PathVariable String continentName) {

        return countryService.getAllCountries().stream()
                .filter(country -> country.getContinent().equals(continentName))
                .collect(Collectors.toList());

    }

    @GetMapping("countries/{id}/neighbours")
    public List<String> getCountryNeighbours(@PathVariable int id) {

        return countryService.getAllCountries().stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .map(country -> country.getNeighbours())
                .orElse(Collections.emptyList());
    }

    @GetMapping("continents/{continentName}/countriesWithMinPopulation")
    public List<Country> getCountriesInContinentWithMinPopulation(@PathVariable String continentName, @RequestParam(required = false) int minimumPop) {

        return countryService.getAllCountries().stream()
                .filter(country -> country.getContinent().equals(continentName))
                .filter(country -> country.getPopulation() > minimumPop)
                .collect(Collectors.toList());


    }

    @GetMapping("countries/{x}/{y}")
    public List<Country> getCountriesByXButNotByY(@PathVariable String x, @PathVariable String y) {

        return countryService.getAllCountries().stream()
                .filter(country -> country.getNeighbours().contains(x))
                .filter(country -> country.getNeighbours().stream().noneMatch(neighbor -> neighbor.equals(y)))
                .collect(Collectors.toList());
    }
}

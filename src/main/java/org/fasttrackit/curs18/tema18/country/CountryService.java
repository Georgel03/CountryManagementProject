package org.fasttrackit.curs18.tema18.country;

import org.fasttrackit.curs18.tema18.controller.Country;
import org.fasttrackit.curs18.tema18.controller.FilesToCountryConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    protected List<Country> allCountries;

    public CountryService() {
        this.allCountries = FilesToCountryConverter.retrieveCountryFromFile("C:\\Users\\gstan\\IdeaProjects\\curs18\\tema18\\tema18\\src\\main\\resources\\countries.txt");
    }
    public List<Country> getAllCountries() {
        return allCountries;
    }
}

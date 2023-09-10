package org.fasttrackit.curs18.tema18.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FilesToCountryConverter {

    public static List<Country> retrieveCountryFromFile(String filepath) {

        List<Country> countries = new ArrayList<>();
        int id = 0;
        File file = new File(filepath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String[] neighborCodes = parts[5].split("~");
                    List<String> neighbours = Arrays.asList(neighborCodes);
                    Country createdCountry = new Country(++id, parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4], neighbours);
                    countries.add(createdCountry);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File was not found!");
        }

        return countries;

    }
}

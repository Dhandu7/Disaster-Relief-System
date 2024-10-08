package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenderOptionsReader {
    private static final String FILE_PATH = "edu/ucalgary/oop/GenderOptions.txt"; // since edu is treated as the source
                                                                                  // directory, no need to change file
                                                                                  // path for mac and windows as txt
                                                                                  // file is contained within the repo
                                                                                  // folder
    List<String> genderOptions;

    public GenderOptionsReader() {
        genderOptions = new ArrayList<>();
        loadGenderOptions();
    }

    private void loadGenderOptions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                genderOptions.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading gender options file: " + e.getMessage());
        }
    }

    public static List<String> getGenderOptions() {
        List<String> genderOptions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                genderOptions.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading gender options file: " + e.getMessage());
        }
        return genderOptions;
    }
}

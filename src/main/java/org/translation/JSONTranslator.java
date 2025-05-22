package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    private Map<String, Map<String, String>> countries;
    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */

    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));
            countries = new HashMap<>();

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String, String> languages = new HashMap<>();
                for (String key : jsonObject.keySet()) {
                    Object value = jsonObject.get(key);
                    if (value instanceof String) {
                        languages.put(key, (String) value);
                    }
                }
                countries.put((String) jsonObject.get("alpha3"), languages);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        List<String> countryLanguages = new ArrayList<>(countries.get(country).keySet());
        countryLanguages.remove("alpha2");
        countryLanguages.remove("alpha3");
        countryLanguages.remove("id");
        return countryLanguages;
    }

    @Override
    public List<String> getCountries() {
        List<String> countryList = new ArrayList<>();
        for (String key: countries.keySet()) {
            countryList.add(countries.get(key).get("alpha3"));
        }
        return countryList;
    }

    @Override
    public String translate(String country, String language) {
        String countryName = null;
        if (countries.containsKey(country)) {
            countryName = countries.get(country).get(language);
        }
        return countryName;
    }
}

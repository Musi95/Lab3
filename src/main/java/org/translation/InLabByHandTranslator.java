package org.translation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Extra Task: if your group has extra time, you can add support for another country code in this class.

/**
 * An implementation of the Translator interface which translates
 * the country code "can" to several languages.
 */
public class InLabByHandTranslator implements Translator {
    private static final String CANADA = "can";
    /**
     * Returns the language abbreviations for all languages whose translations are
     * available for the given country.
     *
     * @param country the country
     * @return list of language abbreviations which are available for this country
     */

    @Override
    public List<String> getCountryLanguages(String country) {
        if (CANADA.equals(country)) {
            return new ArrayList<>(List.of("de", "en", "zh", "es", "ur"));
        }
        return new ArrayList<>();
    }

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of("can"));
    }

    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     *
     * @param country  the country
     * @param language the language
     * @return the name of the country in the given language or null if no translation is available
     */

    @Override
    public String translate(String country, String language) {
        String ret = null;
        if ("de".equals(language)) {
            ret = "Kanada";
        }
        else if ("en".equals(language)) {
            ret = "Canada";
        }
        else if ("zh".equals(language)) {
            ret = "加拿大";
        }
        else if ("es".equals(language)) {
            ret = "Canadá";
        }
        else if ("ur".equals(language)) {
            ret = "کینیڈا";
        }
        return ret;
    }
}

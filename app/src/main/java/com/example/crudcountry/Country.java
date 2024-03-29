package com.example.crudcountry;

public class Country {
    private String countryName;
    private String capital;

    public Country(String countryName, String capital) {
        this.countryName = countryName;
        this.capital = capital;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}

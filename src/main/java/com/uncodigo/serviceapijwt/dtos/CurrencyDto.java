package com.uncodigo.serviceapijwt.dtos;

import com.uncodigo.serviceapijwt.models.Currency;

public class CurrencyDto {

    private Integer id;
    private String name;
    private String symbol;

    public CurrencyDto(Integer id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public static CurrencyDto fromCurrency(Currency currency) {
        return new CurrencyDto(currency.getId(), currency.getName(), currency.getSymbol());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

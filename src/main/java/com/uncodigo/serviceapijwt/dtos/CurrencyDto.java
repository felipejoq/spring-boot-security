package com.uncodigo.serviceapijwt.dtos;

import com.uncodigo.serviceapijwt.models.Currency;

public class CurrencyDto {

    private Integer id;
    private String name;

    public CurrencyDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CurrencyDto fromCurrency(Currency currency) {
        return new CurrencyDto(currency.getId(), currency.getName());
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
}

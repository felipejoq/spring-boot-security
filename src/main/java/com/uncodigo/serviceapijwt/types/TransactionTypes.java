package com.uncodigo.serviceapijwt.types;

public enum TransactionTypes {
    TRANSFERENCIA(1, "Transferencia"),
    DEPOSITO(2, "Depósito"),
    RETIRO(3, "Retiro");

    private final int id;
    private final String name;

    TransactionTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

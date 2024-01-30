package com.example.auth.entity;

public enum Code {
    SUCCESS("Operacja zakończona sukcesen"),
    PERMIT("Przyznano dostep"),
    A1("Nie udało się zalogować"),
    A2("Użytkownik o wskazanej nazwie nie istnieje"),
    A3("Wskazany token jest pusty lub nie ważny"),
    A4("Uzytkownik o takiej nazwie juz istnieje"),
    A5("Uzytkownik o takim emailu juz istnieje");

    public final String label;
    private Code(String label){
        this.label = label;
    }

}

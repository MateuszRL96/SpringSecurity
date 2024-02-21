package com.example.auth.entity;

public enum Code {
    SUCCESS("Operacja zakończona sukcesem"),
    PERMIT("Przyznano dostep"),
    A1("taki uzytkownik nie istnieje lub nie aktywowal konta"),
    A2("Użytkownik o wskazanej nazwie nie istnieje"),
    A3("Wskazany token jest pusty lub nie ważny"),
    A4("Uzytkownik o takiej nazwie juz istnieje"),
    A5("Uzytkownik o takim emailu juz istnieje"),
    A6("Użytkownik nie istnieje");

    public final String label;
    private Code(String label){
        this.label = label;
    }

}

package cz.upce.fei.bdast.gui;

import javafx.scene.control.Button;

/**
 * Tento výčtový typ definuje konstanty reprezentující titulky pro tlačítka
 * ({@link Button}), které jsou používány v rámci této aplikace
 */
public enum Titulek {
    KOMPONENT_VLOZENI("Vložení"),
    KOMPONENT_ZPRISTUPNI("Zpřístupňování"),
    KOMPONENT_ODEBRANI("Odebrání"),
    KOMPONENT_PRIKAZU("Příkazy"),
    KOMPONENT_SOUBORU("Soubory"),
    KOMPONENT_SPOTREBA("Spotřeba"),
    PRVNI("První"),
    POSLEDNI("Poslední"),
    NASLEDNIK("Následník"),
    PREDCHUDCE("Předchůdce"),
    AKTUALNI("Aktuální"),
    GENERUJ("Generuj"),
    ZRUS("Zruš"),
    ULOZ("Ulož"),
    NACTI("Načti"),
    DIALOG_VLOZENI("Vložení"),
    KOMPONENT_DIALOGU_VLOZENI("Vytvořte vlastní měření"),
    ID_SENZORU("Identifikátor:"),
    SPOTREBA_VT("Spotřeba VT:"),
    SPOTREBA_NT("Spotřeba NT:"),
    SPOTREBA_M3("Spotřeba m^3:"),
    KALENDAR("Kalendář:"),
    TLACITKO_FAJN("Fajn"),
    TLACITKO_ZRUSIT("Zrušit"),
    NAPOVEDA_DIALOGU_GENERATORU("10"),
    TITULEK_DIALOGU_GENERATORU("Generátor"),
    ZAHLAVI_DIALOGU_GENERATORU("Generování Měření"),
    KONTEXT_DIALOGU_GENERATORU("Zadejte celočíselný počet:"),
    SPOTREBA_MAX("Max"),
    SPOTREBA_PRUMER("Průměr"),
    SPOTREBA_DEN("Den"),
    DIALOG_SPOTREBA("Výpočet spotřeby"),
    KOMPONENT_DIALOGU_SPOTREBA("Spotřeba"),
    DATUM_OD("Datum od:"),
    DATUM_DO("Datum do:");

    private final String nadpis;

    Titulek(String nadpis) { this.nadpis = nadpis; }

    public String getNadpis() { return nadpis; }
}

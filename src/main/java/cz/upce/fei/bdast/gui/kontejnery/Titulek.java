package cz.upce.fei.bdast.gui.kontejnery;

import javafx.scene.control.Button;

/**
 * Tento výčtový typ definuje konstanty reprezentující titulky pro tlačítka
 * ({@link Button}), které jsou používány v rámci této aplikace
 */
public enum Titulek {
    KOMPONENT_VLOZENI("Vložení"),
    VLOZ_PRVNI("První"),
    VLOZ_POSLEDNI("Poslední"),
    VLOZ_NASLEDNIKA("Následník"),
    VLOZ_PREDCHUDCE("Předchůdce"),
    KOMPONENT_ZPRISTUPNI("Zpřístupňování"),
    ZPRISTUPNI_PRVNI("První"),
    ZPRISTUPNI_POSLEDNI("Poslední"),
    ZPRISTUPNI_NASLEDNIKA("Následník"),
    ZPRISTUPNI_PREDCHUDCE("Předchůdce"),
    ZPRISTUPNI_AKTUALNI("Aktuální");

    private final String nadpis;

    Titulek(String nadpis) { this.nadpis = nadpis; }

    public String getNadpis() { return nadpis; }
}

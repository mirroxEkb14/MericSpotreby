package cz.upce.fei.bdats.data.vycty;

import cz.upce.fei.bdats.generator.MereniGenerator;

import java.util.Random;

/**
 * Tento výčtový typ uchovává jednotlivé typy senzorů.
 *
 * @author amirov 10/4/2023
 */
public enum TypSenzoru {
    ELEKTRIKA("Elektrika"),
    VODA("Voda");

    /**
     * Tyto dvě konstanty reprezentují unikátní identifikátory pro každý
     * typ senzorů, tj. pro snadnější generování náhodných prvků seznamu
     * v {@link MereniGenerator}u
     */
    public static final int ELEKTRIKA_ID = 1;
    public static final int VODA_ID = 2;

    private final String nazev;

    TypSenzoru(String nazev) { this.nazev = nazev; }

    public String getNazev() { return nazev; }

    /**
     * Získá náhodnou enum konstantu z tohoto výčtového typu
     * <p>
     * <b>Poznámka</b>: metoda se momentálně v rámci projektu nepoužívá
     *
     * @return náhodná hodnota tohoto vyčtového typy
     */
    public static TypSenzoru dejNahodnyTyp() {
        final Random nahodnost = new Random();
        final int delkaEnumu = TypSenzoru.values().length;
        return TypSenzoru.values()[nahodnost.nextInt(delkaEnumu)];
    }
}

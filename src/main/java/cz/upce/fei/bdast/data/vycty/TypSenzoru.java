package cz.upce.fei.bdast.data.vycty;

import cz.upce.fei.bdast.generator.MereniGenerator;

/**
 * Tento výčtový typ uchovává jednotlivé typy senzorů.
 *
 * @author amirov 10/4/2023
 */
public enum TypSenzoru {
    ELEKTRIKA,
    VODA;

    /**
     * Tyto dvě konstanty reprezentují unikátní identifikátory pro každý
     * typ senzorů, tj. pro snadnější generování náhodných prvků seznamu
     * v {@link MereniGenerator}u
     */
    public static final int ELEKTRIKA_ID = 1;
    public static final int VODA_ID = 2;

//    /**
//     * Získá náhodnou enum konstantu z tohoto výčtového typu
//     *
//     * @return náhodná hodnota tohoto vyčtového typy
//     */
//    public static TypSenzoru dejNahodnyTyp() {
//        final Random nahodnost = new Random();
//        final int delkaEnumu = TypSenzoru.values().length;
//        return TypSenzoru.values()[nahodnost.nextInt(delkaEnumu)];
//    }
}

package cz.upce.fei.bdast.generator;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.data.model.MereniElektrika;
import cz.upce.fei.bdast.data.model.MereniVoda;
import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;
import cz.upce.fei.bdast.data.vycty.TypSenzoru;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;

/**
 * Trída implementuje rozhraní {@link Generator} pro generování nějakého
 * počtu {@link Mereni}
 */
public final class MereniGenerator implements Generator<Mereni> {

    /**
     * Tyto konstanty se používají hlavně v rámci metody {@link MereniGenerator#dejNahodneDatum()}
     * pro generování náhodného data
     */
    private static final int POCET_DNU_V_ROCE = 365;
    private static final int PRVNI_DEN_V_MESICI = 1;
    private static final int NULOVA_HODINA = 0;
    private static final int NULOVA_MINUTA = 0;
    private static final Month PRVNI_MESIC_ROKU = Month.JANUARY;

    /**
     * Konstanta reprezentuje číslo, pomocí němuž v metodě {@link MereniGenerator#dejNahodneCislo()}
     * náhodné číslo se rozšíří do rozsahu od 0.0 (včetně) do 100.0 (vyloučeno)
     */
    private static final double CINITEL = 100.0;
    /**
     * Konstanta vyjadřuje horní omezení pro možnost generování náhodných
     * unikátních identifikátoru jednotlivých senzorů
     */
    private static final int HORNI_MEZ_IDENTIFIKATORU = 100;

    @Override
    public void generuj(IAbstrDoubleList<Mereni> seznam, int pocet) {
        do {
            final int idSenzor = dejNahodnyUnikatniId(seznam);
            final LocalDateTime casMereni = dejNahodneDatum();

            final int nahodneCislo = Generator.super.dejNahodneCislo();
            switch (nahodneCislo) {
                case TypSenzoru.ELEKTRIKA_ID -> {
                    final double spotrebaVT = dejNahodneDesetinneCislo();
                    final double spotrebaNT = dejNahodneDesetinneCislo();
                    seznam.vlozPosledni(new MereniElektrika(
                            idSenzor, casMereni, spotrebaVT, spotrebaNT));
                }
                case TypSenzoru.VODA_ID -> {
                    final double spotrebaM3 = dejNahodneDesetinneCislo();
                    seznam.vlozPosledni(new MereniVoda(
                            idSenzor, casMereni, spotrebaM3));
                }
            }
        } while (pocet-- != Generator.KONTROLUJICI_NULA);
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Generuje náhodný neopakovatelný identifikátor (id) pro senzor
     * <p>
     * Krátký popis logiky:
     * <ul>
     * <li> V cyklu {@code do-while} se generuje náhodné číslo pro {@code idSenzor}
     * v rozmezí od 0 (včetně) do 100 (vyjma), dolní mezí je tedy 0 a horní
     * mezí je 99
     * <li> Dále metoda prochází seznam uvedený v argumentech a kontroluje, zda již
     * vygenerovaný "idSenzor" existuje u některého z existujících prvků, přičemž
     * cyklus pokračuje, dokud {@code jeUnikatni} zůstává {@code false}. Jakmile je
     * vygenerován unikátní "idSenzor", cyklus končí
     * </ul>
     *
     * @param seznam seznam prvků, pro který je potřeba zajistit, že hodnota
     *               {@code idSenzor} bude unikátní pro každý prvek v tomto seznamu
     *
     * @return unikátní id pro senzor
     */
    private int dejNahodnyUnikatniId(IAbstrDoubleList<Mereni> seznam) {
        final Random nahodnost = new Random();
        int idSenzor;
        boolean jeUnikatni;
        do {
            jeUnikatni = true;
            idSenzor = nahodnost.nextInt(HORNI_MEZ_IDENTIFIKATORU);
            for (Mereni mereni : seznam) {
                if (mereni.getIdSenzor() == idSenzor) {
                    jeUnikatni = false;
                    break;
                }
            }
        } while (!jeUnikatni);
        return idSenzor;
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Metoda {@link Random#nextDouble()} vygeneruje náhodné desetinné číslo typu
     * {@code double} v rozsahu od 0.0 (včetně) do 1.0 (vyloučeno), což je vlastně
     * pseudonáhodné číslo. Pak výsledek z předchozího kroku je násobeno číslem
     * 100.0, aby se toto náhodné číslo rozšíří do rozsahu od 0.0 (včetně) do
     * 100.0 (vyloučeno)
     *
     * @return náhodné desetinné číslo v rozsahu >=0 a <100
     */
    private double dejNahodneDesetinneCislo() {
        return new Random().nextDouble() * CINITEL;
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Generuje náhodné datum v rámci letošního roku
     * <p>
     * Den roku získá:
     * <ol>
     * <li> Pomocí metody {@link Math#random()}, vratí náhodnou hodnotu typu {@code double}
     * mezi 0,0 (včetně) a 1,0 (vyloučeno), což je vlastně pseudonáhodným číslem
     * <li> Vynásobí tuto náhodnou hodnotu číslem 365. Tím získá náhodnou hodnotu mezi 0,0
     * (včetně) a 365,0 (vyloučeno)
     * <li> Přetypuje tuto náhodnou hodnotu typu {@code double} na {@code int}. Tím efektivně
     * ořízne desetinnou část čísla a získá náhodné celé číslo mezi 0 (včetně) a 364 (včetně)
     * <li> Nakonec k tomuto výsledku přidá 1, aby posunul rozsah na 1 (včetně) až 365 (včetně),
     * což reprezentuje platný rozsah dnů v roce
     * </ol>
     * Náhodné datum získá:
     * <ol>
     * <li> {@code startOfYear} objekt reprezentuje 1. leden aktuálního roku v půlnoci (00:00:00).
     * Je instancí {@link LocalDateTime}
     * <li> volá se metoda {@link LocalDateTime#plusDays(long)} na tomto objektu, čímž přidává k
     * datu začátku roku počet dnů, který byl vygenerován v předchozím kroku ({@code denRoku}),
     * což vlastně způsobí posun v datu na požadovaný náhodný den v rámci aktuálního roku
     * </ol>
     *
     * @return objekt typu {@link LocalDateTime} reprezentující náhodné datum
     * letošního roku
     */
    private LocalDateTime dejNahodneDatum() {
        final int letostiRok = LocalDateTime.now().getYear();
        final int denRoku = 1 + (int) (Math.random() * POCET_DNU_V_ROCE);
        final LocalDateTime zacatekRoku = LocalDateTime.of(letostiRok,
                PRVNI_MESIC_ROKU, PRVNI_DEN_V_MESICI, NULOVA_HODINA, NULOVA_MINUTA);
        return zacatekRoku.plusDays(denRoku);
    }
}

package cz.upce.fei.bdast.spravce;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.data.vycty.Pozice;
import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Tento interfejs deklaruje metody pro implementaci všech příkazů v rámci
 * správy měření.
 * <p>
 * Implementuje rozhraní {@link Iterable}.
 *
 * @author amirov 10/9/2023
 */
public interface Ovladani extends Iterable<Mereni> {

    /**
     * Vloží nové měření do seznamu na příslušnou pozici
     * (první, poslední, předchůdce, následník).
     *
     * @param mereni nová položka, která bude přidána do seznamu.
     * @param pozice pozice podle výčtového typu {@link Pozice}, na
     *               kterou daná položka bude přidaná.
     */
    void vlozMereni(Mereni mereni, Pozice pozice);

    /**
     * Zpřístupní měření z požadované pozice
     * (první, poslední, předchůdce, následník, aktuální).
     *
     * @param pozice možná pozice, reprezentovaná v {@link Pozice}.
     *
     * @return vrací jednotlivé měření z uvedené pozice.
     */
    Mereni zpristupniMereni(Pozice pozice);

    /**
     * Odebere měření z požadované pozice
     * (první, poslední, předchůdce, následník, aktuální).
     *
     * @param pozice možná pozice podle {@link Pozice}.
     *
     * @return vrací odkaz na odebíraný objekt.
     */
    Mereni odeberMereni(Pozice pozice);

    /**
     * Umožňuje, aby se objekt stal cílem vylepšeného příkazu „for-each loop“.
     *
     * @return vrací iterátor nad prvky typu {@link Mereni}.
     */
    Iterator<Mereni> iterator();

    /**
     * Pomocí iterátoru vyhledá všechna měření daného senzoru v rámci
     * požadovaného dne.
     * <p>
     * Seznam je následně možné vypsat.
     *
     * @param idSenzoru unikátní identifikátor daného senzoru.
     * @param datum reprezentuje den, v jehož rámci proběhly jedtotlivá měření.
     *
     * @return vrací seznam nalezených prvků.
     */
    IAbstrDoubleList<Mereni> MereniDen(int idSenzoru, LocalDate datum);

    /**
     * Pomocí iterátoru vyhledá maximální spotřebu daného senzoru v rámci
     * požadovaného intervalu. Pro elektrický senzor je hodnota dána
     * součtem VT a NT.
     * <p>
     * Hodnota je následně zobrazena v GUI.
     *
     * @param idSenzoru id daného senzoru.
     * @param datumOd začátek intervalu.
     * @param datumDo konec intervalu.
     *
     * @return vrací maximální spotřebu senzoru v rámci určitého intervalu.
     */
    double maxSpotreba(int idSenzoru, LocalDateTime datumOd, LocalDateTime datumDo);

    /**
     * Pomocí iterátoru zjistí průměrnou spotřebu v rámci daného intervalu. Pro elektrický
     * senzor je hodnota dána součtem VT a NT.
     * <p>
     * Hodnota je následně zobrazena v GUI.
     *
     * @param idSenzoru id požadovaného senzoru.
     * @param datumOd den od.
     * @param datumDo den do.
     *
     * @return vrací průměrnou spotřebu senzoru v rámci požadovaného intervalu.
     */
    double prumerSpotreba(int idSenzoru, LocalDateTime datumOd, LocalDateTime datumDo);

    /**
     * Zruší všechny měření.
     */
    void zrus();
}

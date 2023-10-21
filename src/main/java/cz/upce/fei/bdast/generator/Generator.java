package cz.upce.fei.bdast.generator;

import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;

import java.util.Random;

/**
 * Funkcionální rozhraní deklaruje metody pro generování určetého
 * počtu prvků pro datovou strukturu obousměrný necyklicky
 * zřetězený lineární seznam {@link cz.upce.fei.bdast.kolekce.AbstrDoubleList}
 *
 * @param <T> generický typ (zástupce pro budoucí datový typ)
 */
@FunctionalInterface
public interface Generator<T> {

    /**
     * Dolní omezení pro generování náhodného čisla typu {@link Integer}
     */
    int DOLNI_OMEZENI = 1;
    /**
     * Horní omezení pro generování náhodného čisla typu {@link Integer}
     */
    int HORNI_OMEZENI_VCETNE = 1;
    /**
     * Konstanta reprezentuje počet druhů měření
     */
    int POCET_DRUHU_MERENI = 2;
    /**
     * Konstanta reprezentují hodnotu nula pro různé kontroly
     * ve třídě implementující toto rozhraní - {@link MereniGenerator}
     */
    int KONTROLUJICI_NULA = 0;

    /**
     * Metoda náhodně generuje data podle kapacity, uvedené v argumentu
     */
    void generuj(IAbstrDoubleList<T> seznam, int pocet);

    /**
     * Generuje a vrací náhodné číslo typu {@link Integer}
     *
     * @return náhodné číslo od 1 včetně do 3 nevčetně
     */
    default int dejNahodneCislo() {
        return new Random().nextInt(DOLNI_OMEZENI,
                POCET_DRUHU_MERENI + HORNI_OMEZENI_VCETNE);
    }
}

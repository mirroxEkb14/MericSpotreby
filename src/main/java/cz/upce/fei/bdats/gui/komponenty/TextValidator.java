package cz.upce.fei.bdats.gui.komponenty;

import java.util.function.Predicate;

/**
 * Funkční rozhraní reprezentuje predikát, tedy funkci, která provádí testování na vstupním
 * objektu a vrací pravdivostní hodnotu ({@code true} nebo {@code false}). {@link Predicate}
 * je často používán pro filtraci dat, vyhledávání a validaci různých podmínek
 */
@FunctionalInterface
public interface TextValidator<T> {

    /**
     * Je jediná abstraktní metoda v tomto rozhraní. Provádí validaci (testování)
     * vstupního objektu a určovat, zda je platný nebo ne
     */
    boolean jeValidni(T vstup);

    /**
     * Výchozí metoda (default method) v rozhraní. Jedná se o alias pro abstraktní metodu
     * {@code jeValidni}. Testuje vstupy
     */
    default boolean testuj(T vstup) {
        return jeValidni(vstup);
    }
}

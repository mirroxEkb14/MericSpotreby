package cz.upce.fei.bdats.gui.alerty;

import java.util.function.Consumer;

/**
 * Funkcionálních rozhraní {@link Consumer} reprezentuje operaci, která přijímá jeden
 * argument typu {@code T} a nevrací žádnou hodnotu. Hlavní metoda je {@code void accept(T t)},
 * která provádí operaci nad vstupním argumentem {@code t}. Hlavním účelem je umožnit
 * aplikaci provádět určité akce nebo operace nad daty typu {@code T}, jako je třeba
 * tisk, zpracování, ukládání, nebo jakákoliv jiná manipulace s daty, která neprovádí
 * návratovou hodnotu
 * <p>
 * U této implementace dochází k logování chyb a vytvočení aleru s informací o aktuálním prvku
 * seznamu
 */
@FunctionalInterface
public interface AlertKonzument<T> {
    void akceptovat(T t);
}

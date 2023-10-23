package cz.upce.fei.bdast.gui.dialogy;

import java.util.function.Function;

/**
 * Funkcionální rozhraní {@link Function} definuje jedinou metodu {@link Function#apply(Object)},
 * která přijímá vstupní hodnotu typu {@code T} a vrací výstupní hodnotu typu {@code R}.
 * Toto rozhraní je používáno pro reprezentaci funkce, která provádí transformaci
 * vstupu na výstup:
 * <ul>
 * <li> <b>T</b> značí typ vstupního argumentu (nebo vstupu), který přijímá funkce
 * <li> <b>R</b> značí typ výstupní hodnoty (nebo výstupu), který funkce vrací
 * </ul>
 */
@FunctionalInterface
public interface TypSenzoruValidator<T, R> {
    R aplikovat(T t);
}

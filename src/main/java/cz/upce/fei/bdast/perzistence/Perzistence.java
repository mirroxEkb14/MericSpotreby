package cz.upce.fei.bdast.perzistence;

import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;

import java.io.IOException;

/**
 * Toto rozhraní definuje dvě metody pro ukládání a načítání dat z/do binárního formátu
 */
public interface Perzistence<T> {

    /**
     * Má za úkol uložit data z abstraktního seznamu {@link IAbstrDoubleList} do binárního formátu
     *
     * @param seznam parametr metody, což je seznam, který obsahuje data, která budou uložena
     *
     * @throws IOException může se vystavit při práci se soubory (zachycení potenciálních chyb při
     * vstupu/výstupu)
     */
    void ulozBin(IAbstrDoubleList<T> seznam) throws IOException;

    /**
     * Má za úkol načíst data z binárního formátu do abstraktního seznamu {@link IAbstrDoubleList}
     *
     * @param seznam parametr metody, což je seznam, do kterého budou načtena data
     *
     * @return vrací abstraktní seznam, který obsahuje načtená data
     *
     * @throws IOException může se vystavit při práci s binárním souborem
     */
    IAbstrDoubleList<T> nactiBin(IAbstrDoubleList<T> seznam) throws IOException;
}

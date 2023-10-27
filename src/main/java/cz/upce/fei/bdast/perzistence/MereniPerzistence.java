package cz.upce.fei.bdast.perzistence;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

/**
 * Tato třída je implementací rozhraní {@link Perzistence}, které umožňuje ukládat a načítat
 * objekty typu {@link Mereni} do/z binárního souboru
 */
public final class MereniPerzistence implements Perzistence<Mereni> {

    /**
     * Konstanta určuje relativní cestu k binárnímu souboru
     */
    private final String CESTA_BIN = "src/main/java/cz/upce/fei/bdast/util/data.bin";

    /**
     * Popis logiky:
     * <ol>
     * <li> Používá třídu {@link ObjectOutputStream} k zápisu dat do binárního souboru na dané cestě,
     * která je reprezentována konstantou {@link MereniPerzistence#CESTA_BIN}. Binární soubor bude
     * obsahovat serializované objekty
     * <li> Použije {@link Objects#requireNonNull(Object)} k zajištění, že seznam není
     * {@code null}, což je důležitou kontrolou, aby se zabránilo zápisu {@code null} objektů do souboru
     * <li> Zapisuje do souboru velikost seznamu pomocí {@link ObjectOutputStream#writeInt(int)}, což
     * umožní později načíst správný počet položek při čtení ze souboru
     * <li> Používá iterator k průchodu seznamem {@code seznam} a zápisu jednotlivých objektů. Pro každý
     * objekt v seznamu používá {@link ObjectOutputStream#writeObject(Object)} k jeho serializaci a
     * zápisu do souboru
     * <li> Zachytává výjimku typu {@link IOException}, pokud během zápisu do souboru dojde k chybě. V
     * takovém případě výjimku zachytí a předá ji výše jako novou {@link IOException}, čímž zajišťuje,
     * že chyby během zápisu jsou řádně zpracovány a předány zpět volajícímu kódu
     * </ol>
     */
    @Override
    public void ulozBin(IAbstrDoubleList<Mereni> seznam) throws IOException {
        try (ObjectOutputStream vystup = new ObjectOutputStream(new FileOutputStream(CESTA_BIN))) {
            Objects.requireNonNull(seznam);

            vystup.writeInt(seznam.velikost());
            Iterator<Mereni> it = seznam.iterator();
            while (it.hasNext()) {
                vystup.writeObject(it.next());
            }
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    /**
     * Popis logiky:
     * <ol>
     * <li> Používá třídu {@link ObjectInputStream} k načítání dat ze souboru na dané cestě, která je
     * reprezentována konstantou {@link MereniPerzistence#CESTA_BIN}. Tento soubor obsahuje
     * serializované objekty
     * <li> Použije {@link Objects#requireNonNull(Object)} k zajištění, že seznam není {@code null},
     * aby se zajistilo, že načtená data budou správně uložena do seznamu
     * <li> Volá metodu {@link IAbstrDoubleList#zrus()} na seznamu, což vymaže všechny existující položky
     * v seznamu, čímž se připraví seznam na příjem nových dat
     * <li> Získává počet položek uložených v binárním souboru pomocí {@link ObjectInputStream#readInt()}.
     * Toto číslo určuje, kolik položek bude následovat
     * <li> Používá cyklus {@code for} k načítání a vkládání jednotlivých objektů do seznamu. Pro každou
     * položku volá metodu {@link IAbstrDoubleList#vlozPosledni(Object)}, což načte objekt ze souboru a
     * vloží ho na konec seznamu
     * <li> Zachycuje výjimky typu {@link IOException} a {@link ClassNotFoundException}, které mohou nastat
     * během operace načítání. V případě výjimky zachycuje tuto výjimku a předá ji výše jako novou {@link IOException},
     * čímž zajišťuje, že chyby během operace načítání jsou řádně zpracovány a předány zpět volajícímu kódu
     * </ol>
     */
    @Override
    public IAbstrDoubleList<Mereni> nactiBin(IAbstrDoubleList<Mereni> seznam) throws IOException {
        try (ObjectInputStream vstup = new ObjectInputStream(new FileInputStream(CESTA_BIN))) {
            Objects.requireNonNull(seznam);

            seznam.zrus();
            final int pocet = vstup.readInt();
            for (int i = 0; i < pocet; i++) {
                seznam.vlozPosledni((Mereni) vstup.readObject());
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        }
        return seznam;
    }
}

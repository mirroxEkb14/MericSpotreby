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

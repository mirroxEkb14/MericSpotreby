package cz.upce.fei.bdast.data.model;

import cz.upce.fei.bdast.data.vycty.TypSenzoru;

import java.time.LocalDateTime;

/**
 * Abstraktní třída je rodičem pro potomky:
 * <ol>
 * <li> {@link MereniElektrika}
 * <li> {@link MereniVoda}
 * </ol>
 *
 * @author amirov 10/4/2023
 */
public abstract class Mereni {

    /**
     * Unikátní identifikátor jednotlivého senzoru
     */
    private final int idSenzor;
    /**
     * Typ daného senzoru, reprezentovaný ve výčtovém typu {@link TypSenzoru}
     */
    private final TypSenzoru typSenzoru;
    /**
     * Čas měření spotřeby
     * <p>
     * Vždy celá hodina
     */
    private final LocalDateTime casMereni;

    public Mereni(int idSenzor, TypSenzoru typSenzoru, LocalDateTime casMereni) {
        this.idSenzor = idSenzor;
        this.typSenzoru = typSenzoru;
        this.casMereni = casMereni;
    }

    public int getIdSenzor() { return idSenzor; }

//    public TypSenzoru getTypSenzoru() { return typSenzoru; }

    public LocalDateTime getCasMereni() { return casMereni; }

    @Override
    public String toString() {
        return "Mereni{" +
                "idSenzor=" + idSenzor +
                ", typSenzoru=" + typSenzoru +
                ", casMereni=" + casMereni +
                '}';
    }
}

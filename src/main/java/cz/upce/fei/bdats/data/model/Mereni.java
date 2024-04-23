package cz.upce.fei.bdats.data.model;

import cz.upce.fei.bdats.data.vycty.TypSenzoru;

import java.io.ObjectStreamClass;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstraktní třída je rodičem pro potomky:
 * <ol>
 * <li> {@link MereniElektrika}
 * <li> {@link MereniVoda}
 * </ol>
 *
 * @author amirov 10/4/2023
 */
public abstract class Mereni implements Serializable  {

    @Serial
    private static final long serialVersionUID = ObjectStreamClass.lookup(Mereni.class).getSerialVersionUID();

    /**
     * Vlastní formát pro zobrazení {@link LocalDateTime} u přektyté metody {@link Object#toString()}
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
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

    public LocalDateTime getCasMereni() { return casMereni; }

    public String getZformatovanyCasMereni() { return casMereni.format(formatter); }

    @Override
    public String toString() {
        return "Mereni{" +
                "formatter=" + formatter +
                ", idSenzor=" + idSenzor +
                ", typSenzoru=" + typSenzoru +
                ", casMereni=" + casMereni +
                '}';
    }
}

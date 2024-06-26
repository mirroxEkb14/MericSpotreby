package cz.upce.fei.bdats.data.model;

import cz.upce.fei.bdats.data.vycty.TypSenzoru;

import java.time.LocalDateTime;

/**
 * Tato třída reprezentuje konkrétní měření. Je potomkem abstraktní třídy {@link Mereni}.
 *
 * @author amirov 10/4/2023
 */
public final class MereniVoda extends Mereni {

    /**
     * Spotřeba vody v metrech na třetí
     */
    private final double spotrebaM3;

    public MereniVoda(int idSenzor, LocalDateTime casMereni, double spotrebaM3) {
        super(idSenzor, TypSenzoru.VODA, casMereni);

        this.spotrebaM3 = spotrebaM3;
    }

    public double getSpotrebaM3() { return spotrebaM3; }

    @Override
    public String toString() {
        return "Voda: " +
                "id(" + super.getIdSenzor() + "), " +
                "datum(" + super.getZformatovanyCasMereni() + "), " +
                "spotřebaM3(" + spotrebaM3 + ")";
    }
}

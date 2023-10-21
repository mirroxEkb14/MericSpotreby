package cz.upce.fei.bdast.data.model;

import cz.upce.fei.bdast.data.vycty.TypSenzoru;

import java.time.LocalDateTime;

/**
 * Tato třída reprezentuje konkrétní měření. Je potomkem abstraktní třídy {@link Mereni}.
 *
 * @author amirov 10/4/2023
 */
public final class MereniElektrika extends Mereni {

    /**
     * Spotřeba vysokého (denního) tarifu elektřiny
     */
    private final double spotrebaVT;
    /**
     * Spotřeba nočního tarifu elektřiny
     */
    private final double spotrebaNT;

    public MereniElektrika(int idSenzor, LocalDateTime casMereni, double spotrebaVT, double spotrebaNT) {
        super(idSenzor, TypSenzoru.ELEKTRIKA, casMereni);

        this.spotrebaVT = spotrebaVT;
        this.spotrebaNT = spotrebaNT;
    }

    public double getSpotrebaVT() { return spotrebaVT; }

    public double getSpotrebaNT() { return spotrebaNT; }
}

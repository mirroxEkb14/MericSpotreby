package cz.upce.fei.bdast.gui.alerty;

import javafx.scene.control.Alert;

/**
 * Třída představuje vlastní implementaci dialogového okna (alertu), který slouží
 * k zobrazování informačních zpráv uživatelům
 */
public class SpotrebaAlert extends Alert {

    /**
     * Proměnná obsahuje konstantní řetězec, který bude použit jako titulek pro dialogové okno
     */
    private final String TITULEK = "Informační Alert";

    /**
     * Konstruktor přijímá dvě řetězce jako argumenty a nastaví typ alertu na
     * {@link AlertType#INFORMATION}, což znamená, že dialog bude mít ikonu
     * informace a modrá barva
     *
     * @param zprava bude zobrazen v těle dialogového okna
     * @param zahlavi bude zobrazeno jako nadpis (header) dialogového okna
     */
    public SpotrebaAlert(String zprava, String zahlavi) {
        super(AlertType.INFORMATION);

        this.setTitle(TITULEK);
        this.setHeaderText(zahlavi);
        this.setContentText(zprava);
    }

    /**
     * Statická metoda slouží pro zobrazování dialogových oken s informacemi
     *
     * @param zprava text informační zprávy
     * @param zahlavi nadpis okna
     */
    public static void zobraziSpotrebuAlert(String zprava, String zahlavi) {
        final SpotrebaAlert alert = new SpotrebaAlert(zprava, zahlavi);
        alert.showAndWait();
    }
}

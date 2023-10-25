package cz.upce.fei.bdast.gui.komponenty;

import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.alerty.ChybovaZprava;
import cz.upce.fei.bdast.gui.alerty.ErrorAlert;
import cz.upce.fei.bdast.gui.dialogy.DialogSpotreba;
import cz.upce.fei.bdast.gui.dialogy.TypSpotreby;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

/**
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s
 * tlačítky ({@link Button}) pro výpočet spotřeb jednotlivých měření
 * <p>
 * Třída je Singleton
 */
public final class KomponentSpotreba extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnSpotrebaMax, btnSpotrebaPrumer, btnSpotrebaDen;

    private static KomponentSpotreba instance;

    /**
     * Tovární metoda (factory method) vracející existující nebo nově vytvořenou instanci
     * dané třídy
     */
    public static KomponentSpotreba getInstance() {
        if (instance == null)
            instance = new KomponentSpotreba();
        return instance;
    }

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    private KomponentSpotreba() {
        this.btnSpotrebaMax = new Tlacitko(
                Titulek.SPOTREBA_MAX.getNadpis());
        this.btnSpotrebaMax.setDisable(true);
        this.btnSpotrebaMax.setOnAction(actionEvent -> nastavSpotrebaMax());

        this.btnSpotrebaPrumer = new Tlacitko(
                Titulek.SPOTREBA_PRUMER.getNadpis());
        this.btnSpotrebaPrumer.setDisable(true);
        this.btnSpotrebaPrumer.setOnAction(actionEvent -> nastavSpotrebaPrumer());

        this.btnSpotrebaDen = new Tlacitko(
                Titulek.SPOTREBA_DEN.getNadpis());
        this.btnSpotrebaDen.setDisable(true);
        this.btnSpotrebaDen.setOnAction(actionEvent -> nastavSpotrebaDen());

        nastavKomponentSpotreba();
    }

    private void nastavKomponentSpotreba() {
        this.setText(Titulek.KOMPONENT_SPOTREBA.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(btnSpotrebaMax, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnSpotrebaPrumer, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnSpotrebaDen, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        GridPane.setColumnSpan(btnSpotrebaDen, MrizkovyPanel.ROZPETI_SLOUPCU);
        return gridPane;
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#SPOTREBA_MAX}
     */
    private void nastavSpotrebaMax() {
        new DialogSpotreba(TypSpotreby.MAX).showAndWait();
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#SPOTREBA_PRUMER}
     */
    private void nastavSpotrebaPrumer() {
        ErrorAlert.nahlasErrorLog(ChybovaZprava.NEDOSTUPNI.getZprava());
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#SPOTREBA_DEN}
     */
    private void nastavSpotrebaDen() {
        ErrorAlert.nahlasErrorLog(ChybovaZprava.NEDOSTUPNI.getZprava());
    }

    public boolean jeVypnutoBtnSpotrebaMax() { return btnSpotrebaMax.isDisabled(); }

    public boolean jeVypnutoBtnSpotrebaPrumer() { return btnSpotrebaPrumer.isDisabled(); }

    public boolean jeVypnutoBtnSpotrebaDen() { return btnSpotrebaDen.isDisabled(); }

// <editor-fold defaultstate="collapsed" desc="Přepínače">
    public void zapniBtnSpotrebaMax() { btnSpotrebaMax.setDisable(false); }

    public void vypniBtnSpotrebaMax() { btnSpotrebaMax.setDisable(true); }

    public void zapniBtnSpotrebaPrumer() { btnSpotrebaPrumer.setDisable(false); }

    public void vypniBtnSpotrebaPrumer() { btnSpotrebaPrumer.setDisable(true); }

    public void zapniBtnSpotrebaDen() { btnSpotrebaDen.setDisable(false); }

    public void vypniBtnSpotrebaDen() { btnSpotrebaDen.setDisable(true); }
// </editor-fold>
}

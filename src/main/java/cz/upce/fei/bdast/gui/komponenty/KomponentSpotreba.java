package cz.upce.fei.bdast.gui.komponenty;

import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.alerty.ChybovaZprava;
import cz.upce.fei.bdast.gui.alerty.ErrorAlert;
import cz.upce.fei.bdast.gui.alerty.SpotrebaAlert;
import cz.upce.fei.bdast.gui.dialogy.DialogSpotreba;
import cz.upce.fei.bdast.gui.dialogy.TypSpotreby;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import cz.upce.fei.bdast.spravce.SpravceMereni;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
     * Definuje anonymní třídu (lambda výraz). Lambda výraz zjišťuje, zda je vstupní řetězec
     * {@code t} prázdný. Pak provádí konverzi a pokud dojde k chybě, vratí {@code false.}
     * Očekává se platné celočíselné číslo
     */
    private final TextValidator<String> validatorCelychCisel = t -> {
        if (t.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(t);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    };

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
     * <p>
     * Popis logiky:
     * <ol>
     * <li> Vytvoří nový dialogový okno pro nastavení maximální spotřeby s předáním typu spotřeby a
     * zobrazí dialogové okno
     * <li> Z dialogového komponentu získá text z pole {@link TextField} s id senzoru
     * <li> Pak se používá validátor {@code validatorCelychCisel}, aby se ověřilo, zda vstup (id senzoru)
     * je platné celočíselné číslo a konvertuje platný vstup na celé číslo
     * <li> Vytváří objekty {@link LocalDateTime} pro datum od a datum do na základě dat vybraných v
     * dialogovém komponentu
     * <li> Pak získá výslednou maximální spotřebu pomocí {@link SpravceMereni#maxSpotreba(int, LocalDateTime, LocalDateTime)}
     * <li> Pokračuje k ověření výsledku: pokud výsledek je {@link Double#MIN_VALUE}, to znamená, že pro
     * tuto kombinaci parametrů neexistují záznamy a zobrazí se informační alert s chybovou zprávou
     * <li> Na konci zobrazí informační alert s výsledkem maximální spotřeby
     * <li> Pokud ale validace id senzoru neuspěje, zobrazí se chybový alert
     * </ol>
     */
    private void nastavSpotrebaMax() {
        new DialogSpotreba(TypSpotreby.MAX).showAndWait();

        final KomponentMaxSpotreba dialogovyKomponent = (KomponentMaxSpotreba) DialogSpotreba.getDialogovyKomponent();
        final String vstup = dialogovyKomponent.getTfIdSenzoru().getText();
        if (validatorCelychCisel.testuj(vstup)) {
            final int idSenzoru = Integer.parseInt(vstup);
            final LocalDateTime datumOd = LocalDateTime.of(
                    dialogovyKomponent.getDpDatumOd().getValue(), LocalTime.MIDNIGHT);
            final LocalDateTime datumDo = LocalDateTime.of(
                    dialogovyKomponent.getDpDatumDo().getValue(), LocalTime.MIDNIGHT);

            final double pozadovanyId = SpravceMereni.getInstance().maxSpotreba(idSenzoru, datumOd, datumDo);
            if (pozadovanyId == Double.MIN_VALUE) {
                SpotrebaAlert.zobraziSpotrebuAlert(Titulek.ZPRAVA_ALERT_SPOTREBA_NAX.getNadpis(),
                        Titulek.ZAHLAVI_ALERTU_SPOTREBA_MAX.getNadpis());
                return;
            }
            SpotrebaAlert.zobraziSpotrebuAlert(String.valueOf(pozadovanyId), Titulek.ZAHLAVI_ALERTU_SPOTREBA_MAX.getNadpis());
            return;
        }
        ErrorAlert.nahlasErrorLog(ChybovaZprava.MAXIMALNI_SPOTREBA.getZprava());
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

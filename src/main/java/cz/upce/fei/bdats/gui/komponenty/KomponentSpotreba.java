package cz.upce.fei.bdats.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdats.data.model.Mereni;
import cz.upce.fei.bdats.gui.Titulek;
import cz.upce.fei.bdats.gui.alerty.ChybovaZprava;
import cz.upce.fei.bdats.gui.alerty.ErrorAlert;
import cz.upce.fei.bdats.gui.alerty.SpotrebaAlert;
import cz.upce.fei.bdats.gui.dialogy.DialogSpotreba;
import cz.upce.fei.bdats.gui.dialogy.TypSpotreby;
import cz.upce.fei.bdats.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdats.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdats.gui.kontejnery.Tlacitko;
import cz.upce.fei.bdats.kolekce.IAbstrDoubleList;
import cz.upce.fei.bdats.spravce.SpravceMereni;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
// </editor-fold>

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

    private final String ODRADKOVANI = "\n";

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
     * <p>
     * Popis logiky:
     * <ol>
     * <li> Vytváří nový dialog typu {@link DialogSpotreba} s argumentem {@link TypSpotreby#PRUMER} a zobrazí
     * ho voláním {@link Dialog#showAndWait()}. Tento dialog slouží k zadávání parametrů pro výpočet průměrné
     * spotřeby
     * <li> Po zavření dialogu:
     *      <ol>
     *      <li> Získává dialogový komponent {@link KomponentPrumerSpotreba} z dialogu pro získání dat z dialogu
     *      <li> Získává textový vstup z textového pole {@code tfIdSenzoru} v dialogovém komponentu, které obsahuje
     *      identifikátor senzoru
     *      <li> Validuje vstup od uživatele pomocí {@link TextValidator}. Pokud vstup odpovídá očekávanému
     *      celočíselnému formátu, pokračuje v dalším zpracování:
     *          <ol>
     *          <li> Převádí textový vstup na celočíselný identifikátor senzoru {@code idSenzoru}
     *          <li> Získává data o datumech od a do z dialogového komponentu {@link KomponentPrumerSpotreba} a
     *          konvertuje je na {@link LocalDateTime}
     *          <li> Volá metodu {@link SpravceMereni#prumerSpotreba(int, LocalDateTime, LocalDateTime)} ze správce s
     *          předanými parametry (identifikátor senzoru a datumy), která provádí výpočet průměrné spotřeby
     *          <li> Pokud byla průměrná spotřeba úspěšně získána:
     *              <ol>
     *              <li> Zobrazí průměrnou spotřebu jako hlášku (alert). V případě chyby (například pokud průměrná
     *              spotřeba nemůže být spočítána) metoda zobrazí chybové hlášení (alert)
     *              </ol>
     *          </ol>
     *      <li> Pokud dojde k nějaké neočekávané chybě nebo neplatnému vstupu, zobrazí jeden z druhů chybového hlášení,
     *      čímž zaznamenává chybu a zobrazuje uživateli chybové hlášení
     *      </ol>
     * </ol>
     */
    private void nastavSpotrebaPrumer() {
        new DialogSpotreba(TypSpotreby.PRUMER).showAndWait();

        final KomponentPrumerSpotreba dialogovyKomponent = (KomponentPrumerSpotreba) DialogSpotreba.getDialogovyKomponent();
        final String vstup = dialogovyKomponent.getTfIdSenzoru().getText();
        if (validatorCelychCisel.testuj(vstup)) {
            final int idSenzoru = Integer.parseInt(vstup);
            final LocalDateTime datumOd = LocalDateTime.of(
                    dialogovyKomponent.getDpDatumOd().getValue(), LocalTime.MIDNIGHT);
            final LocalDateTime datumDo = LocalDateTime.of(
                    dialogovyKomponent.getDpDatumDo().getValue(), LocalTime.MIDNIGHT);

            final double pozadovanyId = SpravceMereni.getInstance().prumerSpotreba(idSenzoru, datumOd, datumDo);
            if (pozadovanyId == Double.MIN_VALUE) {
                SpotrebaAlert.zobraziSpotrebuAlert(Titulek.ZPRAVA_ALERT_SPOTREBA_PRUMER.getNadpis(),
                        Titulek.ZAHLAVI_ALERTU_SPOTREBA_PRUMER.getNadpis());
                return;
            }
            SpotrebaAlert.zobraziSpotrebuAlert(String.valueOf(pozadovanyId), Titulek.ZAHLAVI_ALERTU_SPOTREBA_PRUMER.getNadpis());
            return;
        }
        ErrorAlert.nahlasErrorLog(ChybovaZprava.PRUMER_SPOTREBA.getZprava());
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#SPOTREBA_DEN}
     * <p>
     * Popis logiky:
     * <ol>
     * <li> Vytváří nový dialog typu {@link DialogSpotreba} s argumentem {@link TypSpotreby#DEN} a zobrazí ho voláním
     * {@link Dialog#showAndWait()}, který slouží k zadávání parametrů pro výpočet spotřeby pro konkrétní den
     * <li> Po zavření dialogu:
     *      <ol>
     *      <li> Získává dialogový komponent {@link KomponentDenSpotreba} z dialogu pro získání dat z dialogu
     *      <li> Získává textový vstup z textového pole {@code tfIdSenzoru} v dialogovém komponentu, obsahující
     *      identifikátor senzoru
     *      <li> Validuje vstup od uživatele pomocí {@link TextValidator}. Pokud vstup odpovídá očekávanému
     *      celočíselnému formátu, pokračuje v dalším zpracování:
     *          <ol>
     *          <li> Převádí textový vstup na celočíselný identifikátor senzoru {@code idSenzoru}
     *          <li> Získává datum z dialogového komponentu pomocí {@link KomponentDenSpotreba#getDatum()}. Datum
     *          reprezentuje konkrétní den, pro který se má provést výpočet spotřeby
     *          <li> Volá metodu {@link SpravceMereni#mereniDen(int, LocalDate)} ze správce s předanými parametry
     *          (identifikátor senzoru a datum), která provádí výpočet spotřeby pro konkrétní den
     *          <li> V případě, že seznam měření je prázdný:
     *              <ol>
     *              <li> Zobrazí chybovou zprávu, že nejsou k dispozici žádná data
     *              </ol>
     *          <li> Pokud byla spotřeba úspěšně získána, zobrazí výsledky v podobě textového řetězce, obsahující data o
     *          měřeních pro daný den
     *          </ol>
     *      <li> Pokud vstup nebyl validní, metoda také zaznamená chybovou zprávu
     *      </ol>
     * </ol>
     */
    private void nastavSpotrebaDen() {
        new DialogSpotreba(TypSpotreby.DEN).showAndWait();

        final KomponentDenSpotreba dialogovyKomponent = (KomponentDenSpotreba) DialogSpotreba.getDialogovyKomponent();
        final String vstup = dialogovyKomponent.getTfIdSenzoru().getText();
        if (validatorCelychCisel.testuj(vstup)) {
            final int idSenzoru = Integer.parseInt(vstup);
            final LocalDate datum = dialogovyKomponent.getDatum().getValue();

            final IAbstrDoubleList<Mereni> seznamMereni = SpravceMereni.getInstance().mereniDen(idSenzoru, datum);
            if (seznamMereni.jePrazdny()) {
                ErrorAlert.nahlasErrorLog(ChybovaZprava.DEN_SPOTREBA_ZADNE_PRVKY.getZprava());
                return;
            }
            final StringBuilder sb = new StringBuilder();
            for (Mereni mereni : seznamMereni) {
                sb.append(mereni);
                sb.append(ODRADKOVANI);
            }
            SpotrebaAlert.zobraziSpotrebuAlert(sb.toString(), Titulek.ZAHLAVI_ALERTU_SPOTREBA_DEN.getNadpis());
        }
        ErrorAlert.nahlasErrorLog(ChybovaZprava.DEN_SPOTREBA_SPATNA_DATA.getZprava());
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

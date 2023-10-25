package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.alerty.ChybovaZprava;
import cz.upce.fei.bdast.gui.alerty.ErrorAlert;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import cz.upce.fei.bdast.gui.koreny.SeznamPanel;
import cz.upce.fei.bdast.spravce.SpravceMereni;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link javafx.scene.control.TitledPane} s tlačítky
 * ({@link javafx.scene.control.Button}) pro uložení a načtení dat do a z souboru
 * <p>
 * Třída je Singleton
 */
public final class KomponentSouboru extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnUloz, btnNacti;

    private static KomponentSouboru instance;

    /**
     * Tovární metoda (factory method) vracející existující nebo nově vytvořenou instanci
     * dané třídy
     */
    public static KomponentSouboru getInstance() {
        if (instance == null)
            instance = new KomponentSouboru();
        return instance;
    }

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    private KomponentSouboru() {
        this.btnUloz = new Tlacitko(
                Titulek.ULOZ.getNadpis());
        this.btnUloz.setDisable(true);
        this.btnUloz.setOnAction(actionEvent -> nastavUdalostUloz());

        this.btnNacti = new Tlacitko(
                Titulek.NACTI.getNadpis());
        this.btnNacti.setDisable(true);
        this.btnNacti.setOnAction(actionEvent -> nastavUdalostNacti());

        nastavKomponentSouboru();
    }

    private void nastavKomponentSouboru() {
        this.setText(Titulek.KOMPONENT_SOUBORU.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(btnUloz, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnNacti, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        return gridPane;
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#ULOZ}
     */
    private void nastavUdalostUloz() {
        if (SpravceMereni.getInstance().zalohuj()) {
            zapniBtnNacti();
            return;
        }
        ErrorAlert.nahlasErrorLog(ChybovaZprava.ZALOHOVANI.getZprava());
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#NACTI}
     */
    private void nastavUdalostNacti() {
        if (SpravceMereni.getInstance().obnov()) {
            SeznamPanel.getInstance().obnovSeznam(
                    SpravceMereni.getInstance().dejDatovod());
            return;
        }
        ErrorAlert.nahlasErrorLog(ChybovaZprava.OBNOVENI.getZprava());
    }

    /**
     * Veřejná pomocní metoda
     * <p>
     * Ověří, zda je tlačítko {@code btnUloz} vypnuto/deaktivováno
     *
     * @return vrací {@code true}, pokud je vypnuto (disabled), v opačném případě {@code false}
     */
    public boolean jeVypnutoBtnUloz() { return btnUloz.isDisabled(); }

    /**
     * Veřejná pomocní metoda
     * <p>
     * Ověří, zda je tlačítko {@code btnNacti} vypnuto/deaktivováno
     *
     * @return vrací {@code true}, pokud je vypnuto (disabled), v opačném případě {@code false}
     */
    public boolean jeVypnutoBtnNacti() { return btnNacti.isDisabled(); }


// <editor-fold defaultstate="collapsed" desc="Přepínače">
    public void zapniBtnUloz() { btnUloz.setDisable(false); }

    public void vypniBtnUloz() { btnUloz.setDisable(true); }

    public void zapniBtnNacti() { btnNacti.setDisable(false); }

    public void vypniBtnNacti() { btnNacti.setDisable(true); }
// </editor-fold>
}

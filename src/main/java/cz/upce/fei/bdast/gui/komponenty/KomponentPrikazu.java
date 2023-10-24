package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s tlačítky
 * ({@link Button}) pro některé určité příkazy jako je generování prvků nebo
 * zrušení celého seznamu
 * <p>
 * Třída je Singleton
 */
public final class KomponentPrikazu extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnGeneruj, btnZrus;

    private static KomponentPrikazu instance;

    /**
     * Tovární metoda (factory method) vracející existující nebo nově vytvořenou instanci
     * dané třídy
     */
    public static KomponentPrikazu getInstance() {
        if (instance == null)
            instance = new KomponentPrikazu();
        return instance;
    }

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    private KomponentPrikazu() {
        this.btnGeneruj = new Tlacitko(
                Titulek.GENERUJ.getNadpis());

        this.btnZrus = new Tlacitko(
                Titulek.ZRUS.getNadpis());
        this.btnZrus.setDisable(true);

        nastavKomponentPrikazu();
    }

    private void nastavKomponentPrikazu() {
        this.setText(Titulek.KOMPONENT_PRIKAZU.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(btnGeneruj, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnZrus, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        return gridPane;
    }

    /**
     * Veřejná pomocní metoda
     * <p>
     * Ověří, zda je tlačítko {@code btnZrus} vypnuto/deaktivováno
     *
     * @return vrací {@code true}, pokud je vypnuto (disabled), v opačném případě {@code false}
     */
    public boolean jeVypnutoZrus() { return btnZrus.isDisabled(); }

// <editor-fold defaultstate="collapsed" desc="Přepínače">
    public void zapniBtnZrus() { btnZrus.setDisable(true); }

    public void vypniBtnZrus() { btnZrus.setDisable(false); }
// </editor-fold>
}

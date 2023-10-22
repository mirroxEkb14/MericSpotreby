package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Titulek;
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
 */
public final class KomponentPrikazu extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnGeneruj, btnZrus;

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    public KomponentPrikazu() {
        this.btnGeneruj = new Tlacitko(
                Titulek.GENERUJ.getNadpis());
        this.btnZrus = new Tlacitko(
                Titulek.ZRUS.getNadpis());

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

//<editor-fold defaultstate="collapsed" desc="Gettery">
    public Button getBtnGeneruj() { return btnGeneruj; }

    public Button getBtnZrus() { return btnZrus; }
//</editor-fold>
}

package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link javafx.scene.control.TitledPane} s tlačítky
 * ({@link javafx.scene.control.Button}) pro uložení a načtení dat do a z souboru
 */
public final class KomponentSouboru extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnUloz, btnNacti;

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    public KomponentSouboru() {
        this.btnUloz = new Tlacitko(
                Titulek.ULOZ.getNadpis());
        this.btnNacti = new Tlacitko(
                Titulek.NACTI.getNadpis());

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

//<editor-fold defaultstate="collapsed" desc="Gettery">
    public Button getBtnGeneruj() { return btnUloz; }

    public Button getBtnZrus() { return btnNacti; }
//</editor-fold>
}

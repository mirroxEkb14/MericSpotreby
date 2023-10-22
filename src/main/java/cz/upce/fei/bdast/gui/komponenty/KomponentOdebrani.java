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
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s
 * tlačítky ({@link Button}) pro odebrání prvků seznamu
 */
public final class KomponentOdebrani extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnOdeberPrvni, btnOdeberPosledni,
            btnOdeberNaslednika, btnOdeberPredchudce, btnOdeberAktualni;

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    public KomponentOdebrani() {
        this.btnOdeberPrvni = new Tlacitko(
                Titulek.PRVNI.getNadpis());
        this.btnOdeberPosledni = new Tlacitko(
                Titulek.POSLEDNI.getNadpis());
        this.btnOdeberNaslednika = new Tlacitko(
                Titulek.NASLEDNIK.getNadpis());
        this.btnOdeberPredchudce = new Tlacitko(
                Titulek.PREDCHUDCE.getNadpis());
        this.btnOdeberAktualni = new Tlacitko(
                Titulek.AKTUALNI.getNadpis());
        this.btnOdeberAktualni.setPrefWidth(MrizkovyPanel.PREFEROVANA_SIRKA_VELKEHO_TLACITKA);

        nastavKomponentOdebrani();
    }

    private void nastavKomponentOdebrani() {
        this.setText(Titulek.KOMPONENT_ODEBRANI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(btnOdeberPrvni, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnOdeberPosledni, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnOdeberNaslednika, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(btnOdeberPredchudce, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(btnOdeberAktualni, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        GridPane.setColumnSpan(btnOdeberAktualni, MrizkovyPanel.ROZPETI_SLOUPCU);
        return gridPane;
    }

//<editor-fold defaultstate="collapsed" desc="Gettery">
    public Button getBtnOdeberPrvni() { return btnOdeberPrvni; }

    public Button getBtnOdeberPosledni() { return btnOdeberPosledni; }

    public Button getBtnOdeberNaslednika() { return btnOdeberNaslednika; }

    public Button getBtnOdeberPredchudce() { return btnOdeberPredchudce; }

    public Button getBtnOdeberAktualni() { return btnOdeberAktualni; }
//</editor-fold>
}

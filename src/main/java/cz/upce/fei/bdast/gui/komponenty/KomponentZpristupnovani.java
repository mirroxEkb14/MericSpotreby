package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s
 * tlačítky ({@link Button}) pro zpřístupňování prvků seznamu
 */
public final class KomponentZpristupnovani extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnZpristupniPrvni, btnZpristupniPosledni,
            btnZpristupniNaslednika, btnZpristupniPredchudce, btnZpristupniAktualni;

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    public KomponentZpristupnovani() {
        this.btnZpristupniPrvni = new Tlacitko(
                Titulek.PRVNI.getNadpis());
        this.btnZpristupniPosledni = new Tlacitko(
                Titulek.POSLEDNI.getNadpis());
        this.btnZpristupniNaslednika = new Tlacitko(
                Titulek.NASLEDNIK.getNadpis());
        this.btnZpristupniPredchudce = new Tlacitko(
                Titulek.PREDCHUDCE.getNadpis());
        this.btnZpristupniAktualni = new Tlacitko(
                Titulek.AKTUALNI.getNadpis());
        this.btnZpristupniAktualni.setPrefWidth(MrizkovyPanel.PREFEROVANA_SIRKA_VELKEHO_TLACITKA);

        nastavKomponentZpristupnovani();
    }

    private void nastavKomponentZpristupnovani() {
        this.setText(Titulek.KOMPONENT_ZPRISTUPNI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(btnZpristupniPrvni, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnZpristupniPosledni, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnZpristupniNaslednika, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(btnZpristupniPredchudce, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(btnZpristupniAktualni, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        GridPane.setColumnSpan(btnZpristupniAktualni, MrizkovyPanel.ROZPETI_SLOUPCU);
        return gridPane;
    }

//<editor-fold defaultstate="collapsed" desc="Gettery">
    public Button getBtnZpristupniPrvni() { return btnZpristupniPrvni; }

    public Button getBtnZpristupniPosledni() { return btnZpristupniPosledni; }

    public Button getBtnZpristupniNaslednika() { return btnZpristupniNaslednika; }

    public Button getBtnZpristupniPredchudce() { return btnZpristupniPredchudce; }

    public Button getBtnZpristupniAktualni() { return btnZpristupniAktualni; }
//</editor-fold>
}

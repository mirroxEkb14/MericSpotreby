package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * Třída slouží k vytvoření komponenty pro zadávání dat o průměrné spotřebě. Obsahuje grafické prvky pro
 * zadávání identifikátoru senzoru a rozsahu dat, a je konfigurována pro zobrazení těchto prvků ve vhodném
 * rozložení. Také poskytuje metody {@code getter} pro získání hodnot těchto grafických prvků
 */
public final class KomponentPrumerSpotreba extends TitulkovyPanel
        implements PolozkaKomponenty {

    /**
     * Deklarace grafických prvků
     */
    private final TextField tfIdSenzoru;
    private final DatePicker dpDatumOd, dpDatumDo;
    private final Label lIdSenzoru, lDatumOd, lDatumDo;

    /**
     * Konstruktor inicializuje všechny výše uvedené grafické prvky. Používá k tomu
     * textová pole, výběry data a popisky a přiřazuje jim odpovídající hodnoty a texty
     */
    public KomponentPrumerSpotreba() {
        this.tfIdSenzoru = new TextField();
        this.dpDatumOd = dejMesicniKalendar();
        this.dpDatumDo = dejMesicniKalendar();
        this.lIdSenzoru = new Label(Titulek.ID_SENZORU.getNadpis());
        this.lDatumOd = new Label(Titulek.DATUM_OD.getNadpis());
        this.lDatumDo = new Label(Titulek.DATUM_DO.getNadpis());

        nastavKomponentPrumerSpotreba();
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Nastavuje text titulku a obsah této třídy
     */
    private void nastavKomponentPrumerSpotreba() {
        this.setText(Titulek.KOMPONENT_DIALOGU_SPOTREBA.getNadpis());
        this.setContent(dejGridPane());
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Vytváří a konfiguruje {@link GridPane}, což je kontejnerem pro uspořádání grafických prvků.
     * Následně přidává textová pole, výběry data a popisky do tohoto {@link GridPane} na konkrétní pozice
     */
    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(lIdSenzoru, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(tfIdSenzoru, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(lDatumOd, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(dpDatumOd, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(lDatumDo, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        gridPane.add(dpDatumDo, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        return gridPane;
    }

/**
 * Umožňují získat přístup k soukromým atributům (grafickým prvkům) třídy z jiných částí programu
 */
// <editor-fold defaultstate="collapsed" desc="Gettery">
    public TextField getTfIdSenzoru() { return tfIdSenzoru; }

    public DatePicker getDpDatumOd() { return dpDatumOd; }

    public DatePicker getDpDatumDo() { return dpDatumDo; }
// </editor-fold>
}

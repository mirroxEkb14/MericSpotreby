package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TitledPane;
// </editor-fold>

/**
 * Třída slouží k vytvoření komponenty pro zadávání dat o denní spotřebě. Obsahuje grafické prvky pro
 * zadávání identifikátoru senzoru a konkrétního data, a je konfigurována pro zobrazení těchto prvků
 * ve vhodném rozložení. Také poskytuje metody {@code getter} pro získání hodnot těchto grafických prvků
 */
public class KomponentDenSpotreba extends TitulkovyPanel
        implements PolozkaKomponenty {

    /**
     * Deklarace grafických prvků
     */
    private final TextField tfIdSenzoru;
    private final DatePicker datum;
    private final Label lIdSenzoru, lDatum;

    /**
     * Konstruktor inicializuje všechny výše uvedené grafické prvky. Používá k tomu
     * textové pole, výběr data a popisky a přiřazuje jim odpovídající hodnoty a texty
     */
    public KomponentDenSpotreba() {
        this.tfIdSenzoru = new TextField();
        this.datum = dejMesicniKalendar();
        this.lIdSenzoru = new Label(Titulek.ID_SENZORU.getNadpis());
        this.lDatum = new Label(Titulek.KONKRETNI_DEN.getNadpis());

        nastavKomponentDenSpotreba();
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Nastavuje text titulku a obsah třídy pomocí metod {@link TitledPane#setText(String)}
     * a {@link TitledPane#setContent(Node)}
     */
    private void nastavKomponentDenSpotreba() {
        this.setText(Titulek.KOMPONENT_DIALOGU_SPOTREBA.getNadpis());
        this.setContent(dejGridPane());
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Vytváří a konfiguruje {@link GridPane}, což je kontejner pro uspořádání grafických prvků.
     * Následně přidává textová pole, výběr data a popisky do tohoto kontejneru na konkrétní pozice
     */
    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(lIdSenzoru, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(tfIdSenzoru, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(lDatum, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(datum, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        return gridPane;
    }

/**
 * Umožňují získat přístup k soukromým atributům (grafickým prvkům) třídy z jiných částí programu
 */
// <editor-fold defaultstate="collapsed" desc="Gettery">
    public TextField getTfIdSenzoru() { return tfIdSenzoru; }

    public DatePicker getDatum() { return datum; }
// </editor-fold>
}

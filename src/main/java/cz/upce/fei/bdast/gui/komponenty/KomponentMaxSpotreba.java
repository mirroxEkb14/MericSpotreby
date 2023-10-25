package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.data.model.MereniElektrika;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření dialogu pro vytvoření nového {@link Mereni},
 * a to je měření typu {@link MereniElektrika}
 */
public final class KomponentMaxSpotreba extends TitulkovyPanel
        implements PolozkaVlozeni {

    /**
     * Deklarace grafických prvků
     */
    private final TextField tfIdSenzoru;
    private final DatePicker dpDatumOd, dpDatumDo;
    private final Label lIdSenzoru, lDatumOd, lDatumDo;

    public KomponentMaxSpotreba() {
        this.tfIdSenzoru = new TextField();
        this.dpDatumOd = dejMesicniKalendar();
        this.dpDatumDo = dejMesicniKalendar();
        this.lIdSenzoru = new Label(Titulek.ID_SENZORU.getNadpis());
        this.lDatumOd = new Label(Titulek.DATUM_OD.getNadpis());
        this.lDatumDo = new Label(Titulek.DATUM_DO.getNadpis());

        nastavKomponentMaxSpotreba();
    }

    private void nastavKomponentMaxSpotreba() {
        this.setText(Titulek.KOMPONENT_DIALOGU_SPOTREBA.getNadpis());
        this.setContent(dejGridPane());
    }

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

// <editor-fold defaultstate="collapsed" desc="Gettery">
    public TextField getTfIdSenzoru() { return tfIdSenzoru; }

    public DatePicker getDpDatumOd() { return dpDatumOd; }

    public DatePicker getDpDatumDo() { return dpDatumDo; }
// </editor-fold>
}

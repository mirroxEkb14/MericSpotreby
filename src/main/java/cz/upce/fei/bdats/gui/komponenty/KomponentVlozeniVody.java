package cz.upce.fei.bdats.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdats.data.model.Mereni;
import cz.upce.fei.bdats.data.model.MereniVoda;
import cz.upce.fei.bdats.gui.Titulek;
import cz.upce.fei.bdats.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdats.gui.kontejnery.TitulkovyPanel;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření dialogu pro vytvoření nového {@link Mereni},
 * a to je měření typu {@link MereniVoda}
 */
public final class KomponentVlozeniVody extends TitulkovyPanel
        implements PolozkaKomponenty {

    /**
     * Deklarace grafických prvků
     */
    private final TextField tfIdSenzoru, tfSpotrebaM3;
    private final DatePicker kalendar;
    private final Label lIdSenzoru, lSpotrebaM3, lKalendar;

    public KomponentVlozeniVody(int idSenzoru) {
        this.tfSpotrebaM3 = new TextField();
        this.tfSpotrebaM3.setText(this.VYCHOZI_HODNOTA_SPOTREBY);
        this.kalendar = this.dejMesicniKalendar();
        this.lSpotrebaM3 = new Label(Titulek.SPOTREBA_M3.getNadpis());
        this.lKalendar = new Label(Titulek.KALENDAR.getNadpis());
        this.lIdSenzoru = new Label(Titulek.ID_SENZORU.getNadpis());

        this.tfIdSenzoru = new TextField(String.valueOf(idSenzoru));
        this.tfIdSenzoru.setDisable(true);

        nastavKomponentVlozeniVody();
    }

    private void nastavKomponentVlozeniVody() {
        this.setText(Titulek.KOMPONENT_DIALOGU_VLOZENI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(lIdSenzoru, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(tfIdSenzoru, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(lSpotrebaM3, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(tfSpotrebaM3, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(lKalendar, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        gridPane.add(kalendar, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        return gridPane;
    }

// <editor-fold defaultstate="collapsed" desc="Gettery">
    public TextField getTfSpotrebaM3() {
        return tfSpotrebaM3;
    }

    public DatePicker getKalendar() {
        return kalendar;
    }
// </editor-fold>
}

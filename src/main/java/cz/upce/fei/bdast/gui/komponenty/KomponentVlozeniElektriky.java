package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.data.model.MereniElektrika;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření dialogu pro vytvoření nového {@link Mereni},
 * a to je měření typu {@link MereniElektrika}
 */
public final class KomponentVlozeniElektriky extends TitulkovyPanel
        implements PolozkaVlozeni {

    /**
     * Deklarace grafických prvků
     */
    private final TextField tfIdSenzoru, tfSpotrebaVT, tfSpotrebaNT;
    private final DatePicker kalendar;
    private final Label lIdSenzoru, lSpotrebaVT, lSpotrebaNT, lKalendar;

    public KomponentVlozeniElektriky(int idSenzoru) {
        this.tfSpotrebaVT = new TextField();
        this.tfSpotrebaVT.setText(this.VYCHOZI_HODNOTA_SPOTREBY);
        this.tfSpotrebaNT = new TextField();
        this.tfSpotrebaNT.setText(this.VYCHOZI_HODNOTA_SPOTREBY);
        this.kalendar = this.dejMesicniKalendar();
        this.lSpotrebaVT = new Label(Titulek.SPOTREBA_VT.getNadpis());
        this.lSpotrebaNT = new Label(Titulek.SPOTREBA_NT.getNadpis());
        this.lKalendar = new Label(Titulek.KALENDAR.getNadpis());
        this.lIdSenzoru = new Label(Titulek.ID_SENZORU.getNadpis());

        this.tfIdSenzoru = new TextField(String.valueOf(idSenzoru));
        this.tfIdSenzoru.setDisable(true);

        nastavKomponentVlozeniElektriky();
    }

    private void nastavKomponentVlozeniElektriky() {
        this.setText(Titulek.KOMPONENT_DIALOGU_VLOZENI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(lIdSenzoru, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(tfIdSenzoru, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(lSpotrebaVT, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(tfSpotrebaVT, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(lSpotrebaNT, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        gridPane.add(tfSpotrebaNT, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        gridPane.add(lKalendar, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_CTVRTY);
        gridPane.add(kalendar, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_CTVRTY);
        return gridPane;
    }

// <editor-fold defaultstate="collapsed" desc="Gettery">
    public TextField getTfSpotrebaVT() { return tfSpotrebaVT; }

    public TextField getTfSpotrebaNT() { return tfSpotrebaNT; }

    public DatePicker getKalendar() { return kalendar; }
// </editor-fold>
}

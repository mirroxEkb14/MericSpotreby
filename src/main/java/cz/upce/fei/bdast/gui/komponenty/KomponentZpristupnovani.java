package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.data.vycty.Pozice;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import cz.upce.fei.bdast.gui.koreny.SeznamPanel;
import cz.upce.fei.bdast.spravce.SpravceMereni;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s
 * tlačítky ({@link Button}) pro zpřístupňování prvků seznamu
 * <p>
 * Třída je Singleton
 */
public final class KomponentZpristupnovani extends TitulkovyPanel {

    private static KomponentZpristupnovani instance;

    /**
     * Tovární metoda (factory method) vracející existující nebo nově vytvořenou instanci
     * dané třídy
     */
    public static KomponentZpristupnovani getInstance() {
        if (instance == null)
            instance = new KomponentZpristupnovani();
        return instance;
    }

    /**
     * Deklarace tlačítek
     */
    private final Button btnZpristupniPrvni, btnZpristupniPosledni,
            btnZpristupniNaslednika, btnZpristupniPredchudce, btnZpristupniAktualni;

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    private KomponentZpristupnovani() {
        this.btnZpristupniPrvni = new Tlacitko(
                Titulek.PRVNI.getNadpis());
        this.btnZpristupniPrvni.setDisable(true);
        this.btnZpristupniPrvni.setOnAction(actionEvent -> nastavUdalostZpristupniPrvni());

        this.btnZpristupniPosledni = new Tlacitko(
                Titulek.POSLEDNI.getNadpis());
        this.btnZpristupniPosledni.setDisable(true);
        this.btnZpristupniPosledni.setOnAction(actionEvent -> nastavUdalostZpristupniPosledni());

        this.btnZpristupniNaslednika = new Tlacitko(
                Titulek.NASLEDNIK.getNadpis());
        this.btnZpristupniNaslednika.setDisable(true);

        this.btnZpristupniPredchudce = new Tlacitko(
                Titulek.PREDCHUDCE.getNadpis());
        this.btnZpristupniPredchudce.setDisable(true);

        this.btnZpristupniAktualni = new Tlacitko(
                Titulek.AKTUALNI.getNadpis());
        this.btnZpristupniAktualni.setDisable(true);
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

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#PRVNI}
     */
    private void nastavUdalostZpristupniPrvni() {
        SeznamPanel.getInstance().posunNaPrvni();
        SpravceMereni.getInstance().zpristupniMereni(Pozice.PRVNI);
        if (KomponentVlozeni.getInstance().jeVypnutoVlozNaslednika())  KomponentVlozeni.getInstance().zapniBtnVlozNaslednika();
        if (jeVypnutoZpristupniNaslednika())  zapniBtnZpristupniNaslednika();

        KomponentVlozeni.getInstance().vypniBtnVlozPredchudce();
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#POSLEDNI}
     */
    private void nastavUdalostZpristupniPosledni() {
        SeznamPanel.getInstance().posunNaPosledni();
        SpravceMereni.getInstance().zpristupniMereni(Pozice.POSLEDNI);
        if (KomponentVlozeni.getInstance().jeVypnutoVlozPredchudce())  KomponentVlozeni.getInstance().zapniBtnVlozPredchudce();
        if (jeVypnutoZpristupniPredchudce())  zapniBtnZpristupniPredchudce();

        KomponentVlozeni.getInstance().vypniBtnVlozNaslednika();
    }

    public boolean jeVypnutoZpristupniPrvni() { return btnZpristupniPrvni.isDisabled(); }

    public boolean jeVypnutoZpristupniPosledni() { return btnZpristupniPosledni.isDisabled(); }

    public boolean jeVypnutoZpristupniNaslednika() { return btnZpristupniNaslednika.isDisabled(); }

    public boolean jeVypnutoZpristupniPredchudce() { return btnZpristupniPredchudce.isDisabled(); }

    public boolean jeVypnutoZpristupniAktualni() { return btnZpristupniAktualni.isDisabled(); }

// <editor-fold defaultstate="collapsed" desc="Přepínače">
    public void prepniBtnZpristupniAktualni() { btnZpristupniAktualni.setDisable(!btnZpristupniAktualni.isDisabled()); }

    public void zapniBtnZpristupniPrvni() { btnZpristupniPrvni.setDisable(false); }

    public void vypniBtnZpristupniPrvni() { btnZpristupniPrvni.setDisable(true); }

    public void zapniBtnZpristupniPosledni() { btnZpristupniPosledni.setDisable(false); }

    public void vypniBtnZpristupniPosledni() { btnZpristupniPosledni.setDisable(true); }

    public void zapniBtnZpristupniNaslednika() { btnZpristupniNaslednika.setDisable(false); }

    public void vypniBtnZpristupniNaslednika() { btnZpristupniNaslednika.setDisable(true); }

    public void zapniBtnZpristupniPredchudce() { btnZpristupniPredchudce.setDisable(false); }

    public void vypniBtnZpristupniPredchudce() { btnZpristupniPredchudce.setDisable(true); }
// </editor-fold>
}

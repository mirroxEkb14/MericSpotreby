package cz.upce.fei.bdats.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdats.data.vycty.Pozice;
import cz.upce.fei.bdats.gui.alerty.InfoAlert;
import cz.upce.fei.bdats.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdats.gui.Titulek;
import cz.upce.fei.bdats.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdats.gui.kontejnery.Tlacitko;
import cz.upce.fei.bdats.gui.koreny.SeznamPanel;
import cz.upce.fei.bdats.spravce.SpravceMereni;
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
        this.btnZpristupniNaslednika.setOnAction(actionEvent -> nastavUdalostZpristupniNaslednika());

        this.btnZpristupniPredchudce = new Tlacitko(
                Titulek.PREDCHUDCE.getNadpis());
        this.btnZpristupniPredchudce.setDisable(true);
        this.btnZpristupniPredchudce.setOnAction(actionEvent -> nastavUdalostZpristupniPredchudce());

        this.btnZpristupniAktualni = new Tlacitko(
                Titulek.AKTUALNI.getNadpis());
        this.btnZpristupniAktualni.setDisable(true);
        this.btnZpristupniAktualni.setPrefWidth(MrizkovyPanel.PREFEROVANA_SIRKA_VELKEHO_TLACITKA);
        this.btnZpristupniAktualni.setOnAction(actionEvent -> nastavUdalostZpristupniAktualni());

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
        if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())  KomponentVlozeni.getInstance().zapniBtnVlozNaslednika();
        if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())  KomponentVlozeni.getInstance().zapniBtnVlozPredchudce();
        if (jeVypnutoBtnZpristupniNaslednika() && SeznamPanel.getInstance().jeIndexPlatnyProNaslednika())
            zapniBtnZpristupniNaslednika();
        if (jeVypnutoBtnZpristupniAktualni()) zapniBtnZpristupniAktualni();
        if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberNaslednika() &&
                SeznamPanel.getInstance().jeIndexPlatnyProNaslednika())
            KomponentOdebrani.getInstance().zapniBtnOdeberNaslednika();
        if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberAktualni())
            KomponentOdebrani.getInstance().zapniBtnOdeberAktualni();

        vypniBtnZpristupniPredchudce();
        KomponentOdebrani.getInstance().vypniBtnOdeberPredchudce();
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#POSLEDNI}
     */
    private void nastavUdalostZpristupniPosledni() {
        SeznamPanel.getInstance().posunNaPosledni();
        SpravceMereni.getInstance().zpristupniMereni(Pozice.POSLEDNI);
        if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())  KomponentVlozeni.getInstance().zapniBtnVlozPredchudce();
        if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())  KomponentVlozeni.getInstance().zapniBtnVlozNaslednika();
        if (jeVypnutoBtnZpristupniPredchudce() && SeznamPanel.getInstance().jeIndexPlatnyProPredchudce())
            zapniBtnZpristupniPredchudce();
        if (jeVypnutoBtnZpristupniAktualni()) zapniBtnZpristupniAktualni();
        if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPredchudce() &&
                SeznamPanel.getInstance().jeIndexPlatnyProPredchudce())
            KomponentOdebrani.getInstance().zapniBtnOdeberPredchudce();
        if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberAktualni())
            KomponentOdebrani.getInstance().zapniBtnOdeberAktualni();

        vypniBtnZpristupniNaslednika();
        KomponentOdebrani.getInstance().vypniBtnOdeberNaslednika();
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#NASLEDNIK}
     */
    private void nastavUdalostZpristupniNaslednika() {
        if (SeznamPanel.getInstance().jeIndexPlatnyProNaslednika()) {
            SeznamPanel.getInstance().posunNaNaslednika();
            SpravceMereni.getInstance().zpristupniMereni(Pozice.NASLEDNIK);

            if (SeznamPanel.getInstance().jeNaslednikPoslednim()) {
                vypniBtnZpristupniNaslednika();
                KomponentOdebrani.getInstance().vypniBtnOdeberNaslednika();
                if (jeVypnutoBtnZpristupniPredchudce()) zapniBtnZpristupniPredchudce();
                if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPredchudce())
                    KomponentOdebrani.getInstance().zapniBtnOdeberPredchudce();
                return;
            }
            if (jeVypnutoBtnZpristupniPredchudce()) zapniBtnZpristupniPredchudce();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce()) zapniBtnZpristupniPredchudce();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())
                KomponentVlozeni.getInstance().zapniBtnVlozNaslednika();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())
                KomponentVlozeni.getInstance().zapniBtnVlozPredchudce();
            if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPredchudce())
                KomponentOdebrani.getInstance().zapniBtnOdeberPredchudce();
        }
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#PREDCHUDCE}
     */
    private void nastavUdalostZpristupniPredchudce() {
        if (SeznamPanel.getInstance().jeIndexPlatnyProPredchudce()) {
            SeznamPanel.getInstance().posunNaPredchudce();
            SpravceMereni.getInstance().zpristupniMereni(Pozice.PREDCHUDCE);

            if (SeznamPanel.getInstance().jePredchudcePrvnim()) {
                vypniBtnZpristupniPredchudce();
                KomponentOdebrani.getInstance().vypniBtnOdeberPredchudce();
                if (jeVypnutoBtnZpristupniNaslednika()) zapniBtnZpristupniNaslednika();
                if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberNaslednika())
                    KomponentOdebrani.getInstance().zapniBtnOdeberNaslednika();
                return;
            }
            if (jeVypnutoBtnZpristupniPredchudce()) zapniBtnZpristupniPredchudce();
            if (jeVypnutoBtnZpristupniNaslednika()) zapniBtnZpristupniNaslednika();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())
                KomponentVlozeni.getInstance().zapniBtnVlozNaslednika();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())
                KomponentVlozeni.getInstance().zapniBtnVlozPredchudce();
            if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberNaslednika())
                KomponentOdebrani.getInstance().zapniBtnOdeberNaslednika();
        }
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#AKTUALNI}
     */
    private void nastavUdalostZpristupniAktualni() {
        InfoAlert.nahlasInfoLog(SeznamPanel.getInstance().dejAktualniPrvek().toString());
    }

    public boolean jeVypnutoBtnZpristupniPrvni() { return btnZpristupniPrvni.isDisabled(); }

    public boolean jeVypnutoBtnZpristupniPosledni() { return btnZpristupniPosledni.isDisabled(); }

    public boolean jeVypnutoBtnZpristupniNaslednika() { return btnZpristupniNaslednika.isDisabled(); }

    public boolean jeVypnutoBtnZpristupniPredchudce() { return btnZpristupniPredchudce.isDisabled(); }

    public boolean jeVypnutoBtnZpristupniAktualni() { return btnZpristupniAktualni.isDisabled(); }

// <editor-fold defaultstate="collapsed" desc="Přepínače">
    public void zapniBtnZpristupniPrvni() { btnZpristupniPrvni.setDisable(false); }

    public void vypniBtnZpristupniPrvni() { btnZpristupniPrvni.setDisable(true); }

    public void zapniBtnZpristupniPosledni() { btnZpristupniPosledni.setDisable(false); }

    public void vypniBtnZpristupniPosledni() { btnZpristupniPosledni.setDisable(true); }

    public void zapniBtnZpristupniNaslednika() { btnZpristupniNaslednika.setDisable(false); }

    public void vypniBtnZpristupniNaslednika() { btnZpristupniNaslednika.setDisable(true); }

    public void zapniBtnZpristupniPredchudce() { btnZpristupniPredchudce.setDisable(false); }

    public void vypniBtnZpristupniPredchudce() { btnZpristupniPredchudce.setDisable(true); }

    public void zapniBtnZpristupniAktualni() { btnZpristupniAktualni.setDisable(false); }

    public void vypniBtnZpristupniAktualni() { btnZpristupniAktualni.setDisable(true); }
// </editor-fold>
}

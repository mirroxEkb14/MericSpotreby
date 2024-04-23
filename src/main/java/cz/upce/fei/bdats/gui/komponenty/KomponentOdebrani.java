package cz.upce.fei.bdats.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdats.gui.alerty.WarningAlert;
import cz.upce.fei.bdats.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdats.gui.Titulek;
import cz.upce.fei.bdats.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdats.gui.kontejnery.Tlacitko;
import cz.upce.fei.bdats.gui.koreny.SeznamPanel;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s
 * tlačítky ({@link Button}) pro odebrání prvků seznamu
 * <p>
 * Třída je Singleton
 */
public final class KomponentOdebrani extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnOdeberPrvni, btnOdeberPosledni,
            btnOdeberNaslednika, btnOdeberPredchudce, btnOdeberAktualni;

    private static KomponentOdebrani instance;

    /**
     * Tovární metoda (factory method) vracející existující nebo nově vytvořenou instanci
     * dané třídy
     */
    public static KomponentOdebrani getInstance() {
        if (instance == null)
            instance = new KomponentOdebrani();
        return instance;
    }

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    private KomponentOdebrani() {
        this.btnOdeberPrvni = new Tlacitko(
                Titulek.PRVNI.getNadpis());
        this.btnOdeberPrvni.setDisable(true);
        this.btnOdeberPrvni.setOnAction(actionEvent -> nastavUdalostOdeberPrvni());

        this.btnOdeberPosledni = new Tlacitko(
                Titulek.POSLEDNI.getNadpis());
        this.btnOdeberPosledni.setDisable(true);
        this.btnOdeberPosledni.setOnAction(actionEvent -> nastavUdalostOdeberPosledni());

        this.btnOdeberNaslednika = new Tlacitko(
                Titulek.NASLEDNIK.getNadpis());
        this.btnOdeberNaslednika.setDisable(true);
        this.btnOdeberNaslednika.setOnAction(actionEvent -> nastavUdalostOdeberNaslednika());

        this.btnOdeberPredchudce = new Tlacitko(
                Titulek.PREDCHUDCE.getNadpis());
        this.btnOdeberPredchudce.setDisable(true);
        this.btnOdeberPredchudce.setOnAction(actionEvent -> nastavUdalostOdeberPredchudce());

        this.btnOdeberAktualni = new Tlacitko(
                Titulek.AKTUALNI.getNadpis());
        this.btnOdeberAktualni.setDisable(true);
        this.btnOdeberAktualni.setOnAction(actionEvent -> nastavUdalostOdeberAktualni());

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

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#PRVNI}
     */
    private void nastavUdalostOdeberPrvni() {
        final boolean bylAktualniPrvnim = SeznamPanel.getInstance().smazPrvni();
        overProPrvniPosledni(bylAktualniPrvnim);
        if (!SeznamPanel.getInstance().jeIndexPlatnyProPredchudce() &&
                !KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniPredchudce()) {
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniPredchudce();
        }
        if (!SeznamPanel.getInstance().jeIndexPlatnyProPredchudce() &&
                !KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPredchudce()) {
            KomponentOdebrani.getInstance().vypniBtnOdeberPredchudce();
        }
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#POSLEDNI}
     */
    private void nastavUdalostOdeberPosledni() {
        final boolean bylAktualniPoslednim = SeznamPanel.getInstance().smazPosledni();
        overProPrvniPosledni(bylAktualniPoslednim);
        if (!SeznamPanel.getInstance().jeIndexPlatnyProNaslednika() &&
                !KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniNaslednika()) {
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniNaslednika();
        }
        if (!SeznamPanel.getInstance().jeIndexPlatnyProNaslednika() &&
                !KomponentOdebrani.getInstance().jeVypnutoBtnOdeberNaslednika()) {
            KomponentOdebrani.getInstance().vypniBtnOdeberNaslednika();
        }
    }

    private void overProPrvniPosledni(boolean bylAkrualniPrvnimPoslednim) {
        if (bylAkrualniPrvnimPoslednim) {
            if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniAktualni())
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniAktualni();
            if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberAktualni())
                KomponentOdebrani.getInstance().vypniBtnOdeberAktualni();
            if (!KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())
                KomponentVlozeni.getInstance().vypniBtnVlozNaslednika();
            if (!KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())
                KomponentVlozeni.getInstance().vypniBtnVlozPredchudce();
            if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniNaslednika())
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniNaslednika();
            if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniPredchudce())
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniPredchudce();
            if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniAktualni())
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniAktualni();
            if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberNaslednika())
                KomponentOdebrani.getInstance().vypniBtnOdeberNaslednika();
            if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPredchudce())
                KomponentOdebrani.getInstance().vypniBtnOdeberPredchudce();
            if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberAktualni())
                KomponentOdebrani.getInstance().vypniBtnOdeberAktualni();
        }
        if (SeznamPanel.getInstance().jePrazdny()) {
            vypniBtnOdeberPrvni();
            vypniBtnOdeberPosledni();
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniPrvni();
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniPosledni();
            KomponentPrikazu.getInstance().vypniBtnZrus();
            KomponentSouboru.getInstance().vypniBtnUloz();
            KomponentSpotreba.getInstance().vypniBtnSpotrebaMax();
            KomponentSpotreba.getInstance().vypniBtnSpotrebaPrumer();
            KomponentSpotreba.getInstance().vypniBtnSpotrebaDen();
        }
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#NASLEDNIK}
     */
    private void nastavUdalostOdeberNaslednika() {
        final boolean jeNaslednik = SeznamPanel.getInstance().jeNaslednik();
        if (jeNaslednik) {
            SeznamPanel.getInstance().smazNaslednika();

            if (SeznamPanel.getInstance().jeNaslednikPoslednim()) {
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniNaslednika();
                vypniBtnOdeberNaslednika();
                if (KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniPredchudce() &&
                        SeznamPanel.getInstance().jePredchudce())
                    KomponentZpristupnovani.getInstance().zapniBtnZpristupniPredchudce();
                if (jeVypnutoBtnOdeberPredchudce() && SeznamPanel.getInstance().jePredchudce())
                    zapniBtnOdeberPredchudce();
                return;
            }
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())
                KomponentZpristupnovani.getInstance().zapniBtnZpristupniPredchudce();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())
                KomponentVlozeni.getInstance().zapniBtnVlozNaslednika();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())
                KomponentVlozeni.getInstance().zapniBtnVlozPredchudce();
        }
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#PREDCHUDCE}
     */
    private void nastavUdalostOdeberPredchudce() {
        final boolean jePredchudce = SeznamPanel.getInstance().jePredchudce();
        if (jePredchudce) {
            SeznamPanel.getInstance().smazPredchudce();

            if (SeznamPanel.getInstance().jePredchudcePrvnim()) {
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniPredchudce();
                vypniBtnOdeberPredchudce();
                if (KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniNaslednika() &&
                        SeznamPanel.getInstance().jeNaslednik())
                    KomponentZpristupnovani.getInstance().zapniBtnZpristupniNaslednika();
                if (jeVypnutoBtnOdeberNaslednika() && SeznamPanel.getInstance().jeNaslednik())
                    zapniBtnOdeberNaslednika();
                return;
            }
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())
                KomponentZpristupnovani.getInstance().zapniBtnZpristupniNaslednika();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())
                KomponentVlozeni.getInstance().zapniBtnVlozPredchudce();
            if (KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())
                KomponentVlozeni.getInstance().zapniBtnVlozNaslednika();
        }
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#AKTUALNI}
     */
    private void nastavUdalostOdeberAktualni() {
        if (SeznamPanel.getInstance().jeIndexPlatny()) {
            WarningAlert.nahlasVarovaciLog(SeznamPanel.getInstance().smazAktualni().toString());

            KomponentZpristupnovani.getInstance().vypniBtnZpristupniAktualni();
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniNaslednika();
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniPredchudce();
            KomponentOdebrani.getInstance().vypniBtnOdeberAktualni();
            KomponentOdebrani.getInstance().vypniBtnOdeberNaslednika();
            KomponentOdebrani.getInstance().vypniBtnOdeberPredchudce();
            KomponentVlozeni.getInstance().vypniBtnVlozNaslednika();
            KomponentVlozeni.getInstance().vypniBtnVlozPredchudce();
            if (SeznamPanel.getInstance().jePrazdny()) {
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniPrvni();
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniPosledni();
                KomponentOdebrani.getInstance().vypniBtnOdeberPrvni();
                KomponentOdebrani.getInstance().vypniBtnOdeberPosledni();
                KomponentPrikazu.getInstance().vypniBtnZrus();
                KomponentSouboru.getInstance().vypniBtnUloz();
                KomponentSpotreba.getInstance().vypniBtnSpotrebaMax();
                KomponentSpotreba.getInstance().vypniBtnSpotrebaPrumer();
                KomponentSpotreba.getInstance().vypniBtnSpotrebaDen();
            }
        }
    }

    public boolean jeVypnutoBtnOdeberPrvni() { return btnOdeberPrvni.isDisabled(); }

    public boolean jeVypnutoBtnOdeberPosledni() { return btnOdeberPosledni.isDisabled(); }

    public boolean jeVypnutoBtnOdeberNaslednika() { return btnOdeberNaslednika.isDisabled(); }

    public boolean jeVypnutoBtnOdeberPredchudce() { return btnOdeberPredchudce.isDisabled(); }

    public boolean jeVypnutoBtnOdeberAktualni() { return btnOdeberAktualni.isDisabled(); }


// <editor-fold defaultstate="collapsed" desc="Přepínače">
    public void zapniBtnOdeberPrvni() { btnOdeberPrvni.setDisable(false); }

    public void vypniBtnOdeberPrvni() { btnOdeberPrvni.setDisable(true); }

    public void zapniBtnOdeberPosledni() { btnOdeberPosledni.setDisable(false); }

    public void vypniBtnOdeberPosledni() { btnOdeberPosledni.setDisable(true); }

    public void zapniBtnOdeberNaslednika() { btnOdeberNaslednika.setDisable(false); }

    public void vypniBtnOdeberNaslednika() { btnOdeberNaslednika.setDisable(true); }

    public void zapniBtnOdeberPredchudce() { btnOdeberPredchudce.setDisable(false); }

    public void vypniBtnOdeberPredchudce() { btnOdeberPredchudce.setDisable(true); }

    public void zapniBtnOdeberAktualni() { btnOdeberAktualni.setDisable(false); }

    public void vypniBtnOdeberAktualni() { btnOdeberAktualni.setDisable(true); }
// </editor-fold>
}

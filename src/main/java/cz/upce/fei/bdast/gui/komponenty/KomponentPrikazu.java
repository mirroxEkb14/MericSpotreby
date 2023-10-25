package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.dialogy.DialogGenerator;
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import cz.upce.fei.bdast.gui.koreny.SeznamPanel;
import cz.upce.fei.bdast.spravce.SpravceMereni;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

import java.util.Optional;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s tlačítky
 * ({@link Button}) pro některé určité příkazy jako je generování prvků nebo
 * zrušení celého seznamu
 * <p>
 * Třída je Singleton
 */
public final class KomponentPrikazu extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnGeneruj, btnZrus;

    private static KomponentPrikazu instance;

    /**
     * Instance rozhraní {@link TextValidator} pro validaci řetězců, které by měly
     * představovat celá čísla
     * <p>
     * Ověří, zda zadaný text je platným celočíselným číslem. Lambda výraz reprezentuje
     * implementaci metody {@link TextValidator#jeValidni(Object)}. Vezme vstupní řetězec
     * {@code t} a provádí validaci
     */
    private final TextValidator<String> validatorCelychCisel = t -> {
        if (t.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(t);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    };

    /**
     * Tovární metoda (factory method) vracející existující nebo nově vytvořenou instanci
     * dané třídy
     */
    public static KomponentPrikazu getInstance() {
        if (instance == null)
            instance = new KomponentPrikazu();
        return instance;
    }

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    private KomponentPrikazu() {
        this.btnGeneruj = new Tlacitko(
                Titulek.GENERUJ.getNadpis());
        this.btnGeneruj.setOnAction(actionEvent -> nastavUdalostGeneruj());

        this.btnZrus = new Tlacitko(
                Titulek.ZRUS.getNadpis());
        this.btnZrus.setDisable(true);
        this.btnZrus.setOnAction(actionEvent -> nastavUdalostZrus());

        nastavKomponentPrikazu();
    }

    private void nastavKomponentPrikazu() {
        this.setText(Titulek.KOMPONENT_PRIKAZU.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(btnGeneruj, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnZrus, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        return gridPane;
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#GENERUJ}
     */
    private void nastavUdalostGeneruj() {
        final Optional<String> vstup = new DialogGenerator().showAndWait();
        if (vstup.isPresent() && validatorCelychCisel.testuj(vstup.get())) {
            final int pocetMereni = Integer.parseInt(vstup.get());
            SpravceMereni.getInstance().generuj(pocetMereni);
            SeznamPanel.getInstance().obnovSeznam(SpravceMereni.getInstance().dejDatovod());

            if (KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniPrvni())
                KomponentZpristupnovani.getInstance().zapniBtnZpristupniPrvni();
            if (KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniPosledni())
                KomponentZpristupnovani.getInstance().zapniBtnZpristupniPosledni();
            if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPrvni())
                KomponentOdebrani.getInstance().zapniBtnOdeberPrvni();
            if (KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPosledni())
                KomponentOdebrani.getInstance().zapniBtnOdeberPosledni();
            if (jeVypnutoBtnZrus()) zapniBtnZrus();
            if (KomponentSouboru.getInstance().jeVypnutoBtnUloz())
                KomponentSouboru.getInstance().zapniBtnUloz();
            if (KomponentSpotreba.getInstance().jeVypnutoBtnSpotrebaMax())
                KomponentSpotreba.getInstance().zapniBtnSpotrebaMax();
            if (KomponentSpotreba.getInstance().jeVypnutoBtnSpotrebaPrumer())
                KomponentSpotreba.getInstance().zapniBtnSpotrebaPrumer();
            if (KomponentSpotreba.getInstance().jeVypnutoBtnSpotrebaDen())
                KomponentSpotreba.getInstance().zapniBtnSpotrebaDen();
        }
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#ZRUS}
     */
    private void nastavUdalostZrus() {
        SeznamPanel.getInstance().vymaz();
        SpravceMereni.getInstance().zrus();
        vypniBtnZrus();
        if (!KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())
            KomponentVlozeni.getInstance().vypniBtnVlozNaslednika();
        if (!KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())
            KomponentVlozeni.getInstance().vypniBtnVlozPredchudce();
        KomponentZpristupnovani.getInstance().vypniBtnZpristupniPrvni();
        KomponentZpristupnovani.getInstance().vypniBtnZpristupniPosledni();
        if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniNaslednika())
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniNaslednika();
        if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniPredchudce())
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniPredchudce();
        if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniAktualni())
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniAktualni();
        KomponentOdebrani.getInstance().vypniBtnOdeberPrvni();
        KomponentOdebrani.getInstance().vypniBtnOdeberPosledni();
        if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberNaslednika())
            KomponentOdebrani.getInstance().vypniBtnOdeberNaslednika();
        if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPredchudce())
            KomponentOdebrani.getInstance().vypniBtnOdeberPredchudce();
        if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberAktualni())
            KomponentOdebrani.getInstance().vypniBtnOdeberAktualni();
        if (!KomponentSouboru.getInstance().jeVypnutoBtnUloz())
            KomponentSouboru.getInstance().vypniBtnUloz();
        if (!KomponentSpotreba.getInstance().jeVypnutoBtnSpotrebaMax())
            KomponentSpotreba.getInstance().vypniBtnSpotrebaMax();
        if (!KomponentSpotreba.getInstance().jeVypnutoBtnSpotrebaPrumer())
            KomponentSpotreba.getInstance().vypniBtnSpotrebaPrumer();
        if (!KomponentSpotreba.getInstance().jeVypnutoBtnSpotrebaDen())
            KomponentSpotreba.getInstance().vypniBtnSpotrebaDen();
    }

    /**
     * Veřejná pomocní metoda
     * <p>
     * Ověří, zda je tlačítko {@code btnZrus} vypnuto/deaktivováno
     *
     * @return vrací {@code true}, pokud je vypnuto (disabled), v opačném případě {@code false}
     */
    public boolean jeVypnutoBtnZrus() { return btnZrus.isDisabled(); }

// <editor-fold defaultstate="collapsed" desc="Přepínače">
    public void zapniBtnZrus() { btnZrus.setDisable(false); }

    public void vypniBtnZrus() { btnZrus.setDisable(true); }
// </editor-fold>
}

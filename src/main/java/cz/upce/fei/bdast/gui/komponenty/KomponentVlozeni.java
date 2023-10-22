package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu s tlačítky ({@link Button}) pro
 * vložení prvků do seznamu. Označení těchto tlačítek je reprezentováno enumerací
 * {@link Titulek}. Tato tlačítka jsou uspořádány v kontejnerové třídě {@link GridPane},
 * která umožňuje organizovat prvky do řádků a sloupců
 */
public final class KomponentVlozeni extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnVlozPrvni, btnVlozPosledni, btnVlozNaslednika, btnVlozPredchudce;

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    public KomponentVlozeni() {
        this.btnVlozPrvni = new Tlacitko(
                Titulek.PREDCHUDCE.getNadpis());
        this.btnVlozPosledni = new Tlacitko(
                Titulek.POSLEDNI.getNadpis());
        this.btnVlozNaslednika = new Tlacitko(
                Titulek.NASLEDNIK.getNadpis());
        this.btnVlozPredchudce = new Tlacitko(
                Titulek.PREDCHUDCE.getNadpis());

        nastavKomponentVlozeni();
    }

    private void nastavKomponentVlozeni() {
        this.setText(Titulek.KOMPONENT_VLOZENI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(btnVlozPrvni, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnVlozPosledni, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnVlozNaslednika, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(btnVlozPredchudce, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        return gridPane;
    }

    /**
     * Metoda {@link Button#setOnAction(EventHandler)} se používá k nastavení akce
     * (události) nebo kódu, který se má provést po stisknutí tohoto tlačítka, což
     * dává tlačítkům funkcionalitu
     * <p>
     * Akce může být definována jako lambda výraz
     * <p>
     * U implementrace tlačítka {@code Prvni} dojde k:
     * <ul>
     * <li>
     * <li>
     * <li>
     * </ul>
     */
    private void nastavUdalostPrvni() {

    }

/**
 * Editor folding v Javě je funkce textových editorů, která umožňuje skrývat a
 * rozbalovat bloky kódu, což zvyšuje přehlednost a usnadňuje navigaci v kódu
 * <p>
 * Když blok kódu je skryt, většina vývojových prostředí zobrazí záhlaví, které
 * obsahuje název bloku kódu a umožňuje rozbalit ho zpět:
 * <ul>
 * <li> <b>Seskupení importů</b>: je možné seskupit importy na začátku souboru,
 * aby bylo jasné, které balíčky jsou použity
 * <li> <b>Seskupení konstant</b>: je možné seskupit konstanty nebo enum hodnoty,
 * aby bylo možné rychle najít potřebnou hodnotu
 * </ul>
 */
//<editor-fold defaultstate="collapsed" desc="Gettery">
    public Button getBtnVlozPrvni() { return btnVlozPrvni; }

    public Button getBtnVlozPosledni() { return btnVlozPosledni; }

    public Button getBtnVlozNaslednika() { return btnVlozNaslednika; }

    public Button getBtnVlozPredchudce() { return btnVlozPredchudce; }
//</editor-fold>
}

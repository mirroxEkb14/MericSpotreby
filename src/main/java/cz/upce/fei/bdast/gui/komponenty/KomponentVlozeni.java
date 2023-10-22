package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.kontejnery.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
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

// <editor-fold defaultstate="collapsed" desc="Konstanty pro umístění prvků u GridPane">
    /**
     * Metoda {@code add(Node child, int columnIndex, int rowIndex)} je metodou třídy
     * {@link GridPane}, která se používá k přidávání prvků (některého typu {@link Node},
     * což může být například tlačítko, textové pole nebo jiný uživatelský ovládací prvek)
     * do mřížky (grid) v {@link GridPane}. Tato metoda umožňuje specifikovat, kam v mřížce
     * má být prvek umístěn na základě jeho sloupcového (columnIndex) a řádkového (rowIndex)
     * indexu
     */
    private static final int SLOUPCOVY_INDEX_PRVNIHO = 0;
    private static final int RADKOVY_INDEX_PRVNIHO = 0;
    private static final int SLOUPCOVY_INDEX_POSLEDNIHO = 1;
    private static final int RADKOVY_INDEX_POSLEDNIHO = 0;
    private static final int SLOUPCOVY_INDEX_NASLEDNIKA = 0;
    private static final int RADKOVY_INDEX_NASLEDNIKA = 1;
    private static final int SLOUPCOVY_INDEX_PREDCHUDCE = 1;
    private static final int RADKOVY_INDEX_PREDCHUDCE = 1;
// </editor-fold>

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    public KomponentVlozeni() {
        this.btnVlozPrvni = new Tlacitko(
                Titulek.VLOZ_PRVNI.getNadpis());
        this.btnVlozPosledni = new Tlacitko(
                Titulek.VLOZ_POSLEDNI.getNadpis());
        this.btnVlozNaslednika = new Tlacitko(
                Titulek.VLOZ_NASLEDNIKA.getNadpis());
        this.btnVlozPredchudce = new Tlacitko(
                Titulek.VLOZ_PREDCHUDCE.getNadpis());

        nastavKomponentVlozeni();
    }

    private void nastavKomponentVlozeni() {
        this.setText(Titulek.KOMPONENT_VLOZENI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(super.ODSAZENI_OKRAJE));
        gridPane.setVgap(super.VERTIKALNI_MEZERA);
        gridPane.setHgap(super.HORIZONTALNI_MEZERA);
        gridPane.add(btnVlozPrvni, SLOUPCOVY_INDEX_PRVNIHO, RADKOVY_INDEX_PRVNIHO);
        gridPane.add(btnVlozPosledni, SLOUPCOVY_INDEX_POSLEDNIHO, RADKOVY_INDEX_POSLEDNIHO);
        gridPane.add(btnVlozNaslednika, SLOUPCOVY_INDEX_NASLEDNIKA, RADKOVY_INDEX_NASLEDNIKA);
        gridPane.add(btnVlozPredchudce, SLOUPCOVY_INDEX_PREDCHUDCE, RADKOVY_INDEX_PREDCHUDCE);
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

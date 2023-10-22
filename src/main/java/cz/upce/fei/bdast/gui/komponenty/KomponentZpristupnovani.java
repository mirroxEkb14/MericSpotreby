package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.kontejnery.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s
 * tlačítky ({@link Button}) pro zpřístupňování prvků seznamu
 */
public final class KomponentZpristupnovani extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnZpristupniPrvni, btnZpristupniPosledni,
            btnZpristupniNaslednika, btnZpristupniPredchudce, btnZpristupniAktualni;

// <editor-fold defaultstate="collapsed" desc="Konstanty pro umístění prvků u GridPane">
    /**
     * Konstanta vyjadřuje preferovánou šiřku tlačítka {@code Aktualni}
     */
    private static final double PREFEROVANA_SIRKA_TLACITKA_AKTUALNI = 300.0;
    /**
     * Metoda {@link GridPane#setColumnSpan(Node, Integer)} umožňuje upravit vizuální
     * rozložení prvků v {@link GridPane} sloužící k nastavení počtu sloupců, které má
     * daný prvek ({@link Node}) v mřížce zabírat:
     * <ul>
     * <li> {@code child}: Toto je prvek ({@link Node}), který chcete upravit a
     * nastavit počet sloupců, které má zabírat v mřížce
     * <li> {@code value}: určuje počet sloupců, které má prvek zabírat
     * </ul>
     * Tato metoda je užitečná, pokud je zapotřebí změnit rozložení prvků v mřížce a
     * zajistit, aby určité prvky zabíraly více sloupců nebo řádků než ostatní prvky. To
     * umožňuje vytvářet složitější rozvržení uživatelského rozhraní s pružným
     * uspořádáním prvků
     */
    private static final int ROZPETI_SLOUPCU = 2;

    /**
     * Konstanty reprezentují sloupcové (columnIndex) a řádkové (rowIndex) indexy
     * pro umístění v mřížce u {@link GridPane}
     */
    private static final int SLOUPCOVY_INDEX_PRVNIHO = 0;
    private static final int RADKOVY_INDEX_PRVNIHO = 0;
    private static final int SLOUPCOVY_INDEX_POSLEDNIHO = 1;
    private static final int RADKOVY_INDEX_POSLEDNIHO = 0;
    private static final int SLOUPCOVY_INDEX_NASLEDNIKA = 0;
    private static final int RADKOVY_INDEX_NASLEDNIKA = 1;
    private static final int SLOUPCOVY_INDEX_PREDCHUDCE = 1;
    private static final int RADKOVY_INDEX_PREDCHUDCE = 1;
    private static final int SLOUPCOVY_INDEX_AKTUALNIHO = 0;
    private static final int RADKOVY_INDEX_AKTUALNIHO = 2;
// </editor-fold>

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    public KomponentZpristupnovani() {
        this.btnZpristupniPrvni = new Tlacitko(
                Titulek.ZPRISTUPNI_PRVNI.getNadpis());
        this.btnZpristupniPosledni = new Tlacitko(
                Titulek.ZPRISTUPNI_POSLEDNI.getNadpis());
        this.btnZpristupniNaslednika = new Tlacitko(
                Titulek.ZPRISTUPNI_NASLEDNIKA.getNadpis());
        this.btnZpristupniPredchudce = new Tlacitko(
                Titulek.ZPRISTUPNI_PREDCHUDCE.getNadpis());
        this.btnZpristupniAktualni = new Tlacitko(
                Titulek.ZPRISTUPNI_AKTUALNI.getNadpis());
        this.btnZpristupniAktualni.setPrefWidth(PREFEROVANA_SIRKA_TLACITKA_AKTUALNI);

        nastavKomponentZpristupnovani();
    }

    private void nastavKomponentZpristupnovani() {
        this.setText(Titulek.KOMPONENT_ZPRISTUPNI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(super.ODSAZENI_OKRAJE));
        gridPane.setVgap(super.VERTIKALNI_MEZERA);
        gridPane.setHgap(super.HORIZONTALNI_MEZERA);
        gridPane.add(btnZpristupniPrvni, SLOUPCOVY_INDEX_PRVNIHO, RADKOVY_INDEX_PRVNIHO);
        gridPane.add(btnZpristupniPosledni, SLOUPCOVY_INDEX_POSLEDNIHO, RADKOVY_INDEX_POSLEDNIHO);
        gridPane.add(btnZpristupniNaslednika, SLOUPCOVY_INDEX_NASLEDNIKA, RADKOVY_INDEX_NASLEDNIKA);
        gridPane.add(btnZpristupniPredchudce, SLOUPCOVY_INDEX_PREDCHUDCE, RADKOVY_INDEX_PREDCHUDCE);
        gridPane.add(btnZpristupniAktualni, SLOUPCOVY_INDEX_AKTUALNIHO, RADKOVY_INDEX_AKTUALNIHO);
        GridPane.setColumnSpan(btnZpristupniAktualni, ROZPETI_SLOUPCU);
        return gridPane;
    }

//<editor-fold defaultstate="collapsed" desc="Gettery">
    public Button getBtnZpristupniPrvni() { return btnZpristupniPrvni; }

    public Button getBtnZpristupniPosledni() { return btnZpristupniPosledni; }

    public Button getBtnZpristupniNaslednika() { return btnZpristupniNaslednika; }

    public Button getBtnZpristupniPredchudce() { return btnZpristupniPredchudce; }

    public Button getBtnZpristupniAktualni() { return btnZpristupniAktualni; }
//</editor-fold>
}

package cz.upce.fei.bdast.gui.kontejnery;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Třída {@link GridPane} je používána k vytváření mřížky (grid) pro uspořádání prvků
 * (komponent) do řádků a sloupců. Tato implementace obsahuje:
 * <ul>
 * <li> obecná nastavení pro všechny potomky
 * <li> veřejné konstanty pro odsazení u třídy {@link GridPane}, které jsou používany
 * potomky této třídy a jsou stejné pro každého potomka, a proto není potřeba je
 * definovat uvnitř potomků, když je možné umožnit potomkům přistupovat k těmto
 * konstantám přes klíčové slovo {@code super}
 * </ul>
 */
public class MrizkovyPanel extends GridPane {

// <editor-fold defaultstate="collapsed" desc="Konstanty pro odsazení u GridPane">
    /**
     * Konstanta pro vytvýření nového objektu {@link Insets}, reprezentující odsazení
     * okraje (místa mezi okrajem a obsahem) v různých směrech
     * <p>
     * Konstruktor bere jedno číslo typu {@code double} jako argument a používá ho pro
     * vytvoření odsazení (insets) ve všech směrech (horní, pravý, dolní a levý) se
     * stejnou hodnotou {@code v}.
     * <p>
     * Používá se obvykle k definování mezery nebo okraje kolem komponenty (například
     * tlačítka, panelu apod.), aby byl zachován určitý odstup od okolního obsahu nebo
     * okraje okna
     */
    public final double ODSAZENI_OKRAJE = 10.0;
    /**
     * Metody {@link GridPane#setVgap(double)} a {@link GridPane#setHgap(double)} slouží
     * k nastavení vertikálního (mezery mezi řádky) a horizontálního (mezery mezi sloupci)
     * mezery mezi jednotlivými buňkami mřížky
     */
    public final double VERTIKALNI_MEZERA = 5.0;
    public final double HORIZONTALNI_MEZERA = 5.0;
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Konstanty pro umístění prvků u GridPane">
    /**
     * Konstanta vyjadřuje preferovánou šiřku tlačítka, které je jenom jedno na
     * celém řádku
     */
    public static final double PREFEROVANA_SIRKA_VELKEHO_TLACITKA = 300.0;
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
    public static final int ROZPETI_SLOUPCU = 2;
    /**
     * Konstanty reprezentují sloupcové (columnIndex) a řádkové (rowIndex) indexy
     * pro umístění v mřížce u {@link GridPane}
     * <p>
     * Metoda {@code add(Node child, int columnIndex, int rowIndex)} je metodou třídy
     * {@link GridPane}, která se používá k přidávání prvků (některého typu {@link Node},
     * což může být například tlačítko, textové pole nebo jiný uživatelský ovládací prvek)
     * do mřížky (grid) v {@link GridPane}. Tato metoda umožňuje specifikovat, kam v mřížce
     * má být prvek umístěn na základě jeho sloupcového (columnIndex) a řádkového (rowIndex)
     * indexu
     */
    public static final int SLOUPCOVY_INDEX_PRVNI = 0;
    public static final int RADKOVY_INDEX_PRVNI = 0;
    public static final int SLOUPCOVY_INDEX_DRUHY = 1;
    public static final int RADKOVY_INDEX_DRUHY = 1;
    public static final int RADKOVY_INDEX_TRETI = 2;
// </editor-fold>

    /**
     * Konstruktor nastaví obecné vlastnosti, které budou dědit všechny potomky
     */
    public MrizkovyPanel() {
        this.setPadding(new Insets(ODSAZENI_OKRAJE));
        this.setVgap(VERTIKALNI_MEZERA);
        this.setHgap(HORIZONTALNI_MEZERA);
    }
}

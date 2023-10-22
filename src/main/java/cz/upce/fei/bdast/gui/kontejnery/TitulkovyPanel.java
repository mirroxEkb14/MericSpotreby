package cz.upce.fei.bdast.gui.kontejnery;

import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

/**
 * Třída dědí {@link TitledPane} reprezentující jednu z komponentů uživatelského
 * rozhraní zobrazující obsah ve sklopném panelu s nadpisem (titulkem).
 * <p>
 * {@link TitledPane} je užitečný pro organizaci obsahu ve sklápěcích sekcích,
 * kde uživatel může vybrat, které sekce chce zobrazovat, a které skrýt. To je
 * často používáno v rozhraních pro nastavení, záložkách nebo zprávách s různými
 * detaily
 * <p>
 * Tento panel může být buď rozložený (expanded), což znamená, že jeho obsah je
 * viditelný, nebo složený (collapsed), což znamená, že jeho obsah je skryt a
 * zobrazen je pouze nadpis
 * <p>
 * Hlavní vlastnosti zahrnují:
 * <ol>
 * <li> <b>Nadpis (Title)</b>: obsahuje nadpis, který zobrazuje název nebo popis obsahu
 * panelu, a může být textovým řetězcem nebo jiným vizuálním prvkem
 * <li> <b>Sklopnost (Collapsibility)</b>: uživatel může sklopit (collapse) nebo rozložit
 * (expand) panel pomocí kliknutí na nadpis, což umožňuje zobrazovat nebo skrývat
 * obsah panelu
 * <li> <b>Obsah (Content)</b>: uvnitř je možné umístit libovolný obsah, včetně textu,
 * ovládacích prvků, dalších kontejnerů nebo grafických prvků
 * </ol>
 * Také obsahuje veřejné konstanty pro odsazení u třídy {@link GridPane}, které jsou
 * používany potomky této třídy a jsou stejné pro každého potomka, a proto není
 * potřeba je definovat uvnitř potomků, když je možné umožnit potomkům přistupovat
 * k těmto konstantám přes klíčové slovo {@code super}
 */
public class TitulkovyPanel extends TitledPane {

// <editor-fold defaultstate="collapsed" desc="Konstanty pro nastavení TitledPane">
    /**
     * Konstanta vyjadřuje počáteční stav panelu s nadpisem:
     * <ul>
     * <li> Pokud je hodnota expanded nastavena na {@code true}, panel bude
     * rozložený a jeho obsah bude viditelný při inicializaci
     * <li> Pokud je hodnota expanded nastavena na {@code false}, panel bude
     * složený a jeho obsah bude skryt při inicializaci
     * </ul>
     */
    private static final boolean JE_ROZLOZEN = true;
    /**
     * Konstanta reprezentuje možnosti skládání (collapsing) panelu s nadpisem:
     * <ul>
     * <li> Pokud je hodnota collapsible nastavena na {@code true}, uživatel
     * může sklopit a rozložit panel kliknutím na nadpis
     * <li> Pokud je hodnota collapsible nastavena na {@code false}, uživatel
     * nebude moci panel sklopit nebo rozložit, a nadpis bude pouze informativní
     * </ul>
     */
    private static final boolean JE_SKLADAN = false;
    /**
     * Konstanta slouží k nastavení animace při otevírání a zavírání panelu s nadpisem
     * a určuje, zda má být animace povolena ({@code true}) nebo zakázána ({@code false})
     * <p>
     * Když je animace povolena, panel bude rozložen nebo složen s animací, což vytváří
     * vizuální efekt při změně stavu zlepšuje vizuální zkušenost uživatele
     */
    private static final boolean JE_ANIMOVANY = true;
// </editor-fold>

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
     * Třída {@link GridPane} je používána k vytváření mřížky (grid) pro uspořádání prvků
     * (komponent) do řádků a sloupců. Metody {@link GridPane#setVgap(double)} a
     * {@link GridPane#setHgap(double)} slouží k nastavení vertikálního (mezery mezi řádky)
     * a horizontálního (mezery mezi sloupci) mezery mezi jednotlivými buňkami mřížky
     */
    public final double VERTIKALNI_MEZERA = 5.0;
    public final double HORIZONTALNI_MEZERA = 5.0;
// </editor-fold>

    public TitulkovyPanel() {
        this.setExpanded(JE_ROZLOZEN);
        this.setCollapsible(JE_SKLADAN);
        this.setAnimated(JE_ANIMOVANY);
    }
}

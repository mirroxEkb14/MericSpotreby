package cz.upce.fei.bdats.gui.kontejnery;

import javafx.scene.control.TitledPane;

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

    /**
     * Konstruktor nastaví obecné vlastnosti, které budou dědit všechny potomky
     */
    public TitulkovyPanel() {
        this.setExpanded(JE_ROZLOZEN);
        this.setCollapsible(JE_SKLADAN);
        this.setAnimated(JE_ANIMOVANY);
    }
}

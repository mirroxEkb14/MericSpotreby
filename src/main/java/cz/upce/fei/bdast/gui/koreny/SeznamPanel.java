package cz.upce.fei.bdast.gui.koreny;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;
import cz.upce.fei.bdast.kolekce.AbstrDoubleList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.text.Font;

import java.util.Iterator;

/**
 * Tato třída, reprezentující {@link ListView}:
 * <ul>
 * <li> představuje vizuální komponentu určenou k zobrazení seznamu položek
 * uživatelského rozhraní
 * <li> umožňuje uživateli procházet a vybírat jednotlivé položky ze seznamu
 * pomocí myši nebo klávesnice
 * <li> umožňuje přizpůsobit vzhled a chování položek seznamu pomocí buňkových
 * továren (cell factories), týmž lze dosáhnout zobrazení složitějších prvků
 * než pouze textové řetězce
 * <li> může reagovat na akce uživatele, jako je kliknutí na položku seznamu
 * (tj. podporuje události)
 * </ul>
 * U této implementaci dochází k tomu, že tento seznam obsahuje prvky typu
 * {@link Mereni} a pomocí tlačítek z druhé části okna ({@link PrikazPanel})
 * je možné manipulovat těmito položkami
 */
public final class SeznamPanel extends ListView<Mereni> {

    /**
     * Konstanta vyjadřuje minimální možnou šiřku seznamu prvků
     */
    private static final int MIN_SIRKA_SEZNAMU = 580;
    /**
     * Konstanty jsou potřebné pro nastavení této třídy v metodě {@link SeznamPanel#nastavSeznamPanel()}
     */
    private static final String NAZEV_SEZNAM_FONTU = "Monospaced";
    private static final int DIMENZE_SEZNAM_FONTU = 13;
    private static final String PRAZDNY_RETEZEC = "";

    public SeznamPanel() { nastavSeznamPanel(); }

    /**
     * Popis logicky:
     * <ol>
     * <li> Odstraní veškerý stávající obsah seznamu tím, že vymaže všechny existující
     * položky (objekty) v {@link ListView}
     * <li> Cyklus {@code while} projde všechny položky seznamu pomocí iterátoru
     * <li> V každém průchodu cyklem se aktuální objekt meření (nově vybraný) přidá do
     * seznamu {@link ListView}
     * </ol>
     * <b>Poznámka</b>: pokud by třída {@link IAbstrDoubleList} také deklarovala metodu
     * {@code Stream&lt;T&gt; stream()}, která by převedla obsah seznamu na datový proud
     * (v implementační třídě {@link AbstrDoubleList} by se nepřekrývala), bylo by možné
     * použit ten datovod jako argument - {@code Stream&lt;Motorka&gt; datovod}
     *
     * @param seznamMereni seznam obsahující objekty typu {@link Mereni}
     */
    public void obnovSeznam(IAbstrDoubleList<Mereni> seznamMereni) {
        this.getItems().clear();

        final Iterator<Mereni> seznamIterator = seznamMereni.iterator();
        while (seznamIterator.hasNext()) {
            final Mereni mereni = seznamIterator.next();
            this.getItems().add(mereni);
        }
    }

    /**
     * Popis logiky:
     * <ol>
     * <li> Nastaví režim výběru, čímž se omezí možnost výběru na jedinou položku v seznamu,
     * což znamená, že uživatel může vybrat pouze jednu položku najednou:
     *      <ol>
     *      <li> {@link ListView#getSelectionModel()} vrátí odkaz na objekt typu
     *      {@link MultipleSelectionModel}, který spravuje výběr v {@link ListView}
     *      <li> {@link MultipleSelectionModel#setSelectionMode(SelectionMode)}
     *      nastaví režim výběru na {@link SelectionMode#SINGLE}, což znamená, že
     *      uživatel může vybrat pouze jednu položku najednou
     *      </ol>
     * <li> Nastaví minimální šířku pro komponentu (tj. minimální množství horizontálního
     * místa, které {@link ListView} může v rozložení využívat)
     * <li> Nastaví buňkovou továrnu (cell factory), určující, jak budou položky v seznamu
     * zobrazeny. V tomto případě nastaví zobrazení textových popisků (text) pro položky
     * typu {@link Mereni} a také nastaví písmo pro tyto popisky:
     *      <ol>
     *      <li> Vytváří novou buňku {@link ListCell} pro zobrazení položek v seznamu
     *      {@link ListView} pomocí anonymní třídě
     *      <li> Metoda definuje, jakým způsobem se mají aktualizovat buňky (popisky) pro
     *      jednotlivé položky seznamu. Tato metoda se volá automaticky pro každou položku,
     *      když se buňka má aktualizovat
     *      <li> Volá metodu rodičovské třídy (je-li možné provádět potřebné aktualizace)
     *      <li> pokud buňka není prázdná (empty) a zda položka (item) není nulová, tak:
     *              <ol>
     *              <li> nastaví text buňky na řetězec, který vznikne voláním metody
     *              {@code toString()} na objektu typu {@link Mereni} - {@link Mereni#toString()}.
     *              To znamená, že text buňky bude odpovídat textové reprezentaci objektu
     *              {@link Mereni}
     *              <li> nastaví písmo pro text v buňce. V tomto případě je písmo nastaveno
     *              na {@code Monospaced} (pevná šířka) s velikostí 13 bodů
     *              </ol>
     *      <li> pokud buňka je prázdná nebo položka je nulová, tak:
     *              <ol>
     *              <li> nastaví text buňky na prázdný řetězec
     *              </ol>
     *      </ol>
     * </ol>
     */
    private void nastavSeznamPanel() {
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.setMinWidth(MIN_SIRKA_SEZNAMU);
        this.setCellFactory(cell -> new ListCell<>() {
            @Override
            protected void updateItem(Mereni item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty && item != null) {
                    setText(item.toString());
                    setFont(Font.font(NAZEV_SEZNAM_FONTU, DIMENZE_SEZNAM_FONTU));
                } else {
                    setText(PRAZDNY_RETEZEC);
                }
            }
        });
    }
}

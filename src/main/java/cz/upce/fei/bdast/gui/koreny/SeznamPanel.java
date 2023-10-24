package cz.upce.fei.bdast.gui.koreny;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.data.vycty.Pozice;
import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;
import cz.upce.fei.bdast.kolekce.AbstrDoubleList;
import cz.upce.fei.bdast.spravce.SpravceMereni;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.text.Font;
import cz.upce.fei.bdast.gui.Titulek;

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
 * <p>
 * Třída je Singleton
 */
public final class SeznamPanel extends ListView<Mereni> {

    /**
     * Konstanta vyjadřuje minimální možnou šiřku seznamu prvků
     */
    private static final int MIN_SIRKA_SEZNAMU = 660;
    /**
     * Konstanty jsou potřebné pro nastavení této třídy v metodě {@link SeznamPanel#nastavSeznamPanel()}
     */
    private static final String NAZEV_SEZNAM_FONTU = "Monospaced";
    private static final int DIMENZE_SEZNAM_FONTU = 13;
    private static final String PRAZDNY_RETEZEC = "";
    /**
     * Privátní kosntanta pro kontroly na číslo nula. Používá se, aby se vyhnout
     * magickým číslem (magic numbers) v kódu
     */
    private static final int NULOVA_HODNOTA = 0;
    /**
     * Instance na správu seznamu {@link SpravceMereni}
     */
    private final SpravceMereni seznamMereni = SpravceMereni.getInstance();
    /**
     * Konstanta reprezentuje první pozici v seznamu. Používá se hlavně pri přidávání
     * prvků na začátek seznamu
     *
     * @see SeznamPanel#pridej(Mereni, Pozice)
     */
    private final int PRVNI_INDEX_SEZNAMU = 0;
    /**
     * Veřejná konstanta vyjadřuje číslo zvyšující velikost seznamu o jedničku
     * <p>
     * Používá se hlavně při dostávání id pro nový prvek v seznamu (například, při
     * operaci vložení), který je možné dostat sečtením aktuální velikosti seznamu
     * s touto konstantou, což vlastně dá číslo, které by bylo možné vyjadřit jako
     * id posledního prvku seznamu + jednička
     */
    public final int ZVETSOVAC_SEZNAMU = 1;
    /**
     * Konstanta vyjadřuje číslo zmenšující velikost seznamu o jedničku
     * <p>
     * Používá se hlavně při operací zpřistupňování, když je stisknuto tlačítko {@link Titulek#POSLEDNI}
     */
    private final int ZMENSOVAC_SEZNAMU = 1;
    /**
     * Konstanta slouží pro vložení prvku za prvek, na kterém je aktuální index zaměřený
     * pomocí vzorečku {@code aktualniIndex + 1}
     */
    private final int ZVETSOVAC_NASLEDNIKA = 1;
    /**
     * Privátní atribut sloužící k uchování indexu aktuálního vybraného prvku v seznamu.
     * Tento index označuje, na který prvek v seznamu je aktuálně zaměřený. Tím umožňuje
     * sledovat, který prvek v seznamu je aktuálně vybrán, což je zapotřebí pro navigaci
     * a manipulaci s prvky v seznamu
     */
    private int aktualniIndex = -1;
    /**
     * Konstanta reprezentuje pozici prvního prvku v seznamu
     */
    private final int INDEX_PRVNI = 0;

    private static SeznamPanel instance;

    /**
     * Tovární metoda (factory method) vratí existující nebo nově vytvořenou instanci
     * této třídy
     */
    public static SeznamPanel getInstance() {
        if (instance == null)
            instance = new SeznamPanel();
        return instance;
    }

    private SeznamPanel() { nastavSeznamPanel(); }

    /**
     * Přidá novou instanci třídy {@link Mereni} do seznamu a do vizuálního seznamu
     *
     * @param mereni nově vytvořená instance {@link Mereni}
     */
    public void pridej(Mereni mereni, Pozice pozice) {
        switch (pozice) {
            case PRVNI -> this.getItems().add(PRVNI_INDEX_SEZNAMU, mereni);
            case POSLEDNI -> this.getItems().add(mereni);
            case NASLEDNIK -> pridejNaslednika(mereni);
            case PREDCHUDCE -> pridejPredchudce(mereni);
        }
        seznamMereni.vlozMereni(mereni, pozice);
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Vloží nový prvek za aktuálně vybraný prvek v seznamu
     *
     * @param mereni nový prvek typu {@link Mereni}
     */
    private void pridejNaslednika(Mereni mereni) {
        if (jeIndexPlatny())
            this.getItems().add(aktualniIndex + ZVETSOVAC_NASLEDNIKA, mereni);
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Vloží nový prvek před aktuálně vybraným prvkem v seznamu
     *
     * @param mereni nový prvek typu {@link Mereni}
     */
    private void pridejPredchudce(Mereni mereni) {
        if (jeIndexPlatny()) {
            this.getItems().add(aktualniIndex, mereni);
            aktualniIndex++;
        }
    }

    /**
     * Nastaví interní ukazatel {@code aktualniIndex} na {@code 0} (první prvek) a zvolí
     * tento prvek v seznamu
     */
    public void posunNaPrvni() {
        aktualniIndex = INDEX_PRVNI;
        this.getSelectionModel().select(aktualniIndex);
    }

    /**
     * Nastaví interní ukazatel {@code aktualniIndex} na index posledního prvku v seznamu
     * a vybere tento prvek
     */
    public void posunNaPosledni() {
        aktualniIndex = this.getItems().size() - ZMENSOVAC_SEZNAMU;
        this.getSelectionModel().select(aktualniIndex);
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Zkontroluje, zda je {@code aktualniIndex} v platném rozmezí pro seznam prvků. To znamená, že se
     * ověřuje, zda je aktuální index větší než nebo roven {@code 0} (což značí platný index v seznamu)
     * a zároveň menší než počet prvků v seznamu (což značí, že index nevykazuje mimo rozsah seznamu)
     *
     * @return vrací {@code true}, pokud je index v platném rozmezí, v opačném případě {@code false}
     */
    private boolean jeIndexPlatny() { return aktualniIndex >= 0 && aktualniIndex < getItems().size(); }

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

    public int dejVelikost() { return this.getItems().size(); }

    public boolean jeSeznamPanelPrazdny() { return dejVelikost() == NULOVA_HODNOTA; }
}

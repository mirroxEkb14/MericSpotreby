package cz.upce.fei.bdast.gui.dialogy;

import cz.upce.fei.bdast.data.vycty.TypSenzoru;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.komponenty.KomponentVlozeniElektriky;
import cz.upce.fei.bdast.gui.komponenty.KomponentVlozeniVody;
import cz.upce.fei.bdast.gui.komponenty.PolozkaVlozeni;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.tvurce.TvurceMereni;

import java.util.Collection;

/**
 * Třída {@link Dialog} slouží k vytváření dialogových oken a interaktivních dialogů
 * v uživatelském rozhraní. Tato třída umožňuje snadno vytvářet dialogy pro
 * zobrazování zpráv, vstupu dat od uživatele a dalších interakcí. Dialogy jsou často
 * používány pro zobrazování důležitých zpráv, varování nebo pro vyžádání vstupu od
 * uživatele. {@link Dialog} a jeho odvozené třídy jsou užitečné pro komunikaci s
 * uživateli v aplikacích a pro získávání potřebných informací od uživatele v
 * interaktivní formě
 * <p>
 * Třída {@link ButtonType} reprezentuje různé typy tlačítek, které mohou být použity
 * v dialogových oknech a dalších uživatelských rozhraních.  je výčtový typ (enum),
 * což znamená, že definuje konkrétní hodnoty, které mohou být použity pro různé typy
 * tlačítek
 */
public final class DialogVlozeni extends Dialog<ButtonType> {

    /**
     * Statický atribut uchovává instanci dialogu s uživatelskými daty. Používá se
     * hladně při vytvoření nového {@link Mereni}
     *
     * @see TvurceMereni#dejNoveMereni(int, TypSenzoru)
     */
    private static PolozkaVlozeni dialogovyKomponent;

    public DialogVlozeni(int idSenzoru, TypSenzoru typSenzoru) {
        this.setTitle(Titulek.DIALOG_VLOZENI.getNadpis());
        nastavTlacitkaDialogu();
        nastavDialogVlozeni(idSenzoru, typSenzoru);
    }

    /**
     * Privátní pomocní metoda.
     * <p>
     * Nastaví komponentu pro toto dialogové okénko podle typy {@link Mereni}, zvolené
     * uživatelem u {@link ChoiceBox}
     *
     * <b>Poznámka</b>: případ {@code default} zde není zapotřebí, protože vstupní typ
     * senzoru bude nějakou hodnotou z již existujících ve výčtovém typu {@link TypSenzoru}
     * a na to již byla proveda kontrola na začátku metody {@code KomponentVlozeni.dejNoveMereni()},
     * která by nepovolila pokročování logiky
     */
    private void nastavDialogVlozeni(int idSenzoru, TypSenzoru typSenzoru) {
        switch (typSenzoru) {
            case ELEKTRIKA -> {
                dialogovyKomponent = new KomponentVlozeniElektriky(idSenzoru);
                this.getDialogPane().setContent((Node) dialogovyKomponent);
            }
            case VODA -> {
                dialogovyKomponent = new KomponentVlozeniVody(idSenzoru);
                this.getDialogPane().setContent((Node) dialogovyKomponent);
            }
        }
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Nastaví konfiguraci tlačítek dialogového okna ({@link Dialog}) a přidá tlačítka, jako
     * jsou tlačítka "OK" a "Storno" do dialogového okna:
     * <ul>
     * <li> <b>Dialog&lt;ButtonType&gt;</b>: instance dialogového okna, do kterého bude tlačítka
     * přidáno
     * <li> {@link Dialog#getDialogPane()}: získá odkaz na panel dialogového okna, což je vlastně
     * část dialogu, kde jsou zobrazovány obsah a tlačítka
     * <li> {@link DialogPane#getButtonTypes()} získá seznam dostupných typů tlačítek pro dialogové
     * okno. V tomto případě získá seznam, do kterého bude přidány další typy tlačítek
     * <li> {@link ObservableList#addAll(Collection)} přidá nová tlačítka do seznamu tlačítek
     * dialogového okna. V tomto příkladu jsou přidána dvě tlačítka: "OK" a "Storno," reprezentovaná
     * objekty {@code okTlacitko} a {@code cancelTlacitko}. Tato tlačítka budou k dispozici uživateli,
     * aby mohl vybrat odpovídající akci
     * </ul>
     */
    private void nastavTlacitkaDialogu() {
        final ButtonType okTlacitko = new ButtonType(
                Titulek.TLACITKO_FAJN.getNadpis(), ButtonBar.ButtonData.OK_DONE);
        final ButtonType cancelTlacitko = new ButtonType(
                Titulek.TLACITKO_ZRUSIT.getNadpis(), ButtonBar.ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(okTlacitko, cancelTlacitko);
    }

// <editor-fold defaultstate="collapsed" desc="Gettery">
    public static PolozkaVlozeni getDialogovyKomponent() { return dialogovyKomponent; }
// </editor-fold>
}

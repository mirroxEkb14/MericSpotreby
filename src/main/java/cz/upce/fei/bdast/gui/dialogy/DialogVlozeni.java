package cz.upce.fei.bdast.gui.dialogy;

import cz.upce.fei.bdast.data.vycty.TypSenzoru;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.komponenty.KomponentVlozeniElektriky;
import cz.upce.fei.bdast.gui.komponenty.KomponentVlozeniVody;
import cz.upce.fei.bdast.gui.komponenty.PolozkaVlozeni;
import javafx.scene.Node;
import javafx.scene.control.*;
import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.tvurce.TvurceMereni;

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
public final class DialogVlozeni extends Dialog<ButtonType>
        implements DialogovyKomponent {

    /**
     * Statický atribut uchovává instanci dialogu s uživatelskými daty. Používá se
     * hladně při vytvoření nového {@link Mereni}
     *
     * @see TvurceMereni#dejNoveMereni(int, TypSenzoru)
     */
    private static PolozkaVlozeni dialogovyKomponent;

    public DialogVlozeni(int idSenzoru, TypSenzoru typSenzoru) {
        this.setTitle(Titulek.DIALOG_VLOZENI.getNadpis());
        this.setDialogPane(this.dejNastavenaTlacitkaDialogu(this.getDialogPane()));
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

// <editor-fold defaultstate="collapsed" desc="Gettery">
    public static PolozkaVlozeni getDialogovyKomponent() { return dialogovyKomponent; }
// </editor-fold>
}
